package com.yml.fblivevid.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.yml.fblivevid.MainActivity;
import com.yml.fblivevid.R;
import com.yml.fblivevid.utils.Constant;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private SharedPreferences preferences;
    private List<String> stringList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);

        preferences = getSharedPreferences(getPackageName() , MODE_PRIVATE);

        callbackManager = CallbackManager.Factory.create();
//        String permissions[] = new String[]{"publish_actions","manage_pages", "publish_pages","publish_actions", "user_managed_groups","publish_actions","user_events"};
        String permissions[] = new String[]{"publish_actions","manage_pages", "publish_pages","publish_actions"};
        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
//        loginButton.setPublishPermissions(permissions);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.e("amlan","LoginResult"+loginResult);
                if(loginResult!=null){
                    preferences.edit().putBoolean(Constant.IS_LOGGED_IN , true).apply();
                    startActivity(new Intent(LoginActivity.this , MainActivity.class));
                    finish();
                }

            }

            @Override
            public void onCancel() {
                Log.e("amlan","onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.e("amlan","error");
                error.printStackTrace();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


}
