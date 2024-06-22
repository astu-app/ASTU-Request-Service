package org.astu.requestservice.request

import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class EmailServiceImpl(
    @Value("\${spring.mail.email}")
    private var emailFrom: String,
    @Value("\${spring.mail.username}")
private var username: String,
    @Value("\${spring.mail.port}")
    private var port: String,
    @Value("\${spring.mail.host}")
    private var host: String,
    @Value("\${spring.mail.password}")
    private var password: String,
    @Value("\${spring.mail.properties.mail.smtp.starttls.enable}")
    private var starttls: String,
    @Value("\${spring.mail.properties.mail.smtp.auth}")
    private var auth: String,

) : EmailService {
    fun createEmailSender(): JavaMailSenderImpl{
        val mailSender = JavaMailSenderImpl()
        mailSender.host = host
        mailSender.port = port.toInt()

        mailSender.username = username
        mailSender.password = password

        val props = mailSender.javaMailProperties
        props["mail.transport.protocol"] = "smtp"
        props["mail.smtp.auth"] = auth
        props["mail.smtp.starttls.enable"] = starttls
        props["mail.debug"] = "true"
        return mailSender
    }

    override fun sendEmail(email: String, subject: String, body: String, files: List<MultipartFile>) {
        val emailSender = createEmailSender()

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