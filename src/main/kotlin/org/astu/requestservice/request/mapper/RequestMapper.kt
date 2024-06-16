package org.astu.requestservice.request.mapper

import org.astu.requestservice.request.controller.RequestDTO
import org.astu.requestservice.request.controller.RequirementFieldDTO
import org.astu.requestservice.request.model.Request
import org.astu.requestservice.request.model.RequirementField
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
abstract class RequestMapper {
    @Mapping(target = "name", source = "template.name")
    @Mapping(target = "description", source = "template.description")
    abstract fun toDto(request: Request): RequestDTO

    @Mapping(target = "name", source = "requirement.name")
    @Mapping(target = "description", source = "requirement.description")
    @Mapping(target = "type", source = "requirement.requirementType.name")
    abstract fun toDto(requestDTO: RequirementField): RequirementFieldDTO
}