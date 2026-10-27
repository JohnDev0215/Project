package c.n;

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
        final /* synthetic */ View f2426a;

        a(View view) {
            this.f2426a = view;
        }

        @Override // c.n.m.f
        public void e(m mVar) {
            c0.g(this.f2426a, 1.0f);
            c0.a(this.f2426a);
            mVar.P(this);
        }
    }

    /* JADX INFO: compiled from: Fade.java */
    private static class b extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final View f2428a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private boolean f2429b = false;

        b(View view) {
            this.f2428a = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            c0.g(this.f2428a, 1.0f);
            if (this.f2429b) {
                this.f2428a.setLayerType(0, null);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            if (c.g.k.t.L(this.f2428a) && this.f2428a.getLayerType() == 0) {
                this.f2429b = true;
                this.f2428a.setLayerType(2, null);
            }
        }
    }

    public d(int i) {
        i0(i);
    }

    private Animator j0(View view, float f, float f2) {
        if (f == f2) {
            return null;
        }
        c0.g(view, f);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, c0.f2424b, f2);
        objectAnimatorOfFloat.addListener(new b(view));
        a(new a(view));
        return objectAnimatorOfFloat;
    }

    private static float k0(s sVar, float f) {
        Float f2;
        return (sVar == null || (f2 = (Float) sVar.f2493a.get("android:fade:transitionAlpha")) == null) ? f : f2.floatValue();
    }

    @Override // c.n.j0
    public Animator e0(ViewGroup viewGroup, View view, s sVar, s sVar2) {
        float fK0 = k0(sVar, 0.0f);
        return j0(view, fK0 != 1.0f ? fK0 : 0.0f, 1.0f);
    }

    @Override // c.n.j0
    public Animator g0(ViewGroup viewGroup, View view, s sVar, s sVar2) {
        c0.e(view);
        return j0(view, k0(sVar, 1.0f), 0.0f);
    }

    @Override // c.n.j0, c.n.m
    public void j(s sVar) {
        super.j(sVar);
        sVar.f2493a.put("android:fade:transitionAlpha", Float.valueOf(c0.c(sVar.f2494b)));
    }
}
