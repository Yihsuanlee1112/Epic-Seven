package com.github.arenaofvalor.presentation.view;import androidx.appcompat.app.AppCompatActivity;import androidx.recyclerview.widget.LinearLayoutManager;import androidx.recyclerview.widget.RecyclerView;import android.content.Context;import android.content.SharedPreferences;import android.os.Bundle;import android.widget.Toast;import com.github.arenaofvalor.Constants;import com.github.arenaofvalor.R;import com.github.arenaofvalor.data.ArenaOfValorAPI;import com.github.arenaofvalor.presentation.controller.MainController;import com.github.arenaofvalor.presentation.model.ArenaOfValorResponse;import com.github.arenaofvalor.presentation.model.Hero;import com.google.gson.Gson;import com.google.gson.GsonBuilder;import com.google.gson.reflect.TypeToken;import java.lang.reflect.Type;import java.util.ArrayList;import java.util.List;import retrofit2.Call;import retrofit2.Callback;import retrofit2.Response;import retrofit2.Retrofit;import retrofit2.converter.gson.GsonConverterFactory;public class MainActivity extends AppCompatActivity{    private RecyclerView recyclerView ;    private AovAdapter mAdapter;    private RecyclerView.LayoutManager layoutManager;    private MainController controller;    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_main);        controller = new MainController(                this,                new GsonBuilder().setLenient().create(),                getSharedPreferences("application_esiea", Context.MODE_PRIVATE)        );        controller.onStart();    }    public void showList(List<Hero> HeroList){        recyclerView = findViewById(R.id.recyclerView);        // use this setting to        // improve performance if you know that changes        // in content do not change the layout size        // of the RecyclerView        recyclerView.setHasFixedSize(true);        // use a linear layout manager        layoutManager = new LinearLayoutManager(this);        recyclerView.setLayoutManager(layoutManager);        // define an adapter        mAdapter = new AovAdapter(HeroList);        recyclerView.setAdapter(mAdapter);    }    public void showError() {        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();    }}