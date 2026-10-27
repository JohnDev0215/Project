package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.view.Display;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.motion.widget.q;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class MotionLayout extends ConstraintLayout implements b.g.k.m {
    public static boolean z;
    q A;
    int A0;
    Interpolator B;
    int B0;
    Interpolator C;
    int C0;
    float D;
    int D0;
    private int E;
    int E0;
    int F;
    float F0;
    private int G;
    private androidx.constraintlayout.core.j.a.d G0;
    private int H;
    private boolean H0;
    private int I;
    private i I0;
    private boolean J;
    private Runnable J0;
    HashMap<View, n> K;
    private int[] K0;
    private long L;
    int L0;
    private float M;
    private boolean M0;
    float N;
    int N0;
    float O;
    HashMap<View, b.e.a.a.e> O0;
    private long P;
    private int P0;
    float Q;
    private int Q0;
    private boolean R;
    private int R0;
    boolean S;
    Rect S0;
    boolean T;
    private boolean T0;
    private j U;
    TransitionState U0;
    private float V;
    f V0;
    private float W;
    private boolean W0;
    int X;
    private RectF X0;
    e Y;
    private View Y0;
    private boolean Z;
    private Matrix Z0;
    private b.e.a.a.b a0;
    ArrayList<Integer> a1;
    private d b0;
    private androidx.constraintlayout.motion.widget.c c0;
    boolean d0;
    int e0;
    int f0;
    int g0;
    int h0;
    boolean i0;
    float j0;
    float k0;
    long l0;
    float m0;
    private boolean n0;
    private ArrayList<MotionHelper> o0;
    private ArrayList<MotionHelper> p0;
    private ArrayList<MotionHelper> q0;
    private CopyOnWriteArrayList<j> r0;
    private int s0;
    private long t0;
    private float u0;
    private int v0;
    private float w0;
    boolean x0;
    protected boolean y0;
    int z0;

    enum TransitionState {
        UNDEFINED,
        SETUP,
        MOVING,
        FINISHED
    }

    class a implements Runnable {

        /* JADX INFO: renamed from: b */
        final /* synthetic */ View f893b;

        a(View view) {
            this.f893b = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f893b.setNestedScrollingEnabled(true);
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            MotionLayout.this.I0.a();
        }
    }

    static /* synthetic */ class c {

        /* JADX INFO: renamed from: a */
        static final /* synthetic */ int[] f896a;

        static {
            int[] iArr = new int[TransitionState.values().length];
            f896a = iArr;
            try {
                iArr[TransitionState.UNDEFINED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f896a[TransitionState.SETUP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f896a[TransitionState.MOVING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f896a[TransitionState.FINISHED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    class d extends o {

        /* JADX INFO: renamed from: a */
        float f897a = 0.0f;

        /* JADX INFO: renamed from: b */
        float f898b = 0.0f;

        /* JADX INFO: renamed from: c */
        float f899c;

        d() {
        }

        @Override // androidx.constraintlayout.motion.widget.o
        public float a() {
            return MotionLayout.this.D;
        }

        public void b(float f, float f2, float f3) {
            this.f897a = f;
            this.f898b = f2;
            this.f899c = f3;
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            float f2;
            float f3;
            float f4 = this.f897a;
            if (f4 > 0.0f) {
                float f5 = this.f899c;
                if (f4 / f5 < f) {
                    f = f4 / f5;
                }
                MotionLayout.this.D = f4 - (f5 * f);
                f2 = (f4 * f) - (((f5 * f) * f) / 2.0f);
                f3 = this.f898b;
            } else {
                float f6 = this.f899c;
                if ((-f4) / f6 < f) {
                    f = (-f4) / f6;
                }
                MotionLayout.this.D = (f6 * f) + f4;
                f2 = (f4 * f) + (((f6 * f) * f) / 2.0f);
                f3 = this.f898b;
            }
            return f2 + f3;
        }
    }

    private class e {

        /* JADX INFO: renamed from: a */
        float[] f901a;

        /* JADX INFO: renamed from: b */
        int[] f902b;

        /* JADX INFO: renamed from: c */
        float[] f903c;

        /* JADX INFO: renamed from: d */
        Path f904d;

        /* JADX INFO: renamed from: e */
        Paint f905e;
        Paint f;
        Paint g;
        Paint h;
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

        public e() {
            this.t = 1;
            Paint paint = new Paint();
            this.f905e = paint;
            paint.setAntiAlias(true);
            this.f905e.setColor(-21965);
            this.f905e.setStrokeWidth(2.0f);
            this.f905e.setStyle(Paint.Style.STROKE);
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
            this.f903c = new float[100];
            this.f902b = new int[50];
            if (this.s) {
                this.f905e.setStrokeWidth(8.0f);
                this.i.setStrokeWidth(8.0f);
                this.f.setStrokeWidth(8.0f);
                this.t = 4;
            }
        }

        private void c(Canvas canvas) {
            canvas.drawLines(this.f901a, this.f905e);
        }

        private void d(Canvas canvas) {
            boolean z = false;
            boolean z2 = false;
            for (int i = 0; i < this.q; i++) {
                int[] iArr = this.f902b;
                if (iArr[i] == 1) {
                    z = true;
                }
                if (iArr[i] == 0) {
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
            float[] fArr = this.f901a;
            float f = fArr[0];
            float f2 = fArr[1];
            float f3 = fArr[fArr.length - 2];
            float f4 = fArr[fArr.length - 1];
            canvas.drawLine(Math.min(f, f3), Math.max(f2, f4), Math.max(f, f3), Math.max(f2, f4), this.g);
            canvas.drawLine(Math.min(f, f3), Math.min(f2, f4), Math.min(f, f3), Math.max(f2, f4), this.g);
        }

        private void f(Canvas canvas, float f, float f2) {
            float[] fArr = this.f901a;
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

        private void g(Canvas canvas) {
            float[] fArr = this.f901a;
            canvas.drawLine(fArr[0], fArr[1], fArr[fArr.length - 2], fArr[fArr.length - 1], this.g);
        }

        private void h(Canvas canvas, float f, float f2) {
            float[] fArr = this.f901a;
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

        private void i(Canvas canvas, float f, float f2, int i, int i2) {
            String str = "" + (((int) (((double) (((f - (i / 2)) * 100.0f) / (MotionLayout.this.getWidth() - i))) + 0.5d)) / 100.0f);
            l(str, this.h);
            canvas.drawText(str, ((f / 2.0f) - (this.r.width() / 2)) + 0.0f, f2 - 20.0f, this.h);
            canvas.drawLine(f, f2, Math.min(0.0f, 1.0f), f2, this.g);
            String str2 = "" + (((int) (((double) (((f2 - (i2 / 2)) * 100.0f) / (MotionLayout.this.getHeight() - i2))) + 0.5d)) / 100.0f);
            l(str2, this.h);
            canvas.drawText(str2, f + 5.0f, 0.0f - ((f2 / 2.0f) - (this.r.height() / 2)), this.h);
            canvas.drawLine(f, f2, f, Math.max(0.0f, 1.0f), this.g);
        }

        private void j(Canvas canvas, n nVar) {
            this.f904d.reset();
            for (int i = 0; i <= 50; i++) {
                nVar.e(i / 50, this.j, 0);
                Path path = this.f904d;
                float[] fArr = this.j;
                path.moveTo(fArr[0], fArr[1]);
                Path path2 = this.f904d;
                float[] fArr2 = this.j;
                path2.lineTo(fArr2[2], fArr2[3]);
                Path path3 = this.f904d;
                float[] fArr3 = this.j;
                path3.lineTo(fArr3[4], fArr3[5]);
                Path path4 = this.f904d;
                float[] fArr4 = this.j;
                path4.lineTo(fArr4[6], fArr4[7]);
                this.f904d.close();
            }
            this.f905e.setColor(1140850688);
            canvas.translate(2.0f, 2.0f);
            canvas.drawPath(this.f904d, this.f905e);
            canvas.translate(-2.0f, -2.0f);
            this.f905e.setColor(-65536);
            canvas.drawPath(this.f904d, this.f905e);
        }

        private void k(Canvas canvas, int i, int i2, n nVar) {
            int width;
            int height;
            float f;
            float f2;
            View view = nVar.f942b;
            if (view != null) {
                width = view.getWidth();
                height = nVar.f942b.getHeight();
            } else {
                width = 0;
                height = 0;
            }
            for (int i3 = 1; i3 < i2 - 1; i3++) {
                if (i != 4 || this.f902b[i3 - 1] != 0) {
                    float[] fArr = this.f903c;
                    int i4 = i3 * 2;
                    float f3 = fArr[i4];
                    float f4 = fArr[i4 + 1];
                    this.f904d.reset();
                    this.f904d.moveTo(f3, f4 + 10.0f);
                    this.f904d.lineTo(f3 + 10.0f, f4);
                    this.f904d.lineTo(f3, f4 - 10.0f);
                    this.f904d.lineTo(f3 - 10.0f, f4);
                    this.f904d.close();
                    int i5 = i3 - 1;
                    nVar.q(i5);
                    if (i == 4) {
                        int[] iArr = this.f902b;
                        if (iArr[i5] == 1) {
                            h(canvas, f3 - 0.0f, f4 - 0.0f);
                        } else if (iArr[i5] == 0) {
                            f(canvas, f3 - 0.0f, f4 - 0.0f);
                        } else {
                            if (iArr[i5] == 2) {
                                f = f4;
                                f2 = f3;
                                i(canvas, f3 - 0.0f, f4 - 0.0f, width, height);
                            }
                            canvas.drawPath(this.f904d, this.i);
                        }
                        f = f4;
                        f2 = f3;
                        canvas.drawPath(this.f904d, this.i);
                    } else {
                        f = f4;
                        f2 = f3;
                    }
                    if (i == 2) {
                        h(canvas, f2 - 0.0f, f - 0.0f);
                    }
                    if (i == 3) {
                        f(canvas, f2 - 0.0f, f - 0.0f);
                    }
                    if (i == 6) {
                        i(canvas, f2 - 0.0f, f - 0.0f, width, height);
                    }
                    canvas.drawPath(this.f904d, this.i);
                }
            }
            float[] fArr2 = this.f901a;
            if (fArr2.length > 1) {
                canvas.drawCircle(fArr2[0], fArr2[1], 8.0f, this.f);
                float[] fArr3 = this.f901a;
                canvas.drawCircle(fArr3[fArr3.length - 2], fArr3[fArr3.length - 1], 8.0f, this.f);
            }
        }

        public void a(Canvas canvas, HashMap<View, n> map, int i, int i2) {
            if (map == null || map.size() == 0) {
                return;
            }
            canvas.save();
            if (!MotionLayout.this.isInEditMode() && (i2 & 1) == 2) {
                String str = MotionLayout.this.getContext().getResources().getResourceName(MotionLayout.this.G) + ":" + MotionLayout.this.getProgress();
                canvas.drawText(str, 10.0f, MotionLayout.this.getHeight() - 30, this.h);
                canvas.drawText(str, 11.0f, MotionLayout.this.getHeight() - 29, this.f905e);
            }
            for (n nVar : map.values()) {
                int iM = nVar.m();
                if (i2 > 0 && iM == 0) {
                    iM = 1;
                }
                if (iM != 0) {
                    this.q = nVar.c(this.f903c, this.f902b);
                    if (iM >= 1) {
                        int i3 = i / 16;
                        float[] fArr = this.f901a;
                        if (fArr == null || fArr.length != i3 * 2) {
                            this.f901a = new float[i3 * 2];
                            this.f904d = new Path();
                        }
                        int i4 = this.t;
                        canvas.translate(i4, i4);
                        this.f905e.setColor(1996488704);
                        this.i.setColor(1996488704);
                        this.f.setColor(1996488704);
                        this.g.setColor(1996488704);
                        nVar.d(this.f901a, i3);
                        b(canvas, iM, this.q, nVar);
                        this.f905e.setColor(-21965);
                        this.f.setColor(-2067046);
                        this.i.setColor(-2067046);
                        this.g.setColor(-13391360);
                        int i5 = this.t;
                        canvas.translate(-i5, -i5);
                        b(canvas, iM, this.q, nVar);
                        if (iM == 5) {
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

    class f {

        /* JADX INFO: renamed from: a */
        androidx.constraintlayout.core.widgets.d f906a = new androidx.constraintlayout.core.widgets.d();

        /* JADX INFO: renamed from: b */
        androidx.constraintlayout.core.widgets.d f907b = new androidx.constraintlayout.core.widgets.d();

        /* JADX INFO: renamed from: c */
        androidx.constraintlayout.widget.b f908c = null;

        /* JADX INFO: renamed from: d */
        androidx.constraintlayout.widget.b f909d = null;

        /* JADX INFO: renamed from: e */
        int f910e;
        int f;

        f() {
        }

        private void b(int i, int i2) {
            int optimizationLevel = MotionLayout.this.getOptimizationLevel();
            MotionLayout motionLayout = MotionLayout.this;
            if (motionLayout.F == motionLayout.getStartState()) {
                MotionLayout motionLayout2 = MotionLayout.this;
                androidx.constraintlayout.core.widgets.d dVar = this.f907b;
                androidx.constraintlayout.widget.b bVar = this.f909d;
                motionLayout2.u(dVar, optimizationLevel, (bVar == null || bVar.g == 0) ? i : i2, (bVar == null || bVar.g == 0) ? i2 : i);
                androidx.constraintlayout.widget.b bVar2 = this.f908c;
                if (bVar2 != null) {
                    MotionLayout motionLayout3 = MotionLayout.this;
                    androidx.constraintlayout.core.widgets.d dVar2 = this.f906a;
                    int i3 = bVar2.g;
                    int i4 = i3 == 0 ? i : i2;
                    if (i3 == 0) {
                        i = i2;
                    }
                    motionLayout3.u(dVar2, optimizationLevel, i4, i);
                    return;
                }
                return;
            }
            androidx.constraintlayout.widget.b bVar3 = this.f908c;
            if (bVar3 != null) {
                MotionLayout motionLayout4 = MotionLayout.this;
                androidx.constraintlayout.core.widgets.d dVar3 = this.f906a;
                int i5 = bVar3.g;
                motionLayout4.u(dVar3, optimizationLevel, i5 == 0 ? i : i2, i5 == 0 ? i2 : i);
            }
            MotionLayout motionLayout5 = MotionLayout.this;
            androidx.constraintlayout.core.widgets.d dVar4 = this.f907b;
            androidx.constraintlayout.widget.b bVar4 = this.f909d;
            int i6 = (bVar4 == null || bVar4.g == 0) ? i : i2;
            if (bVar4 == null || bVar4.g == 0) {
                i = i2;
            }
            motionLayout5.u(dVar4, optimizationLevel, i6, i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        private void j(androidx.constraintlayout.core.widgets.d dVar, androidx.constraintlayout.widget.b bVar) {
            SparseArray<ConstraintWidget> sparseArray = new SparseArray<>();
            Constraints.a aVar = new Constraints.a(-2, -2);
            sparseArray.clear();
            sparseArray.put(0, dVar);
            sparseArray.put(MotionLayout.this.getId(), dVar);
            if (bVar != null && bVar.g != 0) {
                MotionLayout motionLayout = MotionLayout.this;
                motionLayout.u(this.f907b, motionLayout.getOptimizationLevel(), View.MeasureSpec.makeMeasureSpec(MotionLayout.this.getHeight(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(MotionLayout.this.getWidth(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
            }
            for (ConstraintWidget constraintWidget : dVar.u1()) {
                sparseArray.put(((View) constraintWidget.u()).getId(), constraintWidget);
            }
            for (ConstraintWidget constraintWidget2 : dVar.u1()) {
                View view = (View) constraintWidget2.u();
                bVar.l(view.getId(), aVar);
                constraintWidget2.n1(bVar.A(view.getId()));
                constraintWidget2.O0(bVar.v(view.getId()));
                if (view instanceof ConstraintHelper) {
                    bVar.j((ConstraintHelper) view, constraintWidget2, aVar, sparseArray);
                    if (view instanceof Barrier) {
                        ((Barrier) view).w();
                    }
                }
                if (Build.VERSION.SDK_INT >= 17) {
                    aVar.resolveLayoutDirection(MotionLayout.this.getLayoutDirection());
                } else {
                    aVar.resolveLayoutDirection(0);
                }
                MotionLayout.this.c(false, view, constraintWidget2, aVar, sparseArray);
                if (bVar.z(view.getId()) == 1) {
                    constraintWidget2.m1(view.getVisibility());
                } else {
                    constraintWidget2.m1(bVar.y(view.getId()));
                }
            }
            for (ConstraintWidget constraintWidget3 : dVar.u1()) {
                if (constraintWidget3 instanceof androidx.constraintlayout.core.widgets.k) {
                    ConstraintHelper constraintHelper = (ConstraintHelper) constraintWidget3.u();
                    androidx.constraintlayout.core.widgets.g gVar = (androidx.constraintlayout.core.widgets.g) constraintWidget3;
                    constraintHelper.u(dVar, gVar, sparseArray);
                    ((androidx.constraintlayout.core.widgets.k) gVar).x1();
                }
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:72:0x00e9  */
        /* JADX WARN: Removed duplicated region for block: B:90:0x013d A[SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void a() {
            /*
                Method dump skipped, instruction units count: 360
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionLayout.f.a():void");
        }

        void c(androidx.constraintlayout.core.widgets.d dVar, androidx.constraintlayout.core.widgets.d dVar2) {
            ArrayList<ConstraintWidget> arrayListU1 = dVar.u1();
            HashMap<ConstraintWidget, ConstraintWidget> map = new HashMap<>();
            map.put(dVar, dVar2);
            dVar2.u1().clear();
            dVar2.n(dVar, map);
            for (ConstraintWidget constraintWidget : arrayListU1) {
                ConstraintWidget aVar = constraintWidget instanceof androidx.constraintlayout.core.widgets.a ? new androidx.constraintlayout.core.widgets.a() : constraintWidget instanceof androidx.constraintlayout.core.widgets.f ? new androidx.constraintlayout.core.widgets.f() : constraintWidget instanceof androidx.constraintlayout.core.widgets.e ? new androidx.constraintlayout.core.widgets.e() : constraintWidget instanceof androidx.constraintlayout.core.widgets.j ? new androidx.constraintlayout.core.widgets.j() : constraintWidget instanceof androidx.constraintlayout.core.widgets.g ? new androidx.constraintlayout.core.widgets.h() : new ConstraintWidget();
                dVar2.c(aVar);
                map.put(constraintWidget, aVar);
            }
            for (ConstraintWidget constraintWidget2 : arrayListU1) {
                map.get(constraintWidget2).n(constraintWidget2, map);
            }
        }

        ConstraintWidget d(androidx.constraintlayout.core.widgets.d dVar, View view) {
            if (dVar.u() == view) {
                return dVar;
            }
            ArrayList<ConstraintWidget> arrayListU1 = dVar.u1();
            int size = arrayListU1.size();
            for (int i = 0; i < size; i++) {
                ConstraintWidget constraintWidget = arrayListU1.get(i);
                if (constraintWidget.u() == view) {
                    return constraintWidget;
                }
            }
            return null;
        }

        void e(androidx.constraintlayout.core.widgets.d dVar, androidx.constraintlayout.widget.b bVar, androidx.constraintlayout.widget.b bVar2) {
            this.f908c = bVar;
            this.f909d = bVar2;
            this.f906a = new androidx.constraintlayout.core.widgets.d();
            this.f907b = new androidx.constraintlayout.core.widgets.d();
            this.f906a.Y1(((ConstraintLayout) MotionLayout.this).f1035e.L1());
            this.f907b.Y1(((ConstraintLayout) MotionLayout.this).f1035e.L1());
            this.f906a.x1();
            this.f907b.x1();
            c(((ConstraintLayout) MotionLayout.this).f1035e, this.f906a);
            c(((ConstraintLayout) MotionLayout.this).f1035e, this.f907b);
            if (MotionLayout.this.O > 0.5d) {
                if (bVar != null) {
                    j(this.f906a, bVar);
                }
                j(this.f907b, bVar2);
            } else {
                j(this.f907b, bVar2);
                if (bVar != null) {
                    j(this.f906a, bVar);
                }
            }
            this.f906a.b2(MotionLayout.this.q());
            this.f906a.d2();
            this.f907b.b2(MotionLayout.this.q());
            this.f907b.d2();
            ViewGroup.LayoutParams layoutParams = MotionLayout.this.getLayoutParams();
            if (layoutParams != null) {
                if (layoutParams.width == -2) {
                    androidx.constraintlayout.core.widgets.d dVar2 = this.f906a;
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    dVar2.S0(dimensionBehaviour);
                    this.f907b.S0(dimensionBehaviour);
                }
                if (layoutParams.height == -2) {
                    androidx.constraintlayout.core.widgets.d dVar3 = this.f906a;
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    dVar3.j1(dimensionBehaviour2);
                    this.f907b.j1(dimensionBehaviour2);
                }
            }
        }

        public boolean f(int i, int i2) {
            return (i == this.f910e && i2 == this.f) ? false : true;
        }

        public void g(int i, int i2) {
            int mode = View.MeasureSpec.getMode(i);
            int mode2 = View.MeasureSpec.getMode(i2);
            MotionLayout motionLayout = MotionLayout.this;
            motionLayout.D0 = mode;
            motionLayout.E0 = mode2;
            motionLayout.getOptimizationLevel();
            b(i, i2);
            if (((MotionLayout.this.getParent() instanceof MotionLayout) && mode == 1073741824 && mode2 == 1073741824) ? false : true) {
                b(i, i2);
                MotionLayout.this.z0 = this.f906a.Y();
                MotionLayout.this.A0 = this.f906a.z();
                MotionLayout.this.B0 = this.f907b.Y();
                MotionLayout.this.C0 = this.f907b.z();
                MotionLayout motionLayout2 = MotionLayout.this;
                motionLayout2.y0 = (motionLayout2.z0 == motionLayout2.B0 && motionLayout2.A0 == motionLayout2.C0) ? false : true;
            }
            MotionLayout motionLayout3 = MotionLayout.this;
            int i3 = motionLayout3.z0;
            int i4 = motionLayout3.A0;
            int i5 = motionLayout3.D0;
            if (i5 == Integer.MIN_VALUE || i5 == 0) {
                i3 = (int) (i3 + (motionLayout3.F0 * (motionLayout3.B0 - i3)));
            }
            int i6 = i3;
            int i7 = motionLayout3.E0;
            if (i7 == Integer.MIN_VALUE || i7 == 0) {
                i4 = (int) (i4 + (motionLayout3.F0 * (motionLayout3.C0 - i4)));
            }
            MotionLayout.this.t(i, i2, i6, i4, this.f906a.T1() || this.f907b.T1(), this.f906a.R1() || this.f907b.R1());
        }

        public void h() {
            g(MotionLayout.this.H, MotionLayout.this.I);
            MotionLayout.this.z0();
        }

        public void i(int i, int i2) {
            this.f910e = i;
            this.f = i2;
        }
    }

    protected interface g {
        void a(MotionEvent motionEvent);

        float b();

        float c();

        void d();

        void e(int i);
    }

    private static class h implements g {

        /* JADX INFO: renamed from: a */
        private static h f911a = new h();

        /* JADX INFO: renamed from: b */
        VelocityTracker f912b;

        private h() {
        }

        public static h f() {
            f911a.f912b = VelocityTracker.obtain();
            return f911a;
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.g
        public void a(MotionEvent motionEvent) {
            VelocityTracker velocityTracker = this.f912b;
            if (velocityTracker != null) {
                velocityTracker.addMovement(motionEvent);
            }
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.g
        public float b() {
            VelocityTracker velocityTracker = this.f912b;
            if (velocityTracker != null) {
                return velocityTracker.getYVelocity();
            }
            return 0.0f;
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.g
        public float c() {
            VelocityTracker velocityTracker = this.f912b;
            if (velocityTracker != null) {
                return velocityTracker.getXVelocity();
            }
            return 0.0f;
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.g
        public void d() {
            VelocityTracker velocityTracker = this.f912b;
            if (velocityTracker != null) {
                velocityTracker.recycle();
                this.f912b = null;
            }
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.g
        public void e(int i) {
            VelocityTracker velocityTracker = this.f912b;
            if (velocityTracker != null) {
                velocityTracker.computeCurrentVelocity(i);
            }
        }
    }

    class i {

        /* JADX INFO: renamed from: a */
        float f913a = Float.NaN;

        /* JADX INFO: renamed from: b */
        float f914b = Float.NaN;

        /* JADX INFO: renamed from: c */
        int f915c = -1;

        /* JADX INFO: renamed from: d */
        int f916d = -1;

        /* JADX INFO: renamed from: e */
        final String f917e = "motion.progress";
        final String f = "motion.velocity";
        final String g = "motion.StartState";
        final String h = "motion.EndState";

        i() {
        }

        void a() {
            int i = this.f915c;
            if (i != -1 || this.f916d != -1) {
                if (i == -1) {
                    MotionLayout.this.F0(this.f916d);
                } else {
                    int i2 = this.f916d;
                    if (i2 == -1) {
                        MotionLayout.this.x0(i, -1, -1);
                    } else {
                        MotionLayout.this.y0(i, i2);
                    }
                }
                MotionLayout.this.setState(TransitionState.SETUP);
            }
            if (Float.isNaN(this.f914b)) {
                if (Float.isNaN(this.f913a)) {
                    return;
                }
                MotionLayout.this.setProgress(this.f913a);
            } else {
                MotionLayout.this.w0(this.f913a, this.f914b);
                this.f913a = Float.NaN;
                this.f914b = Float.NaN;
                this.f915c = -1;
                this.f916d = -1;
            }
        }

        public Bundle b() {
            Bundle bundle = new Bundle();
            bundle.putFloat("motion.progress", this.f913a);
            bundle.putFloat("motion.velocity", this.f914b);
            bundle.putInt("motion.StartState", this.f915c);
            bundle.putInt("motion.EndState", this.f916d);
            return bundle;
        }

        public void c() {
            this.f916d = MotionLayout.this.G;
            this.f915c = MotionLayout.this.E;
            this.f914b = MotionLayout.this.getVelocity();
            this.f913a = MotionLayout.this.getProgress();
        }

        public void d(int i) {
            this.f916d = i;
        }

        public void e(float f) {
            this.f913a = f;
        }

        public void f(int i) {
            this.f915c = i;
        }

        public void g(Bundle bundle) {
            this.f913a = bundle.getFloat("motion.progress");
            this.f914b = bundle.getFloat("motion.velocity");
            this.f915c = bundle.getInt("motion.StartState");
            this.f916d = bundle.getInt("motion.EndState");
        }

        public void h(float f) {
            this.f914b = f;
        }
    }

    public interface j {
        void a(MotionLayout motionLayout, int i, int i2);

        void b(MotionLayout motionLayout, int i, boolean z, float f);

        void c(MotionLayout motionLayout, int i, int i2, float f);

        void d(MotionLayout motionLayout, int i);
    }

    public MotionLayout(Context context) {
        super(context);
        this.C = null;
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
        this.a0 = new b.e.a.a.b();
        this.b0 = new d();
        this.d0 = true;
        this.i0 = false;
        this.n0 = false;
        this.o0 = null;
        this.p0 = null;
        this.q0 = null;
        this.r0 = null;
        this.s0 = 0;
        this.t0 = -1L;
        this.u0 = 0.0f;
        this.v0 = 0;
        this.w0 = 0.0f;
        this.x0 = false;
        this.y0 = false;
        this.G0 = new androidx.constraintlayout.core.j.a.d();
        this.H0 = false;
        this.J0 = null;
        this.K0 = null;
        this.L0 = 0;
        this.M0 = false;
        this.N0 = 0;
        this.O0 = new HashMap<>();
        this.S0 = new Rect();
        this.T0 = false;
        this.U0 = TransitionState.UNDEFINED;
        this.V0 = new f();
        this.W0 = false;
        this.X0 = new RectF();
        this.Y0 = null;
        this.Z0 = null;
        this.a1 = new ArrayList<>();
        q0(null);
    }

    public Rect A0(ConstraintWidget constraintWidget) {
        this.S0.top = constraintWidget.a0();
        this.S0.left = constraintWidget.Z();
        Rect rect = this.S0;
        int iY = constraintWidget.Y();
        Rect rect2 = this.S0;
        rect.right = iY + rect2.left;
        int iZ = constraintWidget.z();
        Rect rect3 = this.S0;
        rect2.bottom = iZ + rect3.top;
        return rect3;
    }

    private static boolean M0(float f2, float f3, float f4) {
        if (f2 > 0.0f) {
            float f5 = f2 / f4;
            return f3 + ((f2 * f5) - (((f4 * f5) * f5) / 2.0f)) > 1.0f;
        }
        float f6 = (-f2) / f4;
        return f3 + ((f2 * f6) + (((f4 * f6) * f6) / 2.0f)) < 0.0f;
    }

    private boolean Z(View view, MotionEvent motionEvent, float f2, float f3) {
        Matrix matrix = view.getMatrix();
        if (matrix.isIdentity()) {
            motionEvent.offsetLocation(f2, f3);
            boolean zOnTouchEvent = view.onTouchEvent(motionEvent);
            motionEvent.offsetLocation(-f2, -f3);
            return zOnTouchEvent;
        }
        MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
        motionEventObtain.offsetLocation(f2, f3);
        if (this.Z0 == null) {
            this.Z0 = new Matrix();
        }
        matrix.invert(this.Z0);
        motionEventObtain.transform(this.Z0);
        boolean zOnTouchEvent2 = view.onTouchEvent(motionEventObtain);
        motionEventObtain.recycle();
        return zOnTouchEvent2;
    }

    private void a0() {
        q qVar = this.A;
        if (qVar == null) {
            Log.e("MotionLayout", "CHECK: motion scene not set! set \"app:layoutDescription=\"@xml/file\"");
            return;
        }
        int iF = qVar.F();
        q qVar2 = this.A;
        b0(iF, qVar2.l(qVar2.F()));
        SparseIntArray sparseIntArray = new SparseIntArray();
        SparseIntArray sparseIntArray2 = new SparseIntArray();
        for (q.b bVar : this.A.o()) {
            if (bVar == this.A.f953c) {
                Log.v("MotionLayout", "CHECK: CURRENT");
            }
            c0(bVar);
            int iA = bVar.A();
            int iY = bVar.y();
            String strC = androidx.constraintlayout.motion.widget.b.c(getContext(), iA);
            String strC2 = androidx.constraintlayout.motion.widget.b.c(getContext(), iY);
            if (sparseIntArray.get(iA) == iY) {
                Log.e("MotionLayout", "CHECK: two transitions with the same start and end " + strC + "->" + strC2);
            }
            if (sparseIntArray2.get(iY) == iA) {
                Log.e("MotionLayout", "CHECK: you can't have reverse transitions" + strC + "->" + strC2);
            }
            sparseIntArray.put(iA, iY);
            sparseIntArray2.put(iY, iA);
            if (this.A.l(iA) == null) {
                Log.e("MotionLayout", " no such constraintSetStart " + strC);
            }
            if (this.A.l(iY) == null) {
                Log.e("MotionLayout", " no such constraintSetEnd " + strC);
            }
        }
    }

    private void b0(int i2, androidx.constraintlayout.widget.b bVar) {
        String strC = androidx.constraintlayout.motion.widget.b.c(getContext(), i2);
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            int id = childAt.getId();
            if (id == -1) {
                Log.w("MotionLayout", "CHECK: " + strC + " ALL VIEWS SHOULD HAVE ID's " + childAt.getClass().getName() + " does not!");
            }
            if (bVar.u(id) == null) {
                Log.w("MotionLayout", "CHECK: " + strC + " NO CONSTRAINTS for " + androidx.constraintlayout.motion.widget.b.d(childAt));
            }
        }
        int[] iArrW = bVar.w();
        for (int i4 = 0; i4 < iArrW.length; i4++) {
            int i5 = iArrW[i4];
            String strC2 = androidx.constraintlayout.motion.widget.b.c(getContext(), i5);
            if (findViewById(iArrW[i4]) == null) {
                Log.w("MotionLayout", "CHECK: " + strC + " NO View matches id " + strC2);
            }
            if (bVar.v(i5) == -1) {
                Log.w("MotionLayout", "CHECK: " + strC + "(" + strC2 + ") no LAYOUT_HEIGHT");
            }
            if (bVar.A(i5) == -1) {
                Log.w("MotionLayout", "CHECK: " + strC + "(" + strC2 + ") no LAYOUT_HEIGHT");
            }
        }
    }

    private void c0(q.b bVar) {
        if (bVar.A() == bVar.y()) {
            Log.e("MotionLayout", "CHECK: start and end constraint set should not be the same!");
        }
    }

    private void d0() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            n nVar = this.K.get(childAt);
            if (nVar != null) {
                nVar.E(childAt);
            }
        }
    }

    private void g0() {
        boolean z2;
        float fSignum = Math.signum(this.Q - this.O);
        long nanoTime = getNanoTime();
        Interpolator interpolator = this.B;
        float interpolation = this.O + (!(interpolator instanceof b.e.a.a.b) ? (((nanoTime - this.P) * fSignum) * 1.0E-9f) / this.M : 0.0f);
        if (this.R) {
            interpolation = this.Q;
        }
        if ((fSignum <= 0.0f || interpolation < this.Q) && (fSignum > 0.0f || interpolation > this.Q)) {
            z2 = false;
        } else {
            interpolation = this.Q;
            z2 = true;
        }
        if (interpolator != null && !z2) {
            interpolation = this.Z ? interpolator.getInterpolation((nanoTime - this.L) * 1.0E-9f) : interpolator.getInterpolation(interpolation);
        }
        if ((fSignum > 0.0f && interpolation >= this.Q) || (fSignum <= 0.0f && interpolation <= this.Q)) {
            interpolation = this.Q;
        }
        this.F0 = interpolation;
        int childCount = getChildCount();
        long nanoTime2 = getNanoTime();
        Interpolator interpolator2 = this.C;
        if (interpolator2 != null) {
            interpolation = interpolator2.getInterpolation(interpolation);
        }
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            n nVar = this.K.get(childAt);
            if (nVar != null) {
                nVar.x(childAt, interpolation, nanoTime2, this.G0);
            }
        }
        if (this.y0) {
            requestLayout();
        }
    }

    private void h0() {
        CopyOnWriteArrayList<j> copyOnWriteArrayList;
        if ((this.U == null && ((copyOnWriteArrayList = this.r0) == null || copyOnWriteArrayList.isEmpty())) || this.w0 == this.N) {
            return;
        }
        if (this.v0 != -1) {
            j jVar = this.U;
            if (jVar != null) {
                jVar.a(this, this.E, this.G);
            }
            CopyOnWriteArrayList<j> copyOnWriteArrayList2 = this.r0;
            if (copyOnWriteArrayList2 != null) {
                Iterator<j> it = copyOnWriteArrayList2.iterator();
                while (it.hasNext()) {
                    it.next().a(this, this.E, this.G);
                }
            }
            this.x0 = true;
        }
        this.v0 = -1;
        float f2 = this.N;
        this.w0 = f2;
        j jVar2 = this.U;
        if (jVar2 != null) {
            jVar2.c(this, this.E, this.G, f2);
        }
        CopyOnWriteArrayList<j> copyOnWriteArrayList3 = this.r0;
        if (copyOnWriteArrayList3 != null) {
            Iterator<j> it2 = copyOnWriteArrayList3.iterator();
            while (it2.hasNext()) {
                it2.next().c(this, this.E, this.G, this.N);
            }
        }
        this.x0 = true;
    }

    private boolean p0(float f2, float f3, View view, MotionEvent motionEvent) {
        boolean z2;
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                if (p0((r3.getLeft() + f2) - view.getScrollX(), (r3.getTop() + f3) - view.getScrollY(), viewGroup.getChildAt(childCount), motionEvent)) {
                    z2 = true;
                    break;
                }
            }
            z2 = false;
        } else {
            z2 = false;
        }
        if (!z2) {
            this.X0.set(f2, f3, (view.getRight() + f2) - view.getLeft(), (view.getBottom() + f3) - view.getTop());
            if ((motionEvent.getAction() != 0 || this.X0.contains(motionEvent.getX(), motionEvent.getY())) && Z(view, motionEvent, -f2, -f3)) {
                return true;
            }
        }
        return z2;
    }

    private void q0(AttributeSet attributeSet) {
        q qVar;
        z = isInEditMode();
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, androidx.constraintlayout.widget.e.ca);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            boolean z2 = true;
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                if (index == androidx.constraintlayout.widget.e.fa) {
                    this.A = new q(getContext(), this, typedArrayObtainStyledAttributes.getResourceId(index, -1));
                } else if (index == androidx.constraintlayout.widget.e.ea) {
                    this.F = typedArrayObtainStyledAttributes.getResourceId(index, -1);
                } else if (index == androidx.constraintlayout.widget.e.ha) {
                    this.Q = typedArrayObtainStyledAttributes.getFloat(index, 0.0f);
                    this.S = true;
                } else if (index == androidx.constraintlayout.widget.e.da) {
                    z2 = typedArrayObtainStyledAttributes.getBoolean(index, z2);
                } else if (index == androidx.constraintlayout.widget.e.ia) {
                    if (this.X == 0) {
                        this.X = typedArrayObtainStyledAttributes.getBoolean(index, false) ? 2 : 0;
                    }
                } else if (index == androidx.constraintlayout.widget.e.ga) {
                    this.X = typedArrayObtainStyledAttributes.getInt(index, 0);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
            if (this.A == null) {
                Log.e("MotionLayout", "WARNING NO app:layoutDescription tag");
            }
            if (!z2) {
                this.A = null;
            }
        }
        if (this.X != 0) {
            a0();
        }
        if (this.F != -1 || (qVar = this.A) == null) {
            return;
        }
        this.F = qVar.F();
        this.E = this.A.F();
        this.G = this.A.q();
    }

    private void u0() {
        CopyOnWriteArrayList<j> copyOnWriteArrayList;
        if (this.U == null && ((copyOnWriteArrayList = this.r0) == null || copyOnWriteArrayList.isEmpty())) {
            return;
        }
        this.x0 = false;
        for (Integer num : this.a1) {
            j jVar = this.U;
            if (jVar != null) {
                jVar.d(this, num.intValue());
            }
            CopyOnWriteArrayList<j> copyOnWriteArrayList2 = this.r0;
            if (copyOnWriteArrayList2 != null) {
                Iterator<j> it = copyOnWriteArrayList2.iterator();
                while (it.hasNext()) {
                    it.next().d(this, num.intValue());
                }
            }
        }
        this.a1.clear();
    }

    public void z0() {
        int childCount = getChildCount();
        this.V0.a();
        boolean z2 = true;
        this.S = true;
        SparseArray sparseArray = new SparseArray();
        int i2 = 0;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            sparseArray.put(childAt.getId(), this.K.get(childAt));
        }
        int width = getWidth();
        int height = getHeight();
        int iJ = this.A.j();
        if (iJ != -1) {
            for (int i4 = 0; i4 < childCount; i4++) {
                n nVar = this.K.get(getChildAt(i4));
                if (nVar != null) {
                    nVar.D(iJ);
                }
            }
        }
        SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();
        int[] iArr = new int[this.K.size()];
        int i5 = 0;
        for (int i6 = 0; i6 < childCount; i6++) {
            n nVar2 = this.K.get(getChildAt(i6));
            if (nVar2.h() != -1) {
                sparseBooleanArray.put(nVar2.h(), true);
                iArr[i5] = nVar2.h();
                i5++;
            }
        }
        if (this.q0 != null) {
            for (int i7 = 0; i7 < i5; i7++) {
                n nVar3 = this.K.get(findViewById(iArr[i7]));
                if (nVar3 != null) {
                    this.A.t(nVar3);
                }
            }
            Iterator<MotionHelper> it = this.q0.iterator();
            while (it.hasNext()) {
                it.next().D(this, this.K);
            }
            for (int i8 = 0; i8 < i5; i8++) {
                n nVar4 = this.K.get(findViewById(iArr[i8]));
                if (nVar4 != null) {
                    nVar4.I(width, height, this.M, getNanoTime());
                }
            }
        } else {
            for (int i9 = 0; i9 < i5; i9++) {
                n nVar5 = this.K.get(findViewById(iArr[i9]));
                if (nVar5 != null) {
                    this.A.t(nVar5);
                    nVar5.I(width, height, this.M, getNanoTime());
                }
            }
        }
        for (int i10 = 0; i10 < childCount; i10++) {
            View childAt2 = getChildAt(i10);
            n nVar6 = this.K.get(childAt2);
            if (!sparseBooleanArray.get(childAt2.getId()) && nVar6 != null) {
                this.A.t(nVar6);
                nVar6.I(width, height, this.M, getNanoTime());
            }
        }
        float fE = this.A.E();
        if (fE != 0.0f) {
            boolean z3 = ((double) fE) < 0.0d;
            float fAbs = Math.abs(fE);
            float fMax = -3.4028235E38f;
            float fMin = Float.MAX_VALUE;
            int i11 = 0;
            float fMax2 = -3.4028235E38f;
            float fMin2 = Float.MAX_VALUE;
            while (true) {
                if (i11 >= childCount) {
                    z2 = false;
                    break;
                }
                n nVar7 = this.K.get(getChildAt(i11));
                if (!Float.isNaN(nVar7.m)) {
                    break;
                }
                float fN = nVar7.n();
                float fO = nVar7.o();
                float f2 = z3 ? fO - fN : fO + fN;
                fMin2 = Math.min(fMin2, f2);
                fMax2 = Math.max(fMax2, f2);
                i11++;
            }
            if (!z2) {
                while (i2 < childCount) {
                    n nVar8 = this.K.get(getChildAt(i2));
                    float fN2 = nVar8.n();
                    float fO2 = nVar8.o();
                    float f3 = z3 ? fO2 - fN2 : fO2 + fN2;
                    nVar8.o = 1.0f / (1.0f - fAbs);
                    nVar8.n = fAbs - (((f3 - fMin2) * fAbs) / (fMax2 - fMin2));
                    i2++;
                }
                return;
            }
            for (int i12 = 0; i12 < childCount; i12++) {
                n nVar9 = this.K.get(getChildAt(i12));
                if (!Float.isNaN(nVar9.m)) {
                    fMin = Math.min(fMin, nVar9.m);
                    fMax = Math.max(fMax, nVar9.m);
                }
            }
            while (i2 < childCount) {
                n nVar10 = this.K.get(getChildAt(i2));
                if (!Float.isNaN(nVar10.m)) {
                    nVar10.o = 1.0f / (1.0f - fAbs);
                    if (z3) {
                        nVar10.n = fAbs - (((fMax - nVar10.m) / (fMax - fMin)) * fAbs);
                    } else {
                        nVar10.n = fAbs - (((nVar10.m - fMin) * fAbs) / (fMax - fMin));
                    }
                }
                i2++;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:62:0x0093  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void B0(int r10, float r11, float r12) {
        /*
            Method dump skipped, instruction units count: 254
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionLayout.B0(int, float, float):void");
    }

    public void C0() {
        X(1.0f);
        this.J0 = null;
    }

    public void D0(Runnable runnable) {
        X(1.0f);
        this.J0 = runnable;
    }

    public void E0() {
        X(0.0f);
    }

    public void F0(int i2) {
        if (isAttachedToWindow()) {
            H0(i2, -1, -1);
            return;
        }
        if (this.I0 == null) {
            this.I0 = new i();
        }
        this.I0.d(i2);
    }

    public void G0(int i2, int i3) {
        if (isAttachedToWindow()) {
            I0(i2, -1, -1, i3);
            return;
        }
        if (this.I0 == null) {
            this.I0 = new i();
        }
        this.I0.d(i2);
    }

    public void H0(int i2, int i3, int i4) {
        I0(i2, i3, i4, -1);
    }

    public void I0(int i2, int i3, int i4, int i5) {
        androidx.constraintlayout.widget.g gVar;
        int iA;
        q qVar = this.A;
        if (qVar != null && (gVar = qVar.f952b) != null && (iA = gVar.a(this.F, i2, i3, i4)) != -1) {
            i2 = iA;
        }
        int i6 = this.F;
        if (i6 == i2) {
            return;
        }
        if (this.E == i2) {
            X(0.0f);
            if (i5 > 0) {
                this.M = i5 / 1000.0f;
                return;
            }
            return;
        }
        if (this.G == i2) {
            X(1.0f);
            if (i5 > 0) {
                this.M = i5 / 1000.0f;
                return;
            }
            return;
        }
        this.G = i2;
        if (i6 != -1) {
            y0(i6, i2);
            X(1.0f);
            this.O = 0.0f;
            C0();
            if (i5 > 0) {
                this.M = i5 / 1000.0f;
                return;
            }
            return;
        }
        this.Z = false;
        this.Q = 1.0f;
        this.N = 0.0f;
        this.O = 0.0f;
        this.P = getNanoTime();
        this.L = getNanoTime();
        this.R = false;
        this.B = null;
        if (i5 == -1) {
            this.M = this.A.p() / 1000.0f;
        }
        this.E = -1;
        this.A.X(-1, this.G);
        SparseArray sparseArray = new SparseArray();
        if (i5 == 0) {
            this.M = this.A.p() / 1000.0f;
        } else if (i5 > 0) {
            this.M = i5 / 1000.0f;
        }
        int childCount = getChildCount();
        this.K.clear();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            this.K.put(childAt, new n(childAt));
            sparseArray.put(childAt.getId(), this.K.get(childAt));
        }
        this.S = true;
        this.V0.e(this.f1035e, null, this.A.l(i2));
        v0();
        this.V0.a();
        d0();
        int width = getWidth();
        int height = getHeight();
        if (this.q0 != null) {
            for (int i8 = 0; i8 < childCount; i8++) {
                n nVar = this.K.get(getChildAt(i8));
                if (nVar != null) {
                    this.A.t(nVar);
                }
            }
            Iterator<MotionHelper> it = this.q0.iterator();
            while (it.hasNext()) {
                it.next().D(this, this.K);
            }
            for (int i9 = 0; i9 < childCount; i9++) {
                n nVar2 = this.K.get(getChildAt(i9));
                if (nVar2 != null) {
                    nVar2.I(width, height, this.M, getNanoTime());
                }
            }
        } else {
            for (int i10 = 0; i10 < childCount; i10++) {
                n nVar3 = this.K.get(getChildAt(i10));
                if (nVar3 != null) {
                    this.A.t(nVar3);
                    nVar3.I(width, height, this.M, getNanoTime());
                }
            }
        }
        float fE = this.A.E();
        if (fE != 0.0f) {
            float fMin = Float.MAX_VALUE;
            float fMax = -3.4028235E38f;
            for (int i11 = 0; i11 < childCount; i11++) {
                n nVar4 = this.K.get(getChildAt(i11));
                float fO = nVar4.o() + nVar4.n();
                fMin = Math.min(fMin, fO);
                fMax = Math.max(fMax, fO);
            }
            for (int i12 = 0; i12 < childCount; i12++) {
                n nVar5 = this.K.get(getChildAt(i12));
                float fN = nVar5.n();
                float fO2 = nVar5.o();
                nVar5.o = 1.0f / (1.0f - fE);
                nVar5.n = fE - ((((fN + fO2) - fMin) * fE) / (fMax - fMin));
            }
        }
        this.N = 0.0f;
        this.O = 0.0f;
        this.S = true;
        invalidate();
    }

    public void J0() {
        this.V0.e(this.f1035e, this.A.l(this.E), this.A.l(this.G));
        v0();
    }

    public void K0(int i2, androidx.constraintlayout.widget.b bVar) {
        q qVar = this.A;
        if (qVar != null) {
            qVar.U(i2, bVar);
        }
        J0();
        if (this.F == i2) {
            bVar.i(this);
        }
    }

    public void L0(int i2, View... viewArr) {
        q qVar = this.A;
        if (qVar != null) {
            qVar.c0(i2, viewArr);
        } else {
            Log.e("MotionLayout", " no motionScene");
        }
    }

    void X(float f2) {
        if (this.A == null) {
            return;
        }
        float f3 = this.O;
        float f4 = this.N;
        if (f3 != f4 && this.R) {
            this.O = f4;
        }
        float f5 = this.O;
        if (f5 == f2) {
            return;
        }
        this.Z = false;
        this.Q = f2;
        this.M = r0.p() / 1000.0f;
        setProgress(this.Q);
        this.B = null;
        this.C = this.A.s();
        this.R = false;
        this.L = getNanoTime();
        this.S = true;
        this.N = f5;
        this.O = f5;
        invalidate();
    }

    public boolean Y(int i2, n nVar) {
        q qVar = this.A;
        if (qVar != null) {
            return qVar.g(i2, nVar);
        }
        return false;
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        t tVar;
        ArrayList<MotionHelper> arrayList = this.q0;
        if (arrayList != null) {
            Iterator<MotionHelper> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().C(canvas);
            }
        }
        f0(false);
        q qVar = this.A;
        if (qVar != null && (tVar = qVar.s) != null) {
            tVar.c();
        }
        super.dispatchDraw(canvas);
        if (this.A == null) {
            return;
        }
        if ((this.X & 1) == 1 && !isInEditMode()) {
            this.s0++;
            long nanoTime = getNanoTime();
            long j2 = this.t0;
            if (j2 != -1) {
                if (nanoTime - j2 > 200000000) {
                    this.u0 = ((int) ((this.s0 / (r5 * 1.0E-9f)) * 100.0f)) / 100.0f;
                    this.s0 = 0;
                    this.t0 = nanoTime;
                }
            } else {
                this.t0 = nanoTime;
            }
            Paint paint = new Paint();
            paint.setTextSize(42.0f);
            String str = this.u0 + " fps " + androidx.constraintlayout.motion.widget.b.e(this, this.E) + " -> ";
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(androidx.constraintlayout.motion.widget.b.e(this, this.G));
            sb.append(" (progress: ");
            sb.append(((int) (getProgress() * 1000.0f)) / 10.0f);
            sb.append(" ) state=");
            int i2 = this.F;
            sb.append(i2 == -1 ? "undefined" : androidx.constraintlayout.motion.widget.b.e(this, i2));
            String string = sb.toString();
            paint.setColor(-16777216);
            canvas.drawText(string, 11.0f, getHeight() - 29, paint);
            paint.setColor(-7864184);
            canvas.drawText(string, 10.0f, getHeight() - 30, paint);
        }
        if (this.X > 1) {
            if (this.Y == null) {
                this.Y = new e();
            }
            this.Y.a(canvas, this.K, this.A.p(), this.X);
        }
        ArrayList<MotionHelper> arrayList2 = this.q0;
        if (arrayList2 != null) {
            Iterator<MotionHelper> it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                it2.next().B(canvas);
            }
        }
    }

    void e0(boolean z2) {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            n nVar = this.K.get(getChildAt(i2));
            if (nVar != null) {
                nVar.f(z2);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:243:0x00e2 A[PHI: r3
  0x00e2: PHI (r3v50 float) = (r3v49 float), (r3v51 float), (r3v51 float) binds: [B:228:0x00ab, B:239:0x00d6, B:241:0x00da] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:253:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:274:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:277:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:292:0x01ae  */
    /* JADX WARN: Removed duplicated region for block: B:298:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:301:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:308:0x01eb  */
    /* JADX WARN: Removed duplicated region for block: B:313:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:324:0x0221  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void f0(boolean r24) {
        /*
            Method dump skipped, instruction units count: 629
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionLayout.f0(boolean):void");
    }

    public int[] getConstraintSetIds() {
        q qVar = this.A;
        if (qVar == null) {
            return null;
        }
        return qVar.n();
    }

    public int getCurrentState() {
        return this.F;
    }

    public ArrayList<q.b> getDefinedTransitions() {
        q qVar = this.A;
        if (qVar == null) {
            return null;
        }
        return qVar.o();
    }

    public androidx.constraintlayout.motion.widget.c getDesignTool() {
        if (this.c0 == null) {
            this.c0 = new androidx.constraintlayout.motion.widget.c(this);
        }
        return this.c0;
    }

    public int getEndState() {
        return this.G;
    }

    protected long getNanoTime() {
        return System.nanoTime();
    }

    public float getProgress() {
        return this.O;
    }

    public q getScene() {
        return this.A;
    }

    public int getStartState() {
        return this.E;
    }

    public float getTargetPosition() {
        return this.Q;
    }

    public Bundle getTransitionState() {
        if (this.I0 == null) {
            this.I0 = new i();
        }
        this.I0.c();
        return this.I0.b();
    }

    public long getTransitionTimeMs() {
        if (this.A != null) {
            this.M = r0.p() / 1000.0f;
        }
        return (long) (this.M * 1000.0f);
    }

    public float getVelocity() {
        return this.D;
    }

    @Override // b.g.k.l
    public void h(View view, View view2, int i2, int i3) {
        this.l0 = getNanoTime();
        this.m0 = 0.0f;
        this.j0 = 0.0f;
        this.k0 = 0.0f;
    }

    @Override // b.g.k.l
    public void i(View view, int i2) {
        q qVar = this.A;
        if (qVar != null) {
            float f2 = this.m0;
            if (f2 == 0.0f) {
                return;
            }
            qVar.Q(this.j0 / f2, this.k0 / f2);
        }
    }

    protected void i0() {
        int iIntValue;
        CopyOnWriteArrayList<j> copyOnWriteArrayList;
        if ((this.U != null || ((copyOnWriteArrayList = this.r0) != null && !copyOnWriteArrayList.isEmpty())) && this.v0 == -1) {
            this.v0 = this.F;
            if (this.a1.isEmpty()) {
                iIntValue = -1;
            } else {
                ArrayList<Integer> arrayList = this.a1;
                iIntValue = arrayList.get(arrayList.size() - 1).intValue();
            }
            int i2 = this.F;
            if (iIntValue != i2 && i2 != -1) {
                this.a1.add(Integer.valueOf(i2));
            }
        }
        u0();
        Runnable runnable = this.J0;
        if (runnable != null) {
            runnable.run();
        }
        int[] iArr = this.K0;
        if (iArr == null || this.L0 <= 0) {
            return;
        }
        F0(iArr[0]);
        int[] iArr2 = this.K0;
        System.arraycopy(iArr2, 1, iArr2, 0, iArr2.length - 1);
        this.L0--;
    }

    @Override // android.view.View
    public boolean isAttachedToWindow() {
        return Build.VERSION.SDK_INT >= 19 ? super.isAttachedToWindow() : getWindowToken() != null;
    }

    @Override // b.g.k.l
    public void j(View view, int i2, int i3, int[] iArr, int i4) {
        q.b bVar;
        r rVarB;
        int iQ;
        q qVar = this.A;
        if (qVar == null || (bVar = qVar.f953c) == null || !bVar.C()) {
            return;
        }
        int i5 = -1;
        if (!bVar.C() || (rVarB = bVar.B()) == null || (iQ = rVarB.q()) == -1 || view.getId() == iQ) {
            if (qVar.w()) {
                r rVarB2 = bVar.B();
                if (rVarB2 != null && (rVarB2.e() & 4) != 0) {
                    i5 = i3;
                }
                float f2 = this.N;
                if ((f2 == 1.0f || f2 == 0.0f) && view.canScrollVertically(i5)) {
                    return;
                }
            }
            if (bVar.B() != null && (bVar.B().e() & 1) != 0) {
                float fX = qVar.x(i2, i3);
                float f3 = this.O;
                if ((f3 <= 0.0f && fX < 0.0f) || (f3 >= 1.0f && fX > 0.0f)) {
                    if (Build.VERSION.SDK_INT >= 21) {
                        view.setNestedScrollingEnabled(false);
                        view.post(new a(view));
                        return;
                    }
                    return;
                }
            }
            float f4 = this.N;
            long nanoTime = getNanoTime();
            float f5 = i2;
            this.j0 = f5;
            float f6 = i3;
            this.k0 = f6;
            this.m0 = (float) ((nanoTime - this.l0) * 1.0E-9d);
            this.l0 = nanoTime;
            qVar.P(f5, f6);
            if (f4 != this.N) {
                iArr[0] = i2;
                iArr[1] = i3;
            }
            f0(false);
            if (iArr[0] == 0 && iArr[1] == 0) {
                return;
            }
            this.i0 = true;
        }
    }

    public void j0(int i2, boolean z2, float f2) {
        j jVar = this.U;
        if (jVar != null) {
            jVar.b(this, i2, z2, f2);
        }
        CopyOnWriteArrayList<j> copyOnWriteArrayList = this.r0;
        if (copyOnWriteArrayList != null) {
            Iterator<j> it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                it.next().b(this, i2, z2, f2);
            }
        }
    }

    void k0(int i2, float f2, float f3, float f4, float[] fArr) {
        String resourceName;
        HashMap<View, n> map = this.K;
        View viewK = k(i2);
        n nVar = map.get(viewK);
        if (nVar != null) {
            nVar.l(f2, f3, f4, fArr);
            float y = viewK.getY();
            int i3 = ((f2 - this.V) > 0.0f ? 1 : ((f2 - this.V) == 0.0f ? 0 : -1));
            this.V = f2;
            this.W = y;
            return;
        }
        if (viewK == null) {
            resourceName = "" + i2;
        } else {
            resourceName = viewK.getContext().getResources().getResourceName(i2);
        }
        Log.w("MotionLayout", "WARNING could not find view id " + resourceName);
    }

    public androidx.constraintlayout.widget.b l0(int i2) {
        q qVar = this.A;
        if (qVar == null) {
            return null;
        }
        return qVar.l(i2);
    }

    @Override // b.g.k.m
    public void m(View view, int i2, int i3, int i4, int i5, int i6, int[] iArr) {
        if (this.i0 || i2 != 0 || i3 != 0) {
            iArr[0] = iArr[0] + i4;
            iArr[1] = iArr[1] + i5;
        }
        this.i0 = false;
    }

    n m0(int i2) {
        return this.K.get(findViewById(i2));
    }

    @Override // b.g.k.l
    public void n(View view, int i2, int i3, int i4, int i5, int i6) {
    }

    public q.b n0(int i2) {
        return this.A.G(i2);
    }

    @Override // b.g.k.l
    public boolean o(View view, View view2, int i2, int i3) {
        q.b bVar;
        q qVar = this.A;
        return (qVar == null || (bVar = qVar.f953c) == null || bVar.B() == null || (this.A.f953c.B().e() & 2) != 0) ? false : true;
    }

    public void o0(View view, float f2, float f3, float[] fArr, int i2) {
        float f4;
        float fA = this.D;
        float f5 = this.O;
        if (this.B != null) {
            float fSignum = Math.signum(this.Q - f5);
            float interpolation = this.B.getInterpolation(this.O + 1.0E-5f);
            float interpolation2 = this.B.getInterpolation(this.O);
            fA = (fSignum * ((interpolation - interpolation2) / 1.0E-5f)) / this.M;
            f4 = interpolation2;
        } else {
            f4 = f5;
        }
        Interpolator interpolator = this.B;
        if (interpolator instanceof o) {
            fA = ((o) interpolator).a();
        }
        n nVar = this.K.get(view);
        if ((i2 & 1) == 0) {
            nVar.r(f4, view.getWidth(), view.getHeight(), f2, f3, fArr);
        } else {
            nVar.l(f4, f2, f3, fArr);
        }
        if (i2 < 2) {
            fArr[0] = fArr[0] * fA;
            fArr[1] = fArr[1] * fA;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        q.b bVar;
        int i2;
        Display display;
        super.onAttachedToWindow();
        if (Build.VERSION.SDK_INT >= 17 && (display = getDisplay()) != null) {
            this.R0 = display.getRotation();
        }
        q qVar = this.A;
        if (qVar != null && (i2 = this.F) != -1) {
            androidx.constraintlayout.widget.b bVarL = qVar.l(i2);
            this.A.T(this);
            ArrayList<MotionHelper> arrayList = this.q0;
            if (arrayList != null) {
                Iterator<MotionHelper> it = arrayList.iterator();
                while (it.hasNext()) {
                    it.next().A(this);
                }
            }
            if (bVarL != null) {
                bVarL.i(this);
            }
            this.E = this.F;
        }
        t0();
        i iVar = this.I0;
        if (iVar != null) {
            if (this.T0) {
                post(new b());
                return;
            } else {
                iVar.a();
                return;
            }
        }
        q qVar2 = this.A;
        if (qVar2 == null || (bVar = qVar2.f953c) == null || bVar.x() != 4) {
            return;
        }
        C0();
        setState(TransitionState.SETUP);
        setState(TransitionState.MOVING);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        r rVarB;
        int iQ;
        RectF rectFP;
        q qVar = this.A;
        if (qVar != null && this.J) {
            t tVar = qVar.s;
            if (tVar != null) {
                tVar.h(motionEvent);
            }
            q.b bVar = this.A.f953c;
            if (bVar != null && bVar.C() && (rVarB = bVar.B()) != null && ((motionEvent.getAction() != 0 || (rectFP = rVarB.p(this, new RectF())) == null || rectFP.contains(motionEvent.getX(), motionEvent.getY())) && (iQ = rVarB.q()) != -1)) {
                View view = this.Y0;
                if (view == null || view.getId() != iQ) {
                    this.Y0 = findViewById(iQ);
                }
                if (this.Y0 != null) {
                    this.X0.set(r0.getLeft(), this.Y0.getTop(), this.Y0.getRight(), this.Y0.getBottom());
                    if (this.X0.contains(motionEvent.getX(), motionEvent.getY()) && !p0(this.Y0.getLeft(), this.Y0.getTop(), this.Y0, motionEvent)) {
                        return onTouchEvent(motionEvent);
                    }
                }
            }
        }
        return false;
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        this.H0 = true;
        try {
            if (this.A == null) {
                super.onLayout(z2, i2, i3, i4, i5);
                return;
            }
            int i6 = i4 - i2;
            int i7 = i5 - i3;
            if (this.g0 != i6 || this.h0 != i7) {
                v0();
                f0(true);
            }
            this.g0 = i6;
            this.h0 = i7;
            this.e0 = i6;
            this.f0 = i7;
        } finally {
            this.H0 = false;
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        if (this.A == null) {
            super.onMeasure(i2, i3);
            return;
        }
        boolean z2 = false;
        boolean z3 = (this.H == i2 && this.I == i3) ? false : true;
        if (this.W0) {
            this.W0 = false;
            t0();
            u0();
            z3 = true;
        }
        if (this.j) {
            z3 = true;
        }
        this.H = i2;
        this.I = i3;
        int iF = this.A.F();
        int iQ = this.A.q();
        if ((z3 || this.V0.f(iF, iQ)) && this.E != -1) {
            super.onMeasure(i2, i3);
            this.V0.e(this.f1035e, this.A.l(iF), this.A.l(iQ));
            this.V0.h();
            this.V0.i(iF, iQ);
        } else {
            if (z3) {
                super.onMeasure(i2, i3);
            }
            z2 = true;
        }
        if (this.y0 || z2) {
            int paddingTop = getPaddingTop() + getPaddingBottom();
            int iY = this.f1035e.Y() + getPaddingLeft() + getPaddingRight();
            int iZ = this.f1035e.z() + paddingTop;
            int i4 = this.D0;
            if (i4 == Integer.MIN_VALUE || i4 == 0) {
                iY = (int) (this.z0 + (this.F0 * (this.B0 - r8)));
                requestLayout();
            }
            int i5 = this.E0;
            if (i5 == Integer.MIN_VALUE || i5 == 0) {
                iZ = (int) (this.A0 + (this.F0 * (this.C0 - r8)));
                requestLayout();
            }
            setMeasuredDimension(iY, iZ);
        }
        g0();
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, b.g.k.n
    public boolean onNestedFling(View view, float f2, float f3, boolean z2) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, b.g.k.n
    public boolean onNestedPreFling(View view, float f2, float f3) {
        return false;
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i2) {
        q qVar = this.A;
        if (qVar != null) {
            qVar.W(q());
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        q qVar = this.A;
        if (qVar == null || !this.J || !qVar.b0()) {
            return super.onTouchEvent(motionEvent);
        }
        q.b bVar = this.A.f953c;
        if (bVar != null && !bVar.C()) {
            return super.onTouchEvent(motionEvent);
        }
        this.A.R(motionEvent, getCurrentState(), this);
        if (this.A.f953c.D(4)) {
            return this.A.f953c.B().r();
        }
        return true;
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public void onViewAdded(View view) {
        super.onViewAdded(view);
        if (view instanceof MotionHelper) {
            MotionHelper motionHelper = (MotionHelper) view;
            if (this.r0 == null) {
                this.r0 = new CopyOnWriteArrayList<>();
            }
            this.r0.add(motionHelper);
            if (motionHelper.z()) {
                if (this.o0 == null) {
                    this.o0 = new ArrayList<>();
                }
                this.o0.add(motionHelper);
            }
            if (motionHelper.y()) {
                if (this.p0 == null) {
                    this.p0 = new ArrayList<>();
                }
                this.p0.add(motionHelper);
            }
            if (motionHelper.x()) {
                if (this.q0 == null) {
                    this.q0 = new ArrayList<>();
                }
                this.q0.add(motionHelper);
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        ArrayList<MotionHelper> arrayList = this.o0;
        if (arrayList != null) {
            arrayList.remove(view);
        }
        ArrayList<MotionHelper> arrayList2 = this.p0;
        if (arrayList2 != null) {
            arrayList2.remove(view);
        }
    }

    public boolean r0() {
        return this.J;
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View, android.view.ViewParent
    public void requestLayout() {
        q qVar;
        q.b bVar;
        if (!this.y0 && this.F == -1 && (qVar = this.A) != null && (bVar = qVar.f953c) != null) {
            int iZ = bVar.z();
            if (iZ == 0) {
                return;
            }
            if (iZ == 2) {
                int childCount = getChildCount();
                for (int i2 = 0; i2 < childCount; i2++) {
                    this.K.get(getChildAt(i2)).z();
                }
                return;
            }
        }
        super.requestLayout();
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout
    protected void s(int i2) {
        this.m = null;
    }

    protected g s0() {
        return h.f();
    }

    public void setDebugMode(int i2) {
        this.X = i2;
        invalidate();
    }

    public void setDelayedApplicationOfInitialState(boolean z2) {
        this.T0 = z2;
    }

    public void setInteractionEnabled(boolean z2) {
        this.J = z2;
    }

    public void setInterpolatedProgress(float f2) {
        if (this.A != null) {
            setState(TransitionState.MOVING);
            Interpolator interpolatorS = this.A.s();
            if (interpolatorS != null) {
                setProgress(interpolatorS.getInterpolation(f2));
                return;
            }
        }
        setProgress(f2);
    }

    public void setOnHide(float f2) {
        ArrayList<MotionHelper> arrayList = this.p0;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.p0.get(i2).setProgress(f2);
            }
        }
    }

    public void setOnShow(float f2) {
        ArrayList<MotionHelper> arrayList = this.o0;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.o0.get(i2).setProgress(f2);
            }
        }
    }

    public void setProgress(float f2) {
        if (f2 < 0.0f || f2 > 1.0f) {
            Log.w("MotionLayout", "Warning! Progress is defined for values between 0.0 and 1.0 inclusive");
        }
        if (!isAttachedToWindow()) {
            if (this.I0 == null) {
                this.I0 = new i();
            }
            this.I0.e(f2);
            return;
        }
        if (f2 <= 0.0f) {
            if (this.O == 1.0f && this.F == this.G) {
                setState(TransitionState.MOVING);
            }
            this.F = this.E;
            if (this.O == 0.0f) {
                setState(TransitionState.FINISHED);
            }
        } else if (f2 >= 1.0f) {
            if (this.O == 0.0f && this.F == this.E) {
                setState(TransitionState.MOVING);
            }
            this.F = this.G;
            if (this.O == 1.0f) {
                setState(TransitionState.FINISHED);
            }
        } else {
            this.F = -1;
            setState(TransitionState.MOVING);
        }
        if (this.A == null) {
            return;
        }
        this.R = true;
        this.Q = f2;
        this.N = f2;
        this.P = -1L;
        this.L = -1L;
        this.B = null;
        this.S = true;
        invalidate();
    }

    public void setScene(q qVar) {
        this.A = qVar;
        qVar.W(q());
        v0();
    }

    void setStartState(int i2) {
        if (isAttachedToWindow()) {
            this.F = i2;
            return;
        }
        if (this.I0 == null) {
            this.I0 = new i();
        }
        this.I0.f(i2);
        this.I0.d(i2);
    }

    void setState(TransitionState transitionState) {
        TransitionState transitionState2 = TransitionState.FINISHED;
        if (transitionState == transitionState2 && this.F == -1) {
            return;
        }
        TransitionState transitionState3 = this.U0;
        this.U0 = transitionState;
        TransitionState transitionState4 = TransitionState.MOVING;
        if (transitionState3 == transitionState4 && transitionState == transitionState4) {
            h0();
        }
        int i2 = c.f896a[transitionState3.ordinal()];
        if (i2 != 1 && i2 != 2) {
            if (i2 == 3 && transitionState == transitionState2) {
                i0();
                return;
            }
            return;
        }
        if (transitionState == transitionState4) {
            h0();
        }
        if (transitionState == transitionState2) {
            i0();
        }
    }

    public void setTransition(int i2) {
        if (this.A != null) {
            q.b bVarN0 = n0(i2);
            this.E = bVarN0.A();
            this.G = bVarN0.y();
            if (!isAttachedToWindow()) {
                if (this.I0 == null) {
                    this.I0 = new i();
                }
                this.I0.f(this.E);
                this.I0.d(this.G);
                return;
            }
            float f2 = Float.NaN;
            int i3 = this.F;
            if (i3 == this.E) {
                f2 = 0.0f;
            } else if (i3 == this.G) {
                f2 = 1.0f;
            }
            this.A.Y(bVarN0);
            this.V0.e(this.f1035e, this.A.l(this.E), this.A.l(this.G));
            v0();
            if (this.O != f2) {
                if (f2 == 0.0f) {
                    e0(true);
                    this.A.l(this.E).i(this);
                } else if (f2 == 1.0f) {
                    e0(false);
                    this.A.l(this.G).i(this);
                }
            }
            this.O = Float.isNaN(f2) ? 0.0f : f2;
            if (!Float.isNaN(f2)) {
                setProgress(f2);
                return;
            }
            Log.v("MotionLayout", androidx.constraintlayout.motion.widget.b.b() + " transitionToStart ");
            E0();
        }
    }

    public void setTransitionDuration(int i2) {
        q qVar = this.A;
        if (qVar == null) {
            Log.e("MotionLayout", "MotionScene not defined");
        } else {
            qVar.V(i2);
        }
    }

    public void setTransitionListener(j jVar) {
        this.U = jVar;
    }

    public void setTransitionState(Bundle bundle) {
        if (this.I0 == null) {
            this.I0 = new i();
        }
        this.I0.g(bundle);
        if (isAttachedToWindow()) {
            this.I0.a();
        }
    }

    void t0() {
        q qVar = this.A;
        if (qVar == null) {
            return;
        }
        if (qVar.h(this, this.F)) {
            requestLayout();
            return;
        }
        int i2 = this.F;
        if (i2 != -1) {
            this.A.f(this, i2);
        }
        if (this.A.b0()) {
            this.A.Z();
        }
    }

    @Override // android.view.View
    public String toString() {
        Context context = getContext();
        return androidx.constraintlayout.motion.widget.b.c(context, this.E) + "->" + androidx.constraintlayout.motion.widget.b.c(context, this.G) + " (pos:" + this.O + " Dpos/Dt:" + this.D;
    }

    public void v0() {
        this.V0.h();
        invalidate();
    }

    public void w0(float f2, float f3) {
        if (!isAttachedToWindow()) {
            if (this.I0 == null) {
                this.I0 = new i();
            }
            this.I0.e(f2);
            this.I0.h(f3);
            return;
        }
        setProgress(f2);
        setState(TransitionState.MOVING);
        this.D = f3;
        if (f3 != 0.0f) {
            X(f3 > 0.0f ? 1.0f : 0.0f);
        } else {
            if (f2 == 0.0f || f2 == 1.0f) {
                return;
            }
            X(f2 > 0.5f ? 1.0f : 0.0f);
        }
    }

    public void x0(int i2, int i3, int i4) {
        setState(TransitionState.SETUP);
        this.F = i2;
        this.E = -1;
        this.G = -1;
        androidx.constraintlayout.widget.a aVar = this.m;
        if (aVar != null) {
            aVar.d(i2, i3, i4);
            return;
        }
        q qVar = this.A;
        if (qVar != null) {
            qVar.l(i2).i(this);
        }
    }

    public void y0(int i2, int i3) {
        if (!isAttachedToWindow()) {
            if (this.I0 == null) {
                this.I0 = new i();
            }
            this.I0.f(i2);
            this.I0.d(i3);
            return;
        }
        q qVar = this.A;
        if (qVar != null) {
            this.E = i2;
            this.G = i3;
            qVar.X(i2, i3);
            this.V0.e(this.f1035e, this.A.l(i2), this.A.l(i3));
            v0();
            this.O = 0.0f;
            E0();
        }
    }

    protected void setTransition(q.b bVar) {
        this.A.Y(bVar);
        setState(TransitionState.SETUP);
        if (this.F == this.A.q()) {
            this.O = 1.0f;
            this.N = 1.0f;
            this.Q = 1.0f;
        } else {
            this.O = 0.0f;
            this.N = 0.0f;
            this.Q = 0.0f;
        }
        this.P = bVar.D(1) ? -1L : getNanoTime();
        int iF = this.A.F();
        int iQ = this.A.q();
        if (iF == this.E && iQ == this.G) {
            return;
        }
        this.E = iF;
        this.G = iQ;
        this.A.X(iF, iQ);
        this.V0.e(this.f1035e, this.A.l(this.E), this.A.l(this.G));
        this.V0.i(this.E, this.G);
        this.V0.h();
        v0();
    }

    public MotionLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.C = null;
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
        this.a0 = new b.e.a.a.b();
        this.b0 = new d();
        this.d0 = true;
        this.i0 = false;
        this.n0 = false;
        this.o0 = null;
        this.p0 = null;
        this.q0 = null;
        this.r0 = null;
        this.s0 = 0;
        this.t0 = -1L;
        this.u0 = 0.0f;
        this.v0 = 0;
        this.w0 = 0.0f;
        this.x0 = false;
        this.y0 = false;
        this.G0 = new androidx.constraintlayout.core.j.a.d();
        this.H0 = false;
        this.J0 = null;
        this.K0 = null;
        this.L0 = 0;
        this.M0 = false;
        this.N0 = 0;
        this.O0 = new HashMap<>();
        this.S0 = new Rect();
        this.T0 = false;
        this.U0 = TransitionState.UNDEFINED;
        this.V0 = new f();
        this.W0 = false;
        this.X0 = new RectF();
        this.Y0 = null;
        this.Z0 = null;
        this.a1 = new ArrayList<>();
        q0(attributeSet);
    }

    public MotionLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.C = null;
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
        this.a0 = new b.e.a.a.b();
        this.b0 = new d();
        this.d0 = true;
        this.i0 = false;
        this.n0 = false;
        this.o0 = null;
        this.p0 = null;
        this.q0 = null;
        this.r0 = null;
        this.s0 = 0;
        this.t0 = -1L;
        this.u0 = 0.0f;
        this.v0 = 0;
        this.w0 = 0.0f;
        this.x0 = false;
        this.y0 = false;
        this.G0 = new androidx.constraintlayout.core.j.a.d();
        this.H0 = false;
        this.J0 = null;
        this.K0 = null;
        this.L0 = 0;
        this.M0 = false;
        this.N0 = 0;
        this.O0 = new HashMap<>();
        this.S0 = new Rect();
        this.T0 = false;
        this.U0 = TransitionState.UNDEFINED;
        this.V0 = new f();
        this.W0 = false;
        this.X0 = new RectF();
        this.Y0 = null;
        this.Z0 = null;
        this.a1 = new ArrayList<>();
        q0(attributeSet);
    }
}
