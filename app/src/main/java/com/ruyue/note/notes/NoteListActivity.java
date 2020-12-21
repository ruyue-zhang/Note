package com.ruyue.note.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ruyue.note.R;
import com.ruyue.note.detailPage.DetailPageActivity;
import com.ruyue.note.model.Note;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class NoteListActivity extends AppCompatActivity {

    private List<Note> noteList;

    @OnClick(R.id.create_note)
    public void onCreateClick() {
        Intent intent = new Intent(NoteListActivity.this, DetailPageActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        ButterKnife.bind(this);

        noteList = initNoteList();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(NoteListActivity.this));
        recyclerView.setAdapter(new NoteListAdapter(noteList, NoteListActivity.this));
    }


    private List<Note> initNoteList() {
        List<Note> notes = new ArrayList<>();
        notes.add(new Note(1, "111","1111111111","10:36", "10:37"));
        notes.add(new Note(2, "222","1111111111","10:36", "10:37"));
        notes.add(new Note(3, "333","1111111111","10:36", "10:37"));
        notes.add(new Note(4, "444","1111111111","10:36", "10:37"));
        notes.add(new Note(4, "444","1111111111","10:36", "10:37"));
        notes.add(new Note(4, "444","1111111111","10:36", "10:37"));
        notes.add(new Note(4, "444","1111111111","10:36", "10:37"));
        notes.add(new Note(4, "444","1111111111","10:36", "10:37"));
        notes.add(new Note(4, "444","1111111111","10:36", "10:37"));
        notes.add(new Note(4, "444","1111111111","10:36", "10:37"));
        notes.add(new Note(4, "444","1111111111","10:36", "10:37"));
        notes.add(new Note(4, "444","1111111111","10:36", "10:37"));
        return notes;
    }


}