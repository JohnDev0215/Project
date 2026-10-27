package p;

import java.util.concurrent.CancellationException;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
public final class fr implements yq {
    public final yq c;
    public ry0 e;

    public fr(kotlinx.coroutines.channels.a aVar) {
        this.c = aVar;
    }

    @Override // p.hb2
    public final void d(ry0 ry0Var) {
        this.c.d(ry0Var);
    }

    @Override // p.l12
    public final Object e(c00 c00Var) {
        return this.c.e(c00Var);
    }

    @Override // p.l12
    public final Object g(ContinuationImpl continuationImpl) {
        return this.c.g(continuationImpl);
    }

    @Override // p.hb2
    public final boolean h(Throwable th) {
        ry0 ry0Var;
        boolean zH = this.c.h(th);
        if (zH && (ry0Var = this.e) != null) {
            ry0Var.invoke(th);
        }
        this.e = null;
        return zH;
    }

    @Override // p.l12
    public final void i(CancellationException cancellationException) {
        this.c.i(cancellationException);
    }

    @Override // p.l12
    public final go iterator() {
        return this.c.iterator();
    }

    @Override // p.hb2
    public final Object l(Object obj, c00 c00Var) {
        return this.c.l(obj, c00Var);
    }

    @Override // p.l12
    public final Object m() {
        return this.c.m();
    }

    @Override // p.hb2
    public final Object n(Object obj) {
        return this.c.n(obj);
    }

    @Override // p.hb2
    public final boolean o() {
        return this.c.o();
    }
}
