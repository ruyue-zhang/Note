package com.ruyue.note.detailPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ruyue.note.R;
import com.ruyue.note.databinding.ActivityDetailPageBinding;
import com.ruyue.note.databinding.ActivityLoginBinding;
import com.ruyue.note.login.LoginActivity;
import com.ruyue.note.login.LoginViewModel;
import com.ruyue.note.notes.NoteListActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailPageActivity extends AppCompatActivity {

    @OnClick({R.id.delete, R.id.finish})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.delete:
                break;
            case R.id.finish:
                detailPageViewModel.insertInRoom();
                Intent intent = new Intent(DetailPageActivity.this, NoteListActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                break;

        }
    }
    private DetailPageViewModel detailPageViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        detailPageViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(DetailPageViewModel.class);
        detailPageViewModel = ViewModelProviders.of(this).get(DetailPageViewModel.class);
        ActivityDetailPageBinding binding = DataBindingUtil.setContentView(DetailPageActivity.this, R.layout.activity_detail_page);
        binding.setLifecycleOwner(this);
        binding.setDetailPageViewModel(detailPageViewModel);
        ButterKnife.bind(this);
    }
}