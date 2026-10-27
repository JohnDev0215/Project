package b.d.a.a.y;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

/* JADX INFO: loaded from: classes.dex */
public class a extends AnimatorListenerAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f2133a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ h f2134b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ ExtendedFloatingActionButton.e f2135c = null;

    public a(ExtendedFloatingActionButton extendedFloatingActionButton, h hVar) {
        this.f2134b = hVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
        this.f2133a = true;
        this.f2134b.e();
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        this.f2134b.d();
        if (this.f2133a) {
            return;
        }
        this.f2134b.a(null);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        this.f2134b.onAnimationStart(animator);
        this.f2133a = false;
    }
}
