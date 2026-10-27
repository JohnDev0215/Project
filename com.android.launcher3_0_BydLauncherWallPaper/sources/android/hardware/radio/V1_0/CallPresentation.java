package android.hardware.radio.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class CallPresentation {
    public static final int ALLOWED = 0;
    public static final int PAYPHONE = 3;
    public static final int RESTRICTED = 1;
    public static final int UNKNOWN = 2;

    public static final String toString(int i) {
        return i == 0 ? "ALLOWED" : i == 1 ? "RESTRICTED" : i == 2 ? "UNKNOWN" : i == 3 ? "PAYPHONE" : "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("ALLOWED");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("RESTRICTED");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("UNKNOWN");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("PAYPHONE");
            i2 |= 3;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
