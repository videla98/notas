package com.example.notas.view

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notas.R
import com.example.notas.databinding.FragmentSummaryBinding
import com.example.notas.model.Note
import com.example.notas.model.NotesDB
import com.example.notas.recyclerview.NoteRecyclerViewAdapter

class SummaryFragment : Fragment(R.layout.fragment_summary){

    private var mutableNoteList: MutableList<Note> = NotesDB.notes.toMutableList()
    private lateinit var binding: FragmentSummaryBinding
    private lateinit var adapter: NoteRecyclerViewAdapter
    private val llmanager: LinearLayoutManager = LinearLayoutManager(this.context)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSummaryBinding.inflate(layoutInflater)
        binding.btnAdd.setOnClickListener {
            onItemAdded((1..mutableNoteList.size).random())
        }
        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = NoteRecyclerViewAdapter(
            mutableNoteList,
            { note -> onItemSelected(note) },
            { position -> onItemDeleted(position) }
        )
        binding.rvNotes.layoutManager = llmanager
        binding.rvNotes.adapter = adapter
    }

    private fun onItemSelected(note: Note) {
        Toast.makeText(this.context, note.title, Toast.LENGTH_SHORT).show()
    }

    private fun onItemDeleted(position: Int) {
        mutableNoteList.removeAt(position)
        adapter.notifyItemRemoved(position)
    }

    private fun onItemAdded(position: Int) {
        mutableNoteList.add(position, Note("Nueva Nota", "Soy muy importante"))
        adapter.notifyItemInserted(position)
        llmanager.scrollToPosition(position)
    }

}