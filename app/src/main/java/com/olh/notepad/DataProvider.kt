package com.olh.notepad

object DataProvider {
    private val items = mutableListOf<MainItem>()

    fun getAllItems(): List<MainItem> = items

    fun addItem(item: MainItem) {
        items.add(0, item) // En üste ekle
    }
}