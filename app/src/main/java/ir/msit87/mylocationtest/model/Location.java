package ir.msit87.mylocationtest.model;

import com.google.gson.annotations.SerializedName;

/**
 * The Location Model Class Created by MSIT on 10/2/2017.
 */

public class Location {

    @SerializedName("lat")
    private Double lat;

    @SerializedName("lng")
    private Double lng;

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

}
