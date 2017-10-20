package ir.msit87.mylocationtest.modelDirection;

import java.io.Serializable;

/**
 * Created by MSIT on 10/18/2017.
 */

public class InputDirectionQuery implements Serializable {

    private String origin;

    private String destination;

    private String sensor;

    private String mode;

//    private String key;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getSensor() {
        return sensor;
    }

    public void setSensor(String sensor) {
        this.sensor = sensor;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

//    public String getKey() {
//        return key;
//    }
//
//    public void setKey(String key) {
//        this.key = key;
//    }
}
