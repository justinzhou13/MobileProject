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

    private ArrayList<String> populateArray(Map<String,Object> users) {
        ArrayList<String> rideList = new ArrayList<>();

        String requestedRegion = mUser.getRegion();
        String requestedTime = mUser.getTime();

        ArrayList<Map> userMapList = new ArrayList<>();

        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : users.entrySet()) {
            Map singleUser = (Map) entry.getValue();

            /*if(!singleUser.equals(mUser))
                userMapList.add(singleUser);*/
            if(!singleUser.get("username").equals(mUser.getUsername())) {
                String name = (String) (singleUser.get("name"));
                String region = (String) (singleUser.get("region"));
                String time = (String) (singleUser.get("time"));
                String driving;
                if (singleUser.get("driving").equals("true"))
                    driving = "Driving";
                else
                    driving = "Passenger";
                String phone = (String) (singleUser.get("phonenumber"));

                String rideOption = name + "\n" + region + "\n" + driving + "\n" + phone;

                rideList.add(rideOption);
            }
        }

        /*
        ArrayList<Map> orderedUserMaps = new ArrayList<>();
        //Users going to the same destination at the same time
        for (Map userMap : userMapList) {
            String region = (String)(userMap.get("region"));
            String time = (String)(userMap.get("time"));

            if(region.equals(requestedRegion) && time.equals(requestedTime)) {
                orderedUserMaps.add(userMap);
                userMapList.remove(userMap);
            }
        }
        //everyone else
        for (Map userMap : userMapList) {
            orderedUserMaps.add(userMap);
        }

        //populating entries for ListView
        for (Map userMap : orderedUserMaps){
            String name = (String)(userMap.get("name"));
            String region = (String)(userMap.get("region"));
            String time = (String)(userMap.get("time"));
            String driving;
            if(userMap.get("driving").equals("true"))
                driving = "Driving";
            else
                driving = "Passenger";
            String phone = (String)(userMap.get("phonenumber"));

            String rideOption = name + "\n" + region + "\n" + driving + "\n" + phone;

            rideList.add(rideOption);
        }*/

        return rideList;
    }
}
