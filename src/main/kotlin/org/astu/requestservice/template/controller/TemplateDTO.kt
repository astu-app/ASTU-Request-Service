package org.astu.requestservice.template.controller

import java.util.*

class TemplateDTO(
    val id: UUID,
    val name: String,
    val description: String,
    val category: String,
    val requirements: List<RequirementDto>
)

