package com.olh.notepad.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olh.notepad.model.NoteEntity
import com.olh.notepad.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(private val repository: NoteRepository) : ViewModel() {

    val allNotes = repository.allNotes

    fun insert(note: NoteEntity) = viewModelScope.launch {
        repository.insert(note)
    }

    fun delete(note: NoteEntity) = viewModelScope.launch {
        repository.delete(note)
    }
}
