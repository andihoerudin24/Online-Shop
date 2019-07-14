package com.tutorkomputer.bukatoko.data.retrofit;

import com.tutorkomputer.bukatoko.data.model.Notification;
import com.tutorkomputer.bukatoko.data.model.Upload;
import com.tutorkomputer.bukatoko.data.model.rajaongkir.City;
import com.tutorkomputer.bukatoko.data.model.rajaongkir.Cost;
import com.tutorkomputer.bukatoko.data.model.Detail;
import com.tutorkomputer.bukatoko.data.model.Product;
import com.tutorkomputer.bukatoko.data.model.User;
import com.tutorkomputer.bukatoko.data.model.rajaongkir.Waybill;
import com.tutorkomputer.bukatoko.data.model.transaction.TransDetail;
import com.tutorkomputer.bukatoko.data.model.transaction.TransUser;
import com.tutorkomputer.bukatoko.data.model.transaction.Transpost;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("products")
    Call<Product> getProducts();

    @GET("products/{id}")
    Call<Detail> getDetail(@Path("id") int id);

    @FormUrlEncoded
    @POST("auth/login")
    Call<User> authLogin (@Field("email") String email,
                          @Field("password") String password);


    @FormUrlEncoded
    @POST("auth/register")
    Call<User> authRegister (@Field("name") String name,
                             @Field("email") String email,
                             @Field("password") String password);


    @FormUrlEncoded
    @POST("auth/update/{id}")
    Call<User> updateUser (  @Path("id") String id,
                             @Field("name") String name,
                             @Field("email") String email,
                             @Field("password") String password);



    @POST("transaction")
    Call<Transpost> insertTrans
                           (@Body Transpost transpost);


    @GET("transaction-user/{id}/unpaid")
    Call<TransUser> getTransUnpaid(@Path("id") String id);

    @GET("transaction/{code}")
    Call<TransDetail> getTransDetail(@Path("code") String code);

    @GET("transaction-user/{id}/paid")
    Call<TransUser> getTransPaid(@Path("id") String id);


    @FormUrlEncoded
    @POST("auth/firebase-token/{id}")
    Call<User> updateToken(
            @Path("id") String user_id,
            @Field("token") String token
            );

    @Multipart
    @POST("upload/{code}")
    Call<Upload> uploadImage(
            @Path("code") String code,
            @Part MultipartBody.Part file
    );


    @GET("notification/{id}")
    Call<Notification> myNotification (@Path("id") String user_id);



    /** Api Raja ongkir */

    @GET("city")
    Call<City> getCities(@Query("key") String key);

    @FormUrlEncoded
    @POST("cost")
    Call<Cost> getCost (
                           @Field("key") String key,
                           @Field("origin") String origin,
                           @Field("destination") String destination,
                           @Field("weight") String weight,
                           @Field("courier") String courier

                           );

    @FormUrlEncoded
    @POST("waybill")
    Call<Waybill> getWaybill (
                              @Field("key") String key,
                              @Field("waybill") String waybill,
                              @Field("courier") String courier
                           );
}
