package m7;

/* JADX INFO: compiled from: Apn2Status.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a {
    public static final String a(byte b8) {
        if (b8 == 0) {
            return "OPEN";
        }
        if (b8 == 1) {
            return "CLOSE";
        }
        return "0x" + Integer.toHexString(Byte.toUnsignedInt(b8));
    }
}
