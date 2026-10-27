package defpackage;

/* JADX INFO: compiled from: Unconfined.kt */
/* JADX INFO: loaded from: classes.dex */
public final class fi3 extends p00 {
    public static final /* synthetic */ int f = 0;

    static {
        new fi3();
    }

    @Override // defpackage.p00
    public final void h1(m00 m00Var, Runnable runnable) {
        if (((fu3) m00Var.get(fu3.e)) == null) {
            throw new UnsupportedOperationException("Dispatchers.Unconfined.dispatch function can only be used by the yield function. If you wrap Unconfined dispatcher in your code, make sure you properly delegate isDispatchNeeded and dispatch calls.");
        }
    }

    @Override // defpackage.p00
    public final String toString() {
        return "Dispatchers.Unconfined";
    }
}
