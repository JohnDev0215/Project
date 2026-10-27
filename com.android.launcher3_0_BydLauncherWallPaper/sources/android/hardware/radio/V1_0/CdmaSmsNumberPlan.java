package android.hardware.radio.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class CdmaSmsNumberPlan {
    public static final int DATA = 3;
    public static final int PRIVATE = 9;
    public static final int RESERVED_10 = 10;
    public static final int RESERVED_11 = 11;
    public static final int RESERVED_12 = 12;
    public static final int RESERVED_13 = 13;
    public static final int RESERVED_14 = 14;
    public static final int RESERVED_15 = 15;
    public static final int RESERVED_2 = 2;
    public static final int RESERVED_5 = 5;
    public static final int RESERVED_6 = 6;
    public static final int RESERVED_7 = 7;
    public static final int RESERVED_8 = 8;
    public static final int TELEPHONY = 1;
    public static final int TELEX = 4;
    public static final int UNKNOWN = 0;

    public static final String toString(int i) {
        return i == 0 ? "UNKNOWN" : i == 1 ? "TELEPHONY" : i == 2 ? "RESERVED_2" : i == 3 ? "DATA" : i == 4 ? "TELEX" : i == 5 ? "RESERVED_5" : i == 6 ? "RESERVED_6" : i == 7 ? "RESERVED_7" : i == 8 ? "RESERVED_8" : i == 9 ? "PRIVATE" : i == 10 ? "RESERVED_10" : i == 11 ? "RESERVED_11" : i == 12 ? "RESERVED_12" : i == 13 ? "RESERVED_13" : i == 14 ? "RESERVED_14" : i == 15 ? "RESERVED_15" : "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("UNKNOWN");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("TELEPHONY");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("RESERVED_2");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("DATA");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("TELEX");
            i2 |= 4;
        }
        if ((i & 5) == 5) {
            arrayList.add("RESERVED_5");
            i2 |= 5;
        }
        if ((i & 6) == 6) {
            arrayList.add("RESERVED_6");
            i2 |= 6;
        }
        if ((i & 7) == 7) {
            arrayList.add("RESERVED_7");
            i2 |= 7;
        }
        if ((i & 8) == 8) {
            arrayList.add("RESERVED_8");
            i2 |= 8;
        }
        if ((i & 9) == 9) {
            arrayList.add("PRIVATE");
            i2 |= 9;
        }
        if ((i & 10) == 10) {
            arrayList.add("RESERVED_10");
            i2 |= 10;
        }
        if ((i & 11) == 11) {
            arrayList.add("RESERVED_11");
            i2 |= 11;
        }
        if ((i & 12) == 12) {
            arrayList.add("RESERVED_12");
            i2 |= 12;
        }
        if ((i & 13) == 13) {
            arrayList.add("RESERVED_13");
            i2 |= 13;
        }
        if ((i & 14) == 14) {
            arrayList.add("RESERVED_14");
            i2 |= 14;
        }
        if ((i & 15) == 15) {
            arrayList.add("RESERVED_15");
            i2 |= 15;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
