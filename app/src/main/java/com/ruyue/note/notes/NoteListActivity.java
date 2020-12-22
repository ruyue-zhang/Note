package com.ruyue.note.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;

import com.ruyue.note.R;
import com.ruyue.note.databinding.ActivityNoteListBinding;
import com.ruyue.note.detailPage.DetailPageActivity;
import com.ruyue.note.detailPage.DetailPageViewModel;
import com.ruyue.note.model.Note;
import com.ruyue.note.utils.Const;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NoteListActivity extends AppCompatActivity {

    private List<Note> noteList = null;
    private NoteListViewModel noteListViewModel;
    private NoteListAdapter adapter;

    @BindView(R.id.change_sort)
    Button changeSort;

    @OnClick(R.id.create_note)
    public void onCreateClick() {
        Intent intent = new Intent(NoteListActivity.this, DetailPageActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.change_sort)
    public void changeSortClick() {
        if ((changeSort.getText() == Const.SORT_BY_CREATE_DATE)) {
            changeSort.setText(Const.SORT_BY_MODIFY_DATE);
        } else {
            changeSort.setText(Const.SORT_BY_CREATE_DATE);
        }
        Collections.sort(noteList);
        Collections.reverse(noteList);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        noteListViewModel = ViewModelProviders.of(this).get(NoteListViewModel.class);
        ActivityNoteListBinding binding = DataBindingUtil.setContentView(NoteListActivity.this, R.layout.activity_note_list);
        binding.setLifecycleOwner(this);
        binding.setNoteListViewModel(noteListViewModel);
        ButterKnife.bind(this);

        while (noteList == null) {
            noteList = noteListViewModel.getNodeList();
        }

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(NoteListActivity.this));
        adapter = new NoteListAdapter(noteList, NoteListActivity.this);
        recyclerView.setAdapter(adapter);

//        noteListViewModel.getNodeList().observe(this, notes -> {
//            noteList.clear();
//            noteList.addAll(notes);
//            adapter.notifyDataSetChanged();
//        });
    }
}