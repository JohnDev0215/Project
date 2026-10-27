package com.ecarx.xui.adaptapi;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public interface CommonInfo {
    public static final int SEAT_ROW1 = 7;
    public static final int SEAT_ROW1_CENTER = 2;
    public static final int SEAT_ROW1_LEFT = 1;
    public static final int SEAT_ROW1_RIGHT = 4;
    public static final int SEAT_ROW2 = 56;
    public static final int SEAT_ROW2_CENTER = 16;
    public static final int SEAT_ROW2_LEFT = 8;
    public static final int SEAT_ROW2_RIGHT = 32;
    public static final int SEAT_ROW3 = 448;
    public static final int SEAT_ROW3_CENTER = 128;
    public static final int SEAT_ROW3_LEFT = 64;
    public static final int SEAT_ROW3_RIGHT = 256;
    public static final int ZONE_ALL = Integer.MIN_VALUE;
    public static final int ZONE_ROW_1_ALL = 7;
    public static final int ZONE_ROW_1_ALL_NO_CENTER = 5;
    public static final int ZONE_ROW_1_CENTER = 2;
    public static final int ZONE_ROW_1_LEFT = 1;
    public static final int ZONE_ROW_1_RIGHT = 4;
    public static final int ZONE_ROW_2_ALL = 56;
    public static final int ZONE_ROW_2_CENTER = 16;
    public static final int ZONE_ROW_2_LEFT = 8;
    public static final int ZONE_ROW_2_RIGHT = 32;
    public static final int ZONE_ROW_3_ALL = 448;
    public static final int ZONE_ROW_3_CENTER = 128;
    public static final int ZONE_ROW_3_LEFT = 64;
    public static final int ZONE_ROW_3_RIGHT = 256;

    @Retention(RetentionPolicy.SOURCE)
    public @interface SeatZone {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface VehicleZone {
    }
}
