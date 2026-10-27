package a.p.e;

import a.p.e.l;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public class k extends AnimatorListenerAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ l.a f1190a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ ViewPropertyAnimator f1191b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ View f1192c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ l f1193d;

    public k(l lVar, l.a aVar, ViewPropertyAnimator viewPropertyAnimator, View view) {
        this.f1193d = lVar;
        this.f1190a = aVar;
        this.f1191b = viewPropertyAnimator;
        this.f1192c = view;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        this.f1191b.setListener(null);
        this.f1192c.setAlpha(1.0f);
        this.f1192c.setTranslationX(0.0f);
        this.f1192c.setTranslationY(0.0f);
        this.f1193d.d(this.f1190a.f1195b);
        this.f1193d.r.remove(this.f1190a.f1195b);
        this.f1193d.l();
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        l lVar = this.f1193d;
        RecyclerView.b0 b0Var = this.f1190a.f1195b;
        if (lVar == null) {
            throw null;
        }
    }
}
