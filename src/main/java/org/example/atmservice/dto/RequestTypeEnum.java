package org.example.atmservice.dto;

public enum RequestTypeEnum {

    FAILURE_RESTART("FAILURE_RESTART"),
    PRIORITY("PRIORITY"),
    SIGNAL_LOW("SIGNAL_LOW"),
    STANDARD("STANDARD");

    private final String value;

    RequestTypeEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}