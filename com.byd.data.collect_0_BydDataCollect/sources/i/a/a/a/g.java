package i.a.a.a;

/* JADX INFO: compiled from: NetworkType.java */
/* JADX INFO: loaded from: classes.dex */
public final class g {
    public static final String a(int i2) {
        if (i2 == 0) {
            return "NETWORK_TYPE_NOREGISTER";
        }
        if (i2 == 1) {
            return "NETWORK_TYPE_CM";
        }
        if (i2 == 2) {
            return "NETWORK_TYPE_CU";
        }
        if (i2 == 3) {
            return "NETWORK_TYPE_CT";
        }
        if (i2 == 4) {
            return "NETWORK_TYPE_UNKNOW";
        }
        return "0x" + Integer.toHexString(i2);
    }
}
