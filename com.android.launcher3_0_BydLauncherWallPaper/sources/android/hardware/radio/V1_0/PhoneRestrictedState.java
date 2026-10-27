package android.hardware.radio.V1_0;

import android.security.keystore.KeyProperties;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class PhoneRestrictedState {
    public static final int CS_ALL = 4;
    public static final int CS_EMERGENCY = 1;
    public static final int CS_NORMAL = 2;
    public static final int NONE = 0;
    public static final int PS_ALL = 16;

    public static final String toString(int i) {
        return i == 0 ? KeyProperties.DIGEST_NONE : i == 1 ? "CS_EMERGENCY" : i == 2 ? "CS_NORMAL" : i == 4 ? "CS_ALL" : i == 16 ? "PS_ALL" : "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(KeyProperties.DIGEST_NONE);
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("CS_EMERGENCY");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("CS_NORMAL");
            i2 |= 2;
        }
        if ((i & 4) == 4) {
            arrayList.add("CS_ALL");
            i2 |= 4;
        }
        if ((i & 16) == 16) {
            arrayList.add("PS_ALL");
            i2 |= 16;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
