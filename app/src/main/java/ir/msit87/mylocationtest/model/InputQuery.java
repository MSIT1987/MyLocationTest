package ir.msit87.mylocationtest.model;

import java.io.Serializable;

/**
 * Created by MSIT on 10/3/2017.
 */

public class InputQuery implements Serializable {

    private String input;

    private String radius;

    private String language;

    private String component;

    private String key;

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }
}
