package org.astu.requestservice

import api.account.client.models.AccountDTO
import kotlinx.serialization.json.Json
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient
import java.util.*

@Component
class AccountClient(
    @Value("\${service.host}")
    private val serviceHost: String
) {

    fun getAccount(userId: UUID): AccountDTO {
        /**
         * Получение userGroups из данных об аккаунте
         */
        val restClient = RestClient.create()
        val host = if (serviceHost.endsWith("/")) serviceHost else "$serviceHost/"
//        return AccountApi(client).getAccount(userId.toString())
        val account =  restClient.get()
            .uri("$host/api/account/$userId")
            .exchange { request, response ->
                if (response.statusCode.is2xxSuccessful) {
                    val body = response.bodyTo(String::class.java)
                    val account = Json.decodeFromString<AccountDTO>(body!!)
                    return@exchange account
                } else
                    throw Exception("Request failed with status code ${response.statusCode}")
            }
        return account
    }

}