package com.ruyue.note.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.ruyue.note.R;
import com.ruyue.note.databinding.ActivityNoteListBinding;
import com.ruyue.note.detailPage.DetailPageActivity;
import com.ruyue.note.login.LoginActivity;
import com.ruyue.note.model.Note;
import com.ruyue.note.utils.Const;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class NoteListActivity extends AppCompatActivity {
    private static final String TAG = NoteListActivity.class.getSimpleName();

    private List<Note> noteList = null;
    private NoteListViewModel noteListViewModel;
    private NoteListAdapter adapter;

    @BindView(R.id.change_sort)
    Button changeSort;
    @BindView(R.id.note_count)
    TextView noteCount;
    @BindView(R.id.search_box)
    EditText searchBox;
    @BindView(R.id.draw_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @OnClick(R.id.create_note)
    public void onCreateClick() {
        Intent intent = new Intent(NoteListActivity.this, DetailPageActivity.class);
        intent.putExtra(Const.OPERATION, Const.OPERATION_CREATE);
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

    @OnTextChanged(R.id.search_box)
    public void searchBoxChanged() {
        String keyword = searchBox.getText().toString();
        if(keyword.length() > 0) {
            noteList.clear();
            noteList.addAll(noteListViewModel.getNodeListForSearch(keyword));
        } else {
            noteList.clear();
            noteList.addAll(noteListViewModel.getNodeList());
        }
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

        setToolBar();
        navItemClickListener();
        setRecyclerView();
        liveNoteListObserve();
    }

    private void liveNoteListObserve() {
        noteListViewModel.getLiveNodeList().observe(this, notes -> {
            noteCount.setText(notes.size()+"个便签");
            noteList.clear();
            noteList.addAll(notes);
            adapter.notifyDataSetChanged();
        });
    }

    private void setRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(NoteListActivity.this));
        adapter = new NoteListAdapter(noteList, NoteListActivity.this);
        recyclerView.setAdapter(adapter);
    }

    private void navItemClickListener() {
        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.logout:
                    logout();
                default:
                    break;
            }
            return true;
        });
    }

    private void setToolBar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.navigation);
        }
    }

    private void logout() {
        LoginActivity.sharedPreferences.edit().putBoolean(Const.IS_LOGIN, false).apply();
        Intent intent = new Intent(NoteListActivity.this, LoginActivity.class);
        startActivity(intent);
        NoteListActivity.this.finish();
        drawerLayout.closeDrawers();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                break;
        }
        return true;
    }
}