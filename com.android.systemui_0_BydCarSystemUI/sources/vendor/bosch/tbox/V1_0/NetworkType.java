package vendor.bosch.tbox.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class NetworkType {
    public static final int NETWORK_TYPE_CM = 1;
    public static final int NETWORK_TYPE_CT = 3;
    public static final int NETWORK_TYPE_CU = 2;
    public static final int NETWORK_TYPE_NOREGISTER = 0;
    public static final int NETWORK_TYPE_UNKNOW = 4;

    public static final String toString(int i) {
        return i == 0 ? "NETWORK_TYPE_NOREGISTER" : i == 1 ? "NETWORK_TYPE_CM" : i == 2 ? "NETWORK_TYPE_CU" : i == 3 ? "NETWORK_TYPE_CT" : i == 4 ? "NETWORK_TYPE_UNKNOW" : "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("NETWORK_TYPE_NOREGISTER");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("NETWORK_TYPE_CM");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("NETWORK_TYPE_CU");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("NETWORK_TYPE_CT");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("NETWORK_TYPE_UNKNOW");
            i2 |= 4;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
