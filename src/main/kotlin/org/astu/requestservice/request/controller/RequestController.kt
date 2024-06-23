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
    fun success(@RequestParam files: MultipartFile, @PathVariable id: UUID): String {
        return requestService.successRequest(id, listOf(files))
    }

    @PostMapping("{id}/approve")
    fun approve(@PathVariable id: UUID, @RequestParam comment: String): String {
        return requestService.successRequest(id, listOf(), comment)
    }

    @PostMapping("{id}/fail")
    fun fail(@PathVariable id: UUID, @RequestBody failDTO: FailRequestDTO): String {
        return requestService.failRequest(id, failDTO)
    }

    @GetMapping("user/{userId}")
    fun getRequestsForUser(@PathVariable userId: UUID): List<RequestDTO> {
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

    @PostMapping("user/{requestId}/delete")
    fun removeRequest(@PathVariable requestId: UUID): String {
        return requestService.removeRequest(requestId)
    }
}