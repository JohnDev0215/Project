package m7;

/* JADX INFO: compiled from: NetworkType.java */
/* JADX INFO: loaded from: classes2.dex */
public final class f {
    public static final String a(int i8) {
        if (i8 == 0) {
            return "NETWORK_TYPE_NOREGISTER";
        }
        if (i8 == 1) {
            return "NETWORK_TYPE_CM";
        }
        if (i8 == 2) {
            return "NETWORK_TYPE_CU";
        }
        if (i8 == 3) {
            return "NETWORK_TYPE_CT";
        }
        if (i8 == 4) {
            return "NETWORK_TYPE_UNKNOW";
        }
        return "0x" + Integer.toHexString(i8);
    }
}
