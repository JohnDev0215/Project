package com.byd.car.hardware.bcm.seat;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
public class SeatExtended {
    public static final long FEATURE_ELECTRIC_CONTROL = 2;
    public static final long FEATURE_MANUAL_CONTROL = 4;
    public static final long SEAT_ALL = 6;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ExtendedFeature {
    }
}
