package com.frankding.android.ridetj;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class catchrideActivity extends AppCompatActivity {

    private static User mUser;
    private DatabaseReference ref;
    private ListView mListView;
    private DataListener listener;

    public static Intent newIntent(Context packageContext, User user) {
        Intent i = new Intent(packageContext, catchrideActivity.class);
        mUser = user;
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catchride);

        configListView();
    }

    private void configListView() {
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference();

        mListView = (ListView)(findViewById(R.id.rideListLV));

        listener = new DataListener(this, mListView);

        userRef.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Get map of users in dataSnapshot
                        List<String>  rideArray = new ArrayList<>();
                        rideArray = populateArray((Map<String,Object>) dataSnapshot.getValue());
                        listener.onSuccess(rideArray);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle error
                    }
                });
    }

    private ArrayList<String> populateArray(Map<String,Object> users){
        ArrayList<String> rideList = new ArrayList<>();

        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : users.entrySet()){

            //Get user map
            Map singleUser = (Map) entry.getValue();
            //Get phone field and append to list
            String username = (String)(singleUser.get("username"));
            String region = (String)(singleUser.get("region"));
            String driving;
            if(singleUser.get("driving").equals("true"))
                driving = "Driving";
            else
                driving = "Passenger";
            String phone = (String)(singleUser.get("phonenumber"));

            rideList.add(username);
        }

        return rideList;
    }
}
