package org.astu.requestservice.template.controller

import org.astu.requestservice.template.model.UserGroup
import java.util.*

class AddTemplateDTO(
    val name: String,
    val description: String,
    val category: String,

    val departmentId: UUID,

    val requirements: List<AddRequirementDTO>,

    val groups: List<UserGroup>
)