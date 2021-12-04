package com.example.proj;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.internal.NonNull;

public class MainActivity extends AppCompatActivity {
    EditText _txtUser, _txtPass;
    Button _btnLogin, _btnReg;
    Spinner _spinner;

    private FirebaseAuth mAuth;


//    public void getCurrentUser() {
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if (user != null) {
//            // Name, email address, and profile photo Url
//            String name = user.getDisplayName();
//            String email = user.getEmail();
//            Uri photoUrl = user.getPhotoUrl();
//
//            // Check if user's email is verified
//            boolean emailVerified = user.isEmailVerified();
//
//            // The user's ID, unique to the Firebase project. Do NOT use this value to
//            // authenticate with your backend server, if you have one. Use
//            // FirebaseUser.getIdToken() instead.
//            String uid = user.getUid();
//        }
//    }

//        @Override
//        public void onStart () {
//            super.onStart();
//            // Check if user is signed in (non-null) and update UI accordingly.
//            FirebaseUser currentUser = mAuth.getCurrentUser();
//            updateUI(currentUser);
//        }


//        public void signIn(String emailAddress, String password) {
//            mAuth.signInWithEmailAndPassword(email, password)
//                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (task.isSuccessful()) {
//                                // Sign in success, update UI with the signed-in user's information
//                                Log.d(TAG, "signInWithEmail:success");
//                                FirebaseUser user = mAuth.getCurrentUser();
//                                updateUI(user);
//                            } else {
//                                // If sign in fails, display a message to the user.
//                                Log.w(TAG, "signInWithEmail:failure", task.getException());
//                                Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
//                                        Toast.LENGTH_SHORT).show();
//                                updateUI(null);
//                            }
//        }

    private void createUser(){
        String email = _txtUser.getText().toString();
        String password = _txtPass.getText().toString();

        if (TextUtils.isEmpty(email)){
            _txtUser.setError("Email cannot be empty");
            _txtUser.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            _txtPass.setError("Password cannot be empty");
            _txtPass.requestFocus();
        }else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, PatientLogin.class));
                    }else{
                        Toast.makeText(MainActivity.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _txtPass=(EditText)findViewById(R.id.txtPass);
        _txtUser=(EditText)findViewById(R.id.txtUser);
        _btnLogin= (Button) findViewById(R.id.btnLogin);
        _spinner= (Spinner) findViewById(R.id.spinner);
        _btnReg = (Button) findViewById(R.id.btnReg);
        mAuth = FirebaseAuth.getInstance();

        _btnReg.setOnClickListener(view ->{
            createUser();
        });
        _btnLogin.setOnClickListener(view ->{
            startActivity(new Intent(MainActivity.this, PatientLogin.class));
        });
        
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this,R.array.usertype,R.layout.support_simple_spinner_dropdown_item);
        _spinner.setAdapter(adapter);
        _btnLogin.setOnClickListener(v -> {
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
        });

        _btnReg.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Patient.class);
            startActivity(intent);

        });
 //       Toast.makeText(MainActivity.this, "Firebase connection (SUCCESS)", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            startActivity(new Intent(MainActivity.this, PatientLogin.class));
        }
    }
}
