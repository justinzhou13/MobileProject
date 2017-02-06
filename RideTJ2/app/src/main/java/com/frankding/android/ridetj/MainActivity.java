package com.frankding.android.ridetj;

import android.app.Activity;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText mEmailfield;
    private EditText mPassfield;
    private Button mLogin;
    private Button mSignup;
    private User mNewUser;
    private ArrayList<String> mSignInfo;
    private static String mUsername;
    private static String mPassword;
    private DatabaseReference ref;
    private static final String FIREBASE_URL = "https://ridetj-e12cb.firebaseio.com/";
    private static final int REQUEST_CODE_USER = 1;
    private DataSnapshot mData;

    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK)
            return;
        if (requestCode == REQUEST_CODE_USER){
            if(data==null)
                return;
            mSignInfo = RegisterActivity.user(data);
            mNewUser = new User(mSignInfo.get(0),mSignInfo.get(1),mSignInfo.get(2),mSignInfo.get(3),mSignInfo.get(4));
            ref.child(mSignInfo.get(3)).setValue(mNewUser);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ref = FirebaseDatabase.getInstance().getReference();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                try{
                mData = dataSnapshot;}
                catch (Exception e){
                    System.out.print(e);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError){
                System.out.println("The read failed: "+databaseError.getCode());
            }

        });


        mEmailfield = (EditText)findViewById(R.id.editText);
        mPassfield = (EditText)findViewById(R.id.editText2);
        mLogin = (Button)findViewById(R.id.button10);
        mSignup = (Button)findViewById(R.id.button11);

        mLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mUsername = mEmailfield.getText().toString();
                mPassword = mPassfield.getText().toString();
                try{
                User temp = mData.child(mUsername).getValue(User.class);
                if(mPassword.equals(temp.getPassword())) {
                    Toast.makeText(MainActivity.this, "Login Success!", Toast.LENGTH_SHORT).show();
                    Intent j = timeActivity.newIntent(MainActivity.this, temp);
                    startActivity(j);
                }
                    else{
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
                }
                catch(Exception e){
                    System.out.println(e);
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mSignup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = RegisterActivity.newIntent(MainActivity.this);
                startActivityForResult(i,REQUEST_CODE_USER);
            }
        });

    }
}
