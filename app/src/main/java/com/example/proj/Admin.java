package com.example.proj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Admin extends AppCompatActivity {
    Button _btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        _btnSearch= (Button) findViewById(R.id.btnSearch);

        _btnSearch.setOnClickListener(v -> {
            Intent intent = new Intent(Admin.this, DoseInfo.class);
            startActivity(intent);
        });
    }
}
