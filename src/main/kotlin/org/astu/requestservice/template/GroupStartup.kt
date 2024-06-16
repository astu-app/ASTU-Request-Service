package org.astu.requestservice.template

import org.astu.requestservice.template.model.Groups
import org.astu.requestservice.template.model.UserGroup
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class GroupStartup(private val groupsRepository: GroupsRepository) {
    @EventListener
    fun initialize(ignoredEvent: ApplicationReadyEvent?) {
        val groups = groupsRepository.findAll()
        UserGroup.entries.forEach { entry ->
            val group = groups.firstOrNull { it.id == entry.ordinal }
            if (group == null || group.name != entry.name)
                groupsRepository.save(Groups(id = entry.ordinal, name = entry.name))
        }
    }
}