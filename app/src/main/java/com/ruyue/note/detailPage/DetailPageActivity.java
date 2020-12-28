package com.ruyue.note.detailPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ruyue.note.R;
import com.ruyue.note.databinding.ActivityDetailPageBinding;
import com.ruyue.note.databinding.ActivityLoginBinding;
import com.ruyue.note.login.LoginActivity;
import com.ruyue.note.login.LoginViewModel;
import com.ruyue.note.model.Note;
import com.ruyue.note.notes.NoteListActivity;
import com.ruyue.note.utils.Const;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class DetailPageActivity extends AppCompatActivity {
    private DetailPageViewModel detailPageViewModel;
    private Note note;


    @BindView(R.id.title)
    EditText editText;
    @BindView(R.id.finish)
    TextView finish;
    @BindView(R.id.delete)
    TextView delete;

    @OnTextChanged(R.id.title)
    public void onTitleChange() {
        finish.setEnabled(editText.getText().toString().length() > 0);
    }

    @OnClick({R.id.delete, R.id.finish})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.delete:
                detailPageViewModel.deleteNoteById(note);
                jumpToNoteListPage();
                break;
            case R.id.finish:
                detailPageViewModel.operateRoom(note);
                jumpToNoteListPage();
                break;
            default:
                break;
        }
    }

    private void jumpToNoteListPage() {
        Intent intent = new Intent(DetailPageActivity.this, NoteListActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        detailPageViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(DetailPageViewModel.class);
        detailPageViewModel = ViewModelProviders.of(this).get(DetailPageViewModel.class);
        ActivityDetailPageBinding binding = DataBindingUtil.setContentView(DetailPageActivity.this, R.layout.activity_detail_page);
        binding.setLifecycleOwner(this);
        binding.setDetailPageViewModel(detailPageViewModel);
        ButterKnife.bind(this);
        getIntentData();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        String operation = intent.getStringExtra(Const.OPERATION);
        if (operation.equals(Const.OPERATION_MODIFY)) {
            String noteString = intent.getStringExtra("note");
            note = new Gson().fromJson(noteString, Note.class);
            detailPageViewModel.initView(note);
            delete.setEnabled(true);
            DetailPageViewModel.isUpdate = true;
        } else {
            delete.setEnabled(false);
            DetailPageViewModel.isUpdate = false;
        }
    }
}