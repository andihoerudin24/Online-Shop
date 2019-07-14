package com.tutorkomputer.bukatoko.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
import com.glide.slider.library.Animations.DescriptionAnimation;
import com.glide.slider.library.SliderLayout;
import com.glide.slider.library.SliderTypes.BaseSliderView;
import com.glide.slider.library.SliderTypes.DefaultSliderView;
import com.glide.slider.library.Tricks.ViewPagerEx;
import com.tutorkomputer.bukatoko.App;
import com.tutorkomputer.bukatoko.R;
import com.tutorkomputer.bukatoko.activity.dialog.CartDialog;
import com.tutorkomputer.bukatoko.data.Constant;
import com.tutorkomputer.bukatoko.data.model.Detail;
import com.tutorkomputer.bukatoko.data.retrofit.Api;
import com.tutorkomputer.bukatoko.data.retrofit.ApiInterface;
import com.tutorkomputer.bukatoko.utils.Constans;
import com.tutorkomputer.bukatoko.utils.Converter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener,
        ViewPagerEx.OnPageChangeListener {

    Bundle bundle;
    TextView txtName,txtPrice,txtDescription;
    SliderLayout sliderLayout;
    ImageButton btnAddCart;
    Button btnCheckout;
    int detailPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        bundle = getIntent().getExtras();
        Log.e("_PRODUCT_ID",bundle.getString("PRODUCT_IMAGE"));

        txtName=findViewById(R.id.txtNameDetail);
        txtPrice=findViewById(R.id.txtPrice);
        txtDescription=findViewById(R.id.txtDescription);
        btnAddCart=findViewById(R.id.btnAddCart);
        btnCheckout=findViewById(R.id.btnCheckout);

        //event
        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                 addToCart();
//              Long  cart_id =App.sqLiteHelper.addToCart(bundle.getInt("PRODUCT_ID"),txtName.getText().toString(),
//                        bundle.getString("PRODUCT_IMAGE"),
//                        detailPrice);
//              Log.e("_log_car",String.valueOf(cart_id));

                 new CartDialog().showCartDialog(DetailActivity.this);
            }
        });

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart();


                Constant.SHOP_NOW=true;
                startActivity(new Intent(DetailActivity.this,CartActivity.class));
                finish();
            }
        });


        getDetails();

        getSupportActionBar().setTitle("Detail Barang");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private void addToCart(){
        if (App.sqLiteHelper.checkExists(bundle.getInt("PRODUCT_ID")) == 0){

            Long cart_id =App.sqLiteHelper.addToCart(
                    bundle.getInt("PRODUCT_ID"),
                    txtName.getText().toString(),
                    bundle.getString("PRODUCT_IMAGE"),
                    detailPrice);
            Log.e("_logcaratid",String.valueOf(cart_id));

        }
    }


    private void getDetails(){
        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
        Call<Detail> call         = apiInterface.getDetail(bundle.getInt("PRODUCT_ID"));

        call.enqueue(new Callback<Detail>() {
            @Override
            public void onResponse(Call<Detail> call, Response<Detail> response) {
                  Detail.Data data = response.body().getData();
                  Log.e("_getproduc",data.getProduct());
                  txtName.setText(data.getProduct());
                  txtPrice.setText(Converter.rupiah(data.getPrice()));
                  detailPrice = data.getPrice();

                  if (data.getDescription()!=null){
                     txtDescription.setText(Html.fromHtml(data.getDescription()));
                  }
                  Detail detail =response.body();
                  List<Detail.Data.Images> images =  detail.getData().getImages();
                  ArrayList<String> arrayList = new ArrayList<>();
                  for (Detail.Data.Images img : images){
                      arrayList.add(Constans.COVER_PATH + img.getImage());
                      Log.e("_logimage",Constans.COVER_PATH + img.getImage());
                  }
                  setSlider(arrayList);



            }

            @Override
            public void onFailure(Call<Detail> call, Throwable t) {

            }
        });

    }

    private void setSlider(ArrayList<String> urlImgs){
        sliderLayout = findViewById(R.id.slider);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.centerCrop();
        //.diskCacheStrategy(DiskCacheStrategy.NONE)
        requestOptions.placeholder(R.drawable.no_profile);
        requestOptions.error(R.drawable.no_profile);

        for (int i = 0; i < urlImgs.size(); i++) {
            DefaultSliderView sliderView = new DefaultSliderView(this);
            // if you want show image only / without description text use DefaultSliderView instead

            // initialize SliderLayout
            sliderView
                    .image(urlImgs.get(i))
                    .setRequestOption(requestOptions)
                    .setProgressBarVisible(false)
                    .setOnSliderClickListener(this);

            //add your extra information
            sliderView.bundle(new Bundle());
            sliderLayout.addSlider(sliderView);
        }
        // set Slider Transition Animation
        // mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);

        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(4000);
        sliderLayout.addOnPageChangeListener(this);
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
