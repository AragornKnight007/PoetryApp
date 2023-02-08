package com.example.poetryapp.Activities;

import androidx.annotation.NonNull;
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

public class UpdatePoetry extends AppCompatActivity {

    Toolbar toolbar;
    EditText poetryData;
    AppCompatButton updatebtn;



    int poetryId;
    String poetryDataString;
    ApiInterface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_poetry);

        initialization();
        setuptoolbar();

        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String p_data = poetryData.getText().toString();

                if(p_data.equals("")){
                    poetryData.setError("Field Is Empty");



                }
                else{

                    callapi(p_data , poetryId+"");








                }




            }
        });




    }


    private void initialization(){
        toolbar = findViewById(R.id.update_poetry_toolbar);
        poetryData = findViewById(R.id.update_poetry_data_edittext);
        updatebtn = findViewById(R.id.update_data_button);

       poetryId = getIntent().getIntExtra("p_id",0);
       poetryDataString = getIntent().getStringExtra("p_data");

        poetryData.setText(poetryDataString);

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

    private void callapi(String pData, String pid){

        apiInterface.updatepoetry(pData,pid).enqueue(new Callback<DeleteResponse>() {
            @Override
            public void onResponse( Call<DeleteResponse> call,  Response<DeleteResponse> response) {

                try{
                    if(response.body().getStatus().equals("1")){

                        Toast.makeText(UpdatePoetry.this, "Updated Successfully", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Toast.makeText(UpdatePoetry.this, "Not Updated ", Toast.LENGTH_SHORT).show();
                    }




                }
                catch (Exception e){

                    Log.e("Exp",e.getLocalizedMessage());

                }




            }

            @Override
            public void onFailure(Call<DeleteResponse> call, Throwable t) {

                Log.e("Update Fail",t.getLocalizedMessage());

            }
        });






    }





}






