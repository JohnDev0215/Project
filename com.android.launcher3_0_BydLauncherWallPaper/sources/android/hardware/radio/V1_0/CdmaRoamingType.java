package android.hardware.radio.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class CdmaRoamingType {
    public static final int AFFILIATED_ROAM = 1;
    public static final int ANY_ROAM = 2;
    public static final int HOME_NETWORK = 0;

    public static final String toString(int i) {
        return i == 0 ? "HOME_NETWORK" : i == 1 ? "AFFILIATED_ROAM" : i == 2 ? "ANY_ROAM" : "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("HOME_NETWORK");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("AFFILIATED_ROAM");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("ANY_ROAM");
            i2 |= 2;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
