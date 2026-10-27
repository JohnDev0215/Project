package defpackage;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.provider.Settings;
import android.util.AndroidRuntimeException;
import defpackage.af0;
import defpackage.q5;
import defpackage.td;
import java.util.ArrayList;

/* JADX INFO: compiled from: DeterminateDrawable.java */
/* JADX INFO: loaded from: classes.dex */
public final class c90<S extends td> extends vd0 {
    public static final a u = new a();
    public xd0<S> p;
    public final dr2 q;
    public final cr2 r;
    public float s;
    public boolean t;

    /* JADX INFO: compiled from: DeterminateDrawable.java */
    public class a extends sn0 {
        public a() {
            super("indicatorLevel");
        }

        @Override // defpackage.sn0
        public final float d(Object obj) {
            return ((c90) obj).s * 10000.0f;
        }

        @Override // defpackage.sn0
        public final void k(Object obj, float f) {
            c90 c90Var = (c90) obj;
            c90Var.s = f / 10000.0f;
            c90Var.invalidateSelf();
        }
    }

    public c90(Context context, td tdVar, xd0<S> xd0Var) {
        super(context, tdVar);
        this.t = false;
        this.p = xd0Var;
        xd0Var.b = this;
        dr2 dr2Var = new dr2();
        this.q = dr2Var;
        dr2Var.b = 1.0f;
        dr2Var.c = false;
        dr2Var.a = Math.sqrt(50.0f);
        dr2Var.c = false;
        cr2 cr2Var = new cr2(this);
        this.r = cr2Var;
        cr2Var.r = dr2Var;
        if (this.l != 1.0f) {
            this.l = 1.0f;
            invalidateSelf();
        }
    }

    public void addSpringAnimationEndListener(af0.h hVar) {
        ArrayList<af0.h> arrayList = this.r.j;
        if (arrayList.contains(hVar)) {
            return;
        }
        arrayList.add(hVar);
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        Rect rect = new Rect();
        if (!getBounds().isEmpty() && isVisible() && canvas.getClipBounds(rect)) {
            canvas.save();
            xd0<S> xd0Var = this.p;
            float fC = c();
            xd0Var.a.a();
            xd0Var.a(canvas, fC);
            xd0<S> xd0Var2 = this.p;
            Paint paint = this.m;
            xd0Var2.c(canvas, paint);
            this.p.b(canvas, paint, 0.0f, this.s, oj2.e(this.f.c[0], this.n));
            canvas.restore();
        }
    }

    @Override // defpackage.vd0
    public final boolean g(boolean z, boolean z2, boolean z3) {
        boolean zG = super.g(z, z2, z3);
        w5 w5Var = this.g;
        ContentResolver contentResolver = this.e.getContentResolver();
        w5Var.getClass();
        float f = Settings.Global.getFloat(contentResolver, "animator_duration_scale", 1.0f);
        if (f == 0.0f) {
            this.t = true;
        } else {
            this.t = false;
            float f2 = 50.0f / f;
            dr2 dr2Var = this.q;
            dr2Var.getClass();
            if (f2 <= 0.0f) {
                throw new IllegalArgumentException("Spring stiffness constant must be positive.");
            }
            dr2Var.a = Math.sqrt(f2);
            dr2Var.c = false;
        }
        return zG;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return this.p.d();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        return this.p.e();
    }

    @Override // android.graphics.drawable.Drawable
    public final void jumpToCurrentState() {
        this.r.e();
        this.s = getLevel() / 10000.0f;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean onLevelChange(int i) {
        boolean z = this.t;
        cr2 cr2Var = this.r;
        if (z) {
            cr2Var.e();
            this.s = i / 10000.0f;
            invalidateSelf();
        } else {
            cr2Var.b = this.s * 10000.0f;
            cr2Var.c = true;
            float f = i;
            if (cr2Var.f) {
                cr2Var.s = f;
            } else {
                if (cr2Var.r == null) {
                    cr2Var.r = new dr2(f);
                }
                dr2 dr2Var = cr2Var.r;
                double d = f;
                dr2Var.i = d;
                double d2 = (float) d;
                if (d2 > Float.MAX_VALUE) {
                    throw new UnsupportedOperationException("Final position of the spring cannot be greater than the max value.");
                }
                float f2 = cr2Var.g;
                if (d2 < f2) {
                    throw new UnsupportedOperationException("Final position of the spring cannot be less than the min value.");
                }
                double dAbs = Math.abs(cr2Var.i * 0.75f);
                dr2Var.d = dAbs;
                dr2Var.e = dAbs * 62.5d;
                if (!((q5.d) af0.b().e).a()) {
                    throw new AndroidRuntimeException("Animations may only be started on the same thread as the animation handler");
                }
                boolean z2 = cr2Var.f;
                if (!z2 && !z2) {
                    cr2Var.f = true;
                    if (!cr2Var.c) {
                        cr2Var.b = cr2Var.e.d(cr2Var.d);
                    }
                    float f3 = cr2Var.b;
                    if (f3 > Float.MAX_VALUE || f3 < f2) {
                        throw new IllegalArgumentException("Starting value need to be in between min value and max value");
                    }
                    af0.b().a(cr2Var);
                }
            }
        }
        return true;
    }

    public void removeSpringAnimationEndListener(af0.h hVar) {
        this.r.removeEndListener(hVar);
    }
}
