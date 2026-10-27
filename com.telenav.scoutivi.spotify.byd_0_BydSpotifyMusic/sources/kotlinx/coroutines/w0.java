package kotlinx.coroutines;

/* JADX INFO: compiled from: CancellableContinuation.kt */
/* JADX INFO: loaded from: classes.dex */
final class w0 extends g {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final v0 f7842e;

    public w0(v0 v0Var) {
        this.f7842e = v0Var;
    }

    @Override // kotlinx.coroutines.h
    public void a(Throwable th) {
        this.f7842e.dispose();
    }

    @Override // r5.l
    public /* bridge */ /* synthetic */ g5.r invoke(Throwable th) {
        a(th);
        return g5.r.f6563a;
    }

    public String toString() {
        return "DisposeOnCancel[" + this.f7842e + ']';
    }
}
