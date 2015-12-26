package com.fabriciooliveira.projectstone.domain;

/**
 * Created by fabriciooliveira on 12/26/15.
 */
public class Payment {

    private String name;

    private String value;

    public Payment() {

    }

    public Payment(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
