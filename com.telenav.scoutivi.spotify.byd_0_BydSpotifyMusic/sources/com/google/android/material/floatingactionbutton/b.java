package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.util.Property;
import android.view.View;
import java.util.ArrayList;

/* JADX INFO: compiled from: FloatingActionButtonImplLollipop.java */
/* JADX INFO: loaded from: classes.dex */
class b extends a {
    b(FloatingActionButton floatingActionButton, d3.b bVar) {
        super(floatingActionButton, bVar);
    }

    private Animator e0(float f7, float f8) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ObjectAnimator.ofFloat(this.f4558x, "elevation", f7).setDuration(0L)).with(ObjectAnimator.ofFloat(this.f4558x, (Property<FloatingActionButton, Float>) View.TRANSLATION_Z, f8).setDuration(100L));
        animatorSet.setInterpolator(a.E);
        return animatorSet;
    }

    @Override // com.google.android.material.floatingactionbutton.a
    void A() {
        b0();
    }

    @Override // com.google.android.material.floatingactionbutton.a
    void C(int[] iArr) {
    }

    @Override // com.google.android.material.floatingactionbutton.a
    void D(float f7, float f8, float f9) {
        StateListAnimator stateListAnimator = new StateListAnimator();
        stateListAnimator.addState(a.F, e0(f7, f9));
        stateListAnimator.addState(a.G, e0(f7, f8));
        stateListAnimator.addState(a.H, e0(f7, f8));
        stateListAnimator.addState(a.I, e0(f7, f8));
        AnimatorSet animatorSet = new AnimatorSet();
        ArrayList arrayList = new ArrayList();
        arrayList.add(ObjectAnimator.ofFloat(this.f4558x, "elevation", f7).setDuration(0L));
        arrayList.add(ObjectAnimator.ofFloat(this.f4558x, (Property<FloatingActionButton, Float>) View.TRANSLATION_Z, 0.0f).setDuration(100L));
        animatorSet.playSequentially((Animator[]) arrayList.toArray(new Animator[0]));
        animatorSet.setInterpolator(a.E);
        stateListAnimator.addState(a.J, animatorSet);
        stateListAnimator.addState(a.K, e0(0.0f, 0.0f));
        this.f4558x.setStateListAnimator(stateListAnimator);
        if (V()) {
            b0();
        }
    }

    @Override // com.google.android.material.floatingactionbutton.a
    boolean I() {
        return false;
    }

    @Override // com.google.android.material.floatingactionbutton.a
    void R(ColorStateList colorStateList) {
        Drawable drawable = this.f4537c;
        if (drawable instanceof RippleDrawable) {
            ((RippleDrawable) drawable).setColor(c3.b.d(colorStateList));
        } else {
            super.R(colorStateList);
        }
    }

    @Override // com.google.android.material.floatingactionbutton.a
    boolean V() {
        return this.f4559y.b() || !X();
    }

    @Override // com.google.android.material.floatingactionbutton.a
    void Z() {
    }

    @Override // com.google.android.material.floatingactionbutton.a
    public float m() {
        return this.f4558x.getElevation();
    }

    @Override // com.google.android.material.floatingactionbutton.a
    void r(Rect rect) {
        if (this.f4559y.b()) {
            super.r(rect);
        } else if (X()) {
            rect.set(0, 0, 0, 0);
        } else {
            int sizeDimension = (this.f4544j - this.f4558x.getSizeDimension()) / 2;
            rect.set(sizeDimension, sizeDimension, sizeDimension, sizeDimension);
        }
    }

    @Override // com.google.android.material.floatingactionbutton.a
    void y() {
    }
}
