package api.account.client.models

import kotlinx.serialization.Serializable


@Serializable
class AccountDTO {
    var id: String? = null
    var firstName: String? = null
    var secondName: String? = null
    var patronymic: String ? = null
    var isEmployee: Boolean? = null
    var isStudent: Boolean? = null
    var isAdmin: Boolean? = null
    var isTeacher: Boolean? = null
    var employeeInfo: EmployeeInfoDTO? = null
    var studentInfo: StudentInfoDTO? = null
    var teacherInfo: TeacherInfoDTO? = null
}
