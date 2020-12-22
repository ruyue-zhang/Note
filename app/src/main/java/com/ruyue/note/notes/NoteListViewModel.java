package com.ruyue.note.notes;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ruyue.note.model.Note;
import com.ruyue.note.repository.LocalDataSource;

import java.util.Collections;
import java.util.List;

public class NoteListViewModel extends AndroidViewModel {
    private LocalDataSource localDataSource;
    private LiveData<List<Note>> nodeList;
    private List<Note> notes;

    public NoteListViewModel(@NonNull Application application) {
        super(application);
        this.localDataSource = LocalDataSource.getInstance(this.getApplication());
        nodeList = localDataSource.noteDao().getLiveNoteList();
    }

    public LiveData<List<Note>> getLiveNodeList() {
        return nodeList;
    }

    public List<Note> getNodeList() {
        new Thread(() -> {
            notes = localDataSource.noteDao().getNoteList();
        }).start();
        return notes;
    }
}
