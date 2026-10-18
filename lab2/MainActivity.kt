package com.example.pr2_contacts

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val viewModel: ContactViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvContactName = findViewById<TextView>(R.id.tvContactName)
        val btnPrev = findViewById<Button>(R.id.btnPrev)
        val btnNext = findViewById<Button>(R.id.btnNext)
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnEdit = findViewById<Button>(R.id.btnEdit)
        val btnDelete = findViewById<Button>(R.id.btnDelete)

        viewModel.contacts.observe(this) { updateUI(tvContactName) }
        viewModel.currentIndex.observe(this) { updateUI(tvContactName) }

        btnPrev.setOnClickListener { viewModel.prevContact() }
        btnNext.setOnClickListener { viewModel.nextContact() }

        btnAdd.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }

        btnEdit.setOnClickListener {
            val current = viewModel.getCurrentContact()
            if (current == null) {
                Toast.makeText(this, "Нет контактов для изменения", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, EditActivity::class.java)
            intent.putExtra("surname", current.surname)
            intent.putExtra("name", current.name)
            intent.putExtra("patronymic", current.patronymic)
            intent.putExtra("phone", current.phone)
            startActivity(intent)
        }

        btnDelete.setOnClickListener {
            viewModel.deleteCurrentContact()
            Toast.makeText(this, "Контакт удален", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI(tvContactName: TextView) {
        val contact = viewModel.getCurrentContact()
        tvContactName.text = contact?.getShortName() ?: "Нет контактов"
    }
}
