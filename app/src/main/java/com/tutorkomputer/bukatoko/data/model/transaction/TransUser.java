package com.tutorkomputer.bukatoko.data.model.transaction;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TransUser {
//
//               "transaction_code": "TR00003",
//               "user": "Mala Wahyuni",
//               "destination": "jakarta",
//               "ongkir": "25000.00",
//               "resi_Code": null,
//               "kurir": null,
//               "grandtotal": "Rp.420.000",
//               "status_transaction": "pending",
//               "date_transaction": "23-04-2019"
    @SerializedName("data")
    private List<Data> data;


    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public class Data
    {
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



    }
}
