package defpackage;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: compiled from: JobSupport.kt */
/* JADX INFO: loaded from: classes.dex */
public final class q21 extends o31 {
    public static final /* synthetic */ AtomicIntegerFieldUpdater j = AtomicIntegerFieldUpdater.newUpdater(q21.class, "_invoked");
    private volatile /* synthetic */ int _invoked = 0;
    public final jr0<Throwable, vi3> i;

    public q21(q31 q31Var) {
        this.i = q31Var;
    }

    @Override // defpackage.jr0
    public final /* bridge */ /* synthetic */ vi3 c(Throwable th) {
        l(th);
        return vi3.a;
    }

    @Override // defpackage.tv
    public final void l(Throwable th) {
        if (j.compareAndSet(this, 0, 1)) {
            this.i.c(th);
        }
    }
}
