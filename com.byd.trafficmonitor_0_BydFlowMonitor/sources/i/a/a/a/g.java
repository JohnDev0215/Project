package i.a.a.a;

/* JADX INFO: compiled from: UpDownStatus.java */
/* JADX INFO: loaded from: classes.dex */
public final class g {
    public static final String a(byte b2) {
        if (b2 == 0) {
            return "NONE";
        }
        if (b2 == 1) {
            return "IN";
        }
        if (b2 == 2) {
            return "OUT";
        }
        if (b2 == 3) {
            return "IN_AND_OUT";
        }
        return "0x" + Integer.toHexString(Byte.toUnsignedInt(b2));
    }
}
