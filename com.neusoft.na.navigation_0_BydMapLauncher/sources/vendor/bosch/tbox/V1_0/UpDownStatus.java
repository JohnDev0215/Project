package vendor.bosch.tbox.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public final class UpDownStatus {
    public static final byte IN = 1;
    public static final byte IN_AND_OUT = 3;
    public static final byte NONE = 0;
    public static final byte OUT = 2;

    public static final String toString(byte b) {
        return b == 0 ? "NONE" : b == 1 ? "IN" : b == 2 ? "OUT" : b == 3 ? "IN_AND_OUT" : "0x" + Integer.toHexString(Byte.toUnsignedInt(b));
    }

    public static final String dumpBitfield(byte b) {
        byte b2;
        ArrayList arrayList = new ArrayList();
        arrayList.add("NONE");
        if ((b & 1) == 1) {
            arrayList.add("IN");
            b2 = (byte) 1;
        } else {
            b2 = 0;
        }
        if ((b & 2) == 2) {
            arrayList.add("OUT");
            b2 = (byte) (b2 | 2);
        }
        if ((b & 3) == 3) {
            arrayList.add("IN_AND_OUT");
            b2 = (byte) (b2 | 3);
        }
        if (b != b2) {
            arrayList.add("0x" + Integer.toHexString(Byte.toUnsignedInt((byte) (b & (~b2)))));
        }
        return String.join(" | ", arrayList);
    }
}
