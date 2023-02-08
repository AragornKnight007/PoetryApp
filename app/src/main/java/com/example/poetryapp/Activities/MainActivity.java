package com.example.poetryapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.poetryapp.Adapter.PoetryAdapter;
import com.example.poetryapp.Api.ApiClient;
import com.example.poetryapp.Api.ApiInterface;
import com.example.poetryapp.Models.PoetryModel;
import com.example.poetryapp.R;
import com.example.poetryapp.Response.GetPoetryResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    PoetryAdapter poetryAdapter;

    Toolbar toolbar;

    ApiInterface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialization();
        setSupportActionBar(toolbar);
        getdata();


    }

    private  void initialization(){

        recyclerView = findViewById(R.id.poetry_recyclerView);
        Retrofit retrofit = ApiClient.getclient();
        apiInterface = retrofit.create(ApiInterface.class);
        toolbar = findViewById(R.id.main_toolbar);

    }

    private  void setadapter(List<PoetryModel> poetryModels){


        poetryAdapter = new PoetryAdapter(this,poetryModels);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(poetryAdapter);

    }

    private void getdata(){

        apiInterface.getpoetry().enqueue(new Callback<GetPoetryResponse>() {
            @Override
            public void onResponse(Call<GetPoetryResponse> call, Response<GetPoetryResponse> response) {
                try{
                    if(response!=null){
                        if(response.body().getStatus().equals("1")){

                            setadapter(response.body().getData());



                        }
                        else{
                            Toast.makeText(MainActivity.this,response.body().getMessage(),Toast.LENGTH_SHORT).show();

                        }
                    }


                }

                catch (Exception e) {
                    Log.e("exp", e.getLocalizedMessage());
                }


            }

            @Override
            public void onFailure(Call<GetPoetryResponse> call, Throwable t) {

                Log.e("Failure",t.getLocalizedMessage());

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){

            case R.id.add_property:

                Intent intent = new Intent(MainActivity.this , AddPoetry.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);


                
                

        }


    }
}