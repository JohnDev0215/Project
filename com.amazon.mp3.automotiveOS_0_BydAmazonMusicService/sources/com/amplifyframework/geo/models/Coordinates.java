package com.amplifyframework.geo.models;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes5.dex */
public final class Coordinates implements Geometry {
    private double latitude;
    private double longitude;

    public Coordinates() {
        this(0.0d, 0.0d);
    }

    public Coordinates(double d, double d2) {
        this.latitude = d;
        this.longitude = d2;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double d) {
        this.latitude = d;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double d) {
        this.longitude = d;
    }

    public double centralAngle(Coordinates coordinates) {
        double radians = Math.toRadians(this.latitude - coordinates.latitude);
        double d = radians / 2.0d;
        double radians2 = Math.toRadians(this.longitude - coordinates.longitude) / 2.0d;
        double dSin = (Math.sin(d) * Math.sin(d)) + (Math.sin(radians2) * Math.sin(radians2) * Math.cos(Math.toRadians(this.latitude)) * Math.cos(Math.toRadians(coordinates.latitude)));
        return Math.atan2(Math.sqrt(dSin), Math.sqrt(1.0d - dSin)) * 2.0d;
    }

    public String toString() {
        return "Coordinates{latitude=" + this.latitude + ", longitude=" + this.longitude + '}';
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Coordinates.class != obj.getClass()) {
            return false;
        }
        Coordinates coordinates = (Coordinates) obj;
        return ObjectsCompat.equals(Double.valueOf(this.latitude), Double.valueOf(coordinates.latitude)) && ObjectsCompat.equals(Double.valueOf(this.longitude), Double.valueOf(coordinates.longitude));
    }

    public int hashCode() {
        return ObjectsCompat.hash(Double.valueOf(this.latitude), Double.valueOf(this.longitude));
    }
}
