package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import java.util.ArrayList;

/* JADX INFO: compiled from: CompositorAnimationHandler.java */
/* JADX INFO: loaded from: classes.dex */
public final class dw extends AnimatorListenerAdapter {
    public final /* synthetic */ kw e;
    public final /* synthetic */ ew f;

    public dw(ew ewVar, kw kwVar) {
        this.f = ewVar;
        this.e = kwVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        ArrayList<kw> arrayList = this.f.a;
        kw kwVar = this.e;
        arrayList.remove(kwVar);
        kwVar.removeListener(this);
    }
}
