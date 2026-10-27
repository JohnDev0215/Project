package kotlinx.coroutines;

/* JADX INFO: compiled from: CancellableContinuation.kt */
/* JADX INFO: loaded from: classes.dex */
final class a2 extends c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final kotlinx.coroutines.internal.o f7514e;

    public a2(kotlinx.coroutines.internal.o oVar) {
        this.f7514e = oVar;
    }

    @Override // kotlinx.coroutines.h
    public void a(Throwable th) {
        this.f7514e.M();
    }

    @Override // r5.l
    public /* bridge */ /* synthetic */ g5.r invoke(Throwable th) {
        a(th);
        return g5.r.f6563a;
    }

    public String toString() {
        return "RemoveOnCancel[" + this.f7514e + ']';
    }
}
