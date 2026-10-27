package android.hardware.radio.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class CdmaSmsNumberType {
    public static final int ABBREVIATED = 6;
    public static final int ALPHANUMERIC = 5;
    public static final int INTERNATIONAL_OR_DATA_IP = 1;
    public static final int NATIONAL_OR_INTERNET_MAIL = 2;
    public static final int NETWORK = 3;
    public static final int RESERVED_7 = 7;
    public static final int SUBSCRIBER = 4;
    public static final int UNKNOWN = 0;

    public static final String toString(int i) {
        return i == 0 ? "UNKNOWN" : i == 1 ? "INTERNATIONAL_OR_DATA_IP" : i == 2 ? "NATIONAL_OR_INTERNET_MAIL" : i == 3 ? "NETWORK" : i == 4 ? "SUBSCRIBER" : i == 5 ? "ALPHANUMERIC" : i == 6 ? "ABBREVIATED" : i == 7 ? "RESERVED_7" : "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("UNKNOWN");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("INTERNATIONAL_OR_DATA_IP");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("NATIONAL_OR_INTERNET_MAIL");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("NETWORK");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("SUBSCRIBER");
            i2 |= 4;
        }
        if ((i & 5) == 5) {
            arrayList.add("ALPHANUMERIC");
            i2 |= 5;
        }
        if ((i & 6) == 6) {
            arrayList.add("ABBREVIATED");
            i2 |= 6;
        }
        if ((i & 7) == 7) {
            arrayList.add("RESERVED_7");
            i2 |= 7;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
