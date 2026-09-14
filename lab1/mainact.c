package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val curNum = findViewById<EditText>(R.id.EditTextCur)

        // Цифры 0-9
        val digitButtons = listOf(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3,
            R.id.button4, R.id.button5, R.id.button6, R.id.button7,
            R.id.button8, R.id.button9
        )
        for (id in digitButtons) {
            findViewById<Button>(id).setOnClickListener {
                val digit = (it as Button).text.toString()
                curNum.append(digit)
            }
        }

        // Точка
        val btnDot: Button = findViewById(R.id.buttonDot)
        btnDot.setOnClickListener {
            val text = curNum.text.toString()
            if (text.isEmpty()) {
                curNum.append("0.")
            } else if (!text.contains(".")) {
                curNum.append(".")
            }
        }
    }
}
