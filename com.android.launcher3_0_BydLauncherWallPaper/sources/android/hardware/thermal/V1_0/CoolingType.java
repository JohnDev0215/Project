package android.hardware.thermal.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class CoolingType {
    public static final int FAN_RPM = 0;

    public static final String toString(int i) {
        return i == 0 ? "FAN_RPM" : "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("FAN_RPM");
        if (i != 0) {
            arrayList.add("0x" + Integer.toHexString(i & (-1)));
        }
        return String.join(" | ", arrayList);
    }
}
