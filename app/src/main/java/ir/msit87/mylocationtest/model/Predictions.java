package ir.msit87.mylocationtest.model;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * The Predictions Model Class Created by MSIT on 10/2/2017.
 */

public class Predictions {

    @SerializedName("description")
    private String description;

    @SerializedName("id")
    private String id;

    @SerializedName("matched_substrings")
    private ArrayList<Matched_SubStrings> matched_substrings = new ArrayList<>();

    @SerializedName("place_id")
    private String place_id;

    @SerializedName("reference")
    private String reference;

    @SerializedName("structured_formatting")
    private Structured_Formatting structured_formatting;

    @SerializedName("terms")
    private ArrayList<Terms> terms = new ArrayList<>();

    @SerializedName("types")
    private ArrayList<String> types = new ArrayList<>();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Matched_SubStrings> getMatched_substrings() {
        return matched_substrings;
    }

    public void setMatched_substrings(ArrayList<Matched_SubStrings> matched_substrings) {
        this.matched_substrings = matched_substrings;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Structured_Formatting getStructured_formatting() {
        return structured_formatting;
    }

    public void setStructured_formatting(Structured_Formatting structured_formatting) {
        this.structured_formatting = structured_formatting;
    }

    public ArrayList<Terms> getTerms() {
        return terms;
    }

    public void setTerms(ArrayList<Terms> terms) {
        this.terms = terms;
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<String> types) {
        this.types = types;
    }
}
