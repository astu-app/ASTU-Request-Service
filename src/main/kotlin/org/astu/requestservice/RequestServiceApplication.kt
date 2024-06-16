package org.astu.requestservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RequestServiceApplication

fun main(args: Array<String>) {
    runApplication<RequestServiceApplication>(*args)
}
