package B3;

import java.io.Closeable;
import r9.AbstractC2312b;
import r9.D;
import r9.InterfaceC2321k;
import r9.z;

/* JADX INFO: loaded from: classes.dex */
public final class l extends m {
    public final z l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final r9.o f634m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final String f635n;
    public final Closeable o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f636p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public D f637q;

    public l(z zVar, r9.o oVar, String str, Closeable closeable) {
        this.l = zVar;
        this.f634m = oVar;
        this.f635n = str;
        this.o = closeable;
    }

    @Override // B3.m, java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() {
        try {
            this.f636p = true;
            D d = this.f637q;
            if (d != null) {
                N3.d.a(d);
            }
            Closeable closeable = this.o;
            if (closeable != null) {
                N3.d.a(closeable);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // B3.m
    public final C7.a m() {
        return null;
    }

    @Override // B3.m
    public final synchronized InterfaceC2321k v() {
        if (this.f636p) {
            throw new IllegalStateException("closed");
        }
        D d = this.f637q;
        if (d != null) {
            return d;
        }
        D dC = AbstractC2312b.c(this.f634m.k(this.l));
        this.f637q = dC;
        return dC;
    }
}
