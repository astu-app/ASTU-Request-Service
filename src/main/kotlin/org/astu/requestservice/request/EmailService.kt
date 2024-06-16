package org.astu.requestservice.request

import org.springframework.web.multipart.MultipartFile

interface EmailService {
    fun sendEmail(email: String, subject: String, body: String, files: List<MultipartFile> = listOf())
}