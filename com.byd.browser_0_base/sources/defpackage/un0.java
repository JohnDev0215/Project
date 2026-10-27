package defpackage;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.util.Property;
import android.view.View;
import com.google.android.material.R$color;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.floatingactionbutton.d;
import java.util.ArrayList;

/* JADX INFO: compiled from: FloatingActionButtonImplLollipop.java */
/* JADX INFO: loaded from: classes.dex */
public final class un0 extends d {

    /* JADX INFO: compiled from: FloatingActionButtonImplLollipop.java */
    public static class a extends sc1 {
        public a(uj2 uj2Var) {
            super(uj2Var);
        }

        @Override // defpackage.sc1, android.graphics.drawable.Drawable
        public final boolean isStateful() {
            return true;
        }
    }

    public un0(FloatingActionButton floatingActionButton, FloatingActionButton.b bVar) {
        super(floatingActionButton, bVar);
    }

    @Override // com.google.android.material.floatingactionbutton.d
    public final float e() {
        return this.v.getElevation();
    }

    @Override // com.google.android.material.floatingactionbutton.d
    public final void f(Rect rect) {
        if (FloatingActionButton.this.o) {
            super.f(rect);
            return;
        }
        boolean z = this.f;
        FloatingActionButton floatingActionButton = this.v;
        if (!z || floatingActionButton.getSizeDimension() >= this.k) {
            rect.set(0, 0, 0, 0);
        } else {
            int sizeDimension = (this.k - floatingActionButton.getSizeDimension()) / 2;
            rect.set(sizeDimension, sizeDimension, sizeDimension, sizeDimension);
        }
    }

    @Override // com.google.android.material.floatingactionbutton.d
    public final void g(ColorStateList colorStateList, PorterDuff.Mode mode, ColorStateList colorStateList2, int i) {
        Drawable layerDrawable;
        sc1 sc1VarS = s();
        this.b = sc1VarS;
        sc1VarS.setTintList(colorStateList);
        if (mode != null) {
            this.b.setTintMode(mode);
        }
        sc1 sc1Var = this.b;
        FloatingActionButton floatingActionButton = this.v;
        sc1Var.k(floatingActionButton.getContext());
        if (i > 0) {
            Context context = floatingActionButton.getContext();
            uj2 uj2Var = this.a;
            uj2Var.getClass();
            th thVar = new th(uj2Var);
            int i2 = R$color.design_fab_stroke_top_outer_color;
            Object obj = bz.a;
            int color = context.getColor(i2);
            int color2 = context.getColor(R$color.design_fab_stroke_top_inner_color);
            int color3 = context.getColor(R$color.design_fab_stroke_end_inner_color);
            int color4 = context.getColor(R$color.design_fab_stroke_end_outer_color);
            thVar.i = color;
            thVar.j = color2;
            thVar.k = color3;
            thVar.l = color4;
            float f = i;
            if (thVar.h != f) {
                thVar.h = f;
                thVar.b.setStrokeWidth(f * 1.3333f);
                thVar.n = true;
                thVar.invalidateSelf();
            }
            if (colorStateList != null) {
                thVar.m = colorStateList.getColorForState(thVar.getState(), thVar.m);
            }
            thVar.p = colorStateList;
            thVar.n = true;
            thVar.invalidateSelf();
            this.d = thVar;
            th thVar2 = this.d;
            thVar2.getClass();
            sc1 sc1Var2 = this.b;
            sc1Var2.getClass();
            layerDrawable = new LayerDrawable(new Drawable[]{thVar2, sc1Var2});
        } else {
            this.d = null;
            layerDrawable = this.b;
        }
        RippleDrawable rippleDrawable = new RippleDrawable(dc2.c(colorStateList2), layerDrawable, null);
        this.c = rippleDrawable;
        this.e = rippleDrawable;
    }

    @Override // com.google.android.material.floatingactionbutton.d
    public final void i() {
        q();
    }

    @Override // com.google.android.material.floatingactionbutton.d
    public final void k(float f, float f2, float f3) {
        StateListAnimator stateListAnimator = new StateListAnimator();
        stateListAnimator.addState(d.H, r(f, f3));
        stateListAnimator.addState(d.I, r(f, f2));
        stateListAnimator.addState(d.f31J, r(f, f2));
        stateListAnimator.addState(d.K, r(f, f2));
        AnimatorSet animatorSet = new AnimatorSet();
        ArrayList arrayList = new ArrayList();
        FloatingActionButton floatingActionButton = this.v;
        arrayList.add(ObjectAnimator.ofFloat(floatingActionButton, "elevation", f).setDuration(0L));
        arrayList.add(ObjectAnimator.ofFloat(floatingActionButton, (Property<FloatingActionButton, Float>) View.TRANSLATION_Z, 0.0f).setDuration(100L));
        animatorSet.playSequentially((Animator[]) arrayList.toArray(new Animator[0]));
        animatorSet.setInterpolator(d.C);
        stateListAnimator.addState(d.L, animatorSet);
        stateListAnimator.addState(d.M, r(0.0f, 0.0f));
        floatingActionButton.setStateListAnimator(stateListAnimator);
        if (o()) {
            q();
        }
    }

    @Override // com.google.android.material.floatingactionbutton.d
    public final void m(ColorStateList colorStateList) {
        Drawable drawable = this.c;
        if (drawable instanceof RippleDrawable) {
            ((RippleDrawable) drawable).setColor(dc2.c(colorStateList));
        } else {
            super.m(colorStateList);
        }
    }

    @Override // com.google.android.material.floatingactionbutton.d
    public final boolean o() {
        if (FloatingActionButton.this.o) {
            return true;
        }
        return !(!this.f || this.v.getSizeDimension() >= this.k);
    }

    public final AnimatorSet r(float f, float f2) {
        AnimatorSet animatorSet = new AnimatorSet();
        FloatingActionButton floatingActionButton = this.v;
        animatorSet.play(ObjectAnimator.ofFloat(floatingActionButton, "elevation", f).setDuration(0L)).with(ObjectAnimator.ofFloat(floatingActionButton, (Property<FloatingActionButton, Float>) View.TRANSLATION_Z, f2).setDuration(100L));
        animatorSet.setInterpolator(d.C);
        return animatorSet;
    }

    public final sc1 s() {
        uj2 uj2Var = this.a;
        uj2Var.getClass();
        return new a(uj2Var);
    }

    @Override // com.google.android.material.floatingactionbutton.d
    public final void h() {
    }

    @Override // com.google.android.material.floatingactionbutton.d
    public final void p() {
    }

    @Override // com.google.android.material.floatingactionbutton.d
    public final void j(int[] iArr) {
    }
}
