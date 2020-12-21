package com.ruyue.note.repository;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ruyue.note.model.Note;
import com.ruyue.note.model.NoteDao;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class LocalDataSource extends RoomDatabase {
    private static final String DATABASE_NAME = "noteDateBase";
    private static LocalDataSource LocalDataSource;

    public static synchronized LocalDataSource getInstance(Context context)
    {
        if(LocalDataSource == null)
        {
            LocalDataSource = Room
                    .databaseBuilder(context.getApplicationContext(), LocalDataSource.class, DATABASE_NAME)
                    .build();
        }
        return LocalDataSource;
    }

    public abstract NoteDao noteDao();
}
