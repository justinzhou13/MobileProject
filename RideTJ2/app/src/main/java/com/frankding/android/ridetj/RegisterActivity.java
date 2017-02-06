package com.frankding.android.ridetj;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    private EditText fullNameET;
    private EditText emailET;
    private EditText phoneET;
    private Spinner regionSPIN;
    private EditText passwordET;
    private Button mSubmit;
    private ArrayList<String> mUserInfo;
    private static final String EXTRA_USER =  "com.frankding.android.ridetj.user";

    public static Intent newIntent(Context packageContext) {
        Intent i = new Intent(packageContext, RegisterActivity.class);
        return i;
    }

    public static ArrayList<String> user(Intent result){
        return result.getStringArrayListExtra(EXTRA_USER);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fullNameET = (EditText)(findViewById(R.id.fullNameET));
        emailET = (EditText)(findViewById(R.id.emailET));
        phoneET = (EditText)(findViewById(R.id.phoneET));
        regionSPIN = (Spinner)(findViewById(R.id.regionSPIN));
        passwordET = (EditText)(findViewById(R.id.passwordET));
        mSubmit = (Button)findViewById(R.id.registerBTN);
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUserInfo = new ArrayList<String>();
                String fullName = fullNameET.getText().toString();
                String email = emailET.getText().toString();
                String region = regionSPIN.getSelectedItem().toString();
                String password = passwordET.getText().toString();
                String phone = phoneET.getText().toString();
                mUserInfo.add(fullName);
                mUserInfo.add(region);
                mUserInfo.add(password);
                mUserInfo.add(email);
                mUserInfo.add(phone);
                setUserResult(mUserInfo);
                Toast.makeText(RegisterActivity.this, "Registration Complete!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
    private void setUserResult(ArrayList<String> user){
        Intent data = new Intent();
        data.putExtra(EXTRA_USER,user);
        setResult(RESULT_OK, data);
    }
}
