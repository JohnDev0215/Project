package kotlinx.coroutines;

import g5.l;

/* JADX INFO: compiled from: JobSupport.kt */
/* JADX INFO: loaded from: classes.dex */
final class b2<T> extends r1<s1> {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final k<T> f7516j;

    /* JADX WARN: Multi-variable type inference failed */
    public b2(s1 s1Var, k<? super T> kVar) {
        super(s1Var);
        this.f7516j = kVar;
    }

    @Override // kotlinx.coroutines.a0
    public void Q(Throwable th) {
        Object state$kotlinx_coroutines_core = ((s1) this.f7715i).getState$kotlinx_coroutines_core();
        if (state$kotlinx_coroutines_core instanceof y) {
            k<T> kVar = this.f7516j;
            Throwable th2 = ((y) state$kotlinx_coroutines_core).f7851a;
            l.a aVar = g5.l.f6557e;
            kVar.resumeWith(g5.l.a(g5.m.a(th2)));
            return;
        }
        k<T> kVar2 = this.f7516j;
        Object objH = t1.h(state$kotlinx_coroutines_core);
        l.a aVar2 = g5.l.f6557e;
        kVar2.resumeWith(g5.l.a(objH));
    }

    @Override // r5.l
    public /* bridge */ /* synthetic */ g5.r invoke(Throwable th) {
        Q(th);
        return g5.r.f6563a;
    }

    @Override // kotlinx.coroutines.internal.o
    public String toString() {
        return "ResumeAwaitOnCompletion[" + this.f7516j + ']';
    }
}
