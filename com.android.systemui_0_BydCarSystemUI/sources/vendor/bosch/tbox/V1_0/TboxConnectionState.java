package vendor.bosch.tbox.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class TboxConnectionState {
    public static final int ABNORMAL = 2;
    public static final int ABSENT = 1;
    public static final int CONNECTED = 4;
    public static final int CONNECTING = 3;
    public static final int UNKNOW = 0;

    public static final String toString(int i) {
        return i == 0 ? "UNKNOW" : i == 1 ? "ABSENT" : i == 2 ? "ABNORMAL" : i == 3 ? "CONNECTING" : i == 4 ? "CONNECTED" : "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("UNKNOW");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("ABSENT");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("ABNORMAL");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("CONNECTING");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("CONNECTED");
            i2 |= 4;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
