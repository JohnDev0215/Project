package h2;

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
import n2.h;
import n2.m;

/* JADX INFO: compiled from: FloatingActionButtonImplLollipop.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends d {

    /* JADX INFO: compiled from: FloatingActionButtonImplLollipop.java */
    public static class a extends h {
        public a(m mVar) {
            super(mVar);
        }

        @Override // n2.h, android.graphics.drawable.Drawable
        public boolean isStateful() {
            return true;
        }
    }

    public e(FloatingActionButton floatingActionButton, m2.b bVar) {
        super(floatingActionButton, bVar);
    }

    @Override // h2.d
    public void A() {
    }

    @Override // h2.d
    public void C() {
        f0();
    }

    @Override // h2.d
    public void E(int[] iArr) {
        if (Build.VERSION.SDK_INT == 21) {
            if (!this.F.isEnabled()) {
                this.F.setElevation(0.0f);
                this.F.setTranslationZ(0.0f);
                return;
            }
            this.F.setElevation(this.f3104o);
            if (this.F.isPressed()) {
                this.F.setTranslationZ(this.f3106q);
            } else if (this.F.isFocused() || this.F.isHovered()) {
                this.F.setTranslationZ(this.f3105p);
            } else {
                this.F.setTranslationZ(0.0f);
            }
        }
    }

    @Override // h2.d
    public void F(float f3, float f4, float f5) {
        int i3 = Build.VERSION.SDK_INT;
        if (i3 == 21) {
            this.F.refreshDrawableState();
        } else {
            StateListAnimator stateListAnimator = new StateListAnimator();
            stateListAnimator.addState(d.f3091b, j0(f3, f5));
            stateListAnimator.addState(d.f3092c, j0(f3, f4));
            stateListAnimator.addState(d.f3093d, j0(f3, f4));
            stateListAnimator.addState(d.f3094e, j0(f3, f4));
            AnimatorSet animatorSet = new AnimatorSet();
            ArrayList arrayList = new ArrayList();
            arrayList.add(ObjectAnimator.ofFloat(this.F, "elevation", f3).setDuration(0L));
            if (i3 >= 22 && i3 <= 24) {
                FloatingActionButton floatingActionButton = this.F;
                arrayList.add(ObjectAnimator.ofFloat(floatingActionButton, (Property<FloatingActionButton, Float>) View.TRANSLATION_Z, floatingActionButton.getTranslationZ()).setDuration(100L));
            }
            arrayList.add(ObjectAnimator.ofFloat(this.F, (Property<FloatingActionButton, Float>) View.TRANSLATION_Z, 0.0f).setDuration(100L));
            animatorSet.playSequentially((Animator[]) arrayList.toArray(new Animator[0]));
            animatorSet.setInterpolator(d.f3090a);
            stateListAnimator.addState(d.f3095f, animatorSet);
            stateListAnimator.addState(d.f3096g, j0(0.0f, 0.0f));
            this.F.setStateListAnimator(stateListAnimator);
        }
        if (Z()) {
            f0();
        }
    }

    @Override // h2.d
    public boolean K() {
        return false;
    }

    @Override // h2.d
    public void V(ColorStateList colorStateList) {
        Drawable drawable = this.f3099j;
        if (drawable instanceof RippleDrawable) {
            ((RippleDrawable) drawable).setColor(l2.b.d(colorStateList));
        } else {
            super.V(colorStateList);
        }
    }

    @Override // h2.d
    public boolean Z() {
        return this.G.b() || !b0();
    }

    @Override // h2.d
    public void d0() {
    }

    public c i0(int i3, ColorStateList colorStateList) {
        Context context = this.F.getContext();
        c cVar = new c((m) k0.h.c(this.f3097h));
        cVar.e(b0.a.b(context, R$color.design_fab_stroke_top_outer_color), b0.a.b(context, R$color.design_fab_stroke_top_inner_color), b0.a.b(context, R$color.design_fab_stroke_end_inner_color), b0.a.b(context, R$color.design_fab_stroke_end_outer_color));
        cVar.d(i3);
        cVar.c(colorStateList);
        return cVar;
    }

    @Override // h2.d
    public h j() {
        return new a((m) k0.h.c(this.f3097h));
    }

    public final Animator j0(float f3, float f4) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ObjectAnimator.ofFloat(this.F, "elevation", f3).setDuration(0L)).with(ObjectAnimator.ofFloat(this.F, (Property<FloatingActionButton, Float>) View.TRANSLATION_Z, f4).setDuration(100L));
        animatorSet.setInterpolator(d.f3090a);
        return animatorSet;
    }

    @Override // h2.d
    public float n() {
        return this.F.getElevation();
    }

    @Override // h2.d
    public void s(Rect rect) {
        if (this.G.b()) {
            super.s(rect);
        } else if (b0()) {
            rect.set(0, 0, 0, 0);
        } else {
            int sizeDimension = (this.f3107r - this.F.getSizeDimension()) / 2;
            rect.set(sizeDimension, sizeDimension, sizeDimension, sizeDimension);
        }
    }

    @Override // h2.d
    public void x(ColorStateList colorStateList, PorterDuff.Mode mode, ColorStateList colorStateList2, int i3) {
        Drawable layerDrawable;
        h hVarJ = j();
        this.f3098i = hVarJ;
        hVarJ.setTintList(colorStateList);
        if (mode != null) {
            this.f3098i.setTintMode(mode);
        }
        this.f3098i.N(this.F.getContext());
        if (i3 > 0) {
            c cVarI0 = i0(i3, colorStateList);
            this.f3100k = cVarI0;
            layerDrawable = new LayerDrawable(new Drawable[]{(Drawable) k0.h.c(cVarI0), (Drawable) k0.h.c(this.f3098i)});
        } else {
            this.f3100k = null;
            layerDrawable = this.f3098i;
        }
        RippleDrawable rippleDrawable = new RippleDrawable(l2.b.d(colorStateList2), layerDrawable, null);
        this.f3099j = rippleDrawable;
        this.f3101l = rippleDrawable;
    }
}
