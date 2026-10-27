package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.util.Property;
import android.view.View;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
class c extends b {
    c(FloatingActionButton floatingActionButton, b.a.a.a.a0.b bVar) {
        super(floatingActionButton, bVar);
    }

    private Animator e0(float f, float f2) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ObjectAnimator.ofFloat(this.F, "elevation", f).setDuration(0L)).with(ObjectAnimator.ofFloat(this.F, (Property<FloatingActionButton, Float>) View.TRANSLATION_Z, f2).setDuration(100L));
        animatorSet.setInterpolator(b.f3103a);
        return animatorSet;
    }

    @Override // com.google.android.material.floatingactionbutton.b
    void A() {
        b0();
    }

    @Override // com.google.android.material.floatingactionbutton.b
    void C(int[] iArr) {
        FloatingActionButton floatingActionButton;
        if (Build.VERSION.SDK_INT == 21) {
            float f = 0.0f;
            if (this.F.isEnabled()) {
                this.F.setElevation(this.o);
                if (this.F.isPressed()) {
                    floatingActionButton = this.F;
                    f = this.q;
                } else if (this.F.isFocused() || this.F.isHovered()) {
                    floatingActionButton = this.F;
                    f = this.p;
                }
                floatingActionButton.setTranslationZ(f);
            }
            this.F.setElevation(0.0f);
            floatingActionButton = this.F;
            floatingActionButton.setTranslationZ(f);
        }
    }

    @Override // com.google.android.material.floatingactionbutton.b
    void D(float f, float f2, float f3) {
        int i = Build.VERSION.SDK_INT;
        if (i == 21) {
            this.F.refreshDrawableState();
        } else {
            StateListAnimator stateListAnimator = new StateListAnimator();
            stateListAnimator.addState(b.f3104b, e0(f, f3));
            stateListAnimator.addState(b.f3105c, e0(f, f2));
            stateListAnimator.addState(b.f3106d, e0(f, f2));
            stateListAnimator.addState(b.e, e0(f, f2));
            AnimatorSet animatorSet = new AnimatorSet();
            ArrayList arrayList = new ArrayList();
            arrayList.add(ObjectAnimator.ofFloat(this.F, "elevation", f).setDuration(0L));
            if (i >= 22 && i <= 24) {
                FloatingActionButton floatingActionButton = this.F;
                arrayList.add(ObjectAnimator.ofFloat(floatingActionButton, (Property<FloatingActionButton, Float>) View.TRANSLATION_Z, floatingActionButton.getTranslationZ()).setDuration(100L));
            }
            arrayList.add(ObjectAnimator.ofFloat(this.F, (Property<FloatingActionButton, Float>) View.TRANSLATION_Z, 0.0f).setDuration(100L));
            animatorSet.playSequentially((Animator[]) arrayList.toArray(new Animator[0]));
            animatorSet.setInterpolator(b.f3103a);
            stateListAnimator.addState(b.f, animatorSet);
            stateListAnimator.addState(b.g, e0(0.0f, 0.0f));
            this.F.setStateListAnimator(stateListAnimator);
        }
        if (V()) {
            b0();
        }
    }

    @Override // com.google.android.material.floatingactionbutton.b
    boolean I() {
        return false;
    }

    @Override // com.google.android.material.floatingactionbutton.b
    void R(ColorStateList colorStateList) {
        Drawable drawable = this.j;
        if (drawable instanceof RippleDrawable) {
            ((RippleDrawable) drawable).setColor(b.a.a.a.z.b.d(colorStateList));
        } else {
            super.R(colorStateList);
        }
    }

    @Override // com.google.android.material.floatingactionbutton.b
    boolean V() {
        return this.G.b() || !X();
    }

    @Override // com.google.android.material.floatingactionbutton.b
    void Z() {
    }

    @Override // com.google.android.material.floatingactionbutton.b
    public float m() {
        return this.F.getElevation();
    }

    @Override // com.google.android.material.floatingactionbutton.b
    void r(Rect rect) {
        if (this.G.b()) {
            super.r(rect);
        } else {
            int sizeDimension = !X() ? (this.r - this.F.getSizeDimension()) / 2 : 0;
            rect.set(sizeDimension, sizeDimension, sizeDimension, sizeDimension);
        }
    }

    @Override // com.google.android.material.floatingactionbutton.b
    void y() {
    }
}
