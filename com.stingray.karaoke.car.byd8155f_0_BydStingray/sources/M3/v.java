package m3;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public final class v extends AnimatorListenerAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f19151a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ x f19152b;

    public /* synthetic */ v(x xVar, int i7) {
        this.f19151a = i7;
        this.f19152b = xVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        switch (this.f19151a) {
            case 0:
                x xVar = this.f19152b;
                View view = xVar.f19158b;
                if (view != null) {
                    view.setVisibility(4);
                }
                ViewGroup viewGroup = xVar.c;
                if (viewGroup != null) {
                    viewGroup.setVisibility(4);
                }
                ViewGroup viewGroup2 = xVar.f19159e;
                if (viewGroup2 != null) {
                    viewGroup2.setVisibility(4);
                }
                break;
            case 1:
            default:
                super.onAnimationEnd(animator);
                break;
            case 2:
                this.f19152b.i(0);
                break;
            case 3:
                this.f19152b.i(0);
                break;
            case 4:
                ViewGroup viewGroup3 = this.f19152b.f19160f;
                if (viewGroup3 != null) {
                    viewGroup3.setVisibility(4);
                }
                break;
            case 5:
                ViewGroup viewGroup4 = this.f19152b.f19162h;
                if (viewGroup4 != null) {
                    viewGroup4.setVisibility(4);
                }
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        x xVar = this.f19152b;
        switch (this.f19151a) {
            case 0:
                View view = xVar.f19164j;
                if ((view instanceof C1805e) && !xVar.f19155A) {
                    C1805e c1805e = (C1805e) view;
                    ValueAnimator valueAnimator = c1805e.f19031P;
                    if (valueAnimator.isStarted()) {
                        valueAnimator.cancel();
                    }
                    valueAnimator.setFloatValues(c1805e.f19032Q, 0.0f);
                    valueAnimator.setDuration(250L);
                    valueAnimator.start();
                    break;
                }
                break;
            case 1:
                View view2 = xVar.f19158b;
                if (view2 != null) {
                    view2.setVisibility(0);
                }
                ViewGroup viewGroup = xVar.c;
                if (viewGroup != null) {
                    viewGroup.setVisibility(0);
                }
                ViewGroup viewGroup2 = xVar.f19159e;
                if (viewGroup2 != null) {
                    viewGroup2.setVisibility(xVar.f19155A ? 0 : 4);
                }
                View view3 = xVar.f19164j;
                if ((view3 instanceof C1805e) && !xVar.f19155A) {
                    C1805e c1805e2 = (C1805e) view3;
                    ValueAnimator valueAnimator2 = c1805e2.f19031P;
                    if (valueAnimator2.isStarted()) {
                        valueAnimator2.cancel();
                    }
                    c1805e2.f19033R = false;
                    valueAnimator2.setFloatValues(c1805e2.f19032Q, 1.0f);
                    valueAnimator2.setDuration(250L);
                    valueAnimator2.start();
                    break;
                }
                break;
            case 2:
                xVar.i(4);
                break;
            case 3:
                xVar.i(4);
                break;
            case 4:
                ViewGroup viewGroup3 = xVar.f19162h;
                if (viewGroup3 != null) {
                    viewGroup3.setVisibility(0);
                    xVar.f19162h.setTranslationX(r7.getWidth());
                    ViewGroup viewGroup4 = xVar.f19162h;
                    viewGroup4.scrollTo(viewGroup4.getWidth(), 0);
                }
                break;
            default:
                ViewGroup viewGroup5 = xVar.f19160f;
                if (viewGroup5 != null) {
                    viewGroup5.setVisibility(0);
                }
                break;
        }
    }
}
