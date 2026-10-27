package defpackage;

/* JADX INFO: compiled from: MainCoroutineDispatcher.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class cb1 extends p00 {
    public abstract cb1 j1();

    @Override // defpackage.p00
    public String toString() {
        cb1 cb1VarJ1;
        String str;
        u70 u70Var = xa0.a;
        cb1 cb1Var = eb1.a;
        if (this == cb1Var) {
            str = "Dispatchers.Main";
        } else {
            try {
                cb1VarJ1 = cb1Var.j1();
            } catch (UnsupportedOperationException unused) {
                cb1VarJ1 = null;
            }
            str = this == cb1VarJ1 ? "Dispatchers.Main.immediate" : null;
        }
        if (str != null) {
            return str;
        }
        return getClass().getSimpleName() + '@' + d70.d(this);
    }
}
