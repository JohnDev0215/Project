package kotlinx.coroutines;

/* JADX INFO: compiled from: JobSupport.kt */
/* JADX INFO: loaded from: classes.dex */
final class d2<T, R> extends r1<s1> {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final kotlinx.coroutines.selects.f<R> f7600j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private final r5.p<T, k5.d<? super R>, Object> f7601k;

    /* JADX WARN: Multi-variable type inference failed */
    public d2(s1 s1Var, kotlinx.coroutines.selects.f<? super R> fVar, r5.p<? super T, ? super k5.d<? super R>, ? extends Object> pVar) {
        super(s1Var);
        this.f7600j = fVar;
        this.f7601k = pVar;
    }

    @Override // kotlinx.coroutines.a0
    public void Q(Throwable th) {
        if (this.f7600j.j()) {
            ((s1) this.f7715i).selectAwaitCompletion$kotlinx_coroutines_core(this.f7600j, this.f7601k);
        }
    }

    @Override // r5.l
    public /* bridge */ /* synthetic */ g5.r invoke(Throwable th) {
        Q(th);
        return g5.r.f6563a;
    }

    @Override // kotlinx.coroutines.internal.o
    public String toString() {
        return "SelectAwaitOnCompletion[" + this.f7600j + ']';
    }
}
