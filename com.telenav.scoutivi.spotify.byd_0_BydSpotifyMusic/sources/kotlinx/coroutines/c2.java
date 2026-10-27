package kotlinx.coroutines;

import g5.l;

/* JADX INFO: compiled from: JobSupport.kt */
/* JADX INFO: loaded from: classes.dex */
final class c2 extends r1<l1> {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final k5.d<g5.r> f7519j;

    /* JADX WARN: Multi-variable type inference failed */
    public c2(l1 l1Var, k5.d<? super g5.r> dVar) {
        super(l1Var);
        this.f7519j = dVar;
    }

    @Override // kotlinx.coroutines.a0
    public void Q(Throwable th) {
        k5.d<g5.r> dVar = this.f7519j;
        g5.r rVar = g5.r.f6563a;
        l.a aVar = g5.l.f6557e;
        dVar.resumeWith(g5.l.a(rVar));
    }

    @Override // r5.l
    public /* bridge */ /* synthetic */ g5.r invoke(Throwable th) {
        Q(th);
        return g5.r.f6563a;
    }

    @Override // kotlinx.coroutines.internal.o
    public String toString() {
        return "ResumeOnCompletion[" + this.f7519j + ']';
    }
}
