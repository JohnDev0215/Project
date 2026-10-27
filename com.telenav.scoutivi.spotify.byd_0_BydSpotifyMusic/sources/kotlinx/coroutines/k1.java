package kotlinx.coroutines;

/* JADX INFO: compiled from: JobSupport.kt */
/* JADX INFO: loaded from: classes.dex */
final class k1 extends r1<l1> {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final r5.l<Throwable, g5.r> f7700j;

    /* JADX WARN: Multi-variable type inference failed */
    public k1(l1 l1Var, r5.l<? super Throwable, g5.r> lVar) {
        super(l1Var);
        this.f7700j = lVar;
    }

    @Override // kotlinx.coroutines.a0
    public void Q(Throwable th) {
        this.f7700j.invoke(th);
    }

    @Override // r5.l
    public /* bridge */ /* synthetic */ g5.r invoke(Throwable th) {
        Q(th);
        return g5.r.f6563a;
    }

    @Override // kotlinx.coroutines.internal.o
    public String toString() {
        return "InvokeOnCompletion[" + m0.a(this) + '@' + m0.b(this) + ']';
    }
}
