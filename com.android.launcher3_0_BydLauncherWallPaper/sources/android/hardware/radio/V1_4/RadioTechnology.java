package android.hardware.radio.V1_4;

import com.android.internal.telephony.DctConstants;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class RadioTechnology {
    public static final int EDGE = 2;
    public static final int EHRPD = 13;
    public static final int EVDO_0 = 7;
    public static final int EVDO_A = 8;
    public static final int EVDO_B = 12;
    public static final int GPRS = 1;
    public static final int GSM = 16;
    public static final int HSDPA = 9;
    public static final int HSPA = 11;
    public static final int HSPAP = 15;
    public static final int HSUPA = 10;
    public static final int IS95A = 4;
    public static final int IS95B = 5;
    public static final int IWLAN = 18;
    public static final int LTE = 14;
    public static final int LTE_CA = 19;
    public static final int NR = 20;
    public static final int ONE_X_RTT = 6;
    public static final int TD_SCDMA = 17;
    public static final int UMTS = 3;
    public static final int UNKNOWN = 0;

    public static final String toString(int i) {
        return i == 0 ? "UNKNOWN" : i == 1 ? "GPRS" : i == 2 ? "EDGE" : i == 3 ? "UMTS" : i == 4 ? "IS95A" : i == 5 ? "IS95B" : i == 6 ? "ONE_X_RTT" : i == 7 ? "EVDO_0" : i == 8 ? "EVDO_A" : i == 9 ? "HSDPA" : i == 10 ? "HSUPA" : i == 11 ? "HSPA" : i == 12 ? "EVDO_B" : i == 13 ? "EHRPD" : i == 14 ? DctConstants.RAT_NAME_LTE : i == 15 ? "HSPAP" : i == 16 ? "GSM" : i == 17 ? "TD_SCDMA" : i == 18 ? "IWLAN" : i == 19 ? "LTE_CA" : i == 20 ? "NR" : "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("UNKNOWN");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("GPRS");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("EDGE");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("UMTS");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("IS95A");
            i2 |= 4;
        }
        if ((i & 5) == 5) {
            arrayList.add("IS95B");
            i2 |= 5;
        }
        if ((i & 6) == 6) {
            arrayList.add("ONE_X_RTT");
            i2 |= 6;
        }
        if ((i & 7) == 7) {
            arrayList.add("EVDO_0");
            i2 |= 7;
        }
        if ((i & 8) == 8) {
            arrayList.add("EVDO_A");
            i2 |= 8;
        }
        if ((i & 9) == 9) {
            arrayList.add("HSDPA");
            i2 |= 9;
        }
        if ((i & 10) == 10) {
            arrayList.add("HSUPA");
            i2 |= 10;
        }
        if ((i & 11) == 11) {
            arrayList.add("HSPA");
            i2 |= 11;
        }
        if ((i & 12) == 12) {
            arrayList.add("EVDO_B");
            i2 |= 12;
        }
        if ((i & 13) == 13) {
            arrayList.add("EHRPD");
            i2 |= 13;
        }
        if ((i & 14) == 14) {
            arrayList.add(DctConstants.RAT_NAME_LTE);
            i2 |= 14;
        }
        if ((i & 15) == 15) {
            arrayList.add("HSPAP");
            i2 |= 15;
        }
        if ((i & 16) == 16) {
            arrayList.add("GSM");
            i2 |= 16;
        }
        if ((i & 17) == 17) {
            arrayList.add("TD_SCDMA");
            i2 |= 17;
        }
        if ((i & 18) == 18) {
            arrayList.add("IWLAN");
            i2 |= 18;
        }
        if ((i & 19) == 19) {
            arrayList.add("LTE_CA");
            i2 |= 19;
        }
        if ((i & 20) == 20) {
            arrayList.add("NR");
            i2 |= 20;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
