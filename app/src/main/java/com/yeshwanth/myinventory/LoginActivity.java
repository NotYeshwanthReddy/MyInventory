package com.yeshwanth.myinventory;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    EditText mEmail, mPassword;
    String email_id, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmail = (EditText) findViewById(R.id.Email_ID_1);
        mPassword = (EditText) findViewById(R.id.Password_1);
        GetUserDetails();
        if(BackgroundTask.LoginState == 1){
            GotoMain();
        }
    }
    public void RegisterAct(View view){
        startActivity(new Intent(this, RegisterActivity.class));
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
        finish();
    }
    public void LoginButton(View view){
        email_id = mEmail.getText().toString();
        password = mPassword.getText().toString();

        if(BackgroundTask.LoginState == 1){
            SaveUserDetails();
            GotoMain();
        }
        else if (!TextUtils.isEmpty(email_id) || !TextUtils.isEmpty(password)){
            Log.d("logging in", "login");
            String method = "login";
            BackgroundTask backgroundTask = new BackgroundTask(this);
            backgroundTask.execute(method, email_id, password);
        }
    }
    public void GotoMain(){
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
        finish();
    }

    public void SaveUserDetails(){
        SharedPreferences sharedPreferences = getSharedPreferences("UserDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("UserEmail", mEmail.getText().toString());
        editor.putString("UserName", BackgroundTask.UserName);
        editor.putInt("LoginState", BackgroundTask.LoginState);
        editor.apply();
    }
    public void GetUserDetails(){
        SharedPreferences sharedPreferences = getSharedPreferences("UserDetails", Context.MODE_PRIVATE);
        BackgroundTask.LoginState = sharedPreferences.getInt("LoginState", 0);
    }
}
