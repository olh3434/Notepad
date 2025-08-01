package com.olh.notepad.repository

import com.olh.notepad.model.NoteDao
import com.olh.notepad.model.NoteEntity

class NoteRepository(private val noteDao: NoteDao) {

    val allNotes = noteDao.getAllNotes()

    suspend fun insert(note: NoteEntity) {
        noteDao.insertNote(note)
    }

    suspend fun delete(note: NoteEntity) {
        noteDao.deleteNote(note)
    }
}
