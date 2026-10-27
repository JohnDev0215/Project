package m7;

/* JADX INFO: compiled from: NetworkState.java */
/* JADX INFO: loaded from: classes2.dex */
public final class e {
    public static final String a(byte b8) {
        if (b8 == 0) {
            return "NETWORK_NONE";
        }
        if (b8 == 1) {
            return "NETWORK_CONNECTING";
        }
        if (b8 == 2) {
            return "NETWORK_2G_ONLINE";
        }
        if (b8 == 3) {
            return "NETWORK_3G_ONLINE";
        }
        if (b8 == 4) {
            return "NETWORK_4G_ONLINE";
        }
        return "0x" + Integer.toHexString(Byte.toUnsignedInt(b8));
    }
}
