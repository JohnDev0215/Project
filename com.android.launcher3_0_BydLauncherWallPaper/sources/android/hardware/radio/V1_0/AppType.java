package android.hardware.radio.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class AppType {
    public static final int CSIM = 4;
    public static final int ISIM = 5;
    public static final int RUIM = 3;
    public static final int SIM = 1;
    public static final int UNKNOWN = 0;
    public static final int USIM = 2;

    public static final String toString(int i) {
        return i == 0 ? "UNKNOWN" : i == 1 ? "SIM" : i == 2 ? "USIM" : i == 3 ? "RUIM" : i == 4 ? "CSIM" : i == 5 ? "ISIM" : "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("UNKNOWN");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("SIM");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("USIM");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("RUIM");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("CSIM");
            i2 |= 4;
        }
        if ((i & 5) == 5) {
            arrayList.add("ISIM");
            i2 |= 5;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
