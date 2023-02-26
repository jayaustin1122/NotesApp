package com.example.notesapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notesapp.models.Note

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("Select * from NotesTable order by id ASC")
    fun getAll(): LiveData<List<Note>>

    @Query("UPDATE NotesTable Set title = :title, desc = :desc Where id = :id ")
    suspend fun update (id : Int?, title: String?, desc : String?)
}