package com.example.pr2_contacts

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class EditActivity : AppCompatActivity() {

    private val viewModel: ContactViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val etSurname = findViewById<EditText>(R.id.etSurnameEdit)
        val etName = findViewById<EditText>(R.id.etNameEdit)
        val etPatronymic = findViewById<EditText>(R.id.etPatronymicEdit)
        val etPhone = findViewById<EditText>(R.id.etPhoneEdit)
        val btnSave = findViewById<Button>(R.id.btnSaveEdit)

        // Получаем данные из MainActivity через Intent
        val surname = intent.getStringExtra("surname") ?: ""
        val name = intent.getStringExtra("name") ?: ""
        val patronymic = intent.getStringExtra("patronymic") ?: ""
        val phone = intent.getStringExtra("phone") ?: ""

        etSurname.setText(surname)
        etName.setText(name)
        etPatronymic.setText(patronymic)
        etPhone.setText(phone)

        btnSave.setOnClickListener {
            val newSurname = etSurname.text.toString()
            val newName = etName.text.toString()
            val newPatronymic = etPatronymic.text.toString()
            val newPhone = etPhone.text.toString()

            if (newSurname.isEmpty() && newName.isEmpty()) {
                Toast.makeText(this, "Введите хотя бы фамилию или имя", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val updatedContact = Contact(newSurname, newName, newPatronymic, newPhone)
            viewModel.updateCurrentContact(updatedContact)

            finish()
        }
    }
}
