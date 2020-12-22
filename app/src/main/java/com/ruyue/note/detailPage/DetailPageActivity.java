package com.ruyue.note.detailPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.ruyue.note.R;
import com.ruyue.note.databinding.ActivityDetailPageBinding;
import com.ruyue.note.databinding.ActivityLoginBinding;
import com.ruyue.note.login.LoginActivity;
import com.ruyue.note.login.LoginViewModel;

public class DetailPageActivity extends AppCompatActivity {
    private DetailPageViewModel detailPageViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        detailPageViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(DetailPageViewModel.class);
        detailPageViewModel = ViewModelProviders.of(this).get(DetailPageViewModel.class);
        ActivityDetailPageBinding binding = DataBindingUtil.setContentView(DetailPageActivity.this, R.layout.activity_detail_page);
        binding.setLifecycleOwner(this);
        binding.setDetailPageViewModel(detailPageViewModel);
    }
}