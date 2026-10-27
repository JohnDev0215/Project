package h5;

/* JADX INFO: renamed from: h5.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1375a {
    public static final C1375a c;
    public static final C1375a d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f15540a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final RuntimeException f15541b;

    static {
        if (p.o) {
            d = null;
            c = null;
        } else {
            d = new C1375a(false, null);
            c = new C1375a(true, null);
        }
    }

    public C1375a(boolean z6, RuntimeException runtimeException) {
        this.f15540a = z6;
        this.f15541b = runtimeException;
    }
}
