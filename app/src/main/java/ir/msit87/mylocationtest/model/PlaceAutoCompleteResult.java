package ir.msit87.mylocationtest.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * PlaceAutoCompleteResult Class Created by MSIT on 10/2/2017.
 */

public class PlaceAutoCompleteResult {

    @SerializedName("predictions")
    private ArrayList<Predictions> predictions = new ArrayList<>();

    @SerializedName("status")
    private String status;

    public ArrayList<Predictions> getPredictions() {
        return predictions;
    }

    public void setPredictions(ArrayList<Predictions> predictions) {
        this.predictions = predictions;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
