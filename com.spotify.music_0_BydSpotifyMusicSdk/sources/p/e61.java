package p;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public final class e61 extends q61 {
    public static final AtomicIntegerFieldUpdater n = AtomicIntegerFieldUpdater.newUpdater(e61.class, "_invoked");
    private volatile int _invoked;
    public final ry0 m;

    public e61(ry0 ry0Var) {
        this.m = ry0Var;
    }

    @Override // p.ry0
    public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
        l((Throwable) obj);
        return lq2.a;
    }

    @Override // p.t61
    public final void l(Throwable th) {
        if (n.compareAndSet(this, 0, 1)) {
            this.m.invoke(th);
        }
    }
}
