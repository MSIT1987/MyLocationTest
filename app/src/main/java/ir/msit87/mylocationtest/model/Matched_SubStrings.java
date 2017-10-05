package ir.msit87.mylocationtest.model;

import com.google.gson.annotations.SerializedName;

/**
 * The Matched_SubStrings Model Class Created by MSIT on 10/2/2017.
 */

public class Matched_SubStrings {

    @SerializedName("length")
    private Integer length;

    @SerializedName("offset")
    private Integer offset;

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
