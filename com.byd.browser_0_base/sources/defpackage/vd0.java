package defpackage;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.provider.Settings;
import android.util.Property;
import defpackage.l5;
import java.util.ArrayList;

/* JADX INFO: compiled from: DrawableWithAnimatedVisibilityChange.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class vd0 extends Drawable implements l5 {
    public static final a o = new a();
    public final Context e;
    public final td f;
    public ValueAnimator h;
    public ValueAnimator i;
    public ArrayList j;
    public boolean k;
    public float l;
    public int n;
    public final Paint m = new Paint();
    public w5 g = new w5();

    /* JADX INFO: compiled from: DrawableWithAnimatedVisibilityChange.java */
    public class a extends Property<vd0, Float> {
        public a() {
            super(Float.class, "growFraction");
        }

        @Override // android.util.Property
        public final Float get(vd0 vd0Var) {
            return Float.valueOf(vd0Var.c());
        }

        @Override // android.util.Property
        public final void set(vd0 vd0Var, Float f) {
            vd0 vd0Var2 = vd0Var;
            float fFloatValue = f.floatValue();
            if (vd0Var2.l != fFloatValue) {
                vd0Var2.l = fFloatValue;
                vd0Var2.invalidateSelf();
            }
        }
    }

    public vd0(Context context, td tdVar) {
        this.e = context;
        this.f = tdVar;
        setAlpha(255);
    }

    @Override // defpackage.l5
    public final void a(l5.a aVar) {
        if (this.j == null) {
            this.j = new ArrayList();
        }
        if (this.j.contains(aVar)) {
            return;
        }
        this.j.add(aVar);
    }

    public final float c() {
        td tdVar = this.f;
        if (!(tdVar.e != 0)) {
            if (!(tdVar.f != 0)) {
                return 1.0f;
            }
        }
        return this.l;
    }

    public final boolean d() {
        ValueAnimator valueAnimator = this.i;
        return valueAnimator != null && valueAnimator.isRunning();
    }

    public final boolean e() {
        ValueAnimator valueAnimator = this.h;
        return valueAnimator != null && valueAnimator.isRunning();
    }

    public final boolean f(boolean z, boolean z2, boolean z3) {
        w5 w5Var = this.g;
        ContentResolver contentResolver = this.e.getContentResolver();
        w5Var.getClass();
        return g(z, z2, z3 && Settings.Global.getFloat(contentResolver, "animator_duration_scale", 1.0f) > 0.0f);
    }

    public boolean g(boolean z, boolean z2, boolean z3) {
        ValueAnimator valueAnimator = this.h;
        a aVar = o;
        if (valueAnimator == null) {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, aVar, 0.0f, 1.0f);
            this.h = objectAnimatorOfFloat;
            objectAnimatorOfFloat.setDuration(500L);
            this.h.setInterpolator(u5.b);
            ValueAnimator valueAnimator2 = this.h;
            if (valueAnimator2 != null && valueAnimator2.isRunning()) {
                throw new IllegalArgumentException("Cannot set showAnimator while the current showAnimator is running.");
            }
            this.h = valueAnimator2;
            valueAnimator2.addListener(new td0(this));
        }
        if (this.i == null) {
            ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this, aVar, 1.0f, 0.0f);
            this.i = objectAnimatorOfFloat2;
            objectAnimatorOfFloat2.setDuration(500L);
            this.i.setInterpolator(u5.b);
            ValueAnimator valueAnimator3 = this.i;
            if (valueAnimator3 != null && valueAnimator3.isRunning()) {
                throw new IllegalArgumentException("Cannot set hideAnimator while the current hideAnimator is running.");
            }
            this.i = valueAnimator3;
            valueAnimator3.addListener(new ud0(this));
        }
        boolean z4 = false;
        if (!isVisible() && !z) {
            return false;
        }
        ValueAnimator valueAnimator4 = z ? this.h : this.i;
        if (!z3) {
            if (valueAnimator4.isRunning()) {
                valueAnimator4.end();
            } else {
                boolean z5 = this.k;
                this.k = true;
                valueAnimator4.end();
                this.k = z5;
            }
            return super.setVisible(z, false);
        }
        if (z3 && valueAnimator4.isRunning()) {
            return false;
        }
        boolean z6 = !z || super.setVisible(z, false);
        td tdVar = this.f;
        if (!z ? tdVar.f != 0 : tdVar.e != 0) {
            z4 = true;
        }
        if (z4) {
            if (z2 || !valueAnimator4.isPaused()) {
                valueAnimator4.start();
            } else {
                valueAnimator4.resume();
            }
            return z6;
        }
        boolean z7 = this.k;
        this.k = true;
        valueAnimator4.end();
        this.k = z7;
        return z6;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getAlpha() {
        return this.n;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -3;
    }

    public final boolean h(l5.a aVar) {
        ArrayList arrayList = this.j;
        if (arrayList == null || !arrayList.contains(aVar)) {
            return false;
        }
        this.j.remove(aVar);
        if (!this.j.isEmpty()) {
            return true;
        }
        this.j = null;
        return true;
    }

    @Override // android.graphics.drawable.Animatable
    public final boolean isRunning() {
        return e() || d();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        this.n = i;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        this.m.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean setVisible(boolean z, boolean z2) {
        return f(z, z2, true);
    }

    @Override // android.graphics.drawable.Animatable
    public final void start() {
        g(true, true, false);
    }

    @Override // android.graphics.drawable.Animatable
    public final void stop() {
        g(false, true, false);
    }
}
