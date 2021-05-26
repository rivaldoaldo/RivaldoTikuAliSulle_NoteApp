package id.ac.unhas.noteapp.db

import android.app.Application
import androidx.lifecycle.LiveData
import kontlinx.coroutines.Dispatchers
import kontlinx.coroutines.launch
import kontlinx.coroutines.runBlocking

class NoteRepository(application: Application) {

    private val noteDao: NoteDao?
    private var notes: LiveData<List<Note>>? = null

    init {
        val db = AppDatabase.getInstance(application.applicationContext)
        noteDao = db?.noteDao()
        notes = noteDao?.getNotes()
    }

    fun getNotes(): LiveData<List<Note>>? {
        return notes
    }

    fun insert(note: Note) = runBlocking {
        this.launch(Dispatchers.IO) {
            noteDao?.insertNote(note)
        }
    }

    fun delete(note: Note) {
        runBlocking {
            this.launch(Dispatchers.IO) {
                noteDao?.deleteNote(note)
            }
        }
    }

    fun update(note: Note) = runBlocking {
        this.launch(Dispatchers.IO) {
            noteDao?.updateNote(note)
        }
    }

}
