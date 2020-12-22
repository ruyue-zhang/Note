package com.ruyue.note.detailPage;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.ruyue.note.model.Note;
import com.ruyue.note.repository.LocalDataSource;
import com.ruyue.note.utils.DateUtil;

import java.util.List;

public class DetailPageViewModel extends AndroidViewModel {
    private static final String TAG = DetailPageViewModel.class.getSimpleName();
    private String title;
    private String content;
    private MutableLiveData<List<Note>> noteList;
    private LocalDataSource localDataSource;

    public DetailPageViewModel(@NonNull Application application) {
        super(application);
        localDataSource = LocalDataSource.getInstance(this.getApplication());
    }

    public MutableLiveData<List<Note>> getNoteList() {
        if(noteList == null){
            noteList = new MutableLiveData<>();
        }
        return noteList;
    }

    public void setNoteList(MutableLiveData<List<Note>> noteList) {
        this.noteList = noteList;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void insertInRoom() {
        String date = DateUtil.stampToDate(System.currentTimeMillis());
        Log.d(TAG, date);
        Note note = new Note(getTitle(), getContent(), date, null);
        localDataSource.noteDao().insertNote(note);
    }
}
