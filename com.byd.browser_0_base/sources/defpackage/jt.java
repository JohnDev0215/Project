package defpackage;

import android.animation.ObjectAnimator;
import android.hardware.bydauto.BYDAutoFeatureIds;
import android.hardware.bydauto_test.TestDiPilotManagerImplCallback;
import android.util.Property;
import com.google.android.material.progressindicator.BaseProgressIndicator;
import com.google.android.material.progressindicator.CircularProgressIndicatorSpec;
import defpackage.l5;

/* JADX INFO: compiled from: CircularIndeterminateAnimatorDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public final class jt extends nz0<ObjectAnimator> {
    public static final int[] l = {0, 1350, 2700, 4050};
    public static final int[] m = {667, 2017, 3367, BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_STERILIZATION_STATUS_SET};
    public static final int[] n = {1000, 2350, 3700, TestDiPilotManagerImplCallback.SET_INTELLIGENT_SPEED_LIMIT_CONTROL_STATE};
    public static final a o = new a();
    public static final b p = new b();
    public ObjectAnimator d;
    public ObjectAnimator e;
    public final jk0 f;
    public final CircularProgressIndicatorSpec g;
    public int h;
    public float i;
    public float j;
    public l5.a k;

    /* JADX INFO: compiled from: CircularIndeterminateAnimatorDelegate.java */
    public class a extends Property<jt, Float> {
        public a() {
            super(Float.class, "animationFraction");
        }

        @Override // android.util.Property
        public final Float get(jt jtVar) {
            return Float.valueOf(jtVar.i);
        }

        @Override // android.util.Property
        public final void set(jt jtVar, Float f) {
            jk0 jk0Var;
            jt jtVar2 = jtVar;
            float fFloatValue = f.floatValue();
            jtVar2.i = fFloatValue;
            int i = (int) (5400.0f * fFloatValue);
            float f2 = fFloatValue * 1520.0f;
            float[] fArr = jtVar2.b;
            fArr[0] = (-20.0f) + f2;
            fArr[1] = f2;
            int i2 = 0;
            while (true) {
                jk0Var = jtVar2.f;
                if (i2 >= 4) {
                    break;
                }
                float f3 = 667;
                fArr[1] = (jk0Var.getInterpolation((i - jt.l[i2]) / f3) * 250.0f) + fArr[1];
                fArr[0] = (jk0Var.getInterpolation((i - jt.m[i2]) / f3) * 250.0f) + fArr[0];
                i2++;
            }
            float f4 = fArr[0];
            float f5 = fArr[1];
            float f6 = ((f5 - f4) * jtVar2.j) + f4;
            fArr[0] = f6;
            fArr[0] = f6 / 360.0f;
            fArr[1] = f5 / 360.0f;
            int i3 = 0;
            while (true) {
                if (i3 >= 4) {
                    break;
                }
                float f7 = (i - jt.n[i3]) / 333;
                if (f7 >= 0.0f && f7 <= 1.0f) {
                    int i4 = i3 + jtVar2.h;
                    CircularProgressIndicatorSpec circularProgressIndicatorSpec = jtVar2.g;
                    int[] iArr = circularProgressIndicatorSpec.c;
                    int length = i4 % iArr.length;
                    jtVar2.c[0] = i8.a(jk0Var.getInterpolation(f7), Integer.valueOf(oj2.e(iArr[length], jtVar2.a.n)), Integer.valueOf(oj2.e(circularProgressIndicatorSpec.c[(length + 1) % iArr.length], jtVar2.a.n))).intValue();
                    break;
                }
                i3++;
            }
            jtVar2.a.invalidateSelf();
        }
    }

    /* JADX INFO: compiled from: CircularIndeterminateAnimatorDelegate.java */
    public class b extends Property<jt, Float> {
        public b() {
            super(Float.class, "completeEndFraction");
        }

        @Override // android.util.Property
        public final Float get(jt jtVar) {
            return Float.valueOf(jtVar.j);
        }

        @Override // android.util.Property
        public final void set(jt jtVar, Float f) {
            jtVar.j = f.floatValue();
        }
    }

    public jt(CircularProgressIndicatorSpec circularProgressIndicatorSpec) {
        super(1);
        this.h = 0;
        this.k = null;
        this.g = circularProgressIndicatorSpec;
        this.f = new jk0();
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
        this.c[0] = oj2.e(this.g.c[0], this.a.n);
        this.j = 0.0f;
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
        if (this.a.isVisible()) {
            this.e.start();
        } else {
            a();
        }
    }

    @Override // defpackage.nz0
    public final void e() {
        if (this.d == null) {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, o, 0.0f, 1.0f);
            this.d = objectAnimatorOfFloat;
            objectAnimatorOfFloat.setDuration(5400L);
            this.d.setInterpolator(null);
            this.d.setRepeatCount(-1);
            this.d.addListener(new gt(this));
        }
        if (this.e == null) {
            ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this, p, 0.0f, 1.0f);
            this.e = objectAnimatorOfFloat2;
            objectAnimatorOfFloat2.setDuration(333L);
            this.e.setInterpolator(this.f);
            this.e.addListener(new ht(this));
        }
        this.h = 0;
        this.c[0] = oj2.e(this.g.c[0], this.a.n);
        this.j = 0.0f;
        this.d.start();
    }

    @Override // defpackage.nz0
    public final void f() {
        this.k = null;
    }
}
