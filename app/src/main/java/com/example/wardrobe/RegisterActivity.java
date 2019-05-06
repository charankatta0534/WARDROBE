package com.example.wardrobe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText mTextUsername;
    EditText mTextPassword;
    EditText mConfTextPassword;
    Button mButtonRegister;
    TextView mTextViewLogin;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db=new DatabaseHelper(this);
        mTextUsername=(EditText)findViewById(R.id.edittext_username);
        mTextPassword=(EditText)findViewById(R.id.edittext_password);
        mConfTextPassword=(EditText)findViewById(R.id.edittext_cnf_password);
        mButtonRegister=(Button) findViewById(R.id.buttonregister);
        mTextViewLogin=(Button)findViewById(R.id.textview_login);
        mTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginIntent=new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(LoginIntent);
            }
        });
        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=mTextUsername.getText().toString().trim();
                String pwd=mTextPassword.getText().toString().trim();
                String cnf_password=mConfTextPassword.getText().toString().trim();
                if (pwd.equals(cnf_password))
                {
                    long val=db.adduser(user,pwd);
                    if (val>0) {
                        Toast.makeText(RegisterActivity.this, "you have registered", Toast.LENGTH_SHORT).show();
                        Intent movetologin = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(movetologin);
                    }
                    else
                    {
                        Toast.makeText(RegisterActivity.this, "Error in Registration", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(RegisterActivity.this, "password is not matching", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
