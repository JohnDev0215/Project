package android.hardware.radio.V1_5;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class RadioAccessNetworks {
    public static final int CDMA2000 = 5;
    public static final int EUTRAN = 3;
    public static final int GERAN = 1;
    public static final int NGRAN = 4;
    public static final int UNKNOWN = 0;
    public static final int UTRAN = 2;

    public static final String toString(int i) {
        return i == 1 ? "GERAN" : i == 2 ? "UTRAN" : i == 3 ? "EUTRAN" : i == 0 ? "UNKNOWN" : i == 4 ? "NGRAN" : i == 5 ? "CDMA2000" : "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("GERAN");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("UTRAN");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("EUTRAN");
            i2 |= 3;
        }
        arrayList.add("UNKNOWN");
        if ((i & 4) == 4) {
            arrayList.add("NGRAN");
            i2 |= 4;
        }
        if ((i & 5) == 5) {
            arrayList.add("CDMA2000");
            i2 |= 5;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
