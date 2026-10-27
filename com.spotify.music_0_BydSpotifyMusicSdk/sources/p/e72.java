package p;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class e72 extends kotlinx.coroutines.b implements o80 {
    public static final /* synthetic */ int l = 0;
    public final Scheduler k;

    public e72(Scheduler scheduler) {
        this.k = scheduler;
    }

    @Override // kotlinx.coroutines.b
    public final void G0(y10 y10Var, Runnable runnable) {
        this.k.c(runnable);
    }

    @Override // p.o80
    public final oa0 J(long j, ul2 ul2Var, y10 y10Var) {
        final Disposable disposableD = this.k.d(ul2Var, j, TimeUnit.MILLISECONDS);
        return new oa0() { // from class: p.c72
            @Override // p.oa0
            public final void dispose() {
                disposableD.dispose();
            }
        };
    }

    @Override // p.o80
    public final void X(long j, zp zpVar) {
        kotlinx.coroutines.rx3.a.d(zpVar, this.k.d(new ua(zpVar, 21, this), j, TimeUnit.MILLISECONDS));
    }

    public final boolean equals(Object obj) {
        return (obj instanceof e72) && ((e72) obj).k == this.k;
    }

    public final int hashCode() {
        return System.identityHashCode(this.k);
    }

    @Override // kotlinx.coroutines.b
    public final String toString() {
        return this.k.toString();
    }
}
