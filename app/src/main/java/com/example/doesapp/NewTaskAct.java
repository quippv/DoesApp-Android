package com.example.doesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class NewTaskAct extends AppCompatActivity {

    TextView titlePage, addTitle, addDesc, addDue;
    EditText titleDoes, descDoes, dueDoes;
    Button btnSaveTask, btnCancelTask;
    DatabaseReference reference;
    Integer doesNum = new Random().nextInt();
    String keyDoes = Integer.toString(doesNum);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        titlePage = findViewById(R.id.titlepage);

        addTitle = findViewById(R.id.addtitle);
        titleDoes = findViewById(R.id.titledoes);

        addDesc = findViewById(R.id.adddesc);
        descDoes = findViewById(R.id.descdoes);

        addDue = findViewById(R.id.adddue);
        dueDoes = findViewById(R.id.duedoes);

        btnSaveTask = findViewById(R.id.btnsavetask);
        btnCancelTask = findViewById(R.id.btncanceltask);

        //import font
        Typeface monsLight = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Light.ttf");
        Typeface monsMedium = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Medium.ttf");

        // Customize the font
        titlePage.setTypeface(monsMedium);

        addTitle.setTypeface(monsLight);
        titleDoes.setTypeface(monsMedium);

        addDesc.setTypeface(monsLight);
        descDoes.setTypeface(monsMedium);

        addDue.setTypeface(monsLight);
        dueDoes.setTypeface(monsMedium);

        btnCancelTask.setTypeface(monsLight);
        btnSaveTask.setTypeface(monsMedium);

        btnSaveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Masukan data ke database
                reference = FirebaseDatabase.getInstance().getReference().child("DoesApp").
                        child("Does" + doesNum);
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("titleDoes").setValue(titleDoes.getText().toString());
                        dataSnapshot.getRef().child("descDoes").setValue(descDoes.getText().toString());
                        dataSnapshot.getRef().child("dateDoes").setValue(dueDoes.getText().toString());
                        dataSnapshot.getRef().child("keyDoes").setValue(keyDoes);

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        btnCancelTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewTaskAct.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        });

    }
}
