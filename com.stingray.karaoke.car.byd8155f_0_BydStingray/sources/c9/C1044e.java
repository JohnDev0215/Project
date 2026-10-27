package c9;

/* JADX INFO: renamed from: c9.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1044e extends r9.p {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C1045f f12556m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ P.C f12557n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1044e(C1045f c1045f, P.C c, r9.H h10) {
        super(h10);
        this.f12556m = c1045f;
        this.f12557n = c;
    }

    @Override // r9.p, r9.H, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        C1045f c1045f = this.f12556m;
        P.C c = this.f12557n;
        synchronized (c1045f) {
            if (c.f5202a) {
                return;
            }
            c.f5202a = true;
            super.close();
            ((A2.c) this.f12557n.f5203b).c();
        }
    }
}
