package org.astu.requestservice.request

import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class EmailServiceImpl(
    private var emailSender: JavaMailSender,
    @Value("\${spring.mail.email}")
    private var emailFrom: String
) : EmailService {


    override fun sendEmail(email: String, subject: String, body: String, files: List<MultipartFile>) {
        val message = emailSender.createMimeMessage()

        val helper = MimeMessageHelper(message, true)
        helper.setFrom(emailFrom)
        helper.setTo(email)
        helper.setSubject(subject)
        helper.setText(body)

        for (file in files) {
            val source = { file.inputStream }
            if (file.contentType != null)
                helper.addAttachment(file.name, source, file.contentType!!)
            else
                helper.addAttachment(file.name, source)
        }

        emailSender.send(message)
    }
}