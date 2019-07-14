package com.tutorkomputer.bukatoko.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tutorkomputer.bukatoko.R;
import com.tutorkomputer.bukatoko.data.model.rajaongkir.Waybill;

import java.util.List;

public class WaybillAdapter extends RecyclerView.Adapter<WaybillAdapter.ViewHolder> {

    private List<Waybill.RajaOngkir.Result.Manifest> manifests;
    Context context;

    public WaybillAdapter(Context context, List<Waybill.RajaOngkir.Result.Manifest> manifests)
    {
        this.context =context;
        this.manifests= manifests;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_waybill,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Waybill.RajaOngkir.Result.Manifest manifest = manifests.get(i);

        viewHolder.txtDescription.setText(manifest.getManifest_description());
        viewHolder.txtDatetime.setText(manifest.getManifest_date() + " " + manifest.getManifest_time());



    }

    @Override
    public int getItemCount() {
        return manifests.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtDescription,txtDatetime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDescription=itemView.findViewById(R.id.txtDescription);
            txtDatetime=itemView.findViewById(R.id.txtDatetime);
        }
    }
}
