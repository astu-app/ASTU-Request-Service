package org.astu.requestservice.request.controller

import org.astu.requestservice.request.RequestService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.*

@RestController
@RequestMapping("api/request")
class RequestController(private val requestService: RequestService) {
    @PostMapping("{id}/success", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun success(@RequestParam files: MultipartFile, @PathVariable id: UUID) {
        requestService.successRequest(id, listOf(files))
    }

    @PostMapping("{id}/fail")
    fun fail(@PathVariable id: UUID, @RequestBody failDTO: FailRequestDTO) {
        requestService.failRequest(id, failDTO)
    }

    @GetMapping("user/{userId}")
    fun getRequestsForUser(@PathVariable userId: UUID): List<RequestDTO>{
        return requestService.getForUser(userId)
    }

    @GetMapping("employee/{departmentId}")
    fun getRequestsForEmployee(@PathVariable departmentId: UUID): List<RequestDTO> {
        return requestService.getForEmployee(departmentId)
    }

    @PostMapping("user/{userId}")
    fun addRequest(@RequestBody addRequestDTO: AddRequestDTO, @PathVariable userId: UUID): UUID {
        return requestService.addRequest(userId, addRequestDTO)
    }

    @DeleteMapping("user/{requestId}")
    fun removeRequest(@PathVariable requestId: UUID) {
        requestService.removeRequest(requestId)
    }
}