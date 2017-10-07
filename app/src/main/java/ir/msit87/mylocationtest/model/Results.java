package ir.msit87.mylocationtest.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by MSIT on 10/6/2017.
 */

public class Results {

    @SerializedName("address_components")
    private ArrayList<Address_Component> address_components = new ArrayList<>();

    @SerializedName("formatted_address")
    private String formatted_address;

    @SerializedName("geometry")
    private Geometry geometry;

    @SerializedName("place_id")
    private String place_id;

    @SerializedName("types")
    private ArrayList<String> types = new ArrayList<>();

    public ArrayList<Address_Component> getAddress_components() {
        return address_components;
    }

    public void setAddress_components(ArrayList<Address_Component> address_components) {
        this.address_components = address_components;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<String> types) {
        this.types = types;
    }

}
