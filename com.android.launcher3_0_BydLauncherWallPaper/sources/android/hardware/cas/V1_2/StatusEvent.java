package android.hardware.cas.V1_2;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class StatusEvent {
    public static final byte PLUGIN_PHYSICAL_MODULE_CHANGED = 0;
    public static final byte PLUGIN_SESSION_NUMBER_CHANGED = 1;

    public static final String toString(byte b) {
        return b == 0 ? "PLUGIN_PHYSICAL_MODULE_CHANGED" : b == 1 ? "PLUGIN_SESSION_NUMBER_CHANGED" : "0x" + Integer.toHexString(Byte.toUnsignedInt(b));
    }

    public static final String dumpBitfield(byte b) {
        byte b2;
        ArrayList arrayList = new ArrayList();
        arrayList.add("PLUGIN_PHYSICAL_MODULE_CHANGED");
        if ((b & 1) == 1) {
            arrayList.add("PLUGIN_SESSION_NUMBER_CHANGED");
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
