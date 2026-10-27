package b.o;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: compiled from: Fade.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends j0 {

    /* JADX INFO: compiled from: Fade.java */
    class a extends n {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ View f2832a;

        a(View view) {
            this.f2832a = view;
        }

        @Override // b.o.m.f
        public void e(m mVar) {
            c0.g(this.f2832a, 1.0f);
            c0.a(this.f2832a);
            mVar.T(this);
        }
    }

    /* JADX INFO: compiled from: Fade.java */
    private static class b extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final View f2834a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private boolean f2835b = false;

        b(View view) {
            this.f2834a = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            c0.g(this.f2834a, 1.0f);
            if (this.f2835b) {
                this.f2834a.setLayerType(0, null);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            if (b.g.k.t.L(this.f2834a) && this.f2834a.getLayerType() == 0) {
                this.f2835b = true;
                this.f2834a.setLayerType(2, null);
            }
        }
    }

    public d(int i2) {
        m0(i2);
    }

    private Animator n0(View view, float f2, float f3) {
        if (f2 == f3) {
            return null;
        }
        c0.g(view, f2);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, c0.f2830b, f3);
        objectAnimatorOfFloat.addListener(new b(view));
        b(new a(view));
        return objectAnimatorOfFloat;
    }

    private static float o0(s sVar, float f2) {
        Float f3;
        return (sVar == null || (f3 = (Float) sVar.f2919a.get("android:fade:transitionAlpha")) == null) ? f2 : f3.floatValue();
    }

    @Override // b.o.j0
    public Animator i0(ViewGroup viewGroup, View view, s sVar, s sVar2) {
        float fO0 = o0(sVar, 0.0f);
        return n0(view, fO0 != 1.0f ? fO0 : 0.0f, 1.0f);
    }

    @Override // b.o.j0
    public Animator k0(ViewGroup viewGroup, View view, s sVar, s sVar2) {
        c0.e(view);
        return n0(view, o0(sVar, 1.0f), 0.0f);
    }

    @Override // b.o.j0, b.o.m
    public void l(s sVar) {
        super.l(sVar);
        sVar.f2919a.put("android:fade:transitionAlpha", Float.valueOf(c0.c(sVar.f2920b)));
    }
}
