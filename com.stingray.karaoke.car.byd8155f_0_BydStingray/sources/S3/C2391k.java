package s3;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* JADX INFO: renamed from: s3.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2391k extends AnimatorListenerAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f22293a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ C2392l f22294b;

    public C2391k(C2392l c2392l) {
        this.f22294b = c2392l;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        this.f22293a = true;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        if (this.f22293a) {
            this.f22293a = false;
            return;
        }
        C2392l c2392l = this.f22294b;
        if (((Float) c2392l.f22319z.getAnimatedValue()).floatValue() == 0.0f) {
            c2392l.f22297A = 0;
            c2392l.e(0);
        } else {
            c2392l.f22297A = 2;
            c2392l.f22312s.invalidate();
        }
    }
}
