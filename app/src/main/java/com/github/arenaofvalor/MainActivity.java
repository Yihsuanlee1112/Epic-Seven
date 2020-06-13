package com.github.arenaofvalor;import androidx.appcompat.app.AppCompatActivity;import androidx.recyclerview.widget.LinearLayoutManager;import androidx.recyclerview.widget.RecyclerView;import android.content.SharedPreferences;import android.os.Bundle;import android.util.Log;import android.widget.Toast;import com.google.gson.Gson;import com.google.gson.GsonBuilder;import java.util.ArrayList;import java.util.List;import retrofit2.Call;import retrofit2.Callback;import retrofit2.Response;import retrofit2.Retrofit;import retrofit2.converter.gson.GsonConverterFactory;public class MainActivity extends AppCompatActivity{    private RecyclerView recyclerView ;    private AovAdapter mAdapter;    private RecyclerView.LayoutManager layoutManager;    private List<Hero> AovHeroes = new ArrayList<>();    private SharedPreferences sharedPreferences;    private Gson gson;    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_main);        APIcall();    }    private void showList(List<Hero> HeroList){        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);        // use this setting to        // improve performance if you know that changes        // in content do not change the layout size        // of the RecyclerView        recyclerView.setHasFixedSize(true);        // use a linear layout manager        layoutManager = new LinearLayoutManager(this);        recyclerView.setLayoutManager(layoutManager);        // define an adapter        mAdapter = new AovAdapter(HeroList);        recyclerView.setAdapter(mAdapter);    }    private void APIcall(){        Gson gson = new GsonBuilder()                .setLenient()                .create();        Retrofit retrofit = new Retrofit.Builder()                .baseUrl("https://raw.githubusercontent.com/Yihsuanlee1112/arenaofvalor/master/app/Files/")                .addConverterFactory(GsonConverterFactory.create(gson))                .build();        ArenaOfValorAPI arenaOfValorAPI = retrofit.create(ArenaOfValorAPI.class);        Log.d("REBECCA", "BEFORE CALLBACK");        Call<ArenaOfValorResponse> call = arenaOfValorAPI.getHeroResponse();        call.enqueue(new Callback<ArenaOfValorResponse>() {            @Override            public void onResponse(Call<ArenaOfValorResponse> call, Response<ArenaOfValorResponse> response) {                Log.d("REBECCA", "INSIDE CALLBACK");                if(response.isSuccessful()&& response.body()!= null){                    AovHeroes = response.body().getResults();                    Toast.makeText(getApplicationContext(), "API Success", Toast.LENGTH_SHORT).show();                    showList(AovHeroes);                    /*for(Hero H: AovHeroes){                        System.out.println(H.getId()+" "+" "+H.getType()+" "+" "+H.getName());                    }System.out.println(AovHeroes.size());                     */                }                else{                    System.out.println("Fail");                }            }            @Override            public void onFailure(Call<ArenaOfValorResponse> call, Throwable t) {                showError();                System.out.println("Fail");            }        });        Log.d("REBECCA", "AFTER CALLBACK");    }    private void showError() {        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();    }}