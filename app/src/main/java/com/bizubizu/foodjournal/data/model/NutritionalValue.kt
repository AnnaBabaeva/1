package com.bizubizu.foodjournal.data.model

import androidx.room.Entity
import androidx.room.ForeignKey

/*@Entity(
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Note::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("nutritionalId")
        )
    )
)*/
data class NutritionalValue(

    //val nutritionalId: Int,
    var type: String,
    var value: Int
){
    companion object {
        fun of(string: String): NutritionalValue{
            val array = string.split(",")
            return NutritionalValue(array [0], array[1].toInt())
        }
    }



    override fun toString(): String {
        return "$type,$value"
    }
}
