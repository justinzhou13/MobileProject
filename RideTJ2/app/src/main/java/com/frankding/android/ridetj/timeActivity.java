package com.frankding.android.ridetj;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class timeActivity extends AppCompatActivity {
    private static User mUser;
    private Spinner mTimeslot;
    private CheckBox mDriving;
    private Button mContinue;
    private DatabaseReference ref;
    private static final String FIREBASE_URL = "https://ridetj-e12cb.firebaseio.com/";

    public static Intent newIntent(Context packageContext,User user) {
        Intent i = new Intent(packageContext, timeActivity.class);
        mUser = user;
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        ref = FirebaseDatabase.getInstance().getReference();


        mTimeslot = (Spinner)findViewById(R.id.regionSPIN);
        mDriving = (CheckBox)findViewById(R.id.checkBox);
        mContinue = (Button)findViewById(R.id.contBTN);

        mContinue.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String timeslot = mTimeslot.getSelectedItem().toString();
                Boolean isDriving = mDriving.isChecked();

                mUser.setTime(timeslot);
                mUser.setDriving(isDriving);
                ref.child(mUser.getUsername()).setValue(mUser);

                Intent i = catchrideActivity.newIntent(timeActivity.this,mUser);
                startActivity(i);
            }
        });
    }
}
