package android.hardware.radio.V1_1;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class GeranBands {
    public static final int BAND_450 = 3;
    public static final int BAND_480 = 4;
    public static final int BAND_710 = 5;
    public static final int BAND_750 = 6;
    public static final int BAND_850 = 8;
    public static final int BAND_DCS1800 = 12;
    public static final int BAND_E900 = 10;
    public static final int BAND_ER900 = 14;
    public static final int BAND_P900 = 9;
    public static final int BAND_PCS1900 = 13;
    public static final int BAND_R900 = 11;
    public static final int BAND_T380 = 1;
    public static final int BAND_T410 = 2;
    public static final int BAND_T810 = 7;

    public static final String toString(int i) {
        return i == 1 ? "BAND_T380" : i == 2 ? "BAND_T410" : i == 3 ? "BAND_450" : i == 4 ? "BAND_480" : i == 5 ? "BAND_710" : i == 6 ? "BAND_750" : i == 7 ? "BAND_T810" : i == 8 ? "BAND_850" : i == 9 ? "BAND_P900" : i == 10 ? "BAND_E900" : i == 11 ? "BAND_R900" : i == 12 ? "BAND_DCS1800" : i == 13 ? "BAND_PCS1900" : i == 14 ? "BAND_ER900" : "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("BAND_T380");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("BAND_T410");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("BAND_450");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("BAND_480");
            i2 |= 4;
        }
        if ((i & 5) == 5) {
            arrayList.add("BAND_710");
            i2 |= 5;
        }
        if ((i & 6) == 6) {
            arrayList.add("BAND_750");
            i2 |= 6;
        }
        if ((i & 7) == 7) {
            arrayList.add("BAND_T810");
            i2 |= 7;
        }
        if ((i & 8) == 8) {
            arrayList.add("BAND_850");
            i2 |= 8;
        }
        if ((i & 9) == 9) {
            arrayList.add("BAND_P900");
            i2 |= 9;
        }
        if ((i & 10) == 10) {
            arrayList.add("BAND_E900");
            i2 |= 10;
        }
        if ((i & 11) == 11) {
            arrayList.add("BAND_R900");
            i2 |= 11;
        }
        if ((i & 12) == 12) {
            arrayList.add("BAND_DCS1800");
            i2 |= 12;
        }
        if ((i & 13) == 13) {
            arrayList.add("BAND_PCS1900");
            i2 |= 13;
        }
        if ((i & 14) == 14) {
            arrayList.add("BAND_ER900");
            i2 |= 14;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
