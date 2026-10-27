package p;

import java.util.concurrent.CancellationException;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.JobCancellationException;

/* JADX INFO: loaded from: classes.dex */
public final class rx1 extends a1 implements sx1, yq {
    public final yq l;

    public rx1(y10 y10Var, kotlinx.coroutines.channels.a aVar) {
        super(y10Var, true, true);
        this.l = aVar;
    }

    @Override // p.a1
    public final void Y(Throwable th, boolean z) {
        if (this.l.h(th) || z) {
            return;
        }
        ux2.m(this.k, th);
    }

    @Override // p.a1
    public final void Z(Object obj) {
        this.l.h(null);
    }

    @Override // p.a1, kotlinx.coroutines.g, p.p61
    public final boolean b() {
        return super.b();
    }

    @Override // p.hb2
    public final void d(ry0 ry0Var) {
        this.l.d(ry0Var);
    }

    @Override // p.l12
    public final Object e(c00 c00Var) {
        return this.l.e(c00Var);
    }

    @Override // p.l12
    public final Object g(ContinuationImpl continuationImpl) {
        return this.l.g(continuationImpl);
    }

    @Override // p.hb2
    public final boolean h(Throwable th) {
        return this.l.h(th);
    }

    @Override // kotlinx.coroutines.g, p.p61
    public final void i(CancellationException cancellationException) {
        Object objI = I();
        if (objI instanceof hv) {
            return;
        }
        if ((objI instanceof w61) && ((w61) objI).e()) {
            return;
        }
        if (cancellationException == null) {
            cancellationException = new JobCancellationException(w(), null, this);
        }
        u(cancellationException);
    }

    @Override // p.l12
    public final go iterator() {
        return this.l.iterator();
    }

    @Override // p.hb2
    public final Object l(Object obj, c00 c00Var) {
        return this.l.l(obj, c00Var);
    }

    @Override // p.l12
    public final Object m() {
        return this.l.m();
    }

    @Override // p.hb2
    public final Object n(Object obj) {
        return this.l.n(obj);
    }

    @Override // p.hb2
    public final boolean o() {
        return this.l.o();
    }

    @Override // kotlinx.coroutines.g
    public final void u(CancellationException cancellationException) {
        this.l.i(cancellationException);
        t(cancellationException);
    }
}
