package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

//Класс имитации начального загрузочного экрана
public class Starter extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starter);

        //Ожидание перехода на экран регистрации
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask()
        {
            @Override
            public void run()
            {
                Intent intent = new Intent(Starter.this, SignIn.class);
                startActivity(intent);
            }
        };
        timer.schedule(timerTask, 2000L);
    }
}