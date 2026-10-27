package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import defpackage.df3;
import defpackage.fh3;
import defpackage.in3;
import defpackage.om3;
import defpackage.sv2;
import defpackage.wn3;
import java.util.HashMap;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class Fade extends Visibility {

    public static class a extends AnimatorListenerAdapter {
        public final View e;
        public boolean f = false;

        public a(View view) {
            this.e = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            wn3.a aVar = wn3.a;
            View view = this.e;
            view.setTransitionAlpha(1.0f);
            if (this.f) {
                view.setLayerType(0, null);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
            View view = this.e;
            if (om3.d.g(view) && view.getLayerType() == 0) {
                this.f = true;
                view.setLayerType(2, null);
            }
        }
    }

    public Fade(int i) {
        if ((i & (-4)) != 0) {
            throw new IllegalArgumentException("Only MODE_IN and MODE_OUT flags are allowed");
        }
        this.C = i;
    }

    @Override // androidx.transition.Visibility
    public final Animator T(ViewGroup viewGroup, View view, df3 df3Var, df3 df3Var2) {
        Float f;
        float fFloatValue = (df3Var == null || (f = (Float) df3Var.a.get("android:fade:transitionAlpha")) == null) ? 0.0f : f.floatValue();
        return V(view, fFloatValue != 1.0f ? fFloatValue : 0.0f, 1.0f);
    }

    @Override // androidx.transition.Visibility
    public final Animator U(ViewGroup viewGroup, View view, df3 df3Var) {
        Float f;
        wn3.a aVar = wn3.a;
        return V(view, (df3Var == null || (f = (Float) df3Var.a.get("android:fade:transitionAlpha")) == null) ? 1.0f : f.floatValue(), 0.0f);
    }

    public final ObjectAnimator V(View view, float f, float f2) {
        if (f == f2) {
            return null;
        }
        wn3.a aVar = wn3.a;
        view.setTransitionAlpha(f);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, wn3.a, f2);
        objectAnimatorOfFloat.addListener(new a(view));
        b(new b(view));
        return objectAnimatorOfFloat;
    }

    @Override // androidx.transition.Visibility, androidx.transition.Transition
    public final void l(df3 df3Var) {
        Q(df3Var);
        HashMap map = df3Var.a;
        wn3.a aVar = wn3.a;
        map.put("android:fade:transitionAlpha", Float.valueOf(df3Var.b.getTransitionAlpha()));
    }

    public Fade() {
    }

    @SuppressLint({"RestrictedApi"})
    public Fade(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, sv2.d);
        int iD = fh3.d(typedArrayObtainStyledAttributes, (XmlResourceParser) attributeSet, "fadingMode", 0, this.C);
        if ((iD & (-4)) == 0) {
            this.C = iD;
            typedArrayObtainStyledAttributes.recycle();
            return;
        }
        throw new IllegalArgumentException("Only MODE_IN and MODE_OUT flags are allowed");
    }
}
