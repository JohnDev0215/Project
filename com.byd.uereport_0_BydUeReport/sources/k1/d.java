package k1;

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

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ View f5725d;

        public a(View view) {
            this.f5725d = view;
        }

        @Override // k1.l.f
        public void e(l lVar) {
            b0.g(this.f5725d, 1.0f);
            b0.a(this.f5725d);
            lVar.P(this);
        }
    }

    /* JADX INFO: compiled from: Fade.java */
    public static class b extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final View f5727d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f5728e = false;

        public b(View view) {
            this.f5727d = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            b0.g(this.f5727d, 1.0f);
            if (this.f5728e) {
                this.f5727d.setLayerType(0, null);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            if (o0.t.M(this.f5727d) && this.f5727d.getLayerType() == 0) {
                this.f5728e = true;
                this.f5727d.setLayerType(2, null);
            }
        }
    }

    public d(int i8) {
        i0(i8);
    }

    public static float k0(r rVar, float f8) {
        Float f9;
        return (rVar == null || (f9 = (Float) rVar.f5822a.get("android:fade:transitionAlpha")) == null) ? f8 : f9.floatValue();
    }

    @Override // k1.i0
    public Animator e0(ViewGroup viewGroup, View view, r rVar, r rVar2) {
        float fK0 = k0(rVar, 0.0f);
        return j0(view, fK0 != 1.0f ? fK0 : 0.0f, 1.0f);
    }

    @Override // k1.i0
    public Animator g0(ViewGroup viewGroup, View view, r rVar, r rVar2) {
        b0.e(view);
        return j0(view, k0(rVar, 1.0f), 0.0f);
    }

    @Override // k1.i0, k1.l
    public void j(r rVar) {
        super.j(rVar);
        rVar.f5822a.put("android:fade:transitionAlpha", Float.valueOf(b0.c(rVar.f5823b)));
    }

    public final Animator j0(View view, float f8, float f9) {
        if (f8 == f9) {
            return null;
        }
        b0.g(view, f8);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, b0.f5696b, f9);
        objectAnimatorOfFloat.addListener(new b(view));
        a(new a(view));
        return objectAnimatorOfFloat;
    }
}
