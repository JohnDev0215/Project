package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: DefaultItemAnimator.java */
/* JADX INFO: loaded from: classes.dex */
public final class f extends AnimatorListenerAdapter {
    public final /* synthetic */ RecyclerView.a0 e;
    public final /* synthetic */ int f;
    public final /* synthetic */ View g;
    public final /* synthetic */ int h;
    public final /* synthetic */ ViewPropertyAnimator i;
    public final /* synthetic */ c j;

    public f(c cVar, RecyclerView.a0 a0Var, int i, View view, int i2, ViewPropertyAnimator viewPropertyAnimator) {
        this.j = cVar;
        this.e = a0Var;
        this.f = i;
        this.g = view;
        this.h = i2;
        this.i = viewPropertyAnimator;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        int i = this.f;
        View view = this.g;
        if (i != 0) {
            view.setTranslationX(0.0f);
        }
        if (this.h != 0) {
            view.setTranslationY(0.0f);
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        this.i.setListener(null);
        c cVar = this.j;
        RecyclerView.a0 a0Var = this.e;
        cVar.h(a0Var);
        cVar.p.remove(a0Var);
        cVar.o();
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        this.j.getClass();
    }
}
