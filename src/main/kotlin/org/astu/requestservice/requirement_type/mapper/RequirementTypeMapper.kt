package org.astu.requestservice.requirement_type.mapper

import org.astu.requestservice.requirement_type.controller.RequirementTypeDTO
import org.astu.requestservice.requirement_type.model.RequirementType
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
abstract class RequirementTypeMapper {
    abstract fun toDto(requirementType: RequirementType): RequirementTypeDTO
}