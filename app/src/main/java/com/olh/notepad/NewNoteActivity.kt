package com.olh.notepad

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class NewNoteActivity : AppCompatActivity() {

    private val categories = listOf("İş", "Kişisel", "Sağlık", "Alışveriş", "Genel")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_note)

        val editTitle = findViewById<EditText>(R.id.editTitle)
        val editContent = findViewById<EditText>(R.id.editContent)
        val spinnerCategory = findViewById<Spinner>(R.id.spinnerCategoryNew)
        val btnSave = findViewById<Button>(R.id.btnSave)

        spinnerCategory.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, categories)

        btnSave.setOnClickListener {
            val title = editTitle.text.toString()
            val content = editContent.text.toString()
            val category = spinnerCategory.selectedItem.toString()

            if (content.isNotBlank()) {
                val resultIntent = Intent()
                resultIntent.putExtra("note_title", title)
                resultIntent.putExtra("note_text", content)
                resultIntent.putExtra("note_category", category)
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
        }
    }
}
