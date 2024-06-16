package org.astu.requestservice.template.controller

import java.util.*

class AddRequirementDTO(
    val requirementTypeId: UUID,
    val name: String,
    val description: String,
    val isMandatory: Boolean
)