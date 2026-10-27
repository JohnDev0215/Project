package b.q;

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
        public final /* synthetic */ View f1449a;

        public a(View view) {
            this.f1449a = view;
        }

        @Override // b.q.l.f
        public void e(l lVar) {
            b0.g(this.f1449a, 1.0f);
            b0.a(this.f1449a);
            lVar.P(this);
        }
    }

    /* JADX INFO: compiled from: Fade.java */
    public static class b extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final View f1451a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1452b = false;

        public b(View view) {
            this.f1451a = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            b0.g(this.f1451a, 1.0f);
            if (this.f1452b) {
                this.f1451a.setLayerType(0, null);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            if (b.g.j.t.L(this.f1451a) && this.f1451a.getLayerType() == 0) {
                this.f1452b = true;
                this.f1451a.setLayerType(2, null);
            }
        }
    }

    public d(int i) {
        i0(i);
    }

    public static float k0(r rVar, float f) {
        Float f2;
        return (rVar == null || (f2 = (Float) rVar.f1512a.get("android:fade:transitionAlpha")) == null) ? f : f2.floatValue();
    }

    @Override // b.q.i0
    public Animator e0(ViewGroup viewGroup, View view, r rVar, r rVar2) {
        float fK0 = k0(rVar, 0.0f);
        return j0(view, fK0 != 1.0f ? fK0 : 0.0f, 1.0f);
    }

    @Override // b.q.i0
    public Animator g0(ViewGroup viewGroup, View view, r rVar, r rVar2) {
        b0.e(view);
        return j0(view, k0(rVar, 1.0f), 0.0f);
    }

    @Override // b.q.i0, b.q.l
    public void j(r rVar) {
        super.j(rVar);
        rVar.f1512a.put("android:fade:transitionAlpha", Float.valueOf(b0.c(rVar.f1513b)));
    }

    public final Animator j0(View view, float f, float f2) {
        if (f == f2) {
            return null;
        }
        b0.g(view, f);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, b0.f1429b, f2);
        objectAnimatorOfFloat.addListener(new b(view));
        a(new a(view));
        return objectAnimatorOfFloat;
    }
}
