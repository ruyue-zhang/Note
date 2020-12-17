package com.ruyue.note.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.ruyue.note.R;
import com.ruyue.note.databinding.ActivityLoginBinding;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    private LoginViewModel loginViewModel;

    @OnClick(R.id.login_btn)
    public void loginBtnOnclick(View v) {
        loginViewModel.login();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(LoginViewModel.class);
        ActivityLoginBinding binding = DataBindingUtil.setContentView(LoginActivity.this, R.layout.activity_login);
        binding.setLifecycleOwner(this);
        binding.setLoginViewModel(loginViewModel);
        ButterKnife.bind(this);
    }
}