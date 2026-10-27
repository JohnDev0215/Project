package s3;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;

/* JADX INFO: renamed from: s3.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2385e extends AnimatorListenerAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f22270a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ C2386f f22271b;
    public final /* synthetic */ ViewPropertyAnimator c;
    public final /* synthetic */ View d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ C2388h f22272e;

    public /* synthetic */ C2385e(C2388h c2388h, C2386f c2386f, ViewPropertyAnimator viewPropertyAnimator, View view, int i7) {
        this.f22270a = i7;
        this.f22272e = c2388h;
        this.f22271b = c2386f;
        this.c = viewPropertyAnimator;
        this.d = view;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        switch (this.f22270a) {
            case 0:
                this.c.setListener(null);
                View view = this.d;
                view.setAlpha(1.0f);
                view.setTranslationX(0.0f);
                view.setTranslationY(0.0f);
                C2386f c2386f = this.f22271b;
                U u4 = c2386f.f22273a;
                C2388h c2388h = this.f22272e;
                c2388h.c(u4);
                c2388h.f22290r.remove(c2386f.f22273a);
                c2388h.i();
                break;
            default:
                this.c.setListener(null);
                View view2 = this.d;
                view2.setAlpha(1.0f);
                view2.setTranslationX(0.0f);
                view2.setTranslationY(0.0f);
                C2386f c2386f2 = this.f22271b;
                U u10 = c2386f2.f22274b;
                C2388h c2388h2 = this.f22272e;
                c2388h2.c(u10);
                c2388h2.f22290r.remove(c2386f2.f22274b);
                c2388h2.i();
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        switch (this.f22270a) {
            case 0:
                U u4 = this.f22271b.f22273a;
                this.f22272e.getClass();
                break;
            default:
                U u10 = this.f22271b.f22274b;
                this.f22272e.getClass();
                break;
        }
    }
}
