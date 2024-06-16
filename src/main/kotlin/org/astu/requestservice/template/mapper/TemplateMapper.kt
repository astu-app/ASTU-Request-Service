package org.astu.requestservice.template.mapper

import org.astu.requestservice.requirement.model.Requirement
import org.astu.requestservice.template.controller.AddTemplateDTO
import org.astu.requestservice.template.controller.RequirementDto
import org.astu.requestservice.template.controller.TemplateDTO
import org.astu.requestservice.template.model.Template
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
abstract class TemplateMapper {
    @Mapping(target = "groups", ignore = true)
    @Mapping(target = "requirements", ignore = true)
    @Mapping(target = "requests", ignore = true)
    @Mapping(target = "id", ignore = true)
    abstract fun map(dto: AddTemplateDTO): Template

    abstract fun map(template: Template): TemplateDTO

    @Mapping(target = "requirementType", source = "requirementType.name")
    abstract fun map(dto: Requirement): RequirementDto
}