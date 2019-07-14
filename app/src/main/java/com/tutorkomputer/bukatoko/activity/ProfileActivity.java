package com.tutorkomputer.bukatoko.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tutorkomputer.bukatoko.App;
import com.tutorkomputer.bukatoko.R;
import com.tutorkomputer.bukatoko.data.model.User;
import com.tutorkomputer.bukatoko.data.retrofit.Api;
import com.tutorkomputer.bukatoko.data.retrofit.ApiInterface;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    LinearLayout linearEdit,linearData;
    TextView txtName,txtEmail,txtPass;
    Button btnSave;
    FloatingActionButton fab;
    EditText edtName,edtEmail,edtCurr,edtPass,edtConf;
    boolean edit =false;
    HashMap<String,String> sessPref;
    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findId();

        //get session user
        sessPref= App.prefsManager.getUserDetail();
        user_id =sessPref.get(App.prefsManager.SESS_ID);

        txtName.setText(sessPref.get(App.prefsManager.SESS_NAME));
        txtEmail.setText(sessPref.get(App.prefsManager.SESS_EMAIL));
        txtPass.setText(sessPref.get(App.prefsManager.SESS_PASS));

        edtName.setText(sessPref.get(App.prefsManager.SESS_NAME));
        edtEmail.setText(sessPref.get(App.prefsManager.SESS_EMAIL));


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edtCurr.getText().toString().equals(sessPref.get(App.prefsManager.SESS_PASS))){
                    Toast.makeText(getApplicationContext(),"Password Saat Ini Salah",Toast.LENGTH_SHORT).show();
                }else if(!edtPass.getText().toString().equals(edtConf.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Konfrimasi Password Baru Dengan Benar",Toast.LENGTH_SHORT).show();
                }else if(edtPass.length() <= 0) {
                    Toast.makeText(getApplicationContext(), "Konfrimasi Password Baru Dengan Benar", Toast.LENGTH_SHORT).show();
                }else{
                    setUpdate();
                }
            }
        });


        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                if (edit){
                    editTrue();
                }else{
                    editFalse();
                }
            }
        });
    }

    private void setUpdate(){
//        Toast.makeText(getApplicationContext(),"Berhasil",Toast.LENGTH_SHORT).show();
        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
        Call<User> call =apiInterface.updateUser(sessPref.get(App.prefsManager.SESS_ID),
                                                 edtName.getText().toString(),
                                                 edtEmail.getText().toString(),
                                                 edtPass.getText().toString());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    App.prefsManager.logoutUser();
                    App.prefsManager.createLoginSession(user_id,
                                                        edtName.getText().toString(),
                                                        edtEmail.getText().toString(),
                                                        edtPass.getText().toString());
                    editTrue();
                }else {
                    Toast.makeText(getApplicationContext(),response.message(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }


    private void findId(){
        linearEdit=findViewById(R.id.linearEdit);
        linearData=findViewById(R.id.linearData);

        txtName=findViewById(R.id.txtName);
        txtEmail=findViewById(R.id.txtEmail);
        txtPass=findViewById(R.id.txtPass);


        edtName =findViewById(R.id.edtName);
        edtEmail =findViewById(R.id.edtEmail);
        edtCurr =findViewById(R.id.edtCurr);
        edtPass =findViewById(R.id.edtPass);
        edtConf =findViewById(R.id.edtConf);

        btnSave =findViewById(R.id.btnSave);
    }

    private void editTrue(){
        linearData.setVisibility(View.VISIBLE);
        linearEdit.setVisibility(View.GONE);
        fab.setImageDrawable(ContextCompat.getDrawable(ProfileActivity.this,R.drawable.ic_fab_edit));
        edit =false;

    }

    private void editFalse(){
        linearData.setVisibility(View.GONE);
        linearEdit.setVisibility(View.VISIBLE);
        fab.setImageDrawable(ContextCompat.getDrawable(ProfileActivity.this,R.drawable.ic_fab_close));
        edit =true;

    }
}
