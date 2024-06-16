package org.astu.requestservice.template

import org.astu.requestservice.template.model.Groups
import org.springframework.data.jpa.repository.JpaRepository

interface GroupsRepository : JpaRepository<Groups, Int>