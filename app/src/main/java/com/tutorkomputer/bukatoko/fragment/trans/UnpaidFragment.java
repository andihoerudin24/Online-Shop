package com.tutorkomputer.bukatoko.fragment.trans;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tutorkomputer.bukatoko.App;
import com.tutorkomputer.bukatoko.R;
import com.tutorkomputer.bukatoko.adapter.TransUnpaidAdapter;
import com.tutorkomputer.bukatoko.data.database.PrefsManager;
import com.tutorkomputer.bukatoko.data.model.transaction.TransUser;
import com.tutorkomputer.bukatoko.data.retrofit.Api;
import com.tutorkomputer.bukatoko.data.retrofit.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class UnpaidFragment extends Fragment {

    RecyclerView recyclerView;
    TextView   textView;


    public UnpaidFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_unpaid, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        textView = view.findViewById(R.id.textView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        getTrans();
        return  view;
    }


    private void getTrans()
    {
        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
        Call<TransUser> call      =apiInterface.getTransUnpaid(
                App.prefsManager.getUserDetail().get(PrefsManager.SESS_ID)
        );

        call.enqueue(new Callback<TransUser>() {
            @Override
            public void onResponse(Call<TransUser> call, Response<TransUser> response) {
                 if (response.isSuccessful()){
                       TransUser transUser =response.body();
                       List<TransUser.Data> data = transUser.getData();
                       recyclerView.setAdapter(new TransUnpaidAdapter(getContext(),data));


                 }else{
                     textView.setVisibility(View.VISIBLE);
                 }
            }

            @Override
            public void onFailure(Call<TransUser> call, Throwable t) {

            }
        });
    }

}
