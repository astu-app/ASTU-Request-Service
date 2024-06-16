package org.astu.requestservice.request

import org.astu.requestservice.request.model.Request
import org.astu.requestservice.request.model.RequestStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RequestRepository : JpaRepository<Request, UUID> {

    fun findByUserId(userId: UUID): List<Request>


    fun findByTemplate_DepartmentIdAndStatus(departmentId: UUID, status: RequestStatus): List<Request>
}