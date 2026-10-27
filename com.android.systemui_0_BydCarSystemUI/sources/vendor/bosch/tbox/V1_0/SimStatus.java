package vendor.bosch.tbox.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class SimStatus {
    public static final int ABSENT = 1;
    public static final int FAULT = 3;
    public static final int SIM_READY = 2;
    public static final int UNKNOWN = 0;

    public static final String toString(int i) {
        return i == 0 ? "UNKNOWN" : i == 1 ? "ABSENT" : i == 2 ? "SIM_READY" : i == 3 ? "FAULT" : "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("UNKNOWN");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("ABSENT");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("SIM_READY");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("FAULT");
            i2 |= 3;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
