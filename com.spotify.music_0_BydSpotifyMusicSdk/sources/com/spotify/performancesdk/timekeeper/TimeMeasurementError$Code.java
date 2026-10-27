package com.spotify.performancesdk.timekeeper;

/* JADX INFO: loaded from: classes.dex */
public enum TimeMeasurementError$Code {
    DUPLICATED_POINTS(1),
    BUILD_TWICE(2),
    POINTS_NOT_COMPLETED(3),
    INVALID_POINT_IDENTIFIERS(4),
    INVALID_DIMENSIONS(6);

    private final int value;

    TimeMeasurementError$Code(int i) {
        this.value = i;
    }

    public final int a() {
        return this.value;
    }
}
