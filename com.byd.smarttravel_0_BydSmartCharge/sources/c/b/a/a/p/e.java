package c.b.a.a.p;

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
import c.b.a.a.v.h;
import c.b.a.a.v.m;
import com.google.android.material.R$color;
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

        @Override // c.b.a.a.v.h, android.graphics.drawable.Drawable
        public boolean isStateful() {
            return true;
        }
    }

    public e(FloatingActionButton floatingActionButton, c.b.a.a.u.b bVar) {
        super(floatingActionButton, bVar);
    }

    @Override // c.b.a.a.p.d
    public void A() {
    }

    @Override // c.b.a.a.p.d
    public void C() {
        f0();
    }

    @Override // c.b.a.a.p.d
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

    @Override // c.b.a.a.p.d
    public void F(float f, float f2, float f3) {
        int i = Build.VERSION.SDK_INT;
        if (i == 21) {
            this.F.refreshDrawableState();
        } else {
            StateListAnimator stateListAnimator = new StateListAnimator();
            stateListAnimator.addState(d.f1755b, j0(f, f3));
            stateListAnimator.addState(d.f1756c, j0(f, f2));
            stateListAnimator.addState(d.f1757d, j0(f, f2));
            stateListAnimator.addState(d.e, j0(f, f2));
            AnimatorSet animatorSet = new AnimatorSet();
            ArrayList arrayList = new ArrayList();
            arrayList.add(ObjectAnimator.ofFloat(this.F, "elevation", f).setDuration(0L));
            if (i >= 22 && i <= 24) {
                FloatingActionButton floatingActionButton = this.F;
                arrayList.add(ObjectAnimator.ofFloat(floatingActionButton, (Property<FloatingActionButton, Float>) View.TRANSLATION_Z, floatingActionButton.getTranslationZ()).setDuration(100L));
            }
            arrayList.add(ObjectAnimator.ofFloat(this.F, (Property<FloatingActionButton, Float>) View.TRANSLATION_Z, 0.0f).setDuration(100L));
            animatorSet.playSequentially((Animator[]) arrayList.toArray(new Animator[0]));
            animatorSet.setInterpolator(d.f1754a);
            stateListAnimator.addState(d.f, animatorSet);
            stateListAnimator.addState(d.g, j0(0.0f, 0.0f));
            this.F.setStateListAnimator(stateListAnimator);
        }
        if (Z()) {
            f0();
        }
    }

    @Override // c.b.a.a.p.d
    public boolean K() {
        return false;
    }

    @Override // c.b.a.a.p.d
    public void V(ColorStateList colorStateList) {
        Drawable drawable = this.j;
        if (drawable instanceof RippleDrawable) {
            ((RippleDrawable) drawable).setColor(c.b.a.a.t.b.d(colorStateList));
        } else {
            super.V(colorStateList);
        }
    }

    @Override // c.b.a.a.p.d
    public boolean Z() {
        return this.G.b() || !b0();
    }

    @Override // c.b.a.a.p.d
    public void d0() {
    }

    public c i0(int i, ColorStateList colorStateList) {
        Context context = this.F.getContext();
        c cVar = new c((m) b.g.i.h.c(this.h));
        cVar.e(b.g.b.a.b(context, R$color.design_fab_stroke_top_outer_color), b.g.b.a.b(context, R$color.design_fab_stroke_top_inner_color), b.g.b.a.b(context, R$color.design_fab_stroke_end_inner_color), b.g.b.a.b(context, R$color.design_fab_stroke_end_outer_color));
        cVar.d(i);
        cVar.c(colorStateList);
        return cVar;
    }

    @Override // c.b.a.a.p.d
    public h j() {
        return new a((m) b.g.i.h.c(this.h));
    }

    public final Animator j0(float f, float f2) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ObjectAnimator.ofFloat(this.F, "elevation", f).setDuration(0L)).with(ObjectAnimator.ofFloat(this.F, (Property<FloatingActionButton, Float>) View.TRANSLATION_Z, f2).setDuration(100L));
        animatorSet.setInterpolator(d.f1754a);
        return animatorSet;
    }

    @Override // c.b.a.a.p.d
    public float n() {
        return this.F.getElevation();
    }

    @Override // c.b.a.a.p.d
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

    @Override // c.b.a.a.p.d
    public void x(ColorStateList colorStateList, PorterDuff.Mode mode, ColorStateList colorStateList2, int i) {
        Drawable layerDrawable;
        h hVarJ = j();
        this.i = hVarJ;
        hVarJ.setTintList(colorStateList);
        if (mode != null) {
            this.i.setTintMode(mode);
        }
        this.i.N(this.F.getContext());
        if (i > 0) {
            this.k = i0(i, colorStateList);
            layerDrawable = new LayerDrawable(new Drawable[]{(Drawable) b.g.i.h.c(this.k), (Drawable) b.g.i.h.c(this.i)});
        } else {
            this.k = null;
            layerDrawable = this.i;
        }
        RippleDrawable rippleDrawable = new RippleDrawable(c.b.a.a.t.b.d(colorStateList2), layerDrawable, null);
        this.j = rippleDrawable;
        this.l = rippleDrawable;
    }
}
