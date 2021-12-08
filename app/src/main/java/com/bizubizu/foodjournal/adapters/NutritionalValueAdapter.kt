package com.bizubizu.foodjournal.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bizubizu.foodjournal.R
import com.bizubizu.foodjournal.data.model.NutritionalValue
import com.bizubizu.foodjournal.databinding.RecyclerViewNewNoteNutritionalValueBinding

class NutritionalValueAdapter(private val nutritionalValue: MutableList<NutritionalValue>) :
    RecyclerView.Adapter<NutritionalValueAdapter.NutritionalValueViewHolder>() {

    var dropdownNutritionalValueStrings: Array<String>? = null

    //биндинг
    class NutritionalValueViewHolder(val binding: RecyclerViewNewNoteNutritionalValueBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NutritionalValueViewHolder {

        if(dropdownNutritionalValueStrings == null)
        dropdownNutritionalValueStrings = parent.context.resources.getStringArray(R.array.spinner_calories)

        return NutritionalValueViewHolder(
            RecyclerViewNewNoteNutritionalValueBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: NutritionalValueViewHolder, position: Int) {
        val arrayAdapterDropdownNutritionalValueRv = ArrayAdapter(
            holder.binding.root.context,
            R.layout.option_item_new_note,
            dropdownNutritionalValueStrings!!
        )
        holder.binding.dropdownNutritionalValue.setAdapter(arrayAdapterDropdownNutritionalValueRv)

        val item = nutritionalValue[position]
        if(item.type == "")
            item.type = dropdownNutritionalValueStrings!![0]

        holder.binding.dropdownNutritionalValue.setText(item.type,false)
        holder.binding.editTextNutritionalValueRv.setText(item.value.toString())
    }

    override fun getItemCount() = nutritionalValue.size
}