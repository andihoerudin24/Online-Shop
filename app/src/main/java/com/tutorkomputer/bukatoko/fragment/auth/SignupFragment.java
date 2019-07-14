package com.tutorkomputer.bukatoko.fragment.auth;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tutorkomputer.bukatoko.R;
import com.tutorkomputer.bukatoko.activity.SignupActivity;
import com.tutorkomputer.bukatoko.data.model.User;
import com.tutorkomputer.bukatoko.data.retrofit.Api;
import com.tutorkomputer.bukatoko.data.retrofit.ApiInterface;
import com.tutorkomputer.bukatoko.utils.Converter;
import com.xwray.passwordview.PasswordView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment {
    EditText edtEmail,edtName;
    PasswordView edtPass,edtConfrim;
    Button btnSignup;

    public SignupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view= inflater.inflate(R.layout.fragment_signup, container, false);

        edtName     =view.findViewById(R.id.edtName);
        edtEmail    =view.findViewById(R.id.edtEmail);
        edtPass     =view.findViewById(R.id.edtPass);
        edtConfrim  =view.findViewById(R.id.edtConfrim);
        btnSignup   =view.findViewById(R.id.btnSignup);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtName.length() > 0 && edtEmail.length()>0 && edtPass.length()>0 && edtConfrim.length()>0){

                       if (!edtPass.getText().toString().equals(edtConfrim.getText().toString())){
                           Toast.makeText(getContext(),"Konfrimasi Password Dengan Benar",Toast.LENGTH_SHORT).show();
                       }else if(!Converter.isValidEmailId(edtEmail.getText().toString())){
                           Toast.makeText(getContext(),"Isi Format Email Dengan Benar",Toast.LENGTH_SHORT).show();
                       }else{
                           Auth();
                       }
                }else{
                    Toast.makeText(getContext(),"Isi Data Dengan Benar",Toast.LENGTH_SHORT).show();
                }
            }
        });

      return view;
    }

    private void Auth(){
        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
        Call<User> call =apiInterface.authRegister(edtName.getText().toString(),
                                                   edtEmail.getText().toString(),
                                                   edtPass.getText().toString());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(getContext(),"Berhasil Menjadi Pengguna Baru",Toast.LENGTH_SHORT).show();
                SignupActivity.tabLayout.getTabAt(1).select();

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

}
