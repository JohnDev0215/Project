package h.a.a.a;

/* JADX INFO: compiled from: DomainECU.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static final String a(int i) {
        if (i == 0) {
            return "ALL";
        }
        if (i == 1) {
            return "IHU";
        }
        if (i == 2) {
            return "TBOX";
        }
        if (i == 3) {
            return "NAVI";
        }
        if (i == 4) {
            return "BLUTO";
        }
        if (i == 5) {
            return "END";
        }
        return "0x" + Integer.toHexString(i);
    }
}
