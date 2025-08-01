package com.olh.notepad.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.olh.notepad.databinding.ItemNoteBinding
import com.olh.notepad.model.NoteEntity
import java.text.SimpleDateFormat
import java.util.*

class NoteAdapter(
    private val notes: List<NoteEntity>,
    private val onDeleteClick: (NoteEntity) -> Unit
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNoteBinding.inflate(inflater, parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        with(holder.binding) {
            textViewTitle.text = note.title
            textViewContent.text = note.content
            textViewTimestamp.text = formatTimestamp(note.timestamp)
            buttonDelete.setOnClickListener { onDeleteClick(note) }
        }
    }

    override fun getItemCount(): Int = notes.size

    private fun formatTimestamp(timestamp: Long): String {
        val sdf = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.getDefault())
        return sdf.format(Date(timestamp))
    }
}
