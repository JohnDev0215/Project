package s3;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;

/* JADX INFO: renamed from: s3.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2384d extends AnimatorListenerAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ U f22266a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f22267b;
    public final /* synthetic */ View c;
    public final /* synthetic */ int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ ViewPropertyAnimator f22268e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ C2388h f22269f;

    public C2384d(C2388h c2388h, U u4, int i7, View view, int i10, ViewPropertyAnimator viewPropertyAnimator) {
        this.f22269f = c2388h;
        this.f22266a = u4;
        this.f22267b = i7;
        this.c = view;
        this.d = i10;
        this.f22268e = viewPropertyAnimator;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        int i7 = this.f22267b;
        View view = this.c;
        if (i7 != 0) {
            view.setTranslationX(0.0f);
        }
        if (this.d != 0) {
            view.setTranslationY(0.0f);
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        this.f22268e.setListener(null);
        C2388h c2388h = this.f22269f;
        U u4 = this.f22266a;
        c2388h.c(u4);
        c2388h.f22288p.remove(u4);
        c2388h.i();
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        this.f22269f.getClass();
    }
}
