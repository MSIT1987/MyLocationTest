package ir.msit87.mylocationtest.model;

import com.google.gson.annotations.SerializedName;

/**
 * The Terms Model ClassCreated by MSIT on 10/2/2017.
 */

public class Terms {

    @SerializedName("offset")
    private Integer offset;

    @SerializedName("value")
    private String value;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
