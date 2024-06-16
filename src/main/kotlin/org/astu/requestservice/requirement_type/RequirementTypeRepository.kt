package org.astu.requestservice.requirement_type

import org.astu.requestservice.requirement_type.model.RequirementType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RequirementTypeRepository : JpaRepository<RequirementType, UUID>