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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import o0.m;
import x.n;
import x.o;
import x.q;
import x.t;
import z.j;

/* JADX INFO: loaded from: classes.dex */
public class MotionLayout extends ConstraintLayout implements m {
    public static boolean A;
    public int A0;
    public q B;
    public int B0;
    public Interpolator C;
    public int C0;
    public float D;
    public int D0;
    public int E;
    public float E0;
    public int F;
    public x.e F0;
    public int G;
    public boolean G0;
    public int H;
    public h H0;
    public int I;
    public TransitionState I0;
    public boolean J;
    public e J0;
    public HashMap<View, n> K;
    public boolean K0;
    public long L;
    public RectF L0;
    public float M;
    public View M0;
    public float N;
    public ArrayList<Integer> N0;
    public float O;
    public long P;
    public float Q;
    public boolean R;
    public boolean S;
    public boolean T;
    public i U;
    public float V;
    public float W;
    public int X;
    public d Y;
    public boolean Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public w.g f829a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public c f830b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public x.b f831c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public boolean f832d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public int f833e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public int f834f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public int f835g0;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public int f836h0;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public boolean f837i0;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public float f838j0;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public float f839k0;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public long f840l0;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    public float f841m0;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public boolean f842n0;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public ArrayList<MotionHelper> f843o0;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    public ArrayList<MotionHelper> f844p0;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    public ArrayList<i> f845q0;

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    public int f846r0;

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    public long f847s0;

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    public float f848t0;

    /* JADX INFO: renamed from: u0, reason: collision with root package name */
    public int f849u0;

    /* JADX INFO: renamed from: v0, reason: collision with root package name */
    public float f850v0;

    /* JADX INFO: renamed from: w0, reason: collision with root package name */
    public boolean f851w0;

    /* JADX INFO: renamed from: x0, reason: collision with root package name */
    public boolean f852x0;

    /* JADX INFO: renamed from: y0, reason: collision with root package name */
    public int f853y0;

    /* JADX INFO: renamed from: z0, reason: collision with root package name */
    public int f854z0;

    public enum TransitionState {
        UNDEFINED,
        SETUP,
        MOVING,
        FINISHED
    }

    public class a implements Runnable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ View f855d;

        public a(View view) {
            this.f855d = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f855d.setNestedScrollingEnabled(true);
        }
    }

    public static /* synthetic */ class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f857a;

        static {
            int[] iArr = new int[TransitionState.values().length];
            f857a = iArr;
            try {
                iArr[TransitionState.UNDEFINED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f857a[TransitionState.SETUP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f857a[TransitionState.MOVING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f857a[TransitionState.FINISHED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public class c extends o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float f858a = 0.0f;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f859b = 0.0f;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f860c;

        public c() {
        }

        @Override // x.o
        public float a() {
            return MotionLayout.this.D;
        }

        public void b(float f8, float f9, float f10) {
            this.f858a = f8;
            this.f859b = f9;
            this.f860c = f10;
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f8) {
            float f9;
            float f10;
            float f11 = this.f858a;
            if (f11 > 0.0f) {
                float f12 = this.f860c;
                if (f11 / f12 < f8) {
                    f8 = f11 / f12;
                }
                MotionLayout.this.D = f11 - (f12 * f8);
                f9 = (f11 * f8) - (((f12 * f8) * f8) / 2.0f);
                f10 = this.f859b;
            } else {
                float f13 = this.f860c;
                if ((-f11) / f13 < f8) {
                    f8 = (-f11) / f13;
                }
                MotionLayout.this.D = (f13 * f8) + f11;
                f9 = (f11 * f8) + (((f13 * f8) * f8) / 2.0f);
                f10 = this.f859b;
            }
            return f9 + f10;
        }
    }

    public class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float[] f862a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int[] f863b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float[] f864c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Path f865d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Paint f866e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public Paint f867f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public Paint f868g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public Paint f869h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public Paint f870i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public float[] f871j;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public DashPathEffect f877p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public int f878q;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        public int f881t;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public final int f872k = -21965;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public final int f873l = -2067046;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public final int f874m = -13391360;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public final int f875n = 1996488704;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public final int f876o = 10;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public Rect f879r = new Rect();

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public boolean f880s = false;

        public d() {
            this.f881t = 1;
            Paint paint = new Paint();
            this.f866e = paint;
            paint.setAntiAlias(true);
            this.f866e.setColor(-21965);
            this.f866e.setStrokeWidth(2.0f);
            this.f866e.setStyle(Paint.Style.STROKE);
            Paint paint2 = new Paint();
            this.f867f = paint2;
            paint2.setAntiAlias(true);
            this.f867f.setColor(-2067046);
            this.f867f.setStrokeWidth(2.0f);
            this.f867f.setStyle(Paint.Style.STROKE);
            Paint paint3 = new Paint();
            this.f868g = paint3;
            paint3.setAntiAlias(true);
            this.f868g.setColor(-13391360);
            this.f868g.setStrokeWidth(2.0f);
            this.f868g.setStyle(Paint.Style.STROKE);
            Paint paint4 = new Paint();
            this.f869h = paint4;
            paint4.setAntiAlias(true);
            this.f869h.setColor(-13391360);
            this.f869h.setTextSize(MotionLayout.this.getContext().getResources().getDisplayMetrics().density * 12.0f);
            this.f871j = new float[8];
            Paint paint5 = new Paint();
            this.f870i = paint5;
            paint5.setAntiAlias(true);
            DashPathEffect dashPathEffect = new DashPathEffect(new float[]{4.0f, 8.0f}, 0.0f);
            this.f877p = dashPathEffect;
            this.f868g.setPathEffect(dashPathEffect);
            this.f864c = new float[100];
            this.f863b = new int[50];
            if (this.f880s) {
                this.f866e.setStrokeWidth(8.0f);
                this.f870i.setStrokeWidth(8.0f);
                this.f867f.setStrokeWidth(8.0f);
                this.f881t = 4;
            }
        }

        public void a(Canvas canvas, HashMap<View, n> map, int i8, int i9) {
            if (map == null || map.size() == 0) {
                return;
            }
            canvas.save();
            if (!MotionLayout.this.isInEditMode() && (i9 & 1) == 2) {
                String str = MotionLayout.this.getContext().getResources().getResourceName(MotionLayout.this.G) + ":" + MotionLayout.this.getProgress();
                canvas.drawText(str, 10.0f, MotionLayout.this.getHeight() - 30, this.f869h);
                canvas.drawText(str, 11.0f, MotionLayout.this.getHeight() - 29, this.f866e);
            }
            for (n nVar : map.values()) {
                int iH = nVar.h();
                if (i9 > 0 && iH == 0) {
                    iH = 1;
                }
                if (iH != 0) {
                    this.f878q = nVar.c(this.f864c, this.f863b);
                    if (iH >= 1) {
                        int i10 = i8 / 16;
                        float[] fArr = this.f862a;
                        if (fArr == null || fArr.length != i10 * 2) {
                            this.f862a = new float[i10 * 2];
                            this.f865d = new Path();
                        }
                        int i11 = this.f881t;
                        canvas.translate(i11, i11);
                        this.f866e.setColor(1996488704);
                        this.f870i.setColor(1996488704);
                        this.f867f.setColor(1996488704);
                        this.f868g.setColor(1996488704);
                        nVar.d(this.f862a, i10);
                        b(canvas, iH, this.f878q, nVar);
                        this.f866e.setColor(-21965);
                        this.f867f.setColor(-2067046);
                        this.f870i.setColor(-2067046);
                        this.f868g.setColor(-13391360);
                        int i12 = this.f881t;
                        canvas.translate(-i12, -i12);
                        b(canvas, iH, this.f878q, nVar);
                        if (iH == 5) {
                            j(canvas, nVar);
                        }
                    }
                }
            }
            canvas.restore();
        }

        public void b(Canvas canvas, int i8, int i9, n nVar) {
            if (i8 == 4) {
                d(canvas);
            }
            if (i8 == 2) {
                g(canvas);
            }
            if (i8 == 3) {
                e(canvas);
            }
            c(canvas);
            k(canvas, i8, i9, nVar);
        }

        public final void c(Canvas canvas) {
            canvas.drawLines(this.f862a, this.f866e);
        }

        public final void d(Canvas canvas) {
            boolean z7 = false;
            boolean z8 = false;
            for (int i8 = 0; i8 < this.f878q; i8++) {
                int[] iArr = this.f863b;
                if (iArr[i8] == 1) {
                    z7 = true;
                }
                if (iArr[i8] == 2) {
                    z8 = true;
                }
            }
            if (z7) {
                g(canvas);
            }
            if (z8) {
                e(canvas);
            }
        }

        public final void e(Canvas canvas) {
            float[] fArr = this.f862a;
            float f8 = fArr[0];
            float f9 = fArr[1];
            float f10 = fArr[fArr.length - 2];
            float f11 = fArr[fArr.length - 1];
            canvas.drawLine(Math.min(f8, f10), Math.max(f9, f11), Math.max(f8, f10), Math.max(f9, f11), this.f868g);
            canvas.drawLine(Math.min(f8, f10), Math.min(f9, f11), Math.min(f8, f10), Math.max(f9, f11), this.f868g);
        }

        public final void f(Canvas canvas, float f8, float f9) {
            float[] fArr = this.f862a;
            float f10 = fArr[0];
            float f11 = fArr[1];
            float f12 = fArr[fArr.length - 2];
            float f13 = fArr[fArr.length - 1];
            float fMin = Math.min(f10, f12);
            float fMax = Math.max(f11, f13);
            float fMin2 = f8 - Math.min(f10, f12);
            float fMax2 = Math.max(f11, f13) - f9;
            String str = "" + (((int) (((double) ((fMin2 * 100.0f) / Math.abs(f12 - f10))) + 0.5d)) / 100.0f);
            l(str, this.f869h);
            canvas.drawText(str, ((fMin2 / 2.0f) - (this.f879r.width() / 2)) + fMin, f9 - 20.0f, this.f869h);
            canvas.drawLine(f8, f9, Math.min(f10, f12), f9, this.f868g);
            String str2 = "" + (((int) (((double) ((fMax2 * 100.0f) / Math.abs(f13 - f11))) + 0.5d)) / 100.0f);
            l(str2, this.f869h);
            canvas.drawText(str2, f8 + 5.0f, fMax - ((fMax2 / 2.0f) - (this.f879r.height() / 2)), this.f869h);
            canvas.drawLine(f8, f9, f8, Math.max(f11, f13), this.f868g);
        }

        public final void g(Canvas canvas) {
            float[] fArr = this.f862a;
            canvas.drawLine(fArr[0], fArr[1], fArr[fArr.length - 2], fArr[fArr.length - 1], this.f868g);
        }

        public final void h(Canvas canvas, float f8, float f9) {
            float[] fArr = this.f862a;
            float f10 = fArr[0];
            float f11 = fArr[1];
            float f12 = fArr[fArr.length - 2];
            float f13 = fArr[fArr.length - 1];
            float fHypot = (float) Math.hypot(f10 - f12, f11 - f13);
            float f14 = f12 - f10;
            float f15 = f13 - f11;
            float f16 = (((f8 - f10) * f14) + ((f9 - f11) * f15)) / (fHypot * fHypot);
            float f17 = f10 + (f14 * f16);
            float f18 = f11 + (f16 * f15);
            Path path = new Path();
            path.moveTo(f8, f9);
            path.lineTo(f17, f18);
            float fHypot2 = (float) Math.hypot(f17 - f8, f18 - f9);
            String str = "" + (((int) ((fHypot2 * 100.0f) / fHypot)) / 100.0f);
            l(str, this.f869h);
            canvas.drawTextOnPath(str, path, (fHypot2 / 2.0f) - (this.f879r.width() / 2), -20.0f, this.f869h);
            canvas.drawLine(f8, f9, f17, f18, this.f868g);
        }

        public final void i(Canvas canvas, float f8, float f9, int i8, int i9) {
            String str = "" + (((int) (((double) (((f8 - (i8 / 2)) * 100.0f) / (MotionLayout.this.getWidth() - i8))) + 0.5d)) / 100.0f);
            l(str, this.f869h);
            canvas.drawText(str, ((f8 / 2.0f) - (this.f879r.width() / 2)) + 0.0f, f9 - 20.0f, this.f869h);
            canvas.drawLine(f8, f9, Math.min(0.0f, 1.0f), f9, this.f868g);
            String str2 = "" + (((int) (((double) (((f9 - (i9 / 2)) * 100.0f) / (MotionLayout.this.getHeight() - i9))) + 0.5d)) / 100.0f);
            l(str2, this.f869h);
            canvas.drawText(str2, f8 + 5.0f, 0.0f - ((f9 / 2.0f) - (this.f879r.height() / 2)), this.f869h);
            canvas.drawLine(f8, f9, f8, Math.max(0.0f, 1.0f), this.f868g);
        }

        public final void j(Canvas canvas, n nVar) {
            this.f865d.reset();
            for (int i8 = 0; i8 <= 50; i8++) {
                nVar.e(i8 / 50, this.f871j, 0);
                Path path = this.f865d;
                float[] fArr = this.f871j;
                path.moveTo(fArr[0], fArr[1]);
                Path path2 = this.f865d;
                float[] fArr2 = this.f871j;
                path2.lineTo(fArr2[2], fArr2[3]);
                Path path3 = this.f865d;
                float[] fArr3 = this.f871j;
                path3.lineTo(fArr3[4], fArr3[5]);
                Path path4 = this.f865d;
                float[] fArr4 = this.f871j;
                path4.lineTo(fArr4[6], fArr4[7]);
                this.f865d.close();
            }
            this.f866e.setColor(1140850688);
            canvas.translate(2.0f, 2.0f);
            canvas.drawPath(this.f865d, this.f866e);
            canvas.translate(-2.0f, -2.0f);
            this.f866e.setColor(-65536);
            canvas.drawPath(this.f865d, this.f866e);
        }

        public final void k(Canvas canvas, int i8, int i9, n nVar) {
            int width;
            int height;
            int i10;
            float f8;
            float f9;
            View view = nVar.f8913a;
            if (view != null) {
                width = view.getWidth();
                height = nVar.f8913a.getHeight();
            } else {
                width = 0;
                height = 0;
            }
            for (int i11 = 1; i11 < i9 - 1; i11++) {
                if (i8 != 4 || this.f863b[i11 - 1] != 0) {
                    float[] fArr = this.f864c;
                    int i12 = i11 * 2;
                    float f10 = fArr[i12];
                    float f11 = fArr[i12 + 1];
                    this.f865d.reset();
                    this.f865d.moveTo(f10, f11 + 10.0f);
                    this.f865d.lineTo(f10 + 10.0f, f11);
                    this.f865d.lineTo(f10, f11 - 10.0f);
                    this.f865d.lineTo(f10 - 10.0f, f11);
                    this.f865d.close();
                    int i13 = i11 - 1;
                    nVar.k(i13);
                    if (i8 == 4) {
                        int[] iArr = this.f863b;
                        if (iArr[i13] == 1) {
                            h(canvas, f10 - 0.0f, f11 - 0.0f);
                        } else if (iArr[i13] == 2) {
                            f(canvas, f10 - 0.0f, f11 - 0.0f);
                        } else {
                            if (iArr[i13] == 3) {
                                i10 = 3;
                                f8 = f11;
                                f9 = f10;
                                i(canvas, f10 - 0.0f, f11 - 0.0f, width, height);
                            }
                            canvas.drawPath(this.f865d, this.f870i);
                        }
                        i10 = 3;
                        f8 = f11;
                        f9 = f10;
                        canvas.drawPath(this.f865d, this.f870i);
                    } else {
                        i10 = 3;
                        f8 = f11;
                        f9 = f10;
                    }
                    if (i8 == 2) {
                        h(canvas, f9 - 0.0f, f8 - 0.0f);
                    }
                    if (i8 == i10) {
                        f(canvas, f9 - 0.0f, f8 - 0.0f);
                    }
                    if (i8 == 6) {
                        i(canvas, f9 - 0.0f, f8 - 0.0f, width, height);
                    }
                    canvas.drawPath(this.f865d, this.f870i);
                }
            }
            float[] fArr2 = this.f862a;
            if (fArr2.length > 1) {
                canvas.drawCircle(fArr2[0], fArr2[1], 8.0f, this.f867f);
                float[] fArr3 = this.f862a;
                canvas.drawCircle(fArr3[fArr3.length - 2], fArr3[fArr3.length - 1], 8.0f, this.f867f);
            }
        }

        public void l(String str, Paint paint) {
            paint.getTextBounds(str, 0, str.length(), this.f879r);
        }
    }

    public class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public z.d f883a = new z.d();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public z.d f884b = new z.d();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public b0.b f885c = null;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public b0.b f886d = null;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f887e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f888f;

        public e() {
        }

        public void a() {
            int childCount = MotionLayout.this.getChildCount();
            MotionLayout.this.K.clear();
            for (int i8 = 0; i8 < childCount; i8++) {
                View childAt = MotionLayout.this.getChildAt(i8);
                MotionLayout.this.K.put(childAt, new n(childAt));
            }
            for (int i9 = 0; i9 < childCount; i9++) {
                View childAt2 = MotionLayout.this.getChildAt(i9);
                n nVar = MotionLayout.this.K.get(childAt2);
                if (nVar != null) {
                    if (this.f885c != null) {
                        ConstraintWidget constraintWidgetC = c(this.f883a, childAt2);
                        if (constraintWidgetC != null) {
                            nVar.t(constraintWidgetC, this.f885c);
                        } else if (MotionLayout.this.X != 0) {
                            Log.e("MotionLayout", x.a.a() + "no widget for  " + x.a.c(childAt2) + " (" + childAt2.getClass().getName() + ")");
                        }
                    }
                    if (this.f886d != null) {
                        ConstraintWidget constraintWidgetC2 = c(this.f884b, childAt2);
                        if (constraintWidgetC2 != null) {
                            nVar.q(constraintWidgetC2, this.f886d);
                        } else if (MotionLayout.this.X != 0) {
                            Log.e("MotionLayout", x.a.a() + "no widget for  " + x.a.c(childAt2) + " (" + childAt2.getClass().getName() + ")");
                        }
                    }
                }
            }
        }

        public void b(z.d dVar, z.d dVar2) {
            ArrayList<ConstraintWidget> arrayListL0 = dVar.L0();
            HashMap<ConstraintWidget, ConstraintWidget> map = new HashMap<>();
            map.put(dVar, dVar2);
            dVar2.L0().clear();
            dVar2.l(dVar, map);
            for (ConstraintWidget constraintWidget : arrayListL0) {
                ConstraintWidget aVar = constraintWidget instanceof z.a ? new z.a() : constraintWidget instanceof z.f ? new z.f() : constraintWidget instanceof z.e ? new z.e() : constraintWidget instanceof z.g ? new z.h() : new ConstraintWidget();
                dVar2.a(aVar);
                map.put(constraintWidget, aVar);
            }
            for (ConstraintWidget constraintWidget2 : arrayListL0) {
                map.get(constraintWidget2).l(constraintWidget2, map);
            }
        }

        public ConstraintWidget c(z.d dVar, View view) {
            if (dVar.r() == view) {
                return dVar;
            }
            ArrayList<ConstraintWidget> arrayListL0 = dVar.L0();
            int size = arrayListL0.size();
            for (int i8 = 0; i8 < size; i8++) {
                ConstraintWidget constraintWidget = arrayListL0.get(i8);
                if (constraintWidget.r() == view) {
                    return constraintWidget;
                }
            }
            return null;
        }

        public void d(z.d dVar, b0.b bVar, b0.b bVar2) {
            this.f885c = bVar;
            this.f886d = bVar2;
            this.f883a = new z.d();
            this.f884b = new z.d();
            this.f883a.h1(MotionLayout.this.f1071f.W0());
            this.f884b.h1(MotionLayout.this.f1071f.W0());
            this.f883a.O0();
            this.f884b.O0();
            b(MotionLayout.this.f1071f, this.f883a);
            b(MotionLayout.this.f1071f, this.f884b);
            if (MotionLayout.this.O > 0.5d) {
                if (bVar != null) {
                    i(this.f883a, bVar);
                }
                i(this.f884b, bVar2);
            } else {
                i(this.f884b, bVar2);
                if (bVar != null) {
                    i(this.f883a, bVar);
                }
            }
            this.f883a.j1(MotionLayout.this.p());
            this.f883a.l1();
            this.f884b.j1(MotionLayout.this.p());
            this.f884b.l1();
            ViewGroup.LayoutParams layoutParams = MotionLayout.this.getLayoutParams();
            if (layoutParams != null) {
                if (layoutParams.width == -2) {
                    z.d dVar2 = this.f883a;
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    dVar2.m0(dimensionBehaviour);
                    this.f884b.m0(dimensionBehaviour);
                }
                if (layoutParams.height == -2) {
                    z.d dVar3 = this.f883a;
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    dVar3.B0(dimensionBehaviour2);
                    this.f884b.B0(dimensionBehaviour2);
                }
            }
        }

        public boolean e(int i8, int i9) {
            return (i8 == this.f887e && i9 == this.f888f) ? false : true;
        }

        public void f(int i8, int i9) {
            int mode = View.MeasureSpec.getMode(i8);
            int mode2 = View.MeasureSpec.getMode(i9);
            MotionLayout motionLayout = MotionLayout.this;
            motionLayout.C0 = mode;
            motionLayout.D0 = mode2;
            int optimizationLevel = motionLayout.getOptimizationLevel();
            MotionLayout motionLayout2 = MotionLayout.this;
            if (motionLayout2.F == motionLayout2.getStartState()) {
                MotionLayout.this.t(this.f884b, optimizationLevel, i8, i9);
                if (this.f885c != null) {
                    MotionLayout.this.t(this.f883a, optimizationLevel, i8, i9);
                }
            } else {
                if (this.f885c != null) {
                    MotionLayout.this.t(this.f883a, optimizationLevel, i8, i9);
                }
                MotionLayout.this.t(this.f884b, optimizationLevel, i8, i9);
            }
            if (((MotionLayout.this.getParent() instanceof MotionLayout) && mode == 1073741824 && mode2 == 1073741824) ? false : true) {
                MotionLayout motionLayout3 = MotionLayout.this;
                motionLayout3.C0 = mode;
                motionLayout3.D0 = mode2;
                if (motionLayout3.F == motionLayout3.getStartState()) {
                    MotionLayout.this.t(this.f884b, optimizationLevel, i8, i9);
                    if (this.f885c != null) {
                        MotionLayout.this.t(this.f883a, optimizationLevel, i8, i9);
                    }
                } else {
                    if (this.f885c != null) {
                        MotionLayout.this.t(this.f883a, optimizationLevel, i8, i9);
                    }
                    MotionLayout.this.t(this.f884b, optimizationLevel, i8, i9);
                }
                MotionLayout.this.f853y0 = this.f883a.Q();
                MotionLayout.this.f854z0 = this.f883a.w();
                MotionLayout.this.A0 = this.f884b.Q();
                MotionLayout.this.B0 = this.f884b.w();
                MotionLayout motionLayout4 = MotionLayout.this;
                motionLayout4.f852x0 = (motionLayout4.f853y0 == motionLayout4.A0 && motionLayout4.f854z0 == motionLayout4.B0) ? false : true;
            }
            MotionLayout motionLayout5 = MotionLayout.this;
            int i10 = motionLayout5.f853y0;
            int i11 = motionLayout5.f854z0;
            int i12 = motionLayout5.C0;
            if (i12 == Integer.MIN_VALUE || i12 == 0) {
                i10 = (int) (i10 + (motionLayout5.E0 * (motionLayout5.A0 - i10)));
            }
            int i13 = motionLayout5.D0;
            if (i13 == Integer.MIN_VALUE || i13 == 0) {
                i11 = (int) (i11 + (motionLayout5.E0 * (motionLayout5.B0 - i11)));
            }
            MotionLayout.this.s(i8, i9, i10, i11, this.f883a.d1() || this.f884b.d1(), this.f883a.b1() || this.f884b.b1());
        }

        public void g() {
            f(MotionLayout.this.H, MotionLayout.this.I);
            MotionLayout.this.q0();
        }

        public void h(int i8, int i9) {
            this.f887e = i8;
            this.f888f = i9;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final void i(z.d dVar, b0.b bVar) {
            SparseArray<ConstraintWidget> sparseArray = new SparseArray<>();
            Constraints.a aVar = new Constraints.a(-2, -2);
            sparseArray.clear();
            sparseArray.put(0, dVar);
            sparseArray.put(MotionLayout.this.getId(), dVar);
            for (ConstraintWidget constraintWidget : dVar.L0()) {
                sparseArray.put(((View) constraintWidget.r()).getId(), constraintWidget);
            }
            for (ConstraintWidget constraintWidget2 : dVar.L0()) {
                View view = (View) constraintWidget2.r();
                bVar.g(view.getId(), aVar);
                constraintWidget2.F0(bVar.t(view.getId()));
                constraintWidget2.i0(bVar.o(view.getId()));
                if (view instanceof ConstraintHelper) {
                    bVar.e((ConstraintHelper) view, constraintWidget2, aVar, sparseArray);
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
                if (bVar.s(view.getId()) == 1) {
                    constraintWidget2.E0(view.getVisibility());
                } else {
                    constraintWidget2.E0(bVar.r(view.getId()));
                }
            }
            for (ConstraintWidget constraintWidget3 : dVar.L0()) {
                if (constraintWidget3 instanceof j) {
                    ConstraintHelper constraintHelper = (ConstraintHelper) constraintWidget3.r();
                    z.g gVar = (z.g) constraintWidget3;
                    constraintHelper.s(dVar, gVar, sparseArray);
                    ((j) gVar).M0();
                }
            }
        }
    }

    public interface f {
        void a(MotionEvent motionEvent);

        float b();

        float c();

        void d();

        void e(int i8);
    }

    public static class g implements f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static g f890a = new g();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public VelocityTracker f891b;

        public static g f() {
            f890a.f891b = VelocityTracker.obtain();
            return f890a;
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.f
        public void a(MotionEvent motionEvent) {
            VelocityTracker velocityTracker = this.f891b;
            if (velocityTracker != null) {
                velocityTracker.addMovement(motionEvent);
            }
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.f
        public float b() {
            return this.f891b.getYVelocity();
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.f
        public float c() {
            return this.f891b.getXVelocity();
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.f
        public void d() {
            this.f891b.recycle();
            this.f891b = null;
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.f
        public void e(int i8) {
            this.f891b.computeCurrentVelocity(i8);
        }
    }

    public class h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float f892a = Float.NaN;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f893b = Float.NaN;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f894c = -1;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f895d = -1;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final String f896e = "motion.progress";

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final String f897f = "motion.velocity";

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final String f898g = "motion.StartState";

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final String f899h = "motion.EndState";

        public h() {
        }

        public void a() {
            int i8 = this.f894c;
            if (i8 != -1 || this.f895d != -1) {
                if (i8 == -1) {
                    MotionLayout.this.u0(this.f895d);
                } else {
                    int i9 = this.f895d;
                    if (i9 == -1) {
                        MotionLayout.this.o0(i8, -1, -1);
                    } else {
                        MotionLayout.this.p0(i8, i9);
                    }
                }
                MotionLayout.this.setState(TransitionState.SETUP);
            }
            if (Float.isNaN(this.f893b)) {
                if (Float.isNaN(this.f892a)) {
                    return;
                }
                MotionLayout.this.setProgress(this.f892a);
            } else {
                MotionLayout.this.n0(this.f892a, this.f893b);
                this.f892a = Float.NaN;
                this.f893b = Float.NaN;
                this.f894c = -1;
                this.f895d = -1;
            }
        }

        public Bundle b() {
            Bundle bundle = new Bundle();
            bundle.putFloat("motion.progress", this.f892a);
            bundle.putFloat("motion.velocity", this.f893b);
            bundle.putInt("motion.StartState", this.f894c);
            bundle.putInt("motion.EndState", this.f895d);
            return bundle;
        }

        public void c() {
            this.f895d = MotionLayout.this.G;
            this.f894c = MotionLayout.this.E;
            this.f893b = MotionLayout.this.getVelocity();
            this.f892a = MotionLayout.this.getProgress();
        }

        public void d(int i8) {
            this.f895d = i8;
        }

        public void e(float f8) {
            this.f892a = f8;
        }

        public void f(int i8) {
            this.f894c = i8;
        }

        public void g(Bundle bundle) {
            this.f892a = bundle.getFloat("motion.progress");
            this.f893b = bundle.getFloat("motion.velocity");
            this.f894c = bundle.getInt("motion.StartState");
            this.f895d = bundle.getInt("motion.EndState");
        }

        public void h(float f8) {
            this.f893b = f8;
        }
    }

    public interface i {
        void a(MotionLayout motionLayout, int i8, int i9, float f8);

        void b(MotionLayout motionLayout, int i8, int i9);

        void c(MotionLayout motionLayout, int i8, boolean z7, float f8);

        void d(MotionLayout motionLayout, int i8);
    }

    public MotionLayout(Context context) {
        super(context);
        this.D = 0.0f;
        this.E = -1;
        this.F = -1;
        this.G = -1;
        this.H = 0;
        this.I = 0;
        this.J = true;
        this.K = new HashMap<>();
        this.L = 0L;
        this.M = 1.0f;
        this.N = 0.0f;
        this.O = 0.0f;
        this.Q = 0.0f;
        this.S = false;
        this.T = false;
        this.X = 0;
        this.Z = false;
        this.f829a0 = new w.g();
        this.f830b0 = new c();
        this.f832d0 = true;
        this.f837i0 = false;
        this.f842n0 = false;
        this.f843o0 = null;
        this.f844p0 = null;
        this.f845q0 = null;
        this.f846r0 = 0;
        this.f847s0 = -1L;
        this.f848t0 = 0.0f;
        this.f849u0 = 0;
        this.f850v0 = 0.0f;
        this.f851w0 = false;
        this.f852x0 = false;
        this.F0 = new x.e();
        this.G0 = false;
        this.I0 = TransitionState.UNDEFINED;
        this.J0 = new e();
        this.K0 = false;
        this.L0 = new RectF();
        this.M0 = null;
        this.N0 = new ArrayList<>();
        h0(null);
    }

    public static boolean w0(float f8, float f9, float f10) {
        if (f8 > 0.0f) {
            float f11 = f8 / f10;
            return f9 + ((f8 * f11) - (((f10 * f11) * f11) / 2.0f)) > 1.0f;
        }
        float f12 = (-f8) / f10;
        return f9 + ((f8 * f12) + (((f10 * f12) * f12) / 2.0f)) < 0.0f;
    }

    public void T(float f8) {
        if (this.B == null) {
            return;
        }
        float f9 = this.O;
        float f10 = this.N;
        if (f9 != f10 && this.R) {
            this.O = f10;
        }
        float f11 = this.O;
        if (f11 == f8) {
            return;
        }
        this.Z = false;
        this.Q = f8;
        this.M = r0.m() / 1000.0f;
        setProgress(this.Q);
        this.C = this.B.p();
        this.R = false;
        this.L = getNanoTime();
        this.S = true;
        this.N = f11;
        this.O = f11;
        invalidate();
    }

    public final void U() {
        q qVar = this.B;
        if (qVar == null) {
            Log.e("MotionLayout", "CHECK: motion scene not set! set \"app:layoutDescription=\"@xml/file\"");
            return;
        }
        int iX = qVar.x();
        q qVar2 = this.B;
        V(iX, qVar2.i(qVar2.x()));
        SparseIntArray sparseIntArray = new SparseIntArray();
        SparseIntArray sparseIntArray2 = new SparseIntArray();
        for (q.b bVar : this.B.l()) {
            if (bVar == this.B.f8957c) {
                Log.v("MotionLayout", "CHECK: CURRENT");
            }
            W(bVar);
            int iA = bVar.A();
            int iY = bVar.y();
            String strB = x.a.b(getContext(), iA);
            String strB2 = x.a.b(getContext(), iY);
            if (sparseIntArray.get(iA) == iY) {
                Log.e("MotionLayout", "CHECK: two transitions with the same start and end " + strB + "->" + strB2);
            }
            if (sparseIntArray2.get(iY) == iA) {
                Log.e("MotionLayout", "CHECK: you can't have reverse transitions" + strB + "->" + strB2);
            }
            sparseIntArray.put(iA, iY);
            sparseIntArray2.put(iY, iA);
            if (this.B.i(iA) == null) {
                Log.e("MotionLayout", " no such constraintSetStart " + strB);
            }
            if (this.B.i(iY) == null) {
                Log.e("MotionLayout", " no such constraintSetEnd " + strB);
            }
        }
    }

    public final void V(int i8, b0.b bVar) {
        String strB = x.a.b(getContext(), i8);
        int childCount = getChildCount();
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            int id = childAt.getId();
            if (id == -1) {
                Log.w("MotionLayout", "CHECK: " + strB + " ALL VIEWS SHOULD HAVE ID's " + childAt.getClass().getName() + " does not!");
            }
            if (bVar.n(id) == null) {
                Log.w("MotionLayout", "CHECK: " + strB + " NO CONSTRAINTS for " + x.a.c(childAt));
            }
        }
        int[] iArrP = bVar.p();
        for (int i10 = 0; i10 < iArrP.length; i10++) {
            int i11 = iArrP[i10];
            String strB2 = x.a.b(getContext(), i11);
            if (findViewById(iArrP[i10]) == null) {
                Log.w("MotionLayout", "CHECK: " + strB + " NO View matches id " + strB2);
            }
            if (bVar.o(i11) == -1) {
                Log.w("MotionLayout", "CHECK: " + strB + "(" + strB2 + ") no LAYOUT_HEIGHT");
            }
            if (bVar.t(i11) == -1) {
                Log.w("MotionLayout", "CHECK: " + strB + "(" + strB2 + ") no LAYOUT_HEIGHT");
            }
        }
    }

    public final void W(q.b bVar) {
        Log.v("MotionLayout", "CHECK: transition = " + bVar.u(getContext()));
        Log.v("MotionLayout", "CHECK: transition.setDuration = " + bVar.x());
        if (bVar.A() == bVar.y()) {
            Log.e("MotionLayout", "CHECK: start and end constraint set should not be the same!");
        }
    }

    public final void X() {
        int childCount = getChildCount();
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = getChildAt(i8);
            n nVar = this.K.get(childAt);
            if (nVar != null) {
                nVar.s(childAt);
            }
        }
    }

    public void Y(boolean z7) {
        float f8;
        boolean z8;
        int i8;
        float interpolation;
        boolean z9;
        if (this.P == -1) {
            this.P = getNanoTime();
        }
        float f9 = this.O;
        if (f9 > 0.0f && f9 < 1.0f) {
            this.F = -1;
        }
        boolean z10 = false;
        if (this.f842n0 || (this.S && (z7 || this.Q != f9))) {
            float fSignum = Math.signum(this.Q - f9);
            long nanoTime = getNanoTime();
            Interpolator interpolator = this.C;
            if (interpolator instanceof o) {
                f8 = 0.0f;
            } else {
                f8 = (((nanoTime - this.P) * fSignum) * 1.0E-9f) / this.M;
                this.D = f8;
            }
            float f10 = this.O + f8;
            if (this.R) {
                f10 = this.Q;
            }
            if ((fSignum <= 0.0f || f10 < this.Q) && (fSignum > 0.0f || f10 > this.Q)) {
                z8 = false;
            } else {
                f10 = this.Q;
                this.S = false;
                z8 = true;
            }
            this.O = f10;
            this.N = f10;
            this.P = nanoTime;
            if (interpolator != null && !z8) {
                if (this.Z) {
                    interpolation = interpolator.getInterpolation((nanoTime - this.L) * 1.0E-9f);
                    this.O = interpolation;
                    this.P = nanoTime;
                    Interpolator interpolator2 = this.C;
                    if (interpolator2 instanceof o) {
                        float fA = ((o) interpolator2).a();
                        this.D = fA;
                        if (Math.abs(fA) * this.M <= 1.0E-5f) {
                            this.S = false;
                        }
                        if (fA > 0.0f && interpolation >= 1.0f) {
                            this.O = 1.0f;
                            this.S = false;
                            interpolation = 1.0f;
                        }
                        if (fA < 0.0f && interpolation <= 0.0f) {
                            this.O = 0.0f;
                            this.S = false;
                            f10 = 0.0f;
                        }
                    }
                } else {
                    interpolation = interpolator.getInterpolation(f10);
                    Interpolator interpolator3 = this.C;
                    if (interpolator3 instanceof o) {
                        this.D = ((o) interpolator3).a();
                    } else {
                        this.D = ((interpolator3.getInterpolation(f10 + f8) - interpolation) * fSignum) / f8;
                    }
                }
                f10 = interpolation;
            }
            if (Math.abs(this.D) > 1.0E-5f) {
                setState(TransitionState.MOVING);
            }
            if ((fSignum > 0.0f && f10 >= this.Q) || (fSignum <= 0.0f && f10 <= this.Q)) {
                f10 = this.Q;
                this.S = false;
            }
            if (f10 >= 1.0f || f10 <= 0.0f) {
                this.S = false;
                setState(TransitionState.FINISHED);
            }
            int childCount = getChildCount();
            this.f842n0 = false;
            long nanoTime2 = getNanoTime();
            this.E0 = f10;
            for (int i9 = 0; i9 < childCount; i9++) {
                View childAt = getChildAt(i9);
                n nVar = this.K.get(childAt);
                if (nVar != null) {
                    this.f842n0 = nVar.o(childAt, f10, nanoTime2, this.F0) | this.f842n0;
                }
            }
            boolean z11 = (fSignum > 0.0f && f10 >= this.Q) || (fSignum <= 0.0f && f10 <= this.Q);
            if (!this.f842n0 && !this.S && z11) {
                setState(TransitionState.FINISHED);
            }
            if (this.f852x0) {
                requestLayout();
            }
            this.f842n0 = (!z11) | this.f842n0;
            if (f10 <= 0.0f && (i8 = this.E) != -1 && this.F != i8) {
                this.F = i8;
                this.B.i(i8).c(this);
                setState(TransitionState.FINISHED);
                z10 = true;
            }
            if (f10 >= 1.0d) {
                int i10 = this.F;
                int i11 = this.G;
                if (i10 != i11) {
                    this.F = i11;
                    this.B.i(i11).c(this);
                    setState(TransitionState.FINISHED);
                    z10 = true;
                }
            }
            if (this.f842n0 || this.S) {
                invalidate();
            } else if ((fSignum > 0.0f && f10 == 1.0f) || (fSignum < 0.0f && f10 == 0.0f)) {
                setState(TransitionState.FINISHED);
            }
            if ((!this.f842n0 && this.S && fSignum > 0.0f && f10 == 1.0f) || (fSignum < 0.0f && f10 == 0.0f)) {
                k0();
            }
        }
        float f11 = this.O;
        if (f11 < 1.0f) {
            if (f11 <= 0.0f) {
                int i12 = this.F;
                int i13 = this.E;
                z9 = i12 == i13 ? z10 : true;
                this.F = i13;
            }
            this.K0 |= z10;
            if (z10 && !this.G0) {
                requestLayout();
            }
            this.N = this.O;
        }
        int i14 = this.F;
        int i15 = this.G;
        z9 = i14 == i15 ? z10 : true;
        this.F = i15;
        z10 = z9;
        this.K0 |= z10;
        if (z10) {
            requestLayout();
        }
        this.N = this.O;
    }

    public final void Z() {
        boolean z7;
        float fSignum = Math.signum(this.Q - this.O);
        long nanoTime = getNanoTime();
        Interpolator interpolator = this.C;
        float interpolation = this.O + (!(interpolator instanceof w.g) ? (((nanoTime - this.P) * fSignum) * 1.0E-9f) / this.M : 0.0f);
        if (this.R) {
            interpolation = this.Q;
        }
        if ((fSignum <= 0.0f || interpolation < this.Q) && (fSignum > 0.0f || interpolation > this.Q)) {
            z7 = false;
        } else {
            interpolation = this.Q;
            z7 = true;
        }
        if (interpolator != null && !z7) {
            interpolation = this.Z ? interpolator.getInterpolation((nanoTime - this.L) * 1.0E-9f) : interpolator.getInterpolation(interpolation);
        }
        if ((fSignum > 0.0f && interpolation >= this.Q) || (fSignum <= 0.0f && interpolation <= this.Q)) {
            interpolation = this.Q;
        }
        this.E0 = interpolation;
        int childCount = getChildCount();
        long nanoTime2 = getNanoTime();
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = getChildAt(i8);
            n nVar = this.K.get(childAt);
            if (nVar != null) {
                nVar.o(childAt, interpolation, nanoTime2, this.F0);
            }
        }
        if (this.f852x0) {
            requestLayout();
        }
    }

    public final void a0() {
        ArrayList<i> arrayList;
        if ((this.U == null && ((arrayList = this.f845q0) == null || arrayList.isEmpty())) || this.f850v0 == this.N) {
            return;
        }
        if (this.f849u0 != -1) {
            i iVar = this.U;
            if (iVar != null) {
                iVar.b(this, this.E, this.G);
            }
            ArrayList<i> arrayList2 = this.f845q0;
            if (arrayList2 != null) {
                Iterator<i> it = arrayList2.iterator();
                while (it.hasNext()) {
                    it.next().b(this, this.E, this.G);
                }
            }
            this.f851w0 = true;
        }
        this.f849u0 = -1;
        float f8 = this.N;
        this.f850v0 = f8;
        i iVar2 = this.U;
        if (iVar2 != null) {
            iVar2.a(this, this.E, this.G, f8);
        }
        ArrayList<i> arrayList3 = this.f845q0;
        if (arrayList3 != null) {
            Iterator<i> it2 = arrayList3.iterator();
            while (it2.hasNext()) {
                it2.next().a(this, this.E, this.G, this.N);
            }
        }
        this.f851w0 = true;
    }

    public void b0() {
        int iIntValue;
        ArrayList<i> arrayList;
        if ((this.U != null || ((arrayList = this.f845q0) != null && !arrayList.isEmpty())) && this.f849u0 == -1) {
            this.f849u0 = this.F;
            if (this.N0.isEmpty()) {
                iIntValue = -1;
            } else {
                iIntValue = this.N0.get(r0.size() - 1).intValue();
            }
            int i8 = this.F;
            if (iIntValue != i8 && i8 != -1) {
                this.N0.add(Integer.valueOf(i8));
            }
        }
        l0();
    }

    public void c0(int i8, boolean z7, float f8) {
        i iVar = this.U;
        if (iVar != null) {
            iVar.c(this, i8, z7, f8);
        }
        ArrayList<i> arrayList = this.f845q0;
        if (arrayList != null) {
            Iterator<i> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().c(this, i8, z7, f8);
            }
        }
    }

    public void d0(int i8, float f8, float f9, float f10, float[] fArr) {
        String resourceName;
        HashMap<View, n> map = this.K;
        View viewG = g(i8);
        n nVar = map.get(viewG);
        if (nVar != null) {
            nVar.g(f8, f9, f10, fArr);
            float y7 = viewG.getY();
            int i9 = ((f8 - this.V) > 0.0f ? 1 : ((f8 - this.V) == 0.0f ? 0 : -1));
            this.V = f8;
            this.W = y7;
            return;
        }
        if (viewG == null) {
            resourceName = "" + i8;
        } else {
            resourceName = viewG.getContext().getResources().getResourceName(i8);
        }
        Log.w("MotionLayout", "WARNING could not find view id " + resourceName);
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        Y(false);
        super.dispatchDraw(canvas);
        if (this.B == null) {
            return;
        }
        if ((this.X & 1) == 1 && !isInEditMode()) {
            this.f846r0++;
            long nanoTime = getNanoTime();
            long j8 = this.f847s0;
            if (j8 != -1) {
                if (nanoTime - j8 > 200000000) {
                    this.f848t0 = ((int) ((this.f846r0 / (r5 * 1.0E-9f)) * 100.0f)) / 100.0f;
                    this.f846r0 = 0;
                    this.f847s0 = nanoTime;
                }
            } else {
                this.f847s0 = nanoTime;
            }
            Paint paint = new Paint();
            paint.setTextSize(42.0f);
            String str = this.f848t0 + " fps " + x.a.d(this, this.E) + " -> ";
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(x.a.d(this, this.G));
            sb.append(" (progress: ");
            sb.append(((int) (getProgress() * 1000.0f)) / 10.0f);
            sb.append(" ) state=");
            int i8 = this.F;
            sb.append(i8 == -1 ? "undefined" : x.a.d(this, i8));
            String string = sb.toString();
            paint.setColor(-16777216);
            canvas.drawText(string, 11.0f, getHeight() - 29, paint);
            paint.setColor(-7864184);
            canvas.drawText(string, 10.0f, getHeight() - 30, paint);
        }
        if (this.X > 1) {
            if (this.Y == null) {
                this.Y = new d();
            }
            this.Y.a(canvas, this.K, this.B.m(), this.X);
        }
    }

    public q.b e0(int i8) {
        return this.B.y(i8);
    }

    public void f0(View view, float f8, float f9, float[] fArr, int i8) {
        float f10;
        float fA = this.D;
        float f11 = this.O;
        if (this.C != null) {
            float fSignum = Math.signum(this.Q - f11);
            float interpolation = this.C.getInterpolation(this.O + 1.0E-5f);
            float interpolation2 = this.C.getInterpolation(this.O);
            fA = (fSignum * ((interpolation - interpolation2) / 1.0E-5f)) / this.M;
            f10 = interpolation2;
        } else {
            f10 = f11;
        }
        Interpolator interpolator = this.C;
        if (interpolator instanceof o) {
            fA = ((o) interpolator).a();
        }
        n nVar = this.K.get(view);
        if ((i8 & 1) == 0) {
            nVar.l(f10, view.getWidth(), view.getHeight(), f8, f9, fArr);
        } else {
            nVar.g(f10, f8, f9, fArr);
        }
        if (i8 < 2) {
            fArr[0] = fArr[0] * fA;
            fArr[1] = fArr[1] * fA;
        }
    }

    public final boolean g0(float f8, float f9, View view, MotionEvent motionEvent) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i8 = 0; i8 < childCount; i8++) {
                if (g0(view.getLeft() + f8, view.getTop() + f9, viewGroup.getChildAt(i8), motionEvent)) {
                    return true;
                }
            }
        }
        this.L0.set(view.getLeft() + f8, view.getTop() + f9, f8 + view.getRight(), f9 + view.getBottom());
        if (motionEvent.getAction() == 0) {
            if (this.L0.contains(motionEvent.getX(), motionEvent.getY()) && view.onTouchEvent(motionEvent)) {
                return true;
            }
        } else if (view.onTouchEvent(motionEvent)) {
            return true;
        }
        return false;
    }

    public int[] getConstraintSetIds() {
        q qVar = this.B;
        if (qVar == null) {
            return null;
        }
        return qVar.k();
    }

    public int getCurrentState() {
        return this.F;
    }

    public ArrayList<q.b> getDefinedTransitions() {
        q qVar = this.B;
        if (qVar == null) {
            return null;
        }
        return qVar.l();
    }

    public x.b getDesignTool() {
        if (this.f831c0 == null) {
            this.f831c0 = new x.b(this);
        }
        return this.f831c0;
    }

    public int getEndState() {
        return this.G;
    }

    public long getNanoTime() {
        return System.nanoTime();
    }

    public float getProgress() {
        return this.O;
    }

    public int getStartState() {
        return this.E;
    }

    public float getTargetPosition() {
        return this.Q;
    }

    public Bundle getTransitionState() {
        if (this.H0 == null) {
            this.H0 = new h();
        }
        this.H0.c();
        return this.H0.b();
    }

    public long getTransitionTimeMs() {
        if (this.B != null) {
            this.M = r0.m() / 1000.0f;
        }
        return (long) (this.M * 1000.0f);
    }

    public float getVelocity() {
        return this.D;
    }

    @Override // o0.l
    public void h(View view, View view2, int i8, int i9) {
    }

    public final void h0(AttributeSet attributeSet) {
        q qVar;
        A = isInEditMode();
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.MotionLayout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            boolean z7 = true;
            for (int i8 = 0; i8 < indexCount; i8++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i8);
                if (index == R$styleable.MotionLayout_layoutDescription) {
                    this.B = new q(getContext(), this, typedArrayObtainStyledAttributes.getResourceId(index, -1));
                } else if (index == R$styleable.MotionLayout_currentState) {
                    this.F = typedArrayObtainStyledAttributes.getResourceId(index, -1);
                } else if (index == R$styleable.MotionLayout_motionProgress) {
                    this.Q = typedArrayObtainStyledAttributes.getFloat(index, 0.0f);
                    this.S = true;
                } else if (index == R$styleable.MotionLayout_applyMotionScene) {
                    z7 = typedArrayObtainStyledAttributes.getBoolean(index, z7);
                } else if (index == R$styleable.MotionLayout_showPaths) {
                    if (this.X == 0) {
                        this.X = typedArrayObtainStyledAttributes.getBoolean(index, false) ? 2 : 0;
                    }
                } else if (index == R$styleable.MotionLayout_motionDebug) {
                    this.X = typedArrayObtainStyledAttributes.getInt(index, 0);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
            if (this.B == null) {
                Log.e("MotionLayout", "WARNING NO app:layoutDescription tag");
            }
            if (!z7) {
                this.B = null;
            }
        }
        if (this.X != 0) {
            U();
        }
        if (this.F != -1 || (qVar = this.B) == null) {
            return;
        }
        this.F = qVar.x();
        this.E = this.B.x();
        this.G = this.B.n();
    }

    @Override // o0.l
    public void i(View view, int i8) {
        q qVar = this.B;
        if (qVar == null) {
            return;
        }
        float f8 = this.f838j0;
        float f9 = this.f841m0;
        qVar.G(f8 / f9, this.f839k0 / f9);
    }

    public boolean i0() {
        return this.J;
    }

    @Override // android.view.View
    public boolean isAttachedToWindow() {
        return Build.VERSION.SDK_INT >= 19 ? super.isAttachedToWindow() : getWindowToken() != null;
    }

    @Override // o0.l
    public void j(View view, int i8, int i9, int[] iArr, int i10) {
        q.b bVar;
        t tVarB;
        int iK;
        q qVar = this.B;
        if (qVar == null || (bVar = qVar.f8957c) == null || !bVar.C()) {
            return;
        }
        q.b bVar2 = this.B.f8957c;
        if (bVar2 == null || !bVar2.C() || (tVarB = bVar2.B()) == null || (iK = tVarB.k()) == -1 || view.getId() == iK) {
            q qVar2 = this.B;
            if (qVar2 != null && qVar2.t()) {
                float f8 = this.N;
                if ((f8 == 1.0f || f8 == 0.0f) && view.canScrollVertically(-1)) {
                    return;
                }
            }
            if (bVar2.B() != null && (this.B.f8957c.B().d() & 1) != 0) {
                float fU = this.B.u(i8, i9);
                float f9 = this.O;
                if ((f9 <= 0.0f && fU < 0.0f) || (f9 >= 1.0f && fU > 0.0f)) {
                    if (Build.VERSION.SDK_INT >= 21) {
                        view.setNestedScrollingEnabled(false);
                        view.post(new a(view));
                        return;
                    }
                    return;
                }
            }
            float f10 = this.N;
            long nanoTime = getNanoTime();
            float f11 = i8;
            this.f838j0 = f11;
            float f12 = i9;
            this.f839k0 = f12;
            this.f841m0 = (float) ((nanoTime - this.f840l0) * 1.0E-9d);
            this.f840l0 = nanoTime;
            this.B.F(f11, f12);
            if (f10 != this.N) {
                iArr[0] = i8;
                iArr[1] = i9;
            }
            Y(false);
            if (iArr[0] == 0 && iArr[1] == 0) {
                return;
            }
            this.f837i0 = true;
        }
    }

    public f j0() {
        return g.f();
    }

    public final void k0() {
        q qVar = this.B;
        if (qVar == null) {
            return;
        }
        if (qVar.f(this, this.F)) {
            requestLayout();
            return;
        }
        int i8 = this.F;
        if (i8 != -1) {
            this.B.e(this, i8);
        }
        if (this.B.Q()) {
            this.B.O();
        }
    }

    public final void l0() {
        ArrayList<i> arrayList;
        if (this.U == null && ((arrayList = this.f845q0) == null || arrayList.isEmpty())) {
            return;
        }
        this.f851w0 = false;
        for (Integer num : this.N0) {
            i iVar = this.U;
            if (iVar != null) {
                iVar.d(this, num.intValue());
            }
            ArrayList<i> arrayList2 = this.f845q0;
            if (arrayList2 != null) {
                Iterator<i> it = arrayList2.iterator();
                while (it.hasNext()) {
                    it.next().d(this, num.intValue());
                }
            }
        }
        this.N0.clear();
    }

    @Override // o0.m
    public void m(View view, int i8, int i9, int i10, int i11, int i12, int[] iArr) {
        if (this.f837i0 || i8 != 0 || i9 != 0) {
            iArr[0] = iArr[0] + i10;
            iArr[1] = iArr[1] + i11;
        }
        this.f837i0 = false;
    }

    public void m0() {
        this.J0.g();
        invalidate();
    }

    @Override // o0.l
    public void n(View view, int i8, int i9, int i10, int i11, int i12) {
    }

    public void n0(float f8, float f9) {
        if (isAttachedToWindow()) {
            setProgress(f8);
            setState(TransitionState.MOVING);
            this.D = f9;
            T(1.0f);
            return;
        }
        if (this.H0 == null) {
            this.H0 = new h();
        }
        this.H0.e(f8);
        this.H0.h(f9);
    }

    @Override // o0.l
    public boolean o(View view, View view2, int i8, int i9) {
        q.b bVar;
        q qVar = this.B;
        return (qVar == null || (bVar = qVar.f8957c) == null || bVar.B() == null || (this.B.f8957c.B().d() & 2) != 0) ? false : true;
    }

    public void o0(int i8, int i9, int i10) {
        setState(TransitionState.SETUP);
        this.F = i8;
        this.E = -1;
        this.G = -1;
        b0.a aVar = this.f1079n;
        if (aVar != null) {
            aVar.d(i8, i9, i10);
            return;
        }
        q qVar = this.B;
        if (qVar != null) {
            qVar.i(i8).d(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        int i8;
        super.onAttachedToWindow();
        q qVar = this.B;
        if (qVar != null && (i8 = this.F) != -1) {
            b0.b bVarI = qVar.i(i8);
            this.B.J(this);
            if (bVarI != null) {
                bVarI.d(this);
            }
            this.E = this.F;
        }
        k0();
        h hVar = this.H0;
        if (hVar != null) {
            hVar.a();
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        q.b bVar;
        t tVarB;
        int iK;
        RectF rectFJ;
        q qVar = this.B;
        if (qVar != null && this.J && (bVar = qVar.f8957c) != null && bVar.C() && (tVarB = bVar.B()) != null && ((motionEvent.getAction() != 0 || (rectFJ = tVarB.j(this, new RectF())) == null || rectFJ.contains(motionEvent.getX(), motionEvent.getY())) && (iK = tVarB.k()) != -1)) {
            View view = this.M0;
            if (view == null || view.getId() != iK) {
                this.M0 = findViewById(iK);
            }
            if (this.M0 != null) {
                this.L0.set(r0.getLeft(), this.M0.getTop(), this.M0.getRight(), this.M0.getBottom());
                if (this.L0.contains(motionEvent.getX(), motionEvent.getY()) && !g0(0.0f, 0.0f, this.M0, motionEvent)) {
                    return onTouchEvent(motionEvent);
                }
            }
        }
        return false;
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
        this.G0 = true;
        try {
            if (this.B == null) {
                super.onLayout(z7, i8, i9, i10, i11);
                return;
            }
            int i12 = i10 - i8;
            int i13 = i11 - i9;
            if (this.f835g0 != i12 || this.f836h0 != i13) {
                m0();
                Y(true);
            }
            this.f835g0 = i12;
            this.f836h0 = i13;
            this.f833e0 = i12;
            this.f834f0 = i13;
        } finally {
            this.G0 = false;
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View
    public void onMeasure(int i8, int i9) {
        if (this.B == null) {
            super.onMeasure(i8, i9);
            return;
        }
        boolean z7 = false;
        boolean z8 = (this.H == i8 && this.I == i9) ? false : true;
        if (this.K0) {
            this.K0 = false;
            k0();
            l0();
            z8 = true;
        }
        if (this.f1076k) {
            z8 = true;
        }
        this.H = i8;
        this.I = i9;
        int iX = this.B.x();
        int iN = this.B.n();
        if ((z8 || this.J0.e(iX, iN)) && this.E != -1) {
            super.onMeasure(i8, i9);
            this.J0.d(this.f1071f, this.B.i(iX), this.B.i(iN));
            this.J0.g();
            this.J0.h(iX, iN);
        } else {
            z7 = true;
        }
        if (this.f852x0 || z7) {
            int paddingTop = getPaddingTop() + getPaddingBottom();
            int iQ = this.f1071f.Q() + getPaddingLeft() + getPaddingRight();
            int iW = this.f1071f.w() + paddingTop;
            int i10 = this.C0;
            if (i10 == Integer.MIN_VALUE || i10 == 0) {
                iQ = (int) (this.f853y0 + (this.E0 * (this.A0 - r7)));
                requestLayout();
            }
            int i11 = this.D0;
            if (i11 == Integer.MIN_VALUE || i11 == 0) {
                iW = (int) (this.f854z0 + (this.E0 * (this.B0 - r7)));
                requestLayout();
            }
            setMeasuredDimension(iQ, iW);
        }
        Z();
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, o0.n
    public boolean onNestedFling(View view, float f8, float f9, boolean z7) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, o0.n
    public boolean onNestedPreFling(View view, float f8, float f9) {
        return false;
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i8) {
        q qVar = this.B;
        if (qVar != null) {
            qVar.L(p());
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        q qVar = this.B;
        if (qVar == null || !this.J || !qVar.Q()) {
            return super.onTouchEvent(motionEvent);
        }
        q.b bVar = this.B.f8957c;
        if (bVar != null && !bVar.C()) {
            return super.onTouchEvent(motionEvent);
        }
        this.B.H(motionEvent, getCurrentState(), this);
        return true;
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public void onViewAdded(View view) {
        super.onViewAdded(view);
        if (view instanceof MotionHelper) {
            MotionHelper motionHelper = (MotionHelper) view;
            if (this.f845q0 == null) {
                this.f845q0 = new ArrayList<>();
            }
            this.f845q0.add(motionHelper);
            if (motionHelper.w()) {
                if (this.f843o0 == null) {
                    this.f843o0 = new ArrayList<>();
                }
                this.f843o0.add(motionHelper);
            }
            if (motionHelper.v()) {
                if (this.f844p0 == null) {
                    this.f844p0 = new ArrayList<>();
                }
                this.f844p0.add(motionHelper);
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        ArrayList<MotionHelper> arrayList = this.f843o0;
        if (arrayList != null) {
            arrayList.remove(view);
        }
        ArrayList<MotionHelper> arrayList2 = this.f844p0;
        if (arrayList2 != null) {
            arrayList2.remove(view);
        }
    }

    public void p0(int i8, int i9) {
        if (!isAttachedToWindow()) {
            if (this.H0 == null) {
                this.H0 = new h();
            }
            this.H0.f(i8);
            this.H0.d(i9);
            return;
        }
        q qVar = this.B;
        if (qVar != null) {
            this.E = i8;
            this.G = i9;
            qVar.M(i8, i9);
            this.J0.d(this.f1071f, this.B.i(i8), this.B.i(i9));
            m0();
            this.O = 0.0f;
            t0();
        }
    }

    public final void q0() {
        int childCount = getChildCount();
        this.J0.a();
        boolean z7 = true;
        this.S = true;
        int width = getWidth();
        int height = getHeight();
        int iH = this.B.h();
        int i8 = 0;
        if (iH != -1) {
            for (int i9 = 0; i9 < childCount; i9++) {
                n nVar = this.K.get(getChildAt(i9));
                if (nVar != null) {
                    nVar.r(iH);
                }
            }
        }
        for (int i10 = 0; i10 < childCount; i10++) {
            n nVar2 = this.K.get(getChildAt(i10));
            if (nVar2 != null) {
                this.B.q(nVar2);
                nVar2.v(width, height, this.M, getNanoTime());
            }
        }
        float fW = this.B.w();
        if (fW != 0.0f) {
            boolean z8 = ((double) fW) < 0.0d;
            float fAbs = Math.abs(fW);
            float fMax = -3.4028235E38f;
            float fMin = Float.MAX_VALUE;
            float fMax2 = -3.4028235E38f;
            float fMin2 = Float.MAX_VALUE;
            int i11 = 0;
            while (true) {
                if (i11 >= childCount) {
                    z7 = false;
                    break;
                }
                n nVar3 = this.K.get(getChildAt(i11));
                if (!Float.isNaN(nVar3.f8923k)) {
                    break;
                }
                float fI = nVar3.i();
                float fJ = nVar3.j();
                float f8 = z8 ? fJ - fI : fJ + fI;
                fMin2 = Math.min(fMin2, f8);
                fMax2 = Math.max(fMax2, f8);
                i11++;
            }
            if (!z7) {
                while (i8 < childCount) {
                    n nVar4 = this.K.get(getChildAt(i8));
                    float fI2 = nVar4.i();
                    float fJ2 = nVar4.j();
                    float f9 = z8 ? fJ2 - fI2 : fJ2 + fI2;
                    nVar4.f8925m = 1.0f / (1.0f - fAbs);
                    nVar4.f8924l = fAbs - (((f9 - fMin2) * fAbs) / (fMax2 - fMin2));
                    i8++;
                }
                return;
            }
            for (int i12 = 0; i12 < childCount; i12++) {
                n nVar5 = this.K.get(getChildAt(i12));
                if (!Float.isNaN(nVar5.f8923k)) {
                    fMin = Math.min(fMin, nVar5.f8923k);
                    fMax = Math.max(fMax, nVar5.f8923k);
                }
            }
            while (i8 < childCount) {
                n nVar6 = this.K.get(getChildAt(i8));
                if (!Float.isNaN(nVar6.f8923k)) {
                    nVar6.f8925m = 1.0f / (1.0f - fAbs);
                    if (z8) {
                        nVar6.f8924l = fAbs - (((fMax - nVar6.f8923k) / (fMax - fMin)) * fAbs);
                    } else {
                        nVar6.f8924l = fAbs - (((nVar6.f8923k - fMin) * fAbs) / (fMax - fMin));
                    }
                }
                i8++;
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout
    public void r(int i8) {
        this.f1079n = null;
    }

    public void r0(int i8, float f8, float f9) {
        if (this.B == null || this.O == f8) {
            return;
        }
        this.Z = true;
        this.L = getNanoTime();
        float fM = this.B.m() / 1000.0f;
        this.M = fM;
        this.Q = f8;
        this.S = true;
        if (i8 == 0 || i8 == 1 || i8 == 2) {
            if (i8 == 1) {
                f8 = 0.0f;
            } else if (i8 == 2) {
                f8 = 1.0f;
            }
            this.f829a0.c(this.O, f8, f9, fM, this.B.r(), this.B.s());
            int i9 = this.F;
            this.Q = f8;
            this.F = i9;
            this.C = this.f829a0;
        } else if (i8 == 4) {
            this.f830b0.b(f9, this.O, this.B.r());
            this.C = this.f830b0;
        } else if (i8 == 5) {
            if (w0(f9, this.O, this.B.r())) {
                this.f830b0.b(f9, this.O, this.B.r());
                this.C = this.f830b0;
            } else {
                this.f829a0.c(this.O, f8, f9, this.M, this.B.r(), this.B.s());
                this.D = 0.0f;
                int i10 = this.F;
                this.Q = f8;
                this.F = i10;
                this.C = this.f829a0;
            }
        }
        this.R = false;
        this.L = getNanoTime();
        invalidate();
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View, android.view.ViewParent
    public void requestLayout() {
        q qVar;
        q.b bVar;
        if (this.f852x0 || this.F != -1 || (qVar = this.B) == null || (bVar = qVar.f8957c) == null || bVar.z() != 0) {
            super.requestLayout();
        }
    }

    public void s0() {
        T(1.0f);
    }

    public void setDebugMode(int i8) {
        this.X = i8;
        invalidate();
    }

    public void setInteractionEnabled(boolean z7) {
        this.J = z7;
    }

    public void setInterpolatedProgress(float f8) {
        if (this.B != null) {
            setState(TransitionState.MOVING);
            Interpolator interpolatorP = this.B.p();
            if (interpolatorP != null) {
                setProgress(interpolatorP.getInterpolation(f8));
                return;
            }
        }
        setProgress(f8);
    }

    public void setOnHide(float f8) {
        ArrayList<MotionHelper> arrayList = this.f844p0;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i8 = 0; i8 < size; i8++) {
                this.f844p0.get(i8).setProgress(f8);
            }
        }
    }

    public void setOnShow(float f8) {
        ArrayList<MotionHelper> arrayList = this.f843o0;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i8 = 0; i8 < size; i8++) {
                this.f843o0.get(i8).setProgress(f8);
            }
        }
    }

    public void setProgress(float f8) {
        if (!isAttachedToWindow()) {
            if (this.H0 == null) {
                this.H0 = new h();
            }
            this.H0.e(f8);
            return;
        }
        if (f8 <= 0.0f) {
            this.F = this.E;
            if (this.O == 0.0f) {
                setState(TransitionState.FINISHED);
            }
        } else if (f8 >= 1.0f) {
            this.F = this.G;
            if (this.O == 1.0f) {
                setState(TransitionState.FINISHED);
            }
        } else {
            this.F = -1;
            setState(TransitionState.MOVING);
        }
        if (this.B == null) {
            return;
        }
        this.R = true;
        this.Q = f8;
        this.N = f8;
        this.P = -1L;
        this.L = -1L;
        this.C = null;
        this.S = true;
        invalidate();
    }

    public void setScene(q qVar) {
        this.B = qVar;
        qVar.L(p());
        m0();
    }

    public void setState(TransitionState transitionState) {
        TransitionState transitionState2 = TransitionState.FINISHED;
        if (transitionState == transitionState2 && this.F == -1) {
            return;
        }
        TransitionState transitionState3 = this.I0;
        this.I0 = transitionState;
        TransitionState transitionState4 = TransitionState.MOVING;
        if (transitionState3 == transitionState4 && transitionState == transitionState4) {
            a0();
        }
        int i8 = b.f857a[transitionState3.ordinal()];
        if (i8 != 1 && i8 != 2) {
            if (i8 == 3 && transitionState == transitionState2) {
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

    public void setTransition(int i8) {
        if (this.B != null) {
            q.b bVarE0 = e0(i8);
            this.E = bVarE0.A();
            this.G = bVarE0.y();
            if (!isAttachedToWindow()) {
                if (this.H0 == null) {
                    this.H0 = new h();
                }
                this.H0.f(this.E);
                this.H0.d(this.G);
                return;
            }
            float f8 = Float.NaN;
            int i9 = this.F;
            if (i9 == this.E) {
                f8 = 0.0f;
            } else if (i9 == this.G) {
                f8 = 1.0f;
            }
            this.B.N(bVarE0);
            this.J0.d(this.f1071f, this.B.i(this.E), this.B.i(this.G));
            m0();
            this.O = Float.isNaN(f8) ? 0.0f : f8;
            if (!Float.isNaN(f8)) {
                setProgress(f8);
                return;
            }
            Log.v("MotionLayout", x.a.a() + " transitionToStart ");
            t0();
        }
    }

    public void setTransitionDuration(int i8) {
        q qVar = this.B;
        if (qVar == null) {
            Log.e("MotionLayout", "MotionScene not defined");
        } else {
            qVar.K(i8);
        }
    }

    public void setTransitionListener(i iVar) {
        this.U = iVar;
    }

    public void setTransitionState(Bundle bundle) {
        if (this.H0 == null) {
            this.H0 = new h();
        }
        this.H0.g(bundle);
        if (isAttachedToWindow()) {
            this.H0.a();
        }
    }

    public void t0() {
        T(0.0f);
    }

    @Override // android.view.View
    public String toString() {
        Context context = getContext();
        return x.a.b(context, this.E) + "->" + x.a.b(context, this.G) + " (pos:" + this.O + " Dpos/Dt:" + this.D;
    }

    public void u0(int i8) {
        if (isAttachedToWindow()) {
            v0(i8, -1, -1);
            return;
        }
        if (this.H0 == null) {
            this.H0 = new h();
        }
        this.H0.d(i8);
    }

    public void v0(int i8, int i9, int i10) {
        b0.d dVar;
        int iA;
        q qVar = this.B;
        if (qVar != null && (dVar = qVar.f8956b) != null && (iA = dVar.a(this.F, i8, i9, i10)) != -1) {
            i8 = iA;
        }
        int i11 = this.F;
        if (i11 == i8) {
            return;
        }
        if (this.E == i8) {
            T(0.0f);
            return;
        }
        if (this.G == i8) {
            T(1.0f);
            return;
        }
        this.G = i8;
        if (i11 != -1) {
            p0(i11, i8);
            T(1.0f);
            this.O = 0.0f;
            s0();
            return;
        }
        this.Z = false;
        this.Q = 1.0f;
        this.N = 0.0f;
        this.O = 0.0f;
        this.P = getNanoTime();
        this.L = getNanoTime();
        this.R = false;
        this.C = null;
        this.M = this.B.m() / 1000.0f;
        this.E = -1;
        this.B.M(-1, this.G);
        this.B.x();
        int childCount = getChildCount();
        this.K.clear();
        for (int i12 = 0; i12 < childCount; i12++) {
            View childAt = getChildAt(i12);
            this.K.put(childAt, new n(childAt));
        }
        this.S = true;
        this.J0.d(this.f1071f, null, this.B.i(i8));
        m0();
        this.J0.a();
        X();
        int width = getWidth();
        int height = getHeight();
        for (int i13 = 0; i13 < childCount; i13++) {
            n nVar = this.K.get(getChildAt(i13));
            this.B.q(nVar);
            nVar.v(width, height, this.M, getNanoTime());
        }
        float fW = this.B.w();
        if (fW != 0.0f) {
            float fMin = Float.MAX_VALUE;
            float fMax = -3.4028235E38f;
            for (int i14 = 0; i14 < childCount; i14++) {
                n nVar2 = this.K.get(getChildAt(i14));
                float fJ = nVar2.j() + nVar2.i();
                fMin = Math.min(fMin, fJ);
                fMax = Math.max(fMax, fJ);
            }
            for (int i15 = 0; i15 < childCount; i15++) {
                n nVar3 = this.K.get(getChildAt(i15));
                float fI = nVar3.i();
                float fJ2 = nVar3.j();
                nVar3.f8925m = 1.0f / (1.0f - fW);
                nVar3.f8924l = fW - ((((fI + fJ2) - fMin) * fW) / (fMax - fMin));
            }
        }
        this.N = 0.0f;
        this.O = 0.0f;
        this.S = true;
        invalidate();
    }

    public void setTransition(q.b bVar) {
        this.B.N(bVar);
        setState(TransitionState.SETUP);
        if (this.F == this.B.n()) {
            this.O = 1.0f;
            this.N = 1.0f;
            this.Q = 1.0f;
        } else {
            this.O = 0.0f;
            this.N = 0.0f;
            this.Q = 0.0f;
        }
        this.P = bVar.D(1) ? -1L : getNanoTime();
        int iX = this.B.x();
        int iN = this.B.n();
        if (iX == this.E && iN == this.G) {
            return;
        }
        this.E = iX;
        this.G = iN;
        this.B.M(iX, iN);
        this.J0.d(this.f1071f, this.B.i(this.E), this.B.i(this.G));
        this.J0.h(this.E, this.G);
        this.J0.g();
        m0();
    }

    public MotionLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.D = 0.0f;
        this.E = -1;
        this.F = -1;
        this.G = -1;
        this.H = 0;
        this.I = 0;
        this.J = true;
        this.K = new HashMap<>();
        this.L = 0L;
        this.M = 1.0f;
        this.N = 0.0f;
        this.O = 0.0f;
        this.Q = 0.0f;
        this.S = false;
        this.T = false;
        this.X = 0;
        this.Z = false;
        this.f829a0 = new w.g();
        this.f830b0 = new c();
        this.f832d0 = true;
        this.f837i0 = false;
        this.f842n0 = false;
        this.f843o0 = null;
        this.f844p0 = null;
        this.f845q0 = null;
        this.f846r0 = 0;
        this.f847s0 = -1L;
        this.f848t0 = 0.0f;
        this.f849u0 = 0;
        this.f850v0 = 0.0f;
        this.f851w0 = false;
        this.f852x0 = false;
        this.F0 = new x.e();
        this.G0 = false;
        this.I0 = TransitionState.UNDEFINED;
        this.J0 = new e();
        this.K0 = false;
        this.L0 = new RectF();
        this.M0 = null;
        this.N0 = new ArrayList<>();
        h0(attributeSet);
    }

    public MotionLayout(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.D = 0.0f;
        this.E = -1;
        this.F = -1;
        this.G = -1;
        this.H = 0;
        this.I = 0;
        this.J = true;
        this.K = new HashMap<>();
        this.L = 0L;
        this.M = 1.0f;
        this.N = 0.0f;
        this.O = 0.0f;
        this.Q = 0.0f;
        this.S = false;
        this.T = false;
        this.X = 0;
        this.Z = false;
        this.f829a0 = new w.g();
        this.f830b0 = new c();
        this.f832d0 = true;
        this.f837i0 = false;
        this.f842n0 = false;
        this.f843o0 = null;
        this.f844p0 = null;
        this.f845q0 = null;
        this.f846r0 = 0;
        this.f847s0 = -1L;
        this.f848t0 = 0.0f;
        this.f849u0 = 0;
        this.f850v0 = 0.0f;
        this.f851w0 = false;
        this.f852x0 = false;
        this.F0 = new x.e();
        this.G0 = false;
        this.I0 = TransitionState.UNDEFINED;
        this.J0 = new e();
        this.K0 = false;
        this.L0 = new RectF();
        this.M0 = null;
        this.N0 = new ArrayList<>();
        h0(attributeSet);
    }
}
