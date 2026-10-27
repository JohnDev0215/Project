package vendor.bosch.tbox.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public final class ResultCode {
    public static final int GENERIC_ERROR = 1;
    public static final int HALSERVICE_INTERNAL_ERROR = 4;
    public static final int SUCCESS = 0;
    public static final int TBOX_ABSENT = 2;
    public static final int TBOX_UNAUTHORIZED = 3;

    public static final String toString(int i) {
        return i == 0 ? "SUCCESS" : i == 1 ? "GENERIC_ERROR" : i == 2 ? "TBOX_ABSENT" : i == 3 ? "TBOX_UNAUTHORIZED" : i == 4 ? "HALSERVICE_INTERNAL_ERROR" : "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("SUCCESS");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("GENERIC_ERROR");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("TBOX_ABSENT");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("TBOX_UNAUTHORIZED");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("HALSERVICE_INTERNAL_ERROR");
            i2 |= 4;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
