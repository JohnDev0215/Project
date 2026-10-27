package kotlinx.coroutines;

/* JADX INFO: compiled from: JobSupport.kt */
/* JADX INFO: loaded from: classes.dex */
public final class q extends n1<s1> implements p {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final r f7714j;

    public q(s1 s1Var, r rVar) {
        super(s1Var);
        this.f7714j = rVar;
    }

    @Override // kotlinx.coroutines.a0
    public void Q(Throwable th) {
        this.f7714j.parentCancelled((z1) this.f7715i);
    }

    @Override // r5.l
    public /* bridge */ /* synthetic */ g5.r invoke(Throwable th) {
        Q(th);
        return g5.r.f6563a;
    }

    @Override // kotlinx.coroutines.p
    public boolean k(Throwable th) {
        return ((s1) this.f7715i).childCancelled(th);
    }

    @Override // kotlinx.coroutines.internal.o
    public String toString() {
        return "ChildHandle[" + this.f7714j + ']';
    }
}
