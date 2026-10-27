package com.google.android.material.transformation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import android.view.animation.LinearInterpolator;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import defpackage.C0173a;
import defpackage.fj1;
import defpackage.hj1;
import defpackage.u5;
import defpackage.z7;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class FabTransformationBehavior extends ExpandableTransformationBehavior {
    public final Rect c;
    public final RectF d;
    public final RectF e;
    public final int[] f;
    public float g;
    public float h;

    public class a extends AnimatorListenerAdapter {
        public final /* synthetic */ boolean e;
        public final /* synthetic */ View f;
        public final /* synthetic */ View g;

        public a(boolean z, View view, View view2) {
            this.e = z;
            this.f = view;
            this.g = view2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            if (this.e) {
                return;
            }
            this.f.setVisibility(4);
            View view = this.g;
            view.setAlpha(1.0f);
            view.setVisibility(0);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
            if (this.e) {
                this.f.setVisibility(0);
                View view = this.g;
                view.setAlpha(0.0f);
                view.setVisibility(4);
            }
        }
    }

    public static class b {
        public fj1 a;
        public z7 b;
    }

    public FabTransformationBehavior() {
        this.c = new Rect();
        this.d = new RectF();
        this.e = new RectF();
        this.f = new int[2];
    }

    public static Pair u(float f, float f2, boolean z, b bVar) {
        hj1 hj1VarF;
        hj1 hj1VarF2;
        if (f == 0.0f || f2 == 0.0f) {
            hj1VarF = bVar.a.f("translationXLinear");
            hj1VarF2 = bVar.a.f("translationYLinear");
        } else if ((!z || f2 >= 0.0f) && (z || f2 <= 0.0f)) {
            hj1VarF = bVar.a.f("translationXCurveDownwards");
            hj1VarF2 = bVar.a.f("translationYCurveDownwards");
        } else {
            hj1VarF = bVar.a.f("translationXCurveUpwards");
            hj1VarF2 = bVar.a.f("translationYCurveUpwards");
        }
        return new Pair(hj1VarF, hj1VarF2);
    }

    public static float x(b bVar, hj1 hj1Var, float f) {
        long j = hj1Var.a;
        hj1 hj1VarF = bVar.a.f("expansion");
        float interpolation = hj1Var.b().getInterpolation((((hj1VarF.a + hj1VarF.b) + 17) - j) / hj1Var.b);
        LinearInterpolator linearInterpolator = u5.a;
        return C0173a.f(0.0f, f, interpolation, f);
    }

    @Override // com.google.android.material.transformation.ExpandableBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean b(View view, View view2) {
        int expandedComponentIdHint;
        if (view.getVisibility() != 8) {
            return (view2 instanceof FloatingActionButton) && ((expandedComponentIdHint = ((FloatingActionButton) view2).getExpandedComponentIdHint()) == 0 || expandedComponentIdHint == view.getId());
        }
        throw new IllegalStateException("This behavior cannot be attached to a GONE view. Set the view to INVISIBLE instead.");
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final void c(CoordinatorLayout.e eVar) {
        if (eVar.h == 0) {
            eVar.h = 80;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:40:0x01a7  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0373  */
    @Override // com.google.android.material.transformation.ExpandableTransformationBehavior
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.animation.AnimatorSet t(android.view.View r24, android.view.View r25, boolean r26, boolean r27) {
        /*
            Method dump skipped, instruction units count: 976
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.transformation.FabTransformationBehavior.t(android.view.View, android.view.View, boolean, boolean):android.animation.AnimatorSet");
    }

    public final float v(View view, View view2, z7 z7Var) {
        RectF rectF = this.d;
        RectF rectF2 = this.e;
        y(view, rectF);
        rectF.offset(this.g, this.h);
        y(view2, rectF2);
        z7Var.getClass();
        return (rectF2.centerX() - rectF.centerX()) + 0.0f;
    }

    public final float w(View view, View view2, z7 z7Var) {
        RectF rectF = this.d;
        RectF rectF2 = this.e;
        y(view, rectF);
        rectF.offset(this.g, this.h);
        y(view2, rectF2);
        z7Var.getClass();
        return (rectF2.centerY() - rectF.centerY()) + 0.0f;
    }

    public final void y(View view, RectF rectF) {
        rectF.set(0.0f, 0.0f, view.getWidth(), view.getHeight());
        view.getLocationInWindow(this.f);
        rectF.offsetTo(r3[0], r3[1]);
        rectF.offset((int) (-view.getTranslationX()), (int) (-view.getTranslationY()));
    }

    public abstract b z(Context context, boolean z);

    public FabTransformationBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = new Rect();
        this.d = new RectF();
        this.e = new RectF();
        this.f = new int[2];
    }
}
