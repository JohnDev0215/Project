package f.a.a.a;

/* JADX INFO: compiled from: NetworkState.java */
/* JADX INFO: loaded from: classes3.dex */
public final class e {
    public static final String a(byte b2) {
        if (b2 == 0) {
            return "NETWORK_NONE";
        }
        if (b2 == 1) {
            return "NETWORK_CONNECTING";
        }
        if (b2 == 2) {
            return "NETWORK_2G_ONLINE";
        }
        if (b2 == 3) {
            return "NETWORK_3G_ONLINE";
        }
        if (b2 == 4) {
            return "NETWORK_4G_ONLINE";
        }
        return "0x" + Integer.toHexString(Byte.toUnsignedInt(b2));
    }
}
