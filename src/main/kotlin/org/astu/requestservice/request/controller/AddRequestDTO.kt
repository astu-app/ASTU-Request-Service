package org.astu.requestservice.request.controller

import jakarta.validation.constraints.Email
import org.astu.requestservice.request.model.RequestType
import java.util.*

class AddRequestDTO(
    val templateId: UUID,
    val type: RequestType,
    @Email
    val email: String?,
    val fields: List<AddRequirementFieldDTO>
)

