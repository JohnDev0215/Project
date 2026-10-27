package kotlinx.coroutines.internal;

/* JADX INFO: compiled from: Atomic.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public d<?> f7619a;

    public abstract void a(d<?> dVar, Object obj);

    public final d<?> b() {
        d<?> dVar = this.f7619a;
        if (dVar == null) {
            kotlin.jvm.internal.l.u("atomicOp");
        }
        return dVar;
    }

    public abstract Object c(d<?> dVar);

    public final void d(d<?> dVar) {
        this.f7619a = dVar;
    }
}
