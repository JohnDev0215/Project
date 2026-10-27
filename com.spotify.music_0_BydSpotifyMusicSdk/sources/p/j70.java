package p;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;

/* JADX INFO: loaded from: classes.dex */
public final class j70 extends AnimatorListenerAdapter {
    public final /* synthetic */ int a;
    public final /* synthetic */ k70 b;
    public final /* synthetic */ ViewPropertyAnimator c;
    public final /* synthetic */ View d;
    public final /* synthetic */ m70 e;

    public /* synthetic */ j70(m70 m70Var, k70 k70Var, ViewPropertyAnimator viewPropertyAnimator, View view, int i) {
        this.a = i;
        this.e = m70Var;
        this.b = k70Var;
        this.c = viewPropertyAnimator;
        this.d = view;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        int i = this.a;
        m70 m70Var = this.e;
        k70 k70Var = this.b;
        View view = this.d;
        ViewPropertyAnimator viewPropertyAnimator = this.c;
        switch (i) {
            case 0:
                viewPropertyAnimator.setListener(null);
                view.setAlpha(1.0f);
                view.setTranslationX(0.0f);
                view.setTranslationY(0.0f);
                m70Var.c(k70Var.a);
                m70Var.r.remove(k70Var.a);
                m70Var.i();
                break;
            default:
                viewPropertyAnimator.setListener(null);
                view.setAlpha(1.0f);
                view.setTranslationX(0.0f);
                view.setTranslationY(0.0f);
                m70Var.c(k70Var.b);
                m70Var.r.remove(k70Var.b);
                m70Var.i();
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        int i = this.a;
        m70 m70Var = this.e;
        k70 k70Var = this.b;
        switch (i) {
            case 0:
                n22 n22Var = k70Var.a;
                m70Var.getClass();
                break;
            default:
                n22 n22Var2 = k70Var.b;
                m70Var.getClass();
                break;
        }
    }
}
