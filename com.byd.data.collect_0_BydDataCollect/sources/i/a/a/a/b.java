package i.a.a.a;

/* JADX INFO: compiled from: Apn2Status.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {
    public static final String a(byte b2) {
        if (b2 == 0) {
            return "OPEN";
        }
        if (b2 == 1) {
            return "CLOSE";
        }
        return "0x" + Integer.toHexString(a.a(b2));
    }
}
