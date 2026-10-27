package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;
import androidx.constraintlayout.widget.R$styleable;
import defpackage.a6;
import defpackage.c6;
import defpackage.d5;
import defpackage.e5;
import defpackage.g5;
import defpackage.j5;
import defpackage.m9;
import defpackage.o4;
import defpackage.p6;
import defpackage.q4;
import defpackage.q6;
import defpackage.r4;
import defpackage.s6;
import defpackage.t5;
import defpackage.u4;
import defpackage.w5;
import defpackage.x5;
import defpackage.y5;
import defpackage.z5;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class MotionLayout extends ConstraintLayout implements m9 {
    public static boolean y;
    public Interpolator A;
    public int A0;
    public float B;
    public int B0;
    public int C;
    public float C0;
    public int D;
    public u4 D0;
    public int E;
    public boolean E0;
    public int F;
    public h F0;
    public int G;
    public TransitionState G0;
    public boolean H;
    public e H0;
    public HashMap<View, d5> I;
    public boolean I0;
    public long J;
    public RectF J0;
    public float K;
    public View K0;
    public float L;
    public ArrayList<Integer> L0;
    public float M;
    public long N;
    public float O;
    public boolean P;
    public boolean Q;
    public boolean R;
    public i S;
    public float T;
    public float U;
    public int V;
    public d W;
    public boolean X;
    public o4 Y;
    public c Z;
    public r4 a0;
    public boolean b0;
    public int c0;
    public int d0;
    public int e0;
    public int f0;
    public boolean g0;
    public float h0;
    public float i0;
    public long j0;
    public float k0;
    public boolean l0;
    public ArrayList<MotionHelper> m0;
    public ArrayList<MotionHelper> n0;
    public ArrayList<i> o0;
    public int p0;
    public long q0;
    public float r0;
    public int s0;
    public float t0;
    public boolean u0;
    public boolean v0;
    public int w0;
    public int x0;
    public int y0;
    public g5 z;
    public int z0;

    public enum TransitionState {
        UNDEFINED,
        SETUP,
        MOVING,
        FINISHED
    }

    public class a implements Runnable {
        public final /* synthetic */ View b;

        public a(View view) {
            this.b = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.b.setNestedScrollingEnabled(true);
        }
    }

    public static /* synthetic */ class b {
        public static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[TransitionState.values().length];
            a = iArr;
            try {
                iArr[TransitionState.UNDEFINED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[TransitionState.SETUP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[TransitionState.MOVING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[TransitionState.FINISHED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public class c extends e5 {
        public float a = 0.0f;
        public float b = 0.0f;
        public float c;

        public c() {
        }

        @Override // defpackage.e5
        public float a() {
            return MotionLayout.this.B;
        }

        public void b(float f, float f2, float f3) {
            this.a = f;
            this.b = f2;
            this.c = f3;
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            float f2;
            float f3;
            float f4 = this.a;
            if (f4 > 0.0f) {
                float f5 = this.c;
                if (f4 / f5 < f) {
                    f = f4 / f5;
                }
                MotionLayout.this.B = f4 - (f5 * f);
                f2 = (f4 * f) - (((f5 * f) * f) / 2.0f);
                f3 = this.b;
            } else {
                float f6 = this.c;
                if ((-f4) / f6 < f) {
                    f = (-f4) / f6;
                }
                MotionLayout.this.B = (f6 * f) + f4;
                f2 = (f4 * f) + (((f6 * f) * f) / 2.0f);
                f3 = this.b;
            }
            return f2 + f3;
        }
    }

    public class d {
        public float[] a;
        public int[] b;
        public float[] c;
        public Path d;
        public Paint e;
        public Paint f;
        public Paint g;
        public Paint h;
        public Paint i;
        public float[] j;
        public DashPathEffect p;
        public int q;
        public int t;
        public final int k = -21965;
        public final int l = -2067046;
        public final int m = -13391360;
        public final int n = 1996488704;
        public final int o = 10;
        public Rect r = new Rect();
        public boolean s = false;

        public d() {
            this.t = 1;
            Paint paint = new Paint();
            this.e = paint;
            paint.setAntiAlias(true);
            this.e.setColor(-21965);
            this.e.setStrokeWidth(2.0f);
            this.e.setStyle(Paint.Style.STROKE);
            Paint paint2 = new Paint();
            this.f = paint2;
            paint2.setAntiAlias(true);
            this.f.setColor(-2067046);
            this.f.setStrokeWidth(2.0f);
            this.f.setStyle(Paint.Style.STROKE);
            Paint paint3 = new Paint();
            this.g = paint3;
            paint3.setAntiAlias(true);
            this.g.setColor(-13391360);
            this.g.setStrokeWidth(2.0f);
            this.g.setStyle(Paint.Style.STROKE);
            Paint paint4 = new Paint();
            this.h = paint4;
            paint4.setAntiAlias(true);
            this.h.setColor(-13391360);
            this.h.setTextSize(MotionLayout.this.getContext().getResources().getDisplayMetrics().density * 12.0f);
            this.j = new float[8];
            Paint paint5 = new Paint();
            this.i = paint5;
            paint5.setAntiAlias(true);
            DashPathEffect dashPathEffect = new DashPathEffect(new float[]{4.0f, 8.0f}, 0.0f);
            this.p = dashPathEffect;
            this.g.setPathEffect(dashPathEffect);
            this.c = new float[100];
            this.b = new int[50];
            if (this.s) {
                this.e.setStrokeWidth(8.0f);
                this.i.setStrokeWidth(8.0f);
                this.f.setStrokeWidth(8.0f);
                this.t = 4;
            }
        }

        public void a(Canvas canvas, HashMap<View, d5> map, int i, int i2) {
            if (map == null || map.size() == 0) {
                return;
            }
            canvas.save();
            if (!MotionLayout.this.isInEditMode() && (i2 & 1) == 2) {
                String str = MotionLayout.this.getContext().getResources().getResourceName(MotionLayout.this.E) + ":" + MotionLayout.this.getProgress();
                canvas.drawText(str, 10.0f, MotionLayout.this.getHeight() - 30, this.h);
                canvas.drawText(str, 11.0f, MotionLayout.this.getHeight() - 29, this.e);
            }
            for (d5 d5Var : map.values()) {
                int iH = d5Var.h();
                if (i2 > 0 && iH == 0) {
                    iH = 1;
                }
                if (iH != 0) {
                    this.q = d5Var.c(this.c, this.b);
                    if (iH >= 1) {
                        int i3 = i / 16;
                        float[] fArr = this.a;
                        if (fArr == null || fArr.length != i3 * 2) {
                            this.a = new float[i3 * 2];
                            this.d = new Path();
                        }
                        int i4 = this.t;
                        canvas.translate(i4, i4);
                        this.e.setColor(1996488704);
                        this.i.setColor(1996488704);
                        this.f.setColor(1996488704);
                        this.g.setColor(1996488704);
                        d5Var.d(this.a, i3);
                        b(canvas, iH, this.q, d5Var);
                        this.e.setColor(-21965);
                        this.f.setColor(-2067046);
                        this.i.setColor(-2067046);
                        this.g.setColor(-13391360);
                        int i5 = this.t;
                        canvas.translate(-i5, -i5);
                        b(canvas, iH, this.q, d5Var);
                        if (iH == 5) {
                            j(canvas, d5Var);
                        }
                    }
                }
            }
            canvas.restore();
        }

        public void b(Canvas canvas, int i, int i2, d5 d5Var) {
            if (i == 4) {
                d(canvas);
            }
            if (i == 2) {
                g(canvas);
            }
            if (i == 3) {
                e(canvas);
            }
            c(canvas);
            k(canvas, i, i2, d5Var);
        }

        public final void c(Canvas canvas) {
            canvas.drawLines(this.a, this.e);
        }

        public final void d(Canvas canvas) {
            boolean z = false;
            boolean z2 = false;
            for (int i = 0; i < this.q; i++) {
                int[] iArr = this.b;
                if (iArr[i] == 1) {
                    z = true;
                }
                if (iArr[i] == 2) {
                    z2 = true;
                }
            }
            if (z) {
                g(canvas);
            }
            if (z2) {
                e(canvas);
            }
        }

        public final void e(Canvas canvas) {
            float[] fArr = this.a;
            float f = fArr[0];
            float f2 = fArr[1];
            float f3 = fArr[fArr.length - 2];
            float f4 = fArr[fArr.length - 1];
            canvas.drawLine(Math.min(f, f3), Math.max(f2, f4), Math.max(f, f3), Math.max(f2, f4), this.g);
            canvas.drawLine(Math.min(f, f3), Math.min(f2, f4), Math.min(f, f3), Math.max(f2, f4), this.g);
        }

        public final void f(Canvas canvas, float f, float f2) {
            float[] fArr = this.a;
            float f3 = fArr[0];
            float f4 = fArr[1];
            float f5 = fArr[fArr.length - 2];
            float f6 = fArr[fArr.length - 1];
            float fMin = Math.min(f3, f5);
            float fMax = Math.max(f4, f6);
            float fMin2 = f - Math.min(f3, f5);
            float fMax2 = Math.max(f4, f6) - f2;
            String str = "" + (((int) (((double) ((fMin2 * 100.0f) / Math.abs(f5 - f3))) + 0.5d)) / 100.0f);
            l(str, this.h);
            canvas.drawText(str, ((fMin2 / 2.0f) - (this.r.width() / 2)) + fMin, f2 - 20.0f, this.h);
            canvas.drawLine(f, f2, Math.min(f3, f5), f2, this.g);
            String str2 = "" + (((int) (((double) ((fMax2 * 100.0f) / Math.abs(f6 - f4))) + 0.5d)) / 100.0f);
            l(str2, this.h);
            canvas.drawText(str2, f + 5.0f, fMax - ((fMax2 / 2.0f) - (this.r.height() / 2)), this.h);
            canvas.drawLine(f, f2, f, Math.max(f4, f6), this.g);
        }

        public final void g(Canvas canvas) {
            float[] fArr = this.a;
            canvas.drawLine(fArr[0], fArr[1], fArr[fArr.length - 2], fArr[fArr.length - 1], this.g);
        }

        public final void h(Canvas canvas, float f, float f2) {
            float[] fArr = this.a;
            float f3 = fArr[0];
            float f4 = fArr[1];
            float f5 = fArr[fArr.length - 2];
            float f6 = fArr[fArr.length - 1];
            float fHypot = (float) Math.hypot(f3 - f5, f4 - f6);
            float f7 = f5 - f3;
            float f8 = f6 - f4;
            float f9 = (((f - f3) * f7) + ((f2 - f4) * f8)) / (fHypot * fHypot);
            float f10 = f3 + (f7 * f9);
            float f11 = f4 + (f9 * f8);
            Path path = new Path();
            path.moveTo(f, f2);
            path.lineTo(f10, f11);
            float fHypot2 = (float) Math.hypot(f10 - f, f11 - f2);
            String str = "" + (((int) ((fHypot2 * 100.0f) / fHypot)) / 100.0f);
            l(str, this.h);
            canvas.drawTextOnPath(str, path, (fHypot2 / 2.0f) - (this.r.width() / 2), -20.0f, this.h);
            canvas.drawLine(f, f2, f10, f11, this.g);
        }

        public final void i(Canvas canvas, float f, float f2, int i, int i2) {
            String str = "" + (((int) (((double) (((f - (i / 2)) * 100.0f) / (MotionLayout.this.getWidth() - i))) + 0.5d)) / 100.0f);
            l(str, this.h);
            canvas.drawText(str, ((f / 2.0f) - (this.r.width() / 2)) + 0.0f, f2 - 20.0f, this.h);
            canvas.drawLine(f, f2, Math.min(0.0f, 1.0f), f2, this.g);
            String str2 = "" + (((int) (((double) (((f2 - (i2 / 2)) * 100.0f) / (MotionLayout.this.getHeight() - i2))) + 0.5d)) / 100.0f);
            l(str2, this.h);
            canvas.drawText(str2, f + 5.0f, 0.0f - ((f2 / 2.0f) - (this.r.height() / 2)), this.h);
            canvas.drawLine(f, f2, f, Math.max(0.0f, 1.0f), this.g);
        }

        public final void j(Canvas canvas, d5 d5Var) {
            this.d.reset();
            for (int i = 0; i <= 50; i++) {
                d5Var.e(i / 50, this.j, 0);
                Path path = this.d;
                float[] fArr = this.j;
                path.moveTo(fArr[0], fArr[1]);
                Path path2 = this.d;
                float[] fArr2 = this.j;
                path2.lineTo(fArr2[2], fArr2[3]);
                Path path3 = this.d;
                float[] fArr3 = this.j;
                path3.lineTo(fArr3[4], fArr3[5]);
                Path path4 = this.d;
                float[] fArr4 = this.j;
                path4.lineTo(fArr4[6], fArr4[7]);
                this.d.close();
            }
            this.e.setColor(1140850688);
            canvas.translate(2.0f, 2.0f);
            canvas.drawPath(this.d, this.e);
            canvas.translate(-2.0f, -2.0f);
            this.e.setColor(-65536);
            canvas.drawPath(this.d, this.e);
        }

        public final void k(Canvas canvas, int i, int i2, d5 d5Var) {
            int width;
            int height;
            int i3;
            float f;
            float f2;
            View view = d5Var.a;
            if (view != null) {
                width = view.getWidth();
                height = d5Var.a.getHeight();
            } else {
                width = 0;
                height = 0;
            }
            for (int i4 = 1; i4 < i2 - 1; i4++) {
                if (i != 4 || this.b[i4 - 1] != 0) {
                    float[] fArr = this.c;
                    int i5 = i4 * 2;
                    float f3 = fArr[i5];
                    float f4 = fArr[i5 + 1];
                    this.d.reset();
                    this.d.moveTo(f3, f4 + 10.0f);
                    this.d.lineTo(f3 + 10.0f, f4);
                    this.d.lineTo(f3, f4 - 10.0f);
                    this.d.lineTo(f3 - 10.0f, f4);
                    this.d.close();
                    int i6 = i4 - 1;
                    d5Var.k(i6);
                    if (i == 4) {
                        int[] iArr = this.b;
                        if (iArr[i6] == 1) {
                            h(canvas, f3 - 0.0f, f4 - 0.0f);
                        } else if (iArr[i6] == 2) {
                            f(canvas, f3 - 0.0f, f4 - 0.0f);
                        } else {
                            if (iArr[i6] == 3) {
                                i3 = 3;
                                f = f4;
                                f2 = f3;
                                i(canvas, f3 - 0.0f, f4 - 0.0f, width, height);
                            }
                            canvas.drawPath(this.d, this.i);
                        }
                        i3 = 3;
                        f = f4;
                        f2 = f3;
                        canvas.drawPath(this.d, this.i);
                    } else {
                        i3 = 3;
                        f = f4;
                        f2 = f3;
                    }
                    if (i == 2) {
                        h(canvas, f2 - 0.0f, f - 0.0f);
                    }
                    if (i == i3) {
                        f(canvas, f2 - 0.0f, f - 0.0f);
                    }
                    if (i == 6) {
                        i(canvas, f2 - 0.0f, f - 0.0f, width, height);
                    }
                    canvas.drawPath(this.d, this.i);
                }
            }
            float[] fArr2 = this.a;
            if (fArr2.length > 1) {
                canvas.drawCircle(fArr2[0], fArr2[1], 8.0f, this.f);
                float[] fArr3 = this.a;
                canvas.drawCircle(fArr3[fArr3.length - 2], fArr3[fArr3.length - 1], 8.0f, this.f);
            }
        }

        public void l(String str, Paint paint) {
            paint.getTextBounds(str, 0, str.length(), this.r);
        }
    }

    public class e {
        public w5 a = new w5();
        public w5 b = new w5();
        public q6 c = null;
        public q6 d = null;
        public int e;
        public int f;

        public e() {
        }

        public void a() {
            int childCount = MotionLayout.this.getChildCount();
            MotionLayout.this.I.clear();
            for (int i = 0; i < childCount; i++) {
                View childAt = MotionLayout.this.getChildAt(i);
                MotionLayout.this.I.put(childAt, new d5(childAt));
            }
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt2 = MotionLayout.this.getChildAt(i2);
                d5 d5Var = MotionLayout.this.I.get(childAt2);
                if (d5Var != null) {
                    if (this.c != null) {
                        ConstraintWidget constraintWidgetC = c(this.a, childAt2);
                        if (constraintWidgetC != null) {
                            d5Var.t(constraintWidgetC, this.c);
                        } else if (MotionLayout.this.V != 0) {
                            Log.e("MotionLayout", q4.a() + "no widget for  " + q4.c(childAt2) + " (" + childAt2.getClass().getName() + ")");
                        }
                    }
                    if (this.d != null) {
                        ConstraintWidget constraintWidgetC2 = c(this.b, childAt2);
                        if (constraintWidgetC2 != null) {
                            d5Var.q(constraintWidgetC2, this.d);
                        } else if (MotionLayout.this.V != 0) {
                            Log.e("MotionLayout", q4.a() + "no widget for  " + q4.c(childAt2) + " (" + childAt2.getClass().getName() + ")");
                        }
                    }
                }
            }
        }

        public void b(w5 w5Var, w5 w5Var2) {
            ArrayList<ConstraintWidget> arrayListL0 = w5Var.L0();
            HashMap<ConstraintWidget, ConstraintWidget> map = new HashMap<>();
            map.put(w5Var, w5Var2);
            w5Var2.L0().clear();
            w5Var2.l(w5Var, map);
            for (ConstraintWidget constraintWidget : arrayListL0) {
                ConstraintWidget t5Var = constraintWidget instanceof t5 ? new t5() : constraintWidget instanceof y5 ? new y5() : constraintWidget instanceof x5 ? new x5() : constraintWidget instanceof z5 ? new a6() : new ConstraintWidget();
                w5Var2.a(t5Var);
                map.put(constraintWidget, t5Var);
            }
            for (ConstraintWidget constraintWidget2 : arrayListL0) {
                map.get(constraintWidget2).l(constraintWidget2, map);
            }
        }

        public ConstraintWidget c(w5 w5Var, View view) {
            if (w5Var.r() == view) {
                return w5Var;
            }
            ArrayList<ConstraintWidget> arrayListL0 = w5Var.L0();
            int size = arrayListL0.size();
            for (int i = 0; i < size; i++) {
                ConstraintWidget constraintWidget = arrayListL0.get(i);
                if (constraintWidget.r() == view) {
                    return constraintWidget;
                }
            }
            return null;
        }

        public void d(w5 w5Var, q6 q6Var, q6 q6Var2) {
            this.c = q6Var;
            this.d = q6Var2;
            this.a = new w5();
            this.b = new w5();
            this.a.h1(MotionLayout.this.d.W0());
            this.b.h1(MotionLayout.this.d.W0());
            this.a.O0();
            this.b.O0();
            b(MotionLayout.this.d, this.a);
            b(MotionLayout.this.d, this.b);
            if (MotionLayout.this.M > 0.5d) {
                if (q6Var != null) {
                    i(this.a, q6Var);
                }
                i(this.b, q6Var2);
            } else {
                i(this.b, q6Var2);
                if (q6Var != null) {
                    i(this.a, q6Var);
                }
            }
            this.a.j1(MotionLayout.this.p());
            this.a.l1();
            this.b.j1(MotionLayout.this.p());
            this.b.l1();
            ViewGroup.LayoutParams layoutParams = MotionLayout.this.getLayoutParams();
            if (layoutParams != null) {
                if (layoutParams.width == -2) {
                    w5 w5Var2 = this.a;
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    w5Var2.m0(dimensionBehaviour);
                    this.b.m0(dimensionBehaviour);
                }
                if (layoutParams.height == -2) {
                    w5 w5Var3 = this.a;
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    w5Var3.B0(dimensionBehaviour2);
                    this.b.B0(dimensionBehaviour2);
                }
            }
        }

        public boolean e(int i, int i2) {
            return (i == this.e && i2 == this.f) ? false : true;
        }

        public void f(int i, int i2) {
            int mode = View.MeasureSpec.getMode(i);
            int mode2 = View.MeasureSpec.getMode(i2);
            MotionLayout motionLayout = MotionLayout.this;
            motionLayout.A0 = mode;
            motionLayout.B0 = mode2;
            int optimizationLevel = motionLayout.getOptimizationLevel();
            MotionLayout motionLayout2 = MotionLayout.this;
            if (motionLayout2.D == motionLayout2.getStartState()) {
                MotionLayout.this.t(this.b, optimizationLevel, i, i2);
                if (this.c != null) {
                    MotionLayout.this.t(this.a, optimizationLevel, i, i2);
                }
            } else {
                if (this.c != null) {
                    MotionLayout.this.t(this.a, optimizationLevel, i, i2);
                }
                MotionLayout.this.t(this.b, optimizationLevel, i, i2);
            }
            if (((MotionLayout.this.getParent() instanceof MotionLayout) && mode == 1073741824 && mode2 == 1073741824) ? false : true) {
                MotionLayout motionLayout3 = MotionLayout.this;
                motionLayout3.A0 = mode;
                motionLayout3.B0 = mode2;
                if (motionLayout3.D == motionLayout3.getStartState()) {
                    MotionLayout.this.t(this.b, optimizationLevel, i, i2);
                    if (this.c != null) {
                        MotionLayout.this.t(this.a, optimizationLevel, i, i2);
                    }
                } else {
                    if (this.c != null) {
                        MotionLayout.this.t(this.a, optimizationLevel, i, i2);
                    }
                    MotionLayout.this.t(this.b, optimizationLevel, i, i2);
                }
                MotionLayout.this.w0 = this.a.Q();
                MotionLayout.this.x0 = this.a.w();
                MotionLayout.this.y0 = this.b.Q();
                MotionLayout.this.z0 = this.b.w();
                MotionLayout motionLayout4 = MotionLayout.this;
                motionLayout4.v0 = (motionLayout4.w0 == motionLayout4.y0 && motionLayout4.x0 == motionLayout4.z0) ? false : true;
            }
            MotionLayout motionLayout5 = MotionLayout.this;
            int i3 = motionLayout5.w0;
            int i4 = motionLayout5.x0;
            int i5 = motionLayout5.A0;
            if (i5 == Integer.MIN_VALUE || i5 == 0) {
                i3 = (int) (i3 + (motionLayout5.C0 * (motionLayout5.y0 - i3)));
            }
            int i6 = motionLayout5.B0;
            if (i6 == Integer.MIN_VALUE || i6 == 0) {
                i4 = (int) (i4 + (motionLayout5.C0 * (motionLayout5.z0 - i4)));
            }
            MotionLayout.this.s(i, i2, i3, i4, this.a.d1() || this.b.d1(), this.a.b1() || this.b.b1());
        }

        public void g() {
            f(MotionLayout.this.F, MotionLayout.this.G);
            MotionLayout.this.q0();
        }

        public void h(int i, int i2) {
            this.e = i;
            this.f = i2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final void i(w5 w5Var, q6 q6Var) {
            SparseArray<ConstraintWidget> sparseArray = new SparseArray<>();
            Constraints.a aVar = new Constraints.a(-2, -2);
            sparseArray.clear();
            sparseArray.put(0, w5Var);
            sparseArray.put(MotionLayout.this.getId(), w5Var);
            for (ConstraintWidget constraintWidget : w5Var.L0()) {
                sparseArray.put(((View) constraintWidget.r()).getId(), constraintWidget);
            }
            for (ConstraintWidget constraintWidget2 : w5Var.L0()) {
                View view = (View) constraintWidget2.r();
                q6Var.g(view.getId(), aVar);
                constraintWidget2.F0(q6Var.t(view.getId()));
                constraintWidget2.i0(q6Var.o(view.getId()));
                if (view instanceof ConstraintHelper) {
                    q6Var.e((ConstraintHelper) view, constraintWidget2, aVar, sparseArray);
                    if (view instanceof Barrier) {
                        ((Barrier) view).u();
                    }
                }
                if (Build.VERSION.SDK_INT >= 17) {
                    aVar.resolveLayoutDirection(MotionLayout.this.getLayoutDirection());
                } else {
                    aVar.resolveLayoutDirection(0);
                }
                MotionLayout.this.b(false, view, constraintWidget2, aVar, sparseArray);
                if (q6Var.s(view.getId()) == 1) {
                    constraintWidget2.E0(view.getVisibility());
                } else {
                    constraintWidget2.E0(q6Var.r(view.getId()));
                }
            }
            for (ConstraintWidget constraintWidget3 : w5Var.L0()) {
                if (constraintWidget3 instanceof c6) {
                    ConstraintHelper constraintHelper = (ConstraintHelper) constraintWidget3.r();
                    z5 z5Var = (z5) constraintWidget3;
                    constraintHelper.s(w5Var, z5Var, sparseArray);
                    ((c6) z5Var).M0();
                }
            }
        }
    }

    public interface f {
        void a(MotionEvent motionEvent);

        float b();

        float c();

        void d();

        void e(int i);
    }

    public static class g implements f {
        public static g a = new g();
        public VelocityTracker b;

        public static g f() {
            a.b = VelocityTracker.obtain();
            return a;
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.f
        public void a(MotionEvent motionEvent) {
            VelocityTracker velocityTracker = this.b;
            if (velocityTracker != null) {
                velocityTracker.addMovement(motionEvent);
            }
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.f
        public float b() {
            return this.b.getYVelocity();
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.f
        public float c() {
            return this.b.getXVelocity();
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.f
        public void d() {
            this.b.recycle();
            this.b = null;
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.f
        public void e(int i) {
            this.b.computeCurrentVelocity(i);
        }
    }

    public class h {
        public float a = Float.NaN;
        public float b = Float.NaN;
        public int c = -1;
        public int d = -1;
        public final String e = "motion.progress";
        public final String f = "motion.velocity";
        public final String g = "motion.StartState";
        public final String h = "motion.EndState";

        public h() {
        }

        public void a() {
            int i = this.c;
            if (i != -1 || this.d != -1) {
                if (i == -1) {
                    MotionLayout.this.u0(this.d);
                } else {
                    int i2 = this.d;
                    if (i2 == -1) {
                        MotionLayout.this.o0(i, -1, -1);
                    } else {
                        MotionLayout.this.p0(i, i2);
                    }
                }
                MotionLayout.this.setState(TransitionState.SETUP);
            }
            if (Float.isNaN(this.b)) {
                if (Float.isNaN(this.a)) {
                    return;
                }
                MotionLayout.this.setProgress(this.a);
            } else {
                MotionLayout.this.n0(this.a, this.b);
                this.a = Float.NaN;
                this.b = Float.NaN;
                this.c = -1;
                this.d = -1;
            }
        }

        public Bundle b() {
            Bundle bundle = new Bundle();
            bundle.putFloat("motion.progress", this.a);
            bundle.putFloat("motion.velocity", this.b);
            bundle.putInt("motion.StartState", this.c);
            bundle.putInt("motion.EndState", this.d);
            return bundle;
        }

        public void c() {
            this.d = MotionLayout.this.E;
            this.c = MotionLayout.this.C;
            this.b = MotionLayout.this.getVelocity();
            this.a = MotionLayout.this.getProgress();
        }

        public void d(int i) {
            this.d = i;
        }

        public void e(float f) {
            this.a = f;
        }

        public void f(int i) {
            this.c = i;
        }

        public void g(Bundle bundle) {
            this.a = bundle.getFloat("motion.progress");
            this.b = bundle.getFloat("motion.velocity");
            this.c = bundle.getInt("motion.StartState");
            this.d = bundle.getInt("motion.EndState");
        }

        public void h(float f) {
            this.b = f;
        }
    }

    public interface i {
        void a(MotionLayout motionLayout, int i, int i2);

        void b(MotionLayout motionLayout, int i, boolean z, float f);

        void c(MotionLayout motionLayout, int i, int i2, float f);

        void d(MotionLayout motionLayout, int i);
    }

    public MotionLayout(Context context) {
        super(context);
        this.B = 0.0f;
        this.C = -1;
        this.D = -1;
        this.E = -1;
        this.F = 0;
        this.G = 0;
        this.H = true;
        this.I = new HashMap<>();
        this.J = 0L;
        this.K = 1.0f;
        this.L = 0.0f;
        this.M = 0.0f;
        this.O = 0.0f;
        this.Q = false;
        this.R = false;
        this.V = 0;
        this.X = false;
        this.Y = new o4();
        this.Z = new c();
        this.b0 = true;
        this.g0 = false;
        this.l0 = false;
        this.m0 = null;
        this.n0 = null;
        this.o0 = null;
        this.p0 = 0;
        this.q0 = -1L;
        this.r0 = 0.0f;
        this.s0 = 0;
        this.t0 = 0.0f;
        this.u0 = false;
        this.v0 = false;
        this.D0 = new u4();
        this.E0 = false;
        this.G0 = TransitionState.UNDEFINED;
        this.H0 = new e();
        this.I0 = false;
        this.J0 = new RectF();
        this.K0 = null;
        this.L0 = new ArrayList<>();
        h0(null);
    }

    public static boolean w0(float f2, float f3, float f4) {
        if (f2 > 0.0f) {
            float f5 = f2 / f4;
            return f3 + ((f2 * f5) - (((f4 * f5) * f5) / 2.0f)) > 1.0f;
        }
        float f6 = (-f2) / f4;
        return f3 + ((f2 * f6) + (((f4 * f6) * f6) / 2.0f)) < 0.0f;
    }

    public void T(float f2) {
        if (this.z == null) {
            return;
        }
        float f3 = this.M;
        float f4 = this.L;
        if (f3 != f4 && this.P) {
            this.M = f4;
        }
        float f5 = this.M;
        if (f5 == f2) {
            return;
        }
        this.X = false;
        this.O = f2;
        this.K = r0.m() / 1000.0f;
        setProgress(this.O);
        this.A = this.z.p();
        this.P = false;
        this.J = getNanoTime();
        this.Q = true;
        this.L = f5;
        this.M = f5;
        invalidate();
    }

    public final void U() {
        g5 g5Var = this.z;
        if (g5Var == null) {
            Log.e("MotionLayout", "CHECK: motion scene not set! set \"app:layoutDescription=\"@xml/file\"");
            return;
        }
        int iX = g5Var.x();
        g5 g5Var2 = this.z;
        V(iX, g5Var2.i(g5Var2.x()));
        SparseIntArray sparseIntArray = new SparseIntArray();
        SparseIntArray sparseIntArray2 = new SparseIntArray();
        for (g5.b bVar : this.z.l()) {
            if (bVar == this.z.c) {
                Log.v("MotionLayout", "CHECK: CURRENT");
            }
            W(bVar);
            int iA = bVar.A();
            int iY = bVar.y();
            String strB = q4.b(getContext(), iA);
            String strB2 = q4.b(getContext(), iY);
            if (sparseIntArray.get(iA) == iY) {
                Log.e("MotionLayout", "CHECK: two transitions with the same start and end " + strB + "->" + strB2);
            }
            if (sparseIntArray2.get(iY) == iA) {
                Log.e("MotionLayout", "CHECK: you can't have reverse transitions" + strB + "->" + strB2);
            }
            sparseIntArray.put(iA, iY);
            sparseIntArray2.put(iY, iA);
            if (this.z.i(iA) == null) {
                Log.e("MotionLayout", " no such constraintSetStart " + strB);
            }
            if (this.z.i(iY) == null) {
                Log.e("MotionLayout", " no such constraintSetEnd " + strB);
            }
        }
    }

    public final void V(int i2, q6 q6Var) {
        String strB = q4.b(getContext(), i2);
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            int id = childAt.getId();
            if (id == -1) {
                Log.w("MotionLayout", "CHECK: " + strB + " ALL VIEWS SHOULD HAVE ID's " + childAt.getClass().getName() + " does not!");
            }
            if (q6Var.n(id) == null) {
                Log.w("MotionLayout", "CHECK: " + strB + " NO CONSTRAINTS for " + q4.c(childAt));
            }
        }
        int[] iArrP = q6Var.p();
        for (int i4 = 0; i4 < iArrP.length; i4++) {
            int i5 = iArrP[i4];
            String strB2 = q4.b(getContext(), i5);
            if (findViewById(iArrP[i4]) == null) {
                Log.w("MotionLayout", "CHECK: " + strB + " NO View matches id " + strB2);
            }
            if (q6Var.o(i5) == -1) {
                Log.w("MotionLayout", "CHECK: " + strB + "(" + strB2 + ") no LAYOUT_HEIGHT");
            }
            if (q6Var.t(i5) == -1) {
                Log.w("MotionLayout", "CHECK: " + strB + "(" + strB2 + ") no LAYOUT_HEIGHT");
            }
        }
    }

    public final void W(g5.b bVar) {
        Log.v("MotionLayout", "CHECK: transition = " + bVar.u(getContext()));
        Log.v("MotionLayout", "CHECK: transition.setDuration = " + bVar.x());
        if (bVar.A() == bVar.y()) {
            Log.e("MotionLayout", "CHECK: start and end constraint set should not be the same!");
        }
    }

    public final void X() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            d5 d5Var = this.I.get(childAt);
            if (d5Var != null) {
                d5Var.s(childAt);
            }
        }
    }

    public void Y(boolean z) {
        float f2;
        boolean z2;
        int i2;
        float interpolation;
        boolean z3;
        if (this.N == -1) {
            this.N = getNanoTime();
        }
        float f3 = this.M;
        if (f3 > 0.0f && f3 < 1.0f) {
            this.D = -1;
        }
        boolean z4 = false;
        if (this.l0 || (this.Q && (z || this.O != f3))) {
            float fSignum = Math.signum(this.O - f3);
            long nanoTime = getNanoTime();
            Interpolator interpolator = this.A;
            if (interpolator instanceof e5) {
                f2 = 0.0f;
            } else {
                f2 = (((nanoTime - this.N) * fSignum) * 1.0E-9f) / this.K;
                this.B = f2;
            }
            float f4 = this.M + f2;
            if (this.P) {
                f4 = this.O;
            }
            if ((fSignum <= 0.0f || f4 < this.O) && (fSignum > 0.0f || f4 > this.O)) {
                z2 = false;
            } else {
                f4 = this.O;
                this.Q = false;
                z2 = true;
            }
            this.M = f4;
            this.L = f4;
            this.N = nanoTime;
            if (interpolator != null && !z2) {
                if (this.X) {
                    interpolation = interpolator.getInterpolation((nanoTime - this.J) * 1.0E-9f);
                    this.M = interpolation;
                    this.N = nanoTime;
                    Interpolator interpolator2 = this.A;
                    if (interpolator2 instanceof e5) {
                        float fA = ((e5) interpolator2).a();
                        this.B = fA;
                        if (Math.abs(fA) * this.K <= 1.0E-5f) {
                            this.Q = false;
                        }
                        if (fA > 0.0f && interpolation >= 1.0f) {
                            this.M = 1.0f;
                            this.Q = false;
                            interpolation = 1.0f;
                        }
                        if (fA < 0.0f && interpolation <= 0.0f) {
                            this.M = 0.0f;
                            this.Q = false;
                            f4 = 0.0f;
                        }
                    }
                } else {
                    interpolation = interpolator.getInterpolation(f4);
                    Interpolator interpolator3 = this.A;
                    if (interpolator3 instanceof e5) {
                        this.B = ((e5) interpolator3).a();
                    } else {
                        this.B = ((interpolator3.getInterpolation(f4 + f2) - interpolation) * fSignum) / f2;
                    }
                }
                f4 = interpolation;
            }
            if (Math.abs(this.B) > 1.0E-5f) {
                setState(TransitionState.MOVING);
            }
            if ((fSignum > 0.0f && f4 >= this.O) || (fSignum <= 0.0f && f4 <= this.O)) {
                f4 = this.O;
                this.Q = false;
            }
            if (f4 >= 1.0f || f4 <= 0.0f) {
                this.Q = false;
                setState(TransitionState.FINISHED);
            }
            int childCount = getChildCount();
            this.l0 = false;
            long nanoTime2 = getNanoTime();
            this.C0 = f4;
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = getChildAt(i3);
                d5 d5Var = this.I.get(childAt);
                if (d5Var != null) {
                    this.l0 = d5Var.o(childAt, f4, nanoTime2, this.D0) | this.l0;
                }
            }
            boolean z5 = (fSignum > 0.0f && f4 >= this.O) || (fSignum <= 0.0f && f4 <= this.O);
            if (!this.l0 && !this.Q && z5) {
                setState(TransitionState.FINISHED);
            }
            if (this.v0) {
                requestLayout();
            }
            this.l0 = (!z5) | this.l0;
            if (f4 <= 0.0f && (i2 = this.C) != -1 && this.D != i2) {
                this.D = i2;
                this.z.i(i2).c(this);
                setState(TransitionState.FINISHED);
                z4 = true;
            }
            if (f4 >= 1.0d) {
                int i4 = this.D;
                int i5 = this.E;
                if (i4 != i5) {
                    this.D = i5;
                    this.z.i(i5).c(this);
                    setState(TransitionState.FINISHED);
                    z4 = true;
                }
            }
            if (this.l0 || this.Q) {
                invalidate();
            } else if ((fSignum > 0.0f && f4 == 1.0f) || (fSignum < 0.0f && f4 == 0.0f)) {
                setState(TransitionState.FINISHED);
            }
            if ((!this.l0 && this.Q && fSignum > 0.0f && f4 == 1.0f) || (fSignum < 0.0f && f4 == 0.0f)) {
                k0();
            }
        }
        float f5 = this.M;
        if (f5 < 1.0f) {
            if (f5 <= 0.0f) {
                int i6 = this.D;
                int i7 = this.C;
                z3 = i6 == i7 ? z4 : true;
                this.D = i7;
            }
            this.I0 |= z4;
            if (z4 && !this.E0) {
                requestLayout();
            }
            this.L = this.M;
        }
        int i8 = this.D;
        int i9 = this.E;
        z3 = i8 == i9 ? z4 : true;
        this.D = i9;
        z4 = z3;
        this.I0 |= z4;
        if (z4) {
            requestLayout();
        }
        this.L = this.M;
    }

    public final void Z() {
        boolean z;
        float fSignum = Math.signum(this.O - this.M);
        long nanoTime = getNanoTime();
        Interpolator interpolator = this.A;
        float interpolation = this.M + (!(interpolator instanceof o4) ? (((nanoTime - this.N) * fSignum) * 1.0E-9f) / this.K : 0.0f);
        if (this.P) {
            interpolation = this.O;
        }
        if ((fSignum <= 0.0f || interpolation < this.O) && (fSignum > 0.0f || interpolation > this.O)) {
            z = false;
        } else {
            interpolation = this.O;
            z = true;
        }
        if (interpolator != null && !z) {
            interpolation = this.X ? interpolator.getInterpolation((nanoTime - this.J) * 1.0E-9f) : interpolator.getInterpolation(interpolation);
        }
        if ((fSignum > 0.0f && interpolation >= this.O) || (fSignum <= 0.0f && interpolation <= this.O)) {
            interpolation = this.O;
        }
        this.C0 = interpolation;
        int childCount = getChildCount();
        long nanoTime2 = getNanoTime();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            d5 d5Var = this.I.get(childAt);
            if (d5Var != null) {
                d5Var.o(childAt, interpolation, nanoTime2, this.D0);
            }
        }
        if (this.v0) {
            requestLayout();
        }
    }

    public final void a0() {
        ArrayList<i> arrayList;
        if ((this.S == null && ((arrayList = this.o0) == null || arrayList.isEmpty())) || this.t0 == this.L) {
            return;
        }
        if (this.s0 != -1) {
            i iVar = this.S;
            if (iVar != null) {
                iVar.a(this, this.C, this.E);
            }
            ArrayList<i> arrayList2 = this.o0;
            if (arrayList2 != null) {
                Iterator<i> it = arrayList2.iterator();
                while (it.hasNext()) {
                    it.next().a(this, this.C, this.E);
                }
            }
            this.u0 = true;
        }
        this.s0 = -1;
        float f2 = this.L;
        this.t0 = f2;
        i iVar2 = this.S;
        if (iVar2 != null) {
            iVar2.c(this, this.C, this.E, f2);
        }
        ArrayList<i> arrayList3 = this.o0;
        if (arrayList3 != null) {
            Iterator<i> it2 = arrayList3.iterator();
            while (it2.hasNext()) {
                it2.next().c(this, this.C, this.E, this.L);
            }
        }
        this.u0 = true;
    }

    public void b0() {
        int iIntValue;
        ArrayList<i> arrayList;
        if ((this.S != null || ((arrayList = this.o0) != null && !arrayList.isEmpty())) && this.s0 == -1) {
            this.s0 = this.D;
            if (this.L0.isEmpty()) {
                iIntValue = -1;
            } else {
                iIntValue = this.L0.get(r0.size() - 1).intValue();
            }
            int i2 = this.D;
            if (iIntValue != i2 && i2 != -1) {
                this.L0.add(Integer.valueOf(i2));
            }
        }
        l0();
    }

    public void c0(int i2, boolean z, float f2) {
        i iVar = this.S;
        if (iVar != null) {
            iVar.b(this, i2, z, f2);
        }
        ArrayList<i> arrayList = this.o0;
        if (arrayList != null) {
            Iterator<i> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().b(this, i2, z, f2);
            }
        }
    }

    public void d0(int i2, float f2, float f3, float f4, float[] fArr) {
        String resourceName;
        HashMap<View, d5> map = this.I;
        View viewG = g(i2);
        d5 d5Var = map.get(viewG);
        if (d5Var != null) {
            d5Var.g(f2, f3, f4, fArr);
            float y2 = viewG.getY();
            int i3 = ((f2 - this.T) > 0.0f ? 1 : ((f2 - this.T) == 0.0f ? 0 : -1));
            this.T = f2;
            this.U = y2;
            return;
        }
        if (viewG == null) {
            resourceName = "" + i2;
        } else {
            resourceName = viewG.getContext().getResources().getResourceName(i2);
        }
        Log.w("MotionLayout", "WARNING could not find view id " + resourceName);
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        Y(false);
        super.dispatchDraw(canvas);
        if (this.z == null) {
            return;
        }
        if ((this.V & 1) == 1 && !isInEditMode()) {
            this.p0++;
            long nanoTime = getNanoTime();
            long j = this.q0;
            if (j != -1) {
                if (nanoTime - j > 200000000) {
                    this.r0 = ((int) ((this.p0 / (r5 * 1.0E-9f)) * 100.0f)) / 100.0f;
                    this.p0 = 0;
                    this.q0 = nanoTime;
                }
            } else {
                this.q0 = nanoTime;
            }
            Paint paint = new Paint();
            paint.setTextSize(42.0f);
            String str = this.r0 + " fps " + q4.d(this, this.C) + " -> ";
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(q4.d(this, this.E));
            sb.append(" (progress: ");
            sb.append(((int) (getProgress() * 1000.0f)) / 10.0f);
            sb.append(" ) state=");
            int i2 = this.D;
            sb.append(i2 == -1 ? "undefined" : q4.d(this, i2));
            String string = sb.toString();
            paint.setColor(-16777216);
            canvas.drawText(string, 11.0f, getHeight() - 29, paint);
            paint.setColor(-7864184);
            canvas.drawText(string, 10.0f, getHeight() - 30, paint);
        }
        if (this.V > 1) {
            if (this.W == null) {
                this.W = new d();
            }
            this.W.a(canvas, this.I, this.z.m(), this.V);
        }
    }

    public g5.b e0(int i2) {
        return this.z.y(i2);
    }

    public void f0(View view, float f2, float f3, float[] fArr, int i2) {
        float f4;
        float fA = this.B;
        float f5 = this.M;
        if (this.A != null) {
            float fSignum = Math.signum(this.O - f5);
            float interpolation = this.A.getInterpolation(this.M + 1.0E-5f);
            float interpolation2 = this.A.getInterpolation(this.M);
            fA = (fSignum * ((interpolation - interpolation2) / 1.0E-5f)) / this.K;
            f4 = interpolation2;
        } else {
            f4 = f5;
        }
        Interpolator interpolator = this.A;
        if (interpolator instanceof e5) {
            fA = ((e5) interpolator).a();
        }
        d5 d5Var = this.I.get(view);
        if ((i2 & 1) == 0) {
            d5Var.l(f4, view.getWidth(), view.getHeight(), f2, f3, fArr);
        } else {
            d5Var.g(f4, f2, f3, fArr);
        }
        if (i2 < 2) {
            fArr[0] = fArr[0] * fA;
            fArr[1] = fArr[1] * fA;
        }
    }

    public final boolean g0(float f2, float f3, View view, MotionEvent motionEvent) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                if (g0(view.getLeft() + f2, view.getTop() + f3, viewGroup.getChildAt(i2), motionEvent)) {
                    return true;
                }
            }
        }
        this.J0.set(view.getLeft() + f2, view.getTop() + f3, f2 + view.getRight(), f3 + view.getBottom());
        if (motionEvent.getAction() == 0) {
            if (this.J0.contains(motionEvent.getX(), motionEvent.getY()) && view.onTouchEvent(motionEvent)) {
                return true;
            }
        } else if (view.onTouchEvent(motionEvent)) {
            return true;
        }
        return false;
    }

    public int[] getConstraintSetIds() {
        g5 g5Var = this.z;
        if (g5Var == null) {
            return null;
        }
        return g5Var.k();
    }

    public int getCurrentState() {
        return this.D;
    }

    public ArrayList<g5.b> getDefinedTransitions() {
        g5 g5Var = this.z;
        if (g5Var == null) {
            return null;
        }
        return g5Var.l();
    }

    public r4 getDesignTool() {
        if (this.a0 == null) {
            this.a0 = new r4(this);
        }
        return this.a0;
    }

    public int getEndState() {
        return this.E;
    }

    public long getNanoTime() {
        return System.nanoTime();
    }

    public float getProgress() {
        return this.M;
    }

    public int getStartState() {
        return this.C;
    }

    public float getTargetPosition() {
        return this.O;
    }

    public Bundle getTransitionState() {
        if (this.F0 == null) {
            this.F0 = new h();
        }
        this.F0.c();
        return this.F0.b();
    }

    public long getTransitionTimeMs() {
        if (this.z != null) {
            this.K = r0.m() / 1000.0f;
        }
        return (long) (this.K * 1000.0f);
    }

    public float getVelocity() {
        return this.B;
    }

    @Override // defpackage.l9
    public void h(View view, View view2, int i2, int i3) {
    }

    public final void h0(AttributeSet attributeSet) {
        g5 g5Var;
        y = isInEditMode();
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.MotionLayout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            boolean z = true;
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                if (index == R$styleable.MotionLayout_layoutDescription) {
                    this.z = new g5(getContext(), this, typedArrayObtainStyledAttributes.getResourceId(index, -1));
                } else if (index == R$styleable.MotionLayout_currentState) {
                    this.D = typedArrayObtainStyledAttributes.getResourceId(index, -1);
                } else if (index == R$styleable.MotionLayout_motionProgress) {
                    this.O = typedArrayObtainStyledAttributes.getFloat(index, 0.0f);
                    this.Q = true;
                } else if (index == R$styleable.MotionLayout_applyMotionScene) {
                    z = typedArrayObtainStyledAttributes.getBoolean(index, z);
                } else if (index == R$styleable.MotionLayout_showPaths) {
                    if (this.V == 0) {
                        this.V = typedArrayObtainStyledAttributes.getBoolean(index, false) ? 2 : 0;
                    }
                } else if (index == R$styleable.MotionLayout_motionDebug) {
                    this.V = typedArrayObtainStyledAttributes.getInt(index, 0);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
            if (this.z == null) {
                Log.e("MotionLayout", "WARNING NO app:layoutDescription tag");
            }
            if (!z) {
                this.z = null;
            }
        }
        if (this.V != 0) {
            U();
        }
        if (this.D != -1 || (g5Var = this.z) == null) {
            return;
        }
        this.D = g5Var.x();
        this.C = this.z.x();
        this.E = this.z.n();
    }

    @Override // defpackage.l9
    public void i(View view, int i2) {
        g5 g5Var = this.z;
        if (g5Var == null) {
            return;
        }
        float f2 = this.h0;
        float f3 = this.k0;
        g5Var.G(f2 / f3, this.i0 / f3);
    }

    public boolean i0() {
        return this.H;
    }

    @Override // android.view.View
    public boolean isAttachedToWindow() {
        return Build.VERSION.SDK_INT >= 19 ? super.isAttachedToWindow() : getWindowToken() != null;
    }

    @Override // defpackage.l9
    public void j(View view, int i2, int i3, int[] iArr, int i4) {
        g5.b bVar;
        j5 j5VarB;
        int iK;
        g5 g5Var = this.z;
        if (g5Var == null || (bVar = g5Var.c) == null || !bVar.C()) {
            return;
        }
        g5.b bVar2 = this.z.c;
        if (bVar2 == null || !bVar2.C() || (j5VarB = bVar2.B()) == null || (iK = j5VarB.k()) == -1 || view.getId() == iK) {
            g5 g5Var2 = this.z;
            if (g5Var2 != null && g5Var2.t()) {
                float f2 = this.L;
                if ((f2 == 1.0f || f2 == 0.0f) && view.canScrollVertically(-1)) {
                    return;
                }
            }
            if (bVar2.B() != null && (this.z.c.B().d() & 1) != 0) {
                float fU = this.z.u(i2, i3);
                float f3 = this.M;
                if ((f3 <= 0.0f && fU < 0.0f) || (f3 >= 1.0f && fU > 0.0f)) {
                    if (Build.VERSION.SDK_INT >= 21) {
                        view.setNestedScrollingEnabled(false);
                        view.post(new a(view));
                        return;
                    }
                    return;
                }
            }
            float f4 = this.L;
            long nanoTime = getNanoTime();
            float f5 = i2;
            this.h0 = f5;
            float f6 = i3;
            this.i0 = f6;
            this.k0 = (float) ((nanoTime - this.j0) * 1.0E-9d);
            this.j0 = nanoTime;
            this.z.F(f5, f6);
            if (f4 != this.L) {
                iArr[0] = i2;
                iArr[1] = i3;
            }
            Y(false);
            if (iArr[0] == 0 && iArr[1] == 0) {
                return;
            }
            this.g0 = true;
        }
    }

    public f j0() {
        return g.f();
    }

    public final void k0() {
        g5 g5Var = this.z;
        if (g5Var == null) {
            return;
        }
        if (g5Var.f(this, this.D)) {
            requestLayout();
            return;
        }
        int i2 = this.D;
        if (i2 != -1) {
            this.z.e(this, i2);
        }
        if (this.z.Q()) {
            this.z.O();
        }
    }

    public final void l0() {
        ArrayList<i> arrayList;
        if (this.S == null && ((arrayList = this.o0) == null || arrayList.isEmpty())) {
            return;
        }
        this.u0 = false;
        for (Integer num : this.L0) {
            i iVar = this.S;
            if (iVar != null) {
                iVar.d(this, num.intValue());
            }
            ArrayList<i> arrayList2 = this.o0;
            if (arrayList2 != null) {
                Iterator<i> it = arrayList2.iterator();
                while (it.hasNext()) {
                    it.next().d(this, num.intValue());
                }
            }
        }
        this.L0.clear();
    }

    @Override // defpackage.m9
    public void m(View view, int i2, int i3, int i4, int i5, int i6, int[] iArr) {
        if (this.g0 || i2 != 0 || i3 != 0) {
            iArr[0] = iArr[0] + i4;
            iArr[1] = iArr[1] + i5;
        }
        this.g0 = false;
    }

    public void m0() {
        this.H0.g();
        invalidate();
    }

    @Override // defpackage.l9
    public void n(View view, int i2, int i3, int i4, int i5, int i6) {
    }

    public void n0(float f2, float f3) {
        if (isAttachedToWindow()) {
            setProgress(f2);
            setState(TransitionState.MOVING);
            this.B = f3;
            T(1.0f);
            return;
        }
        if (this.F0 == null) {
            this.F0 = new h();
        }
        this.F0.e(f2);
        this.F0.h(f3);
    }

    @Override // defpackage.l9
    public boolean o(View view, View view2, int i2, int i3) {
        g5.b bVar;
        g5 g5Var = this.z;
        return (g5Var == null || (bVar = g5Var.c) == null || bVar.B() == null || (this.z.c.B().d() & 2) != 0) ? false : true;
    }

    public void o0(int i2, int i3, int i4) {
        setState(TransitionState.SETUP);
        this.D = i2;
        this.C = -1;
        this.E = -1;
        p6 p6Var = this.l;
        if (p6Var != null) {
            p6Var.d(i2, i3, i4);
            return;
        }
        g5 g5Var = this.z;
        if (g5Var != null) {
            g5Var.i(i2).d(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        int i2;
        super.onAttachedToWindow();
        g5 g5Var = this.z;
        if (g5Var != null && (i2 = this.D) != -1) {
            q6 q6VarI = g5Var.i(i2);
            this.z.J(this);
            if (q6VarI != null) {
                q6VarI.d(this);
            }
            this.C = this.D;
        }
        k0();
        h hVar = this.F0;
        if (hVar != null) {
            hVar.a();
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        g5.b bVar;
        j5 j5VarB;
        int iK;
        RectF rectFJ;
        g5 g5Var = this.z;
        if (g5Var != null && this.H && (bVar = g5Var.c) != null && bVar.C() && (j5VarB = bVar.B()) != null && ((motionEvent.getAction() != 0 || (rectFJ = j5VarB.j(this, new RectF())) == null || rectFJ.contains(motionEvent.getX(), motionEvent.getY())) && (iK = j5VarB.k()) != -1)) {
            View view = this.K0;
            if (view == null || view.getId() != iK) {
                this.K0 = findViewById(iK);
            }
            if (this.K0 != null) {
                this.J0.set(r0.getLeft(), this.K0.getTop(), this.K0.getRight(), this.K0.getBottom());
                if (this.J0.contains(motionEvent.getX(), motionEvent.getY()) && !g0(0.0f, 0.0f, this.K0, motionEvent)) {
                    return onTouchEvent(motionEvent);
                }
            }
        }
        return false;
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        this.E0 = true;
        try {
            if (this.z == null) {
                super.onLayout(z, i2, i3, i4, i5);
                return;
            }
            int i6 = i4 - i2;
            int i7 = i5 - i3;
            if (this.e0 != i6 || this.f0 != i7) {
                m0();
                Y(true);
            }
            this.e0 = i6;
            this.f0 = i7;
            this.c0 = i6;
            this.d0 = i7;
        } finally {
            this.E0 = false;
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        if (this.z == null) {
            super.onMeasure(i2, i3);
            return;
        }
        boolean z = false;
        boolean z2 = (this.F == i2 && this.G == i3) ? false : true;
        if (this.I0) {
            this.I0 = false;
            k0();
            l0();
            z2 = true;
        }
        if (this.i) {
            z2 = true;
        }
        this.F = i2;
        this.G = i3;
        int iX = this.z.x();
        int iN = this.z.n();
        if ((z2 || this.H0.e(iX, iN)) && this.C != -1) {
            super.onMeasure(i2, i3);
            this.H0.d(this.d, this.z.i(iX), this.z.i(iN));
            this.H0.g();
            this.H0.h(iX, iN);
        } else {
            z = true;
        }
        if (this.v0 || z) {
            int paddingTop = getPaddingTop() + getPaddingBottom();
            int iQ = this.d.Q() + getPaddingLeft() + getPaddingRight();
            int iW = this.d.w() + paddingTop;
            int i4 = this.A0;
            if (i4 == Integer.MIN_VALUE || i4 == 0) {
                iQ = (int) (this.w0 + (this.C0 * (this.y0 - r7)));
                requestLayout();
            }
            int i5 = this.B0;
            if (i5 == Integer.MIN_VALUE || i5 == 0) {
                iW = (int) (this.x0 + (this.C0 * (this.z0 - r7)));
                requestLayout();
            }
            setMeasuredDimension(iQ, iW);
        }
        Z();
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, defpackage.n9
    public boolean onNestedFling(View view, float f2, float f3, boolean z) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, defpackage.n9
    public boolean onNestedPreFling(View view, float f2, float f3) {
        return false;
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i2) {
        g5 g5Var = this.z;
        if (g5Var != null) {
            g5Var.L(p());
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        g5 g5Var = this.z;
        if (g5Var == null || !this.H || !g5Var.Q()) {
            return super.onTouchEvent(motionEvent);
        }
        g5.b bVar = this.z.c;
        if (bVar != null && !bVar.C()) {
            return super.onTouchEvent(motionEvent);
        }
        this.z.H(motionEvent, getCurrentState(), this);
        return true;
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public void onViewAdded(View view) {
        super.onViewAdded(view);
        if (view instanceof MotionHelper) {
            MotionHelper motionHelper = (MotionHelper) view;
            if (this.o0 == null) {
                this.o0 = new ArrayList<>();
            }
            this.o0.add(motionHelper);
            if (motionHelper.w()) {
                if (this.m0 == null) {
                    this.m0 = new ArrayList<>();
                }
                this.m0.add(motionHelper);
            }
            if (motionHelper.v()) {
                if (this.n0 == null) {
                    this.n0 = new ArrayList<>();
                }
                this.n0.add(motionHelper);
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        ArrayList<MotionHelper> arrayList = this.m0;
        if (arrayList != null) {
            arrayList.remove(view);
        }
        ArrayList<MotionHelper> arrayList2 = this.n0;
        if (arrayList2 != null) {
            arrayList2.remove(view);
        }
    }

    public void p0(int i2, int i3) {
        if (!isAttachedToWindow()) {
            if (this.F0 == null) {
                this.F0 = new h();
            }
            this.F0.f(i2);
            this.F0.d(i3);
            return;
        }
        g5 g5Var = this.z;
        if (g5Var != null) {
            this.C = i2;
            this.E = i3;
            g5Var.M(i2, i3);
            this.H0.d(this.d, this.z.i(i2), this.z.i(i3));
            m0();
            this.M = 0.0f;
            t0();
        }
    }

    public final void q0() {
        int childCount = getChildCount();
        this.H0.a();
        boolean z = true;
        this.Q = true;
        int width = getWidth();
        int height = getHeight();
        int iH = this.z.h();
        int i2 = 0;
        if (iH != -1) {
            for (int i3 = 0; i3 < childCount; i3++) {
                d5 d5Var = this.I.get(getChildAt(i3));
                if (d5Var != null) {
                    d5Var.r(iH);
                }
            }
        }
        for (int i4 = 0; i4 < childCount; i4++) {
            d5 d5Var2 = this.I.get(getChildAt(i4));
            if (d5Var2 != null) {
                this.z.q(d5Var2);
                d5Var2.v(width, height, this.K, getNanoTime());
            }
        }
        float fW = this.z.w();
        if (fW != 0.0f) {
            boolean z2 = ((double) fW) < 0.0d;
            float fAbs = Math.abs(fW);
            float fMax = -3.4028235E38f;
            float fMin = Float.MAX_VALUE;
            float fMax2 = -3.4028235E38f;
            float fMin2 = Float.MAX_VALUE;
            int i5 = 0;
            while (true) {
                if (i5 >= childCount) {
                    z = false;
                    break;
                }
                d5 d5Var3 = this.I.get(getChildAt(i5));
                if (!Float.isNaN(d5Var3.k)) {
                    break;
                }
                float fI = d5Var3.i();
                float fJ = d5Var3.j();
                float f2 = z2 ? fJ - fI : fJ + fI;
                fMin2 = Math.min(fMin2, f2);
                fMax2 = Math.max(fMax2, f2);
                i5++;
            }
            if (!z) {
                while (i2 < childCount) {
                    d5 d5Var4 = this.I.get(getChildAt(i2));
                    float fI2 = d5Var4.i();
                    float fJ2 = d5Var4.j();
                    float f3 = z2 ? fJ2 - fI2 : fJ2 + fI2;
                    d5Var4.m = 1.0f / (1.0f - fAbs);
                    d5Var4.l = fAbs - (((f3 - fMin2) * fAbs) / (fMax2 - fMin2));
                    i2++;
                }
                return;
            }
            for (int i6 = 0; i6 < childCount; i6++) {
                d5 d5Var5 = this.I.get(getChildAt(i6));
                if (!Float.isNaN(d5Var5.k)) {
                    fMin = Math.min(fMin, d5Var5.k);
                    fMax = Math.max(fMax, d5Var5.k);
                }
            }
            while (i2 < childCount) {
                d5 d5Var6 = this.I.get(getChildAt(i2));
                if (!Float.isNaN(d5Var6.k)) {
                    d5Var6.m = 1.0f / (1.0f - fAbs);
                    if (z2) {
                        d5Var6.l = fAbs - (((fMax - d5Var6.k) / (fMax - fMin)) * fAbs);
                    } else {
                        d5Var6.l = fAbs - (((d5Var6.k - fMin) * fAbs) / (fMax - fMin));
                    }
                }
                i2++;
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout
    public void r(int i2) {
        this.l = null;
    }

    public void r0(int i2, float f2, float f3) {
        if (this.z == null || this.M == f2) {
            return;
        }
        this.X = true;
        this.J = getNanoTime();
        float fM = this.z.m() / 1000.0f;
        this.K = fM;
        this.O = f2;
        this.Q = true;
        if (i2 == 0 || i2 == 1 || i2 == 2) {
            if (i2 == 1) {
                f2 = 0.0f;
            } else if (i2 == 2) {
                f2 = 1.0f;
            }
            this.Y.c(this.M, f2, f3, fM, this.z.r(), this.z.s());
            int i3 = this.D;
            this.O = f2;
            this.D = i3;
            this.A = this.Y;
        } else if (i2 == 4) {
            this.Z.b(f3, this.M, this.z.r());
            this.A = this.Z;
        } else if (i2 == 5) {
            if (w0(f3, this.M, this.z.r())) {
                this.Z.b(f3, this.M, this.z.r());
                this.A = this.Z;
            } else {
                this.Y.c(this.M, f2, f3, this.K, this.z.r(), this.z.s());
                this.B = 0.0f;
                int i4 = this.D;
                this.O = f2;
                this.D = i4;
                this.A = this.Y;
            }
        }
        this.P = false;
        this.J = getNanoTime();
        invalidate();
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View, android.view.ViewParent
    public void requestLayout() {
        g5 g5Var;
        g5.b bVar;
        if (this.v0 || this.D != -1 || (g5Var = this.z) == null || (bVar = g5Var.c) == null || bVar.z() != 0) {
            super.requestLayout();
        }
    }

    public void s0() {
        T(1.0f);
    }

    public void setDebugMode(int i2) {
        this.V = i2;
        invalidate();
    }

    public void setInteractionEnabled(boolean z) {
        this.H = z;
    }

    public void setInterpolatedProgress(float f2) {
        if (this.z != null) {
            setState(TransitionState.MOVING);
            Interpolator interpolatorP = this.z.p();
            if (interpolatorP != null) {
                setProgress(interpolatorP.getInterpolation(f2));
                return;
            }
        }
        setProgress(f2);
    }

    public void setOnHide(float f2) {
        ArrayList<MotionHelper> arrayList = this.n0;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.n0.get(i2).setProgress(f2);
            }
        }
    }

    public void setOnShow(float f2) {
        ArrayList<MotionHelper> arrayList = this.m0;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.m0.get(i2).setProgress(f2);
            }
        }
    }

    public void setProgress(float f2) {
        if (!isAttachedToWindow()) {
            if (this.F0 == null) {
                this.F0 = new h();
            }
            this.F0.e(f2);
            return;
        }
        if (f2 <= 0.0f) {
            this.D = this.C;
            if (this.M == 0.0f) {
                setState(TransitionState.FINISHED);
            }
        } else if (f2 >= 1.0f) {
            this.D = this.E;
            if (this.M == 1.0f) {
                setState(TransitionState.FINISHED);
            }
        } else {
            this.D = -1;
            setState(TransitionState.MOVING);
        }
        if (this.z == null) {
            return;
        }
        this.P = true;
        this.O = f2;
        this.L = f2;
        this.N = -1L;
        this.J = -1L;
        this.A = null;
        this.Q = true;
        invalidate();
    }

    public void setScene(g5 g5Var) {
        this.z = g5Var;
        g5Var.L(p());
        m0();
    }

    public void setState(TransitionState transitionState) {
        TransitionState transitionState2 = TransitionState.FINISHED;
        if (transitionState == transitionState2 && this.D == -1) {
            return;
        }
        TransitionState transitionState3 = this.G0;
        this.G0 = transitionState;
        TransitionState transitionState4 = TransitionState.MOVING;
        if (transitionState3 == transitionState4 && transitionState == transitionState4) {
            a0();
        }
        int i2 = b.a[transitionState3.ordinal()];
        if (i2 != 1 && i2 != 2) {
            if (i2 == 3 && transitionState == transitionState2) {
                b0();
                return;
            }
            return;
        }
        if (transitionState == transitionState4) {
            a0();
        }
        if (transitionState == transitionState2) {
            b0();
        }
    }

    public void setTransition(int i2) {
        if (this.z != null) {
            g5.b bVarE0 = e0(i2);
            this.C = bVarE0.A();
            this.E = bVarE0.y();
            if (!isAttachedToWindow()) {
                if (this.F0 == null) {
                    this.F0 = new h();
                }
                this.F0.f(this.C);
                this.F0.d(this.E);
                return;
            }
            float f2 = Float.NaN;
            int i3 = this.D;
            if (i3 == this.C) {
                f2 = 0.0f;
            } else if (i3 == this.E) {
                f2 = 1.0f;
            }
            this.z.N(bVarE0);
            this.H0.d(this.d, this.z.i(this.C), this.z.i(this.E));
            m0();
            this.M = Float.isNaN(f2) ? 0.0f : f2;
            if (!Float.isNaN(f2)) {
                setProgress(f2);
                return;
            }
            Log.v("MotionLayout", q4.a() + " transitionToStart ");
            t0();
        }
    }

    public void setTransitionDuration(int i2) {
        g5 g5Var = this.z;
        if (g5Var == null) {
            Log.e("MotionLayout", "MotionScene not defined");
        } else {
            g5Var.K(i2);
        }
    }

    public void setTransitionListener(i iVar) {
        this.S = iVar;
    }

    public void setTransitionState(Bundle bundle) {
        if (this.F0 == null) {
            this.F0 = new h();
        }
        this.F0.g(bundle);
        if (isAttachedToWindow()) {
            this.F0.a();
        }
    }

    public void t0() {
        T(0.0f);
    }

    @Override // android.view.View
    public String toString() {
        Context context = getContext();
        return q4.b(context, this.C) + "->" + q4.b(context, this.E) + " (pos:" + this.M + " Dpos/Dt:" + this.B;
    }

    public void u0(int i2) {
        if (isAttachedToWindow()) {
            v0(i2, -1, -1);
            return;
        }
        if (this.F0 == null) {
            this.F0 = new h();
        }
        this.F0.d(i2);
    }

    public void v0(int i2, int i3, int i4) {
        s6 s6Var;
        int iA;
        g5 g5Var = this.z;
        if (g5Var != null && (s6Var = g5Var.b) != null && (iA = s6Var.a(this.D, i2, i3, i4)) != -1) {
            i2 = iA;
        }
        int i5 = this.D;
        if (i5 == i2) {
            return;
        }
        if (this.C == i2) {
            T(0.0f);
            return;
        }
        if (this.E == i2) {
            T(1.0f);
            return;
        }
        this.E = i2;
        if (i5 != -1) {
            p0(i5, i2);
            T(1.0f);
            this.M = 0.0f;
            s0();
            return;
        }
        this.X = false;
        this.O = 1.0f;
        this.L = 0.0f;
        this.M = 0.0f;
        this.N = getNanoTime();
        this.J = getNanoTime();
        this.P = false;
        this.A = null;
        this.K = this.z.m() / 1000.0f;
        this.C = -1;
        this.z.M(-1, this.E);
        this.z.x();
        int childCount = getChildCount();
        this.I.clear();
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            this.I.put(childAt, new d5(childAt));
        }
        this.Q = true;
        this.H0.d(this.d, null, this.z.i(i2));
        m0();
        this.H0.a();
        X();
        int width = getWidth();
        int height = getHeight();
        for (int i7 = 0; i7 < childCount; i7++) {
            d5 d5Var = this.I.get(getChildAt(i7));
            this.z.q(d5Var);
            d5Var.v(width, height, this.K, getNanoTime());
        }
        float fW = this.z.w();
        if (fW != 0.0f) {
            float fMin = Float.MAX_VALUE;
            float fMax = -3.4028235E38f;
            for (int i8 = 0; i8 < childCount; i8++) {
                d5 d5Var2 = this.I.get(getChildAt(i8));
                float fJ = d5Var2.j() + d5Var2.i();
                fMin = Math.min(fMin, fJ);
                fMax = Math.max(fMax, fJ);
            }
            for (int i9 = 0; i9 < childCount; i9++) {
                d5 d5Var3 = this.I.get(getChildAt(i9));
                float fI = d5Var3.i();
                float fJ2 = d5Var3.j();
                d5Var3.m = 1.0f / (1.0f - fW);
                d5Var3.l = fW - ((((fI + fJ2) - fMin) * fW) / (fMax - fMin));
            }
        }
        this.L = 0.0f;
        this.M = 0.0f;
        this.Q = true;
        invalidate();
    }

    public void setTransition(g5.b bVar) {
        this.z.N(bVar);
        setState(TransitionState.SETUP);
        if (this.D == this.z.n()) {
            this.M = 1.0f;
            this.L = 1.0f;
            this.O = 1.0f;
        } else {
            this.M = 0.0f;
            this.L = 0.0f;
            this.O = 0.0f;
        }
        this.N = bVar.D(1) ? -1L : getNanoTime();
        int iX = this.z.x();
        int iN = this.z.n();
        if (iX == this.C && iN == this.E) {
            return;
        }
        this.C = iX;
        this.E = iN;
        this.z.M(iX, iN);
        this.H0.d(this.d, this.z.i(this.C), this.z.i(this.E));
        this.H0.h(this.C, this.E);
        this.H0.g();
        m0();
    }

    public MotionLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.B = 0.0f;
        this.C = -1;
        this.D = -1;
        this.E = -1;
        this.F = 0;
        this.G = 0;
        this.H = true;
        this.I = new HashMap<>();
        this.J = 0L;
        this.K = 1.0f;
        this.L = 0.0f;
        this.M = 0.0f;
        this.O = 0.0f;
        this.Q = false;
        this.R = false;
        this.V = 0;
        this.X = false;
        this.Y = new o4();
        this.Z = new c();
        this.b0 = true;
        this.g0 = false;
        this.l0 = false;
        this.m0 = null;
        this.n0 = null;
        this.o0 = null;
        this.p0 = 0;
        this.q0 = -1L;
        this.r0 = 0.0f;
        this.s0 = 0;
        this.t0 = 0.0f;
        this.u0 = false;
        this.v0 = false;
        this.D0 = new u4();
        this.E0 = false;
        this.G0 = TransitionState.UNDEFINED;
        this.H0 = new e();
        this.I0 = false;
        this.J0 = new RectF();
        this.K0 = null;
        this.L0 = new ArrayList<>();
        h0(attributeSet);
    }

    public MotionLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.B = 0.0f;
        this.C = -1;
        this.D = -1;
        this.E = -1;
        this.F = 0;
        this.G = 0;
        this.H = true;
        this.I = new HashMap<>();
        this.J = 0L;
        this.K = 1.0f;
        this.L = 0.0f;
        this.M = 0.0f;
        this.O = 0.0f;
        this.Q = false;
        this.R = false;
        this.V = 0;
        this.X = false;
        this.Y = new o4();
        this.Z = new c();
        this.b0 = true;
        this.g0 = false;
        this.l0 = false;
        this.m0 = null;
        this.n0 = null;
        this.o0 = null;
        this.p0 = 0;
        this.q0 = -1L;
        this.r0 = 0.0f;
        this.s0 = 0;
        this.t0 = 0.0f;
        this.u0 = false;
        this.v0 = false;
        this.D0 = new u4();
        this.E0 = false;
        this.G0 = TransitionState.UNDEFINED;
        this.H0 = new e();
        this.I0 = false;
        this.J0 = new RectF();
        this.K0 = null;
        this.L0 = new ArrayList<>();
        h0(attributeSet);
    }
}
