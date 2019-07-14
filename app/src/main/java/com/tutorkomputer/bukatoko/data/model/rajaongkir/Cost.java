package com.tutorkomputer.bukatoko.data.model.rajaongkir;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Cost {

    @SerializedName("rajaongkir")
    private Rajaongkir rajaongkir;

    public Rajaongkir getRajaongkir() {
        return rajaongkir;
    }

    public void setRajaongkir(Rajaongkir rajaongkir) {
        this.rajaongkir = rajaongkir;
    }



    public class Rajaongkir{

        @SerializedName("results")
        private List<Results> results;

        public List<Results> getResults() {
            return results;
        }

        public void setResults(List<Results> results) {
            this.results = results;
        }

        public class Results{

            @SerializedName("code")
            private String code;
            @SerializedName("name")
            private String name;


            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            @SerializedName("costs")
            private List<Costs> costs;

            public List<Costs> getCosts() {
                return costs;
            }

            public void setCosts(List<Costs> costs) {
                this.costs = costs;
            }

            public class Costs{
                @SerializedName("service")
                private String service;
                @SerializedName("description")
                private String description;


                public String getService() {
                    return service;
                }

                public void setService(String service) {
                    this.service = service;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                @SerializedName("cost")
                private List<Data> cost;


                public List<Data> getCost() {
                    return cost;
                }

                public void setCost(List<Data> cost) {
                    this.cost = cost;
                }

                public class Data{
                    @SerializedName("value")
                    private int value;
                    @SerializedName("etd")
                    private String etd;

                    public int getValue() {
                        return value;
                    }

                    public void setValue(int value) {
                        this.value = value;
                    }

                    public String getEtd() {
                        return etd;
                    }

                    public void setEtd(String etd) {
                        this.etd = etd;
                    }


                }
            }

        }
    }
}
