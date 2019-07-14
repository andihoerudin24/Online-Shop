package com.tutorkomputer.bukatoko.data.model;

import android.media.Image;

import com.google.gson.annotations.SerializedName;

import java.util.List;
public class Detail {

    @SerializedName("data")
    private Data data;

    @SerializedName("product")
    private String product;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data{

        @SerializedName("id")
        private int id;
        @SerializedName("product")
        private String product;
        @SerializedName("price")
        private int price;
        @SerializedName("stock")
        private int stock;
        @SerializedName("description")
        private String description;

        @SerializedName("images")
        private List<Images> images;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getProduct() {
            return product;
        }

        public void setProduct(String product) {
            this.product = product;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public List<Images> getImages() {
            return images;
        }

        public void setImages(List<Images> images) {
            this.images = images;
        }

        public class Images {

            private int id;

            private String image;
            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

        }
    }
}