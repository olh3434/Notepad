package com.olh.notepad

import org.json.JSONObject

data class Note(
    val title: String,
    val text: String,
    val timestamp: Long = System.currentTimeMillis(),
    val category: String = "Genel"
) {
    fun toJson(): String {
        val obj = JSONObject()
        obj.put("title", title)
        obj.put("text", text)
        obj.put("timestamp", timestamp)
        obj.put("category", category)
        return obj.toString()
    }

    companion object {
        fun fromJson(json: String): Note {
            val obj = JSONObject(json)
            return Note(
                obj.getString("title"),
                obj.getString("text"),
                obj.optLong("timestamp", System.currentTimeMillis()),
                obj.optString("category", "Genel")
            )
        }
    }
}
