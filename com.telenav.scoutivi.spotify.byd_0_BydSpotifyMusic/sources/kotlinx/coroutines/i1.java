package kotlinx.coroutines;

/* JADX INFO: compiled from: CancellableContinuationImpl.kt */
/* JADX INFO: loaded from: classes.dex */
final class i1 extends g {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final r5.l<Throwable, g5.r> f7615e;

    /* JADX WARN: Multi-variable type inference failed */
    public i1(r5.l<? super Throwable, g5.r> lVar) {
        this.f7615e = lVar;
    }

    @Override // kotlinx.coroutines.h
    public void a(Throwable th) {
        this.f7615e.invoke(th);
    }

    @Override // r5.l
    public /* bridge */ /* synthetic */ g5.r invoke(Throwable th) {
        a(th);
        return g5.r.f6563a;
    }

    public String toString() {
        return "InvokeOnCancel[" + m0.a(this.f7615e) + '@' + m0.b(this) + ']';
    }
}
