package com.tutorkomputer.bukatoko.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tutorkomputer.bukatoko.R;
import com.tutorkomputer.bukatoko.data.model.transaction.TransDetail;

import java.util.List;

public class TransDetailAdapter  extends RecyclerView.Adapter<TransDetailAdapter.ViewHolder> {

    private List<TransDetail.Data.DetailTransaction> transactions;
    Context context;


    public TransDetailAdapter(Context context,List<TransDetail.Data.DetailTransaction>transactions){

        this.context =context;
        this.transactions =transactions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_trans_detal, viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        final TransDetail.Data.DetailTransaction detailTransaction = transactions.get(i);

        viewHolder.txtTitle.setText(detailTransaction.getProduct());
        viewHolder.txtDescription.setText(
                "Harga: "  + detailTransaction.getPrice() +  ", Jumlah: " + detailTransaction.getQty()
        );
        viewHolder.txtTotal.setText("Total Bayar" + detailTransaction.getTotal());


    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle,txtDescription,txtTotal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle=itemView.findViewById(R.id.txtTitle);
            txtDescription=itemView.findViewById(R.id.txtDescription);
            txtTotal=itemView.findViewById(R.id.txtTotal);



        }
    }
}
