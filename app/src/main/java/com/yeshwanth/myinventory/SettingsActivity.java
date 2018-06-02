package com.yeshwanth.myinventory;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    public ImageButton mBackButton;
    private TextView mname, memail, mid, mphno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mname = (TextView) findViewById(R.id.TextViewName);
        memail = (TextView) findViewById(R.id.TextViewEmail);
        mid = (TextView) findViewById(R.id.TextViewUserID);
        mphno = (TextView) findViewById(R.id.TextViewMobileNo);

        SharedPreferences sharedPreferences = getSharedPreferences("UserDetails", Context.MODE_PRIVATE);
        mname.setText(sharedPreferences.getString("UserName", "Name"));
        memail.setText(sharedPreferences.getString("UserEmail", "xyz@gmail.com"));
        mid.setText(sharedPreferences.getString("UserID", "U101116FCS190"));
        mphno.setText(sharedPreferences.getString("UserPhoneNumber", "9123456789"));

        mBackButton = (ImageButton) findViewById(R.id.SettingsBackButton);
        mBackButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
                finish();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabReportABug);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Report a Bug", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public void LogoutFunction (View view){
        BackgroundTask.LoginState = 0;

        SharedPreferences sharedPreferences = getSharedPreferences("UserDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("LoginState", 0);
        editor.apply();

        startActivity(new Intent(SettingsActivity.this, LoginActivity.class));
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
        finish();
    }

}
