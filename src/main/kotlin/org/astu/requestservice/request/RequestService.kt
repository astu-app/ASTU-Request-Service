package org.astu.requestservice.request

import jakarta.transaction.Transactional
import org.astu.requestservice.exception.CommonException
import org.astu.requestservice.request.controller.AddRequestDTO
import org.astu.requestservice.request.controller.AddRequirementFieldDTO
import org.astu.requestservice.request.controller.FailRequestDTO
import org.astu.requestservice.request.controller.RequestDTO
import org.astu.requestservice.request.mapper.RequestMapper
import org.astu.requestservice.request.model.Request
import org.astu.requestservice.request.model.RequestStatus
import org.astu.requestservice.request.model.RequestType
import org.astu.requestservice.request.model.RequirementField
import org.astu.requestservice.requirement.model.Requirement
import org.astu.requestservice.template.TemplateRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Service
class RequestService(
    private val requestRepository: RequestRepository,
    private val requestMapper: RequestMapper,
    private val templateRepository: TemplateRepository,
    private val requirementFieldRepository: RequirementFieldRepository,
    private val emailService: EmailService
) {
    fun getForUser(userId: UUID): List<RequestDTO> = requestRepository.findByUserId(userId).map(requestMapper::toDto)
    fun getForEmployee(departmentId: UUID): List<RequestDTO> =
        requestRepository.findByTemplate_DepartmentIdAndStatus(departmentId, RequestStatus.InProgress)
            .map(requestMapper::toDto)

    @Transactional
    fun addRequest(userId: UUID, dto: AddRequestDTO): UUID {
        if (dto.email.isNullOrBlank() && dto.type == RequestType.Email)
            throw CommonException(HttpStatus.NOT_FOUND, "Запрос требует заполненного поля электронной почты")
        val template = templateRepository.findById(dto.templateId)
            .orElseThrow { CommonException(HttpStatus.NOT_FOUND, "Не удалось найти шаблон с id ${dto.templateId}") }
        val request = Request(template = template, userId = userId, type = dto.type, email = dto.email)
        requestRepository.save(request)

        dto.fields.forEach { field ->
            val requirement = template.requirements.find { it.id == field.requirementId }!!
            validateField(field, requirement)
            createField(field, requirement, request)
        }
        return request.id
    }

    @Transactional
    fun successRequest(requestId: UUID, files: List<MultipartFile>) {
        val request = requestRepository.findById(requestId)
            .orElseThrow { CommonException(HttpStatus.NOT_FOUND, "Не удалось найти заявление с id $requestId") }
        request.status = RequestStatus.Success
        runCatching {
            requestRepository.save(request)
        }.onFailure {
            throw CommonException(HttpStatus.NOT_FOUND, "Не удалось одобрить заявление")
        }
        if (request.type == RequestType.Email) {
            runCatching {
                emailService.sendEmail(
                    request.email!!,
                    "Заявка была одобрена",
                    "Заявка на \"${request.template.name}\" была одобрена",
                    files
                )
            }.onFailure {
                println(it.message)
                println(it)
                throw CommonException(HttpStatus.BAD_REQUEST, "Не удалось отправить сообщение")
            }
        }
    }

    fun failRequest(requestId: UUID, failRequestDTO: FailRequestDTO) {
        val request = requestRepository.findById(requestId)
            .orElseThrow { CommonException(HttpStatus.NOT_FOUND, "Не удалось найти заявление с id $requestId") }
        request.status = RequestStatus.Denied
        request.message = failRequestDTO.message
        kotlin.runCatching {
            requestRepository.save(request)
        }.onFailure {
            throw CommonException(HttpStatus.NOT_FOUND, "Не удалось отказать в заявлении")
        }
    }

    fun removeRequest(requestId: UUID) {
        val request = requestRepository.findById(requestId)
            .orElseThrow { CommonException(HttpStatus.NOT_FOUND, "Не удалось найти заявление с id $requestId") }
        request.status = RequestStatus.Removed
        kotlin.runCatching {
            requestRepository.save(request)
        }.onFailure {
            throw CommonException(HttpStatus.NOT_FOUND, "Не удалось отменить заявление")
        }
    }

    private fun validateField(field: AddRequirementFieldDTO, requirement: Requirement) {
        if (requirement.isMandatory && field.value == "null")
            throw CommonException(HttpStatus.NOT_FOUND, "Поле \"${requirement.name}\" обязательно к заполнению")
    }

    private fun createField(dto: AddRequirementFieldDTO, requirement: Requirement, request: Request) {
        val field = RequirementField(request = request, requirement = requirement, value = dto.value)
        kotlin.runCatching {
            requirementFieldRepository.save(field)
        }.onFailure {
            throw CommonException(HttpStatus.NOT_FOUND, "Не удалось создать заявление")
        }
    }
}