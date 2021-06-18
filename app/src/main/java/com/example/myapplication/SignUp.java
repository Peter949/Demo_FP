package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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
    private String error;


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
                    error = "Заполните все поля";
                    Dilog(SignUp.this);

                }
                else
                    {
                    if (!password.getText().toString().equals(repeat.getText().toString())) {

                        error = "Пароли не соответствуют";
                        Dilog(SignUp.this);
                    } else {
                        reg();

                    }
                }
            }
        });
    }
    public void Dilog(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Ошибка")
                .setMessage(error)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id)
                    {
                    }
                });
        builder.create().show();
    }
    private boolean emailVer(String email)
    {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    private void reg()
    {
        if(!emailVer(email.getText().toString()))
        {
            error = "Не верная почта";
            Dilog(SignUp.this);

        }
        else
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
}