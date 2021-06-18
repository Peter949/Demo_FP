package com.example.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

//Интерфейс взаимодействия с сайтом через методы POST и GET
public interface API
{
    //Метод авторизации через POST с параметром ParamSignIn
    @POST("auth/login")
    Call<ParamSignIn> doSignIn(@Body ParamSignIn paramSignIn);

    @GET("movies?filter=new")
    Call<List<ParamKino>> getKino();
}
