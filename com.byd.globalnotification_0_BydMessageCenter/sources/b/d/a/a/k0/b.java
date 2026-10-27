package b.d.a.a.k0;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.drawable.Drawable;
import com.google.android.material.transformation.FabTransformationBehavior;

/* JADX INFO: loaded from: classes.dex */
public class b extends AnimatorListenerAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ b.d.a.a.u.c f1963a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Drawable f1964b;

    public b(FabTransformationBehavior fabTransformationBehavior, b.d.a.a.u.c cVar, Drawable drawable) {
        this.f1963a = cVar;
        this.f1964b = drawable;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        this.f1963a.setCircularRevealOverlayDrawable(null);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        this.f1963a.setCircularRevealOverlayDrawable(this.f1964b);
    }
}
