package vendor.bosch.tbox.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class NetworkState {
    public static final byte NETWORK_2G_ONLINE = 2;
    public static final byte NETWORK_3G_ONLINE = 3;
    public static final byte NETWORK_4G_ONLINE = 4;
    public static final byte NETWORK_CONNECTING = 1;
    public static final byte NETWORK_NONE = 0;

    public static final String toString(byte b) {
        return b == 0 ? "NETWORK_NONE" : b == 1 ? "NETWORK_CONNECTING" : b == 2 ? "NETWORK_2G_ONLINE" : b == 3 ? "NETWORK_3G_ONLINE" : b == 4 ? "NETWORK_4G_ONLINE" : "0x" + Integer.toHexString(Byte.toUnsignedInt(b));
    }

    public static final String dumpBitfield(byte b) {
        byte b2;
        ArrayList arrayList = new ArrayList();
        arrayList.add("NETWORK_NONE");
        if ((b & 1) == 1) {
            arrayList.add("NETWORK_CONNECTING");
            b2 = (byte) 1;
        } else {
            b2 = 0;
        }
        if ((b & 2) == 2) {
            arrayList.add("NETWORK_2G_ONLINE");
            b2 = (byte) (b2 | 2);
        }
        if ((b & 3) == 3) {
            arrayList.add("NETWORK_3G_ONLINE");
            b2 = (byte) (b2 | 3);
        }
        if ((b & 4) == 4) {
            arrayList.add("NETWORK_4G_ONLINE");
            b2 = (byte) (b2 | 4);
        }
        if (b != b2) {
            arrayList.add("0x" + Integer.toHexString(Byte.toUnsignedInt((byte) (b & (~b2)))));
        }
        return String.join(" | ", arrayList);
    }
}
