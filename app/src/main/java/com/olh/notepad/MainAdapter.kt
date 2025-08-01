package com.olh.notepad

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.olh.notepad.databinding.ItemNoteBinding
import com.olh.notepad.databinding.ItemTodoBinding

class MainAdapter(private val items: List<MainItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_NOTE = 0
        private const val TYPE_TODO = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is MainItem.NoteItem -> TYPE_NOTE
            is MainItem.TodoItem -> TYPE_TODO
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
            else -> throw IllegalArgumentException("Geçersiz viewType: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items[position]) {
            is MainItem.NoteItem -> (holder as NoteViewHolder).bind(item)
            is MainItem.TodoItem -> (holder as TodoViewHolder).bind(item)
        }
    }

    override fun getItemCount(): Int = items.size

    class NoteViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MainItem.NoteItem) {
            binding.textNoteTitle.text = item.title
            binding.textNoteContent.text = item.content
            binding.textTimestamp.text = "Bugün"
            binding.noteCategory.text = "Genel"
        }
    }

    class TodoViewHolder(private val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MainItem.TodoItem) {
            binding.textTodoTitle.text = item.title
            binding.textTodoTasks.text = item.tasks.joinToString(separator = "\n") { "- $it" }
            binding.textTodoTimestamp.text = "Bugün"
            binding.todoCategory.text = "Görev"
        }
    }

}
