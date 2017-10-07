package ir.msit87.mylocationtest.model;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * The Address_Component Model Class Created by MSIT on 10/2/2017.
 */

public class Address_Component {

    @SerializedName("long_name")
    private String long_name;

    @SerializedName("short_name")
    private String short_name;

    @SerializedName("types")
    private ArrayList<String> types = new ArrayList<>();

    public String getLong_name() {
        return long_name;
    }

    public void setLong_name(String long_name) {
        this.long_name = long_name;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<String> types) {
        this.types = types;
    }
}
