package com.example.map

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onclick(view: View) {
        login(
            findViewById<TextInputEditText>(R.id.edtUserName).text.toString(),
            findViewById<TextInputEditText>(R.id.edtPassword).text.toString()
        )
    }

    fun login(userName: String, password: String) {
        if (userName == "name" && password == "1234") {
            startActivity(Intent(this, MapsActivity::class.java))
            Toast.makeText(this, "อัญญมณี พุกแก้ว 6204101361 เข้าสู่ระบบ", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "เกิดข้อผิดพลาด", Toast.LENGTH_SHORT).show()
        }
    }
}