package com.olh.notepad

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.olh.notepad.databinding.ItemNoteBinding
import com.olh.notepad.databinding.ItemTodoBinding

data class NoteItem(val title: String, val content: String)

sealed class DisplayItem {
    data class Note(val item: NoteItem) : DisplayItem()
    data class Todo(val item: TodoItem) : DisplayItem()
}

class MainAdapter(private val items: List<DisplayItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_NOTE = 0
        private const val TYPE_TODO = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is DisplayItem.Note -> TYPE_NOTE
            is DisplayItem.Todo -> TYPE_TODO
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_NOTE -> {
                val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                NoteViewHolder(binding)
            }
            TYPE_TODO -> {
                val binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                TodoViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items[position]) {
            is DisplayItem.Note -> (holder as NoteViewHolder).bind(item.item)
            is DisplayItem.Todo -> (holder as TodoViewHolder).bind(item.item)
        }
    }

    class NoteViewHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NoteItem) {
            binding.textViewTitle.text = item.title
            binding.textViewContent.text = item.content
        }
    }

    class TodoViewHolder(private val binding: ItemTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TodoItem) {
            binding.editTextDescription.setText(item.description)
            binding.editTextDate.setText(item.date)
            binding.checkBoxItem.isChecked = item.isChecked
        }
    }
}
