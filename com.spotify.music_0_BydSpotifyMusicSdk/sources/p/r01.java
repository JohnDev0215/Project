package p;

import androidx.lifecycle.Lifecycle$State;

/* JADX INFO: loaded from: classes.dex */
public final class r01 extends l91 {
    public static final r01 a = new r01();
    public static final q01 b = new q01();

    @Override // p.l91
    public final void a(r91 r91Var) {
        if (!(r91Var instanceof n70)) {
            throw new IllegalArgumentException((r91Var + " must implement androidx.lifecycle.DefaultLifecycleObserver.").toString());
        }
        n70 n70Var = (n70) r91Var;
        q01 q01Var = b;
        n70Var.onCreate(q01Var);
        n70Var.onStart(q01Var);
        n70Var.onResume(q01Var);
    }

    @Override // p.l91
    public final Lifecycle$State b() {
        return Lifecycle$State.m;
    }

    @Override // p.l91
    public final void c(r91 r91Var) {
    }

    public final String toString() {
        return "coil.request.GlobalLifecycle";
    }
}
