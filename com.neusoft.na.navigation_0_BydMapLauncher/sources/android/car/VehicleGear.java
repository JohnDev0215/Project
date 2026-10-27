package android.car;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
public final class VehicleGear {
    public static final int GEAR_DRIVE = 8;
    public static final int GEAR_EIGHTH = 2048;
    public static final int GEAR_FIFTH = 256;
    public static final int GEAR_FIRST = 16;
    public static final int GEAR_FOURTH = 128;
    public static final int GEAR_NEUTRAL = 1;
    public static final int GEAR_NINTH = 4096;
    public static final int GEAR_PARK = 4;
    public static final int GEAR_REVERSE = 2;
    public static final int GEAR_SECOND = 32;
    public static final int GEAR_SEVENTH = 1024;
    public static final int GEAR_SIXTH = 512;
    public static final int GEAR_THIRD = 64;
    public static final int GEAR_UNKNOWN = 0;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Enum {
    }

    private VehicleGear() {
    }

    public static String toString(int i) {
        return i == 0 ? "GEAR_UNKNOWN" : i == 1 ? "GEAR_NEUTRAL" : i == 2 ? "GEAR_REVERSE" : i == 4 ? "GEAR_PARK" : i == 8 ? "GEAR_DRIVE" : i == 16 ? "GEAR_FIRST" : i == 32 ? "GEAR_SECOND" : i == 64 ? "GEAR_THIRD" : i == 128 ? "GEAR_FOURTH" : i == 256 ? "GEAR_FIFTH" : i == 512 ? "GEAR_SIXTH" : i == 1024 ? "GEAR_SEVENTH" : i == 2048 ? "GEAR_EIGHTH" : i == 4096 ? "GEAR_NINTH" : "0x" + Integer.toHexString(i);
    }
}
