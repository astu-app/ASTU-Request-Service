package org.astu.requestservice.request.controller

import org.astu.requestservice.request.model.RequestStatus
import org.astu.requestservice.request.model.RequestType
import java.time.Instant
import java.util.*

class RequestDTO(
    val id: UUID,
    val name: String,
    val description: String,
    val userId: UUID,
    val type: RequestType,
    val status: RequestStatus,
    val message: String? = null,
    val createdAt: Instant,
    val fields: List<RequirementFieldDTO>,
)