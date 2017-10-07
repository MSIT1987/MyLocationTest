package ir.msit87.mylocationtest.model;

import java.io.Serializable;

/**
 * Created by MSIT on 10/6/2017.
 */

public class inputLocationQuery implements Serializable {

    private String placeid;

    private String key;

    public String getPlaceid() {
        return placeid;
    }

    public void setPlaceid(String placeid) {
        this.placeid = placeid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
