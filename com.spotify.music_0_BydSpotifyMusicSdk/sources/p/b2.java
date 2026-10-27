package p;

/* JADX INFO: loaded from: classes.dex */
public final class b2 {
    public static final b2 c;
    public static final b2 d;
    public final boolean a;
    public final Throwable b;

    static {
        if (g2.l) {
            d = null;
            c = null;
        } else {
            d = new b2(null, false);
            c = new b2(null, true);
        }
    }

    public b2(Throwable th, boolean z) {
        this.a = z;
        this.b = th;
    }
}
