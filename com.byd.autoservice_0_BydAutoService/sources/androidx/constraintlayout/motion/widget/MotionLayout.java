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
import l0.m;
import u.n;
import u.o;
import u.q;
import u.t;
import w.j;

/* JADX INFO: loaded from: classes.dex */
public class MotionLayout extends ConstraintLayout implements m {

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static boolean f736y;
    public Interpolator A;
    public int A0;
    public float B;
    public int B0;
    public int C;
    public float C0;
    public int D;
    public u.e D0;
    public int E;
    public boolean E0;
    public int F;
    public h F0;
    public int G;
    public TransitionState G0;
    public boolean H;
    public e H0;
    public HashMap<View, n> I;
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
    public t.g Y;
    public c Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public u.b f737a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public boolean f738b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public int f739c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public int f740d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public int f741e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public int f742f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public boolean f743g0;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public float f744h0;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public float f745i0;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public long f746j0;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public float f747k0;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public boolean f748l0;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    public ArrayList<MotionHelper> f749m0;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public ArrayList<MotionHelper> f750n0;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public ArrayList<i> f751o0;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    public int f752p0;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    public long f753q0;

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    public float f754r0;

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    public int f755s0;

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    public float f756t0;

    /* JADX INFO: renamed from: u0, reason: collision with root package name */
    public boolean f757u0;

    /* JADX INFO: renamed from: v0, reason: collision with root package name */
    public boolean f758v0;

    /* JADX INFO: renamed from: w0, reason: collision with root package name */
    public int f759w0;

    /* JADX INFO: renamed from: x0, reason: collision with root package name */
    public int f760x0;

    /* JADX INFO: renamed from: y0, reason: collision with root package name */
    public int f761y0;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public q f762z;

    /* JADX INFO: renamed from: z0, reason: collision with root package name */
    public int f763z0;

    public enum TransitionState {
        UNDEFINED,
        SETUP,
        MOVING,
        FINISHED
    }

    public class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f764b;

        public a(View view) {
            this.f764b = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f764b.setNestedScrollingEnabled(true);
        }
    }

    public static /* synthetic */ class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f766a;

        static {
            int[] iArr = new int[TransitionState.values().length];
            f766a = iArr;
            try {
                iArr[TransitionState.UNDEFINED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f766a[TransitionState.SETUP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f766a[TransitionState.MOVING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f766a[TransitionState.FINISHED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public class c extends o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float f767a = 0.0f;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f768b = 0.0f;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f769c;

        public c() {
        }

        @Override // u.o
        public float a() {
            return MotionLayout.this.B;
        }

        public void b(float f3, float f4, float f5) {
            this.f767a = f3;
            this.f768b = f4;
            this.f769c = f5;
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f3) {
            float f4;
            float f5;
            float f6 = this.f767a;
            if (f6 > 0.0f) {
                float f7 = this.f769c;
                if (f6 / f7 < f3) {
                    f3 = f6 / f7;
                }
                MotionLayout.this.B = f6 - (f7 * f3);
                f4 = (f6 * f3) - (((f7 * f3) * f3) / 2.0f);
                f5 = this.f768b;
            } else {
                float f8 = this.f769c;
                if ((-f6) / f8 < f3) {
                    f3 = (-f6) / f8;
                }
                MotionLayout.this.B = (f8 * f3) + f6;
                f4 = (f6 * f3) + (((f8 * f3) * f3) / 2.0f);
                f5 = this.f768b;
            }
            return f4 + f5;
        }
    }

    public class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float[] f771a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int[] f772b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float[] f773c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Path f774d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Paint f775e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public Paint f776f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public Paint f777g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public Paint f778h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public Paint f779i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public float[] f780j;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public DashPathEffect f786p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public int f787q;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        public int f790t;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public final int f781k = -21965;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public final int f782l = -2067046;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public final int f783m = -13391360;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public final int f784n = 1996488704;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public final int f785o = 10;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public Rect f788r = new Rect();

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public boolean f789s = false;

        public d() {
            this.f790t = 1;
            Paint paint = new Paint();
            this.f775e = paint;
            paint.setAntiAlias(true);
            this.f775e.setColor(-21965);
            this.f775e.setStrokeWidth(2.0f);
            this.f775e.setStyle(Paint.Style.STROKE);
            Paint paint2 = new Paint();
            this.f776f = paint2;
            paint2.setAntiAlias(true);
            this.f776f.setColor(-2067046);
            this.f776f.setStrokeWidth(2.0f);
            this.f776f.setStyle(Paint.Style.STROKE);
            Paint paint3 = new Paint();
            this.f777g = paint3;
            paint3.setAntiAlias(true);
            this.f777g.setColor(-13391360);
            this.f777g.setStrokeWidth(2.0f);
            this.f777g.setStyle(Paint.Style.STROKE);
            Paint paint4 = new Paint();
            this.f778h = paint4;
            paint4.setAntiAlias(true);
            this.f778h.setColor(-13391360);
            this.f778h.setTextSize(MotionLayout.this.getContext().getResources().getDisplayMetrics().density * 12.0f);
            this.f780j = new float[8];
            Paint paint5 = new Paint();
            this.f779i = paint5;
            paint5.setAntiAlias(true);
            DashPathEffect dashPathEffect = new DashPathEffect(new float[]{4.0f, 8.0f}, 0.0f);
            this.f786p = dashPathEffect;
            this.f777g.setPathEffect(dashPathEffect);
            this.f773c = new float[100];
            this.f772b = new int[50];
            if (this.f789s) {
                this.f775e.setStrokeWidth(8.0f);
                this.f779i.setStrokeWidth(8.0f);
                this.f776f.setStrokeWidth(8.0f);
                this.f790t = 4;
            }
        }

        public void a(Canvas canvas, HashMap<View, n> map, int i3, int i4) {
            if (map == null || map.size() == 0) {
                return;
            }
            canvas.save();
            if (!MotionLayout.this.isInEditMode() && (i4 & 1) == 2) {
                String str = MotionLayout.this.getContext().getResources().getResourceName(MotionLayout.this.E) + ":" + MotionLayout.this.getProgress();
                canvas.drawText(str, 10.0f, MotionLayout.this.getHeight() - 30, this.f778h);
                canvas.drawText(str, 11.0f, MotionLayout.this.getHeight() - 29, this.f775e);
            }
            for (n nVar : map.values()) {
                int iH = nVar.h();
                if (i4 > 0 && iH == 0) {
                    iH = 1;
                }
                if (iH != 0) {
                    this.f787q = nVar.c(this.f773c, this.f772b);
                    if (iH >= 1) {
                        int i5 = i3 / 16;
                        float[] fArr = this.f771a;
                        if (fArr == null || fArr.length != i5 * 2) {
                            this.f771a = new float[i5 * 2];
                            this.f774d = new Path();
                        }
                        int i6 = this.f790t;
                        canvas.translate(i6, i6);
                        this.f775e.setColor(1996488704);
                        this.f779i.setColor(1996488704);
                        this.f776f.setColor(1996488704);
                        this.f777g.setColor(1996488704);
                        nVar.d(this.f771a, i5);
                        b(canvas, iH, this.f787q, nVar);
                        this.f775e.setColor(-21965);
                        this.f776f.setColor(-2067046);
                        this.f779i.setColor(-2067046);
                        this.f777g.setColor(-13391360);
                        int i7 = this.f790t;
                        canvas.translate(-i7, -i7);
                        b(canvas, iH, this.f787q, nVar);
                        if (iH == 5) {
                            j(canvas, nVar);
                        }
                    }
                }
            }
            canvas.restore();
        }

        public void b(Canvas canvas, int i3, int i4, n nVar) {
            if (i3 == 4) {
                d(canvas);
            }
            if (i3 == 2) {
                g(canvas);
            }
            if (i3 == 3) {
                e(canvas);
            }
            c(canvas);
            k(canvas, i3, i4, nVar);
        }

        public final void c(Canvas canvas) {
            canvas.drawLines(this.f771a, this.f775e);
        }

        public final void d(Canvas canvas) {
            boolean z3 = false;
            boolean z4 = false;
            for (int i3 = 0; i3 < this.f787q; i3++) {
                int[] iArr = this.f772b;
                if (iArr[i3] == 1) {
                    z3 = true;
                }
                if (iArr[i3] == 2) {
                    z4 = true;
                }
            }
            if (z3) {
                g(canvas);
            }
            if (z4) {
                e(canvas);
            }
        }

        public final void e(Canvas canvas) {
            float[] fArr = this.f771a;
            float f3 = fArr[0];
            float f4 = fArr[1];
            float f5 = fArr[fArr.length - 2];
            float f6 = fArr[fArr.length - 1];
            canvas.drawLine(Math.min(f3, f5), Math.max(f4, f6), Math.max(f3, f5), Math.max(f4, f6), this.f777g);
            canvas.drawLine(Math.min(f3, f5), Math.min(f4, f6), Math.min(f3, f5), Math.max(f4, f6), this.f777g);
        }

        public final void f(Canvas canvas, float f3, float f4) {
            float[] fArr = this.f771a;
            float f5 = fArr[0];
            float f6 = fArr[1];
            float f7 = fArr[fArr.length - 2];
            float f8 = fArr[fArr.length - 1];
            float fMin = Math.min(f5, f7);
            float fMax = Math.max(f6, f8);
            float fMin2 = f3 - Math.min(f5, f7);
            float fMax2 = Math.max(f6, f8) - f4;
            String str = "" + (((int) (((double) ((fMin2 * 100.0f) / Math.abs(f7 - f5))) + 0.5d)) / 100.0f);
            l(str, this.f778h);
            canvas.drawText(str, ((fMin2 / 2.0f) - (this.f788r.width() / 2)) + fMin, f4 - 20.0f, this.f778h);
            canvas.drawLine(f3, f4, Math.min(f5, f7), f4, this.f777g);
            String str2 = "" + (((int) (((double) ((fMax2 * 100.0f) / Math.abs(f8 - f6))) + 0.5d)) / 100.0f);
            l(str2, this.f778h);
            canvas.drawText(str2, f3 + 5.0f, fMax - ((fMax2 / 2.0f) - (this.f788r.height() / 2)), this.f778h);
            canvas.drawLine(f3, f4, f3, Math.max(f6, f8), this.f777g);
        }

        public final void g(Canvas canvas) {
            float[] fArr = this.f771a;
            canvas.drawLine(fArr[0], fArr[1], fArr[fArr.length - 2], fArr[fArr.length - 1], this.f777g);
        }

        public final void h(Canvas canvas, float f3, float f4) {
            float[] fArr = this.f771a;
            float f5 = fArr[0];
            float f6 = fArr[1];
            float f7 = fArr[fArr.length - 2];
            float f8 = fArr[fArr.length - 1];
            float fHypot = (float) Math.hypot(f5 - f7, f6 - f8);
            float f9 = f7 - f5;
            float f10 = f8 - f6;
            float f11 = (((f3 - f5) * f9) + ((f4 - f6) * f10)) / (fHypot * fHypot);
            float f12 = f5 + (f9 * f11);
            float f13 = f6 + (f11 * f10);
            Path path = new Path();
            path.moveTo(f3, f4);
            path.lineTo(f12, f13);
            float fHypot2 = (float) Math.hypot(f12 - f3, f13 - f4);
            String str = "" + (((int) ((fHypot2 * 100.0f) / fHypot)) / 100.0f);
            l(str, this.f778h);
            canvas.drawTextOnPath(str, path, (fHypot2 / 2.0f) - (this.f788r.width() / 2), -20.0f, this.f778h);
            canvas.drawLine(f3, f4, f12, f13, this.f777g);
        }

        public final void i(Canvas canvas, float f3, float f4, int i3, int i4) {
            String str = "" + (((int) (((double) (((f3 - (i3 / 2)) * 100.0f) / (MotionLayout.this.getWidth() - i3))) + 0.5d)) / 100.0f);
            l(str, this.f778h);
            canvas.drawText(str, ((f3 / 2.0f) - (this.f788r.width() / 2)) + 0.0f, f4 - 20.0f, this.f778h);
            canvas.drawLine(f3, f4, Math.min(0.0f, 1.0f), f4, this.f777g);
            String str2 = "" + (((int) (((double) (((f4 - (i4 / 2)) * 100.0f) / (MotionLayout.this.getHeight() - i4))) + 0.5d)) / 100.0f);
            l(str2, this.f778h);
            canvas.drawText(str2, f3 + 5.0f, 0.0f - ((f4 / 2.0f) - (this.f788r.height() / 2)), this.f778h);
            canvas.drawLine(f3, f4, f3, Math.max(0.0f, 1.0f), this.f777g);
        }

        public final void j(Canvas canvas, n nVar) {
            this.f774d.reset();
            for (int i3 = 0; i3 <= 50; i3++) {
                nVar.e(i3 / 50, this.f780j, 0);
                Path path = this.f774d;
                float[] fArr = this.f780j;
                path.moveTo(fArr[0], fArr[1]);
                Path path2 = this.f774d;
                float[] fArr2 = this.f780j;
                path2.lineTo(fArr2[2], fArr2[3]);
                Path path3 = this.f774d;
                float[] fArr3 = this.f780j;
                path3.lineTo(fArr3[4], fArr3[5]);
                Path path4 = this.f774d;
                float[] fArr4 = this.f780j;
                path4.lineTo(fArr4[6], fArr4[7]);
                this.f774d.close();
            }
            this.f775e.setColor(1140850688);
            canvas.translate(2.0f, 2.0f);
            canvas.drawPath(this.f774d, this.f775e);
            canvas.translate(-2.0f, -2.0f);
            this.f775e.setColor(-65536);
            canvas.drawPath(this.f774d, this.f775e);
        }

        public final void k(Canvas canvas, int i3, int i4, n nVar) {
            int width;
            int height;
            int i5;
            float f3;
            float f4;
            View view = nVar.f5201a;
            if (view != null) {
                width = view.getWidth();
                height = nVar.f5201a.getHeight();
            } else {
                width = 0;
                height = 0;
            }
            for (int i6 = 1; i6 < i4 - 1; i6++) {
                if (i3 != 4 || this.f772b[i6 - 1] != 0) {
                    float[] fArr = this.f773c;
                    int i7 = i6 * 2;
                    float f5 = fArr[i7];
                    float f6 = fArr[i7 + 1];
                    this.f774d.reset();
                    this.f774d.moveTo(f5, f6 + 10.0f);
                    this.f774d.lineTo(f5 + 10.0f, f6);
                    this.f774d.lineTo(f5, f6 - 10.0f);
                    this.f774d.lineTo(f5 - 10.0f, f6);
                    this.f774d.close();
                    int i8 = i6 - 1;
                    nVar.k(i8);
                    if (i3 == 4) {
                        int[] iArr = this.f772b;
                        if (iArr[i8] == 1) {
                            h(canvas, f5 - 0.0f, f6 - 0.0f);
                        } else if (iArr[i8] == 2) {
                            f(canvas, f5 - 0.0f, f6 - 0.0f);
                        } else {
                            if (iArr[i8] == 3) {
                                i5 = 3;
                                f3 = f6;
                                f4 = f5;
                                i(canvas, f5 - 0.0f, f6 - 0.0f, width, height);
                            }
                            canvas.drawPath(this.f774d, this.f779i);
                        }
                        i5 = 3;
                        f3 = f6;
                        f4 = f5;
                        canvas.drawPath(this.f774d, this.f779i);
                    } else {
                        i5 = 3;
                        f3 = f6;
                        f4 = f5;
                    }
                    if (i3 == 2) {
                        h(canvas, f4 - 0.0f, f3 - 0.0f);
                    }
                    if (i3 == i5) {
                        f(canvas, f4 - 0.0f, f3 - 0.0f);
                    }
                    if (i3 == 6) {
                        i(canvas, f4 - 0.0f, f3 - 0.0f, width, height);
                    }
                    canvas.drawPath(this.f774d, this.f779i);
                }
            }
            float[] fArr2 = this.f771a;
            if (fArr2.length > 1) {
                canvas.drawCircle(fArr2[0], fArr2[1], 8.0f, this.f776f);
                float[] fArr3 = this.f771a;
                canvas.drawCircle(fArr3[fArr3.length - 2], fArr3[fArr3.length - 1], 8.0f, this.f776f);
            }
        }

        public void l(String str, Paint paint) {
            paint.getTextBounds(str, 0, str.length(), this.f788r);
        }
    }

    public class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public w.d f792a = new w.d();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public w.d f793b = new w.d();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public y.b f794c = null;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public y.b f795d = null;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f796e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f797f;

        public e() {
        }

        public void a() {
            int childCount = MotionLayout.this.getChildCount();
            MotionLayout.this.I.clear();
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = MotionLayout.this.getChildAt(i3);
                MotionLayout.this.I.put(childAt, new n(childAt));
            }
            for (int i4 = 0; i4 < childCount; i4++) {
                View childAt2 = MotionLayout.this.getChildAt(i4);
                n nVar = MotionLayout.this.I.get(childAt2);
                if (nVar != null) {
                    if (this.f794c != null) {
                        ConstraintWidget constraintWidgetC = c(this.f792a, childAt2);
                        if (constraintWidgetC != null) {
                            nVar.t(constraintWidgetC, this.f794c);
                        } else if (MotionLayout.this.V != 0) {
                            Log.e("MotionLayout", u.a.a() + "no widget for  " + u.a.c(childAt2) + " (" + childAt2.getClass().getName() + ")");
                        }
                    }
                    if (this.f795d != null) {
                        ConstraintWidget constraintWidgetC2 = c(this.f793b, childAt2);
                        if (constraintWidgetC2 != null) {
                            nVar.q(constraintWidgetC2, this.f795d);
                        } else if (MotionLayout.this.V != 0) {
                            Log.e("MotionLayout", u.a.a() + "no widget for  " + u.a.c(childAt2) + " (" + childAt2.getClass().getName() + ")");
                        }
                    }
                }
            }
        }

        public void b(w.d dVar, w.d dVar2) {
            ArrayList<ConstraintWidget> arrayListL0 = dVar.L0();
            HashMap<ConstraintWidget, ConstraintWidget> map = new HashMap<>();
            map.put(dVar, dVar2);
            dVar2.L0().clear();
            dVar2.l(dVar, map);
            for (ConstraintWidget constraintWidget : arrayListL0) {
                ConstraintWidget aVar = constraintWidget instanceof w.a ? new w.a() : constraintWidget instanceof w.f ? new w.f() : constraintWidget instanceof w.e ? new w.e() : constraintWidget instanceof w.g ? new w.h() : new ConstraintWidget();
                dVar2.a(aVar);
                map.put(constraintWidget, aVar);
            }
            for (ConstraintWidget constraintWidget2 : arrayListL0) {
                map.get(constraintWidget2).l(constraintWidget2, map);
            }
        }

        public ConstraintWidget c(w.d dVar, View view) {
            if (dVar.r() == view) {
                return dVar;
            }
            ArrayList<ConstraintWidget> arrayListL0 = dVar.L0();
            int size = arrayListL0.size();
            for (int i3 = 0; i3 < size; i3++) {
                ConstraintWidget constraintWidget = arrayListL0.get(i3);
                if (constraintWidget.r() == view) {
                    return constraintWidget;
                }
            }
            return null;
        }

        public void d(w.d dVar, y.b bVar, y.b bVar2) {
            this.f794c = bVar;
            this.f795d = bVar2;
            this.f792a = new w.d();
            this.f793b = new w.d();
            this.f792a.h1(MotionLayout.this.f980d.W0());
            this.f793b.h1(MotionLayout.this.f980d.W0());
            this.f792a.O0();
            this.f793b.O0();
            b(MotionLayout.this.f980d, this.f792a);
            b(MotionLayout.this.f980d, this.f793b);
            if (MotionLayout.this.M > 0.5d) {
                if (bVar != null) {
                    i(this.f792a, bVar);
                }
                i(this.f793b, bVar2);
            } else {
                i(this.f793b, bVar2);
                if (bVar != null) {
                    i(this.f792a, bVar);
                }
            }
            this.f792a.j1(MotionLayout.this.p());
            this.f792a.l1();
            this.f793b.j1(MotionLayout.this.p());
            this.f793b.l1();
            ViewGroup.LayoutParams layoutParams = MotionLayout.this.getLayoutParams();
            if (layoutParams != null) {
                if (layoutParams.width == -2) {
                    w.d dVar2 = this.f792a;
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    dVar2.m0(dimensionBehaviour);
                    this.f793b.m0(dimensionBehaviour);
                }
                if (layoutParams.height == -2) {
                    w.d dVar3 = this.f792a;
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    dVar3.B0(dimensionBehaviour2);
                    this.f793b.B0(dimensionBehaviour2);
                }
            }
        }

        public boolean e(int i3, int i4) {
            return (i3 == this.f796e && i4 == this.f797f) ? false : true;
        }

        public void f(int i3, int i4) {
            int mode = View.MeasureSpec.getMode(i3);
            int mode2 = View.MeasureSpec.getMode(i4);
            MotionLayout motionLayout = MotionLayout.this;
            motionLayout.A0 = mode;
            motionLayout.B0 = mode2;
            int optimizationLevel = motionLayout.getOptimizationLevel();
            MotionLayout motionLayout2 = MotionLayout.this;
            if (motionLayout2.D == motionLayout2.getStartState()) {
                MotionLayout.this.t(this.f793b, optimizationLevel, i3, i4);
                if (this.f794c != null) {
                    MotionLayout.this.t(this.f792a, optimizationLevel, i3, i4);
                }
            } else {
                if (this.f794c != null) {
                    MotionLayout.this.t(this.f792a, optimizationLevel, i3, i4);
                }
                MotionLayout.this.t(this.f793b, optimizationLevel, i3, i4);
            }
            if (((MotionLayout.this.getParent() instanceof MotionLayout) && mode == 1073741824 && mode2 == 1073741824) ? false : true) {
                MotionLayout motionLayout3 = MotionLayout.this;
                motionLayout3.A0 = mode;
                motionLayout3.B0 = mode2;
                if (motionLayout3.D == motionLayout3.getStartState()) {
                    MotionLayout.this.t(this.f793b, optimizationLevel, i3, i4);
                    if (this.f794c != null) {
                        MotionLayout.this.t(this.f792a, optimizationLevel, i3, i4);
                    }
                } else {
                    if (this.f794c != null) {
                        MotionLayout.this.t(this.f792a, optimizationLevel, i3, i4);
                    }
                    MotionLayout.this.t(this.f793b, optimizationLevel, i3, i4);
                }
                MotionLayout.this.f759w0 = this.f792a.Q();
                MotionLayout.this.f760x0 = this.f792a.w();
                MotionLayout.this.f761y0 = this.f793b.Q();
                MotionLayout.this.f763z0 = this.f793b.w();
                MotionLayout motionLayout4 = MotionLayout.this;
                motionLayout4.f758v0 = (motionLayout4.f759w0 == motionLayout4.f761y0 && motionLayout4.f760x0 == motionLayout4.f763z0) ? false : true;
            }
            MotionLayout motionLayout5 = MotionLayout.this;
            int i5 = motionLayout5.f759w0;
            int i6 = motionLayout5.f760x0;
            int i7 = motionLayout5.A0;
            if (i7 == Integer.MIN_VALUE || i7 == 0) {
                i5 = (int) (i5 + (motionLayout5.C0 * (motionLayout5.f761y0 - i5)));
            }
            int i8 = motionLayout5.B0;
            if (i8 == Integer.MIN_VALUE || i8 == 0) {
                i6 = (int) (i6 + (motionLayout5.C0 * (motionLayout5.f763z0 - i6)));
            }
            MotionLayout.this.s(i3, i4, i5, i6, this.f792a.d1() || this.f793b.d1(), this.f792a.b1() || this.f793b.b1());
        }

        public void g() {
            f(MotionLayout.this.F, MotionLayout.this.G);
            MotionLayout.this.q0();
        }

        public void h(int i3, int i4) {
            this.f796e = i3;
            this.f797f = i4;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final void i(w.d dVar, y.b bVar) {
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
                    w.g gVar = (w.g) constraintWidget3;
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

        void e(int i3);
    }

    public static class g implements f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static g f799a = new g();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public VelocityTracker f800b;

        public static g f() {
            f799a.f800b = VelocityTracker.obtain();
            return f799a;
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.f
        public void a(MotionEvent motionEvent) {
            VelocityTracker velocityTracker = this.f800b;
            if (velocityTracker != null) {
                velocityTracker.addMovement(motionEvent);
            }
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.f
        public float b() {
            return this.f800b.getYVelocity();
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.f
        public float c() {
            return this.f800b.getXVelocity();
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.f
        public void d() {
            this.f800b.recycle();
            this.f800b = null;
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.f
        public void e(int i3) {
            this.f800b.computeCurrentVelocity(i3);
        }
    }

    public class h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float f801a = Float.NaN;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f802b = Float.NaN;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f803c = -1;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f804d = -1;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final String f805e = "motion.progress";

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final String f806f = "motion.velocity";

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final String f807g = "motion.StartState";

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final String f808h = "motion.EndState";

        public h() {
        }

        public void a() {
            int i3 = this.f803c;
            if (i3 != -1 || this.f804d != -1) {
                if (i3 == -1) {
                    MotionLayout.this.u0(this.f804d);
                } else {
                    int i4 = this.f804d;
                    if (i4 == -1) {
                        MotionLayout.this.o0(i3, -1, -1);
                    } else {
                        MotionLayout.this.p0(i3, i4);
                    }
                }
                MotionLayout.this.setState(TransitionState.SETUP);
            }
            if (Float.isNaN(this.f802b)) {
                if (Float.isNaN(this.f801a)) {
                    return;
                }
                MotionLayout.this.setProgress(this.f801a);
            } else {
                MotionLayout.this.n0(this.f801a, this.f802b);
                this.f801a = Float.NaN;
                this.f802b = Float.NaN;
                this.f803c = -1;
                this.f804d = -1;
            }
        }

        public Bundle b() {
            Bundle bundle = new Bundle();
            bundle.putFloat("motion.progress", this.f801a);
            bundle.putFloat("motion.velocity", this.f802b);
            bundle.putInt("motion.StartState", this.f803c);
            bundle.putInt("motion.EndState", this.f804d);
            return bundle;
        }

        public void c() {
            this.f804d = MotionLayout.this.E;
            this.f803c = MotionLayout.this.C;
            this.f802b = MotionLayout.this.getVelocity();
            this.f801a = MotionLayout.this.getProgress();
        }

        public void d(int i3) {
            this.f804d = i3;
        }

        public void e(float f3) {
            this.f801a = f3;
        }

        public void f(int i3) {
            this.f803c = i3;
        }

        public void g(Bundle bundle) {
            this.f801a = bundle.getFloat("motion.progress");
            this.f802b = bundle.getFloat("motion.velocity");
            this.f803c = bundle.getInt("motion.StartState");
            this.f804d = bundle.getInt("motion.EndState");
        }

        public void h(float f3) {
            this.f802b = f3;
        }
    }

    public interface i {
        void a(MotionLayout motionLayout, int i3, int i4);

        void b(MotionLayout motionLayout, int i3, boolean z3, float f3);

        void c(MotionLayout motionLayout, int i3, int i4, float f3);

        void d(MotionLayout motionLayout, int i3);
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
        this.Y = new t.g();
        this.Z = new c();
        this.f738b0 = true;
        this.f743g0 = false;
        this.f748l0 = false;
        this.f749m0 = null;
        this.f750n0 = null;
        this.f751o0 = null;
        this.f752p0 = 0;
        this.f753q0 = -1L;
        this.f754r0 = 0.0f;
        this.f755s0 = 0;
        this.f756t0 = 0.0f;
        this.f757u0 = false;
        this.f758v0 = false;
        this.D0 = new u.e();
        this.E0 = false;
        this.G0 = TransitionState.UNDEFINED;
        this.H0 = new e();
        this.I0 = false;
        this.J0 = new RectF();
        this.K0 = null;
        this.L0 = new ArrayList<>();
        h0(null);
    }

    public static boolean w0(float f3, float f4, float f5) {
        if (f3 > 0.0f) {
            float f6 = f3 / f5;
            return f4 + ((f3 * f6) - (((f5 * f6) * f6) / 2.0f)) > 1.0f;
        }
        float f7 = (-f3) / f5;
        return f4 + ((f3 * f7) + (((f5 * f7) * f7) / 2.0f)) < 0.0f;
    }

    public void T(float f3) {
        if (this.f762z == null) {
            return;
        }
        float f4 = this.M;
        float f5 = this.L;
        if (f4 != f5 && this.P) {
            this.M = f5;
        }
        float f6 = this.M;
        if (f6 == f3) {
            return;
        }
        this.X = false;
        this.O = f3;
        this.K = r0.m() / 1000.0f;
        setProgress(this.O);
        this.A = this.f762z.p();
        this.P = false;
        this.J = getNanoTime();
        this.Q = true;
        this.L = f6;
        this.M = f6;
        invalidate();
    }

    public final void U() {
        q qVar = this.f762z;
        if (qVar == null) {
            Log.e("MotionLayout", "CHECK: motion scene not set! set \"app:layoutDescription=\"@xml/file\"");
            return;
        }
        int iX = qVar.x();
        q qVar2 = this.f762z;
        V(iX, qVar2.i(qVar2.x()));
        SparseIntArray sparseIntArray = new SparseIntArray();
        SparseIntArray sparseIntArray2 = new SparseIntArray();
        for (q.b bVar : this.f762z.l()) {
            if (bVar == this.f762z.f5245c) {
                Log.v("MotionLayout", "CHECK: CURRENT");
            }
            W(bVar);
            int iA = bVar.A();
            int iY = bVar.y();
            String strB = u.a.b(getContext(), iA);
            String strB2 = u.a.b(getContext(), iY);
            if (sparseIntArray.get(iA) == iY) {
                Log.e("MotionLayout", "CHECK: two transitions with the same start and end " + strB + "->" + strB2);
            }
            if (sparseIntArray2.get(iY) == iA) {
                Log.e("MotionLayout", "CHECK: you can't have reverse transitions" + strB + "->" + strB2);
            }
            sparseIntArray.put(iA, iY);
            sparseIntArray2.put(iY, iA);
            if (this.f762z.i(iA) == null) {
                Log.e("MotionLayout", " no such constraintSetStart " + strB);
            }
            if (this.f762z.i(iY) == null) {
                Log.e("MotionLayout", " no such constraintSetEnd " + strB);
            }
        }
    }

    public final void V(int i3, y.b bVar) {
        String strB = u.a.b(getContext(), i3);
        int childCount = getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = getChildAt(i4);
            int id = childAt.getId();
            if (id == -1) {
                Log.w("MotionLayout", "CHECK: " + strB + " ALL VIEWS SHOULD HAVE ID's " + childAt.getClass().getName() + " does not!");
            }
            if (bVar.n(id) == null) {
                Log.w("MotionLayout", "CHECK: " + strB + " NO CONSTRAINTS for " + u.a.c(childAt));
            }
        }
        int[] iArrP = bVar.p();
        for (int i5 = 0; i5 < iArrP.length; i5++) {
            int i6 = iArrP[i5];
            String strB2 = u.a.b(getContext(), i6);
            if (findViewById(iArrP[i5]) == null) {
                Log.w("MotionLayout", "CHECK: " + strB + " NO View matches id " + strB2);
            }
            if (bVar.o(i6) == -1) {
                Log.w("MotionLayout", "CHECK: " + strB + "(" + strB2 + ") no LAYOUT_HEIGHT");
            }
            if (bVar.t(i6) == -1) {
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
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            n nVar = this.I.get(childAt);
            if (nVar != null) {
                nVar.s(childAt);
            }
        }
    }

    public void Y(boolean z3) {
        float f3;
        boolean z4;
        int i3;
        float interpolation;
        boolean z5;
        if (this.N == -1) {
            this.N = getNanoTime();
        }
        float f4 = this.M;
        if (f4 > 0.0f && f4 < 1.0f) {
            this.D = -1;
        }
        boolean z6 = false;
        if (this.f748l0 || (this.Q && (z3 || this.O != f4))) {
            float fSignum = Math.signum(this.O - f4);
            long nanoTime = getNanoTime();
            Interpolator interpolator = this.A;
            if (interpolator instanceof o) {
                f3 = 0.0f;
            } else {
                f3 = (((nanoTime - this.N) * fSignum) * 1.0E-9f) / this.K;
                this.B = f3;
            }
            float f5 = this.M + f3;
            if (this.P) {
                f5 = this.O;
            }
            if ((fSignum <= 0.0f || f5 < this.O) && (fSignum > 0.0f || f5 > this.O)) {
                z4 = false;
            } else {
                f5 = this.O;
                this.Q = false;
                z4 = true;
            }
            this.M = f5;
            this.L = f5;
            this.N = nanoTime;
            if (interpolator != null && !z4) {
                if (this.X) {
                    interpolation = interpolator.getInterpolation((nanoTime - this.J) * 1.0E-9f);
                    this.M = interpolation;
                    this.N = nanoTime;
                    Interpolator interpolator2 = this.A;
                    if (interpolator2 instanceof o) {
                        float fA = ((o) interpolator2).a();
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
                            f5 = 0.0f;
                        }
                    }
                } else {
                    interpolation = interpolator.getInterpolation(f5);
                    Interpolator interpolator3 = this.A;
                    if (interpolator3 instanceof o) {
                        this.B = ((o) interpolator3).a();
                    } else {
                        this.B = ((interpolator3.getInterpolation(f5 + f3) - interpolation) * fSignum) / f3;
                    }
                }
                f5 = interpolation;
            }
            if (Math.abs(this.B) > 1.0E-5f) {
                setState(TransitionState.MOVING);
            }
            if ((fSignum > 0.0f && f5 >= this.O) || (fSignum <= 0.0f && f5 <= this.O)) {
                f5 = this.O;
                this.Q = false;
            }
            if (f5 >= 1.0f || f5 <= 0.0f) {
                this.Q = false;
                setState(TransitionState.FINISHED);
            }
            int childCount = getChildCount();
            this.f748l0 = false;
            long nanoTime2 = getNanoTime();
            this.C0 = f5;
            for (int i4 = 0; i4 < childCount; i4++) {
                View childAt = getChildAt(i4);
                n nVar = this.I.get(childAt);
                if (nVar != null) {
                    this.f748l0 = nVar.o(childAt, f5, nanoTime2, this.D0) | this.f748l0;
                }
            }
            boolean z7 = (fSignum > 0.0f && f5 >= this.O) || (fSignum <= 0.0f && f5 <= this.O);
            if (!this.f748l0 && !this.Q && z7) {
                setState(TransitionState.FINISHED);
            }
            if (this.f758v0) {
                requestLayout();
            }
            this.f748l0 = (!z7) | this.f748l0;
            if (f5 <= 0.0f && (i3 = this.C) != -1 && this.D != i3) {
                this.D = i3;
                this.f762z.i(i3).c(this);
                setState(TransitionState.FINISHED);
                z6 = true;
            }
            if (f5 >= 1.0d) {
                int i5 = this.D;
                int i6 = this.E;
                if (i5 != i6) {
                    this.D = i6;
                    this.f762z.i(i6).c(this);
                    setState(TransitionState.FINISHED);
                    z6 = true;
                }
            }
            if (this.f748l0 || this.Q) {
                invalidate();
            } else if ((fSignum > 0.0f && f5 == 1.0f) || (fSignum < 0.0f && f5 == 0.0f)) {
                setState(TransitionState.FINISHED);
            }
            if ((!this.f748l0 && this.Q && fSignum > 0.0f && f5 == 1.0f) || (fSignum < 0.0f && f5 == 0.0f)) {
                k0();
            }
        }
        float f6 = this.M;
        if (f6 < 1.0f) {
            if (f6 <= 0.0f) {
                int i7 = this.D;
                int i8 = this.C;
                z5 = i7 == i8 ? z6 : true;
                this.D = i8;
            }
            this.I0 |= z6;
            if (z6 && !this.E0) {
                requestLayout();
            }
            this.L = this.M;
        }
        int i9 = this.D;
        int i10 = this.E;
        z5 = i9 == i10 ? z6 : true;
        this.D = i10;
        z6 = z5;
        this.I0 |= z6;
        if (z6) {
            requestLayout();
        }
        this.L = this.M;
    }

    public final void Z() {
        boolean z3;
        float fSignum = Math.signum(this.O - this.M);
        long nanoTime = getNanoTime();
        Interpolator interpolator = this.A;
        float interpolation = this.M + (!(interpolator instanceof t.g) ? (((nanoTime - this.N) * fSignum) * 1.0E-9f) / this.K : 0.0f);
        if (this.P) {
            interpolation = this.O;
        }
        if ((fSignum <= 0.0f || interpolation < this.O) && (fSignum > 0.0f || interpolation > this.O)) {
            z3 = false;
        } else {
            interpolation = this.O;
            z3 = true;
        }
        if (interpolator != null && !z3) {
            interpolation = this.X ? interpolator.getInterpolation((nanoTime - this.J) * 1.0E-9f) : interpolator.getInterpolation(interpolation);
        }
        if ((fSignum > 0.0f && interpolation >= this.O) || (fSignum <= 0.0f && interpolation <= this.O)) {
            interpolation = this.O;
        }
        this.C0 = interpolation;
        int childCount = getChildCount();
        long nanoTime2 = getNanoTime();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            n nVar = this.I.get(childAt);
            if (nVar != null) {
                nVar.o(childAt, interpolation, nanoTime2, this.D0);
            }
        }
        if (this.f758v0) {
            requestLayout();
        }
    }

    public final void a0() {
        ArrayList<i> arrayList;
        if ((this.S == null && ((arrayList = this.f751o0) == null || arrayList.isEmpty())) || this.f756t0 == this.L) {
            return;
        }
        if (this.f755s0 != -1) {
            i iVar = this.S;
            if (iVar != null) {
                iVar.a(this, this.C, this.E);
            }
            ArrayList<i> arrayList2 = this.f751o0;
            if (arrayList2 != null) {
                Iterator<i> it = arrayList2.iterator();
                while (it.hasNext()) {
                    it.next().a(this, this.C, this.E);
                }
            }
            this.f757u0 = true;
        }
        this.f755s0 = -1;
        float f3 = this.L;
        this.f756t0 = f3;
        i iVar2 = this.S;
        if (iVar2 != null) {
            iVar2.c(this, this.C, this.E, f3);
        }
        ArrayList<i> arrayList3 = this.f751o0;
        if (arrayList3 != null) {
            Iterator<i> it2 = arrayList3.iterator();
            while (it2.hasNext()) {
                it2.next().c(this, this.C, this.E, this.L);
            }
        }
        this.f757u0 = true;
    }

    public void b0() {
        int iIntValue;
        ArrayList<i> arrayList;
        if ((this.S != null || ((arrayList = this.f751o0) != null && !arrayList.isEmpty())) && this.f755s0 == -1) {
            this.f755s0 = this.D;
            if (this.L0.isEmpty()) {
                iIntValue = -1;
            } else {
                iIntValue = this.L0.get(r0.size() - 1).intValue();
            }
            int i3 = this.D;
            if (iIntValue != i3 && i3 != -1) {
                this.L0.add(Integer.valueOf(i3));
            }
        }
        l0();
    }

    public void c0(int i3, boolean z3, float f3) {
        i iVar = this.S;
        if (iVar != null) {
            iVar.b(this, i3, z3, f3);
        }
        ArrayList<i> arrayList = this.f751o0;
        if (arrayList != null) {
            Iterator<i> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().b(this, i3, z3, f3);
            }
        }
    }

    public void d0(int i3, float f3, float f4, float f5, float[] fArr) {
        String resourceName;
        HashMap<View, n> map = this.I;
        View viewG = g(i3);
        n nVar = map.get(viewG);
        if (nVar != null) {
            nVar.g(f3, f4, f5, fArr);
            float y3 = viewG.getY();
            int i4 = ((f3 - this.T) > 0.0f ? 1 : ((f3 - this.T) == 0.0f ? 0 : -1));
            this.T = f3;
            this.U = y3;
            return;
        }
        if (viewG == null) {
            resourceName = "" + i3;
        } else {
            resourceName = viewG.getContext().getResources().getResourceName(i3);
        }
        Log.w("MotionLayout", "WARNING could not find view id " + resourceName);
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        Y(false);
        super.dispatchDraw(canvas);
        if (this.f762z == null) {
            return;
        }
        if ((this.V & 1) == 1 && !isInEditMode()) {
            this.f752p0++;
            long nanoTime = getNanoTime();
            long j3 = this.f753q0;
            if (j3 != -1) {
                if (nanoTime - j3 > 200000000) {
                    this.f754r0 = ((int) ((this.f752p0 / (r5 * 1.0E-9f)) * 100.0f)) / 100.0f;
                    this.f752p0 = 0;
                    this.f753q0 = nanoTime;
                }
            } else {
                this.f753q0 = nanoTime;
            }
            Paint paint = new Paint();
            paint.setTextSize(42.0f);
            String str = this.f754r0 + " fps " + u.a.d(this, this.C) + " -> ";
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(u.a.d(this, this.E));
            sb.append(" (progress: ");
            sb.append(((int) (getProgress() * 1000.0f)) / 10.0f);
            sb.append(" ) state=");
            int i3 = this.D;
            sb.append(i3 == -1 ? "undefined" : u.a.d(this, i3));
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
            this.W.a(canvas, this.I, this.f762z.m(), this.V);
        }
    }

    public q.b e0(int i3) {
        return this.f762z.y(i3);
    }

    public void f0(View view, float f3, float f4, float[] fArr, int i3) {
        float f5;
        float fA = this.B;
        float f6 = this.M;
        if (this.A != null) {
            float fSignum = Math.signum(this.O - f6);
            float interpolation = this.A.getInterpolation(this.M + 1.0E-5f);
            float interpolation2 = this.A.getInterpolation(this.M);
            fA = (fSignum * ((interpolation - interpolation2) / 1.0E-5f)) / this.K;
            f5 = interpolation2;
        } else {
            f5 = f6;
        }
        Interpolator interpolator = this.A;
        if (interpolator instanceof o) {
            fA = ((o) interpolator).a();
        }
        n nVar = this.I.get(view);
        if ((i3 & 1) == 0) {
            nVar.l(f5, view.getWidth(), view.getHeight(), f3, f4, fArr);
        } else {
            nVar.g(f5, f3, f4, fArr);
        }
        if (i3 < 2) {
            fArr[0] = fArr[0] * fA;
            fArr[1] = fArr[1] * fA;
        }
    }

    public final boolean g0(float f3, float f4, View view, MotionEvent motionEvent) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i3 = 0; i3 < childCount; i3++) {
                if (g0(view.getLeft() + f3, view.getTop() + f4, viewGroup.getChildAt(i3), motionEvent)) {
                    return true;
                }
            }
        }
        this.J0.set(view.getLeft() + f3, view.getTop() + f4, f3 + view.getRight(), f4 + view.getBottom());
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
        q qVar = this.f762z;
        if (qVar == null) {
            return null;
        }
        return qVar.k();
    }

    public int getCurrentState() {
        return this.D;
    }

    public ArrayList<q.b> getDefinedTransitions() {
        q qVar = this.f762z;
        if (qVar == null) {
            return null;
        }
        return qVar.l();
    }

    public u.b getDesignTool() {
        if (this.f737a0 == null) {
            this.f737a0 = new u.b(this);
        }
        return this.f737a0;
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
        if (this.f762z != null) {
            this.K = r0.m() / 1000.0f;
        }
        return (long) (this.K * 1000.0f);
    }

    public float getVelocity() {
        return this.B;
    }

    @Override // l0.l
    public void h(View view, View view2, int i3, int i4) {
    }

    public final void h0(AttributeSet attributeSet) {
        q qVar;
        f736y = isInEditMode();
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.MotionLayout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            boolean z3 = true;
            for (int i3 = 0; i3 < indexCount; i3++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i3);
                if (index == R$styleable.MotionLayout_layoutDescription) {
                    this.f762z = new q(getContext(), this, typedArrayObtainStyledAttributes.getResourceId(index, -1));
                } else if (index == R$styleable.MotionLayout_currentState) {
                    this.D = typedArrayObtainStyledAttributes.getResourceId(index, -1);
                } else if (index == R$styleable.MotionLayout_motionProgress) {
                    this.O = typedArrayObtainStyledAttributes.getFloat(index, 0.0f);
                    this.Q = true;
                } else if (index == R$styleable.MotionLayout_applyMotionScene) {
                    z3 = typedArrayObtainStyledAttributes.getBoolean(index, z3);
                } else if (index == R$styleable.MotionLayout_showPaths) {
                    if (this.V == 0) {
                        this.V = typedArrayObtainStyledAttributes.getBoolean(index, false) ? 2 : 0;
                    }
                } else if (index == R$styleable.MotionLayout_motionDebug) {
                    this.V = typedArrayObtainStyledAttributes.getInt(index, 0);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
            if (this.f762z == null) {
                Log.e("MotionLayout", "WARNING NO app:layoutDescription tag");
            }
            if (!z3) {
                this.f762z = null;
            }
        }
        if (this.V != 0) {
            U();
        }
        if (this.D != -1 || (qVar = this.f762z) == null) {
            return;
        }
        this.D = qVar.x();
        this.C = this.f762z.x();
        this.E = this.f762z.n();
    }

    @Override // l0.l
    public void i(View view, int i3) {
        q qVar = this.f762z;
        if (qVar == null) {
            return;
        }
        float f3 = this.f744h0;
        float f4 = this.f747k0;
        qVar.G(f3 / f4, this.f745i0 / f4);
    }

    public boolean i0() {
        return this.H;
    }

    @Override // android.view.View
    public boolean isAttachedToWindow() {
        return Build.VERSION.SDK_INT >= 19 ? super.isAttachedToWindow() : getWindowToken() != null;
    }

    @Override // l0.l
    public void j(View view, int i3, int i4, int[] iArr, int i5) {
        q.b bVar;
        t tVarB;
        int iK;
        q qVar = this.f762z;
        if (qVar == null || (bVar = qVar.f5245c) == null || !bVar.C()) {
            return;
        }
        q.b bVar2 = this.f762z.f5245c;
        if (bVar2 == null || !bVar2.C() || (tVarB = bVar2.B()) == null || (iK = tVarB.k()) == -1 || view.getId() == iK) {
            q qVar2 = this.f762z;
            if (qVar2 != null && qVar2.t()) {
                float f3 = this.L;
                if ((f3 == 1.0f || f3 == 0.0f) && view.canScrollVertically(-1)) {
                    return;
                }
            }
            if (bVar2.B() != null && (this.f762z.f5245c.B().d() & 1) != 0) {
                float fU = this.f762z.u(i3, i4);
                float f4 = this.M;
                if ((f4 <= 0.0f && fU < 0.0f) || (f4 >= 1.0f && fU > 0.0f)) {
                    if (Build.VERSION.SDK_INT >= 21) {
                        view.setNestedScrollingEnabled(false);
                        view.post(new a(view));
                        return;
                    }
                    return;
                }
            }
            float f5 = this.L;
            long nanoTime = getNanoTime();
            float f6 = i3;
            this.f744h0 = f6;
            float f7 = i4;
            this.f745i0 = f7;
            this.f747k0 = (float) ((nanoTime - this.f746j0) * 1.0E-9d);
            this.f746j0 = nanoTime;
            this.f762z.F(f6, f7);
            if (f5 != this.L) {
                iArr[0] = i3;
                iArr[1] = i4;
            }
            Y(false);
            if (iArr[0] == 0 && iArr[1] == 0) {
                return;
            }
            this.f743g0 = true;
        }
    }

    public f j0() {
        return g.f();
    }

    public final void k0() {
        q qVar = this.f762z;
        if (qVar == null) {
            return;
        }
        if (qVar.f(this, this.D)) {
            requestLayout();
            return;
        }
        int i3 = this.D;
        if (i3 != -1) {
            this.f762z.e(this, i3);
        }
        if (this.f762z.Q()) {
            this.f762z.O();
        }
    }

    public final void l0() {
        ArrayList<i> arrayList;
        if (this.S == null && ((arrayList = this.f751o0) == null || arrayList.isEmpty())) {
            return;
        }
        this.f757u0 = false;
        for (Integer num : this.L0) {
            i iVar = this.S;
            if (iVar != null) {
                iVar.d(this, num.intValue());
            }
            ArrayList<i> arrayList2 = this.f751o0;
            if (arrayList2 != null) {
                Iterator<i> it = arrayList2.iterator();
                while (it.hasNext()) {
                    it.next().d(this, num.intValue());
                }
            }
        }
        this.L0.clear();
    }

    @Override // l0.m
    public void m(View view, int i3, int i4, int i5, int i6, int i7, int[] iArr) {
        if (this.f743g0 || i3 != 0 || i4 != 0) {
            iArr[0] = iArr[0] + i5;
            iArr[1] = iArr[1] + i6;
        }
        this.f743g0 = false;
    }

    public void m0() {
        this.H0.g();
        invalidate();
    }

    @Override // l0.l
    public void n(View view, int i3, int i4, int i5, int i6, int i7) {
    }

    public void n0(float f3, float f4) {
        if (isAttachedToWindow()) {
            setProgress(f3);
            setState(TransitionState.MOVING);
            this.B = f4;
            T(1.0f);
            return;
        }
        if (this.F0 == null) {
            this.F0 = new h();
        }
        this.F0.e(f3);
        this.F0.h(f4);
    }

    @Override // l0.l
    public boolean o(View view, View view2, int i3, int i4) {
        q.b bVar;
        q qVar = this.f762z;
        return (qVar == null || (bVar = qVar.f5245c) == null || bVar.B() == null || (this.f762z.f5245c.B().d() & 2) != 0) ? false : true;
    }

    public void o0(int i3, int i4, int i5) {
        setState(TransitionState.SETUP);
        this.D = i3;
        this.C = -1;
        this.E = -1;
        y.a aVar = this.f988l;
        if (aVar != null) {
            aVar.d(i3, i4, i5);
            return;
        }
        q qVar = this.f762z;
        if (qVar != null) {
            qVar.i(i3).d(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        int i3;
        super.onAttachedToWindow();
        q qVar = this.f762z;
        if (qVar != null && (i3 = this.D) != -1) {
            y.b bVarI = qVar.i(i3);
            this.f762z.J(this);
            if (bVarI != null) {
                bVarI.d(this);
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
        q.b bVar;
        t tVarB;
        int iK;
        RectF rectFJ;
        q qVar = this.f762z;
        if (qVar != null && this.H && (bVar = qVar.f5245c) != null && bVar.C() && (tVarB = bVar.B()) != null && ((motionEvent.getAction() != 0 || (rectFJ = tVarB.j(this, new RectF())) == null || rectFJ.contains(motionEvent.getX(), motionEvent.getY())) && (iK = tVarB.k()) != -1)) {
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
    public void onLayout(boolean z3, int i3, int i4, int i5, int i6) {
        this.E0 = true;
        try {
            if (this.f762z == null) {
                super.onLayout(z3, i3, i4, i5, i6);
                return;
            }
            int i7 = i5 - i3;
            int i8 = i6 - i4;
            if (this.f741e0 != i7 || this.f742f0 != i8) {
                m0();
                Y(true);
            }
            this.f741e0 = i7;
            this.f742f0 = i8;
            this.f739c0 = i7;
            this.f740d0 = i8;
        } finally {
            this.E0 = false;
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View
    public void onMeasure(int i3, int i4) {
        if (this.f762z == null) {
            super.onMeasure(i3, i4);
            return;
        }
        boolean z3 = false;
        boolean z4 = (this.F == i3 && this.G == i4) ? false : true;
        if (this.I0) {
            this.I0 = false;
            k0();
            l0();
            z4 = true;
        }
        if (this.f985i) {
            z4 = true;
        }
        this.F = i3;
        this.G = i4;
        int iX = this.f762z.x();
        int iN = this.f762z.n();
        if ((z4 || this.H0.e(iX, iN)) && this.C != -1) {
            super.onMeasure(i3, i4);
            this.H0.d(this.f980d, this.f762z.i(iX), this.f762z.i(iN));
            this.H0.g();
            this.H0.h(iX, iN);
        } else {
            z3 = true;
        }
        if (this.f758v0 || z3) {
            int paddingTop = getPaddingTop() + getPaddingBottom();
            int iQ = this.f980d.Q() + getPaddingLeft() + getPaddingRight();
            int iW = this.f980d.w() + paddingTop;
            int i5 = this.A0;
            if (i5 == Integer.MIN_VALUE || i5 == 0) {
                iQ = (int) (this.f759w0 + (this.C0 * (this.f761y0 - r7)));
                requestLayout();
            }
            int i6 = this.B0;
            if (i6 == Integer.MIN_VALUE || i6 == 0) {
                iW = (int) (this.f760x0 + (this.C0 * (this.f763z0 - r7)));
                requestLayout();
            }
            setMeasuredDimension(iQ, iW);
        }
        Z();
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, l0.n
    public boolean onNestedFling(View view, float f3, float f4, boolean z3) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, l0.n
    public boolean onNestedPreFling(View view, float f3, float f4) {
        return false;
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i3) {
        q qVar = this.f762z;
        if (qVar != null) {
            qVar.L(p());
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        q qVar = this.f762z;
        if (qVar == null || !this.H || !qVar.Q()) {
            return super.onTouchEvent(motionEvent);
        }
        q.b bVar = this.f762z.f5245c;
        if (bVar != null && !bVar.C()) {
            return super.onTouchEvent(motionEvent);
        }
        this.f762z.H(motionEvent, getCurrentState(), this);
        return true;
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public void onViewAdded(View view) {
        super.onViewAdded(view);
        if (view instanceof MotionHelper) {
            MotionHelper motionHelper = (MotionHelper) view;
            if (this.f751o0 == null) {
                this.f751o0 = new ArrayList<>();
            }
            this.f751o0.add(motionHelper);
            if (motionHelper.w()) {
                if (this.f749m0 == null) {
                    this.f749m0 = new ArrayList<>();
                }
                this.f749m0.add(motionHelper);
            }
            if (motionHelper.v()) {
                if (this.f750n0 == null) {
                    this.f750n0 = new ArrayList<>();
                }
                this.f750n0.add(motionHelper);
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        ArrayList<MotionHelper> arrayList = this.f749m0;
        if (arrayList != null) {
            arrayList.remove(view);
        }
        ArrayList<MotionHelper> arrayList2 = this.f750n0;
        if (arrayList2 != null) {
            arrayList2.remove(view);
        }
    }

    public void p0(int i3, int i4) {
        if (!isAttachedToWindow()) {
            if (this.F0 == null) {
                this.F0 = new h();
            }
            this.F0.f(i3);
            this.F0.d(i4);
            return;
        }
        q qVar = this.f762z;
        if (qVar != null) {
            this.C = i3;
            this.E = i4;
            qVar.M(i3, i4);
            this.H0.d(this.f980d, this.f762z.i(i3), this.f762z.i(i4));
            m0();
            this.M = 0.0f;
            t0();
        }
    }

    public final void q0() {
        int childCount = getChildCount();
        this.H0.a();
        boolean z3 = true;
        this.Q = true;
        int width = getWidth();
        int height = getHeight();
        int iH = this.f762z.h();
        int i3 = 0;
        if (iH != -1) {
            for (int i4 = 0; i4 < childCount; i4++) {
                n nVar = this.I.get(getChildAt(i4));
                if (nVar != null) {
                    nVar.r(iH);
                }
            }
        }
        for (int i5 = 0; i5 < childCount; i5++) {
            n nVar2 = this.I.get(getChildAt(i5));
            if (nVar2 != null) {
                this.f762z.q(nVar2);
                nVar2.v(width, height, this.K, getNanoTime());
            }
        }
        float fW = this.f762z.w();
        if (fW != 0.0f) {
            boolean z4 = ((double) fW) < 0.0d;
            float fAbs = Math.abs(fW);
            float fMax = -3.4028235E38f;
            float fMin = Float.MAX_VALUE;
            float fMax2 = -3.4028235E38f;
            float fMin2 = Float.MAX_VALUE;
            int i6 = 0;
            while (true) {
                if (i6 >= childCount) {
                    z3 = false;
                    break;
                }
                n nVar3 = this.I.get(getChildAt(i6));
                if (!Float.isNaN(nVar3.f5211k)) {
                    break;
                }
                float fI = nVar3.i();
                float fJ = nVar3.j();
                float f3 = z4 ? fJ - fI : fJ + fI;
                fMin2 = Math.min(fMin2, f3);
                fMax2 = Math.max(fMax2, f3);
                i6++;
            }
            if (!z3) {
                while (i3 < childCount) {
                    n nVar4 = this.I.get(getChildAt(i3));
                    float fI2 = nVar4.i();
                    float fJ2 = nVar4.j();
                    float f4 = z4 ? fJ2 - fI2 : fJ2 + fI2;
                    nVar4.f5213m = 1.0f / (1.0f - fAbs);
                    nVar4.f5212l = fAbs - (((f4 - fMin2) * fAbs) / (fMax2 - fMin2));
                    i3++;
                }
                return;
            }
            for (int i7 = 0; i7 < childCount; i7++) {
                n nVar5 = this.I.get(getChildAt(i7));
                if (!Float.isNaN(nVar5.f5211k)) {
                    fMin = Math.min(fMin, nVar5.f5211k);
                    fMax = Math.max(fMax, nVar5.f5211k);
                }
            }
            while (i3 < childCount) {
                n nVar6 = this.I.get(getChildAt(i3));
                if (!Float.isNaN(nVar6.f5211k)) {
                    nVar6.f5213m = 1.0f / (1.0f - fAbs);
                    if (z4) {
                        nVar6.f5212l = fAbs - (((fMax - nVar6.f5211k) / (fMax - fMin)) * fAbs);
                    } else {
                        nVar6.f5212l = fAbs - (((nVar6.f5211k - fMin) * fAbs) / (fMax - fMin));
                    }
                }
                i3++;
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout
    public void r(int i3) {
        this.f988l = null;
    }

    public void r0(int i3, float f3, float f4) {
        if (this.f762z == null || this.M == f3) {
            return;
        }
        this.X = true;
        this.J = getNanoTime();
        float fM = this.f762z.m() / 1000.0f;
        this.K = fM;
        this.O = f3;
        this.Q = true;
        if (i3 == 0 || i3 == 1 || i3 == 2) {
            if (i3 == 1) {
                f3 = 0.0f;
            } else if (i3 == 2) {
                f3 = 1.0f;
            }
            this.Y.c(this.M, f3, f4, fM, this.f762z.r(), this.f762z.s());
            int i4 = this.D;
            this.O = f3;
            this.D = i4;
            this.A = this.Y;
        } else if (i3 == 4) {
            this.Z.b(f4, this.M, this.f762z.r());
            this.A = this.Z;
        } else if (i3 == 5) {
            if (w0(f4, this.M, this.f762z.r())) {
                this.Z.b(f4, this.M, this.f762z.r());
                this.A = this.Z;
            } else {
                this.Y.c(this.M, f3, f4, this.K, this.f762z.r(), this.f762z.s());
                this.B = 0.0f;
                int i5 = this.D;
                this.O = f3;
                this.D = i5;
                this.A = this.Y;
            }
        }
        this.P = false;
        this.J = getNanoTime();
        invalidate();
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View, android.view.ViewParent
    public void requestLayout() {
        q qVar;
        q.b bVar;
        if (this.f758v0 || this.D != -1 || (qVar = this.f762z) == null || (bVar = qVar.f5245c) == null || bVar.z() != 0) {
            super.requestLayout();
        }
    }

    public void s0() {
        T(1.0f);
    }

    public void setDebugMode(int i3) {
        this.V = i3;
        invalidate();
    }

    public void setInteractionEnabled(boolean z3) {
        this.H = z3;
    }

    public void setInterpolatedProgress(float f3) {
        if (this.f762z != null) {
            setState(TransitionState.MOVING);
            Interpolator interpolatorP = this.f762z.p();
            if (interpolatorP != null) {
                setProgress(interpolatorP.getInterpolation(f3));
                return;
            }
        }
        setProgress(f3);
    }

    public void setOnHide(float f3) {
        ArrayList<MotionHelper> arrayList = this.f750n0;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i3 = 0; i3 < size; i3++) {
                this.f750n0.get(i3).setProgress(f3);
            }
        }
    }

    public void setOnShow(float f3) {
        ArrayList<MotionHelper> arrayList = this.f749m0;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i3 = 0; i3 < size; i3++) {
                this.f749m0.get(i3).setProgress(f3);
            }
        }
    }

    public void setProgress(float f3) {
        if (!isAttachedToWindow()) {
            if (this.F0 == null) {
                this.F0 = new h();
            }
            this.F0.e(f3);
            return;
        }
        if (f3 <= 0.0f) {
            this.D = this.C;
            if (this.M == 0.0f) {
                setState(TransitionState.FINISHED);
            }
        } else if (f3 >= 1.0f) {
            this.D = this.E;
            if (this.M == 1.0f) {
                setState(TransitionState.FINISHED);
            }
        } else {
            this.D = -1;
            setState(TransitionState.MOVING);
        }
        if (this.f762z == null) {
            return;
        }
        this.P = true;
        this.O = f3;
        this.L = f3;
        this.N = -1L;
        this.J = -1L;
        this.A = null;
        this.Q = true;
        invalidate();
    }

    public void setScene(q qVar) {
        this.f762z = qVar;
        qVar.L(p());
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
        int i3 = b.f766a[transitionState3.ordinal()];
        if (i3 != 1 && i3 != 2) {
            if (i3 == 3 && transitionState == transitionState2) {
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

    public void setTransition(int i3) {
        if (this.f762z != null) {
            q.b bVarE0 = e0(i3);
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
            float f3 = Float.NaN;
            int i4 = this.D;
            if (i4 == this.C) {
                f3 = 0.0f;
            } else if (i4 == this.E) {
                f3 = 1.0f;
            }
            this.f762z.N(bVarE0);
            this.H0.d(this.f980d, this.f762z.i(this.C), this.f762z.i(this.E));
            m0();
            this.M = Float.isNaN(f3) ? 0.0f : f3;
            if (!Float.isNaN(f3)) {
                setProgress(f3);
                return;
            }
            Log.v("MotionLayout", u.a.a() + " transitionToStart ");
            t0();
        }
    }

    public void setTransitionDuration(int i3) {
        q qVar = this.f762z;
        if (qVar == null) {
            Log.e("MotionLayout", "MotionScene not defined");
        } else {
            qVar.K(i3);
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
        return u.a.b(context, this.C) + "->" + u.a.b(context, this.E) + " (pos:" + this.M + " Dpos/Dt:" + this.B;
    }

    public void u0(int i3) {
        if (isAttachedToWindow()) {
            v0(i3, -1, -1);
            return;
        }
        if (this.F0 == null) {
            this.F0 = new h();
        }
        this.F0.d(i3);
    }

    public void v0(int i3, int i4, int i5) {
        y.d dVar;
        int iA;
        q qVar = this.f762z;
        if (qVar != null && (dVar = qVar.f5244b) != null && (iA = dVar.a(this.D, i3, i4, i5)) != -1) {
            i3 = iA;
        }
        int i6 = this.D;
        if (i6 == i3) {
            return;
        }
        if (this.C == i3) {
            T(0.0f);
            return;
        }
        if (this.E == i3) {
            T(1.0f);
            return;
        }
        this.E = i3;
        if (i6 != -1) {
            p0(i6, i3);
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
        this.K = this.f762z.m() / 1000.0f;
        this.C = -1;
        this.f762z.M(-1, this.E);
        this.f762z.x();
        int childCount = getChildCount();
        this.I.clear();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            this.I.put(childAt, new n(childAt));
        }
        this.Q = true;
        this.H0.d(this.f980d, null, this.f762z.i(i3));
        m0();
        this.H0.a();
        X();
        int width = getWidth();
        int height = getHeight();
        for (int i8 = 0; i8 < childCount; i8++) {
            n nVar = this.I.get(getChildAt(i8));
            this.f762z.q(nVar);
            nVar.v(width, height, this.K, getNanoTime());
        }
        float fW = this.f762z.w();
        if (fW != 0.0f) {
            float fMin = Float.MAX_VALUE;
            float fMax = -3.4028235E38f;
            for (int i9 = 0; i9 < childCount; i9++) {
                n nVar2 = this.I.get(getChildAt(i9));
                float fJ = nVar2.j() + nVar2.i();
                fMin = Math.min(fMin, fJ);
                fMax = Math.max(fMax, fJ);
            }
            for (int i10 = 0; i10 < childCount; i10++) {
                n nVar3 = this.I.get(getChildAt(i10));
                float fI = nVar3.i();
                float fJ2 = nVar3.j();
                nVar3.f5213m = 1.0f / (1.0f - fW);
                nVar3.f5212l = fW - ((((fI + fJ2) - fMin) * fW) / (fMax - fMin));
            }
        }
        this.L = 0.0f;
        this.M = 0.0f;
        this.Q = true;
        invalidate();
    }

    public void setTransition(q.b bVar) {
        this.f762z.N(bVar);
        setState(TransitionState.SETUP);
        if (this.D == this.f762z.n()) {
            this.M = 1.0f;
            this.L = 1.0f;
            this.O = 1.0f;
        } else {
            this.M = 0.0f;
            this.L = 0.0f;
            this.O = 0.0f;
        }
        this.N = bVar.D(1) ? -1L : getNanoTime();
        int iX = this.f762z.x();
        int iN = this.f762z.n();
        if (iX == this.C && iN == this.E) {
            return;
        }
        this.C = iX;
        this.E = iN;
        this.f762z.M(iX, iN);
        this.H0.d(this.f980d, this.f762z.i(this.C), this.f762z.i(this.E));
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
        this.Y = new t.g();
        this.Z = new c();
        this.f738b0 = true;
        this.f743g0 = false;
        this.f748l0 = false;
        this.f749m0 = null;
        this.f750n0 = null;
        this.f751o0 = null;
        this.f752p0 = 0;
        this.f753q0 = -1L;
        this.f754r0 = 0.0f;
        this.f755s0 = 0;
        this.f756t0 = 0.0f;
        this.f757u0 = false;
        this.f758v0 = false;
        this.D0 = new u.e();
        this.E0 = false;
        this.G0 = TransitionState.UNDEFINED;
        this.H0 = new e();
        this.I0 = false;
        this.J0 = new RectF();
        this.K0 = null;
        this.L0 = new ArrayList<>();
        h0(attributeSet);
    }

    public MotionLayout(Context context, AttributeSet attributeSet, int i3) {
        super(context, attributeSet, i3);
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
        this.Y = new t.g();
        this.Z = new c();
        this.f738b0 = true;
        this.f743g0 = false;
        this.f748l0 = false;
        this.f749m0 = null;
        this.f750n0 = null;
        this.f751o0 = null;
        this.f752p0 = 0;
        this.f753q0 = -1L;
        this.f754r0 = 0.0f;
        this.f755s0 = 0;
        this.f756t0 = 0.0f;
        this.f757u0 = false;
        this.f758v0 = false;
        this.D0 = new u.e();
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
