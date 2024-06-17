package org.astu.requestservice.request

import jakarta.transaction.Transactional
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
            throw IllegalArgumentException("Email is required for email requests")
        val template = templateRepository.findById(dto.templateId)
        val request = Request(template = template.get(), userId = userId, type = dto.type, email = dto.email)
        requestRepository.save(request)

        dto.fields.forEach { field ->
            val requirement = template.get().requirements.find { it.id == field.requirementId }!!
            validateField(field, requirement)
            createField(field, requirement, request)
        }
        return request.id
    }

    @Transactional
    fun successRequest(requestId: UUID, files: List<MultipartFile>) {
        val request = requestRepository.findById(requestId).orElseThrow()
        request.status = RequestStatus.Success
        requestRepository.save(request)
//        if (request.type == RequestType.Email) {
//            emailService.sendEmail(
//                request.email!!,
//                "Your request has been successfully submitted",
//                "Your request has been successfully submitted",
//                files
//            )
//        }
    }

    fun failRequest(requestId: UUID, failRequestDTO: FailRequestDTO) {
        val request = requestRepository.findById(requestId).orElseThrow()
        request.status = RequestStatus.Denied
        request.message = failRequestDTO.message
        requestRepository.save(request)
    }

    fun removeRequest(requestId: UUID) {
        val request = requestRepository.findById(requestId).orElseThrow()
        request.status = RequestStatus.Removed
        requestRepository.save(request)
    }

    private fun validateField(field: AddRequirementFieldDTO, requirement: Requirement) {
        if (requirement.isMandatory && field.value == "null")
            throw IllegalArgumentException("Field ${requirement.name} is mandatory")
    }

    private fun createField(dto: AddRequirementFieldDTO, requirement: Requirement, request: Request) {
        val field = RequirementField(request = request, requirement = requirement, value = dto.value)
        requirementFieldRepository.save(field)
    }
}