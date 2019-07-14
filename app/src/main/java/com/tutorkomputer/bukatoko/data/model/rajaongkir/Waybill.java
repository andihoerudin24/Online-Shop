package com.tutorkomputer.bukatoko.data.model.rajaongkir;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Waybill {

    @SerializedName("rajaongkir")
    private RajaOngkir rajaOngkir;

    public RajaOngkir getRajaOngkir() {
        return rajaOngkir;
    }

    public void setRajaOngkir(RajaOngkir rajaOngkir) {
        this.rajaOngkir = rajaOngkir;
    }

    public class RajaOngkir {

        @SerializedName("result")
        private Result result;

        public Result getResult() {
            return result;
        }

        public void setResult(Result result) {
            this.result = result;
        }

        public class Result{

            @SerializedName("delivery_status")
            private DeliveryStatus deliveryStatus;
            @SerializedName("manifest")
            private List<Manifest> manifest;

            public DeliveryStatus getDeliveryStatus() {
                return deliveryStatus;
            }

            public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
                this.deliveryStatus = deliveryStatus;
            }

            public List<Manifest> getManifest() {
                return manifest;
            }

            public void setManifest(List<Manifest> manifest) {
                this.manifest = manifest;
            }

            public class DeliveryStatus {

                @SerializedName("status")
                private String status;
                @SerializedName("pod_receiver")
                private String pod_receiver;
                @SerializedName("pod_date")
                private String pod_date;
                @SerializedName("pod_time")
                private String pod_time;

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getPod_receiver() {
                    return pod_receiver;
                }

                public void setPod_receiver(String pod_receiver) {
                    this.pod_receiver = pod_receiver;
                }

                public String getPod_date() {
                    return pod_date;
                }

                public void setPod_date(String pod_date) {
                    this.pod_date = pod_date;
                }

                public String getPod_time() {
                    return pod_time;
                }

                public void setPod_time(String pod_time) {
                    this.pod_time = pod_time;
                }
            }

            public class Manifest {

                @SerializedName("manifest_description")
                private String manifest_description;
                @SerializedName("manifest_date")
                private String manifest_date;
                @SerializedName("manifest_time")
                private String manifest_time;

                public String getManifest_description() {
                    return manifest_description;
                }

                public void setManifest_description(String manifest_description) {
                    this.manifest_description = manifest_description;
                }

                public String getManifest_date() {
                    return manifest_date;
                }

                public void setManifest_date(String manifest_date) {
                    this.manifest_date = manifest_date;
                }

                public String getManifest_time() {
                    return manifest_time;
                }

                public void setManifest_time(String manifest_time) {
                    this.manifest_time = manifest_time;
                }
            }
        }

    }
}