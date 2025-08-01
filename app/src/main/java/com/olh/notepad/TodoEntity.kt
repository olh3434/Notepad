package com.olh.notepad

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "todo_table")
@TypeConverters(TodoItemConverter::class)
data class TodoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var title: String,
    var items: List<TodoItem>
)
