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
import b.e.a.b.n;
import b.e.a.b.o;
import b.e.a.b.q;
import b.e.a.b.t;
import b.e.b.j.j;
import b.f.m.m;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class MotionLayout extends ConstraintLayout implements m {
    public static boolean W3;
    public int A4;
    public int B4;
    public int C4;
    public int D4;
    public boolean E4;
    public float F4;
    public float G4;
    public long H4;
    public float I4;
    public boolean J4;
    public ArrayList<MotionHelper> K4;
    public ArrayList<MotionHelper> L4;
    public ArrayList<i> M4;
    public int N4;
    public long O4;
    public float P4;
    public int Q4;
    public float R4;
    public boolean S4;
    public boolean T4;
    public int U4;
    public int V4;
    public int W4;
    public q X3;
    public int X4;
    public Interpolator Y3;
    public int Y4;
    public float Z3;
    public int Z4;
    public int a4;
    public float a5;
    public int b4;
    public b.e.a.b.e b5;
    public int c4;
    public boolean c5;
    public int d4;
    public h d5;
    public int e4;
    public TransitionState e5;
    public boolean f4;
    public e f5;
    public HashMap<View, n> g4;
    public boolean g5;
    public long h4;
    public RectF h5;
    public float i4;
    public View i5;
    public float j4;
    public ArrayList<Integer> j5;
    public float k4;
    public long l4;
    public float m4;
    public boolean n4;
    public boolean o4;
    public boolean p4;
    public i q4;
    public float r4;
    public float s4;
    public int t4;
    public d u4;
    public boolean v4;
    public b.e.a.a.g w4;
    public c x4;
    public b.e.a.b.b y4;
    public boolean z4;

    public enum TransitionState {
        UNDEFINED,
        SETUP,
        MOVING,
        FINISHED
    }

    public class a implements Runnable {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ View f285c;

        public a(View view) {
            this.f285c = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f285c.setNestedScrollingEnabled(true);
        }
    }

    public static /* synthetic */ class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f287a;

        static {
            int[] iArr = new int[TransitionState.values().length];
            f287a = iArr;
            try {
                iArr[TransitionState.UNDEFINED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f287a[TransitionState.SETUP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f287a[TransitionState.MOVING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f287a[TransitionState.FINISHED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public class c extends o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float f288a = 0.0f;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f289b = 0.0f;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f290c;

        public c() {
        }

        @Override // b.e.a.b.o
        public float a() {
            return MotionLayout.this.Z3;
        }

        public void b(float f2, float f3, float f4) {
            this.f288a = f2;
            this.f289b = f3;
            this.f290c = f4;
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f2) {
            float f3;
            float f4;
            float f5 = this.f288a;
            if (f5 > 0.0f) {
                float f6 = this.f290c;
                if (f5 / f6 < f2) {
                    f2 = f5 / f6;
                }
                MotionLayout.this.Z3 = f5 - (f6 * f2);
                f3 = (f5 * f2) - (((f6 * f2) * f2) / 2.0f);
                f4 = this.f289b;
            } else {
                float f7 = this.f290c;
                if ((-f5) / f7 < f2) {
                    f2 = (-f5) / f7;
                }
                MotionLayout.this.Z3 = (f7 * f2) + f5;
                f3 = (f5 * f2) + (((f7 * f2) * f2) / 2.0f);
                f4 = this.f289b;
            }
            return f3 + f4;
        }
    }

    public class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float[] f292a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int[] f293b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float[] f294c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Path f295d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Paint f296e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public Paint f297f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public Paint f298g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public Paint f299h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public Paint f300i;
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
            this.f296e = paint;
            paint.setAntiAlias(true);
            this.f296e.setColor(-21965);
            this.f296e.setStrokeWidth(2.0f);
            this.f296e.setStyle(Paint.Style.STROKE);
            Paint paint2 = new Paint();
            this.f297f = paint2;
            paint2.setAntiAlias(true);
            this.f297f.setColor(-2067046);
            this.f297f.setStrokeWidth(2.0f);
            this.f297f.setStyle(Paint.Style.STROKE);
            Paint paint3 = new Paint();
            this.f298g = paint3;
            paint3.setAntiAlias(true);
            this.f298g.setColor(-13391360);
            this.f298g.setStrokeWidth(2.0f);
            this.f298g.setStyle(Paint.Style.STROKE);
            Paint paint4 = new Paint();
            this.f299h = paint4;
            paint4.setAntiAlias(true);
            this.f299h.setColor(-13391360);
            this.f299h.setTextSize(MotionLayout.this.getContext().getResources().getDisplayMetrics().density * 12.0f);
            this.j = new float[8];
            Paint paint5 = new Paint();
            this.f300i = paint5;
            paint5.setAntiAlias(true);
            DashPathEffect dashPathEffect = new DashPathEffect(new float[]{4.0f, 8.0f}, 0.0f);
            this.p = dashPathEffect;
            this.f298g.setPathEffect(dashPathEffect);
            this.f294c = new float[100];
            this.f293b = new int[50];
            if (this.s) {
                this.f296e.setStrokeWidth(8.0f);
                this.f300i.setStrokeWidth(8.0f);
                this.f297f.setStrokeWidth(8.0f);
                this.t = 4;
            }
        }

        public void a(Canvas canvas, HashMap<View, n> map, int i2, int i3) {
            if (map == null || map.size() == 0) {
                return;
            }
            canvas.save();
            if (!MotionLayout.this.isInEditMode() && (i3 & 1) == 2) {
                String str = MotionLayout.this.getContext().getResources().getResourceName(MotionLayout.this.c4) + ":" + MotionLayout.this.getProgress();
                canvas.drawText(str, 10.0f, MotionLayout.this.getHeight() - 30, this.f299h);
                canvas.drawText(str, 11.0f, MotionLayout.this.getHeight() - 29, this.f296e);
            }
            for (n nVar : map.values()) {
                int iH = nVar.h();
                if (i3 > 0 && iH == 0) {
                    iH = 1;
                }
                if (iH != 0) {
                    this.q = nVar.c(this.f294c, this.f293b);
                    if (iH >= 1) {
                        int i4 = i2 / 16;
                        float[] fArr = this.f292a;
                        if (fArr == null || fArr.length != i4 * 2) {
                            this.f292a = new float[i4 * 2];
                            this.f295d = new Path();
                        }
                        int i5 = this.t;
                        canvas.translate(i5, i5);
                        this.f296e.setColor(1996488704);
                        this.f300i.setColor(1996488704);
                        this.f297f.setColor(1996488704);
                        this.f298g.setColor(1996488704);
                        nVar.d(this.f292a, i4);
                        b(canvas, iH, this.q, nVar);
                        this.f296e.setColor(-21965);
                        this.f297f.setColor(-2067046);
                        this.f300i.setColor(-2067046);
                        this.f298g.setColor(-13391360);
                        int i6 = this.t;
                        canvas.translate(-i6, -i6);
                        b(canvas, iH, this.q, nVar);
                        if (iH == 5) {
                            j(canvas, nVar);
                        }
                    }
                }
            }
            canvas.restore();
        }

        public void b(Canvas canvas, int i2, int i3, n nVar) {
            if (i2 == 4) {
                d(canvas);
            }
            if (i2 == 2) {
                g(canvas);
            }
            if (i2 == 3) {
                e(canvas);
            }
            c(canvas);
            k(canvas, i2, i3, nVar);
        }

        public final void c(Canvas canvas) {
            canvas.drawLines(this.f292a, this.f296e);
        }

        public final void d(Canvas canvas) {
            boolean z = false;
            boolean z2 = false;
            for (int i2 = 0; i2 < this.q; i2++) {
                int[] iArr = this.f293b;
                if (iArr[i2] == 1) {
                    z = true;
                }
                if (iArr[i2] == 2) {
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
            float[] fArr = this.f292a;
            float f2 = fArr[0];
            float f3 = fArr[1];
            float f4 = fArr[fArr.length - 2];
            float f5 = fArr[fArr.length - 1];
            canvas.drawLine(Math.min(f2, f4), Math.max(f3, f5), Math.max(f2, f4), Math.max(f3, f5), this.f298g);
            canvas.drawLine(Math.min(f2, f4), Math.min(f3, f5), Math.min(f2, f4), Math.max(f3, f5), this.f298g);
        }

        public final void f(Canvas canvas, float f2, float f3) {
            float[] fArr = this.f292a;
            float f4 = fArr[0];
            float f5 = fArr[1];
            float f6 = fArr[fArr.length - 2];
            float f7 = fArr[fArr.length - 1];
            float fMin = Math.min(f4, f6);
            float fMax = Math.max(f5, f7);
            float fMin2 = f2 - Math.min(f4, f6);
            float fMax2 = Math.max(f5, f7) - f3;
            String str = "" + (((int) (((double) ((fMin2 * 100.0f) / Math.abs(f6 - f4))) + 0.5d)) / 100.0f);
            l(str, this.f299h);
            canvas.drawText(str, ((fMin2 / 2.0f) - (this.r.width() / 2)) + fMin, f3 - 20.0f, this.f299h);
            canvas.drawLine(f2, f3, Math.min(f4, f6), f3, this.f298g);
            String str2 = "" + (((int) (((double) ((fMax2 * 100.0f) / Math.abs(f7 - f5))) + 0.5d)) / 100.0f);
            l(str2, this.f299h);
            canvas.drawText(str2, f2 + 5.0f, fMax - ((fMax2 / 2.0f) - (this.r.height() / 2)), this.f299h);
            canvas.drawLine(f2, f3, f2, Math.max(f5, f7), this.f298g);
        }

        public final void g(Canvas canvas) {
            float[] fArr = this.f292a;
            canvas.drawLine(fArr[0], fArr[1], fArr[fArr.length - 2], fArr[fArr.length - 1], this.f298g);
        }

        public final void h(Canvas canvas, float f2, float f3) {
            float[] fArr = this.f292a;
            float f4 = fArr[0];
            float f5 = fArr[1];
            float f6 = fArr[fArr.length - 2];
            float f7 = fArr[fArr.length - 1];
            float fHypot = (float) Math.hypot(f4 - f6, f5 - f7);
            float f8 = f6 - f4;
            float f9 = f7 - f5;
            float f10 = (((f2 - f4) * f8) + ((f3 - f5) * f9)) / (fHypot * fHypot);
            float f11 = f4 + (f8 * f10);
            float f12 = f5 + (f10 * f9);
            Path path = new Path();
            path.moveTo(f2, f3);
            path.lineTo(f11, f12);
            float fHypot2 = (float) Math.hypot(f11 - f2, f12 - f3);
            String str = "" + (((int) ((fHypot2 * 100.0f) / fHypot)) / 100.0f);
            l(str, this.f299h);
            canvas.drawTextOnPath(str, path, (fHypot2 / 2.0f) - (this.r.width() / 2), -20.0f, this.f299h);
            canvas.drawLine(f2, f3, f11, f12, this.f298g);
        }

        public final void i(Canvas canvas, float f2, float f3, int i2, int i3) {
            String str = "" + (((int) (((double) (((f2 - (i2 / 2)) * 100.0f) / (MotionLayout.this.getWidth() - i2))) + 0.5d)) / 100.0f);
            l(str, this.f299h);
            canvas.drawText(str, ((f2 / 2.0f) - (this.r.width() / 2)) + 0.0f, f3 - 20.0f, this.f299h);
            canvas.drawLine(f2, f3, Math.min(0.0f, 1.0f), f3, this.f298g);
            String str2 = "" + (((int) (((double) (((f3 - (i3 / 2)) * 100.0f) / (MotionLayout.this.getHeight() - i3))) + 0.5d)) / 100.0f);
            l(str2, this.f299h);
            canvas.drawText(str2, f2 + 5.0f, 0.0f - ((f3 / 2.0f) - (this.r.height() / 2)), this.f299h);
            canvas.drawLine(f2, f3, f2, Math.max(0.0f, 1.0f), this.f298g);
        }

        public final void j(Canvas canvas, n nVar) {
            this.f295d.reset();
            for (int i2 = 0; i2 <= 50; i2++) {
                nVar.e(i2 / 50, this.j, 0);
                Path path = this.f295d;
                float[] fArr = this.j;
                path.moveTo(fArr[0], fArr[1]);
                Path path2 = this.f295d;
                float[] fArr2 = this.j;
                path2.lineTo(fArr2[2], fArr2[3]);
                Path path3 = this.f295d;
                float[] fArr3 = this.j;
                path3.lineTo(fArr3[4], fArr3[5]);
                Path path4 = this.f295d;
                float[] fArr4 = this.j;
                path4.lineTo(fArr4[6], fArr4[7]);
                this.f295d.close();
            }
            this.f296e.setColor(1140850688);
            canvas.translate(2.0f, 2.0f);
            canvas.drawPath(this.f295d, this.f296e);
            canvas.translate(-2.0f, -2.0f);
            this.f296e.setColor(-65536);
            canvas.drawPath(this.f295d, this.f296e);
        }

        public final void k(Canvas canvas, int i2, int i3, n nVar) {
            int width;
            int height;
            int i4;
            float f2;
            float f3;
            View view = nVar.f1294a;
            if (view != null) {
                width = view.getWidth();
                height = nVar.f1294a.getHeight();
            } else {
                width = 0;
                height = 0;
            }
            for (int i5 = 1; i5 < i3 - 1; i5++) {
                if (i2 != 4 || this.f293b[i5 - 1] != 0) {
                    float[] fArr = this.f294c;
                    int i6 = i5 * 2;
                    float f4 = fArr[i6];
                    float f5 = fArr[i6 + 1];
                    this.f295d.reset();
                    this.f295d.moveTo(f4, f5 + 10.0f);
                    this.f295d.lineTo(f4 + 10.0f, f5);
                    this.f295d.lineTo(f4, f5 - 10.0f);
                    this.f295d.lineTo(f4 - 10.0f, f5);
                    this.f295d.close();
                    int i7 = i5 - 1;
                    nVar.k(i7);
                    if (i2 == 4) {
                        int[] iArr = this.f293b;
                        if (iArr[i7] == 1) {
                            h(canvas, f4 - 0.0f, f5 - 0.0f);
                        } else if (iArr[i7] == 2) {
                            f(canvas, f4 - 0.0f, f5 - 0.0f);
                        } else {
                            if (iArr[i7] == 3) {
                                i4 = 3;
                                f2 = f5;
                                f3 = f4;
                                i(canvas, f4 - 0.0f, f5 - 0.0f, width, height);
                            }
                            canvas.drawPath(this.f295d, this.f300i);
                        }
                        i4 = 3;
                        f2 = f5;
                        f3 = f4;
                        canvas.drawPath(this.f295d, this.f300i);
                    } else {
                        i4 = 3;
                        f2 = f5;
                        f3 = f4;
                    }
                    if (i2 == 2) {
                        h(canvas, f3 - 0.0f, f2 - 0.0f);
                    }
                    if (i2 == i4) {
                        f(canvas, f3 - 0.0f, f2 - 0.0f);
                    }
                    if (i2 == 6) {
                        i(canvas, f3 - 0.0f, f2 - 0.0f, width, height);
                    }
                    canvas.drawPath(this.f295d, this.f300i);
                }
            }
            float[] fArr2 = this.f292a;
            if (fArr2.length > 1) {
                canvas.drawCircle(fArr2[0], fArr2[1], 8.0f, this.f297f);
                float[] fArr3 = this.f292a;
                canvas.drawCircle(fArr3[fArr3.length - 2], fArr3[fArr3.length - 1], 8.0f, this.f297f);
            }
        }

        public void l(String str, Paint paint) {
            paint.getTextBounds(str, 0, str.length(), this.r);
        }
    }

    public class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public b.e.b.j.d f301a = new b.e.b.j.d();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public b.e.b.j.d f302b = new b.e.b.j.d();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public b.e.c.b f303c = null;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public b.e.c.b f304d = null;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f305e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f306f;

        public e() {
        }

        public void a() {
            int childCount = MotionLayout.this.getChildCount();
            MotionLayout.this.g4.clear();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = MotionLayout.this.getChildAt(i2);
                MotionLayout.this.g4.put(childAt, new n(childAt));
            }
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt2 = MotionLayout.this.getChildAt(i3);
                n nVar = MotionLayout.this.g4.get(childAt2);
                if (nVar != null) {
                    if (this.f303c != null) {
                        ConstraintWidget constraintWidgetC = c(this.f301a, childAt2);
                        if (constraintWidgetC != null) {
                            nVar.t(constraintWidgetC, this.f303c);
                        } else if (MotionLayout.this.t4 != 0) {
                            Log.e("MotionLayout", b.e.a.b.a.a() + "no widget for  " + b.e.a.b.a.c(childAt2) + " (" + childAt2.getClass().getName() + ")");
                        }
                    }
                    if (this.f304d != null) {
                        ConstraintWidget constraintWidgetC2 = c(this.f302b, childAt2);
                        if (constraintWidgetC2 != null) {
                            nVar.q(constraintWidgetC2, this.f304d);
                        } else if (MotionLayout.this.t4 != 0) {
                            Log.e("MotionLayout", b.e.a.b.a.a() + "no widget for  " + b.e.a.b.a.c(childAt2) + " (" + childAt2.getClass().getName() + ")");
                        }
                    }
                }
            }
        }

        public void b(b.e.b.j.d dVar, b.e.b.j.d dVar2) {
            ArrayList<ConstraintWidget> arrayListL0 = dVar.L0();
            HashMap<ConstraintWidget, ConstraintWidget> map = new HashMap<>();
            map.put(dVar, dVar2);
            dVar2.L0().clear();
            dVar2.l(dVar, map);
            for (ConstraintWidget constraintWidget : arrayListL0) {
                ConstraintWidget aVar = constraintWidget instanceof b.e.b.j.a ? new b.e.b.j.a() : constraintWidget instanceof b.e.b.j.f ? new b.e.b.j.f() : constraintWidget instanceof b.e.b.j.e ? new b.e.b.j.e() : constraintWidget instanceof b.e.b.j.g ? new b.e.b.j.h() : new ConstraintWidget();
                dVar2.a(aVar);
                map.put(constraintWidget, aVar);
            }
            for (ConstraintWidget constraintWidget2 : arrayListL0) {
                map.get(constraintWidget2).l(constraintWidget2, map);
            }
        }

        public ConstraintWidget c(b.e.b.j.d dVar, View view) {
            if (dVar.r() == view) {
                return dVar;
            }
            ArrayList<ConstraintWidget> arrayListL0 = dVar.L0();
            int size = arrayListL0.size();
            for (int i2 = 0; i2 < size; i2++) {
                ConstraintWidget constraintWidget = arrayListL0.get(i2);
                if (constraintWidget.r() == view) {
                    return constraintWidget;
                }
            }
            return null;
        }

        public void d(b.e.b.j.d dVar, b.e.c.b bVar, b.e.c.b bVar2) {
            this.f303c = bVar;
            this.f304d = bVar2;
            this.f301a = new b.e.b.j.d();
            this.f302b = new b.e.b.j.d();
            this.f301a.h1(MotionLayout.this.s.W0());
            this.f302b.h1(MotionLayout.this.s.W0());
            this.f301a.O0();
            this.f302b.O0();
            b(MotionLayout.this.s, this.f301a);
            b(MotionLayout.this.s, this.f302b);
            if (MotionLayout.this.k4 > 0.5d) {
                if (bVar != null) {
                    i(this.f301a, bVar);
                }
                i(this.f302b, bVar2);
            } else {
                i(this.f302b, bVar2);
                if (bVar != null) {
                    i(this.f301a, bVar);
                }
            }
            this.f301a.j1(MotionLayout.this.p());
            this.f301a.l1();
            this.f302b.j1(MotionLayout.this.p());
            this.f302b.l1();
            ViewGroup.LayoutParams layoutParams = MotionLayout.this.getLayoutParams();
            if (layoutParams != null) {
                if (layoutParams.width == -2) {
                    b.e.b.j.d dVar2 = this.f301a;
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    dVar2.m0(dimensionBehaviour);
                    this.f302b.m0(dimensionBehaviour);
                }
                if (layoutParams.height == -2) {
                    b.e.b.j.d dVar3 = this.f301a;
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    dVar3.B0(dimensionBehaviour2);
                    this.f302b.B0(dimensionBehaviour2);
                }
            }
        }

        public boolean e(int i2, int i3) {
            return (i2 == this.f305e && i3 == this.f306f) ? false : true;
        }

        public void f(int i2, int i3) {
            int mode = View.MeasureSpec.getMode(i2);
            int mode2 = View.MeasureSpec.getMode(i3);
            MotionLayout motionLayout = MotionLayout.this;
            motionLayout.Y4 = mode;
            motionLayout.Z4 = mode2;
            int optimizationLevel = motionLayout.getOptimizationLevel();
            MotionLayout motionLayout2 = MotionLayout.this;
            if (motionLayout2.b4 == motionLayout2.getStartState()) {
                MotionLayout.this.t(this.f302b, optimizationLevel, i2, i3);
                if (this.f303c != null) {
                    MotionLayout.this.t(this.f301a, optimizationLevel, i2, i3);
                }
            } else {
                if (this.f303c != null) {
                    MotionLayout.this.t(this.f301a, optimizationLevel, i2, i3);
                }
                MotionLayout.this.t(this.f302b, optimizationLevel, i2, i3);
            }
            if (((MotionLayout.this.getParent() instanceof MotionLayout) && mode == 1073741824 && mode2 == 1073741824) ? false : true) {
                MotionLayout motionLayout3 = MotionLayout.this;
                motionLayout3.Y4 = mode;
                motionLayout3.Z4 = mode2;
                if (motionLayout3.b4 == motionLayout3.getStartState()) {
                    MotionLayout.this.t(this.f302b, optimizationLevel, i2, i3);
                    if (this.f303c != null) {
                        MotionLayout.this.t(this.f301a, optimizationLevel, i2, i3);
                    }
                } else {
                    if (this.f303c != null) {
                        MotionLayout.this.t(this.f301a, optimizationLevel, i2, i3);
                    }
                    MotionLayout.this.t(this.f302b, optimizationLevel, i2, i3);
                }
                MotionLayout.this.U4 = this.f301a.Q();
                MotionLayout.this.V4 = this.f301a.w();
                MotionLayout.this.W4 = this.f302b.Q();
                MotionLayout.this.X4 = this.f302b.w();
                MotionLayout motionLayout4 = MotionLayout.this;
                motionLayout4.T4 = (motionLayout4.U4 == motionLayout4.W4 && motionLayout4.V4 == motionLayout4.X4) ? false : true;
            }
            MotionLayout motionLayout5 = MotionLayout.this;
            int i4 = motionLayout5.U4;
            int i5 = motionLayout5.V4;
            int i6 = motionLayout5.Y4;
            if (i6 == Integer.MIN_VALUE || i6 == 0) {
                i4 = (int) (i4 + (motionLayout5.a5 * (motionLayout5.W4 - i4)));
            }
            int i7 = motionLayout5.Z4;
            if (i7 == Integer.MIN_VALUE || i7 == 0) {
                i5 = (int) (i5 + (motionLayout5.a5 * (motionLayout5.X4 - i5)));
            }
            MotionLayout.this.s(i2, i3, i4, i5, this.f301a.d1() || this.f302b.d1(), this.f301a.b1() || this.f302b.b1());
        }

        public void g() {
            f(MotionLayout.this.d4, MotionLayout.this.e4);
            MotionLayout.this.q0();
        }

        public void h(int i2, int i3) {
            this.f305e = i2;
            this.f306f = i3;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final void i(b.e.b.j.d dVar, b.e.c.b bVar) {
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
                    b.e.b.j.g gVar = (b.e.b.j.g) constraintWidget3;
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

        void e(int i2);
    }

    public static class g implements f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static g f308a = new g();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public VelocityTracker f309b;

        public static g f() {
            f308a.f309b = VelocityTracker.obtain();
            return f308a;
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.f
        public void a(MotionEvent motionEvent) {
            VelocityTracker velocityTracker = this.f309b;
            if (velocityTracker != null) {
                velocityTracker.addMovement(motionEvent);
            }
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.f
        public float b() {
            return this.f309b.getYVelocity();
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.f
        public float c() {
            return this.f309b.getXVelocity();
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.f
        public void d() {
            this.f309b.recycle();
            this.f309b = null;
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.f
        public void e(int i2) {
            this.f309b.computeCurrentVelocity(i2);
        }
    }

    public class h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float f310a = Float.NaN;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f311b = Float.NaN;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f312c = -1;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f313d = -1;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final String f314e = "motion.progress";

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final String f315f = "motion.velocity";

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final String f316g = "motion.StartState";

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final String f317h = "motion.EndState";

        public h() {
        }

        public void a() {
            int i2 = this.f312c;
            if (i2 != -1 || this.f313d != -1) {
                if (i2 == -1) {
                    MotionLayout.this.u0(this.f313d);
                } else {
                    int i3 = this.f313d;
                    if (i3 == -1) {
                        MotionLayout.this.o0(i2, -1, -1);
                    } else {
                        MotionLayout.this.p0(i2, i3);
                    }
                }
                MotionLayout.this.setState(TransitionState.SETUP);
            }
            if (Float.isNaN(this.f311b)) {
                if (Float.isNaN(this.f310a)) {
                    return;
                }
                MotionLayout.this.setProgress(this.f310a);
            } else {
                MotionLayout.this.n0(this.f310a, this.f311b);
                this.f310a = Float.NaN;
                this.f311b = Float.NaN;
                this.f312c = -1;
                this.f313d = -1;
            }
        }

        public Bundle b() {
            Bundle bundle = new Bundle();
            bundle.putFloat("motion.progress", this.f310a);
            bundle.putFloat("motion.velocity", this.f311b);
            bundle.putInt("motion.StartState", this.f312c);
            bundle.putInt("motion.EndState", this.f313d);
            return bundle;
        }

        public void c() {
            this.f313d = MotionLayout.this.c4;
            this.f312c = MotionLayout.this.a4;
            this.f311b = MotionLayout.this.getVelocity();
            this.f310a = MotionLayout.this.getProgress();
        }

        public void d(int i2) {
            this.f313d = i2;
        }

        public void e(float f2) {
            this.f310a = f2;
        }

        public void f(int i2) {
            this.f312c = i2;
        }

        public void g(Bundle bundle) {
            this.f310a = bundle.getFloat("motion.progress");
            this.f311b = bundle.getFloat("motion.velocity");
            this.f312c = bundle.getInt("motion.StartState");
            this.f313d = bundle.getInt("motion.EndState");
        }

        public void h(float f2) {
            this.f311b = f2;
        }
    }

    public interface i {
        void a(MotionLayout motionLayout, int i2, int i3);

        void b(MotionLayout motionLayout, int i2, boolean z, float f2);

        void c(MotionLayout motionLayout, int i2, int i3, float f2);

        void d(MotionLayout motionLayout, int i2);
    }

    public MotionLayout(Context context) {
        super(context);
        this.Z3 = 0.0f;
        this.a4 = -1;
        this.b4 = -1;
        this.c4 = -1;
        this.d4 = 0;
        this.e4 = 0;
        this.f4 = true;
        this.g4 = new HashMap<>();
        this.h4 = 0L;
        this.i4 = 1.0f;
        this.j4 = 0.0f;
        this.k4 = 0.0f;
        this.m4 = 0.0f;
        this.o4 = false;
        this.p4 = false;
        this.t4 = 0;
        this.v4 = false;
        this.w4 = new b.e.a.a.g();
        this.x4 = new c();
        this.z4 = true;
        this.E4 = false;
        this.J4 = false;
        this.K4 = null;
        this.L4 = null;
        this.M4 = null;
        this.N4 = 0;
        this.O4 = -1L;
        this.P4 = 0.0f;
        this.Q4 = 0;
        this.R4 = 0.0f;
        this.S4 = false;
        this.T4 = false;
        this.b5 = new b.e.a.b.e();
        this.c5 = false;
        this.e5 = TransitionState.UNDEFINED;
        this.f5 = new e();
        this.g5 = false;
        this.h5 = new RectF();
        this.i5 = null;
        this.j5 = new ArrayList<>();
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
        if (this.X3 == null) {
            return;
        }
        float f3 = this.k4;
        float f4 = this.j4;
        if (f3 != f4 && this.n4) {
            this.k4 = f4;
        }
        float f5 = this.k4;
        if (f5 == f2) {
            return;
        }
        this.v4 = false;
        this.m4 = f2;
        this.i4 = r0.m() / 1000.0f;
        setProgress(this.m4);
        this.Y3 = this.X3.p();
        this.n4 = false;
        this.h4 = getNanoTime();
        this.o4 = true;
        this.j4 = f5;
        this.k4 = f5;
        invalidate();
    }

    public final void U() {
        q qVar = this.X3;
        if (qVar == null) {
            Log.e("MotionLayout", "CHECK: motion scene not set! set \"app:layoutDescription=\"@xml/file\"");
            return;
        }
        int iX = qVar.x();
        q qVar2 = this.X3;
        V(iX, qVar2.i(qVar2.x()));
        SparseIntArray sparseIntArray = new SparseIntArray();
        SparseIntArray sparseIntArray2 = new SparseIntArray();
        for (q.b bVar : this.X3.l()) {
            if (bVar == this.X3.f1307c) {
                Log.v("MotionLayout", "CHECK: CURRENT");
            }
            W(bVar);
            int iA = bVar.A();
            int iY = bVar.y();
            String strB = b.e.a.b.a.b(getContext(), iA);
            String strB2 = b.e.a.b.a.b(getContext(), iY);
            if (sparseIntArray.get(iA) == iY) {
                Log.e("MotionLayout", "CHECK: two transitions with the same start and end " + strB + "->" + strB2);
            }
            if (sparseIntArray2.get(iY) == iA) {
                Log.e("MotionLayout", "CHECK: you can't have reverse transitions" + strB + "->" + strB2);
            }
            sparseIntArray.put(iA, iY);
            sparseIntArray2.put(iY, iA);
            if (this.X3.i(iA) == null) {
                Log.e("MotionLayout", " no such constraintSetStart " + strB);
            }
            if (this.X3.i(iY) == null) {
                Log.e("MotionLayout", " no such constraintSetEnd " + strB);
            }
        }
    }

    public final void V(int i2, b.e.c.b bVar) {
        String strB = b.e.a.b.a.b(getContext(), i2);
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            int id = childAt.getId();
            if (id == -1) {
                Log.w("MotionLayout", "CHECK: " + strB + " ALL VIEWS SHOULD HAVE ID's " + childAt.getClass().getName() + " does not!");
            }
            if (bVar.n(id) == null) {
                Log.w("MotionLayout", "CHECK: " + strB + " NO CONSTRAINTS for " + b.e.a.b.a.c(childAt));
            }
        }
        int[] iArrP = bVar.p();
        for (int i4 = 0; i4 < iArrP.length; i4++) {
            int i5 = iArrP[i4];
            String strB2 = b.e.a.b.a.b(getContext(), i5);
            if (findViewById(iArrP[i4]) == null) {
                Log.w("MotionLayout", "CHECK: " + strB + " NO View matches id " + strB2);
            }
            if (bVar.o(i5) == -1) {
                Log.w("MotionLayout", "CHECK: " + strB + "(" + strB2 + ") no LAYOUT_HEIGHT");
            }
            if (bVar.t(i5) == -1) {
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
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            n nVar = this.g4.get(childAt);
            if (nVar != null) {
                nVar.s(childAt);
            }
        }
    }

    public void Y(boolean z) {
        float f2;
        boolean z2;
        int i2;
        float interpolation;
        boolean z3;
        if (this.l4 == -1) {
            this.l4 = getNanoTime();
        }
        float f3 = this.k4;
        if (f3 > 0.0f && f3 < 1.0f) {
            this.b4 = -1;
        }
        boolean z4 = false;
        if (this.J4 || (this.o4 && (z || this.m4 != f3))) {
            float fSignum = Math.signum(this.m4 - f3);
            long nanoTime = getNanoTime();
            Interpolator interpolator = this.Y3;
            if (interpolator instanceof o) {
                f2 = 0.0f;
            } else {
                f2 = (((nanoTime - this.l4) * fSignum) * 1.0E-9f) / this.i4;
                this.Z3 = f2;
            }
            float f4 = this.k4 + f2;
            if (this.n4) {
                f4 = this.m4;
            }
            if ((fSignum <= 0.0f || f4 < this.m4) && (fSignum > 0.0f || f4 > this.m4)) {
                z2 = false;
            } else {
                f4 = this.m4;
                this.o4 = false;
                z2 = true;
            }
            this.k4 = f4;
            this.j4 = f4;
            this.l4 = nanoTime;
            if (interpolator != null && !z2) {
                if (this.v4) {
                    interpolation = interpolator.getInterpolation((nanoTime - this.h4) * 1.0E-9f);
                    this.k4 = interpolation;
                    this.l4 = nanoTime;
                    Interpolator interpolator2 = this.Y3;
                    if (interpolator2 instanceof o) {
                        float fA = ((o) interpolator2).a();
                        this.Z3 = fA;
                        if (Math.abs(fA) * this.i4 <= 1.0E-5f) {
                            this.o4 = false;
                        }
                        if (fA > 0.0f && interpolation >= 1.0f) {
                            this.k4 = 1.0f;
                            this.o4 = false;
                            interpolation = 1.0f;
                        }
                        if (fA < 0.0f && interpolation <= 0.0f) {
                            this.k4 = 0.0f;
                            this.o4 = false;
                            f4 = 0.0f;
                        }
                    }
                } else {
                    interpolation = interpolator.getInterpolation(f4);
                    Interpolator interpolator3 = this.Y3;
                    if (interpolator3 instanceof o) {
                        this.Z3 = ((o) interpolator3).a();
                    } else {
                        this.Z3 = ((interpolator3.getInterpolation(f4 + f2) - interpolation) * fSignum) / f2;
                    }
                }
                f4 = interpolation;
            }
            if (Math.abs(this.Z3) > 1.0E-5f) {
                setState(TransitionState.MOVING);
            }
            if ((fSignum > 0.0f && f4 >= this.m4) || (fSignum <= 0.0f && f4 <= this.m4)) {
                f4 = this.m4;
                this.o4 = false;
            }
            if (f4 >= 1.0f || f4 <= 0.0f) {
                this.o4 = false;
                setState(TransitionState.FINISHED);
            }
            int childCount = getChildCount();
            this.J4 = false;
            long nanoTime2 = getNanoTime();
            this.a5 = f4;
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = getChildAt(i3);
                n nVar = this.g4.get(childAt);
                if (nVar != null) {
                    this.J4 = nVar.o(childAt, f4, nanoTime2, this.b5) | this.J4;
                }
            }
            boolean z5 = (fSignum > 0.0f && f4 >= this.m4) || (fSignum <= 0.0f && f4 <= this.m4);
            if (!this.J4 && !this.o4 && z5) {
                setState(TransitionState.FINISHED);
            }
            if (this.T4) {
                requestLayout();
            }
            this.J4 = (!z5) | this.J4;
            if (f4 <= 0.0f && (i2 = this.a4) != -1 && this.b4 != i2) {
                this.b4 = i2;
                this.X3.i(i2).c(this);
                setState(TransitionState.FINISHED);
                z4 = true;
            }
            if (f4 >= 1.0d) {
                int i4 = this.b4;
                int i5 = this.c4;
                if (i4 != i5) {
                    this.b4 = i5;
                    this.X3.i(i5).c(this);
                    setState(TransitionState.FINISHED);
                    z4 = true;
                }
            }
            if (this.J4 || this.o4) {
                invalidate();
            } else if ((fSignum > 0.0f && f4 == 1.0f) || (fSignum < 0.0f && f4 == 0.0f)) {
                setState(TransitionState.FINISHED);
            }
            if ((!this.J4 && this.o4 && fSignum > 0.0f && f4 == 1.0f) || (fSignum < 0.0f && f4 == 0.0f)) {
                k0();
            }
        }
        float f5 = this.k4;
        if (f5 < 1.0f) {
            if (f5 <= 0.0f) {
                int i6 = this.b4;
                int i7 = this.a4;
                z3 = i6 == i7 ? z4 : true;
                this.b4 = i7;
            }
            this.g5 |= z4;
            if (z4 && !this.c5) {
                requestLayout();
            }
            this.j4 = this.k4;
        }
        int i8 = this.b4;
        int i9 = this.c4;
        z3 = i8 == i9 ? z4 : true;
        this.b4 = i9;
        z4 = z3;
        this.g5 |= z4;
        if (z4) {
            requestLayout();
        }
        this.j4 = this.k4;
    }

    public final void Z() {
        boolean z;
        float fSignum = Math.signum(this.m4 - this.k4);
        long nanoTime = getNanoTime();
        Interpolator interpolator = this.Y3;
        float interpolation = this.k4 + (!(interpolator instanceof b.e.a.a.g) ? (((nanoTime - this.l4) * fSignum) * 1.0E-9f) / this.i4 : 0.0f);
        if (this.n4) {
            interpolation = this.m4;
        }
        if ((fSignum <= 0.0f || interpolation < this.m4) && (fSignum > 0.0f || interpolation > this.m4)) {
            z = false;
        } else {
            interpolation = this.m4;
            z = true;
        }
        if (interpolator != null && !z) {
            interpolation = this.v4 ? interpolator.getInterpolation((nanoTime - this.h4) * 1.0E-9f) : interpolator.getInterpolation(interpolation);
        }
        if ((fSignum > 0.0f && interpolation >= this.m4) || (fSignum <= 0.0f && interpolation <= this.m4)) {
            interpolation = this.m4;
        }
        this.a5 = interpolation;
        int childCount = getChildCount();
        long nanoTime2 = getNanoTime();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            n nVar = this.g4.get(childAt);
            if (nVar != null) {
                nVar.o(childAt, interpolation, nanoTime2, this.b5);
            }
        }
        if (this.T4) {
            requestLayout();
        }
    }

    public final void a0() {
        ArrayList<i> arrayList;
        if ((this.q4 == null && ((arrayList = this.M4) == null || arrayList.isEmpty())) || this.R4 == this.j4) {
            return;
        }
        if (this.Q4 != -1) {
            i iVar = this.q4;
            if (iVar != null) {
                iVar.a(this, this.a4, this.c4);
            }
            ArrayList<i> arrayList2 = this.M4;
            if (arrayList2 != null) {
                Iterator<i> it = arrayList2.iterator();
                while (it.hasNext()) {
                    it.next().a(this, this.a4, this.c4);
                }
            }
            this.S4 = true;
        }
        this.Q4 = -1;
        float f2 = this.j4;
        this.R4 = f2;
        i iVar2 = this.q4;
        if (iVar2 != null) {
            iVar2.c(this, this.a4, this.c4, f2);
        }
        ArrayList<i> arrayList3 = this.M4;
        if (arrayList3 != null) {
            Iterator<i> it2 = arrayList3.iterator();
            while (it2.hasNext()) {
                it2.next().c(this, this.a4, this.c4, this.j4);
            }
        }
        this.S4 = true;
    }

    public void b0() {
        int iIntValue;
        ArrayList<i> arrayList;
        if ((this.q4 != null || ((arrayList = this.M4) != null && !arrayList.isEmpty())) && this.Q4 == -1) {
            this.Q4 = this.b4;
            if (this.j5.isEmpty()) {
                iIntValue = -1;
            } else {
                iIntValue = this.j5.get(r0.size() - 1).intValue();
            }
            int i2 = this.b4;
            if (iIntValue != i2 && i2 != -1) {
                this.j5.add(Integer.valueOf(i2));
            }
        }
        l0();
    }

    public void c0(int i2, boolean z, float f2) {
        i iVar = this.q4;
        if (iVar != null) {
            iVar.b(this, i2, z, f2);
        }
        ArrayList<i> arrayList = this.M4;
        if (arrayList != null) {
            Iterator<i> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().b(this, i2, z, f2);
            }
        }
    }

    public void d0(int i2, float f2, float f3, float f4, float[] fArr) {
        String resourceName;
        HashMap<View, n> map = this.g4;
        View viewG = g(i2);
        n nVar = map.get(viewG);
        if (nVar != null) {
            nVar.g(f2, f3, f4, fArr);
            float y = viewG.getY();
            int i3 = ((f2 - this.r4) > 0.0f ? 1 : ((f2 - this.r4) == 0.0f ? 0 : -1));
            this.r4 = f2;
            this.s4 = y;
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
        if (this.X3 == null) {
            return;
        }
        if ((this.t4 & 1) == 1 && !isInEditMode()) {
            this.N4++;
            long nanoTime = getNanoTime();
            long j = this.O4;
            if (j != -1) {
                if (nanoTime - j > 200000000) {
                    this.P4 = ((int) ((this.N4 / (r5 * 1.0E-9f)) * 100.0f)) / 100.0f;
                    this.N4 = 0;
                    this.O4 = nanoTime;
                }
            } else {
                this.O4 = nanoTime;
            }
            Paint paint = new Paint();
            paint.setTextSize(42.0f);
            String str = this.P4 + " fps " + b.e.a.b.a.d(this, this.a4) + " -> ";
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(b.e.a.b.a.d(this, this.c4));
            sb.append(" (progress: ");
            sb.append(((int) (getProgress() * 1000.0f)) / 10.0f);
            sb.append(" ) state=");
            int i2 = this.b4;
            sb.append(i2 == -1 ? "undefined" : b.e.a.b.a.d(this, i2));
            String string = sb.toString();
            paint.setColor(-16777216);
            canvas.drawText(string, 11.0f, getHeight() - 29, paint);
            paint.setColor(-7864184);
            canvas.drawText(string, 10.0f, getHeight() - 30, paint);
        }
        if (this.t4 > 1) {
            if (this.u4 == null) {
                this.u4 = new d();
            }
            this.u4.a(canvas, this.g4, this.X3.m(), this.t4);
        }
    }

    public q.b e0(int i2) {
        return this.X3.y(i2);
    }

    public void f0(View view, float f2, float f3, float[] fArr, int i2) {
        float f4;
        float fA = this.Z3;
        float f5 = this.k4;
        if (this.Y3 != null) {
            float fSignum = Math.signum(this.m4 - f5);
            float interpolation = this.Y3.getInterpolation(this.k4 + 1.0E-5f);
            float interpolation2 = this.Y3.getInterpolation(this.k4);
            fA = (fSignum * ((interpolation - interpolation2) / 1.0E-5f)) / this.i4;
            f4 = interpolation2;
        } else {
            f4 = f5;
        }
        Interpolator interpolator = this.Y3;
        if (interpolator instanceof o) {
            fA = ((o) interpolator).a();
        }
        n nVar = this.g4.get(view);
        if ((i2 & 1) == 0) {
            nVar.l(f4, view.getWidth(), view.getHeight(), f2, f3, fArr);
        } else {
            nVar.g(f4, f2, f3, fArr);
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
        this.h5.set(view.getLeft() + f2, view.getTop() + f3, f2 + view.getRight(), f3 + view.getBottom());
        if (motionEvent.getAction() == 0) {
            if (this.h5.contains(motionEvent.getX(), motionEvent.getY()) && view.onTouchEvent(motionEvent)) {
                return true;
            }
        } else if (view.onTouchEvent(motionEvent)) {
            return true;
        }
        return false;
    }

    public int[] getConstraintSetIds() {
        q qVar = this.X3;
        if (qVar == null) {
            return null;
        }
        return qVar.k();
    }

    public int getCurrentState() {
        return this.b4;
    }

    public ArrayList<q.b> getDefinedTransitions() {
        q qVar = this.X3;
        if (qVar == null) {
            return null;
        }
        return qVar.l();
    }

    public b.e.a.b.b getDesignTool() {
        if (this.y4 == null) {
            this.y4 = new b.e.a.b.b(this);
        }
        return this.y4;
    }

    public int getEndState() {
        return this.c4;
    }

    public long getNanoTime() {
        return System.nanoTime();
    }

    public float getProgress() {
        return this.k4;
    }

    public int getStartState() {
        return this.a4;
    }

    public float getTargetPosition() {
        return this.m4;
    }

    public Bundle getTransitionState() {
        if (this.d5 == null) {
            this.d5 = new h();
        }
        this.d5.c();
        return this.d5.b();
    }

    public long getTransitionTimeMs() {
        if (this.X3 != null) {
            this.i4 = r0.m() / 1000.0f;
        }
        return (long) (this.i4 * 1000.0f);
    }

    public float getVelocity() {
        return this.Z3;
    }

    @Override // b.f.m.l
    public void h(View view, View view2, int i2, int i3) {
    }

    public final void h0(AttributeSet attributeSet) {
        q qVar;
        W3 = isInEditMode();
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, b.e.c.e.H6);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            boolean z = true;
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                if (index == b.e.c.e.K6) {
                    this.X3 = new q(getContext(), this, typedArrayObtainStyledAttributes.getResourceId(index, -1));
                } else if (index == b.e.c.e.J6) {
                    this.b4 = typedArrayObtainStyledAttributes.getResourceId(index, -1);
                } else if (index == b.e.c.e.M6) {
                    this.m4 = typedArrayObtainStyledAttributes.getFloat(index, 0.0f);
                    this.o4 = true;
                } else if (index == b.e.c.e.I6) {
                    z = typedArrayObtainStyledAttributes.getBoolean(index, z);
                } else if (index == b.e.c.e.N6) {
                    if (this.t4 == 0) {
                        this.t4 = typedArrayObtainStyledAttributes.getBoolean(index, false) ? 2 : 0;
                    }
                } else if (index == b.e.c.e.L6) {
                    this.t4 = typedArrayObtainStyledAttributes.getInt(index, 0);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
            if (this.X3 == null) {
                Log.e("MotionLayout", "WARNING NO app:layoutDescription tag");
            }
            if (!z) {
                this.X3 = null;
            }
        }
        if (this.t4 != 0) {
            U();
        }
        if (this.b4 != -1 || (qVar = this.X3) == null) {
            return;
        }
        this.b4 = qVar.x();
        this.a4 = this.X3.x();
        this.c4 = this.X3.n();
    }

    @Override // b.f.m.l
    public void i(View view, int i2) {
        q qVar = this.X3;
        if (qVar == null) {
            return;
        }
        float f2 = this.F4;
        float f3 = this.I4;
        qVar.G(f2 / f3, this.G4 / f3);
    }

    public boolean i0() {
        return this.f4;
    }

    @Override // android.view.View
    public boolean isAttachedToWindow() {
        return Build.VERSION.SDK_INT >= 19 ? super.isAttachedToWindow() : getWindowToken() != null;
    }

    @Override // b.f.m.l
    public void j(View view, int i2, int i3, int[] iArr, int i4) {
        q.b bVar;
        t tVarB;
        int iK;
        q qVar = this.X3;
        if (qVar == null || (bVar = qVar.f1307c) == null || !bVar.C()) {
            return;
        }
        q.b bVar2 = this.X3.f1307c;
        if (bVar2 == null || !bVar2.C() || (tVarB = bVar2.B()) == null || (iK = tVarB.k()) == -1 || view.getId() == iK) {
            q qVar2 = this.X3;
            if (qVar2 != null && qVar2.t()) {
                float f2 = this.j4;
                if ((f2 == 1.0f || f2 == 0.0f) && view.canScrollVertically(-1)) {
                    return;
                }
            }
            if (bVar2.B() != null && (this.X3.f1307c.B().d() & 1) != 0) {
                float fU = this.X3.u(i2, i3);
                float f3 = this.k4;
                if ((f3 <= 0.0f && fU < 0.0f) || (f3 >= 1.0f && fU > 0.0f)) {
                    if (Build.VERSION.SDK_INT >= 21) {
                        view.setNestedScrollingEnabled(false);
                        view.post(new a(view));
                        return;
                    }
                    return;
                }
            }
            float f4 = this.j4;
            long nanoTime = getNanoTime();
            float f5 = i2;
            this.F4 = f5;
            float f6 = i3;
            this.G4 = f6;
            this.I4 = (float) ((nanoTime - this.H4) * 1.0E-9d);
            this.H4 = nanoTime;
            this.X3.F(f5, f6);
            if (f4 != this.j4) {
                iArr[0] = i2;
                iArr[1] = i3;
            }
            Y(false);
            if (iArr[0] == 0 && iArr[1] == 0) {
                return;
            }
            this.E4 = true;
        }
    }

    public f j0() {
        return g.f();
    }

    public final void k0() {
        q qVar = this.X3;
        if (qVar == null) {
            return;
        }
        if (qVar.f(this, this.b4)) {
            requestLayout();
            return;
        }
        int i2 = this.b4;
        if (i2 != -1) {
            this.X3.e(this, i2);
        }
        if (this.X3.Q()) {
            this.X3.O();
        }
    }

    public final void l0() {
        ArrayList<i> arrayList;
        if (this.q4 == null && ((arrayList = this.M4) == null || arrayList.isEmpty())) {
            return;
        }
        this.S4 = false;
        for (Integer num : this.j5) {
            i iVar = this.q4;
            if (iVar != null) {
                iVar.d(this, num.intValue());
            }
            ArrayList<i> arrayList2 = this.M4;
            if (arrayList2 != null) {
                Iterator<i> it = arrayList2.iterator();
                while (it.hasNext()) {
                    it.next().d(this, num.intValue());
                }
            }
        }
        this.j5.clear();
    }

    @Override // b.f.m.m
    public void m(View view, int i2, int i3, int i4, int i5, int i6, int[] iArr) {
        if (this.E4 || i2 != 0 || i3 != 0) {
            iArr[0] = iArr[0] + i4;
            iArr[1] = iArr[1] + i5;
        }
        this.E4 = false;
    }

    public void m0() {
        this.f5.g();
        invalidate();
    }

    @Override // b.f.m.l
    public void n(View view, int i2, int i3, int i4, int i5, int i6) {
    }

    public void n0(float f2, float f3) {
        if (isAttachedToWindow()) {
            setProgress(f2);
            setState(TransitionState.MOVING);
            this.Z3 = f3;
            T(1.0f);
            return;
        }
        if (this.d5 == null) {
            this.d5 = new h();
        }
        this.d5.e(f2);
        this.d5.h(f3);
    }

    @Override // b.f.m.l
    public boolean o(View view, View view2, int i2, int i3) {
        q.b bVar;
        q qVar = this.X3;
        return (qVar == null || (bVar = qVar.f1307c) == null || bVar.B() == null || (this.X3.f1307c.B().d() & 2) != 0) ? false : true;
    }

    public void o0(int i2, int i3, int i4) {
        setState(TransitionState.SETUP);
        this.b4 = i2;
        this.a4 = -1;
        this.c4 = -1;
        b.e.c.a aVar = this.J3;
        if (aVar != null) {
            aVar.d(i2, i3, i4);
            return;
        }
        q qVar = this.X3;
        if (qVar != null) {
            qVar.i(i2).d(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        int i2;
        super.onAttachedToWindow();
        q qVar = this.X3;
        if (qVar != null && (i2 = this.b4) != -1) {
            b.e.c.b bVarI = qVar.i(i2);
            this.X3.J(this);
            if (bVarI != null) {
                bVarI.d(this);
            }
            this.a4 = this.b4;
        }
        k0();
        h hVar = this.d5;
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
        q qVar = this.X3;
        if (qVar != null && this.f4 && (bVar = qVar.f1307c) != null && bVar.C() && (tVarB = bVar.B()) != null && ((motionEvent.getAction() != 0 || (rectFJ = tVarB.j(this, new RectF())) == null || rectFJ.contains(motionEvent.getX(), motionEvent.getY())) && (iK = tVarB.k()) != -1)) {
            View view = this.i5;
            if (view == null || view.getId() != iK) {
                this.i5 = findViewById(iK);
            }
            if (this.i5 != null) {
                this.h5.set(r0.getLeft(), this.i5.getTop(), this.i5.getRight(), this.i5.getBottom());
                if (this.h5.contains(motionEvent.getX(), motionEvent.getY()) && !g0(0.0f, 0.0f, this.i5, motionEvent)) {
                    return onTouchEvent(motionEvent);
                }
            }
        }
        return false;
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        this.c5 = true;
        try {
            if (this.X3 == null) {
                super.onLayout(z, i2, i3, i4, i5);
                return;
            }
            int i6 = i4 - i2;
            int i7 = i5 - i3;
            if (this.C4 != i6 || this.D4 != i7) {
                m0();
                Y(true);
            }
            this.C4 = i6;
            this.D4 = i7;
            this.A4 = i6;
            this.B4 = i7;
        } finally {
            this.c5 = false;
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        if (this.X3 == null) {
            super.onMeasure(i2, i3);
            return;
        }
        boolean z = false;
        boolean z2 = (this.d4 == i2 && this.e4 == i3) ? false : true;
        if (this.g5) {
            this.g5 = false;
            k0();
            l0();
            z2 = true;
        }
        if (this.G3) {
            z2 = true;
        }
        this.d4 = i2;
        this.e4 = i3;
        int iX = this.X3.x();
        int iN = this.X3.n();
        if ((z2 || this.f5.e(iX, iN)) && this.a4 != -1) {
            super.onMeasure(i2, i3);
            this.f5.d(this.s, this.X3.i(iX), this.X3.i(iN));
            this.f5.g();
            this.f5.h(iX, iN);
        } else {
            z = true;
        }
        if (this.T4 || z) {
            int paddingTop = getPaddingTop() + getPaddingBottom();
            int iQ = this.s.Q() + getPaddingLeft() + getPaddingRight();
            int iW = this.s.w() + paddingTop;
            int i4 = this.Y4;
            if (i4 == Integer.MIN_VALUE || i4 == 0) {
                iQ = (int) (this.U4 + (this.a5 * (this.W4 - r7)));
                requestLayout();
            }
            int i5 = this.Z4;
            if (i5 == Integer.MIN_VALUE || i5 == 0) {
                iW = (int) (this.V4 + (this.a5 * (this.X4 - r7)));
                requestLayout();
            }
            setMeasuredDimension(iQ, iW);
        }
        Z();
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, b.f.m.n
    public boolean onNestedFling(View view, float f2, float f3, boolean z) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, b.f.m.n
    public boolean onNestedPreFling(View view, float f2, float f3) {
        return false;
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i2) {
        q qVar = this.X3;
        if (qVar != null) {
            qVar.L(p());
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        q qVar = this.X3;
        if (qVar == null || !this.f4 || !qVar.Q()) {
            return super.onTouchEvent(motionEvent);
        }
        q.b bVar = this.X3.f1307c;
        if (bVar != null && !bVar.C()) {
            return super.onTouchEvent(motionEvent);
        }
        this.X3.H(motionEvent, getCurrentState(), this);
        return true;
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public void onViewAdded(View view) {
        super.onViewAdded(view);
        if (view instanceof MotionHelper) {
            MotionHelper motionHelper = (MotionHelper) view;
            if (this.M4 == null) {
                this.M4 = new ArrayList<>();
            }
            this.M4.add(motionHelper);
            if (motionHelper.w()) {
                if (this.K4 == null) {
                    this.K4 = new ArrayList<>();
                }
                this.K4.add(motionHelper);
            }
            if (motionHelper.v()) {
                if (this.L4 == null) {
                    this.L4 = new ArrayList<>();
                }
                this.L4.add(motionHelper);
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        ArrayList<MotionHelper> arrayList = this.K4;
        if (arrayList != null) {
            arrayList.remove(view);
        }
        ArrayList<MotionHelper> arrayList2 = this.L4;
        if (arrayList2 != null) {
            arrayList2.remove(view);
        }
    }

    public void p0(int i2, int i3) {
        if (!isAttachedToWindow()) {
            if (this.d5 == null) {
                this.d5 = new h();
            }
            this.d5.f(i2);
            this.d5.d(i3);
            return;
        }
        q qVar = this.X3;
        if (qVar != null) {
            this.a4 = i2;
            this.c4 = i3;
            qVar.M(i2, i3);
            this.f5.d(this.s, this.X3.i(i2), this.X3.i(i3));
            m0();
            this.k4 = 0.0f;
            t0();
        }
    }

    public final void q0() {
        int childCount = getChildCount();
        this.f5.a();
        boolean z = true;
        this.o4 = true;
        int width = getWidth();
        int height = getHeight();
        int iH = this.X3.h();
        int i2 = 0;
        if (iH != -1) {
            for (int i3 = 0; i3 < childCount; i3++) {
                n nVar = this.g4.get(getChildAt(i3));
                if (nVar != null) {
                    nVar.r(iH);
                }
            }
        }
        for (int i4 = 0; i4 < childCount; i4++) {
            n nVar2 = this.g4.get(getChildAt(i4));
            if (nVar2 != null) {
                this.X3.q(nVar2);
                nVar2.v(width, height, this.i4, getNanoTime());
            }
        }
        float fW = this.X3.w();
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
                n nVar3 = this.g4.get(getChildAt(i5));
                if (!Float.isNaN(nVar3.k)) {
                    break;
                }
                float fI = nVar3.i();
                float fJ = nVar3.j();
                float f2 = z2 ? fJ - fI : fJ + fI;
                fMin2 = Math.min(fMin2, f2);
                fMax2 = Math.max(fMax2, f2);
                i5++;
            }
            if (!z) {
                while (i2 < childCount) {
                    n nVar4 = this.g4.get(getChildAt(i2));
                    float fI2 = nVar4.i();
                    float fJ2 = nVar4.j();
                    float f3 = z2 ? fJ2 - fI2 : fJ2 + fI2;
                    nVar4.m = 1.0f / (1.0f - fAbs);
                    nVar4.l = fAbs - (((f3 - fMin2) * fAbs) / (fMax2 - fMin2));
                    i2++;
                }
                return;
            }
            for (int i6 = 0; i6 < childCount; i6++) {
                n nVar5 = this.g4.get(getChildAt(i6));
                if (!Float.isNaN(nVar5.k)) {
                    fMin = Math.min(fMin, nVar5.k);
                    fMax = Math.max(fMax, nVar5.k);
                }
            }
            while (i2 < childCount) {
                n nVar6 = this.g4.get(getChildAt(i2));
                if (!Float.isNaN(nVar6.k)) {
                    nVar6.m = 1.0f / (1.0f - fAbs);
                    if (z2) {
                        nVar6.l = fAbs - (((fMax - nVar6.k) / (fMax - fMin)) * fAbs);
                    } else {
                        nVar6.l = fAbs - (((nVar6.k - fMin) * fAbs) / (fMax - fMin));
                    }
                }
                i2++;
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout
    public void r(int i2) {
        this.J3 = null;
    }

    public void r0(int i2, float f2, float f3) {
        if (this.X3 == null || this.k4 == f2) {
            return;
        }
        this.v4 = true;
        this.h4 = getNanoTime();
        float fM = this.X3.m() / 1000.0f;
        this.i4 = fM;
        this.m4 = f2;
        this.o4 = true;
        if (i2 == 0 || i2 == 1 || i2 == 2) {
            if (i2 == 1) {
                f2 = 0.0f;
            } else if (i2 == 2) {
                f2 = 1.0f;
            }
            this.w4.c(this.k4, f2, f3, fM, this.X3.r(), this.X3.s());
            int i3 = this.b4;
            this.m4 = f2;
            this.b4 = i3;
            this.Y3 = this.w4;
        } else if (i2 == 4) {
            this.x4.b(f3, this.k4, this.X3.r());
            this.Y3 = this.x4;
        } else if (i2 == 5) {
            if (w0(f3, this.k4, this.X3.r())) {
                this.x4.b(f3, this.k4, this.X3.r());
                this.Y3 = this.x4;
            } else {
                this.w4.c(this.k4, f2, f3, this.i4, this.X3.r(), this.X3.s());
                this.Z3 = 0.0f;
                int i4 = this.b4;
                this.m4 = f2;
                this.b4 = i4;
                this.Y3 = this.w4;
            }
        }
        this.n4 = false;
        this.h4 = getNanoTime();
        invalidate();
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View, android.view.ViewParent
    public void requestLayout() {
        q qVar;
        q.b bVar;
        if (this.T4 || this.b4 != -1 || (qVar = this.X3) == null || (bVar = qVar.f1307c) == null || bVar.z() != 0) {
            super.requestLayout();
        }
    }

    public void s0() {
        T(1.0f);
    }

    public void setDebugMode(int i2) {
        this.t4 = i2;
        invalidate();
    }

    public void setInteractionEnabled(boolean z) {
        this.f4 = z;
    }

    public void setInterpolatedProgress(float f2) {
        if (this.X3 != null) {
            setState(TransitionState.MOVING);
            Interpolator interpolatorP = this.X3.p();
            if (interpolatorP != null) {
                setProgress(interpolatorP.getInterpolation(f2));
                return;
            }
        }
        setProgress(f2);
    }

    public void setOnHide(float f2) {
        ArrayList<MotionHelper> arrayList = this.L4;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.L4.get(i2).setProgress(f2);
            }
        }
    }

    public void setOnShow(float f2) {
        ArrayList<MotionHelper> arrayList = this.K4;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.K4.get(i2).setProgress(f2);
            }
        }
    }

    public void setProgress(float f2) {
        if (!isAttachedToWindow()) {
            if (this.d5 == null) {
                this.d5 = new h();
            }
            this.d5.e(f2);
            return;
        }
        if (f2 <= 0.0f) {
            this.b4 = this.a4;
            if (this.k4 == 0.0f) {
                setState(TransitionState.FINISHED);
            }
        } else if (f2 >= 1.0f) {
            this.b4 = this.c4;
            if (this.k4 == 1.0f) {
                setState(TransitionState.FINISHED);
            }
        } else {
            this.b4 = -1;
            setState(TransitionState.MOVING);
        }
        if (this.X3 == null) {
            return;
        }
        this.n4 = true;
        this.m4 = f2;
        this.j4 = f2;
        this.l4 = -1L;
        this.h4 = -1L;
        this.Y3 = null;
        this.o4 = true;
        invalidate();
    }

    public void setScene(q qVar) {
        this.X3 = qVar;
        qVar.L(p());
        m0();
    }

    public void setState(TransitionState transitionState) {
        TransitionState transitionState2 = TransitionState.FINISHED;
        if (transitionState == transitionState2 && this.b4 == -1) {
            return;
        }
        TransitionState transitionState3 = this.e5;
        this.e5 = transitionState;
        TransitionState transitionState4 = TransitionState.MOVING;
        if (transitionState3 == transitionState4 && transitionState == transitionState4) {
            a0();
        }
        int i2 = b.f287a[transitionState3.ordinal()];
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
        if (this.X3 != null) {
            q.b bVarE0 = e0(i2);
            this.a4 = bVarE0.A();
            this.c4 = bVarE0.y();
            if (!isAttachedToWindow()) {
                if (this.d5 == null) {
                    this.d5 = new h();
                }
                this.d5.f(this.a4);
                this.d5.d(this.c4);
                return;
            }
            float f2 = Float.NaN;
            int i3 = this.b4;
            if (i3 == this.a4) {
                f2 = 0.0f;
            } else if (i3 == this.c4) {
                f2 = 1.0f;
            }
            this.X3.N(bVarE0);
            this.f5.d(this.s, this.X3.i(this.a4), this.X3.i(this.c4));
            m0();
            this.k4 = Float.isNaN(f2) ? 0.0f : f2;
            if (!Float.isNaN(f2)) {
                setProgress(f2);
                return;
            }
            Log.v("MotionLayout", b.e.a.b.a.a() + " transitionToStart ");
            t0();
        }
    }

    public void setTransitionDuration(int i2) {
        q qVar = this.X3;
        if (qVar == null) {
            Log.e("MotionLayout", "MotionScene not defined");
        } else {
            qVar.K(i2);
        }
    }

    public void setTransitionListener(i iVar) {
        this.q4 = iVar;
    }

    public void setTransitionState(Bundle bundle) {
        if (this.d5 == null) {
            this.d5 = new h();
        }
        this.d5.g(bundle);
        if (isAttachedToWindow()) {
            this.d5.a();
        }
    }

    public void t0() {
        T(0.0f);
    }

    @Override // android.view.View
    public String toString() {
        Context context = getContext();
        return b.e.a.b.a.b(context, this.a4) + "->" + b.e.a.b.a.b(context, this.c4) + " (pos:" + this.k4 + " Dpos/Dt:" + this.Z3;
    }

    public void u0(int i2) {
        if (isAttachedToWindow()) {
            v0(i2, -1, -1);
            return;
        }
        if (this.d5 == null) {
            this.d5 = new h();
        }
        this.d5.d(i2);
    }

    public void v0(int i2, int i3, int i4) {
        b.e.c.f fVar;
        int iA;
        q qVar = this.X3;
        if (qVar != null && (fVar = qVar.f1306b) != null && (iA = fVar.a(this.b4, i2, i3, i4)) != -1) {
            i2 = iA;
        }
        int i5 = this.b4;
        if (i5 == i2) {
            return;
        }
        if (this.a4 == i2) {
            T(0.0f);
            return;
        }
        if (this.c4 == i2) {
            T(1.0f);
            return;
        }
        this.c4 = i2;
        if (i5 != -1) {
            p0(i5, i2);
            T(1.0f);
            this.k4 = 0.0f;
            s0();
            return;
        }
        this.v4 = false;
        this.m4 = 1.0f;
        this.j4 = 0.0f;
        this.k4 = 0.0f;
        this.l4 = getNanoTime();
        this.h4 = getNanoTime();
        this.n4 = false;
        this.Y3 = null;
        this.i4 = this.X3.m() / 1000.0f;
        this.a4 = -1;
        this.X3.M(-1, this.c4);
        this.X3.x();
        int childCount = getChildCount();
        this.g4.clear();
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            this.g4.put(childAt, new n(childAt));
        }
        this.o4 = true;
        this.f5.d(this.s, null, this.X3.i(i2));
        m0();
        this.f5.a();
        X();
        int width = getWidth();
        int height = getHeight();
        for (int i7 = 0; i7 < childCount; i7++) {
            n nVar = this.g4.get(getChildAt(i7));
            this.X3.q(nVar);
            nVar.v(width, height, this.i4, getNanoTime());
        }
        float fW = this.X3.w();
        if (fW != 0.0f) {
            float fMin = Float.MAX_VALUE;
            float fMax = -3.4028235E38f;
            for (int i8 = 0; i8 < childCount; i8++) {
                n nVar2 = this.g4.get(getChildAt(i8));
                float fJ = nVar2.j() + nVar2.i();
                fMin = Math.min(fMin, fJ);
                fMax = Math.max(fMax, fJ);
            }
            for (int i9 = 0; i9 < childCount; i9++) {
                n nVar3 = this.g4.get(getChildAt(i9));
                float fI = nVar3.i();
                float fJ2 = nVar3.j();
                nVar3.m = 1.0f / (1.0f - fW);
                nVar3.l = fW - ((((fI + fJ2) - fMin) * fW) / (fMax - fMin));
            }
        }
        this.j4 = 0.0f;
        this.k4 = 0.0f;
        this.o4 = true;
        invalidate();
    }

    public void setTransition(q.b bVar) {
        this.X3.N(bVar);
        setState(TransitionState.SETUP);
        if (this.b4 == this.X3.n()) {
            this.k4 = 1.0f;
            this.j4 = 1.0f;
            this.m4 = 1.0f;
        } else {
            this.k4 = 0.0f;
            this.j4 = 0.0f;
            this.m4 = 0.0f;
        }
        this.l4 = bVar.D(1) ? -1L : getNanoTime();
        int iX = this.X3.x();
        int iN = this.X3.n();
        if (iX == this.a4 && iN == this.c4) {
            return;
        }
        this.a4 = iX;
        this.c4 = iN;
        this.X3.M(iX, iN);
        this.f5.d(this.s, this.X3.i(this.a4), this.X3.i(this.c4));
        this.f5.h(this.a4, this.c4);
        this.f5.g();
        m0();
    }

    public MotionLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.Z3 = 0.0f;
        this.a4 = -1;
        this.b4 = -1;
        this.c4 = -1;
        this.d4 = 0;
        this.e4 = 0;
        this.f4 = true;
        this.g4 = new HashMap<>();
        this.h4 = 0L;
        this.i4 = 1.0f;
        this.j4 = 0.0f;
        this.k4 = 0.0f;
        this.m4 = 0.0f;
        this.o4 = false;
        this.p4 = false;
        this.t4 = 0;
        this.v4 = false;
        this.w4 = new b.e.a.a.g();
        this.x4 = new c();
        this.z4 = true;
        this.E4 = false;
        this.J4 = false;
        this.K4 = null;
        this.L4 = null;
        this.M4 = null;
        this.N4 = 0;
        this.O4 = -1L;
        this.P4 = 0.0f;
        this.Q4 = 0;
        this.R4 = 0.0f;
        this.S4 = false;
        this.T4 = false;
        this.b5 = new b.e.a.b.e();
        this.c5 = false;
        this.e5 = TransitionState.UNDEFINED;
        this.f5 = new e();
        this.g5 = false;
        this.h5 = new RectF();
        this.i5 = null;
        this.j5 = new ArrayList<>();
        h0(attributeSet);
    }

    public MotionLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.Z3 = 0.0f;
        this.a4 = -1;
        this.b4 = -1;
        this.c4 = -1;
        this.d4 = 0;
        this.e4 = 0;
        this.f4 = true;
        this.g4 = new HashMap<>();
        this.h4 = 0L;
        this.i4 = 1.0f;
        this.j4 = 0.0f;
        this.k4 = 0.0f;
        this.m4 = 0.0f;
        this.o4 = false;
        this.p4 = false;
        this.t4 = 0;
        this.v4 = false;
        this.w4 = new b.e.a.a.g();
        this.x4 = new c();
        this.z4 = true;
        this.E4 = false;
        this.J4 = false;
        this.K4 = null;
        this.L4 = null;
        this.M4 = null;
        this.N4 = 0;
        this.O4 = -1L;
        this.P4 = 0.0f;
        this.Q4 = 0;
        this.R4 = 0.0f;
        this.S4 = false;
        this.T4 = false;
        this.b5 = new b.e.a.b.e();
        this.c5 = false;
        this.e5 = TransitionState.UNDEFINED;
        this.f5 = new e();
        this.g5 = false;
        this.h5 = new RectF();
        this.i5 = null;
        this.j5 = new ArrayList<>();
        h0(attributeSet);
    }
}
