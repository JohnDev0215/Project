package p;

/* JADX INFO: loaded from: classes.dex */
public final class ee2 extends s31 {
    public final ci1 c;
    public boolean e;
    public final ko k;

    public ee2(ko koVar, ci1 ci1Var) {
        this.c = ci1Var;
        this.k = koVar;
    }

    @Override // p.s31
    public final ci1 b() {
        return this.c;
    }

    @Override // p.s31
    public final synchronized ko c() {
        ko koVar;
        try {
            if (!(!this.e)) {
                throw new IllegalStateException("closed".toString());
            }
            koVar = this.k;
            if (koVar == null) {
                c81 c81Var = hu0.a;
                v03.h(null);
                c81Var.l(null);
                throw null;
            }
        } catch (Throwable th) {
            throw th;
        }
        return koVar;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() {
        this.e = true;
        ko koVar = this.k;
        if (koVar != null) {
            i.a(koVar);
        }
    }
}
