package com.tutorkomputer.bukatoko.activity.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.tutorkomputer.bukatoko.App;
import com.tutorkomputer.bukatoko.R;
import com.tutorkomputer.bukatoko.activity.CartActivity;
import com.tutorkomputer.bukatoko.activity.SignupActivity;
import com.tutorkomputer.bukatoko.data.database.PrefsManager;
import com.tutorkomputer.bukatoko.data.model.User;
import com.tutorkomputer.bukatoko.data.retrofit.Api;
import com.tutorkomputer.bukatoko.data.retrofit.ApiInterface;
import com.tutorkomputer.bukatoko.utils.AuthState;
import com.tutorkomputer.bukatoko.utils.Converter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginDialog {

    public void showLoginDialog(final Context context, final Menu menu){
        AlertDialog.Builder builder =new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.dialog_login,null);
        builder.setView(view);

        final EditText edtEmail = view.findViewById(R.id.edtEmail);
        final EditText edtPass = view.findViewById(R.id.edtPass);

        final AlertDialog alertDialog = builder.create();

        view.findViewById(R.id.btnlogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (edtEmail.length() > 0 && edtPass.length() > 0){

                   String email=edtEmail.getText().toString();
                   final String pass =edtPass.getText().toString();

                   if (Converter.isValidEmailId(email)){
                       ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
                       Call<User> call =apiInterface.authLogin(email,pass);
                       call.enqueue(new Callback<User>() {
                           @Override
                           public void onResponse(Call<User> call, Response<User> response) {
                               if (response.isSuccessful()){

                                   User.Data data = response.body().getData();
                                   AuthState.isLoggedIn(menu);
                                   App.prefsManager.createLoginSession(String.valueOf(data.getId()),
                                                                                       data.getName(),
                                                                                       data.getEmail(),pass);

                                   App.sessPref = App.prefsManager.getUserDetail();
                                   AuthState.udpateToken(context,App.sessPref.get(PrefsManager.SESS_TOKEN));

                                alertDialog.dismiss();

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
            }
        });

        view.findViewById(R.id.txtRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, SignupActivity.class));
                alertDialog.dismiss();
            }
        });


        alertDialog.show();
    }
}
