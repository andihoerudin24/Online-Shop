package com.tutorkomputer.bukatoko.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Notification {


    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    @SerializedName("data")
    private List<Data> data;

    public class Data{
        @SerializedName("description")
        private String message;
        
        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }


    }


}
