package org.astu.requestservice.request.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import org.astu.requestservice.template.model.Template
import java.time.Instant
import java.util.*

@Entity
class Request(
    @Id
    val id: UUID = UUID.randomUUID(),

    @ManyToOne
    val template: Template,

    var status: RequestStatus = RequestStatus.InProgress,
    var message: String? = null,

    val type: RequestType,
    val email: String? = null,

    @OneToMany(mappedBy = "request")
    val fields: List<RequirementField> = listOf(),

    val createdAt: Instant = Instant.now(),

    val userId: UUID
)

