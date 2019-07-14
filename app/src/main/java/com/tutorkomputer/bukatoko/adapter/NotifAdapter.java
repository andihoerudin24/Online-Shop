package com.tutorkomputer.bukatoko.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tutorkomputer.bukatoko.R;
import com.tutorkomputer.bukatoko.data.model.Notification;

import java.util.List;

public class NotifAdapter extends RecyclerView.Adapter<NotifAdapter.ViewHolder> {
    private List<Notification.Data> notification;
    Context context;


    public NotifAdapter(Context context, List<Notification.Data> notification){
        this.context=context;
        this.notification=notification;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_notif,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        final Notification.Data data =notification.get(i);

        viewHolder.txtMsg.setText(data.getMessage());

    }

    @Override
    public int getItemCount() {
        return notification.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMsg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMsg=itemView.findViewById(R.id.txtMsg);

        }
    }
}
