package com.google.android.material.floatingactionbutton;

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
import android.os.Build;
import android.util.Property;
import android.view.View;
import d.b.a.a.x.h;
import d.b.a.a.x.m;
import java.util.ArrayList;

/* JADX INFO: compiled from: FloatingActionButtonImplLollipop.java */
/* JADX INFO: loaded from: classes.dex */
class e extends d {

    /* JADX INFO: compiled from: FloatingActionButtonImplLollipop.java */
    static class a extends h {
        a(m mVar) {
            super(mVar);
        }

        @Override // d.b.a.a.x.h, android.graphics.drawable.Drawable
        public boolean isStateful() {
            return true;
        }
    }

    e(FloatingActionButton floatingActionButton, d.b.a.a.w.b bVar) {
        super(floatingActionButton, bVar);
    }

    private Animator j0(float f2, float f3) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ObjectAnimator.ofFloat(this.F, "elevation", f2).setDuration(0L)).with(ObjectAnimator.ofFloat(this.F, (Property<FloatingActionButton, Float>) View.TRANSLATION_Z, f3).setDuration(100L));
        animatorSet.setInterpolator(d.f4058a);
        return animatorSet;
    }

    @Override // com.google.android.material.floatingactionbutton.d
    void A() {
    }

    @Override // com.google.android.material.floatingactionbutton.d
    void C() {
        f0();
    }

    @Override // com.google.android.material.floatingactionbutton.d
    void E(int[] iArr) {
        if (Build.VERSION.SDK_INT == 21) {
            if (!this.F.isEnabled()) {
                this.F.setElevation(0.0f);
                this.F.setTranslationZ(0.0f);
                return;
            }
            this.F.setElevation(this.o);
            if (this.F.isPressed()) {
                this.F.setTranslationZ(this.q);
            } else if (this.F.isFocused() || this.F.isHovered()) {
                this.F.setTranslationZ(this.p);
            } else {
                this.F.setTranslationZ(0.0f);
            }
        }
    }

    @Override // com.google.android.material.floatingactionbutton.d
    void F(float f2, float f3, float f4) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 == 21) {
            this.F.refreshDrawableState();
        } else {
            StateListAnimator stateListAnimator = new StateListAnimator();
            stateListAnimator.addState(d.f4059b, j0(f2, f4));
            stateListAnimator.addState(d.f4060c, j0(f2, f3));
            stateListAnimator.addState(d.f4061d, j0(f2, f3));
            stateListAnimator.addState(d.f4062e, j0(f2, f3));
            AnimatorSet animatorSet = new AnimatorSet();
            ArrayList arrayList = new ArrayList();
            arrayList.add(ObjectAnimator.ofFloat(this.F, "elevation", f2).setDuration(0L));
            if (i2 >= 22 && i2 <= 24) {
                FloatingActionButton floatingActionButton = this.F;
                arrayList.add(ObjectAnimator.ofFloat(floatingActionButton, (Property<FloatingActionButton, Float>) View.TRANSLATION_Z, floatingActionButton.getTranslationZ()).setDuration(100L));
            }
            arrayList.add(ObjectAnimator.ofFloat(this.F, (Property<FloatingActionButton, Float>) View.TRANSLATION_Z, 0.0f).setDuration(100L));
            animatorSet.playSequentially((Animator[]) arrayList.toArray(new Animator[0]));
            animatorSet.setInterpolator(d.f4058a);
            stateListAnimator.addState(d.f4063f, animatorSet);
            stateListAnimator.addState(d.f4064g, j0(0.0f, 0.0f));
            this.F.setStateListAnimator(stateListAnimator);
        }
        if (Z()) {
            f0();
        }
    }

    @Override // com.google.android.material.floatingactionbutton.d
    boolean K() {
        return false;
    }

    @Override // com.google.android.material.floatingactionbutton.d
    void V(ColorStateList colorStateList) {
        Drawable drawable = this.j;
        if (drawable instanceof RippleDrawable) {
            ((RippleDrawable) drawable).setColor(d.b.a.a.v.b.d(colorStateList));
        } else {
            super.V(colorStateList);
        }
    }

    @Override // com.google.android.material.floatingactionbutton.d
    boolean Z() {
        return this.G.b() || !b0();
    }

    @Override // com.google.android.material.floatingactionbutton.d
    void d0() {
    }

    c i0(int i2, ColorStateList colorStateList) {
        Context context = this.F.getContext();
        c cVar = new c((m) b.g.j.h.c(this.f4065h));
        cVar.e(androidx.core.content.a.b(context, d.b.a.a.c.design_fab_stroke_top_outer_color), androidx.core.content.a.b(context, d.b.a.a.c.design_fab_stroke_top_inner_color), androidx.core.content.a.b(context, d.b.a.a.c.design_fab_stroke_end_inner_color), androidx.core.content.a.b(context, d.b.a.a.c.design_fab_stroke_end_outer_color));
        cVar.d(i2);
        cVar.c(colorStateList);
        return cVar;
    }

    @Override // com.google.android.material.floatingactionbutton.d
    h j() {
        return new a((m) b.g.j.h.c(this.f4065h));
    }

    @Override // com.google.android.material.floatingactionbutton.d
    public float n() {
        return this.F.getElevation();
    }

    @Override // com.google.android.material.floatingactionbutton.d
    void s(Rect rect) {
        if (this.G.b()) {
            super.s(rect);
        } else if (b0()) {
            rect.set(0, 0, 0, 0);
        } else {
            int sizeDimension = (this.r - this.F.getSizeDimension()) / 2;
            rect.set(sizeDimension, sizeDimension, sizeDimension, sizeDimension);
        }
    }

    @Override // com.google.android.material.floatingactionbutton.d
    void x(ColorStateList colorStateList, PorterDuff.Mode mode, ColorStateList colorStateList2, int i2) {
        Drawable layerDrawable;
        h hVarJ = j();
        this.f4066i = hVarJ;
        hVarJ.setTintList(colorStateList);
        if (mode != null) {
            this.f4066i.setTintMode(mode);
        }
        this.f4066i.N(this.F.getContext());
        if (i2 > 0) {
            c cVarI0 = i0(i2, colorStateList);
            this.k = cVarI0;
            layerDrawable = new LayerDrawable(new Drawable[]{(Drawable) b.g.j.h.c(cVarI0), (Drawable) b.g.j.h.c(this.f4066i)});
        } else {
            this.k = null;
            layerDrawable = this.f4066i;
        }
        RippleDrawable rippleDrawable = new RippleDrawable(d.b.a.a.v.b.d(colorStateList2), layerDrawable, null);
        this.j = rippleDrawable;
        this.l = rippleDrawable;
    }
}
