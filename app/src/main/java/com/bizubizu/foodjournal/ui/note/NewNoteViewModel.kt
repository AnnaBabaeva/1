package com.bizubizu.foodjournal.ui.note

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bizubizu.foodjournal.App
import com.bizubizu.foodjournal.data.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

class NewNoteViewModel: ViewModel() {

    var note: Note? = null

    /////////////////////////////////////////////////////// определение местонахождения фотографии
    /*private val filesDir = App.instance?.filesDir
    fun getPhotoFile(note: Note): File = File(filesDir, note.photoFileName)*/

    //fun getAllNotes() : LiveData<List<Note>> = getAllNotes()

    fun insertNote() = viewModelScope.launch(Dispatchers.IO) {
            App.instance?.database?.noteDao()?.insert(note)
        }



}