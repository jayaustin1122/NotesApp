package com.example.notesapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NotesTable")
data class Note(
    @PrimaryKey(autoGenerate = true)var id : Int?,
    @ColumnInfo(name = "title")var title : String?,
    @ColumnInfo(name = "desc")var desc : String?,
    @ColumnInfo(name = "date")var date : String?
    ) : java.io.Serializable
