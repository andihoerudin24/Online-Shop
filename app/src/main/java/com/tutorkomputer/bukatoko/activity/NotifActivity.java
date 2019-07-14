package com.tutorkomputer.bukatoko.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tutorkomputer.bukatoko.App;
import com.tutorkomputer.bukatoko.R;
import com.tutorkomputer.bukatoko.adapter.NotifAdapter;
import com.tutorkomputer.bukatoko.data.database.PrefsManager;
import com.tutorkomputer.bukatoko.data.model.Notification;
import com.tutorkomputer.bukatoko.data.retrofit.Api;
import com.tutorkomputer.bukatoko.data.retrofit.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotifActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif);
        recyclerView=findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        getNotif();
        getSupportActionBar().setTitle("Notifikasi");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }


    private void getNotif(){
        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
        Call<Notification> call =apiInterface.myNotification(
                App.prefsManager.getUserDetail().get(PrefsManager.SESS_ID)
        );

        call.enqueue(new Callback<Notification>() {
            @Override
            public void onResponse(Call<Notification> call, Response<Notification> response) {
                Notification notification = response.body();
                List<Notification.Data> data = notification.getData();
                recyclerView.setAdapter(new NotifAdapter(NotifActivity.this,data));
            }

            @Override
            public void onFailure(Call<Notification> call, Throwable t) {

            }
        });
    }

}
