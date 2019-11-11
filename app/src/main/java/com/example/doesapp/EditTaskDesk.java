package com.example.doesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditTaskDesk extends AppCompatActivity {

    EditText titleDoes, descDoes, dueDoes;
    Button btnSaveUpdate, btnDelete;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task_desk);

        titleDoes = findViewById(R.id.titledoes);
        descDoes = findViewById(R.id.descdoes);
        dueDoes = findViewById(R.id.duedoes);

        btnSaveUpdate = findViewById(R.id.btnsaveupdate);
        btnDelete = findViewById(R.id.btndelete);

        //import font
        Typeface monsMedium = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Medium.ttf");

        btnSaveUpdate.setTypeface(monsMedium);
        btnDelete.setTypeface(monsMedium);

        // mengambil sebuah nilai dari halaman sebelumnya
        titleDoes.setText(getIntent().getStringExtra("titleDoes"));
        descDoes.setText(getIntent().getStringExtra("descDoes"));
        dueDoes.setText(getIntent().getStringExtra("dateDoes"));

        final String keykeyDoes = getIntent().getStringExtra("keyDoes");

        reference = FirebaseDatabase.getInstance().getReference().child("DoesApp").
                child("Does" + keykeyDoes);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            Intent intent = new Intent(EditTaskDesk.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        btnSaveUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("titleDoes").setValue(titleDoes.getText().toString());
                        dataSnapshot.getRef().child("descDoes").setValue(descDoes.getText().toString());
                        dataSnapshot.getRef().child("dateDoes").setValue(dueDoes.getText().toString());
                        dataSnapshot.getRef().child("keyDoes").setValue(keykeyDoes);

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
