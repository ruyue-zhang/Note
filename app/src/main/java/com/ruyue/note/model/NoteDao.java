package com.ruyue.note.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNote(Note note);

    @Update
    void update(Note note);

    @Delete
    void deleteNote(Note note);

    @Query("SELECT * FROM notes")
    LiveData<List<Note>> getLiveNoteList();

    @Query("SELECT * FROM notes")
    List<Note> getNoteList();
}
