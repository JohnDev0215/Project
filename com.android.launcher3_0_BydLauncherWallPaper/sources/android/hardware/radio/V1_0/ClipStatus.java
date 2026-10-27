package android.hardware.radio.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class ClipStatus {
    public static final int CLIP_PROVISIONED = 0;
    public static final int CLIP_UNPROVISIONED = 1;
    public static final int UNKNOWN = 2;

    public static final String toString(int i) {
        return i == 0 ? "CLIP_PROVISIONED" : i == 1 ? "CLIP_UNPROVISIONED" : i == 2 ? "UNKNOWN" : "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("CLIP_PROVISIONED");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("CLIP_UNPROVISIONED");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("UNKNOWN");
            i2 |= 2;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
