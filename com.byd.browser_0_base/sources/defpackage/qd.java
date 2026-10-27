package defpackage;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Property;
import android.view.View;
import android.view.animation.LinearInterpolator;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import java.util.ArrayList;

/* JADX INFO: compiled from: BaseMotionStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class qd implements gj1 {
    public final Context a;
    public final ExtendedFloatingActionButton b;
    public final ArrayList<Animator.AnimatorListener> c = new ArrayList<>();
    public final ph1 d;
    public fj1 e;
    public fj1 f;

    /* JADX INFO: compiled from: BaseMotionStrategy.java */
    public class a extends Property<ExtendedFloatingActionButton, Float> {
        public a() {
            super(Float.class, "LABEL_OPACITY_PROPERTY");
        }

        @Override // android.util.Property
        public final Float get(ExtendedFloatingActionButton extendedFloatingActionButton) {
            ExtendedFloatingActionButton extendedFloatingActionButton2 = extendedFloatingActionButton;
            float fAlpha = (Color.alpha(extendedFloatingActionButton2.getCurrentTextColor()) / 255.0f) / Color.alpha(extendedFloatingActionButton2.f30J.getColorForState(extendedFloatingActionButton2.getDrawableState(), qd.this.b.f30J.getDefaultColor()));
            LinearInterpolator linearInterpolator = u5.a;
            return Float.valueOf((fAlpha * 1.0f) + 0.0f);
        }

        @Override // android.util.Property
        public final void set(ExtendedFloatingActionButton extendedFloatingActionButton, Float f) {
            ExtendedFloatingActionButton extendedFloatingActionButton2 = extendedFloatingActionButton;
            Float f2 = f;
            int colorForState = extendedFloatingActionButton2.f30J.getColorForState(extendedFloatingActionButton2.getDrawableState(), qd.this.b.f30J.getDefaultColor());
            float fFloatValue = f2.floatValue();
            LinearInterpolator linearInterpolator = u5.a;
            ColorStateList colorStateListValueOf = ColorStateList.valueOf(Color.argb((int) (((((Color.alpha(colorForState) / 255.0f) - 0.0f) * fFloatValue) + 0.0f) * 255.0f), Color.red(colorForState), Color.green(colorForState), Color.blue(colorForState)));
            if (f2.floatValue() == 1.0f) {
                extendedFloatingActionButton2.g(extendedFloatingActionButton2.f30J);
            } else {
                extendedFloatingActionButton2.g(colorStateListValueOf);
            }
        }
    }

    public qd(ExtendedFloatingActionButton extendedFloatingActionButton, ph1 ph1Var) {
        this.b = extendedFloatingActionButton;
        this.a = extendedFloatingActionButton.getContext();
        this.d = ph1Var;
    }

    @Override // defpackage.gj1
    public void c() {
        this.d.e = null;
    }

    @Override // defpackage.gj1
    public AnimatorSet g() {
        fj1 fj1Var = this.f;
        if (fj1Var == null) {
            if (this.e == null) {
                this.e = fj1.b(this.a, d());
            }
            fj1Var = this.e;
            fj1Var.getClass();
        }
        return h(fj1Var);
    }

    public final AnimatorSet h(fj1 fj1Var) {
        ArrayList arrayList = new ArrayList();
        boolean zG = fj1Var.g("opacity");
        ExtendedFloatingActionButton extendedFloatingActionButton = this.b;
        if (zG) {
            arrayList.add(fj1Var.d("opacity", extendedFloatingActionButton, View.ALPHA));
        }
        if (fj1Var.g("scale")) {
            arrayList.add(fj1Var.d("scale", extendedFloatingActionButton, View.SCALE_Y));
            arrayList.add(fj1Var.d("scale", extendedFloatingActionButton, View.SCALE_X));
        }
        if (fj1Var.g("width")) {
            arrayList.add(fj1Var.d("width", extendedFloatingActionButton, ExtendedFloatingActionButton.L));
        }
        if (fj1Var.g("height")) {
            arrayList.add(fj1Var.d("height", extendedFloatingActionButton, ExtendedFloatingActionButton.M));
        }
        if (fj1Var.g("paddingStart")) {
            arrayList.add(fj1Var.d("paddingStart", extendedFloatingActionButton, ExtendedFloatingActionButton.N));
        }
        if (fj1Var.g("paddingEnd")) {
            arrayList.add(fj1Var.d("paddingEnd", extendedFloatingActionButton, ExtendedFloatingActionButton.O));
        }
        if (fj1Var.g("labelOpacity")) {
            arrayList.add(fj1Var.d("labelOpacity", extendedFloatingActionButton, new a()));
        }
        AnimatorSet animatorSet = new AnimatorSet();
        a8.a0(animatorSet, arrayList);
        return animatorSet;
    }
}
