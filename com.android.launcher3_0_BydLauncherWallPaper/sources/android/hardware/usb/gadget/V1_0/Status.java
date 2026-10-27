package android.hardware.usb.gadget.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class Status {
    public static final int CONFIGURATION_NOT_SUPPORTED = 4;
    public static final int ERROR = 1;
    public static final int FUNCTIONS_APPLIED = 2;
    public static final int FUNCTIONS_NOT_APPLIED = 3;
    public static final int SUCCESS = 0;

    public static final String toString(int i) {
        return i == 0 ? "SUCCESS" : i == 1 ? "ERROR" : i == 2 ? "FUNCTIONS_APPLIED" : i == 3 ? "FUNCTIONS_NOT_APPLIED" : i == 4 ? "CONFIGURATION_NOT_SUPPORTED" : "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("SUCCESS");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("ERROR");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("FUNCTIONS_APPLIED");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("FUNCTIONS_NOT_APPLIED");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("CONFIGURATION_NOT_SUPPORTED");
            i2 |= 4;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
