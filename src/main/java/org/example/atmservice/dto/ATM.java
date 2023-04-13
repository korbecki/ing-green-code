package org.example.atmservice.dto;

public class ATM {
    private final int region;
    private final int atmId;

    public ATM(int region, int atmId) {
        this.region = region;
        this.atmId = atmId;
    }
}
