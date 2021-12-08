package com.bizubizu.foodjournal

import android.app.Application
import androidx.room.Room
import androidx.room.TypeConverters
import com.bizubizu.foodjournal.data.NoteTypeConverters
import com.bizubizu.foodjournal.data.database.AppDatabase
import java.io.File

//Application заменяет  companion object в Database, чтобы мы могли иметь доступ из любого места в приложении
//добавить в манифест android:name=".Application"

class App : Application() {
   private var _database: AppDatabase? = null
    val database get() = _database

    override fun onCreate() {
        super.onCreate()
        instance = this
        _database = Room.databaseBuilder(this, AppDatabase::class.java, "database")
            .build()
    }

    companion object {
        var instance: App? = null
    }


    fun getPhotoFile(fileName: String): File = File(applicationContext.filesDir, fileName)

}
