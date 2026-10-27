package p;

import android.transition.Transition;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class zx0 implements Transition.TransitionListener {
    public final /* synthetic */ Object a;
    public final /* synthetic */ ArrayList b;
    public final /* synthetic */ Object c = null;
    public final /* synthetic */ ArrayList d = null;
    public final /* synthetic */ Object e;
    public final /* synthetic */ ArrayList f;
    public final /* synthetic */ by0 g;

    public zx0(by0 by0Var, Object obj, ArrayList arrayList, Object obj2, ArrayList arrayList2) {
        this.g = by0Var;
        this.a = obj;
        this.b = arrayList;
        this.e = obj2;
        this.f = arrayList2;
    }

    @Override // android.transition.Transition.TransitionListener
    public final void onTransitionCancel(Transition transition) {
    }

    @Override // android.transition.Transition.TransitionListener
    public final void onTransitionEnd(Transition transition) {
        transition.removeListener(this);
    }

    @Override // android.transition.Transition.TransitionListener
    public final void onTransitionPause(Transition transition) {
    }

    @Override // android.transition.Transition.TransitionListener
    public final void onTransitionResume(Transition transition) {
    }

    @Override // android.transition.Transition.TransitionListener
    public final void onTransitionStart(Transition transition) {
        by0 by0Var = this.g;
        Object obj = this.a;
        if (obj != null) {
            by0Var.t(obj, this.b, null);
        }
        Object obj2 = this.c;
        if (obj2 != null) {
            by0Var.t(obj2, this.d, null);
        }
        Object obj3 = this.e;
        if (obj3 != null) {
            by0Var.t(obj3, this.f, null);
        }
    }
}
