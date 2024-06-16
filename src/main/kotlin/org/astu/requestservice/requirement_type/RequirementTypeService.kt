package org.astu.requestservice.requirement_type

import jakarta.transaction.Transactional
import org.astu.requestservice.requirement_type.controller.AddRequirementTypeDTO
import org.astu.requestservice.requirement_type.controller.RequirementTypeDTO
import org.astu.requestservice.requirement_type.mapper.RequirementTypeMapper
import org.astu.requestservice.requirement_type.model.RequirementType
import org.springframework.stereotype.Service

@Service
class RequirementTypeService(
    private val requirementTypeRepository: RequirementTypeRepository,
    private val requirementTypeMapper: RequirementTypeMapper
) {

    fun getAll(): List<RequirementTypeDTO> = requirementTypeRepository.findAll().map(requirementTypeMapper::toDto)

    @Transactional
    fun addRequirementType(dto: AddRequirementTypeDTO): RequirementTypeDTO {
        val requirementType = RequirementType(name = dto.name)
        return requirementTypeRepository.save(requirementType).let { requirementTypeMapper.toDto(it) }
    }
}