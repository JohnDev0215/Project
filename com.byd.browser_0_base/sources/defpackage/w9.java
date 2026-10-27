package defpackage;

import java.util.concurrent.Executor;

/* JADX INFO: compiled from: AutoCloseableRouter.java */
/* JADX INFO: loaded from: classes3.dex */
public final class w9 implements vc2 {
    public final vc2 e;
    public final Executor f;
    public final Exception g = new Exception("AutocloseableRouter allocated at:");
    public boolean h;

    /* JADX INFO: compiled from: AutoCloseableRouter.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            w9.this.close();
        }
    }

    public w9(k00 k00Var, xc2 xc2Var) {
        this.e = xc2Var;
        this.f = ji0.a(k00Var);
    }

    @Override // defpackage.hh1
    public final boolean R0(gq0 gq0Var, gh1 gh1Var) {
        return this.e.R0(gq0Var, gh1Var);
    }

    @Override // defpackage.gh1
    public final boolean a(gq0 gq0Var) {
        return this.e.a(gq0Var);
    }

    @Override // defpackage.gh1, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.e.close();
        this.h = true;
    }

    @Override // defpackage.vt0
    public final ch1 f0() {
        return this.e.f0();
    }

    public final void finalize() throws Throwable {
        if (this.h) {
            super.finalize();
        } else {
            this.f.execute(new a());
            throw new IllegalStateException("Warning: Router objects should be explicitly closed when no longer required otherwise you may leak handles.", this.g);
        }
    }
}
