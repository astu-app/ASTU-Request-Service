package org.astu.requestservice.request.controller

/**
 * DTO for {@link org.astu.requestservice.request.model.RequirementField}
 */
data class RequirementFieldDTO(
    val name: String,
    val description: String,
    val type: String,
    val value: String
)