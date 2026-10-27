package defpackage;

/* JADX INFO: compiled from: ContinuationImpl.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class tb2 extends ed {
    public tb2(b00<Object> b00Var) {
        super(b00Var);
        if (b00Var != null) {
            if (!(b00Var.getContext() == dh0.e)) {
                throw new IllegalArgumentException("Coroutines with restricted suspension must have EmptyCoroutineContext".toString());
            }
        }
    }

    @Override // defpackage.b00
    public final m00 getContext() {
        return dh0.e;
    }
}
