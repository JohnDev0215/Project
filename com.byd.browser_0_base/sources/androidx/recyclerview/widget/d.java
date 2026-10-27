package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: DefaultItemAnimator.java */
/* JADX INFO: loaded from: classes.dex */
public final class d extends AnimatorListenerAdapter {
    public final /* synthetic */ RecyclerView.a0 e;
    public final /* synthetic */ ViewPropertyAnimator f;
    public final /* synthetic */ View g;
    public final /* synthetic */ c h;

    public d(View view, ViewPropertyAnimator viewPropertyAnimator, c cVar, RecyclerView.a0 a0Var) {
        this.h = cVar;
        this.e = a0Var;
        this.f = viewPropertyAnimator;
        this.g = view;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        this.f.setListener(null);
        this.g.setAlpha(1.0f);
        c cVar = this.h;
        RecyclerView.a0 a0Var = this.e;
        cVar.h(a0Var);
        cVar.q.remove(a0Var);
        cVar.o();
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        this.h.getClass();
    }
}
