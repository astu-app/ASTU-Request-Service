package org.astu.requestservice.template.model

import jakarta.persistence.*
import lombok.NoArgsConstructor
import lombok.RequiredArgsConstructor
import org.astu.requestservice.request.model.Request
import org.astu.requestservice.requirement.model.Requirement
import java.util.*

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
class Template(
    @Id
    @Column(name = "id")
    val id: UUID = UUID.randomUUID(),

    val name: String,
    val description: String,
    val category: String,

    @OneToMany(mappedBy = "template")
    val requirements: List<Requirement> = listOf(),

    @OneToMany(mappedBy = "template")
    val requests: List<Request> = listOf(),

    @ManyToMany
    var groups: MutableList<Groups> = mutableListOf(),

    val departmentId: UUID
)

