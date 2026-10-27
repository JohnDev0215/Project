package a.p.e;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public class h extends AnimatorListenerAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ RecyclerView.b0 f1176a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ View f1177b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ ViewPropertyAnimator f1178c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ l f1179d;

    public h(l lVar, RecyclerView.b0 b0Var, View view, ViewPropertyAnimator viewPropertyAnimator) {
        this.f1179d = lVar;
        this.f1176a = b0Var;
        this.f1177b = view;
        this.f1178c = viewPropertyAnimator;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
        this.f1177b.setAlpha(1.0f);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        this.f1178c.setListener(null);
        this.f1179d.d(this.f1176a);
        this.f1179d.o.remove(this.f1176a);
        this.f1179d.l();
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        if (this.f1179d == null) {
            throw null;
        }
    }
}
