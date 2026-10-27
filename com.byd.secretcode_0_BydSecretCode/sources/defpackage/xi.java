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
import android.os.Build;
import android.util.Property;
import android.view.View;
import com.google.android.material.R$color;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

/* JADX INFO: compiled from: FloatingActionButtonImplLollipop.java */
/* JADX INFO: loaded from: classes.dex */
public class xi extends wi {

    /* JADX INFO: compiled from: FloatingActionButtonImplLollipop.java */
    public static class a extends hk {
        public a(mk mkVar) {
            super(mkVar);
        }

        @Override // defpackage.hk, android.graphics.drawable.Drawable
        public boolean isStateful() {
            return true;
        }
    }

    public xi(FloatingActionButton floatingActionButton, zj zjVar) {
        super(floatingActionButton, zjVar);
    }

    @Override // defpackage.wi
    public void A() {
    }

    @Override // defpackage.wi
    public void C() {
        f0();
    }

    @Override // defpackage.wi
    public void E(int[] iArr) {
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

    @Override // defpackage.wi
    public void F(float f, float f2, float f3) {
        int i = Build.VERSION.SDK_INT;
        if (i == 21) {
            this.F.refreshDrawableState();
        } else {
            StateListAnimator stateListAnimator = new StateListAnimator();
            stateListAnimator.addState(wi.b, j0(f, f3));
            stateListAnimator.addState(wi.c, j0(f, f2));
            stateListAnimator.addState(wi.d, j0(f, f2));
            stateListAnimator.addState(wi.e, j0(f, f2));
            AnimatorSet animatorSet = new AnimatorSet();
            ArrayList arrayList = new ArrayList();
            arrayList.add(ObjectAnimator.ofFloat(this.F, "elevation", f).setDuration(0L));
            if (i >= 22 && i <= 24) {
                FloatingActionButton floatingActionButton = this.F;
                arrayList.add(ObjectAnimator.ofFloat(floatingActionButton, (Property<FloatingActionButton, Float>) View.TRANSLATION_Z, floatingActionButton.getTranslationZ()).setDuration(100L));
            }
            arrayList.add(ObjectAnimator.ofFloat(this.F, (Property<FloatingActionButton, Float>) View.TRANSLATION_Z, 0.0f).setDuration(100L));
            animatorSet.playSequentially((Animator[]) arrayList.toArray(new Animator[0]));
            animatorSet.setInterpolator(wi.a);
            stateListAnimator.addState(wi.f, animatorSet);
            stateListAnimator.addState(wi.g, j0(0.0f, 0.0f));
            this.F.setStateListAnimator(stateListAnimator);
        }
        if (Z()) {
            f0();
        }
    }

    @Override // defpackage.wi
    public boolean K() {
        return false;
    }

    @Override // defpackage.wi
    public void V(ColorStateList colorStateList) {
        Drawable drawable = this.j;
        if (drawable instanceof RippleDrawable) {
            ((RippleDrawable) drawable).setColor(xj.d(colorStateList));
        } else {
            super.V(colorStateList);
        }
    }

    @Override // defpackage.wi
    public boolean Z() {
        return this.G.b() || !b0();
    }

    @Override // defpackage.wi
    public void d0() {
    }

    public vi i0(int i, ColorStateList colorStateList) {
        Context context = this.F.getContext();
        vi viVar = new vi((mk) z8.c(this.h));
        viVar.e(e7.b(context, R$color.design_fab_stroke_top_outer_color), e7.b(context, R$color.design_fab_stroke_top_inner_color), e7.b(context, R$color.design_fab_stroke_end_inner_color), e7.b(context, R$color.design_fab_stroke_end_outer_color));
        viVar.d(i);
        viVar.c(colorStateList);
        return viVar;
    }

    @Override // defpackage.wi
    public hk j() {
        return new a((mk) z8.c(this.h));
    }

    public final Animator j0(float f, float f2) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ObjectAnimator.ofFloat(this.F, "elevation", f).setDuration(0L)).with(ObjectAnimator.ofFloat(this.F, (Property<FloatingActionButton, Float>) View.TRANSLATION_Z, f2).setDuration(100L));
        animatorSet.setInterpolator(wi.a);
        return animatorSet;
    }

    @Override // defpackage.wi
    public float n() {
        return this.F.getElevation();
    }

    @Override // defpackage.wi
    public void s(Rect rect) {
        if (this.G.b()) {
            super.s(rect);
        } else if (b0()) {
            rect.set(0, 0, 0, 0);
        } else {
            int sizeDimension = (this.r - this.F.getSizeDimension()) / 2;
            rect.set(sizeDimension, sizeDimension, sizeDimension, sizeDimension);
        }
    }

    @Override // defpackage.wi
    public void x(ColorStateList colorStateList, PorterDuff.Mode mode, ColorStateList colorStateList2, int i) {
        Drawable layerDrawable;
        hk hkVarJ = j();
        this.i = hkVarJ;
        hkVarJ.setTintList(colorStateList);
        if (mode != null) {
            this.i.setTintMode(mode);
        }
        this.i.N(this.F.getContext());
        if (i > 0) {
            vi viVarI0 = i0(i, colorStateList);
            this.k = viVarI0;
            layerDrawable = new LayerDrawable(new Drawable[]{(Drawable) z8.c(viVarI0), (Drawable) z8.c(this.i)});
        } else {
            this.k = null;
            layerDrawable = this.i;
        }
        RippleDrawable rippleDrawable = new RippleDrawable(xj.d(colorStateList2), layerDrawable, null);
        this.j = rippleDrawable;
        this.l = rippleDrawable;
    }
}
