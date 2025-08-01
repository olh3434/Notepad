package com.olh.notepad

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TodoTypeConverter {

    @TypeConverter
    fun fromTodoItemList(value: List<TodoItem>): String {
        val gson = Gson()
        return gson.toJson(value)
    }

    @TypeConverter
    fun toTodoItemList(value: String): List<TodoItem> {
        val gson = Gson()
        val type = object : TypeToken<List<TodoItem>>() {}.type
        return gson.fromJson(value, type)
    }
}
