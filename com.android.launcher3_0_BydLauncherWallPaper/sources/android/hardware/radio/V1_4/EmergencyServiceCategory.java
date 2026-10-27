package android.hardware.radio.V1_4;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class EmergencyServiceCategory {
    public static final int AIEC = 64;
    public static final int AMBULANCE = 2;
    public static final int FIRE_BRIGADE = 4;
    public static final int MARINE_GUARD = 8;
    public static final int MIEC = 32;
    public static final int MOUNTAIN_RESCUE = 16;
    public static final int POLICE = 1;
    public static final int UNSPECIFIED = 0;

    public static final String toString(int i) {
        return i == 0 ? "UNSPECIFIED" : i == 1 ? "POLICE" : i == 2 ? "AMBULANCE" : i == 4 ? "FIRE_BRIGADE" : i == 8 ? "MARINE_GUARD" : i == 16 ? "MOUNTAIN_RESCUE" : i == 32 ? "MIEC" : i == 64 ? "AIEC" : "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("UNSPECIFIED");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("POLICE");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("AMBULANCE");
            i2 |= 2;
        }
        if ((i & 4) == 4) {
            arrayList.add("FIRE_BRIGADE");
            i2 |= 4;
        }
        if ((i & 8) == 8) {
            arrayList.add("MARINE_GUARD");
            i2 |= 8;
        }
        if ((i & 16) == 16) {
            arrayList.add("MOUNTAIN_RESCUE");
            i2 |= 16;
        }
        if ((i & 32) == 32) {
            arrayList.add("MIEC");
            i2 |= 32;
        }
        if ((i & 64) == 64) {
            arrayList.add("AIEC");
            i2 |= 64;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
