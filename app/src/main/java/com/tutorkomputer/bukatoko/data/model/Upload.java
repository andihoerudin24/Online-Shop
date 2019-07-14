package com.tutorkomputer.bukatoko.data.model;

import com.google.gson.annotations.SerializedName;

public class Upload {

    @SerializedName("status")
    private Status status;

    public class Status{
        @SerializedName("code")
        private int code;

        @SerializedName("description")
        private String description;


        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

    }
}
