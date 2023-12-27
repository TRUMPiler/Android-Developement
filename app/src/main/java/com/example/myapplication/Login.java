package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.Firebase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        setContentView(R.layout.activity_login);
        Button b=(Button) findViewById(R.id.buttonGG);
        b.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent i=new Intent(Login.this,Second.class);
                db.collection("Users")
                        .document("UandP").get().addOnCompleteListener(task -> {
                            Intent j=new Intent(Login.this,MainActivity.class);
                            EditText t=(EditText) findViewById(R.id.editTextText);
                            DocumentSnapshot doc=task.getResult();
                            if (task.isSuccessful())
                            {
                                String name=doc.getString("Name");
                                String Entername=t.getText().toString();
                                if(Entername.equals(name))
                                {
                                    String Password=doc.getString("Password");
                                    String EnteredPass=((EditText) findViewById(R.id.editTextTextPassword)).getText().toString();
                                    if(EnteredPass.equals(Password))
                                    {
                                        Toast.makeText(Login.this,"Login successfull",Toast.LENGTH_LONG).show();
                                        startActivity(i);
                                    }
                                    else
                                    {
                                        Toast.makeText(Login.this,"Entered Password is wrong Please try again",Toast.LENGTH_LONG).show();
                                    }
                                }
                                else
                                {
                                    Toast.makeText(Login.this,"User Doesn't Exist",Toast.LENGTH_LONG).show();
                                    startActivity(j);
                                }
                            }
                            else
                            {
                                Toast.makeText(Login.this,"Error:Cannot connect to the server\n Please check your network",Toast.LENGTH_LONG).show();
                                startActivity(j);
                            }

                        });


            }
        });
    }
}
