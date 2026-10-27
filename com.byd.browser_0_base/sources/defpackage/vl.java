package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* JADX INFO: compiled from: CancelAwareAnimatorListener.java */
/* JADX INFO: loaded from: classes2.dex */
public class vl extends AnimatorListenerAdapter {
    public boolean e;

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        if (this.e) {
            return;
        }
        this.e = true;
        a();
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        if (this.e) {
            return;
        }
        this.e = true;
        b();
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        this.e = false;
        c();
    }

    public void a() {
    }

    public void b() {
    }

    public void c() {
    }
}
