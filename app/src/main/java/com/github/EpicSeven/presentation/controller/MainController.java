package com.github.EpicSeven.presentation.controller;import android.content.SharedPreferences;import com.github.EpicSeven.Constants;import com.github.EpicSeven.Singletons;import com.github.EpicSeven.presentation.model.EpicSevenResponse;import com.github.EpicSeven.presentation.model.Hero;import com.github.EpicSeven.presentation.view.MainActivity;import com.google.gson.Gson;import com.google.gson.reflect.TypeToken;import java.lang.reflect.Type;import java.util.ArrayList;import java.util.List;import retrofit2.Call;import retrofit2.Callback;import retrofit2.Response;public class MainController {    private SharedPreferences sharedPreferences;    private Gson gson;    private MainActivity view;    private List<Hero> AovHeroes = new ArrayList<>();    public MainController(MainActivity mainActivity, Gson gson, SharedPreferences sharedPreferences){        this.view = mainActivity;        this.gson = gson;        this.sharedPreferences = sharedPreferences;    }    public void onStart(){        List<Hero> AovHeroes= getDataFromCache();        if(getDataFromCache()!= null)            view.showList(AovHeroes);        else            APIcall();    }    private void APIcall(){        Call<EpicSevenResponse> call = Singletons.getArenaOfValorAPI().getHeroResponse();        call.enqueue(new Callback<EpicSevenResponse>() {            @Override            public void onResponse(Call<EpicSevenResponse> call, Response<EpicSevenResponse> response) {                if(response.isSuccessful()&& response.body()!= null){                    AovHeroes = response.body().getResults();                    saveList(AovHeroes);                    view.showList(AovHeroes);                }                else{                    view.showError();                }            }            @Override            public void onFailure(Call<EpicSevenResponse> call, Throwable t) {                view .showError();            }        });    }    private void saveList(List<Hero> aovHeroes) {        String jsonString = gson.toJson(AovHeroes);        sharedPreferences                .edit()                .putString(Constants.KEY_HERO_LIST, jsonString)                .apply();    }    private List<Hero> getDataFromCache() {        String jsonHero = sharedPreferences.getString(Constants.KEY_HERO_LIST, null) ;        if(jsonHero ==null)            return null;        else {            Type listType = new TypeToken<List<Hero>>() {            }.getType();            return gson.fromJson(jsonHero, listType);        }    }    public void onItemClick(Hero hero){        view.navigateToDetails(hero);    }    private void onButtonAClick(){    }    public void onButtonBclick(){    }}