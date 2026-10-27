package android.hardware.radio.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class CarrierMatchType {
    public static final int ALL = 0;
    public static final int GID1 = 3;
    public static final int GID2 = 4;
    public static final int IMSI_PREFIX = 2;
    public static final int SPN = 1;

    public static final String toString(int i) {
        return i == 0 ? "ALL" : i == 1 ? "SPN" : i == 2 ? "IMSI_PREFIX" : i == 3 ? "GID1" : i == 4 ? "GID2" : "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("ALL");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("SPN");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("IMSI_PREFIX");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("GID1");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("GID2");
            i2 |= 4;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
