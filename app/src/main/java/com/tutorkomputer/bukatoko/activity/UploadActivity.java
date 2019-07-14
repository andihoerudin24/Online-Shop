package com.tutorkomputer.bukatoko.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tutorkomputer.bukatoko.R;
import com.tutorkomputer.bukatoko.data.model.Upload;
import com.tutorkomputer.bukatoko.data.retrofit.Api;
import com.tutorkomputer.bukatoko.data.retrofit.ApiInterface;
import com.tutorkomputer.bukatoko.utils.FileUtils;

import java.io.File;
import java.net.URI;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadActivity extends AppCompatActivity {

    ImageView imageView;
    Button btnUpload;
    TextView textGallery;
    ProgressBar progressBar;
    LinearLayout linearLayout;

    private static final int PICK_IMAGE_REQUEST=1;

    private Uri uri;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        bundle = getIntent().getExtras();
        Log.e("andicode",bundle.getString("TRANSACTION_CODE"));

        imageView=findViewById(R.id.imageView);
        btnUpload=findViewById(R.id.btnUpload);
        textGallery=findViewById(R.id.textGallery);
        progressBar=findViewById(R.id.progressBar);
        linearLayout=findViewById(R.id.linearLayout);


        textGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                permissionGallery();
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uri != null){
                    File file =FileUtils.getFile(getApplicationContext(),uri);
                    uploadImage(file);

                    linearLayout.setVisibility(View.GONE);
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
        });

        getSupportActionBar().setTitle("Upload Bukti Pembayaran");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }


    private void openGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Image"),PICK_IMAGE_REQUEST);
    }

    private void permissionGallery() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE,
                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);

        } else {
            openGallery();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,@Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            uri =data.getData();
            imageView.setImageURI(uri);

            textGallery.setText("Ubah Gambar");
            btnUpload.setVisibility(View.VISIBLE);
        }
    }

    private void uploadImage(File fileimage)
    {
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"),fileimage);

        MultipartBody.Part image =MultipartBody.Part.createFormData("foto",fileimage.getName(),requestFile);

        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
        Call<Upload> call = apiInterface.uploadImage(bundle.getString("TRANSACTION_CODE"),image);
        call.enqueue(new Callback<Upload>() {
            @Override
            public void onResponse(Call<Upload> call, Response<Upload> response) {
                if (response.code() == 202){
                    Toast.makeText(getApplicationContext(),"Bukti Transaksi Berhasil Di upload",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Terjadi Kesalahan",Toast.LENGTH_SHORT).show();
                }


                linearLayout.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<Upload> call, Throwable t) {

            }
        });
    }


}
