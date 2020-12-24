package com.ruyue.note.notes;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ruyue.note.model.Note;
import com.ruyue.note.repository.LocalDataSource;

import org.w3c.dom.Node;

import java.util.Collections;
import java.util.List;

public class NoteListViewModel extends AndroidViewModel {
    private static final String TAG = NoteListViewModel.class.getSimpleName();
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

    public List<Note> getNodeListForSearch(String keyword) {
        new Thread(() -> {
            notes.clear();
            notes.addAll(localDataSource.noteDao().getNoteListByKeyword(keyword));
        }).start();
        return notes;
    }
}
