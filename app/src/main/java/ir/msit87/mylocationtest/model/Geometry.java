package ir.msit87.mylocationtest.model;

import com.google.gson.annotations.SerializedName;

/**
 * The Geometry Model Class Created by MSIT on 10/2/2017.
 */

public class Geometry {

    @SerializedName("location")
    private Location location;

    @SerializedName("viewport")
    private ViewPort viewport;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public ViewPort getViewport() {
        return viewport;
    }

    public void setViewport(ViewPort viewport) {
        this.viewport = viewport;
    }
}
