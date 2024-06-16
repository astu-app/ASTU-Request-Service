package org.astu.requestservice.requirement_type.controller

import org.astu.requestservice.requirement_type.RequirementTypeService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/requirement-type")
class RequirementTypeController(private val requirementTypeService: RequirementTypeService) {
    @GetMapping
    fun get(): List<RequirementTypeDTO> = requirementTypeService.getAll()

    @PostMapping
    fun add(@RequestBody dto: AddRequirementTypeDTO): RequirementTypeDTO = requirementTypeService.addRequirementType(dto)
}