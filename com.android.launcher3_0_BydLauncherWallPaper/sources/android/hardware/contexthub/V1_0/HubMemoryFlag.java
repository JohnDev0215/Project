package android.hardware.contexthub.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class HubMemoryFlag {
    public static final int EXEC = 4;
    public static final int READ = 1;
    public static final int WRITE = 2;

    public static final String toString(int i) {
        return i == 1 ? "READ" : i == 2 ? "WRITE" : i == 4 ? "EXEC" : "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("READ");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("WRITE");
            i2 |= 2;
        }
        if ((i & 4) == 4) {
            arrayList.add("EXEC");
            i2 |= 4;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
