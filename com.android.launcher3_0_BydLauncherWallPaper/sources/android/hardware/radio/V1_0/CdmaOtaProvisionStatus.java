package android.hardware.radio.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class CdmaOtaProvisionStatus {
    public static final int A_KEY_EXCHANGED = 2;
    public static final int COMMITTED = 8;
    public static final int IMSI_DOWNLOADED = 6;
    public static final int MDN_DOWNLOADED = 5;
    public static final int NAM_DOWNLOADED = 4;
    public static final int OTAPA_ABORTED = 11;
    public static final int OTAPA_STARTED = 9;
    public static final int OTAPA_STOPPED = 10;
    public static final int PRL_DOWNLOADED = 7;
    public static final int SPC_RETRIES_EXCEEDED = 1;
    public static final int SPL_UNLOCKED = 0;
    public static final int SSD_UPDATED = 3;

    public static final String toString(int i) {
        return i == 0 ? "SPL_UNLOCKED" : i == 1 ? "SPC_RETRIES_EXCEEDED" : i == 2 ? "A_KEY_EXCHANGED" : i == 3 ? "SSD_UPDATED" : i == 4 ? "NAM_DOWNLOADED" : i == 5 ? "MDN_DOWNLOADED" : i == 6 ? "IMSI_DOWNLOADED" : i == 7 ? "PRL_DOWNLOADED" : i == 8 ? "COMMITTED" : i == 9 ? "OTAPA_STARTED" : i == 10 ? "OTAPA_STOPPED" : i == 11 ? "OTAPA_ABORTED" : "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("SPL_UNLOCKED");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("SPC_RETRIES_EXCEEDED");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("A_KEY_EXCHANGED");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("SSD_UPDATED");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("NAM_DOWNLOADED");
            i2 |= 4;
        }
        if ((i & 5) == 5) {
            arrayList.add("MDN_DOWNLOADED");
            i2 |= 5;
        }
        if ((i & 6) == 6) {
            arrayList.add("IMSI_DOWNLOADED");
            i2 |= 6;
        }
        if ((i & 7) == 7) {
            arrayList.add("PRL_DOWNLOADED");
            i2 |= 7;
        }
        if ((i & 8) == 8) {
            arrayList.add("COMMITTED");
            i2 |= 8;
        }
        if ((i & 9) == 9) {
            arrayList.add("OTAPA_STARTED");
            i2 |= 9;
        }
        if ((i & 10) == 10) {
            arrayList.add("OTAPA_STOPPED");
            i2 |= 10;
        }
        if ((i & 11) == 11) {
            arrayList.add("OTAPA_ABORTED");
            i2 |= 11;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
