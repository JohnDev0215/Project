package kotlinx.coroutines;

/* JADX INFO: compiled from: Unconfined.kt */
/* JADX INFO: loaded from: classes.dex */
public final class k2 extends e0 {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final k2 f7701g = new k2();

    private k2() {
    }

    @Override // kotlinx.coroutines.e0
    public void a0(k5.g gVar, Runnable runnable) {
        l2 l2Var = (l2) gVar.get(l2.f7705g);
        if (l2Var == null) {
            throw new UnsupportedOperationException("Dispatchers.Unconfined.dispatch function can only be used by the yield function. If you wrap Unconfined dispatcher in your code, make sure you properly delegate isDispatchNeeded and dispatch calls.");
        }
        l2Var.f7706e = true;
    }

    @Override // kotlinx.coroutines.e0
    public boolean c0(k5.g gVar) {
        return false;
    }

    @Override // kotlinx.coroutines.e0
    public String toString() {
        return "Dispatchers.Unconfined";
    }
}
