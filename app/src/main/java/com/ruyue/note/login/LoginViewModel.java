package com.ruyue.note.login;

import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.ruyue.note.model.User;
import com.ruyue.note.utils.Const;
import com.ruyue.note.utils.MD5Util;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginViewModel extends ViewModel {
    private static final String TAG = LoginViewModel.class.getSimpleName();

    private String name;
    private String password;
    private User serviceUser;

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

    public int login() {
        String inputName = getName();
        String inputPassword = getPassword();
        String Md5Password = MD5Util.md5Encrypt32Lower(inputPassword);
        if (!inputName.equals(serviceUser.getName())) {
            return Const.USER_NOT_EXIST;
        } else if(!Md5Password.equals(serviceUser.getPassword())) {
            return Const.PASSWORD_NOT_CORRECT;
        } else {
            return Const.LOGIN_SUCCEED;
        }
    }

    protected void getServiceUser() {
        final Request request = new Request.Builder().url(Const.URL).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String result = Objects.requireNonNull(response.body()).string();
                jsonStringToUser(result);
            }
        });
    }

    private void jsonStringToUser(String result) {
        Gson gson = new Gson();
        serviceUser = gson.fromJson(result, User.class);
    }
}
