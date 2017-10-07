package ir.msit87.mylocationtest.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/**
 * The Result Model Class Created by MSIT on 10/2/2017.
 */

public class Result {

    @SerializedName("address_components")
    private ArrayList<Address_Component> address_components = new ArrayList<>();

    @SerializedName("adr_address")
    private String adr_address;

    @SerializedName("formatted_address")
    private String formatted_address;

//    @SerializedName("formatted_phone_number")
//    private String formatted_phone_number;

    @SerializedName("geometry")
    private Geometry geometry;

    @SerializedName("icon")
    private String icon;

    @SerializedName("id")
    private String id;

//    @SerializedName("international_phone_number")
//    private String international_phone_number;

    @SerializedName("name")
    private String name;

    @SerializedName("place_id")
    private String place_id;

//    @SerializedName("rating")
//    private Double rating;

    @SerializedName("reference")
    private String reference;

    @SerializedName("scope")
    private String scope;

    @SerializedName("types")
    private ArrayList<String> types = new ArrayList<>();

//    @SerializedName("reviews")
//    private ArrayList<Reviews> reviews = new ArrayList<>();

    @SerializedName("url")
    private String url;

    @SerializedName("utc_offset")
    private Integer utc_offset;

    @SerializedName("vicinity")
    private String vicinity;

    @SerializedName("website")
    private String website;

    public ArrayList<Address_Component> getAddress_components() {
        return address_components;
    }

    public void setAddress_components(ArrayList<Address_Component> address_components) {
        this.address_components = address_components;
    }

    public String getAdr_address() {
        return adr_address;
    }

    public void setAdr_address(String adr_address) {
        this.adr_address = adr_address;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

//    public String getFormatted_phone_number() {
//        return formatted_phone_number;
//    }
//
//    public void setFormatted_phone_number(String formatted_phone_number) {
//        this.formatted_phone_number = formatted_phone_number;
//    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    public String getInternational_phone_number() {
//        return international_phone_number;
//    }
//
//    public void setInternational_phone_number(String international_phone_number) {
//        this.international_phone_number = international_phone_number;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

//    public Double getRating() {
//        return rating;
//    }
//
//    public void setRating(Double rating) {
//        this.rating = rating;
//    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

//    public ArrayList<Reviews> getReviews() {
//        return reviews;
//    }
//
//    public void setReviews(ArrayList<Reviews> reviews) {
//        this.reviews = reviews;
//    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<String> types) {
        this.types = types;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getUtc_offset() {
        return utc_offset;
    }

    public void setUtc_offset(Integer utc_offset) {
        this.utc_offset = utc_offset;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
