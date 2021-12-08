package com.bizubizu.foodjournal.data.model

import androidx.room.Entity
import androidx.room.ForeignKey

/*@Entity(
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Note::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("diaryId")
        )
    )
)*/
data class NoteDiary(

    //val diaryId: Int,
    var title: String?,
    var text: String?

){
    companion object {
        fun of(string: String): NoteDiary{
            val array = string.split(",")
            return NoteDiary(array [0], array[1])
        }
    }

    override fun toString(): String {
        return "$title,$text"
    }
}
