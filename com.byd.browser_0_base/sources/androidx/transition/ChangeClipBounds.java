package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import defpackage.df3;
import defpackage.in3;
import defpackage.om3;
import defpackage.wn3;
import defpackage.x92;
import java.util.HashMap;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class ChangeClipBounds extends Transition {
    public static final String[] C = {"android:clipBounds:clip"};

    public class a extends AnimatorListenerAdapter {
        public final /* synthetic */ View e;

        public a(View view) {
            this.e = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.f.b(this.e, null);
        }
    }

    public ChangeClipBounds() {
    }

    public final void Q(df3 df3Var) {
        View view = df3Var.b;
        if (view.getVisibility() == 8) {
            return;
        }
        WeakHashMap<View, in3> weakHashMap = om3.a;
        Rect rectA = om3.f.a(view);
        HashMap map = df3Var.a;
        map.put("android:clipBounds:clip", rectA);
        if (rectA == null) {
            map.put("android:clipBounds:bounds", new Rect(0, 0, view.getWidth(), view.getHeight()));
        }
    }

    @Override // androidx.transition.Transition
    public final void i(df3 df3Var) {
        Q(df3Var);
    }

    @Override // androidx.transition.Transition
    public final void l(df3 df3Var) {
        Q(df3Var);
    }

    @Override // androidx.transition.Transition
    public final Animator p(ViewGroup viewGroup, df3 df3Var, df3 df3Var2) {
        if (df3Var != null && df3Var2 != null) {
            HashMap map = df3Var.a;
            if (map.containsKey("android:clipBounds:clip")) {
                HashMap map2 = df3Var2.a;
                if (map2.containsKey("android:clipBounds:clip")) {
                    Rect rect = (Rect) map.get("android:clipBounds:clip");
                    Rect rect2 = (Rect) map2.get("android:clipBounds:clip");
                    boolean z = rect2 == null;
                    if (rect == null && rect2 == null) {
                        return null;
                    }
                    if (rect == null) {
                        rect = (Rect) map.get("android:clipBounds:bounds");
                    } else if (rect2 == null) {
                        rect2 = (Rect) map2.get("android:clipBounds:bounds");
                    }
                    if (rect.equals(rect2)) {
                        return null;
                    }
                    WeakHashMap<View, in3> weakHashMap = om3.a;
                    View view = df3Var2.b;
                    om3.f.b(view, rect);
                    ObjectAnimator objectAnimatorOfObject = ObjectAnimator.ofObject(view, wn3.b, new x92(new Rect()), rect, rect2);
                    if (z) {
                        objectAnimatorOfObject.addListener(new a(view));
                    }
                    return objectAnimatorOfObject;
                }
            }
        }
        return null;
    }

    @Override // androidx.transition.Transition
    public final String[] v() {
        return C;
    }

    public ChangeClipBounds(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
