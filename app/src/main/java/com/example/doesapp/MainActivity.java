package com.example.doesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView titlePage, subtitlePage, endPage, btnAddNew;

    DatabaseReference reference;
    RecyclerView ourDoes;
    ArrayList<MyDoes> list;
    DoesAdapter doesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titlePage = findViewById(R.id.titlepage);
        subtitlePage = findViewById(R.id.subtitlepage);
        endPage = findViewById(R.id.endpage);

        btnAddNew = findViewById(R.id.btnaddnew);

        // import font
        Typeface monsLight = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Light.ttf");
        Typeface monsMedium = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Medium.ttf");

        // Customize the font
        titlePage.setTypeface(monsMedium);
        subtitlePage.setTypeface(monsLight);
        endPage.setTypeface(monsLight);

        btnAddNew.setTypeface(monsLight);

        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(getApplicationContext(), NewTaskAct.class);
                startActivity(intent);
            }
        });

        // data
        ourDoes = (RecyclerView) findViewById(R.id.ourdoes);
        ourDoes.setLayoutManager(new LinearLayoutManager(this));
        ourDoes.setHasFixedSize(true);
        list = new ArrayList<MyDoes>();

        // mengambil data dari firebase
        reference = FirebaseDatabase.getInstance().getReference().child("DoesApp");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // set code untuk megambil data dan menaruh di layout
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()) {
                    MyDoes p = dataSnapshot1.getValue(MyDoes.class);
                    list.add(p);
                }
                doesAdapter = new DoesAdapter(MainActivity.this, list);
                ourDoes.setAdapter(doesAdapter);
                doesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // set code untuk menunjukan pesan error
                Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
