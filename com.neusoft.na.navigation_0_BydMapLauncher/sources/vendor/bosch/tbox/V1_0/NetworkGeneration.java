package vendor.bosch.tbox.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public final class NetworkGeneration {
    public static final int NETWORK_GENERATION_2G = 1;
    public static final int NETWORK_GENERATION_3G = 2;
    public static final int NETWORK_GENERATION_4G = 3;
    public static final int NETWORK_GENERATION_5G = 4;
    public static final int NETWORK_GENERATION_UNKNOW = 0;

    public static final String toString(int i) {
        return i == 0 ? "NETWORK_GENERATION_UNKNOW" : i == 1 ? "NETWORK_GENERATION_2G" : i == 2 ? "NETWORK_GENERATION_3G" : i == 3 ? "NETWORK_GENERATION_4G" : i == 4 ? "NETWORK_GENERATION_5G" : "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("NETWORK_GENERATION_UNKNOW");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("NETWORK_GENERATION_2G");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("NETWORK_GENERATION_3G");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("NETWORK_GENERATION_4G");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("NETWORK_GENERATION_5G");
            i2 |= 4;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
