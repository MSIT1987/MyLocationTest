package ir.msit87.mylocationtest.model;

import com.google.gson.annotations.SerializedName;

/**
 * The ViewPort Model Class Created by MSIT on 10/2/2017.
 */

public class ViewPort {

    @SerializedName("northeast")
    private Location northeast;

    @SerializedName("southwest")
    private Location southwest;

    public Location getNortheast() {
        return northeast;
    }

    public void setNortheast(Location northeast) {
        this.northeast = northeast;
    }

    public Location getSouthwest() {
        return southwest;
    }

    public void setSouthwest(Location southwest) {
        this.southwest = southwest;
    }
}
