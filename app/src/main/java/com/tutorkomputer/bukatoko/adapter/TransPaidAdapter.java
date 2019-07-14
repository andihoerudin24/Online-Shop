package com.tutorkomputer.bukatoko.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.card.MaterialCardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.tutorkomputer.bukatoko.R;
import com.tutorkomputer.bukatoko.activity.TransDetActivity;
import com.tutorkomputer.bukatoko.activity.WaybillActivity;
import com.tutorkomputer.bukatoko.data.model.transaction.TransUser;

import java.util.List;

public class TransPaidAdapter extends RecyclerView.Adapter<TransPaidAdapter.ViewHolder> {

    private List<TransUser.Data> transPaid;
    Context context;

    public TransPaidAdapter(Context context, List<TransUser.Data> transaction)
    {
        this.context =context;
        this.transPaid=transaction;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_trans,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        final TransUser.Data data = transPaid.get(i);
        if (data.getStatus_transaction().equals("sent")){
            viewHolder.btnAction.setText("Lacak Pengiriman");
            viewHolder.btnAction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, WaybillActivity.class);
                    intent.putExtra("WAYBILL",data.getResi_Code());
                    context.startActivity(intent);
//                    Intent intent=  new Intent(context, UploadActivity.class);
//                    intent.putExtra("TRANSACTION_CODE",data.getTransaction_code());
//                    context.startActivity(intent);
                }
            });
        }else {

            viewHolder.btnAction.setVisibility(View.GONE);
        }


        viewHolder.txtTitle.setText(data.getTransaction_code());
        viewHolder.txtStatus.setText(data.getStatus_transaction());

        viewHolder.carView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, TransDetActivity.class);
                intent.putExtra("TRANSACTION_CODE",data.getTransaction_code());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return transPaid.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button btnAction;
        TextView txtTitle,txtStatus;
        MaterialCardView carView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle=itemView.findViewById(R.id.txtTitle);
            txtStatus=itemView.findViewById(R.id.txtStatus);
            btnAction=itemView.findViewById(R.id.btnAction);
            carView=itemView.findViewById(R.id.carView);


        }
    }
}

