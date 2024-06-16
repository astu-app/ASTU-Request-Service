package org.astu.requestservice.request.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import org.astu.requestservice.requirement.model.Requirement
import java.util.*

@Entity
class RequirementField(
    @Id
    val id: UUID = UUID.randomUUID(),

    @ManyToOne
    val request: Request,

    @ManyToOne
    val requirement: Requirement,

    val value: String
)