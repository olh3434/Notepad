package com.olh.notepad.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.olh.notepad.databinding.ItemNoteBinding
import com.olh.notepad.model.NoteEntity

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
            buttonDelete.setOnClickListener { onDeleteClick(note) }
        }
    }

    override fun getItemCount(): Int = notes.size
}
