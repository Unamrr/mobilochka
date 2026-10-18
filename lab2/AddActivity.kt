package com.example.pr2_contacts

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class AddActivity : AppCompatActivity() {

    private val viewModel: ContactViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val etSurname = findViewById<EditText>(R.id.etSurname)
        val etName = findViewById<EditText>(R.id.etName)
        val etPatronymic = findViewById<EditText>(R.id.etPatronymic)
        val etPhone = findViewById<EditText>(R.id.etPhone)
        val btnSave = findViewById<Button>(R.id.btnSaveAdd)

        btnSave.setOnClickListener {
            val surname = etSurname.text.toString()
            val name = etName.text.toString()
            val patronymic = etPatronymic.text.toString()
            val phone = etPhone.text.toString()

            if (surname.isEmpty() && name.isEmpty()) {
                Toast.makeText(this, "Введите хотя бы фамилию или имя", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val newContact = Contact(surname, name, patronymic, phone)
            viewModel.addContact(newContact)

            finish()
        }
    }
}
