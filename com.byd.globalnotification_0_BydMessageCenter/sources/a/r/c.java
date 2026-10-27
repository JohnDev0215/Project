package a.r;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public class c extends b0 {

    public class a extends l {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ View f1313a;

        public a(c cVar, View view) {
            this.f1313a = view;
        }

        @Override // a.r.i.d
        public void c(i iVar) {
            t.f(this.f1313a, 1.0f);
            iVar.w(this);
        }
    }

    public static class b extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final View f1314a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1315b = false;

        public b(View view) {
            this.f1314a = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            t.f(this.f1314a, 1.0f);
            if (this.f1315b) {
                this.f1314a.setLayerType(0, null);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            if (a.h.k.q.z(this.f1314a) && this.f1314a.getLayerType() == 0) {
                this.f1315b = true;
                this.f1314a.setLayerType(2, null);
            }
        }
    }

    public c(int i) {
        if ((i & (-4)) != 0) {
            throw new IllegalArgumentException("Only MODE_IN and MODE_OUT flags are allowed");
        }
        this.I = i;
    }

    @Override // a.r.b0
    public Animator K(ViewGroup viewGroup, View view, p pVar, p pVar2) {
        t.d(view);
        Float f2 = (Float) pVar.f1348a.get("android:fade:transitionAlpha");
        return L(view, f2 != null ? f2.floatValue() : 1.0f, 0.0f);
    }

    public final Animator L(View view, float f2, float f3) {
        if (f2 == f3) {
            return null;
        }
        t.f(view, f2);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, t.f1357b, f3);
        objectAnimatorOfFloat.addListener(new b(view));
        a(new a(this, view));
        return objectAnimatorOfFloat;
    }

    @Override // a.r.i
    public void h(p pVar) {
        I(pVar);
        pVar.f1348a.put("android:fade:transitionAlpha", Float.valueOf(t.b(pVar.f1349b)));
    }
}
