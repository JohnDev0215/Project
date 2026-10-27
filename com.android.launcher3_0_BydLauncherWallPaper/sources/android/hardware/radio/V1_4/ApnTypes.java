package android.hardware.radio.V1_4;

import android.security.keystore.KeyProperties;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class ApnTypes {
    public static final int ALL = 1023;
    public static final int CBS = 128;
    public static final int DEFAULT = 1;
    public static final int DUN = 8;
    public static final int EMERGENCY = 512;
    public static final int FOTA = 32;
    public static final int HIPRI = 16;
    public static final int IA = 256;
    public static final int IMS = 64;
    public static final int MCX = 1024;
    public static final int MMS = 2;
    public static final int NONE = 0;
    public static final int SUPL = 4;

    public static final String toString(int i) {
        return i == 0 ? KeyProperties.DIGEST_NONE : i == 1 ? "DEFAULT" : i == 2 ? "MMS" : i == 4 ? "SUPL" : i == 8 ? "DUN" : i == 16 ? "HIPRI" : i == 32 ? "FOTA" : i == 64 ? "IMS" : i == 128 ? "CBS" : i == 256 ? "IA" : i == 512 ? "EMERGENCY" : i == 1023 ? "ALL" : i == 1024 ? "MCX" : "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(KeyProperties.DIGEST_NONE);
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("DEFAULT");
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("MMS");
            i2 |= 2;
        }
        if ((i & 4) == 4) {
            arrayList.add("SUPL");
            i2 |= 4;
        }
        if ((i & 8) == 8) {
            arrayList.add("DUN");
            i2 |= 8;
        }
        if ((i & 16) == 16) {
            arrayList.add("HIPRI");
            i2 |= 16;
        }
        if ((i & 32) == 32) {
            arrayList.add("FOTA");
            i2 |= 32;
        }
        if ((i & 64) == 64) {
            arrayList.add("IMS");
            i2 |= 64;
        }
        if ((i & 128) == 128) {
            arrayList.add("CBS");
            i2 |= 128;
        }
        if ((i & 256) == 256) {
            arrayList.add("IA");
            i2 |= 256;
        }
        if ((i & 512) == 512) {
            arrayList.add("EMERGENCY");
            i2 |= 512;
        }
        if ((i & 1023) == 1023) {
            arrayList.add("ALL");
            i2 |= 1023;
        }
        if ((i & 1024) == 1024) {
            arrayList.add("MCX");
            i2 |= 1024;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
