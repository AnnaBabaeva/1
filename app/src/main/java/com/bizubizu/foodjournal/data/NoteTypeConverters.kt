package com.bizubizu.foodjournal.data

import androidx.room.TypeConverter
import com.bizubizu.foodjournal.data.model.NoteDiary
import com.bizubizu.foodjournal.data.model.NutritionalValue
import java.util.*
import java.util.stream.Collectors


private const val SEPARATOR = ";"

class NoteTypeConverters {

    companion object {
        @TypeConverter
        @JvmStatic
        fun diaryListToString(diaryList: MutableList<NoteDiary>?): String? =
            diaryList?.map { it }?.joinToString(separator = SEPARATOR)

        @TypeConverter
        @JvmStatic
        fun stringToDiaryList(diaryList: String?): MutableList<NoteDiary>? =
            diaryList?.split(SEPARATOR)?.map { NoteDiary.of(it) }?.toMutableList()

        @TypeConverter
        @JvmStatic
        fun nutritionalListToString(nutritionalList: MutableList<NutritionalValue>?): String? =
            nutritionalList?.map { it }?.joinToString(separator = SEPARATOR)

        @TypeConverter
        @JvmStatic
        fun stringToNutritionalList(nutritionalList: String?): MutableList<NutritionalValue>? =
            nutritionalList?.split(SEPARATOR)?.map { NutritionalValue.of(it) }?.toMutableList()
    }



    //DATE
        @TypeConverter
        fun fromTimestamp(value: Long?): Date? {
            return value?.let { Date(it) }
        }
        @TypeConverter
        fun dateToTimestamp(date: Date?): Long? {
            return date?.time?.toLong()
        }


}