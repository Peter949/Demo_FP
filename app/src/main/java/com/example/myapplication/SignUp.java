package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUp extends AppCompatActivity {

    private Retrofit retrofit;
    private API api;
    private EditText email, password, repeat, firstName, lastName;
    private Button submit;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        submit = findViewById(R.id.submitReg);
        repeat = findViewById(R.id.repeat);
        email = findViewById(R.id.emailReg);
        password = findViewById(R.id.passwordReg);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("http://cinema.areas.su/").build();
        api = retrofit.create(API.class);
        textView = findViewById(R.id.changereg);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, SignIn.class);
                startActivity(intent);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().equals("") || password.getText().toString().equals("") || repeat.getText().toString().equals("") || firstName.getText().toString().equals("") || lastName.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Заполните все поля!", Toast.LENGTH_SHORT).show();

                }
                else {
                    if (email.getText().toString().equals(repeat.getText().toString())) {
                        reg();
                    } else {
                        Toast.makeText(getApplicationContext(), "Пароли не соотвествуют!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    private void reg()
    {
        ParamSignUp paramSignUp = new ParamSignUp();
        paramSignUp.setEmail(email.getText().toString());
        paramSignUp.setPassword(password.getText().toString());
        paramSignUp.setFirstName(firstName.getText().toString());
        paramSignUp.setLastName(lastName.getText().toString());
        Call<ParamSignUp> call = api.doSignUp(paramSignUp);
        call.enqueue(new Callback<ParamSignUp>() {
            @Override
            public void onResponse(Call<ParamSignUp> call, Response<ParamSignUp> response)
            {
                if(response.isSuccessful())
                {

                }
                else
                {

                }
            }

            @Override
            public void onFailure(Call<ParamSignUp> call, Throwable t)
            {

            }
        });
    }
}