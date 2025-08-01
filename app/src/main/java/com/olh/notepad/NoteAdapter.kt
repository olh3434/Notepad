package com.olh.notepad

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(
    private val notes: List<Note>,
    private val onNoteClick: (Note, Int) -> Unit
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.textNoteTitle)
        val text: TextView = itemView.findViewById(R.id.textNoteContent)
        val time: TextView = itemView.findViewById(R.id.textTimestamp)
        val category: TextView = itemView.findViewById(R.id.noteCategory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.title.text = note.title
        holder.text.text = note.text
        holder.time.text = android.text.format.DateFormat.format("dd.MM.yyyy HH:mm", note.timestamp)
        holder.category.text = note.category

        val bgColor = when (note.category) {
            "İş" -> Color.parseColor("#2196F3")
            "Kişisel" -> Color.parseColor("#4CAF50")
            "Sağlık" -> Color.parseColor("#F44336")
            "Alışveriş" -> Color.parseColor("#FF9800")
            else -> Color.parseColor("#9E9E9E")
        }
        holder.category.setBackgroundColor(bgColor)

        holder.itemView.setOnClickListener {
            onNoteClick(note, position)
        }
    }

    override fun getItemCount(): Int = notes.size
}
