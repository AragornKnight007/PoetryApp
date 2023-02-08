package com.example.poetryapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.poetryapp.Api.ApiClient;
import com.example.poetryapp.Api.ApiInterface;
import com.example.poetryapp.R;
import com.example.poetryapp.Response.DeleteResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddPoetry extends AppCompatActivity {

Toolbar toolbar;
EditText poetName , poetryData;
AppCompatButton submitBtn;
ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_poetry);
        initialization();
        setuptoolbar();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String poetryDataString = poetryData.getText().toString();
                String poetNameStirng = poetName.getText().toString();

                if (poetryDataString.equals("")){
                    poetryData.setError("Field Is Empty");

                }
                else{
                    if(poetNameStirng.equals("")){
                        poetName.setError("Field Is Empty");


                    }else{
                        callapi(poetryDataString,poetNameStirng);


                        
                    }



                }




            }
        });





    }

    private void initialization(){
        toolbar = findViewById(R.id.add_poetry_toolbar);
        poetName =findViewById(R.id.add_poet_name_edittext);
        poetryData = findViewById(R.id.add_poetry_data_edittext);
        submitBtn = findViewById(R.id.submit_data_button);
        Retrofit retrofit = ApiClient.getclient();

        apiInterface = retrofit.create(ApiInterface.class);




    }

    private void setuptoolbar(){

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();



            }
        });





    }

    private void callapi(String poetryData, String poetName){

        apiInterface.addpoetry(poetryData,poetName).enqueue(new Callback<DeleteResponse>() {
            @Override
            public void onResponse(Call<DeleteResponse> call, Response<DeleteResponse> response) {

                try{
                    if(response.body().getStatus().equals("1")){

                        Toast.makeText(AddPoetry.this, "Added Successfully", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Toast.makeText(AddPoetry.this, "Not Added ", Toast.LENGTH_SHORT).show();
                    }




                }
                catch (Exception e){

                    Log.e("Exp",e.getLocalizedMessage());

                }

            }

            @Override
            public void onFailure(Call<DeleteResponse> call, Throwable t) {

                Log.e("Failure",t.getLocalizedMessage());

            }
        });

    }




}