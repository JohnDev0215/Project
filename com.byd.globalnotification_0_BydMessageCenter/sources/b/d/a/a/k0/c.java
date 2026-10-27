package b.d.a.a.k0;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import b.d.a.a.u.c;
import com.google.android.material.transformation.FabTransformationBehavior;

/* JADX INFO: loaded from: classes.dex */
public class c extends AnimatorListenerAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ b.d.a.a.u.c f1965a;

    public c(FabTransformationBehavior fabTransformationBehavior, b.d.a.a.u.c cVar) {
        this.f1965a = cVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        c.e revealInfo = this.f1965a.getRevealInfo();
        revealInfo.f2064c = Float.MAX_VALUE;
        this.f1965a.setRevealInfo(revealInfo);
    }
}
