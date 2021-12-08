package com.bizubizu.foodjournal.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bizubizu.foodjournal.data.model.Note
import com.bizubizu.foodjournal.data.model.NoteDiary
import com.bizubizu.foodjournal.data.model.NutritionalValue


@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(notes: List<Note>?)

    @Query("SELECT * FROM note")
    fun getAllNote(): List<Note?>
    //fun getAllNote(): LiveData<List<Note>>

    @Insert
    fun insert(note: Note?)

    @Update
    fun update(note: Note?)
    //suspend fun update(note: Note?)

    @Delete
    fun delete(note: Note?)
    //suspend fun delete(note: Note?)

    /*@Query("SELECT * FROM notediary WHERE diaryId IS :diaryId")
    fun getNoteDiariesForNote(diaryId: Int?): List<NoteDiary?>?

    @Query("SELECT * FROM nutritionalvalue WHERE nutritionalId IS :nutritionalId")
    fun getNutritionalValuesForNote(nutritionalId: Int?): List<NutritionalValue?>?*/



    // Получение всех Person из бд с условием
    /*@Query("SELECT * FROM note WHERE favoriteColor LIKE :color")
    fun getAllPeopleWithFavoriteColor(color: String?): List<Note?>?*/

}