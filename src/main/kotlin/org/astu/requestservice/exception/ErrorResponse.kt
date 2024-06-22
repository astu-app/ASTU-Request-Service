package org.astu.requestservice.exception

import java.time.LocalDateTime

/**
 * Ответ с ошибкой
 */
data class ErrorResponse(
    /** Метка времени возникновения ошибки  */
    private val timestamp: LocalDateTime? = null,

    /** Код состояния HTTP  */
    private val status: Int? = null,

    /** Сообщение (по умолчанию пустое)  */
    private val message: String? = null,
)