package com.tutorkomputer.bukatoko.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tutorkomputer.bukatoko.App;
import com.tutorkomputer.bukatoko.R;
import com.tutorkomputer.bukatoko.activity.CartActivity;
import com.tutorkomputer.bukatoko.data.model.Cart;
import com.tutorkomputer.bukatoko.utils.Converter;
import com.tutorkomputer.bukatoko.utils.GlideImage;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private Context context;
    private List<Cart> carts;


    public CartAdapter(Context context,List<Cart> carts){
        this.context =context;
        this.carts   =carts;
    }



    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_cart,viewGroup,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartAdapter.ViewHolder holder,final int i) {
               final Cart cart=carts.get(i);
               cart.setQty(1);
               cart.setTotal(cart.getTotal());
               GlideImage.get(context,cart.getImage(),holder.imgProd);


               holder.txtName.setText(cart.getProduct());
               holder.txtPrice.setText(
                       Converter.rupiah(cart.getPrice())
               );
               holder.spnQty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                   @Override
                   public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                       int total = Integer.valueOf(parent.getItemAtPosition(position).toString()) * cart.getPrice();
                       holder.txtTotal.setText(Converter.rupiah(total));

                       cart.setQty(Integer.valueOf(parent.getItemAtPosition(position).toString()));
                       cart.setTotal(total);
                       getTotal();
                   }

                   @Override
                   public void onNothingSelected(AdapterView<?> parent) {

                   }
               });

               holder.btnDel.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       AlertDialog.Builder builder = new AlertDialog.Builder(context);
                       builder.setCancelable(false);

                       builder.setTitle("Konfrimasi");
                       builder.setMessage(" Yakin Untuk Menghapus " + cart.getProduct() + " Dari Keranjang ");

                       builder.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               App.sqLiteHelper.removeItem(String.valueOf(cart.getCart_id()));
                               carts.remove(i);
                               notifyDataSetChanged();

                           }
                       });
                       builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                              dialog.dismiss();
                           }
                       });

                       builder.show();
                   }
               });
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProd;
        TextView txtName,txtPrice,txtTotal;
        ImageButton btnDel;
        Spinner spnQty;

        public ViewHolder(View view) {
            super(view);
            imgProd   =view.findViewById(R.id.imgProd);
            txtName   =view.findViewById(R.id.txtName);
            txtPrice  =view.findViewById(R.id.txtPrice);
            btnDel    =view.findViewById(R.id.btnDel);

            txtTotal    =view.findViewById(R.id.txtTotal);
            spnQty    =view.findViewById(R.id.spnQty);


            ArrayList<String> arrayList = new ArrayList<>();
            for (int i=1; i<=5; i++){
                arrayList.add(String.valueOf(i));
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(context,android.R.layout.simple_spinner_item,arrayList);
            spnQty.setAdapter(adapter);
        }
    }


    public int getTotal(){
        int priceTotal =0;
                for(int i=0; i<carts.size();i++){
                    priceTotal += carts.get(i).getTotal();
                }

        CartActivity.txtPriceTotal.setText("Total Rp" + Converter.rupiah(priceTotal));

                return priceTotal;
    }


}
