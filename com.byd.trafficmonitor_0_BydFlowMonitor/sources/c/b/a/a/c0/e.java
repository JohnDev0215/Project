package c.b.a.a.c0;

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
import b.h.l.g;
import c.b.a.a.i0.h;
import c.b.a.a.i0.m;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

/* JADX INFO: compiled from: FloatingActionButtonImplLollipop.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends d {

    /* JADX INFO: compiled from: FloatingActionButtonImplLollipop.java */
    public static class a extends h {
        public a(m mVar) {
            super(mVar);
        }

        @Override // c.b.a.a.i0.h, android.graphics.drawable.Drawable
        public boolean isStateful() {
            return true;
        }
    }

    public e(FloatingActionButton floatingActionButton, c.b.a.a.h0.b bVar) {
        super(floatingActionButton, bVar);
    }

    @Override // c.b.a.a.c0.d
    public void A() {
    }

    @Override // c.b.a.a.c0.d
    public void C() {
        f0();
    }

    @Override // c.b.a.a.c0.d
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

    @Override // c.b.a.a.c0.d
    public void F(float f2, float f3, float f4) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 == 21) {
            this.F.refreshDrawableState();
        } else {
            StateListAnimator stateListAnimator = new StateListAnimator();
            stateListAnimator.addState(d.f3718b, j0(f2, f4));
            stateListAnimator.addState(d.f3719c, j0(f2, f3));
            stateListAnimator.addState(d.f3720d, j0(f2, f3));
            stateListAnimator.addState(d.f3721e, j0(f2, f3));
            AnimatorSet animatorSet = new AnimatorSet();
            ArrayList arrayList = new ArrayList();
            arrayList.add(ObjectAnimator.ofFloat(this.F, "elevation", f2).setDuration(0L));
            if (i2 >= 22 && i2 <= 24) {
                FloatingActionButton floatingActionButton = this.F;
                arrayList.add(ObjectAnimator.ofFloat(floatingActionButton, (Property<FloatingActionButton, Float>) View.TRANSLATION_Z, floatingActionButton.getTranslationZ()).setDuration(100L));
            }
            arrayList.add(ObjectAnimator.ofFloat(this.F, (Property<FloatingActionButton, Float>) View.TRANSLATION_Z, 0.0f).setDuration(100L));
            animatorSet.playSequentially((Animator[]) arrayList.toArray(new Animator[0]));
            animatorSet.setInterpolator(d.f3717a);
            stateListAnimator.addState(d.f3722f, animatorSet);
            stateListAnimator.addState(d.f3723g, j0(0.0f, 0.0f));
            this.F.setStateListAnimator(stateListAnimator);
        }
        if (Z()) {
            f0();
        }
    }

    @Override // c.b.a.a.c0.d
    public boolean K() {
        return false;
    }

    @Override // c.b.a.a.c0.d
    public void V(ColorStateList colorStateList) {
        Drawable drawable = this.j;
        if (drawable instanceof RippleDrawable) {
            ((RippleDrawable) drawable).setColor(c.b.a.a.g0.b.d(colorStateList));
        } else {
            super.V(colorStateList);
        }
    }

    @Override // c.b.a.a.c0.d
    public boolean Z() {
        return this.G.b() || !b0();
    }

    @Override // c.b.a.a.c0.d
    public void d0() {
    }

    public c i0(int i2, ColorStateList colorStateList) {
        Context context = this.F.getContext();
        c cVar = new c((m) g.c(this.f3724h));
        cVar.e(b.h.e.a.b(context, c.b.a.a.c.design_fab_stroke_top_outer_color), b.h.e.a.b(context, c.b.a.a.c.design_fab_stroke_top_inner_color), b.h.e.a.b(context, c.b.a.a.c.design_fab_stroke_end_inner_color), b.h.e.a.b(context, c.b.a.a.c.design_fab_stroke_end_outer_color));
        cVar.d(i2);
        cVar.c(colorStateList);
        return cVar;
    }

    @Override // c.b.a.a.c0.d
    public h j() {
        return new a((m) g.c(this.f3724h));
    }

    public final Animator j0(float f2, float f3) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ObjectAnimator.ofFloat(this.F, "elevation", f2).setDuration(0L)).with(ObjectAnimator.ofFloat(this.F, (Property<FloatingActionButton, Float>) View.TRANSLATION_Z, f3).setDuration(100L));
        animatorSet.setInterpolator(d.f3717a);
        return animatorSet;
    }

    @Override // c.b.a.a.c0.d
    public float n() {
        return this.F.getElevation();
    }

    @Override // c.b.a.a.c0.d
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

    @Override // c.b.a.a.c0.d
    public void x(ColorStateList colorStateList, PorterDuff.Mode mode, ColorStateList colorStateList2, int i2) {
        Drawable layerDrawable;
        h hVarJ = j();
        this.f3725i = hVarJ;
        hVarJ.setTintList(colorStateList);
        if (mode != null) {
            this.f3725i.setTintMode(mode);
        }
        this.f3725i.N(this.F.getContext());
        if (i2 > 0) {
            c cVarI0 = i0(i2, colorStateList);
            this.k = cVarI0;
            layerDrawable = new LayerDrawable(new Drawable[]{(Drawable) g.c(cVarI0), (Drawable) g.c(this.f3725i)});
        } else {
            this.k = null;
            layerDrawable = this.f3725i;
        }
        RippleDrawable rippleDrawable = new RippleDrawable(c.b.a.a.g0.b.d(colorStateList2), layerDrawable, null);
        this.j = rippleDrawable;
        this.l = rippleDrawable;
    }
}
