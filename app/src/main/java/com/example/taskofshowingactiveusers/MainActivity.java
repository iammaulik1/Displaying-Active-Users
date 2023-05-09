package com.example.taskofshowingactiveusers;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.example.taskofshowingactiveusers.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    /*private RadioGroup radioGroup;*/
    private boolean isActive = true;

    private FirebaseFirestore db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = FirebaseFirestore.getInstance();





        /*radioGroup = findViewById(R.id.radioGroup);*/
        Button buttonSubmit = findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(v -> {
            /*int selectedId = radioGroup.getCheckedRadioButtonId();
            RadioButton radioButton = findViewById(selectedId);*/

            //

            //
            /*
            String selection = radioButton.getText().toString();

            if(selection.equals("Yes")) {
                isActive = true;
            } else {
                isActive = false;
            }*/
//
            EditText editTextUsername = findViewById(R.id.username);
            String username = editTextUsername.getText().toString();
  //
            addUser(username,isActive );
        });
    }

    private void addUser(String username ,boolean isActive ) {

        String userId = UUID.randomUUID().toString();  // Create Random user id

        User user = new User(userId,username,isActive);

        db.collection("users")
                .add(user)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(MainActivity.this, "User added successfully", Toast.LENGTH_SHORT).show();

                    // Get the userId of the added user
                   /* String userId = documentReference.getId();*/

                    // Pass the userId to ActiveUsersActivity
                    Intent intent = new Intent(MainActivity.this, ActiveUsersActivity.class);
                    intent.putExtra("userId", userId);
                    startActivity(intent);
                })
                .addOnFailureListener(e -> Toast.makeText(MainActivity.this, "Error adding user", Toast.LENGTH_SHORT).show());
    }

    /*private void addUser(String username ,boolean isActive ) {




        User user = new User(username,isActive);

        db.collection("users")
                .add(user)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(MainActivity.this, "User added successfully", Toast.LENGTH_SHORT).show();
                    if(isActive) {
                        Intent intent = new Intent(MainActivity.this, ActiveUsersActivity.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(e -> Toast.makeText(MainActivity.this, "Error adding user", Toast.LENGTH_SHORT).show());
    }*/
}