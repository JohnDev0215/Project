package defpackage;

import android.transition.Transition;
import java.util.ArrayList;

/* JADX INFO: compiled from: FragmentTransitionCompat21.java */
/* JADX INFO: loaded from: classes.dex */
public final class yp0 implements Transition.TransitionListener {
    public final /* synthetic */ Object e;
    public final /* synthetic */ ArrayList f;
    public final /* synthetic */ Object g = null;
    public final /* synthetic */ ArrayList h = null;
    public final /* synthetic */ Object i;
    public final /* synthetic */ ArrayList j;
    public final /* synthetic */ xp0 k;

    public yp0(xp0 xp0Var, Object obj, ArrayList arrayList, Object obj2, ArrayList arrayList2) {
        this.k = xp0Var;
        this.e = obj;
        this.f = arrayList;
        this.i = obj2;
        this.j = arrayList2;
    }

    @Override // android.transition.Transition.TransitionListener
    public final void onTransitionEnd(Transition transition) {
        transition.removeListener(this);
    }

    @Override // android.transition.Transition.TransitionListener
    public final void onTransitionStart(Transition transition) {
        xp0 xp0Var = this.k;
        Object obj = this.e;
        if (obj != null) {
            xp0Var.t(obj, this.f, null);
        }
        Object obj2 = this.g;
        if (obj2 != null) {
            xp0Var.t(obj2, this.h, null);
        }
        Object obj3 = this.i;
        if (obj3 != null) {
            xp0Var.t(obj3, this.j, null);
        }
    }

    @Override // android.transition.Transition.TransitionListener
    public final void onTransitionCancel(Transition transition) {
    }

    @Override // android.transition.Transition.TransitionListener
    public final void onTransitionPause(Transition transition) {
    }

    @Override // android.transition.Transition.TransitionListener
    public final void onTransitionResume(Transition transition) {
    }
}
