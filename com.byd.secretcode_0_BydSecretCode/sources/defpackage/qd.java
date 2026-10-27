package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: compiled from: Fade.java */
/* JADX INFO: loaded from: classes.dex */
public class qd extends ve {

    /* JADX INFO: compiled from: Fade.java */
    public class a extends zd {
        public final /* synthetic */ View a;

        public a(View view) {
            this.a = view;
        }

        @Override // yd.f
        public void e(yd ydVar) {
            oe.g(this.a, 1.0f);
            oe.a(this.a);
            ydVar.P(this);
        }
    }

    /* JADX INFO: compiled from: Fade.java */
    public static class b extends AnimatorListenerAdapter {
        public final View a;
        public boolean b = false;

        public b(View view) {
            this.a = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            oe.g(this.a, 1.0f);
            if (this.b) {
                this.a.setLayerType(0, null);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            if (t9.L(this.a) && this.a.getLayerType() == 0) {
                this.b = true;
                this.a.setLayerType(2, null);
            }
        }
    }

    public qd(int i) {
        i0(i);
    }

    public static float k0(ee eeVar, float f) {
        Float f2;
        return (eeVar == null || (f2 = (Float) eeVar.a.get("android:fade:transitionAlpha")) == null) ? f : f2.floatValue();
    }

    @Override // defpackage.ve
    public Animator e0(ViewGroup viewGroup, View view, ee eeVar, ee eeVar2) {
        float fK0 = k0(eeVar, 0.0f);
        return j0(view, fK0 != 1.0f ? fK0 : 0.0f, 1.0f);
    }

    @Override // defpackage.ve
    public Animator g0(ViewGroup viewGroup, View view, ee eeVar, ee eeVar2) {
        oe.e(view);
        return j0(view, k0(eeVar, 1.0f), 0.0f);
    }

    @Override // defpackage.ve, defpackage.yd
    public void j(ee eeVar) {
        super.j(eeVar);
        eeVar.a.put("android:fade:transitionAlpha", Float.valueOf(oe.c(eeVar.b)));
    }

    public final Animator j0(View view, float f, float f2) {
        if (f == f2) {
            return null;
        }
        oe.g(view, f);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, oe.b, f2);
        objectAnimatorOfFloat.addListener(new b(view));
        a(new a(view));
        return objectAnimatorOfFloat;
    }
}
