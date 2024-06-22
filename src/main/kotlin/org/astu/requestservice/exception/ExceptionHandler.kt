package org.astu.requestservice.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.ServletWebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDateTime


@ControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {
    private val ERROR_400: String = "Некорректный запрос: "
    private val ERROR_403: String = "Ошибка безопасности: "
    private val ERROR_404: String = "Сущность не найдена: "
    private val ERROR_409: String = "Некорректный запрос: "
    private val ERROR_413: String = "Превышено ограничение: "
    private val ERROR_500: String = "Внутренняя ошибка сервера: "

    @ExceptionHandler(CommonException::class)
    fun handleCommonException(
        exception: CommonException,
        request: ServletWebRequest
    ): ResponseEntity<ErrorResponse> {
        println()
        println()
        println(exception.status)
        println(exception.message)
        val errorResponse = ErrorResponse(LocalDateTime.now(), exception.status.value(), ERROR_404 + exception.message)
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }
}

