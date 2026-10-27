package com.android.car.apps.common.util;

/* JADX INFO: loaded from: classes5.dex */
public class VectorMath {
    public static final float EPSILON = 1.0E-4f;

    private VectorMath() {
    }

    public static float dotProduct(float vx, float vy, float ux, float uy) {
        return (vx * ux) + (vy * uy);
    }

    public static float norm2(float vx, float vy) {
        return (float) Math.sqrt(dotProduct(vx, vy, vx, vy));
    }

    public static float center(float a, float b) {
        return (a + b) / 2.0f;
    }
}
