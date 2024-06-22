package org.astu.requestservice.template.controller

import api.account.client.models.AccountDTO
import org.astu.requestservice.AccountClient
import org.astu.requestservice.template.TemplateService
import org.astu.requestservice.template.model.UserGroup
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("api/template")
class TemplateController(
    private val templateService: TemplateService,
    private val accountClient: AccountClient
) {

    @PostMapping
    fun addTemplate(@RequestBody addTemplateDTO: AddTemplateDTO): UUID {
        return templateService.addTemplate(addTemplateDTO)
    }

    @PostMapping("{accountId}")
    fun getTemplates(@PathVariable accountId: UUID, @RequestBody accountDTO: AccountDTO): List<TemplateDTO> {
//        val account = accountClient.getAccount(accountId)
        val userGroups: List<UserGroup> = getGroups(accountDTO)
        userGroups.forEach{
            println(it)
        }
        return templateService.getTemplates(userGroups)
    }

    private fun getGroups(account: AccountDTO): List<UserGroup> {
//        if (account.isAdmin)
//            return listOf(UserGroup.Student, UserGroup.Employee, UserGroup.Graduate)
        val groups: MutableList<UserGroup> = mutableListOf()
        if (account.isStudent) {
            groups.add(UserGroup.Student)
            groups.add(UserGroup.Graduate)
        }
        if (account.isEmployee || account.isTeacher)
            groups.add(UserGroup.Employee)
        return groups
    }
}