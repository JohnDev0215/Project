package com.amplifyframework.predictions.models;

/* JADX INFO: loaded from: classes5.dex */
public final class Pose {
    private final double pitch;
    private final double roll;
    private final double yaw;

    public Pose(double d, double d2, double d3) {
        this.pitch = d;
        this.roll = d2;
        this.yaw = d3;
    }

    public double getPitch() {
        return this.pitch;
    }

    public double getRoll() {
        return this.roll;
    }

    public double getYaw() {
        return this.yaw;
    }
}
