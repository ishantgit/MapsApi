package com.example.ishant.driventest.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.ishant.driventest.R;
import com.example.ishant.driventest.utils.IntentConstants;
import com.example.ishant.driventest.utils.SharedPreferenceUtils;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;


public class LoginActivity extends BaseActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    private Toolbar toolbar;
    LoginButton loginButton;
    private GoogleApiClient mGoogleApiClient;
    CallbackManager callbackManager;
    private SharedPreferenceUtils sharedPreferenceUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        toolbar = setUpToolbar("LOGIN");
        sharedPreferenceUtils = SharedPreferenceUtils.getInstance();
        if(sharedPreferenceUtils.retrieveStringValue(SharedPreferenceUtils.ACCESS_TOKEN_FACEBOOK,null) == null){
            goToLocationActivity();
        }
        if(!checkLoggedInByGoogle()){
            goToLocationActivity();
        }
        setUpGoogleSignIn();
        setUpFacebookLogin();
    }

    Boolean checkLoggedInByFacebook(){
        String accessToken = sharedPreferenceUtils.retrieveStringValue(SharedPreferenceUtils.ACCESS_TOKEN_FACEBOOK,"");
        if(accessToken.isEmpty()){
            return false;
        }
        return true;
    }

    private boolean checkLoggedInByGoogle(){
        if(sharedPreferenceUtils.retrieveStringValue(SharedPreferenceUtils.ACCESS_TOKEN_GOOGLE,null)!=null){
            return false;
        }else{
            return true;
        }
    }

    private void goToLocationActivity(){
        Intent intent = new Intent();
        intent.setClass(this,LocationActivity.class);
        startActivity(intent);
        finish();
    }

    private void setUpFacebookLogin(){
        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton)findViewById(R.id.login_button);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                sharedPreferenceUtils.storeStringValue(SharedPreferenceUtils.ACCESS_TOKEN_FACEBOOK,loginResult.getAccessToken().getToken());
                goToLocationActivity();
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });
    }

    private void setUpGoogleSignIn(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setScopes(gso.getScopeArray());
        signInButton.setOnClickListener(this);
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.sign_in_button){
            Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
            startActivityForResult(signInIntent, IntentConstants.GOOGLE_SIGN_REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IntentConstants.GOOGLE_SIGN_REQUEST_CODE){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess()){
                sharedPreferenceUtils.storeStringValue(SharedPreferenceUtils.ACCESS_TOKEN_GOOGLE,result.getSignInAccount().getIdToken());
                goToLocationActivity();
            }
        }else{
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }
}
