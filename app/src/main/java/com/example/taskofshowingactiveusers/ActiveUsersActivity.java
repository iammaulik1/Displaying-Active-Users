package com.example.taskofshowingactiveusers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class ActiveUsersActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List<User> userList;

    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_users);

        db = FirebaseFirestore.getInstance();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userList = new ArrayList<>();
        userAdapter = new UserAdapter(this, userList);
        recyclerView.setAdapter(userAdapter);

        getUsers();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.menu_settings){
            Intent intent = new Intent(this,SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void getUsers() {

        //
        Log.d("ActiveUsersActivity", "Getting active users");



        db.collection("users")
                .whereEqualTo("status", true)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        userList.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {

                            String username = document.getString("username");
                            //boolean isActive = document.getBoolean("isActive");



                            User user = new User("",username,true);  // uid ?
                            userList.add(user);
                        }
                        //
                        Log.e("ActiveUsersActivity" , "Number of Active users: " +userList.size());

                        if (userAdapter != null){
                            userAdapter.notifyDataSetChanged();
                        }else {
                            userAdapter = new UserAdapter(this,userList);
                            recyclerView.setAdapter(userAdapter);
                        }
                        //
                        userAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(this, "Error getting active users", Toast.LENGTH_SHORT).show();
                    }
                });
        }
    }



   /* private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List<User> userList;

    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_users);

        db = FirebaseFirestore.getInstance();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userList = new ArrayList<>();
        userAdapter = new UserAdapter(this, userList);
        recyclerView.setAdapter(userAdapter);

        getUsers();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.menu_settings){
            Intent intent = new Intent(this,SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void getUsers() {

        //
        Log.d("ActiveUsersActivity", "Getting active users");



        db.collection("users")
                .whereEqualTo("active", true)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        userList.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {

                            String username = document.getString("username");
                            //boolean isActive = document.getBoolean("isActive");



                            User user = new User(username,true);
                            userList.add(user);
                        }
                        //
                        Log.e("ActiveUsersActivity" , "Number of Active users: " +userList.size());

                        if (userAdapter != null){
                            userAdapter.notifyDataSetChanged();
                        }else {
                            userAdapter = new UserAdapter(this,userList);
                            recyclerView.setAdapter(userAdapter);
                        }
                        //
                        userAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(this, "Error getting active users", Toast.LENGTH_SHORT).show();
                    }
                });
    }*/
