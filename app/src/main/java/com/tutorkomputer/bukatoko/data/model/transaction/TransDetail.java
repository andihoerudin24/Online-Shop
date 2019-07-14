package com.tutorkomputer.bukatoko.data.model.transaction;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TransDetail {

    @SerializedName("data")
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data{
        @SerializedName("transaction_code")
        private String transaction_code;
        @SerializedName("user")
        private String user;
        @SerializedName("destination")
        private String destination;
        @SerializedName("ongkir")
        private String ongkir;
        @SerializedName("resi_Code")
        private String resi_Code;
        @SerializedName("kurir")
        private String kurir;
        @SerializedName("grandtotal")
        private String grandtotal;
        @SerializedName("status_transaction")
        private String status_transaction;


        public String getTransaction_code() {
            return transaction_code;
        }

        public void setTransaction_code(String transaction_code) {
            this.transaction_code = transaction_code;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getDestination() {
            return destination;
        }

        public void setDestination(String destination) {
            this.destination = destination;
        }

        public String getOngkir() {
            return ongkir;
        }

        public void setOngkir(String ongkir) {
            this.ongkir = ongkir;
        }

        public String getResi_Code() {
            return resi_Code;
        }

        public void setResi_Code(String resi_Code) {
            this.resi_Code = resi_Code;
        }

        public String getKurir() {
            return kurir;
        }

        public void setKurir(String kurir) {
            this.kurir = kurir;
        }

        public String getGrandtotal() {
            return grandtotal;
        }

        public void setGrandtotal(String grandtotal) {
            this.grandtotal = grandtotal;
        }

        public String getStatus_transaction() {
            return status_transaction;
        }

        public void setStatus_transaction(String status_transaction) {
            this.status_transaction = status_transaction;
        }


        @SerializedName("detail_transaction")
        private List<DetailTransaction> detail_transaction;


        public List<DetailTransaction> getDetail_transaction() {
            return detail_transaction;
        }

        public void setDetail_transaction(List<DetailTransaction> detail_transaction) {
            this.detail_transaction = detail_transaction;
        }

        public class DetailTransaction{
            @SerializedName("product_id")
            private String product_id;
            @SerializedName("product")
            private String product;
            @SerializedName("price")
            private String price;
            @SerializedName("qty")
            private String qty;
            @SerializedName("total")
            private String total;

            public String getProduct_id() {
                return product_id;
            }

            public void setProduct_id(String product_id) {
                this.product_id = product_id;
            }

            public String getProduct() {
                return product;
            }

            public void setProduct(String product) {
                this.product = product;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getQty() {
                return qty;
            }

            public void setQty(String qty) {
                this.qty = qty;
            }

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

        }



    }

}
