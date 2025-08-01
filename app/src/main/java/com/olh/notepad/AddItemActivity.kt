package com.olh.notepad
import com.olh.notepad.MainItem

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.olh.notepad.databinding.ActivityAddItemBinding

class AddItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val types = listOf("Not", "To-do List")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, types)
        binding.spinnerType.adapter = adapter

        binding.buttonSave.setOnClickListener {
            val title = binding.editTitle.text.toString().trim()
            val content = binding.editContent.text.toString().trim()
            val type = binding.spinnerType.selectedItem.toString()

            if (title.isEmpty() || content.isEmpty()) {
                Toast.makeText(this, "Başlık ve içerik boş olamaz", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            when (type) {
                "Not" -> {
                    val newNote = MainItem.NoteItem(title, content)
                    DataProvider.addItem(newNote)
                }
                "To-do List" -> {
                    val tasks = content.split("\n").map { it.trim() }.filter { it.isNotEmpty() }
                    val newTodo = MainItem.TodoItem(title, tasks)
                    DataProvider.addItem(newTodo)
                }
            }

            finish()
        }
    }
}
