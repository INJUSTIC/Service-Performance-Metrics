package com.partnou.sofixit.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GeoPosition {
    private double latitude;
    private double longitude;

    @Override
    public String toString() {
        return "{" +
                "latitude=" + latitude +
                "; longitude=" + longitude +
                '}';
    }
}
