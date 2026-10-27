package f.a.a.a;

/* JADX INFO: compiled from: Apn2Status.java */
/* JADX INFO: loaded from: classes3.dex */
public final class a {
    public static final String a(byte b2) {
        if (b2 == 0) {
            return "OPEN";
        }
        if (b2 == 1) {
            return "CLOSE";
        }
        return "0x" + Integer.toHexString(Byte.toUnsignedInt(b2));
    }
}
