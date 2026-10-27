package p;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;

/* JADX INFO: loaded from: classes.dex */
public final class h70 extends AnimatorListenerAdapter {
    public final /* synthetic */ int a = 1;
    public final /* synthetic */ n22 b;
    public final /* synthetic */ View c;
    public final /* synthetic */ ViewPropertyAnimator d;
    public final /* synthetic */ m70 e;

    public h70(m70 m70Var, n22 n22Var, ViewPropertyAnimator viewPropertyAnimator, View view) {
        this.e = m70Var;
        this.b = n22Var;
        this.d = viewPropertyAnimator;
        this.c = view;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        switch (this.a) {
            case 1:
                this.c.setAlpha(1.0f);
                break;
            default:
                super.onAnimationCancel(animator);
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        int i = this.a;
        n22 n22Var = this.b;
        m70 m70Var = this.e;
        ViewPropertyAnimator viewPropertyAnimator = this.d;
        switch (i) {
            case 0:
                viewPropertyAnimator.setListener(null);
                this.c.setAlpha(1.0f);
                m70Var.c(n22Var);
                m70Var.q.remove(n22Var);
                m70Var.i();
                break;
            default:
                viewPropertyAnimator.setListener(null);
                m70Var.c(n22Var);
                m70Var.o.remove(n22Var);
                m70Var.i();
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        int i = this.a;
        m70 m70Var = this.e;
        switch (i) {
            case 0:
                m70Var.getClass();
                break;
            default:
                m70Var.getClass();
                break;
        }
    }

    public h70(m70 m70Var, n22 n22Var, View view, ViewPropertyAnimator viewPropertyAnimator) {
        this.e = m70Var;
        this.b = n22Var;
        this.c = view;
        this.d = viewPropertyAnimator;
    }
}
