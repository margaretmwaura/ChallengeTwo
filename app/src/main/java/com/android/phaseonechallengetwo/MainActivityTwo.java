package com.android.phaseonechallengetwo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivityTwo extends AppCompatActivity {

    EditText emailTv , passwordTv;
    String email, password;
    Button signUp;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_two);

        mAuth= FirebaseAuth.getInstance();

       emailTv = (EditText) findViewById(R.id.editText_gmail);
       passwordTv = (EditText) findViewById(R.id.editText_password);
       signUp = (Button) findViewById(R.id.button);

       signUp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               email = emailTv.getText().toString();
               password = passwordTv.getText().toString();

               createAccount();
           }


       });

    }

    private void createAccount()
    {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("Acount", "account created");
                            Intent intent = new Intent(MainActivityTwo.this,EnterDetails.class);
                            startActivity(intent);
                        } else
                            {

                                Log.d("Account","Account not created ");
                        }

                    }
                });
    }

}
