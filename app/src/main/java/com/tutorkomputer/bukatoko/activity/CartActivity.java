package com.tutorkomputer.bukatoko.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tutorkomputer.bukatoko.App;
import com.tutorkomputer.bukatoko.R;
import com.tutorkomputer.bukatoko.adapter.CartAdapter;
import com.tutorkomputer.bukatoko.data.Constant;
import com.tutorkomputer.bukatoko.data.model.Cart;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    public static TextView txtPriceTotal;
    Button btnCheckout;
    RecyclerView  recyclerView;
   public static List<Cart> cartList = new ArrayList<>();
   public static CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartList.clear();

        cartList.addAll(App.sqLiteHelper.myCart());

        txtPriceTotal  =findViewById(R.id.txtPriceTotal);
        btnCheckout    =findViewById(R.id.btnCheckout);
        recyclerView   =findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new CartAdapter(this,cartList);
        recyclerView.setAdapter(adapter);

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this,OngkirActivity.class));
            }
        });

        getSupportActionBar().setTitle("Keranjang");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onSupportNavigateUp() {
        Intent intent =new Intent(CartActivity.this, MainActivity.class);
        startActivity(intent);
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Constant.SHOP_NOW){
            startActivity(new Intent(this,OngkirActivity.class));
            Constant.SHOP_NOW=false;
        }
    }
}
