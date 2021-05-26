package id.ac.unhas.noteapp.db

import androidx.lifecycle.LiveData
import androidx.room

@Dao
interface NoteDao {
    @Query("Select * from note")
    fun getNotes(): LiveData<List<Note>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)
}

annotation class Update

annotation class Delete

annotation class Insert(val onConflict: Any)

annotation class Query(val value: String)

annotation class Dao

class OnConflictStrategy {

}

