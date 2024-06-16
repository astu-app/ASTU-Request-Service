package org.astu.requestservice.requirement.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import org.astu.requestservice.requirement_type.model.RequirementType
import org.astu.requestservice.template.model.Template
import java.util.*

@Entity
class Requirement(
    @Id
    val id: UUID = UUID.randomUUID(),

    val name: String,
    val description: String,
    val isMandatory: Boolean,

    @ManyToOne
    val requirementType: RequirementType,

    @ManyToOne
    val template: Template
)