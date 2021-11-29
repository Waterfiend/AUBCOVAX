package com.example.proj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MedicalPersonnel extends AppCompatActivity {
    Button _btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_personnel);
        _btnSearch= (Button) findViewById(R.id.btnSearch);

        _btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MedicalPersonnel.this, DoseInfo.class);
                startActivity(intent);
            }
        });
    }
}

