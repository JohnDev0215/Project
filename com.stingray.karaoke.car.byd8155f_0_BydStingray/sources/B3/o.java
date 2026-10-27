package B3;

import r9.InterfaceC2321k;
import r9.v;

/* JADX INFO: loaded from: classes.dex */
public final class o extends m {
    public final C7.a l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f638m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final InterfaceC2321k f639n;

    public o(InterfaceC2321k interfaceC2321k, C7.a aVar) {
        this.l = aVar;
        this.f639n = interfaceC2321k;
    }

    @Override // B3.m, java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() {
        this.f638m = true;
        InterfaceC2321k interfaceC2321k = this.f639n;
        if (interfaceC2321k != null) {
            N3.d.a(interfaceC2321k);
        }
    }

    @Override // B3.m
    public final C7.a m() {
        return this.l;
    }

    @Override // B3.m
    public final synchronized InterfaceC2321k v() {
        InterfaceC2321k interfaceC2321k;
        try {
            if (this.f638m) {
                throw new IllegalStateException("closed");
            }
            interfaceC2321k = this.f639n;
            if (interfaceC2321k == null) {
                v vVar = r9.o.f21939a;
                A7.m.c(null);
                vVar.k(null);
                throw null;
            }
        } catch (Throwable th) {
            throw th;
        }
        return interfaceC2321k;
    }
}
