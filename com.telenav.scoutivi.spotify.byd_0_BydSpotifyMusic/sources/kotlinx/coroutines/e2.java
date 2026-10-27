package kotlinx.coroutines;

/* JADX INFO: compiled from: JobSupport.kt */
/* JADX INFO: loaded from: classes.dex */
final class e2<R> extends r1<s1> {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final kotlinx.coroutines.selects.f<R> f7606j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private final r5.l<k5.d<? super R>, Object> f7607k;

    /* JADX WARN: Multi-variable type inference failed */
    public e2(s1 s1Var, kotlinx.coroutines.selects.f<? super R> fVar, r5.l<? super k5.d<? super R>, ? extends Object> lVar) {
        super(s1Var);
        this.f7606j = fVar;
        this.f7607k = lVar;
    }

    @Override // kotlinx.coroutines.a0
    public void Q(Throwable th) {
        if (this.f7606j.j()) {
            b6.a.b(this.f7607k, this.f7606j.i());
        }
    }

    @Override // r5.l
    public /* bridge */ /* synthetic */ g5.r invoke(Throwable th) {
        Q(th);
        return g5.r.f6563a;
    }

    @Override // kotlinx.coroutines.internal.o
    public String toString() {
        return "SelectJoinOnCompletion[" + this.f7606j + ']';
    }
}
