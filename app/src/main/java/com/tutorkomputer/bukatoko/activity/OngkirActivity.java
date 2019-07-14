package com.tutorkomputer.bukatoko.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tutorkomputer.bukatoko.App;
import com.tutorkomputer.bukatoko.R;
import com.tutorkomputer.bukatoko.data.Constant;
import com.tutorkomputer.bukatoko.data.database.PrefsManager;
import com.tutorkomputer.bukatoko.data.model.Detail;
import com.tutorkomputer.bukatoko.data.model.rajaongkir.Cost;
import com.tutorkomputer.bukatoko.data.model.transaction.Transpost;
import com.tutorkomputer.bukatoko.data.retrofit.Api;
import com.tutorkomputer.bukatoko.data.retrofit.ApiInterface;
import com.tutorkomputer.bukatoko.utils.Converter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OngkirActivity extends AppCompatActivity {

    LinearLayout linearSave,linearTrans;
    Button btnSave,btnTrans;
    EditText edtDestination,edtAddress;
    Spinner spnService;
    TextView txtOngkir,txtCancel,txtDismiss;
    List<Integer> listValue;
    List<String> listService;
    ProgressBar progressBar;

    private int ongkirvalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ongkir);

        linearSave=findViewById(R.id.linearSave);
        linearTrans=findViewById(R.id.linearTrans);
        btnSave   =findViewById(R.id.btnSave);
        btnTrans   =findViewById(R.id.btnTrans);
        edtDestination =findViewById(R.id.edtDestination);
        spnService =findViewById(R.id.spnService);
        txtOngkir =findViewById(R.id.txtOngkir);
        txtCancel =findViewById(R.id.txtCancel);
        txtDismiss =findViewById(R.id.txtDismiss);
        edtAddress =findViewById(R.id.edtAddress);
        progressBar =findViewById(R.id.progressBar);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtDestination.length() > 0 || edtAddress.length() > 0){
                    linearSave.setVisibility(View.GONE);
                    progressBar.setVisibility(View.VISIBLE);

                    ArrayList<Transpost.Detail> arrayList = new ArrayList<>();
                    for (int i=0; i< CartActivity.cartList.size(); i++){
                        Transpost.Detail detail = new Transpost().new Detail();
                        detail.setProduct_id(CartActivity.cartList.get(i).getProduct_id());
                        detail.setQty(CartActivity.cartList.get(i).getQty());
                        detail.setPrice(CartActivity.cartList.get(i).getPrice());
                        detail.setTotal(CartActivity.cartList.get(i).getTotal());

                        arrayList.add(detail);
                        Log.e("_detail", String.valueOf(CartActivity.cartList.get(i).getProduct_id()));
                    }

                    Transpost transpost = new Transpost();
                    transpost.setUser_id(Integer.parseInt(App.prefsManager.getUserDetail().get(PrefsManager.SESS_ID)));
                    transpost.setDestination(edtDestination.getText().toString() + " - " + edtAddress.getText().toString());
                    transpost.setOngkir(ongkirvalue);
                    transpost.setGrandtotal(CartActivity.adapter.getTotal() + ongkirvalue);
                    transpost.setDetailList(arrayList);

                    postTransaction(transpost);

                }else{
                    Toast.makeText(getApplicationContext(),"Lengkapi Alamat Pengiriman",Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OngkirActivity.this,TransActivity.class));
                finish();
            }
        });

        txtCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        txtDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        edtDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OngkirActivity.this,CityActivity.class));
            }
        });

        listService = new ArrayList<>();
        listValue   = new ArrayList<>();


        getSupportActionBar().setTitle("Cek Ongkir");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!Constant.DESTINATION.equals("")){
            edtDestination.setText(Constant.DESTINATION_NAME);
            getService();
        }
    }

    private void getService(){
        listValue.clear();
        listService.clear();
        ApiInterface apiInterface = Api.getUrlraja(Constant.BASE_URL_RAJAONGKIR_STARTER).create(ApiInterface.class);
        Call<Cost> call = apiInterface.getCost(Constant.KEY_RAJAONGKIR,"444",Constant.DESTINATION,
                "1000","jne");
        call.enqueue(new Callback<Cost>() {
            @Override
            public void onResponse(Call<Cost> call, Response<Cost> response) {
                Cost.Rajaongkir ongkir =response.body().getRajaongkir();

                List<Cost.Rajaongkir.Results> results = ongkir.getResults();

                for (int i=0; i< results.size(); i++){

                    Log.e("_lazday",results.get(i).getCode());

                    List<Cost.Rajaongkir.Results.Costs> costs = results.get(i).getCosts();

                     for (int j=0; j < costs.size(); j++){
                         Log.e("_lazdayservice",costs.get(j).getDescription());

                         List<Cost.Rajaongkir.Results.Costs.Data> data = costs.get(j).getCost();

                         for (int k=0; k < data.size(); k++){
                             Log.e("_lazdayserviceValue",String.valueOf(data.get(k).getValue()));

                             listService.add("Rp " + Converter.rupiah(data.get(k).getValue()) + "(JNE " + costs.get(j).getService() + ")");

                             listValue.add(data.get(k).getValue());
                         }
                     }

                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(OngkirActivity.this,android.R.layout.simple_list_item_1,listService);
                spnService.setAdapter(arrayAdapter);

                spnService.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        txtOngkir.setText("Rp " + Converter.rupiah(listValue.get(position)));
                        ongkirvalue = listValue.get(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

            }

            @Override
            public void onFailure(Call<Cost> call, Throwable t) {

            }
        });
    }

    private void postTransaction(Transpost transpost){
        ApiInterface apiInterface= Api.getUrl().create(ApiInterface.class);
        Call<Transpost> call = apiInterface.insertTrans(transpost);
        call.enqueue(new Callback<Transpost>() {
            @Override
            public void onResponse(Call<Transpost> call, Response<Transpost> response) {
                if(response.isSuccessful()){
                    Log.e("_respntransa",response.body().toString());
                    linearTrans.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);

                    Toast.makeText(getApplicationContext(),"Transaksi Berhasil Di Buat",Toast.LENGTH_SHORT).show();
                    App.sqLiteHelper.clearTable();
                }
            }

            @Override
            public void onFailure(Call<Transpost> call, Throwable t) {

            }
        });
    }
}
