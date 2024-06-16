package org.astu.requestservice.template.controller

import java.util.*

class RequirementDto(
    val id: UUID,
    val name: String,
    val description: String,
    val requirementType: String,
    val isMandatory: Boolean
)