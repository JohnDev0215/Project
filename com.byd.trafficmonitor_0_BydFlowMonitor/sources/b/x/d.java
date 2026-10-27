package b.x;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: compiled from: Fade.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends j0 {

    /* JADX INFO: compiled from: Fade.java */
    public class a extends n {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ View f3232a;

        public a(View view) {
            this.f3232a = view;
        }

        @Override // b.x.m.f
        public void e(m mVar) {
            c0.g(this.f3232a, 1.0f);
            c0.a(this.f3232a);
            mVar.Q(this);
        }
    }

    /* JADX INFO: compiled from: Fade.java */
    public static class b extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final View f3234a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f3235b = false;

        public b(View view) {
            this.f3234a = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            c0.g(this.f3234a, 1.0f);
            if (this.f3235b) {
                this.f3234a.setLayerType(0, null);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            if (b.h.m.t.L(this.f3234a) && this.f3234a.getLayerType() == 0) {
                this.f3235b = true;
                this.f3234a.setLayerType(2, null);
            }
        }
    }

    public d(int i2) {
        j0(i2);
    }

    public static float l0(s sVar, float f2) {
        Float f3;
        return (sVar == null || (f3 = (Float) sVar.f3315a.get("android:fade:transitionAlpha")) == null) ? f2 : f3.floatValue();
    }

    @Override // b.x.j0
    public Animator f0(ViewGroup viewGroup, View view, s sVar, s sVar2) {
        float fL0 = l0(sVar, 0.0f);
        return k0(view, fL0 != 1.0f ? fL0 : 0.0f, 1.0f);
    }

    @Override // b.x.j0
    public Animator h0(ViewGroup viewGroup, View view, s sVar, s sVar2) {
        c0.e(view);
        return k0(view, l0(sVar, 1.0f), 0.0f);
    }

    @Override // b.x.j0, b.x.m
    public void i(s sVar) {
        super.i(sVar);
        sVar.f3315a.put("android:fade:transitionAlpha", Float.valueOf(c0.c(sVar.f3316b)));
    }

    public final Animator k0(View view, float f2, float f3) {
        if (f2 == f3) {
            return null;
        }
        c0.g(view, f2);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, c0.f3230b, f3);
        objectAnimatorOfFloat.addListener(new b(view));
        a(new a(view));
        return objectAnimatorOfFloat;
    }
}
