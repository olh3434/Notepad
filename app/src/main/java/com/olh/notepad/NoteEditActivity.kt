package com.olh.notepad

import android.app.Activity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class NoteEditActivity : AppCompatActivity() {

    private lateinit var edtTitle: EditText
    private lateinit var edtText: EditText
    private lateinit var spinnerCategory: Spinner
    private lateinit var btnUpdate: Button

    private val categories = listOf("İş", "Kişisel", "Sağlık", "Alışveriş", "Genel")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_edit)

        edtTitle = findViewById(R.id.editTitleEdit)
        edtText = findViewById(R.id.editContentEdit)
        spinnerCategory = findViewById(R.id.spinnerCategoryEdit)
        btnUpdate = findViewById(R.id.btnUpdateNote)

        val title = intent.getStringExtra("note_title") ?: ""
        val text = intent.getStringExtra("note_text") ?: ""
        val category = intent.getStringExtra("note_category") ?: "Genel"

        edtTitle.setText(title)
        edtText.setText(text)

        spinnerCategory.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, categories)
        val index = categories.indexOf(category)
        if (index >= 0) spinnerCategory.setSelection(index)

        btnUpdate.setOnClickListener {
            val updatedTitle = edtTitle.text.toString()
            val updatedText = edtText.text.toString()
            val updatedCategory = spinnerCategory.selectedItem.toString()

            val resultIntent = intent
            resultIntent.putExtra("updated_title", updatedTitle)
            resultIntent.putExtra("updated_text", updatedText)
            resultIntent.putExtra("updated_category", updatedCategory)
            resultIntent.putExtra("note_index", intent.getIntExtra("note_index", -1))
            resultIntent.putExtra("note_time", intent.getLongExtra("note_time", System.currentTimeMillis()))
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}
