package com.example.finalstudybuddy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CourseFragment extends Fragment {

    EditText course;
    Button find;
    String id;
    DatabaseReference ref;
    Courses c;
    long maxId2 = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        System.out.println("YOOOOO");
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        find = view.findViewById(R.id.findButton);

        ref = FirebaseDatabase.getInstance().getReference().child("User");
        c = new Courses();



        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    maxId2=(dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;

    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        //super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.courseEditText).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                id = course.getText().toString();
                System.out.println("This is the course " + id);
                c.setCour(id);
                ref.child(String.valueOf(maxId2+1)).setValue(c);

            }

        });

    }
}
