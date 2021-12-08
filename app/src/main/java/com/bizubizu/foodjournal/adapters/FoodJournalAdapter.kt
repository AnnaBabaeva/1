package com.bizubizu.foodjournal.adapters

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bizubizu.foodjournal.data.model.Note
import com.bizubizu.foodjournal.data.model.NutritionalValue
import com.bizubizu.foodjournal.databinding.RecyclerViewHomeBinding


class FoodJournalAdapter(val foodJournalList: List<Note> ):
    RecyclerView.Adapter<FoodJournalAdapter.FoodJournalViewHolder>(){

    //биндинг
    class FoodJournalViewHolder(val binding: RecyclerViewHomeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodJournalViewHolder {
        return FoodJournalViewHolder(RecyclerViewHomeBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return foodJournalList.size
    }

    override fun onBindViewHolder(holder: FoodJournalViewHolder, position: Int) {
       /* val item = foodJournalList[position]
        holder.binding.recyclerViewImage.setText(item.image)
        holder.binding.recyclerViewResult.setText(item.result)
        holder.binding.recyclerViewDate.setText(item.date)
        holder.binding.recyclerViewCalories.setText(item.calories)
        holder.binding.recyclerViewImageSmile.setText(item.)
        //color // цвет фона */

    }

}