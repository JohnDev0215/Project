package kotlinx.coroutines;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: compiled from: JobSupport.kt */
/* JADX INFO: loaded from: classes.dex */
final class j1 extends n1<l1> {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private static final AtomicIntegerFieldUpdater f7692k = AtomicIntegerFieldUpdater.newUpdater(j1.class, "_invoked");
    private volatile int _invoked;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final r5.l<Throwable, g5.r> f7693j;

    /* JADX WARN: Multi-variable type inference failed */
    public j1(l1 l1Var, r5.l<? super Throwable, g5.r> lVar) {
        super(l1Var);
        this.f7693j = lVar;
        this._invoked = 0;
    }

    @Override // kotlinx.coroutines.a0
    public void Q(Throwable th) {
        if (f7692k.compareAndSet(this, 0, 1)) {
            this.f7693j.invoke(th);
        }
    }

    @Override // r5.l
    public /* bridge */ /* synthetic */ g5.r invoke(Throwable th) {
        Q(th);
        return g5.r.f6563a;
    }

    @Override // kotlinx.coroutines.internal.o
    public String toString() {
        return "InvokeOnCancelling[" + m0.a(this) + '@' + m0.b(this) + ']';
    }
}
