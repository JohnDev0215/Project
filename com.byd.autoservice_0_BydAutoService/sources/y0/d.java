package y0;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: compiled from: Fade.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends i0 {

    /* JADX INFO: compiled from: Fade.java */
    public class a extends m {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ View f5881a;

        public a(View view) {
            this.f5881a = view;
        }

        @Override // y0.l.f
        public void e(l lVar) {
            b0.g(this.f5881a, 1.0f);
            b0.a(this.f5881a);
            lVar.P(this);
        }
    }

    /* JADX INFO: compiled from: Fade.java */
    public static class b extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final View f5883a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f5884b = false;

        public b(View view) {
            this.f5883a = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            b0.g(this.f5883a, 1.0f);
            if (this.f5884b) {
                this.f5883a.setLayerType(0, null);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            if (l0.t.L(this.f5883a) && this.f5883a.getLayerType() == 0) {
                this.f5884b = true;
                this.f5883a.setLayerType(2, null);
            }
        }
    }

    public d(int i3) {
        i0(i3);
    }

    public static float k0(r rVar, float f3) {
        Float f4;
        return (rVar == null || (f4 = (Float) rVar.f5980a.get("android:fade:transitionAlpha")) == null) ? f3 : f4.floatValue();
    }

    @Override // y0.i0
    public Animator e0(ViewGroup viewGroup, View view, r rVar, r rVar2) {
        float fK0 = k0(rVar, 0.0f);
        return j0(view, fK0 != 1.0f ? fK0 : 0.0f, 1.0f);
    }

    @Override // y0.i0
    public Animator g0(ViewGroup viewGroup, View view, r rVar, r rVar2) {
        b0.e(view);
        return j0(view, k0(rVar, 1.0f), 0.0f);
    }

    @Override // y0.i0, y0.l
    public void j(r rVar) {
        super.j(rVar);
        rVar.f5980a.put("android:fade:transitionAlpha", Float.valueOf(b0.c(rVar.f5981b)));
    }

    public final Animator j0(View view, float f3, float f4) {
        if (f3 == f4) {
            return null;
        }
        b0.g(view, f3);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, b0.f5852b, f4);
        objectAnimatorOfFloat.addListener(new b(view));
        a(new a(view));
        return objectAnimatorOfFloat;
    }
}
