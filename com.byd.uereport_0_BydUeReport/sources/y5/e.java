package y5;

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
import e6.h;
import e6.m;
import java.util.ArrayList;

/* JADX INFO: compiled from: FloatingActionButtonImplLollipop.java */
/* JADX INFO: loaded from: classes2.dex */
public class e extends d {

    /* JADX INFO: compiled from: FloatingActionButtonImplLollipop.java */
    public static class a extends h {
        public a(m mVar) {
            super(mVar);
        }

        @Override // e6.h, android.graphics.drawable.Drawable
        public boolean isStateful() {
            return true;
        }
    }

    public e(FloatingActionButton floatingActionButton, d6.b bVar) {
        super(floatingActionButton, bVar);
    }

    @Override // y5.d
    public void A() {
    }

    @Override // y5.d
    public void C() {
        f0();
    }

    @Override // y5.d
    public void E(int[] iArr) {
        if (Build.VERSION.SDK_INT == 21) {
            if (!this.F.isEnabled()) {
                this.F.setElevation(0.0f);
                this.F.setTranslationZ(0.0f);
                return;
            }
            this.F.setElevation(this.f9360o);
            if (this.F.isPressed()) {
                this.F.setTranslationZ(this.f9362q);
            } else if (this.F.isFocused() || this.F.isHovered()) {
                this.F.setTranslationZ(this.f9361p);
            } else {
                this.F.setTranslationZ(0.0f);
            }
        }
    }

    @Override // y5.d
    public void F(float f8, float f9, float f10) {
        int i8 = Build.VERSION.SDK_INT;
        if (i8 == 21) {
            this.F.refreshDrawableState();
        } else {
            StateListAnimator stateListAnimator = new StateListAnimator();
            stateListAnimator.addState(d.f9347b, j0(f8, f10));
            stateListAnimator.addState(d.f9348c, j0(f8, f9));
            stateListAnimator.addState(d.f9349d, j0(f8, f9));
            stateListAnimator.addState(d.f9350e, j0(f8, f9));
            AnimatorSet animatorSet = new AnimatorSet();
            ArrayList arrayList = new ArrayList();
            arrayList.add(ObjectAnimator.ofFloat(this.F, "elevation", f8).setDuration(0L));
            if (i8 >= 22 && i8 <= 24) {
                FloatingActionButton floatingActionButton = this.F;
                arrayList.add(ObjectAnimator.ofFloat(floatingActionButton, (Property<FloatingActionButton, Float>) View.TRANSLATION_Z, floatingActionButton.getTranslationZ()).setDuration(100L));
            }
            arrayList.add(ObjectAnimator.ofFloat(this.F, (Property<FloatingActionButton, Float>) View.TRANSLATION_Z, 0.0f).setDuration(100L));
            animatorSet.playSequentially((Animator[]) arrayList.toArray(new Animator[0]));
            animatorSet.setInterpolator(d.f9346a);
            stateListAnimator.addState(d.f9351f, animatorSet);
            stateListAnimator.addState(d.f9352g, j0(0.0f, 0.0f));
            this.F.setStateListAnimator(stateListAnimator);
        }
        if (Z()) {
            f0();
        }
    }

    @Override // y5.d
    public boolean K() {
        return false;
    }

    @Override // y5.d
    public void V(ColorStateList colorStateList) {
        Drawable drawable = this.f9355j;
        if (drawable instanceof RippleDrawable) {
            ((RippleDrawable) drawable).setColor(c6.b.d(colorStateList));
        } else {
            super.V(colorStateList);
        }
    }

    @Override // y5.d
    public boolean Z() {
        return this.G.c() || !b0();
    }

    @Override // y5.d
    public void d0() {
    }

    public c i0(int i8, ColorStateList colorStateList) {
        Context context = this.F.getContext();
        c cVar = new c((m) n0.h.e(this.f9353h));
        cVar.e(e0.a.b(context, R$color.design_fab_stroke_top_outer_color), e0.a.b(context, R$color.design_fab_stroke_top_inner_color), e0.a.b(context, R$color.design_fab_stroke_end_inner_color), e0.a.b(context, R$color.design_fab_stroke_end_outer_color));
        cVar.d(i8);
        cVar.c(colorStateList);
        return cVar;
    }

    @Override // y5.d
    public h j() {
        return new a((m) n0.h.e(this.f9353h));
    }

    public final Animator j0(float f8, float f9) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ObjectAnimator.ofFloat(this.F, "elevation", f8).setDuration(0L)).with(ObjectAnimator.ofFloat(this.F, (Property<FloatingActionButton, Float>) View.TRANSLATION_Z, f9).setDuration(100L));
        animatorSet.setInterpolator(d.f9346a);
        return animatorSet;
    }

    @Override // y5.d
    public float n() {
        return this.F.getElevation();
    }

    @Override // y5.d
    public void s(Rect rect) {
        if (this.G.c()) {
            super.s(rect);
        } else if (b0()) {
            rect.set(0, 0, 0, 0);
        } else {
            int sizeDimension = (this.f9363r - this.F.getSizeDimension()) / 2;
            rect.set(sizeDimension, sizeDimension, sizeDimension, sizeDimension);
        }
    }

    @Override // y5.d
    public void x(ColorStateList colorStateList, PorterDuff.Mode mode, ColorStateList colorStateList2, int i8) {
        Drawable layerDrawable;
        h hVarJ = j();
        this.f9354i = hVarJ;
        hVarJ.setTintList(colorStateList);
        if (mode != null) {
            this.f9354i.setTintMode(mode);
        }
        this.f9354i.N(this.F.getContext());
        if (i8 > 0) {
            c cVarI0 = i0(i8, colorStateList);
            this.f9356k = cVarI0;
            layerDrawable = new LayerDrawable(new Drawable[]{(Drawable) n0.h.e(cVarI0), (Drawable) n0.h.e(this.f9354i)});
        } else {
            this.f9356k = null;
            layerDrawable = this.f9354i;
        }
        RippleDrawable rippleDrawable = new RippleDrawable(c6.b.d(colorStateList2), layerDrawable, null);
        this.f9355j = rippleDrawable;
        this.f9357l = rippleDrawable;
    }
}
