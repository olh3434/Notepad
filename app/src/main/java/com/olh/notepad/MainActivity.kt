package com.olh.notepad

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val notes = mutableListOf<Note>()
    private lateinit var adapter: NoteAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPref = getSharedPreferences("notes_pref", MODE_PRIVATE)

        val btnAddNote = findViewById<Button>(R.id.btnAddNote)
        recyclerView = findViewById(R.id.recyclerViewNotes)

        loadNotes()

        adapter = NoteAdapter(notes) { selectedNote, position ->
            val intent = Intent(this, NoteEditActivity::class.java)
            intent.putExtra("note_title", selectedNote.title)
            intent.putExtra("note_text", selectedNote.text)
            intent.putExtra("note_time", selectedNote.timestamp)
            intent.putExtra("note_category", selectedNote.category)
            intent.putExtra("note_index", position)
            startActivityForResult(intent, 200)
        }

        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = adapter

        btnAddNote.setOnClickListener {
            val intent = Intent(this, NewNoteActivity::class.java)
            startActivityForResult(intent, 100)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            val title = data?.getStringExtra("note_title") ?: ""
            val text = data?.getStringExtra("note_text") ?: ""
            val category = data?.getStringExtra("note_category") ?: "Genel"

            if (text.isNotBlank()) {
                val newNote = Note(title, text, System.currentTimeMillis(), category)
                notes.add(newNote)
                adapter.notifyItemInserted(notes.size - 1)
                saveNotes()
            }
        }

        if (requestCode == 200 && resultCode == Activity.RESULT_OK) {
            val updatedTitle = data?.getStringExtra("updated_title") ?: ""
            val updatedText = data?.getStringExtra("updated_text") ?: ""
            val updatedCategory = data?.getStringExtra("updated_category") ?: "Genel"
            val updatedIndex = data?.getIntExtra("note_index", -1) ?: -1
            val originalTimestamp = data?.getLongExtra("note_time", System.currentTimeMillis())

            if (updatedIndex in notes.indices && updatedText.isNotBlank()) {
                val updatedNote = Note(updatedTitle, updatedText, originalTimestamp ?: System.currentTimeMillis(), updatedCategory)
                notes[updatedIndex] = updatedNote
                adapter.notifyItemChanged(updatedIndex)
                saveNotes()
            }
        }
    }

    private fun saveNotes() {
        val jsonSet = notes.map { it.toJson() }.toSet()
        sharedPref.edit().putStringSet("notes_set", jsonSet).apply()
    }

    private fun loadNotes() {
        val jsonSet = sharedPref.getStringSet("notes_set", emptySet())
        jsonSet?.forEach {
            notes.add(Note.fromJson(it))
        }
    }
}
