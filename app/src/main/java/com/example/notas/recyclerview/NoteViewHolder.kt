package com.example.notas.recyclerview

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.notas.databinding.RecyclerViewRowBinding
import com.example.notas.model.Note

class NoteViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = RecyclerViewRowBinding.bind(view)

    fun render(noteModel : Note) {
        binding.tvCardNoteTitle.text = noteModel.title
        binding.tvCardNoteBody.text = noteModel.body
        binding.cardView.setOnClickListener {
            Toast.makeText(binding.cardView.context,
                noteModel.title,
                Toast.LENGTH_SHORT).show()
        }
    }
}