package org.astu.requestservice.template

import org.astu.requestservice.template.model.Template
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TemplateRepository : JpaRepository<Template, UUID>, JpaSpecificationExecutor<Template>