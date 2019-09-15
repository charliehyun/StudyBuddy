package com.example.finalstudybuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    Button login;
    Button register;

    String username;
    String pass;
    String userCompare;
    String passCompare;
    DatabaseReference reff;
    User user;
    long maxId = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.emailEditText);
        password = findViewById(R.id.passEditText);
        login = findViewById(R.id.loginButton);
        register = findViewById(R.id.registerButton);



        reff = FirebaseDatabase.getInstance().getReference().child("User");
        System.out.println(reff.toString());
        user = new User();




        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    maxId=(dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                username = email.getText().toString();
                pass = password.getText().toString();
                user.setEmail(username);
                user.setPassword(pass);
                //reff.push().setValue(user);
                reff.child(String.valueOf(maxId+1)).setValue(user);
                Toast.makeText(MainActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, Buddy.class);
                i.putExtra("fragment_home", CourseFragment.class);
                startActivity(i);

                //startActivity(new Intent(MainActivity.this, CourseFragment.class));

            }

        });








        /*login.setOnClickListener(new View.OnClickListener() {
            @Override

            *//*public void onClick(View v) {
                username = email.getText().toString();
                pass = password.getText().toString();

                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        userCompare = dataSnapshot.child("email").getValue().toString();
                        passCompare = dataSnapshot.child("password").getValue().toString();
                        Toast.makeText(MainActivity.this, userCompare + " " + passCompare, Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                    });

                }*//*
                });*/



        }



    }

