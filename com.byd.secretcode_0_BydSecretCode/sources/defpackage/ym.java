package defpackage;

/* JADX INFO: compiled from: UpDownStatus.java */
/* JADX INFO: loaded from: classes.dex */
public final class ym {
    public static final String a(byte b) {
        if (b == 0) {
            return "NONE";
        }
        if (b == 1) {
            return "IN";
        }
        if (b == 2) {
            return "OUT";
        }
        if (b == 3) {
            return "IN_AND_OUT";
        }
        return "0x" + Integer.toHexString(Byte.toUnsignedInt(b));
    }
}
