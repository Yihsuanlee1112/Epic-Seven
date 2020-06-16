package com.github.EpicSeven;import android.content.Context;import android.content.SharedPreferences;import com.github.EpicSeven.data.EpicSevenApi;import com.google.gson.Gson;import com.google.gson.GsonBuilder;import retrofit2.Retrofit;import retrofit2.converter.gson.GsonConverterFactory;public class Singletons {    private static Gson gsonInstance = null;    private static EpicSevenApi aovInstance;    private static SharedPreferences sharedPreferencesInstance;    public static Gson getGson(){        if(gsonInstance == null) {            return new GsonBuilder()                    .setLenient()                    .create();        }        return gsonInstance;    }    public static EpicSevenApi getArenaOfValorAPI(){        if(aovInstance== null){            Retrofit retrofit = new Retrofit.Builder()                    .baseUrl("https://api.epicsevendb.com/")                    .addConverterFactory(GsonConverterFactory.create(getGson()))                    .build();            aovInstance = retrofit.create(EpicSevenApi.class);        }        return aovInstance;    }    public static SharedPreferences getSharedPreferencesInstance(Context context) {        if (sharedPreferencesInstance == null) {            sharedPreferencesInstance = context.getSharedPreferences("application_esiea", Context.MODE_PRIVATE);        }        return sharedPreferencesInstance;    }}