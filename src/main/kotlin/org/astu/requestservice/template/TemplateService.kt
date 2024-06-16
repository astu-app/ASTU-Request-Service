package org.astu.requestservice.template

import jakarta.transaction.Transactional
import org.astu.requestservice.requirement.RequirementRepository
import org.astu.requestservice.requirement.model.Requirement
import org.astu.requestservice.requirement_type.RequirementTypeRepository
import org.astu.requestservice.requirement_type.model.RequirementType
import org.astu.requestservice.template.controller.AddRequirementDTO
import org.astu.requestservice.template.controller.AddTemplateDTO
import org.astu.requestservice.template.controller.TemplateDTO
import org.astu.requestservice.template.mapper.TemplateMapper
import org.astu.requestservice.template.model.Template
import org.astu.requestservice.template.model.UserGroup
import org.springframework.stereotype.Service
import java.util.*

@Service
class TemplateService(
    private val templateMapper: TemplateMapper,
    private val templateRepository: TemplateRepository,
    private val requirementTypeRepository: RequirementTypeRepository,
    private val requirementRepository: RequirementRepository,
    private val groupsRepository: GroupsRepository
) {
    @Transactional
    fun addTemplate(dto: AddTemplateDTO): UUID {
        val groups = groupsRepository.findAll().filter { dto.groups.any { group -> group.ordinal == it.id } }

        val template = Template(
            name = dto.name,
            description = dto.description,
            departmentId = dto.departmentId,
            category = dto.category
        )
        template.groups = groups.toMutableList()

        templateRepository.save(template)

        val types = requirementTypeRepository.findAll()
        dto.requirements.forEach { requirements ->
            val type = types.find { it.id == requirements.requirementTypeId }
                ?: throw RuntimeException("Requirement type not found")
            createRequirement(requirements, type, template)
        }

        return template.id
    }

    fun getTemplates(userGroups: List<UserGroup>): List<TemplateDTO> {
        return templateRepository.findAll()
            .filter { template -> userGroups.any { group -> template.groups.any { group.ordinal == it.id } } }
            .map { templateMapper.map(it) }
    }

    private fun createRequirement(dto: AddRequirementDTO, requirementType: RequirementType, template: Template) {
        val requirement = Requirement(
            name = dto.name,
            description = dto.description,
            isMandatory = dto.isMandatory,
            requirementType = requirementType,
            template = template
        )
        requirementRepository.save(requirement)
    }
}