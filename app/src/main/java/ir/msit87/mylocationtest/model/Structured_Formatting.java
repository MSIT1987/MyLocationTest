package ir.msit87.mylocationtest.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * The Structured_Formatting Model Class Created by MSIT on 10/3/2017.
 */

public class Structured_Formatting {

    @SerializedName("main_text")
    private String main_text;

    @SerializedName("main_text_matched_substrings")
    private ArrayList<Matched_SubStrings> main_text_matched_substrings;

    @SerializedName("secondary_text")
    private String secondary_text;

    public String getMain_text() {
        return main_text;
    }

    public void setMain_text(String main_text) {
        this.main_text = main_text;
    }

    public ArrayList<Matched_SubStrings> getMain_text_matched_substrings() {
        return main_text_matched_substrings;
    }

    public void setMain_text_matched_substrings(ArrayList<Matched_SubStrings> main_text_matched_substrings) {
        this.main_text_matched_substrings = main_text_matched_substrings;
    }

    public String getSecondary_text() {
        return secondary_text;
    }

    public void setSecondary_text(String secondary_text) {
        this.secondary_text = secondary_text;
    }
}
