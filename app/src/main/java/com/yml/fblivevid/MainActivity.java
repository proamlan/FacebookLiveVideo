package com.yml.fblivevid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Arrays;
import java.util.Set;

public class MainActivity extends AppCompatActivity {


    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());

        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_main);

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        Set<String> stringSet = accessToken.getPermissions();
        for (String s : stringSet) {
            Log.e("amlan", "perm " + s);
        }
        String userId = AccessToken.getCurrentAccessToken().getUserId();
        /*new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/" + userId + "/live_videos",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        Log.e("amlan", "response" + response.toString());
                        *//* handle the result *//*
                    }
                }
        ).executeAsync();*/

        // User Object
        /*new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/" + userId,
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        *//* handle the result *//*
                        Log.e("amlan", "response" + response.toString());
                    }
                }
        ).executeAsync();*/


        //Live Video
        Bundle bundle = new Bundle();
//        bundle.putString("broadcast_status","UNPUBLISHED");
        bundle.putString("description","Test Upload");
        bundle.putBoolean("published",false);
        bundle.putBoolean("save_vod",true);
        bundle.putString("stream_type","REGULAR");
        bundle.putString("title","Some Title");
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/"+userId+"/live_videos",
                bundle,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        /* handle the result */
                        Log.e("amlan", "response" + response.toString());
                    }
                }
        ).executeAsync();

    }

    public void onRequestPermssion(View view) {
        String permissions[] = new String[]{"publish_actions", "manage_pages", "publish_pages"};
        String readPermissions[] = new String[]{"user_managed_groups", "user_events"};

        LoginManager.getInstance().logInWithPublishPermissions(this, Arrays.asList(permissions));
//        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList(readPermissions));

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.e("succces", "loginResult");
            }

            @Override
            public void onCancel() {
                Log.e("amlan", "cancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.e("amlan", "error");
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
