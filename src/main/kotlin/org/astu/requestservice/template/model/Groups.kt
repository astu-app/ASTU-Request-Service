package org.astu.requestservice.template.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import lombok.Data
import lombok.NoArgsConstructor

@Entity
@Data
@NoArgsConstructor
class Groups (
    @Column(name = "id")
    @Id
    val id: Int,
    val name: String,
    @ManyToMany(mappedBy = "groups")
    val templates: MutableList<Template> = mutableListOf()
)