package android.hardware.radio.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class SapApduType {
    public static final int APDU = 0;
    public static final int APDU7816 = 1;

    public static final String toString(int i) {
        return i == 0 ? "APDU" : i == 1 ? "APDU7816" : "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("APDU");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("APDU7816");
        } else {
            i2 = 0;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
