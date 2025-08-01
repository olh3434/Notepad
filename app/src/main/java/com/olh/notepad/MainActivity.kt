package com.olh.notepad

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.olh.notepad.adapter.NoteAdapter
import com.olh.notepad.databinding.ActivityMainBinding
import com.olh.notepad.model.NoteDatabase
import com.olh.notepad.model.NoteEntity
import com.olh.notepad.repository.NoteRepository
import com.olh.notepad.viewmodel.NoteViewModel
import com.olh.notepad.viewmodel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var adapter: NoteAdapter? = null

    private val viewModel: NoteViewModel by viewModels {
        NoteViewModelFactory(NoteRepository(NoteDatabase.getDatabase(this).noteDao()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeNotes()
        setupAddNote()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun observeNotes() {
        viewModel.allNotes.observe(this) { notes ->
            adapter = NoteAdapter(notes) { note ->
                viewModel.delete(note)
            }
            binding.recyclerView.adapter = adapter
        }
    }

    private fun setupAddNote() {
        binding.buttonAdd.setOnClickListener {
            val title = binding.editTextTitle.text.toString().trim()
            val content = binding.editTextContent.text.toString().trim()

            if (title.isNotEmpty() || content.isNotEmpty()) {
                val note = NoteEntity(
                    title = title,
                    content = content,
                    type = "note",
                    timestamp = System.currentTimeMillis()
                )
                viewModel.insert(note)
                binding.editTextTitle.text.clear()
                binding.editTextContent.text.clear()
            }
        }
    }
}
