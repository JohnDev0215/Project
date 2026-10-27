package androidx.transition;

import androidx.transition.Transition;
import defpackage.y70;

/* JADX INFO: compiled from: FragmentTransitionSupport.java */
/* JADX INFO: loaded from: classes.dex */
public final class f implements Transition.d {
    public final /* synthetic */ Runnable e;

    public f(y70 y70Var) {
        this.e = y70Var;
    }

    @Override // androidx.transition.Transition.d
    public final void d(Transition transition) {
        this.e.run();
    }

    @Override // androidx.transition.Transition.d
    public final void a() {
    }

    @Override // androidx.transition.Transition.d
    public final void c() {
    }

    @Override // androidx.transition.Transition.d
    public final void e() {
    }

    @Override // androidx.transition.Transition.d
    public final void b(Transition transition) {
    }
}
