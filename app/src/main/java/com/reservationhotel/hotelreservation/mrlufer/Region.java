package com.reservationhotel.hotelreservation.mrlufer;

public class Region {
    float lat;
    float lgt;

    public Region(float lat, float lgt) {
        this.lat = lat;
        this.lgt = lgt;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLgt() {
        return lgt;
    }

    public void setLgt(float lgt) {
        this.lgt = lgt;
    }
}
