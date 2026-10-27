package defpackage;

/* JADX INFO: compiled from: NetworkState.java */
/* JADX INFO: loaded from: classes.dex */
public final class wm {
    public static final String a(byte b) {
        if (b == 0) {
            return "NETWORK_NONE";
        }
        if (b == 1) {
            return "NETWORK_CONNECTING";
        }
        if (b == 2) {
            return "NETWORK_2G_ONLINE";
        }
        if (b == 3) {
            return "NETWORK_3G_ONLINE";
        }
        if (b == 4) {
            return "NETWORK_4G_ONLINE";
        }
        return "0x" + Integer.toHexString(Byte.toUnsignedInt(b));
    }
}
