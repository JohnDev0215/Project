package p;

/* JADX INFO: loaded from: classes.dex */
public final class d1 {
    public static final d1 c;
    public static final d1 d;
    public final boolean a;
    public final Throwable b;

    static {
        if (j1.l) {
            d = null;
            c = null;
        } else {
            d = new d1(null, false);
            c = new d1(null, true);
        }
    }

    public d1(Throwable th, boolean z) {
        this.a = z;
        this.b = th;
    }
}
