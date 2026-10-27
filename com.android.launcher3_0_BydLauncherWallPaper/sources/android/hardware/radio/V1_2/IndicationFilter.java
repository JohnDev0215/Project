package android.hardware.radio.V1_2;

import android.security.keystore.KeyProperties;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class IndicationFilter {
    public static final int ALL = -1;
    public static final int DATA_CALL_DORMANCY_CHANGED = 4;
    public static final int FULL_NETWORK_STATE = 2;
    public static final int LINK_CAPACITY_ESTIMATE = 8;
    public static final int NONE = 0;
    public static final int PHYSICAL_CHANNEL_CONFIG = 16;
    public static final int SIGNAL_STRENGTH = 1;

    public static final String toString(int i) {
        return i == 0 ? KeyProperties.DIGEST_NONE : i == -1 ? "ALL" : i == 1 ? "SIGNAL_STRENGTH" : i == 2 ? "FULL_NETWORK_STATE" : i == 4 ? "DATA_CALL_DORMANCY_CHANGED" : i == 8 ? "LINK_CAPACITY_ESTIMATE" : i == 16 ? "PHYSICAL_CHANNEL_CONFIG" : "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(KeyProperties.DIGEST_NONE);
        int i2 = -1;
        if ((i & (-1)) == -1) {
            arrayList.add("ALL");
        } else {
            i2 = 0;
        }
        if ((i & 1) == 1) {
            arrayList.add("SIGNAL_STRENGTH");
            i2 |= 1;
        }
        if ((i & 2) == 2) {
            arrayList.add("FULL_NETWORK_STATE");
            i2 |= 2;
        }
        if ((i & 4) == 4) {
            arrayList.add("DATA_CALL_DORMANCY_CHANGED");
            i2 |= 4;
        }
        if ((i & 8) == 8) {
            arrayList.add("LINK_CAPACITY_ESTIMATE");
            i2 |= 8;
        }
        if ((i & 16) == 16) {
            arrayList.add("PHYSICAL_CHANNEL_CONFIG");
            i2 |= 16;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
