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
import androidx.constraintlayout.motion.widget.q;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class MotionLayout extends ConstraintLayout implements b.g.k.m {
    public static boolean y;
    Interpolator A;
    int A0;
    float B;
    int B0;
    private int C;
    float C0;
    int D;
    private androidx.constraintlayout.motion.widget.e D0;
    private int E;
    private boolean E0;
    private int F;
    private h F0;
    private int G;
    TransitionState G0;
    private boolean H;
    e H0;
    HashMap<View, n> I;
    private boolean I0;
    private long J;
    private RectF J0;
    private float K;
    private View K0;
    float L;
    ArrayList<Integer> L0;
    float M;
    private long N;
    float O;
    private boolean P;
    boolean Q;
    boolean R;
    private i S;
    private float T;
    private float U;
    int V;
    d W;
    private boolean X;
    private b.e.a.a.g Y;
    private c Z;
    private androidx.constraintlayout.motion.widget.b a0;
    boolean b0;
    int c0;
    int d0;
    int e0;
    int f0;
    boolean g0;
    float h0;
    float i0;
    long j0;
    float k0;
    private boolean l0;
    private ArrayList<MotionHelper> m0;
    private ArrayList<MotionHelper> n0;
    private ArrayList<i> o0;
    private int p0;
    private long q0;
    private float r0;
    private int s0;
    private float t0;
    boolean u0;
    protected boolean v0;
    int w0;
    int x0;
    int y0;
    q z;
    int z0;

    enum TransitionState {
        UNDEFINED,
        SETUP,
        MOVING,
        FINISHED
    }

    class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f750b;

        a(View view) {
            this.f750b = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f750b.setNestedScrollingEnabled(true);
        }
    }

    static /* synthetic */ class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f752a;

        static {
            int[] iArr = new int[TransitionState.values().length];
            f752a = iArr;
            try {
                iArr[TransitionState.UNDEFINED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f752a[TransitionState.SETUP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f752a[TransitionState.MOVING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f752a[TransitionState.FINISHED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    class c extends o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        float f753a = 0.0f;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        float f754b = 0.0f;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        float f755c;

        c() {
        }

        @Override // androidx.constraintlayout.motion.widget.o
        public float a() {
            return MotionLayout.this.B;
        }

        public void b(float f2, float f3, float f4) {
            this.f753a = f2;
            this.f754b = f3;
            this.f755c = f4;
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f2) {
            float f3;
            float f4;
            float f5 = this.f753a;
            if (f5 > 0.0f) {
                float f6 = this.f755c;
                if (f5 / f6 < f2) {
                    f2 = f5 / f6;
                }
                MotionLayout.this.B = f5 - (f6 * f2);
                f3 = (f5 * f2) - (((f6 * f2) * f2) / 2.0f);
                f4 = this.f754b;
            } else {
                float f7 = this.f755c;
                if ((-f5) / f7 < f2) {
                    f2 = (-f5) / f7;
                }
                MotionLayout.this.B = (f7 * f2) + f5;
                f3 = (f5 * f2) + (((f7 * f2) * f2) / 2.0f);
                f4 = this.f754b;
            }
            return f3 + f4;
        }
    }

    private class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        float[] f757a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int[] f758b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        float[] f759c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        Path f760d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        Paint f761e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        Paint f762f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        Paint f763g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        Paint f764h;
        Paint i;
        private float[] j;
        DashPathEffect p;
        int q;
        int t;
        final int k = -21965;
        final int l = -2067046;
        final int m = -13391360;
        final int n = 1996488704;
        final int o = 10;
        Rect r = new Rect();
        boolean s = false;

        public d() {
            this.t = 1;
            Paint paint = new Paint();
            this.f761e = paint;
            paint.setAntiAlias(true);
            this.f761e.setColor(-21965);
            this.f761e.setStrokeWidth(2.0f);
            this.f761e.setStyle(Paint.Style.STROKE);
            Paint paint2 = new Paint();
            this.f762f = paint2;
            paint2.setAntiAlias(true);
            this.f762f.setColor(-2067046);
            this.f762f.setStrokeWidth(2.0f);
            this.f762f.setStyle(Paint.Style.STROKE);
            Paint paint3 = new Paint();
            this.f763g = paint3;
            paint3.setAntiAlias(true);
            this.f763g.setColor(-13391360);
            this.f763g.setStrokeWidth(2.0f);
            this.f763g.setStyle(Paint.Style.STROKE);
            Paint paint4 = new Paint();
            this.f764h = paint4;
            paint4.setAntiAlias(true);
            this.f764h.setColor(-13391360);
            this.f764h.setTextSize(MotionLayout.this.getContext().getResources().getDisplayMetrics().density * 12.0f);
            this.j = new float[8];
            Paint paint5 = new Paint();
            this.i = paint5;
            paint5.setAntiAlias(true);
            DashPathEffect dashPathEffect = new DashPathEffect(new float[]{4.0f, 8.0f}, 0.0f);
            this.p = dashPathEffect;
            this.f763g.setPathEffect(dashPathEffect);
            this.f759c = new float[100];
            this.f758b = new int[50];
            if (this.s) {
                this.f761e.setStrokeWidth(8.0f);
                this.i.setStrokeWidth(8.0f);
                this.f762f.setStrokeWidth(8.0f);
                this.t = 4;
            }
        }

        private void c(Canvas canvas) {
            canvas.drawLines(this.f757a, this.f761e);
        }

        private void d(Canvas canvas) {
            boolean z = false;
            boolean z2 = false;
            for (int i = 0; i < this.q; i++) {
                int[] iArr = this.f758b;
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

        private void e(Canvas canvas) {
            float[] fArr = this.f757a;
            float f2 = fArr[0];
            float f3 = fArr[1];
            float f4 = fArr[fArr.length - 2];
            float f5 = fArr[fArr.length - 1];
            canvas.drawLine(Math.min(f2, f4), Math.max(f3, f5), Math.max(f2, f4), Math.max(f3, f5), this.f763g);
            canvas.drawLine(Math.min(f2, f4), Math.min(f3, f5), Math.min(f2, f4), Math.max(f3, f5), this.f763g);
        }

        private void f(Canvas canvas, float f2, float f3) {
            float[] fArr = this.f757a;
            float f4 = fArr[0];
            float f5 = fArr[1];
            float f6 = fArr[fArr.length - 2];
            float f7 = fArr[fArr.length - 1];
            float fMin = Math.min(f4, f6);
            float fMax = Math.max(f5, f7);
            float fMin2 = f2 - Math.min(f4, f6);
            float fMax2 = Math.max(f5, f7) - f3;
            String str = HttpUrl.FRAGMENT_ENCODE_SET + (((int) (((double) ((fMin2 * 100.0f) / Math.abs(f6 - f4))) + 0.5d)) / 100.0f);
            l(str, this.f764h);
            canvas.drawText(str, ((fMin2 / 2.0f) - (this.r.width() / 2)) + fMin, f3 - 20.0f, this.f764h);
            canvas.drawLine(f2, f3, Math.min(f4, f6), f3, this.f763g);
            String str2 = HttpUrl.FRAGMENT_ENCODE_SET + (((int) (((double) ((fMax2 * 100.0f) / Math.abs(f7 - f5))) + 0.5d)) / 100.0f);
            l(str2, this.f764h);
            canvas.drawText(str2, f2 + 5.0f, fMax - ((fMax2 / 2.0f) - (this.r.height() / 2)), this.f764h);
            canvas.drawLine(f2, f3, f2, Math.max(f5, f7), this.f763g);
        }

        private void g(Canvas canvas) {
            float[] fArr = this.f757a;
            canvas.drawLine(fArr[0], fArr[1], fArr[fArr.length - 2], fArr[fArr.length - 1], this.f763g);
        }

        private void h(Canvas canvas, float f2, float f3) {
            float[] fArr = this.f757a;
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
            String str = HttpUrl.FRAGMENT_ENCODE_SET + (((int) ((fHypot2 * 100.0f) / fHypot)) / 100.0f);
            l(str, this.f764h);
            canvas.drawTextOnPath(str, path, (fHypot2 / 2.0f) - (this.r.width() / 2), -20.0f, this.f764h);
            canvas.drawLine(f2, f3, f11, f12, this.f763g);
        }

        private void i(Canvas canvas, float f2, float f3, int i, int i2) {
            String str = HttpUrl.FRAGMENT_ENCODE_SET + (((int) (((double) (((f2 - (i / 2)) * 100.0f) / (MotionLayout.this.getWidth() - i))) + 0.5d)) / 100.0f);
            l(str, this.f764h);
            canvas.drawText(str, ((f2 / 2.0f) - (this.r.width() / 2)) + 0.0f, f3 - 20.0f, this.f764h);
            canvas.drawLine(f2, f3, Math.min(0.0f, 1.0f), f3, this.f763g);
            String str2 = HttpUrl.FRAGMENT_ENCODE_SET + (((int) (((double) (((f3 - (i2 / 2)) * 100.0f) / (MotionLayout.this.getHeight() - i2))) + 0.5d)) / 100.0f);
            l(str2, this.f764h);
            canvas.drawText(str2, f2 + 5.0f, 0.0f - ((f3 / 2.0f) - (this.r.height() / 2)), this.f764h);
            canvas.drawLine(f2, f3, f2, Math.max(0.0f, 1.0f), this.f763g);
        }

        private void j(Canvas canvas, n nVar) {
            this.f760d.reset();
            for (int i = 0; i <= 50; i++) {
                nVar.e(i / 50, this.j, 0);
                Path path = this.f760d;
                float[] fArr = this.j;
                path.moveTo(fArr[0], fArr[1]);
                Path path2 = this.f760d;
                float[] fArr2 = this.j;
                path2.lineTo(fArr2[2], fArr2[3]);
                Path path3 = this.f760d;
                float[] fArr3 = this.j;
                path3.lineTo(fArr3[4], fArr3[5]);
                Path path4 = this.f760d;
                float[] fArr4 = this.j;
                path4.lineTo(fArr4[6], fArr4[7]);
                this.f760d.close();
            }
            this.f761e.setColor(1140850688);
            canvas.translate(2.0f, 2.0f);
            canvas.drawPath(this.f760d, this.f761e);
            canvas.translate(-2.0f, -2.0f);
            this.f761e.setColor(-65536);
            canvas.drawPath(this.f760d, this.f761e);
        }

        private void k(Canvas canvas, int i, int i2, n nVar) {
            int width;
            int height;
            int i3;
            float f2;
            float f3;
            View view = nVar.f842a;
            if (view != null) {
                width = view.getWidth();
                height = nVar.f842a.getHeight();
            } else {
                width = 0;
                height = 0;
            }
            for (int i4 = 1; i4 < i2 - 1; i4++) {
                if (i != 4 || this.f758b[i4 - 1] != 0) {
                    float[] fArr = this.f759c;
                    int i5 = i4 * 2;
                    float f4 = fArr[i5];
                    float f5 = fArr[i5 + 1];
                    this.f760d.reset();
                    this.f760d.moveTo(f4, f5 + 10.0f);
                    this.f760d.lineTo(f4 + 10.0f, f5);
                    this.f760d.lineTo(f4, f5 - 10.0f);
                    this.f760d.lineTo(f4 - 10.0f, f5);
                    this.f760d.close();
                    int i6 = i4 - 1;
                    nVar.k(i6);
                    if (i == 4) {
                        int[] iArr = this.f758b;
                        if (iArr[i6] == 1) {
                            h(canvas, f4 - 0.0f, f5 - 0.0f);
                        } else if (iArr[i6] == 2) {
                            f(canvas, f4 - 0.0f, f5 - 0.0f);
                        } else {
                            if (iArr[i6] == 3) {
                                i3 = 3;
                                f2 = f5;
                                f3 = f4;
                                i(canvas, f4 - 0.0f, f5 - 0.0f, width, height);
                            }
                            canvas.drawPath(this.f760d, this.i);
                        }
                        i3 = 3;
                        f2 = f5;
                        f3 = f4;
                        canvas.drawPath(this.f760d, this.i);
                    } else {
                        i3 = 3;
                        f2 = f5;
                        f3 = f4;
                    }
                    if (i == 2) {
                        h(canvas, f3 - 0.0f, f2 - 0.0f);
                    }
                    if (i == i3) {
                        f(canvas, f3 - 0.0f, f2 - 0.0f);
                    }
                    if (i == 6) {
                        i(canvas, f3 - 0.0f, f2 - 0.0f, width, height);
                    }
                    canvas.drawPath(this.f760d, this.i);
                }
            }
            float[] fArr2 = this.f757a;
            if (fArr2.length > 1) {
                canvas.drawCircle(fArr2[0], fArr2[1], 8.0f, this.f762f);
                float[] fArr3 = this.f757a;
                canvas.drawCircle(fArr3[fArr3.length - 2], fArr3[fArr3.length - 1], 8.0f, this.f762f);
            }
        }

        public void a(Canvas canvas, HashMap<View, n> map, int i, int i2) {
            if (map == null || map.size() == 0) {
                return;
            }
            canvas.save();
            if (!MotionLayout.this.isInEditMode() && (i2 & 1) == 2) {
                String str = MotionLayout.this.getContext().getResources().getResourceName(MotionLayout.this.E) + ":" + MotionLayout.this.getProgress();
                canvas.drawText(str, 10.0f, MotionLayout.this.getHeight() - 30, this.f764h);
                canvas.drawText(str, 11.0f, MotionLayout.this.getHeight() - 29, this.f761e);
            }
            for (n nVar : map.values()) {
                int iH = nVar.h();
                if (i2 > 0 && iH == 0) {
                    iH = 1;
                }
                if (iH != 0) {
                    this.q = nVar.c(this.f759c, this.f758b);
                    if (iH >= 1) {
                        int i3 = i / 16;
                        float[] fArr = this.f757a;
                        if (fArr == null || fArr.length != i3 * 2) {
                            this.f757a = new float[i3 * 2];
                            this.f760d = new Path();
                        }
                        int i4 = this.t;
                        canvas.translate(i4, i4);
                        this.f761e.setColor(1996488704);
                        this.i.setColor(1996488704);
                        this.f762f.setColor(1996488704);
                        this.f763g.setColor(1996488704);
                        nVar.d(this.f757a, i3);
                        b(canvas, iH, this.q, nVar);
                        this.f761e.setColor(-21965);
                        this.f762f.setColor(-2067046);
                        this.i.setColor(-2067046);
                        this.f763g.setColor(-13391360);
                        int i5 = this.t;
                        canvas.translate(-i5, -i5);
                        b(canvas, iH, this.q, nVar);
                        if (iH == 5) {
                            j(canvas, nVar);
                        }
                    }
                }
            }
            canvas.restore();
        }

        public void b(Canvas canvas, int i, int i2, n nVar) {
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
            k(canvas, i, i2, nVar);
        }

        void l(String str, Paint paint) {
            paint.getTextBounds(str, 0, str.length(), this.r);
        }
    }

    class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        androidx.constraintlayout.solver.widgets.d f765a = new androidx.constraintlayout.solver.widgets.d();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        androidx.constraintlayout.solver.widgets.d f766b = new androidx.constraintlayout.solver.widgets.d();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        androidx.constraintlayout.widget.b f767c = null;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        androidx.constraintlayout.widget.b f768d = null;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f769e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        int f770f;

        e() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        private void i(androidx.constraintlayout.solver.widgets.d dVar, androidx.constraintlayout.widget.b bVar) {
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
                if (constraintWidget3 instanceof androidx.constraintlayout.solver.widgets.j) {
                    ConstraintHelper constraintHelper = (ConstraintHelper) constraintWidget3.r();
                    androidx.constraintlayout.solver.widgets.g gVar = (androidx.constraintlayout.solver.widgets.g) constraintWidget3;
                    constraintHelper.s(dVar, gVar, sparseArray);
                    ((androidx.constraintlayout.solver.widgets.j) gVar).M0();
                }
            }
        }

        public void a() {
            int childCount = MotionLayout.this.getChildCount();
            MotionLayout.this.I.clear();
            for (int i = 0; i < childCount; i++) {
                View childAt = MotionLayout.this.getChildAt(i);
                MotionLayout.this.I.put(childAt, new n(childAt));
            }
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt2 = MotionLayout.this.getChildAt(i2);
                n nVar = MotionLayout.this.I.get(childAt2);
                if (nVar != null) {
                    if (this.f767c != null) {
                        ConstraintWidget constraintWidgetC = c(this.f765a, childAt2);
                        if (constraintWidgetC != null) {
                            nVar.t(constraintWidgetC, this.f767c);
                        } else if (MotionLayout.this.V != 0) {
                            Log.e("MotionLayout", androidx.constraintlayout.motion.widget.a.a() + "no widget for  " + androidx.constraintlayout.motion.widget.a.c(childAt2) + " (" + childAt2.getClass().getName() + ")");
                        }
                    }
                    if (this.f768d != null) {
                        ConstraintWidget constraintWidgetC2 = c(this.f766b, childAt2);
                        if (constraintWidgetC2 != null) {
                            nVar.q(constraintWidgetC2, this.f768d);
                        } else if (MotionLayout.this.V != 0) {
                            Log.e("MotionLayout", androidx.constraintlayout.motion.widget.a.a() + "no widget for  " + androidx.constraintlayout.motion.widget.a.c(childAt2) + " (" + childAt2.getClass().getName() + ")");
                        }
                    }
                }
            }
        }

        void b(androidx.constraintlayout.solver.widgets.d dVar, androidx.constraintlayout.solver.widgets.d dVar2) {
            ArrayList<ConstraintWidget> arrayListL0 = dVar.L0();
            HashMap<ConstraintWidget, ConstraintWidget> map = new HashMap<>();
            map.put(dVar, dVar2);
            dVar2.L0().clear();
            dVar2.l(dVar, map);
            for (ConstraintWidget constraintWidget : arrayListL0) {
                ConstraintWidget aVar = constraintWidget instanceof androidx.constraintlayout.solver.widgets.a ? new androidx.constraintlayout.solver.widgets.a() : constraintWidget instanceof androidx.constraintlayout.solver.widgets.f ? new androidx.constraintlayout.solver.widgets.f() : constraintWidget instanceof androidx.constraintlayout.solver.widgets.e ? new androidx.constraintlayout.solver.widgets.e() : constraintWidget instanceof androidx.constraintlayout.solver.widgets.g ? new androidx.constraintlayout.solver.widgets.h() : new ConstraintWidget();
                dVar2.a(aVar);
                map.put(constraintWidget, aVar);
            }
            for (ConstraintWidget constraintWidget2 : arrayListL0) {
                map.get(constraintWidget2).l(constraintWidget2, map);
            }
        }

        ConstraintWidget c(androidx.constraintlayout.solver.widgets.d dVar, View view) {
            if (dVar.r() == view) {
                return dVar;
            }
            ArrayList<ConstraintWidget> arrayListL0 = dVar.L0();
            int size = arrayListL0.size();
            for (int i = 0; i < size; i++) {
                ConstraintWidget constraintWidget = arrayListL0.get(i);
                if (constraintWidget.r() == view) {
                    return constraintWidget;
                }
            }
            return null;
        }

        void d(androidx.constraintlayout.solver.widgets.d dVar, androidx.constraintlayout.widget.b bVar, androidx.constraintlayout.widget.b bVar2) {
            this.f767c = bVar;
            this.f768d = bVar2;
            this.f765a = new androidx.constraintlayout.solver.widgets.d();
            this.f766b = new androidx.constraintlayout.solver.widgets.d();
            this.f765a.h1(((ConstraintLayout) MotionLayout.this).f1089d.W0());
            this.f766b.h1(((ConstraintLayout) MotionLayout.this).f1089d.W0());
            this.f765a.O0();
            this.f766b.O0();
            b(((ConstraintLayout) MotionLayout.this).f1089d, this.f765a);
            b(((ConstraintLayout) MotionLayout.this).f1089d, this.f766b);
            if (MotionLayout.this.M > 0.5d) {
                if (bVar != null) {
                    i(this.f765a, bVar);
                }
                i(this.f766b, bVar2);
            } else {
                i(this.f766b, bVar2);
                if (bVar != null) {
                    i(this.f765a, bVar);
                }
            }
            this.f765a.j1(MotionLayout.this.p());
            this.f765a.l1();
            this.f766b.j1(MotionLayout.this.p());
            this.f766b.l1();
            ViewGroup.LayoutParams layoutParams = MotionLayout.this.getLayoutParams();
            if (layoutParams != null) {
                if (layoutParams.width == -2) {
                    androidx.constraintlayout.solver.widgets.d dVar2 = this.f765a;
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    dVar2.m0(dimensionBehaviour);
                    this.f766b.m0(dimensionBehaviour);
                }
                if (layoutParams.height == -2) {
                    androidx.constraintlayout.solver.widgets.d dVar3 = this.f765a;
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    dVar3.B0(dimensionBehaviour2);
                    this.f766b.B0(dimensionBehaviour2);
                }
            }
        }

        public boolean e(int i, int i2) {
            return (i == this.f769e && i2 == this.f770f) ? false : true;
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
                MotionLayout.this.t(this.f766b, optimizationLevel, i, i2);
                if (this.f767c != null) {
                    MotionLayout.this.t(this.f765a, optimizationLevel, i, i2);
                }
            } else {
                if (this.f767c != null) {
                    MotionLayout.this.t(this.f765a, optimizationLevel, i, i2);
                }
                MotionLayout.this.t(this.f766b, optimizationLevel, i, i2);
            }
            if (((MotionLayout.this.getParent() instanceof MotionLayout) && mode == 1073741824 && mode2 == 1073741824) ? false : true) {
                MotionLayout motionLayout3 = MotionLayout.this;
                motionLayout3.A0 = mode;
                motionLayout3.B0 = mode2;
                if (motionLayout3.D == motionLayout3.getStartState()) {
                    MotionLayout.this.t(this.f766b, optimizationLevel, i, i2);
                    if (this.f767c != null) {
                        MotionLayout.this.t(this.f765a, optimizationLevel, i, i2);
                    }
                } else {
                    if (this.f767c != null) {
                        MotionLayout.this.t(this.f765a, optimizationLevel, i, i2);
                    }
                    MotionLayout.this.t(this.f766b, optimizationLevel, i, i2);
                }
                MotionLayout.this.w0 = this.f765a.Q();
                MotionLayout.this.x0 = this.f765a.w();
                MotionLayout.this.y0 = this.f766b.Q();
                MotionLayout.this.z0 = this.f766b.w();
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
            MotionLayout.this.s(i, i2, i3, i4, this.f765a.d1() || this.f766b.d1(), this.f765a.b1() || this.f766b.b1());
        }

        public void g() {
            f(MotionLayout.this.F, MotionLayout.this.G);
            MotionLayout.this.q0();
        }

        public void h(int i, int i2) {
            this.f769e = i;
            this.f770f = i2;
        }
    }

    protected interface f {
        void a(MotionEvent motionEvent);

        float b();

        float c();

        void d();

        void e(int i);
    }

    private static class g implements f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static g f772a = new g();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        VelocityTracker f773b;

        private g() {
        }

        public static g f() {
            f772a.f773b = VelocityTracker.obtain();
            return f772a;
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.f
        public void a(MotionEvent motionEvent) {
            VelocityTracker velocityTracker = this.f773b;
            if (velocityTracker != null) {
                velocityTracker.addMovement(motionEvent);
            }
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.f
        public float b() {
            return this.f773b.getYVelocity();
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.f
        public float c() {
            return this.f773b.getXVelocity();
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.f
        public void d() {
            this.f773b.recycle();
            this.f773b = null;
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.f
        public void e(int i) {
            this.f773b.computeCurrentVelocity(i);
        }
    }

    class h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        float f774a = Float.NaN;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        float f775b = Float.NaN;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f776c = -1;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f777d = -1;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final String f778e = "motion.progress";

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final String f779f = "motion.velocity";

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final String f780g = "motion.StartState";

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        final String f781h = "motion.EndState";

        h() {
        }

        void a() {
            int i = this.f776c;
            if (i != -1 || this.f777d != -1) {
                if (i == -1) {
                    MotionLayout.this.u0(this.f777d);
                } else {
                    int i2 = this.f777d;
                    if (i2 == -1) {
                        MotionLayout.this.o0(i, -1, -1);
                    } else {
                        MotionLayout.this.p0(i, i2);
                    }
                }
                MotionLayout.this.setState(TransitionState.SETUP);
            }
            if (Float.isNaN(this.f775b)) {
                if (Float.isNaN(this.f774a)) {
                    return;
                }
                MotionLayout.this.setProgress(this.f774a);
            } else {
                MotionLayout.this.n0(this.f774a, this.f775b);
                this.f774a = Float.NaN;
                this.f775b = Float.NaN;
                this.f776c = -1;
                this.f777d = -1;
            }
        }

        public Bundle b() {
            Bundle bundle = new Bundle();
            bundle.putFloat("motion.progress", this.f774a);
            bundle.putFloat("motion.velocity", this.f775b);
            bundle.putInt("motion.StartState", this.f776c);
            bundle.putInt("motion.EndState", this.f777d);
            return bundle;
        }

        public void c() {
            this.f777d = MotionLayout.this.E;
            this.f776c = MotionLayout.this.C;
            this.f775b = MotionLayout.this.getVelocity();
            this.f774a = MotionLayout.this.getProgress();
        }

        public void d(int i) {
            this.f777d = i;
        }

        public void e(float f2) {
            this.f774a = f2;
        }

        public void f(int i) {
            this.f776c = i;
        }

        public void g(Bundle bundle) {
            this.f774a = bundle.getFloat("motion.progress");
            this.f775b = bundle.getFloat("motion.velocity");
            this.f776c = bundle.getInt("motion.StartState");
            this.f777d = bundle.getInt("motion.EndState");
        }

        public void h(float f2) {
            this.f775b = f2;
        }
    }

    public interface i {
        void a(MotionLayout motionLayout, int i, int i2);

        void b(MotionLayout motionLayout, int i, boolean z, float f2);

        void c(MotionLayout motionLayout, int i, int i2, float f2);

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
        this.Y = new b.e.a.a.g();
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
        this.D0 = new androidx.constraintlayout.motion.widget.e();
        this.E0 = false;
        this.G0 = TransitionState.UNDEFINED;
        this.H0 = new e();
        this.I0 = false;
        this.J0 = new RectF();
        this.K0 = null;
        this.L0 = new ArrayList<>();
        h0(null);
    }

    private void U() {
        q qVar = this.z;
        if (qVar == null) {
            Log.e("MotionLayout", "CHECK: motion scene not set! set \"app:layoutDescription=\"@xml/file\"");
            return;
        }
        int iX = qVar.x();
        q qVar2 = this.z;
        V(iX, qVar2.i(qVar2.x()));
        SparseIntArray sparseIntArray = new SparseIntArray();
        SparseIntArray sparseIntArray2 = new SparseIntArray();
        for (q.b bVar : this.z.l()) {
            if (bVar == this.z.f859c) {
                Log.v("MotionLayout", "CHECK: CURRENT");
            }
            W(bVar);
            int iA = bVar.A();
            int iY = bVar.y();
            String strB = androidx.constraintlayout.motion.widget.a.b(getContext(), iA);
            String strB2 = androidx.constraintlayout.motion.widget.a.b(getContext(), iY);
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

    private void V(int i2, androidx.constraintlayout.widget.b bVar) {
        String strB = androidx.constraintlayout.motion.widget.a.b(getContext(), i2);
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            int id = childAt.getId();
            if (id == -1) {
                Log.w("MotionLayout", "CHECK: " + strB + " ALL VIEWS SHOULD HAVE ID's " + childAt.getClass().getName() + " does not!");
            }
            if (bVar.n(id) == null) {
                Log.w("MotionLayout", "CHECK: " + strB + " NO CONSTRAINTS for " + androidx.constraintlayout.motion.widget.a.c(childAt));
            }
        }
        int[] iArrP = bVar.p();
        for (int i4 = 0; i4 < iArrP.length; i4++) {
            int i5 = iArrP[i4];
            String strB2 = androidx.constraintlayout.motion.widget.a.b(getContext(), i5);
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

    private void W(q.b bVar) {
        Log.v("MotionLayout", "CHECK: transition = " + bVar.u(getContext()));
        Log.v("MotionLayout", "CHECK: transition.setDuration = " + bVar.x());
        if (bVar.A() == bVar.y()) {
            Log.e("MotionLayout", "CHECK: start and end constraint set should not be the same!");
        }
    }

    private void X() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            n nVar = this.I.get(childAt);
            if (nVar != null) {
                nVar.s(childAt);
            }
        }
    }

    private void Z() {
        boolean z;
        float fSignum = Math.signum(this.O - this.M);
        long nanoTime = getNanoTime();
        Interpolator interpolator = this.A;
        float interpolation = this.M + (!(interpolator instanceof b.e.a.a.g) ? (((nanoTime - this.N) * fSignum) * 1.0E-9f) / this.K : 0.0f);
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
            n nVar = this.I.get(childAt);
            if (nVar != null) {
                nVar.o(childAt, interpolation, nanoTime2, this.D0);
            }
        }
        if (this.v0) {
            requestLayout();
        }
    }

    private void a0() {
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

    private boolean g0(float f2, float f3, View view, MotionEvent motionEvent) {
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

    private void h0(AttributeSet attributeSet) {
        q qVar;
        y = isInEditMode();
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, androidx.constraintlayout.widget.e.H6);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            boolean z = true;
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                if (index == androidx.constraintlayout.widget.e.K6) {
                    this.z = new q(getContext(), this, typedArrayObtainStyledAttributes.getResourceId(index, -1));
                } else if (index == androidx.constraintlayout.widget.e.J6) {
                    this.D = typedArrayObtainStyledAttributes.getResourceId(index, -1);
                } else if (index == androidx.constraintlayout.widget.e.M6) {
                    this.O = typedArrayObtainStyledAttributes.getFloat(index, 0.0f);
                    this.Q = true;
                } else if (index == androidx.constraintlayout.widget.e.I6) {
                    z = typedArrayObtainStyledAttributes.getBoolean(index, z);
                } else if (index == androidx.constraintlayout.widget.e.N6) {
                    if (this.V == 0) {
                        this.V = typedArrayObtainStyledAttributes.getBoolean(index, false) ? 2 : 0;
                    }
                } else if (index == androidx.constraintlayout.widget.e.L6) {
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
        if (this.D != -1 || (qVar = this.z) == null) {
            return;
        }
        this.D = qVar.x();
        this.C = this.z.x();
        this.E = this.z.n();
    }

    private void k0() {
        q qVar = this.z;
        if (qVar == null) {
            return;
        }
        if (qVar.f(this, this.D)) {
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

    private void l0() {
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

    /* JADX INFO: Access modifiers changed from: private */
    public void q0() {
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
                n nVar = this.I.get(getChildAt(i3));
                if (nVar != null) {
                    nVar.r(iH);
                }
            }
        }
        for (int i4 = 0; i4 < childCount; i4++) {
            n nVar2 = this.I.get(getChildAt(i4));
            if (nVar2 != null) {
                this.z.q(nVar2);
                nVar2.v(width, height, this.K, getNanoTime());
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
                n nVar3 = this.I.get(getChildAt(i5));
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
                    n nVar4 = this.I.get(getChildAt(i2));
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
                n nVar5 = this.I.get(getChildAt(i6));
                if (!Float.isNaN(nVar5.k)) {
                    fMin = Math.min(fMin, nVar5.k);
                    fMax = Math.max(fMax, nVar5.k);
                }
            }
            while (i2 < childCount) {
                n nVar6 = this.I.get(getChildAt(i2));
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

    private static boolean w0(float f2, float f3, float f4) {
        if (f2 > 0.0f) {
            float f5 = f2 / f4;
            return f3 + ((f2 * f5) - (((f4 * f5) * f5) / 2.0f)) > 1.0f;
        }
        float f6 = (-f2) / f4;
        return f3 + ((f2 * f6) + (((f4 * f6) * f6) / 2.0f)) < 0.0f;
    }

    void T(float f2) {
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

    void Y(boolean z) {
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
            if (interpolator instanceof o) {
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
                            f4 = 0.0f;
                        }
                    }
                } else {
                    interpolation = interpolator.getInterpolation(f4);
                    Interpolator interpolator3 = this.A;
                    if (interpolator3 instanceof o) {
                        this.B = ((o) interpolator3).a();
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
                n nVar = this.I.get(childAt);
                if (nVar != null) {
                    this.l0 = nVar.o(childAt, f4, nanoTime2, this.D0) | this.l0;
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

    protected void b0() {
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

    void d0(int i2, float f2, float f3, float f4, float[] fArr) {
        String resourceName;
        HashMap<View, n> map = this.I;
        View viewG = g(i2);
        n nVar = map.get(viewG);
        if (nVar != null) {
            nVar.g(f2, f3, f4, fArr);
            float y2 = viewG.getY();
            int i3 = ((f2 - this.T) > 0.0f ? 1 : ((f2 - this.T) == 0.0f ? 0 : -1));
            this.T = f2;
            this.U = y2;
            return;
        }
        if (viewG == null) {
            resourceName = HttpUrl.FRAGMENT_ENCODE_SET + i2;
        } else {
            resourceName = viewG.getContext().getResources().getResourceName(i2);
        }
        Log.w("MotionLayout", "WARNING could not find view id " + resourceName);
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
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
            String str = this.r0 + " fps " + androidx.constraintlayout.motion.widget.a.d(this, this.C) + " -> ";
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(androidx.constraintlayout.motion.widget.a.d(this, this.E));
            sb.append(" (progress: ");
            sb.append(((int) (getProgress() * 1000.0f)) / 10.0f);
            sb.append(" ) state=");
            int i2 = this.D;
            sb.append(i2 == -1 ? "undefined" : androidx.constraintlayout.motion.widget.a.d(this, i2));
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

    public q.b e0(int i2) {
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
        if (interpolator instanceof o) {
            fA = ((o) interpolator).a();
        }
        n nVar = this.I.get(view);
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

    public int[] getConstraintSetIds() {
        q qVar = this.z;
        if (qVar == null) {
            return null;
        }
        return qVar.k();
    }

    public int getCurrentState() {
        return this.D;
    }

    public ArrayList<q.b> getDefinedTransitions() {
        q qVar = this.z;
        if (qVar == null) {
            return null;
        }
        return qVar.l();
    }

    public androidx.constraintlayout.motion.widget.b getDesignTool() {
        if (this.a0 == null) {
            this.a0 = new androidx.constraintlayout.motion.widget.b(this);
        }
        return this.a0;
    }

    public int getEndState() {
        return this.E;
    }

    protected long getNanoTime() {
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

    @Override // b.g.k.l
    public void h(View view, View view2, int i2, int i3) {
    }

    @Override // b.g.k.l
    public void i(View view, int i2) {
        q qVar = this.z;
        if (qVar == null) {
            return;
        }
        float f2 = this.h0;
        float f3 = this.k0;
        qVar.G(f2 / f3, this.i0 / f3);
    }

    public boolean i0() {
        return this.H;
    }

    @Override // android.view.View
    public boolean isAttachedToWindow() {
        return Build.VERSION.SDK_INT >= 19 ? super.isAttachedToWindow() : getWindowToken() != null;
    }

    @Override // b.g.k.l
    public void j(View view, int i2, int i3, int[] iArr, int i4) {
        q.b bVar;
        t tVarB;
        int iK;
        q qVar = this.z;
        if (qVar == null || (bVar = qVar.f859c) == null || !bVar.C()) {
            return;
        }
        q.b bVar2 = this.z.f859c;
        if (bVar2 == null || !bVar2.C() || (tVarB = bVar2.B()) == null || (iK = tVarB.k()) == -1 || view.getId() == iK) {
            q qVar2 = this.z;
            if (qVar2 != null && qVar2.t()) {
                float f2 = this.L;
                if ((f2 == 1.0f || f2 == 0.0f) && view.canScrollVertically(-1)) {
                    return;
                }
            }
            if (bVar2.B() != null && (this.z.f859c.B().d() & 1) != 0) {
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

    protected f j0() {
        return g.f();
    }

    @Override // b.g.k.m
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

    @Override // b.g.k.l
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

    @Override // b.g.k.l
    public boolean o(View view, View view2, int i2, int i3) {
        q.b bVar;
        q qVar = this.z;
        return (qVar == null || (bVar = qVar.f859c) == null || bVar.B() == null || (this.z.f859c.B().d() & 2) != 0) ? false : true;
    }

    public void o0(int i2, int i3, int i4) {
        setState(TransitionState.SETUP);
        this.D = i2;
        this.C = -1;
        this.E = -1;
        androidx.constraintlayout.widget.a aVar = this.l;
        if (aVar != null) {
            aVar.d(i2, i3, i4);
            return;
        }
        q qVar = this.z;
        if (qVar != null) {
            qVar.i(i2).d(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        int i2;
        super.onAttachedToWindow();
        q qVar = this.z;
        if (qVar != null && (i2 = this.D) != -1) {
            androidx.constraintlayout.widget.b bVarI = qVar.i(i2);
            this.z.J(this);
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
        q qVar = this.z;
        if (qVar != null && this.H && (bVar = qVar.f859c) != null && bVar.C() && (tVarB = bVar.B()) != null && ((motionEvent.getAction() != 0 || (rectFJ = tVarB.j(this, new RectF())) == null || rectFJ.contains(motionEvent.getX(), motionEvent.getY())) && (iK = tVarB.k()) != -1)) {
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
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
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
    protected void onMeasure(int i2, int i3) {
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
            this.H0.d(this.f1089d, this.z.i(iX), this.z.i(iN));
            this.H0.g();
            this.H0.h(iX, iN);
        } else {
            z = true;
        }
        if (this.v0 || z) {
            int paddingTop = getPaddingTop() + getPaddingBottom();
            int iQ = this.f1089d.Q() + getPaddingLeft() + getPaddingRight();
            int iW = this.f1089d.w() + paddingTop;
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

    @Override // android.view.ViewGroup, android.view.ViewParent, b.g.k.n
    public boolean onNestedFling(View view, float f2, float f3, boolean z) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, b.g.k.n
    public boolean onNestedPreFling(View view, float f2, float f3) {
        return false;
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i2) {
        q qVar = this.z;
        if (qVar != null) {
            qVar.L(p());
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        q qVar = this.z;
        if (qVar == null || !this.H || !qVar.Q()) {
            return super.onTouchEvent(motionEvent);
        }
        q.b bVar = this.z.f859c;
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
        q qVar = this.z;
        if (qVar != null) {
            this.C = i2;
            this.E = i3;
            qVar.M(i2, i3);
            this.H0.d(this.f1089d, this.z.i(i2), this.z.i(i3));
            m0();
            this.M = 0.0f;
            t0();
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout
    protected void r(int i2) {
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
        q qVar;
        q.b bVar;
        if (this.v0 || this.D != -1 || (qVar = this.z) == null || (bVar = qVar.f859c) == null || bVar.z() != 0) {
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

    public void setScene(q qVar) {
        this.z = qVar;
        qVar.L(p());
        m0();
    }

    void setState(TransitionState transitionState) {
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
        int i2 = b.f752a[transitionState3.ordinal()];
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
            q.b bVarE0 = e0(i2);
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
            this.H0.d(this.f1089d, this.z.i(this.C), this.z.i(this.E));
            m0();
            this.M = Float.isNaN(f2) ? 0.0f : f2;
            if (!Float.isNaN(f2)) {
                setProgress(f2);
                return;
            }
            Log.v("MotionLayout", androidx.constraintlayout.motion.widget.a.a() + " transitionToStart ");
            t0();
        }
    }

    public void setTransitionDuration(int i2) {
        q qVar = this.z;
        if (qVar == null) {
            Log.e("MotionLayout", "MotionScene not defined");
        } else {
            qVar.K(i2);
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
        return androidx.constraintlayout.motion.widget.a.b(context, this.C) + "->" + androidx.constraintlayout.motion.widget.a.b(context, this.E) + " (pos:" + this.M + " Dpos/Dt:" + this.B;
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
        androidx.constraintlayout.widget.f fVar;
        int iA;
        q qVar = this.z;
        if (qVar != null && (fVar = qVar.f858b) != null && (iA = fVar.a(this.D, i2, i3, i4)) != -1) {
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
            this.I.put(childAt, new n(childAt));
        }
        this.Q = true;
        this.H0.d(this.f1089d, null, this.z.i(i2));
        m0();
        this.H0.a();
        X();
        int width = getWidth();
        int height = getHeight();
        for (int i7 = 0; i7 < childCount; i7++) {
            n nVar = this.I.get(getChildAt(i7));
            this.z.q(nVar);
            nVar.v(width, height, this.K, getNanoTime());
        }
        float fW = this.z.w();
        if (fW != 0.0f) {
            float fMin = Float.MAX_VALUE;
            float fMax = -3.4028235E38f;
            for (int i8 = 0; i8 < childCount; i8++) {
                n nVar2 = this.I.get(getChildAt(i8));
                float fJ = nVar2.j() + nVar2.i();
                fMin = Math.min(fMin, fJ);
                fMax = Math.max(fMax, fJ);
            }
            for (int i9 = 0; i9 < childCount; i9++) {
                n nVar3 = this.I.get(getChildAt(i9));
                float fI = nVar3.i();
                float fJ2 = nVar3.j();
                nVar3.m = 1.0f / (1.0f - fW);
                nVar3.l = fW - ((((fI + fJ2) - fMin) * fW) / (fMax - fMin));
            }
        }
        this.L = 0.0f;
        this.M = 0.0f;
        this.Q = true;
        invalidate();
    }

    protected void setTransition(q.b bVar) {
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
        this.H0.d(this.f1089d, this.z.i(this.C), this.z.i(this.E));
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
        this.Y = new b.e.a.a.g();
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
        this.D0 = new androidx.constraintlayout.motion.widget.e();
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
        this.Y = new b.e.a.a.g();
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
        this.D0 = new androidx.constraintlayout.motion.widget.e();
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
