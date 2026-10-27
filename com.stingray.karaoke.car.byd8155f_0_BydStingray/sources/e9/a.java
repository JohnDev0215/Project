package e9;

import A7.m;
import P.C;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import r9.C2319i;
import r9.InterfaceC2321k;
import r9.J;
import r9.L;

/* JADX INFO: loaded from: classes.dex */
public final class a implements J {
    public boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2321k f14490m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C f14491n;
    public final /* synthetic */ r9.C o;

    public a(InterfaceC2321k interfaceC2321k, C c, r9.C c10) {
        this.f14490m = interfaceC2321k;
        this.f14491n = c;
        this.o = c10;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        if (!this.l && !d9.b.h(this, TimeUnit.MILLISECONDS)) {
            this.l = true;
            this.f14491n.i();
        }
        this.f14490m.close();
    }

    @Override // r9.J
    public final L e() {
        return this.f14490m.e();
    }

    @Override // r9.J
    public final long p(C2319i c2319i, long j3) throws Throwable {
        m.f(c2319i, "sink");
        try {
            long jP = this.f14490m.p(c2319i, j3);
            r9.C c = this.o;
            if (jP != -1) {
                c2319i.i(c.f21895m, c2319i.f21932m - jP, jP);
                c.b();
                return jP;
            }
            if (!this.l) {
                this.l = true;
                c.close();
            }
            return -1L;
        } catch (IOException e10) {
            if (!this.l) {
                this.l = true;
                this.f14491n.i();
            }
            throw e10;
        }
    }
}
