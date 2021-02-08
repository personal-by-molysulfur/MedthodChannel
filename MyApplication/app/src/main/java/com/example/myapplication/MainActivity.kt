package com.example.myapplication

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity() {

    private lateinit var button: AppCompatButton
    private lateinit var text: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.button_view)
        text = findViewById(R.id.text_view)
        button.setOnClickListener {
            val intent = Intent()
            intent.component = ComponentName(
                "com.molysulfur.example.flutter_app",
                "com.molysulfur.example.flutter_app.MainActivity"
            );
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(intent, 1000)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1000) {
                text.text = data?.extras?.getString("text","No Intent") ?: "No Intent"
            }
        }
    }
}