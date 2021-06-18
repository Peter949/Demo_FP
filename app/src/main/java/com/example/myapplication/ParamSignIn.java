package com.example.myapplication;

//Класс-токен создан для интерфейса API
public class ParamSignIn
{
    //Переменные для авторизации
    private String email;
    private String password;

    //Создание аксессоров для получения и ввода данных авторизации

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
