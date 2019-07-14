package com.tutorkomputer.bukatoko.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.tutorkomputer.bukatoko.R;
import com.tutorkomputer.bukatoko.adapter.CityAdapter;
import com.tutorkomputer.bukatoko.data.Constant;
import com.tutorkomputer.bukatoko.data.model.rajaongkir.City;
import com.tutorkomputer.bukatoko.data.retrofit.Api;
import com.tutorkomputer.bukatoko.data.retrofit.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CityActivity extends AppCompatActivity {
    ProgressBar progressBar;
    RecyclerView recyclerView;
    ImageView imgCancel;
    EditText editText;

    CityAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        progressBar=findViewById(R.id.progressBar);
        recyclerView=findViewById(R.id.recyclerView);
        imgCancel=findViewById(R.id.imgCancel);
        editText=findViewById(R.id.editText);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getCity();
    }


    private  void  getCity(){
        ApiInterface apiInterface = Api.getUrlraja(Constant.BASE_URL_RAJAONGKIR_STARTER).create(ApiInterface.class);
        Call<City> call = apiInterface.getCities(Constant.KEY_RAJAONGKIR);
        call.enqueue(new Callback<City>() {
            @Override
            public void onResponse(Call<City> call, Response<City> response) {
                City.Rajaongkir rajaongkir = response.body().getRajaongkir();
                List<City.Rajaongkir.Results> results = rajaongkir.getResults();
                adapter = new CityAdapter(CityActivity.this,results);
                recyclerView.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);

                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        String search =s.toString();

                        adapter.getFilter().filter(search);
                    }
                });
            }

            @Override
            public void onFailure(Call<City> call, Throwable t) {

            }
        });
    }
}
