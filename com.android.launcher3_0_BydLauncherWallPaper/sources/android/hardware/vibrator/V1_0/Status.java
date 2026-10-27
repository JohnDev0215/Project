package android.hardware.vibrator.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class Status {
    public static final int BAD_VALUE = 2;
    public static final int OK = 0;
    public static final int UNKNOWN_ERROR = 1;
    public static final int UNSUPPORTED_OPERATION = 3;

    public static final String toString(int i) {
        return i == 0 ? "OK" : i == 1 ? "UNKNOWN_ERROR" : i == 2 ? "BAD_VALUE" : i == 3 ? "UNSUPPORTED_OPERATION" : "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("OK");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("UNKNOWN_ERROR");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("BAD_VALUE");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("UNSUPPORTED_OPERATION");
            i2 |= 3;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
