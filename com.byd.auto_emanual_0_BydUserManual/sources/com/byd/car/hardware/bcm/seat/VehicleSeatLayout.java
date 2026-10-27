package com.byd.car.hardware.bcm.seat;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
public class VehicleSeatLayout {
    public static final int LAYOUT_200 = 8;
    public static final int LAYOUT_220 = 6;
    public static final int LAYOUT_222 = 3;
    public static final int LAYOUT_223 = 2;
    public static final int LAYOUT_230 = 4;
    public static final int LAYOUT_232 = 1;
    public static final int LAYOUT_300 = 7;
    public static final int LAYOUT_UNKNOWN = 0;

    @Retention(RetentionPolicy.SOURCE)
    public @interface SeatLayout {
    }
}
