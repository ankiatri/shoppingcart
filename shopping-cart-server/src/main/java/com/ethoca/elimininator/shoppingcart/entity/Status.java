package com.ethoca.elimininator.shoppingcart.entity;

/**
 * Enum for possible values of status. Currently using OPENED/SUBMITTED values
 * but value is also provided to update value in future
 *
 */
public enum Status {

    OPENED("Opened"), SUBMITTED("Submitted");

    private String statusValue;

    Status(String statusValue) {
        this.statusValue = statusValue;
    }

    public String getStatusValue() {
        return statusValue;
    }

}
