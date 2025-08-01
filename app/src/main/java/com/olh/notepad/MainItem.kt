package com.olh.notepad

sealed class MainItem {
    data class NoteItem(val title: String, val content: String) : MainItem()
    data class TodoItem(val title: String, val tasks: List<String>) : MainItem()
}
