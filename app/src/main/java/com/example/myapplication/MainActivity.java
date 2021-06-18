package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
{
    private API api;
    private Retrofit retrofit;
    private RecyclerView rec;
    private List<ParamKino> kinos;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("http://cinema.areas.su/").build();
        api = retrofit.create(API.class);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rec = findViewById(R.id.rec);
        rec.setLayoutManager(linearLayoutManager);
        kinos = new ArrayList<>();
        KinoAdapter adapter = new KinoAdapter(kinos);
        rec.setAdapter(adapter);
        func();
    }
    private void func()
    {
        Call<List<ParamKino>> call = api.getKino();
        call.enqueue(new Callback<List<ParamKino>>() {
            @Override
            public void onResponse(Call<List<ParamKino>> call, Response<List<ParamKino>> response)
            {
                if(response.isSuccessful())
                {
                    kinos.addAll(response.body());
                    rec.getAdapter().notifyDataSetChanged();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Произошла ошибка, зайдите позже!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<ParamKino>> call, Throwable t)
            {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}