package defpackage;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.Property;
import android.view.animation.Interpolator;
import com.google.android.material.R$animator;
import com.google.android.material.progressindicator.BaseProgressIndicator;
import com.google.android.material.progressindicator.LinearProgressIndicatorSpec;
import defpackage.l5;
import java.util.Arrays;

/* JADX INFO: compiled from: LinearIndeterminateDisjointAnimatorDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public final class o71 extends nz0<ObjectAnimator> {
    public static final int[] l = {533, 567, 850, 750};
    public static final int[] m = {1267, 1000, 333, 0};
    public static final a n = new a();
    public ObjectAnimator d;
    public ObjectAnimator e;
    public final Interpolator[] f;
    public final LinearProgressIndicatorSpec g;
    public int h;
    public boolean i;
    public float j;
    public l5.a k;

    /* JADX INFO: compiled from: LinearIndeterminateDisjointAnimatorDelegate.java */
    public class a extends Property<o71, Float> {
        public a() {
            super(Float.class, "animationFraction");
        }

        @Override // android.util.Property
        public final Float get(o71 o71Var) {
            return Float.valueOf(o71Var.j);
        }

        @Override // android.util.Property
        public final void set(o71 o71Var, Float f) {
            o71 o71Var2 = o71Var;
            float fFloatValue = f.floatValue();
            o71Var2.j = fFloatValue;
            int i = (int) (fFloatValue * 1800.0f);
            for (int i2 = 0; i2 < 4; i2++) {
                o71Var2.b[i2] = Math.max(0.0f, Math.min(1.0f, o71Var2.f[i2].getInterpolation((i - o71.m[i2]) / o71.l[i2])));
            }
            if (o71Var2.i) {
                Arrays.fill(o71Var2.c, oj2.e(o71Var2.g.c[o71Var2.h], o71Var2.a.n));
                o71Var2.i = false;
            }
            o71Var2.a.invalidateSelf();
        }
    }

    public o71(Context context, LinearProgressIndicatorSpec linearProgressIndicatorSpec) {
        super(2);
        this.h = 0;
        this.k = null;
        this.g = linearProgressIndicatorSpec;
        this.f = new Interpolator[]{v5.a(context, R$animator.linear_indeterminate_line1_head_interpolator), v5.a(context, R$animator.linear_indeterminate_line1_tail_interpolator), v5.a(context, R$animator.linear_indeterminate_line2_head_interpolator), v5.a(context, R$animator.linear_indeterminate_line2_tail_interpolator)};
    }

    @Override // defpackage.nz0
    public final void a() {
        ObjectAnimator objectAnimator = this.d;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
    }

    @Override // defpackage.nz0
    public final void b() {
        this.h = 0;
        int iE = oj2.e(this.g.c[0], this.a.n);
        int[] iArr = this.c;
        iArr[0] = iE;
        iArr[1] = iE;
    }

    @Override // defpackage.nz0
    public final void c(BaseProgressIndicator.c cVar) {
        this.k = cVar;
    }

    @Override // defpackage.nz0
    public final void d() {
        ObjectAnimator objectAnimator = this.e;
        if (objectAnimator == null || objectAnimator.isRunning()) {
            return;
        }
        a();
        if (this.a.isVisible()) {
            this.e.setFloatValues(this.j, 1.0f);
            this.e.setDuration((long) ((1.0f - this.j) * 1800.0f));
            this.e.start();
        }
    }

    @Override // defpackage.nz0
    public final void e() {
        ObjectAnimator objectAnimator = this.d;
        a aVar = n;
        if (objectAnimator == null) {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, aVar, 0.0f, 1.0f);
            this.d = objectAnimatorOfFloat;
            objectAnimatorOfFloat.setDuration(1800L);
            this.d.setInterpolator(null);
            this.d.setRepeatCount(-1);
            this.d.addListener(new m71(this));
        }
        if (this.e == null) {
            ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this, aVar, 1.0f);
            this.e = objectAnimatorOfFloat2;
            objectAnimatorOfFloat2.setDuration(1800L);
            this.e.setInterpolator(null);
            this.e.addListener(new n71(this));
        }
        this.h = 0;
        int iE = oj2.e(this.g.c[0], this.a.n);
        int[] iArr = this.c;
        iArr[0] = iE;
        iArr[1] = iE;
        this.d.start();
    }

    @Override // defpackage.nz0
    public final void f() {
        this.k = null;
    }
}
