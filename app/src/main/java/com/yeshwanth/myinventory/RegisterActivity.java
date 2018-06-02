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
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText mUserName, mEmail, mBatch, mSection, mEnrollmentNo, mPhoneNumer, mPassword, mConfirmPassword;
    String user_name, email_id, batch, section, enrollment_no, phone_number, password, confpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mUserName = (EditText) findViewById(R.id.User_Name);
        mEmail = (EditText) findViewById(R.id.Email_ID);
        mSection = (EditText) findViewById(R.id.Section);
        mEnrollmentNo = (EditText) findViewById(R.id.Enrollment_Number);
        mPhoneNumer = (EditText) findViewById(R.id.PhoneNumber);
        mBatch = (EditText) findViewById(R.id.Batch);
        mPassword = (EditText) findViewById(R.id.Password);
        mConfirmPassword= (EditText) findViewById(R.id.Confirm_Password);
    }

    public void LoginAct(View view){
        startActivity(new Intent(this, LoginActivity.class));
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
        finish();
    }
    public void RegisterButton(View view){
        user_name = mUserName.getText().toString();
        email_id = mEmail.getText().toString();
        batch = mBatch.getText().toString();
        section = mSection.getText().toString();
        enrollment_no = mEnrollmentNo.getText().toString();
        phone_number = mPhoneNumer.getText().toString();
        password = mPassword.getText().toString();
        confpassword = mConfirmPassword.getText().toString();

        if (TextUtils.isEmpty(user_name) || TextUtils.isEmpty(email_id) ||
                TextUtils.isEmpty(batch) || TextUtils.isEmpty(section) ||
                TextUtils.isEmpty(enrollment_no) || TextUtils.isEmpty(phone_number) ||
                TextUtils.isEmpty(password) || TextUtils.isEmpty(confpassword) ){
            Toast.makeText(this, "Enter all Details", Toast.LENGTH_SHORT).show();
        }
        else{
            if(confpassword.equals(password)){
                Log.d("registering", "register");
                String method = "register";
                BackgroundTask backgroundTask = new BackgroundTask(this);
                backgroundTask.execute(method, user_name, email_id, batch, section, enrollment_no, phone_number, password);
                SaveUserDetails();
                startActivity(new Intent(this, LoginActivity.class));
                overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
                finish();
            }
            else{
                Toast.makeText(this, "password doesn't match", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void SaveUserDetails(){
        SharedPreferences sharedPreferences = getSharedPreferences("UserDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("UserEmail", mEmail.getText().toString());
        editor.putString("UserID", mEnrollmentNo.getText().toString());
        editor.putString("UserName", mUserName.getText().toString());
        editor.putString("UserPhoneNumber", mPhoneNumer.getText().toString());
        editor.apply();
    }
}
