package defpackage;

import android.animation.ObjectAnimator;
import android.util.Property;
import com.google.android.material.progressindicator.BaseProgressIndicator;
import com.google.android.material.progressindicator.LinearProgressIndicatorSpec;
import java.util.Arrays;

/* JADX INFO: compiled from: LinearIndeterminateContiguousAnimatorDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public final class l71 extends nz0<ObjectAnimator> {
    public static final a j = new a();
    public ObjectAnimator d;
    public final jk0 e;
    public final LinearProgressIndicatorSpec f;
    public int g;
    public boolean h;
    public float i;

    /* JADX INFO: compiled from: LinearIndeterminateContiguousAnimatorDelegate.java */
    public class a extends Property<l71, Float> {
        public a() {
            super(Float.class, "animationFraction");
        }

        @Override // android.util.Property
        public final Float get(l71 l71Var) {
            return Float.valueOf(l71Var.i);
        }

        @Override // android.util.Property
        public final void set(l71 l71Var, Float f) {
            l71 l71Var2 = l71Var;
            l71Var2.i = f.floatValue();
            float[] fArr = l71Var2.b;
            fArr[0] = 0.0f;
            float f2 = (((int) (r5 * 333.0f)) - 0) / 667;
            jk0 jk0Var = l71Var2.e;
            float interpolation = jk0Var.getInterpolation(f2);
            fArr[2] = interpolation;
            fArr[1] = interpolation;
            float interpolation2 = jk0Var.getInterpolation(f2 + 0.49925038f);
            fArr[4] = interpolation2;
            fArr[3] = interpolation2;
            fArr[5] = 1.0f;
            if (l71Var2.h && interpolation2 < 1.0f) {
                int[] iArr = l71Var2.c;
                iArr[2] = iArr[1];
                iArr[1] = iArr[0];
                iArr[0] = oj2.e(l71Var2.f.c[l71Var2.g], l71Var2.a.n);
                l71Var2.h = false;
            }
            l71Var2.a.invalidateSelf();
        }
    }

    public l71(LinearProgressIndicatorSpec linearProgressIndicatorSpec) {
        super(3);
        this.g = 1;
        this.f = linearProgressIndicatorSpec;
        this.e = new jk0();
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
        this.h = true;
        this.g = 1;
        Arrays.fill(this.c, oj2.e(this.f.c[0], this.a.n));
    }

    @Override // defpackage.nz0
    public final void e() {
        if (this.d == null) {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, j, 0.0f, 1.0f);
            this.d = objectAnimatorOfFloat;
            objectAnimatorOfFloat.setDuration(333L);
            this.d.setInterpolator(null);
            this.d.setRepeatCount(-1);
            this.d.addListener(new k71(this));
        }
        this.h = true;
        this.g = 1;
        Arrays.fill(this.c, oj2.e(this.f.c[0], this.a.n));
        this.d.start();
    }

    @Override // defpackage.nz0
    public final void d() {
    }

    @Override // defpackage.nz0
    public final void f() {
    }

    @Override // defpackage.nz0
    public final void c(BaseProgressIndicator.c cVar) {
    }
}
