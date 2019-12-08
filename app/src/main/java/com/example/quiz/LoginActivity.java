package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText email,password;
    private Button btnLogin;
    private CheckBox check;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        check = findViewById(R.id.check);
        btnLogin = findViewById(R.id.btnLogin);

        SharedPreferences saveUser = getSharedPreferences("User", Context.MODE_PRIVATE);
        if (saveUser.getString("username","").isEmpty()){
            check.setChecked(false);
        }
        else{
            email.setText(saveUser.getString("username",""));
            password.setText(saveUser.getString("password",""));
            check.setChecked(true);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check.isChecked()) {
                    String user = email.getText().toString();
                    String pswd = password.getText().toString();

                    sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("username", user);
                    editor.putString("password", pswd);
                    editor.commit();
                    Toast.makeText(LoginActivity.this, "Check box checked", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(LoginActivity.this,QuizActivity.class);
                    intent.putExtra("userName", user);
                    startActivity(intent);

                } else {
                    sharedPreferences = getSharedPreferences("User", 0);
                    sharedPreferences.edit().clear().commit();

                    Toast.makeText(LoginActivity.this, "Check box not checked", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}
