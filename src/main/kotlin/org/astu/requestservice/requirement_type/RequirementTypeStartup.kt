package org.astu.requestservice.requirement_type

import org.astu.requestservice.requirement_type.model.RequirementType
import org.astu.requestservice.requirement_type.model.RequirementTypeEnum
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class RequirementTypeStartup(
    private val requirementTypeRepository: RequirementTypeRepository
) {
    @EventListener
    fun initialize(ignoredEvent: ApplicationReadyEvent?) {
        val types = requirementTypeRepository.findAll()
        RequirementTypeEnum.entries.forEach { entry ->
            val type = types.firstOrNull { it.name == entry.name.uppercase() }
            if (type == null)
                requirementTypeRepository.save(RequirementType(name = entry.name.uppercase()))
        }
    }
}