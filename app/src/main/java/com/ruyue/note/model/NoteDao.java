package com.ruyue.note.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

@Dao
public interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNote(Note note);

    @Query("SELECT * FROM notes")
    LiveData<List<Note>> getNoteList();
}
