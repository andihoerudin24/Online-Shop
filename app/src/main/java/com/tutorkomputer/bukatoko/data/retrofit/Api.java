package com.tutorkomputer.bukatoko.data.retrofit;

import com.tutorkomputer.bukatoko.utils.Constans;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    private static  Retrofit retrofit =null;
    public static Retrofit getUrl(){
         retrofit = new Retrofit.Builder()
                .baseUrl(Constans.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
           return  retrofit;
         }


    public static Retrofit getUrlraja(String base_url){
        retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return  retrofit;
    }
}
