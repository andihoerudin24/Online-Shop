package com.tutorkomputer.bukatoko.utils;

import android.content.Context;
import android.view.Menu;
import android.widget.Toast;

import com.tutorkomputer.bukatoko.App;
import com.tutorkomputer.bukatoko.R;
import com.tutorkomputer.bukatoko.data.database.PrefsManager;
import com.tutorkomputer.bukatoko.data.model.User;
import com.tutorkomputer.bukatoko.data.retrofit.Api;
import com.tutorkomputer.bukatoko.data.retrofit.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthState {

    public static void isLoggedIn(Menu menu){
        menu.findItem(R.id.nav_notiv).setVisible(true);
        menu.findItem(R.id.nav_trans).setVisible(true);
        menu.findItem(R.id.nav_profile).setVisible(true);
        menu.findItem(R.id.nav_logout).setVisible(true);


        menu.findItem(R.id.nav_login).setVisible(false);
    }


    public static void isLoggedOut(Menu menu){
        menu.findItem(R.id.nav_notiv).setVisible(false);
        menu.findItem(R.id.nav_trans).setVisible(false);
        menu.findItem(R.id.nav_profile).setVisible(false);
        menu.findItem(R.id.nav_logout).setVisible(false);


        menu.findItem(R.id.nav_login).setVisible(true);
    }


    public static void udpateToken(final Context context,String token){

        App.sessPref = App.prefsManager.getUserDetail();

        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
        Call<User> call =apiInterface.updateToken(App.sessPref.get(PrefsManager.SESS_ID),token);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    Toast.makeText(context, "Token Firebase Berhasil Di Perbarui",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context,response.message(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

    }
}
