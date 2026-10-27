package defpackage;

/* JADX INFO: compiled from: Apn2Status.java */
/* JADX INFO: loaded from: classes.dex */
public final class sm {
    public static final String a(byte b) {
        if (b == 0) {
            return "OPEN";
        }
        if (b == 1) {
            return "CLOSE";
        }
        return "0x" + Integer.toHexString(Byte.toUnsignedInt(b));
    }
}
