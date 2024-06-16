package org.astu.requestservice.requirement_type.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import org.astu.requestservice.requirement.model.Requirement
import java.util.*

@Entity
class RequirementType(
    @Id
    val id: UUID = UUID.randomUUID(),

    val name: String,

    @OneToMany(mappedBy = "requirementType")
    val requirements: List<Requirement> = listOf()
)