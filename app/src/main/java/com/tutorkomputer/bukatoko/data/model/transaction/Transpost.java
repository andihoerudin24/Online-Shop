package com.tutorkomputer.bukatoko.data.model.transaction;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Transpost {

    @SerializedName("user_id")
    private  int user_id;
    @SerializedName("destination")
    private  String destination;
    @SerializedName("ongkir")
    private  int ongkir;
    @SerializedName("grandtotal")
    private  int grandtotal;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getOngkir() {
        return ongkir;
    }

    public void setOngkir(int ongkir) {
        this.ongkir = ongkir;
    }

    public int getGrandtotal() {
        return grandtotal;
    }

    public void setGrandtotal(int grandtotal) {
        this.grandtotal = grandtotal;
    }

    @SerializedName("detail")
    public List<Detail> detailList;


    public List<Detail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<Detail> detailList) {
        this.detailList = detailList;
    }


    public class Detail{
       @SerializedName("product_id")
       private int product_id;
       @SerializedName("qty")
       private int qty;
       @SerializedName("price")
       private int price;
       @SerializedName("total")
       private int total;

        public int getProduct_id() {
            return product_id;
        }

        public void setProduct_id(int product_id) {
            this.product_id = product_id;
        }

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

    }



}
