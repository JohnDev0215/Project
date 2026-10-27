package android.hardware.radio.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class SsTeleserviceType {
    public static final int ALL_DATA_TELESERVICES = 3;
    public static final int ALL_TELESERVICES_EXCEPT_SMS = 5;
    public static final int ALL_TELESEVICES = 1;
    public static final int ALL_TELE_AND_BEARER_SERVICES = 0;
    public static final int SMS_SERVICES = 4;
    public static final int TELEPHONY = 2;

    public static final String toString(int i) {
        return i == 0 ? "ALL_TELE_AND_BEARER_SERVICES" : i == 1 ? "ALL_TELESEVICES" : i == 2 ? "TELEPHONY" : i == 3 ? "ALL_DATA_TELESERVICES" : i == 4 ? "SMS_SERVICES" : i == 5 ? "ALL_TELESERVICES_EXCEPT_SMS" : "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("ALL_TELE_AND_BEARER_SERVICES");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("ALL_TELESEVICES");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("TELEPHONY");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("ALL_DATA_TELESERVICES");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("SMS_SERVICES");
            i2 |= 4;
        }
        if ((i & 5) == 5) {
            arrayList.add("ALL_TELESERVICES_EXCEPT_SMS");
            i2 |= 5;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
