package f.a.a.a;

/* JADX INFO: compiled from: NetworkType.java */
/* JADX INFO: loaded from: classes3.dex */
public final class f {
    public static final String a(int i) {
        if (i == 0) {
            return "NETWORK_TYPE_NOREGISTER";
        }
        if (i == 1) {
            return "NETWORK_TYPE_CM";
        }
        if (i == 2) {
            return "NETWORK_TYPE_CU";
        }
        if (i == 3) {
            return "NETWORK_TYPE_CT";
        }
        if (i == 4) {
            return "NETWORK_TYPE_UNKNOW";
        }
        return "0x" + Integer.toHexString(i);
    }
}
