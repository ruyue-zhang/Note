package com.ruyue.note.login;

import android.util.Log;

import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {
    private static final String TAG = LoginViewModel.class.getSimpleName();

    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void login() {
        String inputName = getName();
        Log.d(TAG, inputName);
    }
}
