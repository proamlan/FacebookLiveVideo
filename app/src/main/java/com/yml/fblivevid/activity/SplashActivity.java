package com.yml.fblivevid.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yml.fblivevid.MainActivity;
import com.yml.fblivevid.R;
import com.yml.fblivevid.utils.Constant;

public class SplashActivity extends AppCompatActivity {

    private SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        preferences = getSharedPreferences(getPackageName() , MODE_PRIVATE);
        if(preferences.getBoolean(Constant.IS_LOGGED_IN ,false)){
            startActivity(new Intent(SplashActivity.this , MainActivity.class));
        }else{
            startActivity(new Intent(SplashActivity.this , LoginActivity.class));
        }
    }
}
