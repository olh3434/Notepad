package com.olh.notepad

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class TodoItemConverter {
    @TypeConverter
    fun fromTodoItemList(items: List<TodoItem>): String {
        return Gson().toJson(items)
    }

    @TypeConverter
    fun toTodoItemList(data: String): List<TodoItem> {
        val type = object : TypeToken<List<TodoItem>>() {}.type
        return Gson().fromJson(data, type)
    }
}
