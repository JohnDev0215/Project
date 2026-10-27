package vendor.bosch.tbox.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class Apn2Status {
    public static final byte CLOSE = 1;
    public static final byte OPEN = 0;

    public static final String toString(byte b) {
        return b == 0 ? "OPEN" : b == 1 ? "CLOSE" : "0x" + Integer.toHexString(Byte.toUnsignedInt(b));
    }

    public static final String dumpBitfield(byte b) {
        byte b2;
        ArrayList arrayList = new ArrayList();
        arrayList.add("OPEN");
        if ((b & 1) == 1) {
            arrayList.add("CLOSE");
            b2 = (byte) 1;
        } else {
            b2 = 0;
        }
        if (b != b2) {
            arrayList.add("0x" + Integer.toHexString(Byte.toUnsignedInt((byte) (b & (~b2)))));
        }
        return String.join(" | ", arrayList);
    }
}
