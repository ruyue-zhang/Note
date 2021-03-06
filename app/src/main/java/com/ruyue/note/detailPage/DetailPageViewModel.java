package com.ruyue.note.detailPage;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ruyue.note.model.Note;
import com.ruyue.note.repository.LocalDataSource;
import com.ruyue.note.utils.DateUtil;

import java.util.List;
import java.util.Observable;
import java.util.concurrent.atomic.AtomicReference;

public class DetailPageViewModel extends AndroidViewModel {
    private static final String TAG = "DetailPageViewModel";
    private ObservableField<String> title = new ObservableField<>();
    private ObservableField<String> content = new ObservableField<>();
    private ObservableField<String> modifyDate = new ObservableField<>();

    public ObservableField<String> getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(ObservableField<String> modifyDate) {
        this.modifyDate = modifyDate;
    }

    public static Boolean isUpdate = false;

    private LocalDataSource localDataSource;

    public DetailPageViewModel(@NonNull Application application) {
        super(application);
        localDataSource = LocalDataSource.getInstance(this.getApplication());
    }

    public void initView(Note note) {
        title.set(note.getTitle());
        content.set(note.getContent());
        modifyDate.set(note.getModifyDate());
    }

    public ObservableField<String> getTitle() {
        return title;
    }

    public void setTitle(ObservableField<String> title) {
        this.title = title;
    }

    public ObservableField<String> getContent() {
        return content;
    }

    public void setContent(ObservableField<String> content) {
        this.content = content;
    }

    public void operateRoom(Note note) {
        if(isUpdate) {
            updateInRoom(note);
        } else {
            insertInRoom();
        }
    }

    public void insertInRoom() {
        String date = DateUtil.stampToDate(System.currentTimeMillis());
        Log.d(TAG, date);
        Note note = new Note(title.get(), content.get(), date, date);
        new Thread(() -> {
            localDataSource.noteDao().insertNote(note);
        }).start();
    }

    public void updateInRoom(Note note) {
        String modifyDate = DateUtil.stampToDate(System.currentTimeMillis());
        note.setTitle(title.get());
        note.setContent(content.get());
        note.setModifyDate(modifyDate);
        new Thread(() -> {
            localDataSource.noteDao().update(note);
        }).start();
    }

    public void deleteNoteById(Note note) {
        new Thread(() -> {
            localDataSource.noteDao().deleteNote(note);
        }).start();
    }
}
