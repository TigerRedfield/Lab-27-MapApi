package com.example.lab_27_vasilev_mapapi_403;

import android.app.Activity;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHelper {


    static final String Base_URL = "http://194.87.68.149:5002/";  //Базовый адрес для конечных точек API

    public  static  CallMapApi getMapApi() {

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder() // создаём экземпляр библиотеки OkHTTP,
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)) // чтобы реализовать связь по HTTP
                .build();

        Retrofit retrofit = new Retrofit.Builder() // создаём экземпляр библиотеки Retrofit
                .baseUrl(Base_URL) // чтобы работать с REST сервисами
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(CallMapApi.class);

    }
}
