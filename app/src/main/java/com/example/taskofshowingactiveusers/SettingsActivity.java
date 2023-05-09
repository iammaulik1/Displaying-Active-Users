package com.example.taskofshowingactiveusers;




import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import android.widget.Switch;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class SettingsActivity extends AppCompatActivity {

    private static final String TAG = "SettingsActivity";
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch activeSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        activeSwitch = findViewById(R.id.active_switch);
        activeSwitch.setChecked(true);

        // Retrieve the current user's active status from Firestore and set the switch accordingly
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            db.collection("users").document(userId).get()
                    .addOnSuccessListener(documentSnapshot -> {
                        User user = documentSnapshot.toObject(User.class);
                        if (user != null && !user.isStatus()) {
                            activeSwitch.setChecked(false);
                        }
                    })
                    .addOnFailureListener(e -> Log.e(TAG, "Error getting user's active status: ", e));
        }

        activeSwitch.setOnCheckedChangeListener((buttonView, isActive) -> {
            // Update the current user's active status in Firestore
            if (currentUser != null) {
                String userId = currentUser.getUid();
                boolean active = isActive;
                String status = active ? "true" : "false";

                FirebaseFirestore.getInstance().collection("users")
                        .document(userId)
                        .update("status", status)
                        .addOnSuccessListener(aVoid -> {
                            Log.d(TAG, "User's active1 status updated successfully");

                            String message = active ? "You are now active" : "You are now inactive";
                            Toast.makeText(buttonView.getContext(), message, Toast.LENGTH_SHORT).show();
                        })
                        .addOnFailureListener(e -> {
                            Log.e(TAG, "Error updating user's active status: ", e);
                            Toast.makeText(buttonView.getContext(), "Error updating active status", Toast.LENGTH_SHORT).show();
                        });
            }
        });
    }
}

/*public class SettingsActivity extends AppCompatActivity {

    private static final String TAG = "SettingsActivity";
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch activeSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        activeSwitch = findViewById(R.id.active_switch);
        //
        activeSwitch.setChecked(true);

        // Retrieve the current user's active status from Firestore and set the switch accordingly
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            db.collection("users").document(userId).get()
                    .addOnSuccessListener(documentSnapshot -> {
                        User user = documentSnapshot.toObject(User.class);
                        if (user != null && !user.isStatus()) {
                            activeSwitch.setChecked(false);
                        }
                    })
                    .addOnFailureListener(e -> Log.e(TAG, "Error getting user's active status: ", e));
        }

        activeSwitch.setOnCheckedChangeListener((buttonView, isActive) -> {
            // Update the current user's active status in Firestore
            if (currentUser != null) {
                String userId = currentUser.getUid();
                //
                boolean active = isActive;
                //
                String status = active? "true":"false";

                // 2nd active to inChecked
                FirebaseFirestore.getInstance().collection("users")
                        .document(userId)
                        .update("status", status)
                        *//*.addOnSuccessListener(aVoid -> Log.d(TAG, "User's active status updated successfully"))
                        .addOnFailureListener(e -> Log.e(TAG, "Error updating user's active status: ", e));*//*

                        .addOnSuccessListener(aVoid -> {
                            Log.d(TAG , "users active status updated successfully");

                            String message = active ? "you are now active" : "You are now Inactive";
                            Toast.makeText(buttonView.getContext(),message , Toast.LENGTH_SHORT).show();
                        })
                        .addOnFailureListener(e ->{
                            Log.e(TAG,"Error updating users active status" , e);
                            Toast.makeText(buttonView.getContext(),"Error Updating active status" , Toast.LENGTH_SHORT).show();
                        });
            }
        });

    }
    }*/

