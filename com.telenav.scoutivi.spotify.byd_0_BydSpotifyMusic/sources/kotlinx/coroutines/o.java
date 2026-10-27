package kotlinx.coroutines;

/* JADX INFO: compiled from: JobSupport.kt */
/* JADX INFO: loaded from: classes.dex */
public final class o extends n1<l1> {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final k<?> f7711j;

    public o(l1 l1Var, k<?> kVar) {
        super(l1Var);
        this.f7711j = kVar;
    }

    @Override // kotlinx.coroutines.a0
    public void Q(Throwable th) {
        k<?> kVar = this.f7711j;
        kVar.G(kVar.w(this.f7715i));
    }

    @Override // r5.l
    public /* bridge */ /* synthetic */ g5.r invoke(Throwable th) {
        Q(th);
        return g5.r.f6563a;
    }

    @Override // kotlinx.coroutines.internal.o
    public String toString() {
        return "ChildContinuation[" + this.f7711j + ']';
    }
}
