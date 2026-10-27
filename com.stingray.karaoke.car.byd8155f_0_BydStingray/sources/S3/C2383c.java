package s3;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;

/* JADX INFO: renamed from: s3.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2383c extends AnimatorListenerAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f22261a = 1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ U f22262b;
    public final /* synthetic */ View c;
    public final /* synthetic */ ViewPropertyAnimator d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ C2388h f22263e;

    public C2383c(C2388h c2388h, U u4, ViewPropertyAnimator viewPropertyAnimator, View view) {
        this.f22263e = c2388h;
        this.f22262b = u4;
        this.d = viewPropertyAnimator;
        this.c = view;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
        switch (this.f22261a) {
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
        switch (this.f22261a) {
            case 0:
                this.d.setListener(null);
                this.c.setAlpha(1.0f);
                C2388h c2388h = this.f22263e;
                U u4 = this.f22262b;
                c2388h.c(u4);
                c2388h.f22289q.remove(u4);
                c2388h.i();
                break;
            default:
                this.d.setListener(null);
                C2388h c2388h2 = this.f22263e;
                U u10 = this.f22262b;
                c2388h2.c(u10);
                c2388h2.o.remove(u10);
                c2388h2.i();
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        switch (this.f22261a) {
            case 0:
                this.f22263e.getClass();
                break;
            default:
                this.f22263e.getClass();
                break;
        }
    }

    public C2383c(C2388h c2388h, U u4, View view, ViewPropertyAnimator viewPropertyAnimator) {
        this.f22263e = c2388h;
        this.f22262b = u4;
        this.c = view;
        this.d = viewPropertyAnimator;
    }
}
