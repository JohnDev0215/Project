package a.p.e;

import a.p.e.l;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public class j extends AnimatorListenerAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ l.a f1186a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ ViewPropertyAnimator f1187b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ View f1188c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ l f1189d;

    public j(l lVar, l.a aVar, ViewPropertyAnimator viewPropertyAnimator, View view) {
        this.f1189d = lVar;
        this.f1186a = aVar;
        this.f1187b = viewPropertyAnimator;
        this.f1188c = view;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        this.f1187b.setListener(null);
        this.f1188c.setAlpha(1.0f);
        this.f1188c.setTranslationX(0.0f);
        this.f1188c.setTranslationY(0.0f);
        this.f1189d.d(this.f1186a.f1194a);
        this.f1189d.r.remove(this.f1186a.f1194a);
        this.f1189d.l();
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        l lVar = this.f1189d;
        RecyclerView.b0 b0Var = this.f1186a.f1194a;
        if (lVar == null) {
            throw null;
        }
    }
}
