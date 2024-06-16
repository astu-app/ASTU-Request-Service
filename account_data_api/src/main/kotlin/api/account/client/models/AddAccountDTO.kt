package api.account.client.models

class AddAccountDTO(
    val firstName: String,
    val secondName: String,
    val patronymic: String? = null,
    val addStudentInfoDTO: AddStudentInfoDTO? = null,
    val addEmployeeInfoDTO: AddEmployeeInfoDTO? = null
)

class AddStudentInfoDTO(
    val studentGroup: String
)

class AddEmployeeInfoDTO(
    val department: String,
    val role: String
)