package com.tutorkomputer.bukatoko.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tutorkomputer.bukatoko.R;
import com.tutorkomputer.bukatoko.adapter.WaybillAdapter;
import com.tutorkomputer.bukatoko.data.Constant;
import com.tutorkomputer.bukatoko.data.model.rajaongkir.Waybill;
import com.tutorkomputer.bukatoko.data.retrofit.Api;
import com.tutorkomputer.bukatoko.data.retrofit.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WaybillActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ProgressBar progressBar;
    TextView txtReciver, txtStatus;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waybill);


        bundle = getIntent().getExtras();

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        txtReciver = findViewById(R.id.txtReciver);
        txtStatus = findViewById(R.id.txtStatus);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        getSupportActionBar().setTitle("Riwayat Pengiriman");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getWaybill("032460068254419");

    }


    @Override
    public boolean onSupportNavigateUp() {
         finish();
        return super.onSupportNavigateUp();
    }

    private void getWaybill(String NO_RESI){

        ApiInterface apiInterface = Api.getUrlraja(Constant.BASE_URL_RAJAONGKIR_PRO).create(ApiInterface.class);
        Call<Waybill> call      =apiInterface.getWaybill(
                Constant.KEY_RAJAONGKIR,NO_RESI,"jne"
        );

        call.enqueue(new Callback<Waybill>() {
            @Override
            public void onResponse(Call<Waybill> call, Response<Waybill> response) {

                Log.e("_Respon",response.toString());
                Waybill.RajaOngkir.Result result = response.body().getRajaOngkir().getResult();

                Waybill.RajaOngkir.Result.DeliveryStatus status = result.getDeliveryStatus();

                txtReciver.setText(status.getPod_receiver());
                txtStatus.setText(status.getStatus());

                List<Waybill.RajaOngkir.Result.Manifest> manifests = result.getManifest();
                recyclerView.setAdapter(new WaybillAdapter(WaybillActivity.this,manifests));

                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<Waybill> call, Throwable t) {
                Log.e("_response",t.toString());
            }
        });

    }
}
