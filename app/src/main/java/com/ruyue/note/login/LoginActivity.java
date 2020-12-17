package com.ruyue.note.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ruyue.note.R;
import com.ruyue.note.databinding.ActivityLoginBinding;
import com.ruyue.note.notes.NoteListActivity;
import com.ruyue.note.utils.Const;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();
    private LoginViewModel loginViewModel;
    private SharedPreferences sharedPreferences;

    @OnClick(R.id.login_btn)
    public void loginBtnOnclick(View v) {
        switch (loginViewModel.login()) {
            case Const.USER_NOT_EXIST:
                Log.d(TAG, "USER_NOT_EXIST");
                break;
            case Const.PASSWORD_NOT_CORRECT:
                Log.d(TAG, "PASSWORD_NOT_CORRECT");
                break;
            case Const.LOGIN_SUCCEED:
                jumpToNoteListActivity();
                sharedPreferences.edit().putBoolean(Const.IS_LOGIN, true).apply();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isLogin()) {
            jumpToNoteListActivity();
        } else {
            loginViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(LoginViewModel.class);
            ActivityLoginBinding binding = DataBindingUtil.setContentView(LoginActivity.this, R.layout.activity_login);
            binding.setLifecycleOwner(this);
            binding.setLoginViewModel(loginViewModel);
            ButterKnife.bind(this);
            loginViewModel.getServiceUser();
        }
    }

    private Boolean isLogin() {
        sharedPreferences = getSharedPreferences(Const.IS_LOGIN, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(Const.IS_LOGIN, false);
    }

    private void jumpToNoteListActivity() {
        Intent intent = new Intent(LoginActivity.this, NoteListActivity.class);
        startActivity(intent);
        LoginActivity.this.finish();
    }
}