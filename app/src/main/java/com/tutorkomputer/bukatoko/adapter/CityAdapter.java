package com.tutorkomputer.bukatoko.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.tutorkomputer.bukatoko.R;
import com.tutorkomputer.bukatoko.data.Constant;
import com.tutorkomputer.bukatoko.data.model.rajaongkir.City;

import java.util.ArrayList;
import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> implements Filterable {

    private List<City.Rajaongkir.Results> cities;
    private List<City.Rajaongkir.Results> citiesFiltered;

    private Context context;
    public CityAdapter(Context context, List<City.Rajaongkir.Results> cities) {
        this.cities         = cities;
        this.citiesFiltered = cities;
        this.context        = context;
    }

    @NonNull
    @Override
    public CityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_city, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final City.Rajaongkir.Results city = citiesFiltered.get(position);

        holder.txtCity.setText( city.getCity_name() );
        holder.txtCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                OngkirDialog.edtDestination.setText( city.getCity_name() );
//                OngkirDialog.DESTINATION = city.getCity_id();

                Constant.DESTINATION = city.getCity_id();
                Constant.DESTINATION_NAME = city.getCity_name();

                ((Activity) context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return citiesFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    citiesFiltered = cities;
                } else {
                    List<City.Rajaongkir.Results> filteredList = new ArrayList<>();
                    for (City.Rajaongkir.Results row : cities) {

                        if (row.getCity_name().toLowerCase().contains(charString.toLowerCase()) ) {
                            filteredList.add(row);
                        }
                    }

                    citiesFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = citiesFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                citiesFiltered = (ArrayList<City.Rajaongkir.Results>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtCity;

        private ViewHolder(View itemView) {
            super(itemView);
            txtCity     = itemView.findViewById(R.id.txtCity);
        }
    }
}