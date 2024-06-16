package org.astu.requestservice.request

import org.astu.requestservice.request.model.RequirementField
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RequirementFieldRepository : JpaRepository<RequirementField, UUID>,
    JpaSpecificationExecutor<RequirementField>