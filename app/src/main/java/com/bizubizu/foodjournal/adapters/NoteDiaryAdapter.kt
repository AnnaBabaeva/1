package com.bizubizu.foodjournal.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bizubizu.foodjournal.data.model.NoteDiary
import com.bizubizu.foodjournal.databinding.RecyclerviewNewNoteTextBinding

class NoteDiaryAdapter(private val noteDiaries: MutableList<NoteDiary>) :
    RecyclerView.Adapter<NoteDiaryAdapter.NoteDiaryViewHolder>() {

    //биндинг
    class NoteDiaryViewHolder(val binding: RecyclerviewNewNoteTextBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteDiaryViewHolder {
        return NoteDiaryViewHolder(
            RecyclerviewNewNoteTextBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: NoteDiaryViewHolder, position: Int) {
        val item = noteDiaries[position]
        holder.binding.newNoteText.setText(item.text)
        holder.binding.newNoteTitle.setText(item.title)
    }

    override fun getItemCount() = noteDiaries.size
}