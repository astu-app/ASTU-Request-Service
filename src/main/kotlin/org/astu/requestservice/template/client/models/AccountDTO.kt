package api.account.client.models

import kotlinx.serialization.Serializable


class AccountDTO {
    val id: String? = null
    val firstName: String? = null
    val secondName: String? = null
    val patronymic: String? = null
    val isEmployee: Boolean = false
    val isStudent: Boolean= false
    val isAdmin: Boolean = false
    val isTeacher: Boolean = false
    val departmentId: String? = null
    val studentGroupId: String? = null
}

