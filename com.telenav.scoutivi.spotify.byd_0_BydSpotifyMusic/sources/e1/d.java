package e1;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: compiled from: Fade.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends f0 {

    /* JADX INFO: compiled from: Fade.java */
    class a extends m {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ View f6074a;

        a(View view) {
            this.f6074a = view;
        }

        @Override // e1.l.f
        public void e(l lVar) {
            y.g(this.f6074a, 1.0f);
            y.a(this.f6074a);
            lVar.P(this);
        }
    }

    /* JADX INFO: compiled from: Fade.java */
    private static class b extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final View f6076a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private boolean f6077b = false;

        b(View view) {
            this.f6076a = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            y.g(this.f6076a, 1.0f);
            if (this.f6077b) {
                this.f6076a.setLayerType(0, null);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            if (i0.u.L(this.f6076a) && this.f6076a.getLayerType() == 0) {
                this.f6077b = true;
                this.f6076a.setLayerType(2, null);
            }
        }
    }

    public d(int i7) {
        i0(i7);
    }

    private Animator j0(View view, float f7, float f8) {
        if (f7 == f8) {
            return null;
        }
        y.g(view, f7);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, y.f6171b, f8);
        objectAnimatorOfFloat.addListener(new b(view));
        a(new a(view));
        return objectAnimatorOfFloat;
    }

    private static float k0(r rVar, float f7) {
        Float f8;
        return (rVar == null || (f8 = (Float) rVar.f6160a.get("android:fade:transitionAlpha")) == null) ? f7 : f8.floatValue();
    }

    @Override // e1.f0
    public Animator e0(ViewGroup viewGroup, View view, r rVar, r rVar2) {
        float fK0 = k0(rVar, 0.0f);
        return j0(view, fK0 != 1.0f ? fK0 : 0.0f, 1.0f);
    }

    @Override // e1.f0
    public Animator g0(ViewGroup viewGroup, View view, r rVar, r rVar2) {
        y.e(view);
        return j0(view, k0(rVar, 1.0f), 0.0f);
    }

    @Override // e1.f0, e1.l
    public void i(r rVar) {
        super.i(rVar);
        rVar.f6160a.put("android:fade:transitionAlpha", Float.valueOf(y.c(rVar.f6161b)));
    }
}
