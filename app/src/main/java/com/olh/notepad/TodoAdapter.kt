package com.olh.notepad

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.olh.notepad.databinding.ItemTodoBinding

class TodoAdapter(
    private val items: MutableList<TodoItem>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val item = items[position]
        val b = holder.binding

        b.editTextDescription.setText(item.description)
        b.editTextDate.setText(item.date)
        b.checkBoxItem.isChecked = item.isChecked

        b.checkBoxItem.setOnCheckedChangeListener { _, isChecked ->
            item.isChecked = isChecked
            b.editTextDescription.paintFlags = if (isChecked)
                b.editTextDescription.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            else
                b.editTextDescription.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }

        b.editTextDescription.setOnFocusChangeListener { _, _ ->
            item.description = b.editTextDescription.text.toString()
        }

        b.editTextDate.setOnFocusChangeListener { _, _ ->
            item.date = b.editTextDate.text.toString()
        }
    }

    override fun getItemCount(): Int = items.size

    fun addItem(item: TodoItem) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun getItems(): List<TodoItem> = items
}
