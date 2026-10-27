package p;

import java.io.Closeable;

/* JADX INFO: loaded from: classes.dex */
public final class du0 extends s31 {
    public final os1 c;
    public final hu0 e;
    public final String k;
    public final Closeable l;
    public boolean m;
    public w02 n;

    public du0(os1 os1Var, hu0 hu0Var, String str, Closeable closeable) {
        this.c = os1Var;
        this.e = hu0Var;
        this.k = str;
        this.l = closeable;
    }

    @Override // p.s31
    public final ci1 b() {
        return null;
    }

    @Override // p.s31
    public final synchronized ko c() {
        if (!(!this.m)) {
            throw new IllegalStateException("closed".toString());
        }
        w02 w02Var = this.n;
        if (w02Var != null) {
            return w02Var;
        }
        w02 w02VarD = v03.d(this.e.l(this.c));
        this.n = w02VarD;
        return w02VarD;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() {
        try {
            this.m = true;
            w02 w02Var = this.n;
            if (w02Var != null) {
                i.a(w02Var);
            }
            Closeable closeable = this.l;
            if (closeable != null) {
                i.a(closeable);
            }
        } catch (Throwable th) {
            throw th;
        }
    }
}
