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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ruyue.note.R;
import com.ruyue.note.databinding.ActivityLoginBinding;
import com.ruyue.note.notes.NoteListActivity;
import com.ruyue.note.utils.Const;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();
    private LoginViewModel loginViewModel;
    public static SharedPreferences sharedPreferences;
    private Boolean isNameLegal = false;
    private Boolean isPasswordLegal = false;

    @BindView(R.id.name)
    EditText inputName;
    @BindView(R.id.password)
    EditText inputPassword;
    @BindView(R.id.login_btn)
    Button loginBtn;

    @OnClick(R.id.login_btn)
    public void loginBtnOnclick(View v) {
        switch (loginViewModel.login()) {
            case Const.USER_NOT_EXIST:
                Toast.makeText(LoginActivity.this, Const.ERROR_NAME, Toast.LENGTH_SHORT).show();
                break;
            case Const.PASSWORD_NOT_CORRECT:
                Toast.makeText(LoginActivity.this, Const.ERROR_PASSWORD, Toast.LENGTH_SHORT).show();
                break;
            case Const.LOGIN_SUCCEED:
                jumpToNoteListActivity();
                sharedPreferences.edit().putBoolean(Const.IS_LOGIN, true).apply();
                break;
            default:
                break;
        }
    }

    @OnTextChanged(R.id.name)
    public void displayNameErrorInfo() {
        isNameLegal = loginViewModel.isInputLegal(inputName, Const.NAME_PATTERN, Const.SET_NAME_ERROR);
        loginBtn.setEnabled(isNameLegal && isPasswordLegal);
    }

    @OnTextChanged(R.id.password)
    public void displayPasswordErrorInfo() {
        isPasswordLegal = loginViewModel.isInputLegal(inputPassword, Const.PASSWORD_PATTERN, Const.SET_PASSWORD_ERROR);
        loginBtn.setEnabled(isNameLegal && isPasswordLegal);
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