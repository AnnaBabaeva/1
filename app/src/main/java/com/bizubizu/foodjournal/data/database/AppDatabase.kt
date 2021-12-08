package com.bizubizu.foodjournal.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bizubizu.foodjournal.data.NoteTypeConverters
import com.bizubizu.foodjournal.data.dao.NoteDao
import com.bizubizu.foodjournal.data.model.Note


@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(NoteTypeConverters::class)

abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao?


}
