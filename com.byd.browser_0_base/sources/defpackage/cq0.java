package defpackage;

import androidx.transition.Transition;
import defpackage.am;

/* JADX INFO: compiled from: FragmentTransitionSupport.java */
/* JADX INFO: loaded from: classes.dex */
public final class cq0 implements am.a {
    public final /* synthetic */ Transition e;

    public cq0(Transition transition) {
        this.e = transition;
    }

    @Override // am.a
    public final void onCancel() {
        this.e.h();
    }
}
