package org.astu.requestservice.requirement

import org.astu.requestservice.requirement.model.Requirement
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RequirementRepository : JpaRepository<Requirement, UUID>