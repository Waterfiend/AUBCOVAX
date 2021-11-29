package com.example.proj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText _txtUser, _txtPass;
    Button _btnLogin, _btnReg;
    Spinner _spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _txtPass=(EditText)findViewById(R.id.txtPass);
        _txtUser=(EditText)findViewById(R.id.txtUser);
        _btnLogin= (Button) findViewById(R.id.btnLogin);
        _spinner= (Spinner) findViewById(R.id.spinner);
        _btnReg = (Button) findViewById(R.id.btnReg);

        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this,R.array.usertype,R.layout.support_simple_spinner_dropdown_item);
        _spinner.setAdapter(adapter);
        _btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String item = _spinner.getSelectedItem().toString();
                if (_txtUser.getText().toString().equals("admin") && _txtPass.getText().toString().equals("admin") && item.equals("Admin")) {
                    Intent intent = new Intent(MainActivity.this, Admin.class);
                    startActivity(intent);


                } else if (_txtUser.getText().toString().equals("patient") && _txtPass.getText().toString().equals("patient") && item.equals("Patient")) {
                    Intent intent = new Intent(MainActivity.this, PatientLogin.class);
                    startActivity(intent);

                }else if (_txtUser.getText().toString().equals("admin") && _txtPass.getText().toString().equals("admin") && item.equals("Medical Personnel")) {
                    Intent intent = new Intent(MainActivity.this, MedicalPersonnel.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
            }
        });
        _btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Patient.class);
                startActivity(intent);

            }
        });
    }
}
