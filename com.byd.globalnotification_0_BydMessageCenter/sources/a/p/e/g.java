package a.p.e;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public class g extends AnimatorListenerAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ RecyclerView.b0 f1172a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ ViewPropertyAnimator f1173b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ View f1174c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ l f1175d;

    public g(l lVar, RecyclerView.b0 b0Var, ViewPropertyAnimator viewPropertyAnimator, View view) {
        this.f1175d = lVar;
        this.f1172a = b0Var;
        this.f1173b = viewPropertyAnimator;
        this.f1174c = view;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        this.f1173b.setListener(null);
        this.f1174c.setAlpha(1.0f);
        this.f1175d.d(this.f1172a);
        this.f1175d.q.remove(this.f1172a);
        this.f1175d.l();
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        if (this.f1175d == null) {
            throw null;
        }
    }
}
