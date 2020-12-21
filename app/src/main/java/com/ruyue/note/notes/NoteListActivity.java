package com.ruyue.note.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ruyue.note.R;
import com.ruyue.note.model.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteListActivity extends AppCompatActivity {

    private List<Note> noteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

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