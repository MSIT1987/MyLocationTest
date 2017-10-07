package ir.msit87.mylocationtest.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by MSIT on 10/2/2017.
 */

public class LocationResponse {

    @SerializedName("results")
    private ArrayList<Results> results = new ArrayList<>();

    @SerializedName("status")
    private String status;


    public ArrayList<Results> getResults() {
        return results;
    }

    public void setResults(ArrayList<Results> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
