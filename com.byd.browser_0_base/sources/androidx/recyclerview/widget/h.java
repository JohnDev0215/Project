package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.c;

/* JADX INFO: compiled from: DefaultItemAnimator.java */
/* JADX INFO: loaded from: classes.dex */
public final class h extends AnimatorListenerAdapter {
    public final /* synthetic */ c.d e;
    public final /* synthetic */ ViewPropertyAnimator f;
    public final /* synthetic */ View g;
    public final /* synthetic */ c h;

    public h(c cVar, c.d dVar, ViewPropertyAnimator viewPropertyAnimator, View view) {
        this.h = cVar;
        this.e = dVar;
        this.f = viewPropertyAnimator;
        this.g = view;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        this.f.setListener(null);
        View view = this.g;
        view.setAlpha(1.0f);
        view.setTranslationX(0.0f);
        view.setTranslationY(0.0f);
        c.d dVar = this.e;
        RecyclerView.a0 a0Var = dVar.b;
        c cVar = this.h;
        cVar.h(a0Var);
        cVar.r.remove(dVar.b);
        cVar.o();
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        RecyclerView.a0 a0Var = this.e.b;
        this.h.getClass();
    }
}
