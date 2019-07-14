package com.tutorkomputer.bukatoko.activity.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import com.tutorkomputer.bukatoko.R;
import com.tutorkomputer.bukatoko.activity.CartActivity;
import com.tutorkomputer.bukatoko.data.Constant;

public class CartDialog {


    public void showCartDialog(final Context context){
        AlertDialog.Builder builder =new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.dialog_cart,null);
        builder.setView(view);

        view.findViewById(R.id.btnCart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, CartActivity.class));
                ((Activity) context).finish();
            }
        });
        view.findViewById(R.id.btnPay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constant.SHOP_NOW=true;
                context.startActivity(new Intent(context, CartActivity.class));
                ((Activity) context).finish();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
