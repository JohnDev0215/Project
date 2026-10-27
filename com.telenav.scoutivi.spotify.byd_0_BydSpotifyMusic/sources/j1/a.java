package j1;

import android.animation.Animator;

/* JADX INFO: compiled from: SimpleAnimatorListener.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements Animator.AnimatorListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f7144a;

    public void a(Animator animator) {
        throw null;
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
        this.f7144a = true;
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        if (this.f7144a) {
            return;
        }
        a(animator);
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationRepeat(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        this.f7144a = false;
    }
}
