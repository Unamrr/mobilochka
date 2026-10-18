package com.example.pr2_contacts

data class Contact(
    var surname: String = "",
    var name: String = "",
    var patronymic: String = "",
    var phone: String = ""
) {

    fun getShortName(): String {
        if (surname.isEmpty() && name.isEmpty()) return "Нет контактов"

        val nameLetter = if (name.isNotEmpty()) name.first() else ' '
        val patronymicLetter = if (patronymic.isNotEmpty()) patronymic.first() else ' '

        return "$surname $nameLetter.$patronymicLetter."
    }
}
