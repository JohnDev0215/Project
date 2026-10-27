package android.hardware.radio.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class SapDisconnectType {
    public static final int GRACEFUL = 0;
    public static final int IMMEDIATE = 1;

    public static final String toString(int i) {
        return i == 0 ? "GRACEFUL" : i == 1 ? "IMMEDIATE" : "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("GRACEFUL");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("IMMEDIATE");
        } else {
            i2 = 0;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
