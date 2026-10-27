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
import androidx.constraintlayout.motion.widget.q;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.e;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import r.e;

/* JADX INFO: loaded from: classes.dex */
public class MotionLayout extends ConstraintLayout implements i0.n {

    /* JADX INFO: renamed from: h1, reason: collision with root package name */
    public static boolean f1260h1;
    private float A0;
    private int B0;
    private float C0;
    q D;
    boolean D0;
    Interpolator E;
    protected boolean E0;
    Interpolator F;
    int F0;
    float G;
    int G0;
    private int H;
    int H0;
    int I;
    int I0;
    private int J;
    int J0;
    private int K;
    int K0;
    private int L;
    float L0;
    private boolean M;
    private p.d M0;
    HashMap<View, m> N;
    private boolean N0;
    private long O;
    private i O0;
    private float P;
    private Runnable P0;
    float Q;
    private int[] Q0;
    float R;
    int R0;
    private long S;
    private boolean S0;
    float T;
    int T0;
    private boolean U;
    HashMap<View, t.e> U0;
    boolean V;
    private int V0;
    boolean W;
    private int W0;
    private int X0;
    Rect Y0;
    private boolean Z0;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    private j f1261a0;

    /* JADX INFO: renamed from: a1, reason: collision with root package name */
    k f1262a1;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    private float f1263b0;

    /* JADX INFO: renamed from: b1, reason: collision with root package name */
    f f1264b1;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    private float f1265c0;

    /* JADX INFO: renamed from: c1, reason: collision with root package name */
    private boolean f1266c1;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    int f1267d0;

    /* JADX INFO: renamed from: d1, reason: collision with root package name */
    private RectF f1268d1;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    e f1269e0;

    /* JADX INFO: renamed from: e1, reason: collision with root package name */
    private View f1270e1;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    private boolean f1271f0;

    /* JADX INFO: renamed from: f1, reason: collision with root package name */
    private Matrix f1272f1;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    private t.b f1273g0;

    /* JADX INFO: renamed from: g1, reason: collision with root package name */
    ArrayList<Integer> f1274g1;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    private d f1275h0;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    private androidx.constraintlayout.motion.widget.b f1276i0;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    boolean f1277j0;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    int f1278k0;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    int f1279l0;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    int f1280m0;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    int f1281n0;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    boolean f1282o0;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    float f1283p0;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    float f1284q0;

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    long f1285r0;

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    float f1286s0;

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    private boolean f1287t0;

    /* JADX INFO: renamed from: u0, reason: collision with root package name */
    private ArrayList<n> f1288u0;

    /* JADX INFO: renamed from: v0, reason: collision with root package name */
    private ArrayList<n> f1289v0;

    /* JADX INFO: renamed from: w0, reason: collision with root package name */
    private ArrayList<n> f1290w0;

    /* JADX INFO: renamed from: x0, reason: collision with root package name */
    private CopyOnWriteArrayList<j> f1291x0;

    /* JADX INFO: renamed from: y0, reason: collision with root package name */
    private int f1292y0;

    /* JADX INFO: renamed from: z0, reason: collision with root package name */
    private long f1293z0;

    class a implements Runnable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ View f1294e;

        a(View view) {
            this.f1294e = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f1294e.setNestedScrollingEnabled(true);
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            MotionLayout.this.O0.a();
        }
    }

    static /* synthetic */ class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f1297a;

        static {
            int[] iArr = new int[k.values().length];
            f1297a = iArr;
            try {
                iArr[k.UNDEFINED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1297a[k.SETUP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1297a[k.MOVING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1297a[k.FINISHED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    class d extends o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        float f1298a = 0.0f;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        float f1299b = 0.0f;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        float f1300c;

        d() {
        }

        @Override // androidx.constraintlayout.motion.widget.o
        public float a() {
            return MotionLayout.this.G;
        }

        public void b(float f7, float f8, float f9) {
            this.f1298a = f7;
            this.f1299b = f8;
            this.f1300c = f9;
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f7) {
            float f8;
            float f9;
            float f10 = this.f1298a;
            if (f10 > 0.0f) {
                float f11 = this.f1300c;
                if (f10 / f11 < f7) {
                    f7 = f10 / f11;
                }
                MotionLayout.this.G = f10 - (f11 * f7);
                f8 = (f10 * f7) - (((f11 * f7) * f7) / 2.0f);
                f9 = this.f1299b;
            } else {
                float f12 = this.f1300c;
                if ((-f10) / f12 < f7) {
                    f7 = (-f10) / f12;
                }
                MotionLayout.this.G = (f12 * f7) + f10;
                f8 = (f10 * f7) + (((f12 * f7) * f7) / 2.0f);
                f9 = this.f1299b;
            }
            return f8 + f9;
        }
    }

    private class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        float[] f1302a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int[] f1303b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        float[] f1304c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        Path f1305d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        Paint f1306e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        Paint f1307f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        Paint f1308g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        Paint f1309h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        Paint f1310i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        private float[] f1311j;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        DashPathEffect f1317p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        int f1318q;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        int f1321t;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        final int f1312k = -21965;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        final int f1313l = -2067046;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        final int f1314m = -13391360;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        final int f1315n = 1996488704;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        final int f1316o = 10;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        Rect f1319r = new Rect();

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        boolean f1320s = false;

        public e() {
            this.f1321t = 1;
            Paint paint = new Paint();
            this.f1306e = paint;
            paint.setAntiAlias(true);
            this.f1306e.setColor(-21965);
            this.f1306e.setStrokeWidth(2.0f);
            this.f1306e.setStyle(Paint.Style.STROKE);
            Paint paint2 = new Paint();
            this.f1307f = paint2;
            paint2.setAntiAlias(true);
            this.f1307f.setColor(-2067046);
            this.f1307f.setStrokeWidth(2.0f);
            this.f1307f.setStyle(Paint.Style.STROKE);
            Paint paint3 = new Paint();
            this.f1308g = paint3;
            paint3.setAntiAlias(true);
            this.f1308g.setColor(-13391360);
            this.f1308g.setStrokeWidth(2.0f);
            this.f1308g.setStyle(Paint.Style.STROKE);
            Paint paint4 = new Paint();
            this.f1309h = paint4;
            paint4.setAntiAlias(true);
            this.f1309h.setColor(-13391360);
            this.f1309h.setTextSize(MotionLayout.this.getContext().getResources().getDisplayMetrics().density * 12.0f);
            this.f1311j = new float[8];
            Paint paint5 = new Paint();
            this.f1310i = paint5;
            paint5.setAntiAlias(true);
            DashPathEffect dashPathEffect = new DashPathEffect(new float[]{4.0f, 8.0f}, 0.0f);
            this.f1317p = dashPathEffect;
            this.f1308g.setPathEffect(dashPathEffect);
            this.f1304c = new float[100];
            this.f1303b = new int[50];
            if (this.f1320s) {
                this.f1306e.setStrokeWidth(8.0f);
                this.f1310i.setStrokeWidth(8.0f);
                this.f1307f.setStrokeWidth(8.0f);
                this.f1321t = 4;
            }
        }

        private void c(Canvas canvas) {
            canvas.drawLines(this.f1302a, this.f1306e);
        }

        private void d(Canvas canvas) {
            boolean z6 = false;
            boolean z7 = false;
            for (int i7 = 0; i7 < this.f1318q; i7++) {
                int[] iArr = this.f1303b;
                if (iArr[i7] == 1) {
                    z6 = true;
                }
                if (iArr[i7] == 0) {
                    z7 = true;
                }
            }
            if (z6) {
                g(canvas);
            }
            if (z7) {
                e(canvas);
            }
        }

        private void e(Canvas canvas) {
            float[] fArr = this.f1302a;
            float f7 = fArr[0];
            float f8 = fArr[1];
            float f9 = fArr[fArr.length - 2];
            float f10 = fArr[fArr.length - 1];
            canvas.drawLine(Math.min(f7, f9), Math.max(f8, f10), Math.max(f7, f9), Math.max(f8, f10), this.f1308g);
            canvas.drawLine(Math.min(f7, f9), Math.min(f8, f10), Math.min(f7, f9), Math.max(f8, f10), this.f1308g);
        }

        private void f(Canvas canvas, float f7, float f8) {
            float[] fArr = this.f1302a;
            float f9 = fArr[0];
            float f10 = fArr[1];
            float f11 = fArr[fArr.length - 2];
            float f12 = fArr[fArr.length - 1];
            float fMin = Math.min(f9, f11);
            float fMax = Math.max(f10, f12);
            float fMin2 = f7 - Math.min(f9, f11);
            float fMax2 = Math.max(f10, f12) - f8;
            String str = "" + (((int) (((double) ((fMin2 * 100.0f) / Math.abs(f11 - f9))) + 0.5d)) / 100.0f);
            l(str, this.f1309h);
            canvas.drawText(str, ((fMin2 / 2.0f) - (this.f1319r.width() / 2)) + fMin, f8 - 20.0f, this.f1309h);
            canvas.drawLine(f7, f8, Math.min(f9, f11), f8, this.f1308g);
            String str2 = "" + (((int) (((double) ((fMax2 * 100.0f) / Math.abs(f12 - f10))) + 0.5d)) / 100.0f);
            l(str2, this.f1309h);
            canvas.drawText(str2, f7 + 5.0f, fMax - ((fMax2 / 2.0f) - (this.f1319r.height() / 2)), this.f1309h);
            canvas.drawLine(f7, f8, f7, Math.max(f10, f12), this.f1308g);
        }

        private void g(Canvas canvas) {
            float[] fArr = this.f1302a;
            canvas.drawLine(fArr[0], fArr[1], fArr[fArr.length - 2], fArr[fArr.length - 1], this.f1308g);
        }

        private void h(Canvas canvas, float f7, float f8) {
            float[] fArr = this.f1302a;
            float f9 = fArr[0];
            float f10 = fArr[1];
            float f11 = fArr[fArr.length - 2];
            float f12 = fArr[fArr.length - 1];
            float fHypot = (float) Math.hypot(f9 - f11, f10 - f12);
            float f13 = f11 - f9;
            float f14 = f12 - f10;
            float f15 = (((f7 - f9) * f13) + ((f8 - f10) * f14)) / (fHypot * fHypot);
            float f16 = f9 + (f13 * f15);
            float f17 = f10 + (f15 * f14);
            Path path = new Path();
            path.moveTo(f7, f8);
            path.lineTo(f16, f17);
            float fHypot2 = (float) Math.hypot(f16 - f7, f17 - f8);
            String str = "" + (((int) ((fHypot2 * 100.0f) / fHypot)) / 100.0f);
            l(str, this.f1309h);
            canvas.drawTextOnPath(str, path, (fHypot2 / 2.0f) - (this.f1319r.width() / 2), -20.0f, this.f1309h);
            canvas.drawLine(f7, f8, f16, f17, this.f1308g);
        }

        private void i(Canvas canvas, float f7, float f8, int i7, int i8) {
            String str = "" + (((int) (((double) (((f7 - (i7 / 2)) * 100.0f) / (MotionLayout.this.getWidth() - i7))) + 0.5d)) / 100.0f);
            l(str, this.f1309h);
            canvas.drawText(str, ((f7 / 2.0f) - (this.f1319r.width() / 2)) + 0.0f, f8 - 20.0f, this.f1309h);
            canvas.drawLine(f7, f8, Math.min(0.0f, 1.0f), f8, this.f1308g);
            String str2 = "" + (((int) (((double) (((f8 - (i8 / 2)) * 100.0f) / (MotionLayout.this.getHeight() - i8))) + 0.5d)) / 100.0f);
            l(str2, this.f1309h);
            canvas.drawText(str2, f7 + 5.0f, 0.0f - ((f8 / 2.0f) - (this.f1319r.height() / 2)), this.f1309h);
            canvas.drawLine(f7, f8, f7, Math.max(0.0f, 1.0f), this.f1308g);
        }

        private void j(Canvas canvas, m mVar) {
            this.f1305d.reset();
            for (int i7 = 0; i7 <= 50; i7++) {
                mVar.e(i7 / 50, this.f1311j, 0);
                Path path = this.f1305d;
                float[] fArr = this.f1311j;
                path.moveTo(fArr[0], fArr[1]);
                Path path2 = this.f1305d;
                float[] fArr2 = this.f1311j;
                path2.lineTo(fArr2[2], fArr2[3]);
                Path path3 = this.f1305d;
                float[] fArr3 = this.f1311j;
                path3.lineTo(fArr3[4], fArr3[5]);
                Path path4 = this.f1305d;
                float[] fArr4 = this.f1311j;
                path4.lineTo(fArr4[6], fArr4[7]);
                this.f1305d.close();
            }
            this.f1306e.setColor(1140850688);
            canvas.translate(2.0f, 2.0f);
            canvas.drawPath(this.f1305d, this.f1306e);
            canvas.translate(-2.0f, -2.0f);
            this.f1306e.setColor(-65536);
            canvas.drawPath(this.f1305d, this.f1306e);
        }

        private void k(Canvas canvas, int i7, int i8, m mVar) {
            int width;
            int height;
            float f7;
            float f8;
            View view = mVar.f1472b;
            if (view != null) {
                width = view.getWidth();
                height = mVar.f1472b.getHeight();
            } else {
                width = 0;
                height = 0;
            }
            for (int i9 = 1; i9 < i8 - 1; i9++) {
                if (i7 != 4 || this.f1303b[i9 - 1] != 0) {
                    float[] fArr = this.f1304c;
                    int i10 = i9 * 2;
                    float f9 = fArr[i10];
                    float f10 = fArr[i10 + 1];
                    this.f1305d.reset();
                    this.f1305d.moveTo(f9, f10 + 10.0f);
                    this.f1305d.lineTo(f9 + 10.0f, f10);
                    this.f1305d.lineTo(f9, f10 - 10.0f);
                    this.f1305d.lineTo(f9 - 10.0f, f10);
                    this.f1305d.close();
                    int i11 = i9 - 1;
                    mVar.q(i11);
                    if (i7 == 4) {
                        int[] iArr = this.f1303b;
                        if (iArr[i11] == 1) {
                            h(canvas, f9 - 0.0f, f10 - 0.0f);
                        } else if (iArr[i11] == 0) {
                            f(canvas, f9 - 0.0f, f10 - 0.0f);
                        } else {
                            if (iArr[i11] == 2) {
                                f7 = f10;
                                f8 = f9;
                                i(canvas, f9 - 0.0f, f10 - 0.0f, width, height);
                            }
                            canvas.drawPath(this.f1305d, this.f1310i);
                        }
                        f7 = f10;
                        f8 = f9;
                        canvas.drawPath(this.f1305d, this.f1310i);
                    } else {
                        f7 = f10;
                        f8 = f9;
                    }
                    if (i7 == 2) {
                        h(canvas, f8 - 0.0f, f7 - 0.0f);
                    }
                    if (i7 == 3) {
                        f(canvas, f8 - 0.0f, f7 - 0.0f);
                    }
                    if (i7 == 6) {
                        i(canvas, f8 - 0.0f, f7 - 0.0f, width, height);
                    }
                    canvas.drawPath(this.f1305d, this.f1310i);
                }
            }
            float[] fArr2 = this.f1302a;
            if (fArr2.length > 1) {
                canvas.drawCircle(fArr2[0], fArr2[1], 8.0f, this.f1307f);
                float[] fArr3 = this.f1302a;
                canvas.drawCircle(fArr3[fArr3.length - 2], fArr3[fArr3.length - 1], 8.0f, this.f1307f);
            }
        }

        public void a(Canvas canvas, HashMap<View, m> map, int i7, int i8) {
            if (map == null || map.size() == 0) {
                return;
            }
            canvas.save();
            if (!MotionLayout.this.isInEditMode() && (i8 & 1) == 2) {
                String str = MotionLayout.this.getContext().getResources().getResourceName(MotionLayout.this.J) + ":" + MotionLayout.this.getProgress();
                canvas.drawText(str, 10.0f, MotionLayout.this.getHeight() - 30, this.f1309h);
                canvas.drawText(str, 11.0f, MotionLayout.this.getHeight() - 29, this.f1306e);
            }
            for (m mVar : map.values()) {
                int iM = mVar.m();
                if (i8 > 0 && iM == 0) {
                    iM = 1;
                }
                if (iM != 0) {
                    this.f1318q = mVar.c(this.f1304c, this.f1303b);
                    if (iM >= 1) {
                        int i9 = i7 / 16;
                        float[] fArr = this.f1302a;
                        if (fArr == null || fArr.length != i9 * 2) {
                            this.f1302a = new float[i9 * 2];
                            this.f1305d = new Path();
                        }
                        int i10 = this.f1321t;
                        canvas.translate(i10, i10);
                        this.f1306e.setColor(1996488704);
                        this.f1310i.setColor(1996488704);
                        this.f1307f.setColor(1996488704);
                        this.f1308g.setColor(1996488704);
                        mVar.d(this.f1302a, i9);
                        b(canvas, iM, this.f1318q, mVar);
                        this.f1306e.setColor(-21965);
                        this.f1307f.setColor(-2067046);
                        this.f1310i.setColor(-2067046);
                        this.f1308g.setColor(-13391360);
                        int i11 = this.f1321t;
                        canvas.translate(-i11, -i11);
                        b(canvas, iM, this.f1318q, mVar);
                        if (iM == 5) {
                            j(canvas, mVar);
                        }
                    }
                }
            }
            canvas.restore();
        }

        public void b(Canvas canvas, int i7, int i8, m mVar) {
            if (i7 == 4) {
                d(canvas);
            }
            if (i7 == 2) {
                g(canvas);
            }
            if (i7 == 3) {
                e(canvas);
            }
            c(canvas);
            k(canvas, i7, i8, mVar);
        }

        void l(String str, Paint paint) {
            paint.getTextBounds(str, 0, str.length(), this.f1319r);
        }
    }

    class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        r.f f1323a = new r.f();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        r.f f1324b = new r.f();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        androidx.constraintlayout.widget.d f1325c = null;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        androidx.constraintlayout.widget.d f1326d = null;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f1327e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        int f1328f;

        f() {
        }

        private void b(int i7, int i8) {
            int optimizationLevel = MotionLayout.this.getOptimizationLevel();
            MotionLayout motionLayout = MotionLayout.this;
            if (motionLayout.I == motionLayout.getStartState()) {
                MotionLayout motionLayout2 = MotionLayout.this;
                r.f fVar = this.f1324b;
                androidx.constraintlayout.widget.d dVar = this.f1326d;
                motionLayout2.v(fVar, optimizationLevel, (dVar == null || dVar.f1771d == 0) ? i7 : i8, (dVar == null || dVar.f1771d == 0) ? i8 : i7);
                androidx.constraintlayout.widget.d dVar2 = this.f1325c;
                if (dVar2 != null) {
                    MotionLayout motionLayout3 = MotionLayout.this;
                    r.f fVar2 = this.f1323a;
                    int i9 = dVar2.f1771d;
                    int i10 = i9 == 0 ? i7 : i8;
                    if (i9 == 0) {
                        i7 = i8;
                    }
                    motionLayout3.v(fVar2, optimizationLevel, i10, i7);
                    return;
                }
                return;
            }
            androidx.constraintlayout.widget.d dVar3 = this.f1325c;
            if (dVar3 != null) {
                MotionLayout motionLayout4 = MotionLayout.this;
                r.f fVar3 = this.f1323a;
                int i11 = dVar3.f1771d;
                motionLayout4.v(fVar3, optimizationLevel, i11 == 0 ? i7 : i8, i11 == 0 ? i8 : i7);
            }
            MotionLayout motionLayout5 = MotionLayout.this;
            r.f fVar4 = this.f1324b;
            androidx.constraintlayout.widget.d dVar4 = this.f1326d;
            int i12 = (dVar4 == null || dVar4.f1771d == 0) ? i7 : i8;
            if (dVar4 == null || dVar4.f1771d == 0) {
                i7 = i8;
            }
            motionLayout5.v(fVar4, optimizationLevel, i12, i7);
        }

        /* JADX WARN: Multi-variable type inference failed */
        private void j(r.f fVar, androidx.constraintlayout.widget.d dVar) {
            SparseArray<r.e> sparseArray = new SparseArray<>();
            e.a aVar = new e.a(-2, -2);
            sparseArray.clear();
            sparseArray.put(0, fVar);
            sparseArray.put(MotionLayout.this.getId(), fVar);
            if (dVar != null && dVar.f1771d != 0) {
                MotionLayout motionLayout = MotionLayout.this;
                motionLayout.v(this.f1324b, motionLayout.getOptimizationLevel(), View.MeasureSpec.makeMeasureSpec(MotionLayout.this.getHeight(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(MotionLayout.this.getWidth(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
            }
            for (r.e eVar : fVar.u1()) {
                sparseArray.put(((View) eVar.u()).getId(), eVar);
            }
            for (r.e eVar2 : fVar.u1()) {
                View view = (View) eVar2.u();
                dVar.l(view.getId(), aVar);
                eVar2.n1(dVar.C(view.getId()));
                eVar2.O0(dVar.x(view.getId()));
                if (view instanceof androidx.constraintlayout.widget.b) {
                    dVar.j((androidx.constraintlayout.widget.b) view, eVar2, aVar, sparseArray);
                    if (view instanceof Barrier) {
                        ((Barrier) view).w();
                    }
                }
                aVar.resolveLayoutDirection(MotionLayout.this.getLayoutDirection());
                MotionLayout.this.d(false, view, eVar2, aVar, sparseArray);
                if (dVar.B(view.getId()) == 1) {
                    eVar2.m1(view.getVisibility());
                } else {
                    eVar2.m1(dVar.A(view.getId()));
                }
            }
            for (r.e eVar3 : fVar.u1()) {
                if (eVar3 instanceof r.m) {
                    androidx.constraintlayout.widget.b bVar = (androidx.constraintlayout.widget.b) eVar3.u();
                    r.i iVar = (r.i) eVar3;
                    bVar.v(fVar, iVar, sparseArray);
                    ((r.m) iVar).w1();
                }
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:24:0x00e9  */
        /* JADX WARN: Removed duplicated region for block: B:42:0x013d A[SYNTHETIC] */
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

        void c(r.f fVar, r.f fVar2) {
            ArrayList<r.e> arrayListU1 = fVar.u1();
            HashMap<r.e, r.e> map = new HashMap<>();
            map.put(fVar, fVar2);
            fVar2.u1().clear();
            fVar2.n(fVar, map);
            for (r.e eVar : arrayListU1) {
                r.e aVar = eVar instanceof r.a ? new r.a() : eVar instanceof r.h ? new r.h() : eVar instanceof r.g ? new r.g() : eVar instanceof r.l ? new r.l() : eVar instanceof r.i ? new r.j() : new r.e();
                fVar2.c(aVar);
                map.put(eVar, aVar);
            }
            for (r.e eVar2 : arrayListU1) {
                map.get(eVar2).n(eVar2, map);
            }
        }

        r.e d(r.f fVar, View view) {
            if (fVar.u() == view) {
                return fVar;
            }
            ArrayList<r.e> arrayListU1 = fVar.u1();
            int size = arrayListU1.size();
            for (int i7 = 0; i7 < size; i7++) {
                r.e eVar = arrayListU1.get(i7);
                if (eVar.u() == view) {
                    return eVar;
                }
            }
            return null;
        }

        void e(r.f fVar, androidx.constraintlayout.widget.d dVar, androidx.constraintlayout.widget.d dVar2) {
            this.f1325c = dVar;
            this.f1326d = dVar2;
            this.f1323a = new r.f();
            this.f1324b = new r.f();
            this.f1323a.Y1(((ConstraintLayout) MotionLayout.this).f1652h.L1());
            this.f1324b.Y1(((ConstraintLayout) MotionLayout.this).f1652h.L1());
            this.f1323a.x1();
            this.f1324b.x1();
            c(((ConstraintLayout) MotionLayout.this).f1652h, this.f1323a);
            c(((ConstraintLayout) MotionLayout.this).f1652h, this.f1324b);
            if (MotionLayout.this.R > 0.5d) {
                if (dVar != null) {
                    j(this.f1323a, dVar);
                }
                j(this.f1324b, dVar2);
            } else {
                j(this.f1324b, dVar2);
                if (dVar != null) {
                    j(this.f1323a, dVar);
                }
            }
            this.f1323a.b2(MotionLayout.this.r());
            this.f1323a.d2();
            this.f1324b.b2(MotionLayout.this.r());
            this.f1324b.d2();
            ViewGroup.LayoutParams layoutParams = MotionLayout.this.getLayoutParams();
            if (layoutParams != null) {
                if (layoutParams.width == -2) {
                    r.f fVar2 = this.f1323a;
                    e.b bVar = e.b.WRAP_CONTENT;
                    fVar2.S0(bVar);
                    this.f1324b.S0(bVar);
                }
                if (layoutParams.height == -2) {
                    r.f fVar3 = this.f1323a;
                    e.b bVar2 = e.b.WRAP_CONTENT;
                    fVar3.j1(bVar2);
                    this.f1324b.j1(bVar2);
                }
            }
        }

        public boolean f(int i7, int i8) {
            return (i7 == this.f1327e && i8 == this.f1328f) ? false : true;
        }

        public void g(int i7, int i8) {
            int mode = View.MeasureSpec.getMode(i7);
            int mode2 = View.MeasureSpec.getMode(i8);
            MotionLayout motionLayout = MotionLayout.this;
            motionLayout.J0 = mode;
            motionLayout.K0 = mode2;
            motionLayout.getOptimizationLevel();
            b(i7, i8);
            if (((MotionLayout.this.getParent() instanceof MotionLayout) && mode == 1073741824 && mode2 == 1073741824) ? false : true) {
                b(i7, i8);
                MotionLayout.this.F0 = this.f1323a.Y();
                MotionLayout.this.G0 = this.f1323a.z();
                MotionLayout.this.H0 = this.f1324b.Y();
                MotionLayout.this.I0 = this.f1324b.z();
                MotionLayout motionLayout2 = MotionLayout.this;
                motionLayout2.E0 = (motionLayout2.F0 == motionLayout2.H0 && motionLayout2.G0 == motionLayout2.I0) ? false : true;
            }
            MotionLayout motionLayout3 = MotionLayout.this;
            int i9 = motionLayout3.F0;
            int i10 = motionLayout3.G0;
            int i11 = motionLayout3.J0;
            if (i11 == Integer.MIN_VALUE || i11 == 0) {
                i9 = (int) (i9 + (motionLayout3.L0 * (motionLayout3.H0 - i9)));
            }
            int i12 = i9;
            int i13 = motionLayout3.K0;
            if (i13 == Integer.MIN_VALUE || i13 == 0) {
                i10 = (int) (i10 + (motionLayout3.L0 * (motionLayout3.I0 - i10)));
            }
            MotionLayout.this.u(i7, i8, i12, i10, this.f1323a.T1() || this.f1324b.T1(), this.f1323a.R1() || this.f1324b.R1());
        }

        public void h() {
            g(MotionLayout.this.K, MotionLayout.this.L);
            MotionLayout.this.z0();
        }

        public void i(int i7, int i8) {
            this.f1327e = i7;
            this.f1328f = i8;
        }
    }

    protected interface g {
        void a();

        void b(MotionEvent motionEvent);

        float c();

        float d();

        void e(int i7);
    }

    private static class h implements g {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private static h f1330b = new h();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        VelocityTracker f1331a;

        private h() {
        }

        public static h f() {
            f1330b.f1331a = VelocityTracker.obtain();
            return f1330b;
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.g
        public void a() {
            VelocityTracker velocityTracker = this.f1331a;
            if (velocityTracker != null) {
                velocityTracker.recycle();
                this.f1331a = null;
            }
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.g
        public void b(MotionEvent motionEvent) {
            VelocityTracker velocityTracker = this.f1331a;
            if (velocityTracker != null) {
                velocityTracker.addMovement(motionEvent);
            }
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.g
        public float c() {
            VelocityTracker velocityTracker = this.f1331a;
            if (velocityTracker != null) {
                return velocityTracker.getYVelocity();
            }
            return 0.0f;
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.g
        public float d() {
            VelocityTracker velocityTracker = this.f1331a;
            if (velocityTracker != null) {
                return velocityTracker.getXVelocity();
            }
            return 0.0f;
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.g
        public void e(int i7) {
            VelocityTracker velocityTracker = this.f1331a;
            if (velocityTracker != null) {
                velocityTracker.computeCurrentVelocity(i7);
            }
        }
    }

    class i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        float f1332a = Float.NaN;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        float f1333b = Float.NaN;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1334c = -1;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f1335d = -1;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final String f1336e = "motion.progress";

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final String f1337f = "motion.velocity";

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final String f1338g = "motion.StartState";

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        final String f1339h = "motion.EndState";

        i() {
        }

        void a() {
            int i7 = this.f1334c;
            if (i7 != -1 || this.f1335d != -1) {
                if (i7 == -1) {
                    MotionLayout.this.F0(this.f1335d);
                } else {
                    int i8 = this.f1335d;
                    if (i8 == -1) {
                        MotionLayout.this.x0(i7, -1, -1);
                    } else {
                        MotionLayout.this.y0(i7, i8);
                    }
                }
                MotionLayout.this.setState(k.SETUP);
            }
            if (Float.isNaN(this.f1333b)) {
                if (Float.isNaN(this.f1332a)) {
                    return;
                }
                MotionLayout.this.setProgress(this.f1332a);
            } else {
                MotionLayout.this.w0(this.f1332a, this.f1333b);
                this.f1332a = Float.NaN;
                this.f1333b = Float.NaN;
                this.f1334c = -1;
                this.f1335d = -1;
            }
        }

        public Bundle b() {
            Bundle bundle = new Bundle();
            bundle.putFloat("motion.progress", this.f1332a);
            bundle.putFloat("motion.velocity", this.f1333b);
            bundle.putInt("motion.StartState", this.f1334c);
            bundle.putInt("motion.EndState", this.f1335d);
            return bundle;
        }

        public void c() {
            this.f1335d = MotionLayout.this.J;
            this.f1334c = MotionLayout.this.H;
            this.f1333b = MotionLayout.this.getVelocity();
            this.f1332a = MotionLayout.this.getProgress();
        }

        public void d(int i7) {
            this.f1335d = i7;
        }

        public void e(float f7) {
            this.f1332a = f7;
        }

        public void f(int i7) {
            this.f1334c = i7;
        }

        public void g(Bundle bundle) {
            this.f1332a = bundle.getFloat("motion.progress");
            this.f1333b = bundle.getFloat("motion.velocity");
            this.f1334c = bundle.getInt("motion.StartState");
            this.f1335d = bundle.getInt("motion.EndState");
        }

        public void h(float f7) {
            this.f1333b = f7;
        }
    }

    public interface j {
        void a(MotionLayout motionLayout, int i7, int i8);

        void b(MotionLayout motionLayout, int i7, boolean z6, float f7);

        void c(MotionLayout motionLayout, int i7, int i8, float f7);

        void d(MotionLayout motionLayout, int i7);
    }

    enum k {
        UNDEFINED,
        SETUP,
        MOVING,
        FINISHED
    }

    public MotionLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.F = null;
        this.G = 0.0f;
        this.H = -1;
        this.I = -1;
        this.J = -1;
        this.K = 0;
        this.L = 0;
        this.M = true;
        this.N = new HashMap<>();
        this.O = 0L;
        this.P = 1.0f;
        this.Q = 0.0f;
        this.R = 0.0f;
        this.T = 0.0f;
        this.V = false;
        this.W = false;
        this.f1267d0 = 0;
        this.f1271f0 = false;
        this.f1273g0 = new t.b();
        this.f1275h0 = new d();
        this.f1277j0 = true;
        this.f1282o0 = false;
        this.f1287t0 = false;
        this.f1288u0 = null;
        this.f1289v0 = null;
        this.f1290w0 = null;
        this.f1291x0 = null;
        this.f1292y0 = 0;
        this.f1293z0 = -1L;
        this.A0 = 0.0f;
        this.B0 = 0;
        this.C0 = 0.0f;
        this.D0 = false;
        this.E0 = false;
        this.M0 = new p.d();
        this.N0 = false;
        this.P0 = null;
        this.Q0 = null;
        this.R0 = 0;
        this.S0 = false;
        this.T0 = 0;
        this.U0 = new HashMap<>();
        this.Y0 = new Rect();
        this.Z0 = false;
        this.f1262a1 = k.UNDEFINED;
        this.f1264b1 = new f();
        this.f1266c1 = false;
        this.f1268d1 = new RectF();
        this.f1270e1 = null;
        this.f1272f1 = null;
        this.f1274g1 = new ArrayList<>();
        q0(attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Rect A0(r.e eVar) {
        this.Y0.top = eVar.a0();
        this.Y0.left = eVar.Z();
        Rect rect = this.Y0;
        int iY = eVar.Y();
        Rect rect2 = this.Y0;
        rect.right = iY + rect2.left;
        int iZ = eVar.z();
        Rect rect3 = this.Y0;
        rect2.bottom = iZ + rect3.top;
        return rect3;
    }

    private static boolean L0(float f7, float f8, float f9) {
        if (f7 > 0.0f) {
            float f10 = f7 / f9;
            return f8 + ((f7 * f10) - (((f9 * f10) * f10) / 2.0f)) > 1.0f;
        }
        float f11 = (-f7) / f9;
        return f8 + ((f7 * f11) + (((f9 * f11) * f11) / 2.0f)) < 0.0f;
    }

    private boolean a0(View view, MotionEvent motionEvent, float f7, float f8) {
        Matrix matrix = view.getMatrix();
        if (matrix.isIdentity()) {
            motionEvent.offsetLocation(f7, f8);
            boolean zOnTouchEvent = view.onTouchEvent(motionEvent);
            motionEvent.offsetLocation(-f7, -f8);
            return zOnTouchEvent;
        }
        MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
        motionEventObtain.offsetLocation(f7, f8);
        if (this.f1272f1 == null) {
            this.f1272f1 = new Matrix();
        }
        matrix.invert(this.f1272f1);
        motionEventObtain.transform(this.f1272f1);
        boolean zOnTouchEvent2 = view.onTouchEvent(motionEventObtain);
        motionEventObtain.recycle();
        return zOnTouchEvent2;
    }

    private void b0() {
        q qVar = this.D;
        if (qVar == null) {
            Log.e("MotionLayout", "CHECK: motion scene not set! set \"app:layoutDescription=\"@xml/file\"");
            return;
        }
        int iE = qVar.E();
        q qVar2 = this.D;
        c0(iE, qVar2.k(qVar2.E()));
        SparseIntArray sparseIntArray = new SparseIntArray();
        SparseIntArray sparseIntArray2 = new SparseIntArray();
        for (q.b bVar : this.D.n()) {
            if (bVar == this.D.f1524c) {
                Log.v("MotionLayout", "CHECK: CURRENT");
            }
            d0(bVar);
            int iA = bVar.A();
            int iY = bVar.y();
            String strC = androidx.constraintlayout.motion.widget.a.c(getContext(), iA);
            String strC2 = androidx.constraintlayout.motion.widget.a.c(getContext(), iY);
            if (sparseIntArray.get(iA) == iY) {
                Log.e("MotionLayout", "CHECK: two transitions with the same start and end " + strC + "->" + strC2);
            }
            if (sparseIntArray2.get(iY) == iA) {
                Log.e("MotionLayout", "CHECK: you can't have reverse transitions" + strC + "->" + strC2);
            }
            sparseIntArray.put(iA, iY);
            sparseIntArray2.put(iY, iA);
            if (this.D.k(iA) == null) {
                Log.e("MotionLayout", " no such constraintSetStart " + strC);
            }
            if (this.D.k(iY) == null) {
                Log.e("MotionLayout", " no such constraintSetEnd " + strC);
            }
        }
    }

    private void c0(int i7, androidx.constraintlayout.widget.d dVar) {
        String strC = androidx.constraintlayout.motion.widget.a.c(getContext(), i7);
        int childCount = getChildCount();
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = getChildAt(i8);
            int id = childAt.getId();
            if (id == -1) {
                Log.w("MotionLayout", "CHECK: " + strC + " ALL VIEWS SHOULD HAVE ID's " + childAt.getClass().getName() + " does not!");
            }
            if (dVar.w(id) == null) {
                Log.w("MotionLayout", "CHECK: " + strC + " NO CONSTRAINTS for " + androidx.constraintlayout.motion.widget.a.d(childAt));
            }
        }
        int[] iArrY = dVar.y();
        for (int i9 = 0; i9 < iArrY.length; i9++) {
            int i10 = iArrY[i9];
            String strC2 = androidx.constraintlayout.motion.widget.a.c(getContext(), i10);
            if (findViewById(iArrY[i9]) == null) {
                Log.w("MotionLayout", "CHECK: " + strC + " NO View matches id " + strC2);
            }
            if (dVar.x(i10) == -1) {
                Log.w("MotionLayout", "CHECK: " + strC + "(" + strC2 + ") no LAYOUT_HEIGHT");
            }
            if (dVar.C(i10) == -1) {
                Log.w("MotionLayout", "CHECK: " + strC + "(" + strC2 + ") no LAYOUT_HEIGHT");
            }
        }
    }

    private void d0(q.b bVar) {
        if (bVar.A() == bVar.y()) {
            Log.e("MotionLayout", "CHECK: start and end constraint set should not be the same!");
        }
    }

    private void e0() {
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            m mVar = this.N.get(childAt);
            if (mVar != null) {
                mVar.B(childAt);
            }
        }
    }

    private void h0() {
        boolean z6;
        float fSignum = Math.signum(this.T - this.R);
        long nanoTime = getNanoTime();
        Interpolator interpolator = this.E;
        float interpolation = this.R + (!(interpolator instanceof t.b) ? (((nanoTime - this.S) * fSignum) * 1.0E-9f) / this.P : 0.0f);
        if (this.U) {
            interpolation = this.T;
        }
        if ((fSignum <= 0.0f || interpolation < this.T) && (fSignum > 0.0f || interpolation > this.T)) {
            z6 = false;
        } else {
            interpolation = this.T;
            z6 = true;
        }
        if (interpolator != null && !z6) {
            interpolation = this.f1271f0 ? interpolator.getInterpolation((nanoTime - this.O) * 1.0E-9f) : interpolator.getInterpolation(interpolation);
        }
        if ((fSignum > 0.0f && interpolation >= this.T) || (fSignum <= 0.0f && interpolation <= this.T)) {
            interpolation = this.T;
        }
        this.L0 = interpolation;
        int childCount = getChildCount();
        long nanoTime2 = getNanoTime();
        Interpolator interpolator2 = this.F;
        if (interpolator2 != null) {
            interpolation = interpolator2.getInterpolation(interpolation);
        }
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            m mVar = this.N.get(childAt);
            if (mVar != null) {
                mVar.u(childAt, interpolation, nanoTime2, this.M0);
            }
        }
        if (this.E0) {
            requestLayout();
        }
    }

    private void i0() {
        CopyOnWriteArrayList<j> copyOnWriteArrayList;
        if ((this.f1261a0 == null && ((copyOnWriteArrayList = this.f1291x0) == null || copyOnWriteArrayList.isEmpty())) || this.C0 == this.Q) {
            return;
        }
        if (this.B0 != -1) {
            j jVar = this.f1261a0;
            if (jVar != null) {
                jVar.a(this, this.H, this.J);
            }
            CopyOnWriteArrayList<j> copyOnWriteArrayList2 = this.f1291x0;
            if (copyOnWriteArrayList2 != null) {
                Iterator<j> it = copyOnWriteArrayList2.iterator();
                while (it.hasNext()) {
                    it.next().a(this, this.H, this.J);
                }
            }
            this.D0 = true;
        }
        this.B0 = -1;
        float f7 = this.Q;
        this.C0 = f7;
        j jVar2 = this.f1261a0;
        if (jVar2 != null) {
            jVar2.c(this, this.H, this.J, f7);
        }
        CopyOnWriteArrayList<j> copyOnWriteArrayList3 = this.f1291x0;
        if (copyOnWriteArrayList3 != null) {
            Iterator<j> it2 = copyOnWriteArrayList3.iterator();
            while (it2.hasNext()) {
                it2.next().c(this, this.H, this.J, this.Q);
            }
        }
        this.D0 = true;
    }

    private boolean p0(float f7, float f8, View view, MotionEvent motionEvent) {
        boolean z6;
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                if (p0((r3.getLeft() + f7) - view.getScrollX(), (r3.getTop() + f8) - view.getScrollY(), viewGroup.getChildAt(childCount), motionEvent)) {
                    z6 = true;
                    break;
                }
            }
            z6 = false;
        } else {
            z6 = false;
        }
        if (!z6) {
            this.f1268d1.set(f7, f8, (view.getRight() + f7) - view.getLeft(), (view.getBottom() + f8) - view.getTop());
            if ((motionEvent.getAction() != 0 || this.f1268d1.contains(motionEvent.getX(), motionEvent.getY())) && a0(view, motionEvent, -f7, -f8)) {
                return true;
            }
        }
        return z6;
    }

    private void q0(AttributeSet attributeSet) {
        q qVar;
        f1260h1 = isInEditMode();
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, androidx.constraintlayout.widget.i.MotionLayout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            boolean z6 = true;
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i7);
                if (index == androidx.constraintlayout.widget.i.MotionLayout_layoutDescription) {
                    this.D = new q(getContext(), this, typedArrayObtainStyledAttributes.getResourceId(index, -1));
                } else if (index == androidx.constraintlayout.widget.i.MotionLayout_currentState) {
                    this.I = typedArrayObtainStyledAttributes.getResourceId(index, -1);
                } else if (index == androidx.constraintlayout.widget.i.MotionLayout_motionProgress) {
                    this.T = typedArrayObtainStyledAttributes.getFloat(index, 0.0f);
                    this.V = true;
                } else if (index == androidx.constraintlayout.widget.i.MotionLayout_applyMotionScene) {
                    z6 = typedArrayObtainStyledAttributes.getBoolean(index, z6);
                } else if (index == androidx.constraintlayout.widget.i.MotionLayout_showPaths) {
                    if (this.f1267d0 == 0) {
                        this.f1267d0 = typedArrayObtainStyledAttributes.getBoolean(index, false) ? 2 : 0;
                    }
                } else if (index == androidx.constraintlayout.widget.i.MotionLayout_motionDebug) {
                    this.f1267d0 = typedArrayObtainStyledAttributes.getInt(index, 0);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
            if (this.D == null) {
                Log.e("MotionLayout", "WARNING NO app:layoutDescription tag");
            }
            if (!z6) {
                this.D = null;
            }
        }
        if (this.f1267d0 != 0) {
            b0();
        }
        if (this.I != -1 || (qVar = this.D) == null) {
            return;
        }
        this.I = qVar.E();
        this.H = this.D.E();
        this.J = this.D.p();
    }

    private void u0() {
        CopyOnWriteArrayList<j> copyOnWriteArrayList;
        if (this.f1261a0 == null && ((copyOnWriteArrayList = this.f1291x0) == null || copyOnWriteArrayList.isEmpty())) {
            return;
        }
        this.D0 = false;
        for (Integer num : this.f1274g1) {
            j jVar = this.f1261a0;
            if (jVar != null) {
                jVar.d(this, num.intValue());
            }
            CopyOnWriteArrayList<j> copyOnWriteArrayList2 = this.f1291x0;
            if (copyOnWriteArrayList2 != null) {
                Iterator<j> it = copyOnWriteArrayList2.iterator();
                while (it.hasNext()) {
                    it.next().d(this, num.intValue());
                }
            }
        }
        this.f1274g1.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z0() {
        int childCount = getChildCount();
        this.f1264b1.a();
        boolean z6 = true;
        this.V = true;
        SparseArray sparseArray = new SparseArray();
        int i7 = 0;
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = getChildAt(i8);
            sparseArray.put(childAt.getId(), this.N.get(childAt));
        }
        int width = getWidth();
        int height = getHeight();
        int i9 = this.D.i();
        if (i9 != -1) {
            for (int i10 = 0; i10 < childCount; i10++) {
                m mVar = this.N.get(getChildAt(i10));
                if (mVar != null) {
                    mVar.A(i9);
                }
            }
        }
        SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();
        int[] iArr = new int[this.N.size()];
        int i11 = 0;
        for (int i12 = 0; i12 < childCount; i12++) {
            m mVar2 = this.N.get(getChildAt(i12));
            if (mVar2.h() != -1) {
                sparseBooleanArray.put(mVar2.h(), true);
                iArr[i11] = mVar2.h();
                i11++;
            }
        }
        if (this.f1290w0 != null) {
            for (int i13 = 0; i13 < i11; i13++) {
                m mVar3 = this.N.get(findViewById(iArr[i13]));
                if (mVar3 != null) {
                    this.D.s(mVar3);
                }
            }
            Iterator<n> it = this.f1290w0.iterator();
            while (it.hasNext()) {
                it.next().D(this, this.N);
            }
            for (int i14 = 0; i14 < i11; i14++) {
                m mVar4 = this.N.get(findViewById(iArr[i14]));
                if (mVar4 != null) {
                    mVar4.F(width, height, this.P, getNanoTime());
                }
            }
        } else {
            for (int i15 = 0; i15 < i11; i15++) {
                m mVar5 = this.N.get(findViewById(iArr[i15]));
                if (mVar5 != null) {
                    this.D.s(mVar5);
                    mVar5.F(width, height, this.P, getNanoTime());
                }
            }
        }
        for (int i16 = 0; i16 < childCount; i16++) {
            View childAt2 = getChildAt(i16);
            m mVar6 = this.N.get(childAt2);
            if (!sparseBooleanArray.get(childAt2.getId()) && mVar6 != null) {
                this.D.s(mVar6);
                mVar6.F(width, height, this.P, getNanoTime());
            }
        }
        float fD = this.D.D();
        if (fD != 0.0f) {
            boolean z7 = ((double) fD) < 0.0d;
            float fAbs = Math.abs(fD);
            float fMax = -3.4028235E38f;
            float fMin = Float.MAX_VALUE;
            int i17 = 0;
            float fMax2 = -3.4028235E38f;
            float fMin2 = Float.MAX_VALUE;
            while (true) {
                if (i17 >= childCount) {
                    z6 = false;
                    break;
                }
                m mVar7 = this.N.get(getChildAt(i17));
                if (!Float.isNaN(mVar7.f1483m)) {
                    break;
                }
                float fN = mVar7.n();
                float fO = mVar7.o();
                float f7 = z7 ? fO - fN : fO + fN;
                fMin2 = Math.min(fMin2, f7);
                fMax2 = Math.max(fMax2, f7);
                i17++;
            }
            if (!z6) {
                while (i7 < childCount) {
                    m mVar8 = this.N.get(getChildAt(i7));
                    float fN2 = mVar8.n();
                    float fO2 = mVar8.o();
                    float f8 = z7 ? fO2 - fN2 : fO2 + fN2;
                    mVar8.f1485o = 1.0f / (1.0f - fAbs);
                    mVar8.f1484n = fAbs - (((f8 - fMin2) * fAbs) / (fMax2 - fMin2));
                    i7++;
                }
                return;
            }
            for (int i18 = 0; i18 < childCount; i18++) {
                m mVar9 = this.N.get(getChildAt(i18));
                if (!Float.isNaN(mVar9.f1483m)) {
                    fMin = Math.min(fMin, mVar9.f1483m);
                    fMax = Math.max(fMax, mVar9.f1483m);
                }
            }
            while (i7 < childCount) {
                m mVar10 = this.N.get(getChildAt(i7));
                if (!Float.isNaN(mVar10.f1483m)) {
                    mVar10.f1485o = 1.0f / (1.0f - fAbs);
                    if (z7) {
                        mVar10.f1484n = fAbs - (((fMax - mVar10.f1483m) / (fMax - fMin)) * fAbs);
                    } else {
                        mVar10.f1484n = fAbs - (((mVar10.f1483m - fMin) * fAbs) / (fMax - fMin));
                    }
                }
                i7++;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0093  */
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
        Z(1.0f);
        this.P0 = null;
    }

    public void D0(Runnable runnable) {
        Z(1.0f);
        this.P0 = runnable;
    }

    public void E0() {
        Z(0.0f);
    }

    public void F0(int i7) {
        if (isAttachedToWindow()) {
            G0(i7, -1, -1);
            return;
        }
        if (this.O0 == null) {
            this.O0 = new i();
        }
        this.O0.d(i7);
    }

    public void G0(int i7, int i8, int i9) {
        H0(i7, i8, i9, -1);
    }

    public void H0(int i7, int i8, int i9, int i10) {
        androidx.constraintlayout.widget.k kVar;
        int iA;
        q qVar = this.D;
        if (qVar != null && (kVar = qVar.f1523b) != null && (iA = kVar.a(this.I, i7, i8, i9)) != -1) {
            i7 = iA;
        }
        int i11 = this.I;
        if (i11 == i7) {
            return;
        }
        if (this.H == i7) {
            Z(0.0f);
            if (i10 > 0) {
                this.P = i10 / 1000.0f;
                return;
            }
            return;
        }
        if (this.J == i7) {
            Z(1.0f);
            if (i10 > 0) {
                this.P = i10 / 1000.0f;
                return;
            }
            return;
        }
        this.J = i7;
        if (i11 != -1) {
            y0(i11, i7);
            Z(1.0f);
            this.R = 0.0f;
            C0();
            if (i10 > 0) {
                this.P = i10 / 1000.0f;
                return;
            }
            return;
        }
        this.f1271f0 = false;
        this.T = 1.0f;
        this.Q = 0.0f;
        this.R = 0.0f;
        this.S = getNanoTime();
        this.O = getNanoTime();
        this.U = false;
        this.E = null;
        if (i10 == -1) {
            this.P = this.D.o() / 1000.0f;
        }
        this.H = -1;
        this.D.W(-1, this.J);
        SparseArray sparseArray = new SparseArray();
        if (i10 == 0) {
            this.P = this.D.o() / 1000.0f;
        } else if (i10 > 0) {
            this.P = i10 / 1000.0f;
        }
        int childCount = getChildCount();
        this.N.clear();
        for (int i12 = 0; i12 < childCount; i12++) {
            View childAt = getChildAt(i12);
            this.N.put(childAt, new m(childAt));
            sparseArray.put(childAt.getId(), this.N.get(childAt));
        }
        this.V = true;
        this.f1264b1.e(this.f1652h, null, this.D.k(i7));
        v0();
        this.f1264b1.a();
        e0();
        int width = getWidth();
        int height = getHeight();
        if (this.f1290w0 != null) {
            for (int i13 = 0; i13 < childCount; i13++) {
                m mVar = this.N.get(getChildAt(i13));
                if (mVar != null) {
                    this.D.s(mVar);
                }
            }
            Iterator<n> it = this.f1290w0.iterator();
            while (it.hasNext()) {
                it.next().D(this, this.N);
            }
            for (int i14 = 0; i14 < childCount; i14++) {
                m mVar2 = this.N.get(getChildAt(i14));
                if (mVar2 != null) {
                    mVar2.F(width, height, this.P, getNanoTime());
                }
            }
        } else {
            for (int i15 = 0; i15 < childCount; i15++) {
                m mVar3 = this.N.get(getChildAt(i15));
                if (mVar3 != null) {
                    this.D.s(mVar3);
                    mVar3.F(width, height, this.P, getNanoTime());
                }
            }
        }
        float fD = this.D.D();
        if (fD != 0.0f) {
            float fMin = Float.MAX_VALUE;
            float fMax = -3.4028235E38f;
            for (int i16 = 0; i16 < childCount; i16++) {
                m mVar4 = this.N.get(getChildAt(i16));
                float fO = mVar4.o() + mVar4.n();
                fMin = Math.min(fMin, fO);
                fMax = Math.max(fMax, fO);
            }
            for (int i17 = 0; i17 < childCount; i17++) {
                m mVar5 = this.N.get(getChildAt(i17));
                float fN = mVar5.n();
                float fO2 = mVar5.o();
                mVar5.f1485o = 1.0f / (1.0f - fD);
                mVar5.f1484n = fD - ((((fN + fO2) - fMin) * fD) / (fMax - fMin));
            }
        }
        this.Q = 0.0f;
        this.R = 0.0f;
        this.V = true;
        invalidate();
    }

    public void I0() {
        this.f1264b1.e(this.f1652h, this.D.k(this.H), this.D.k(this.J));
        v0();
    }

    public void J0(int i7, androidx.constraintlayout.widget.d dVar) {
        q qVar = this.D;
        if (qVar != null) {
            qVar.T(i7, dVar);
        }
        I0();
        if (this.I == i7) {
            dVar.i(this);
        }
    }

    public void K0(int i7, View... viewArr) {
        q qVar = this.D;
        if (qVar != null) {
            qVar.b0(i7, viewArr);
        } else {
            Log.e("MotionLayout", " no motionScene");
        }
    }

    public void Y(j jVar) {
        if (this.f1291x0 == null) {
            this.f1291x0 = new CopyOnWriteArrayList<>();
        }
        this.f1291x0.add(jVar);
    }

    void Z(float f7) {
        if (this.D == null) {
            return;
        }
        float f8 = this.R;
        float f9 = this.Q;
        if (f8 != f9 && this.U) {
            this.R = f9;
        }
        float f10 = this.R;
        if (f10 == f7) {
            return;
        }
        this.f1271f0 = false;
        this.T = f7;
        this.P = r0.o() / 1000.0f;
        setProgress(this.T);
        this.E = null;
        this.F = this.D.r();
        this.U = false;
        this.O = getNanoTime();
        this.V = true;
        this.Q = f10;
        this.R = f10;
        invalidate();
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        v vVar;
        ArrayList<n> arrayList = this.f1290w0;
        if (arrayList != null) {
            Iterator<n> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().C(canvas);
            }
        }
        g0(false);
        q qVar = this.D;
        if (qVar != null && (vVar = qVar.f1540s) != null) {
            vVar.c();
        }
        super.dispatchDraw(canvas);
        if (this.D == null) {
            return;
        }
        if ((this.f1267d0 & 1) == 1 && !isInEditMode()) {
            this.f1292y0++;
            long nanoTime = getNanoTime();
            long j7 = this.f1293z0;
            if (j7 != -1) {
                if (nanoTime - j7 > 200000000) {
                    this.A0 = ((int) ((this.f1292y0 / (r5 * 1.0E-9f)) * 100.0f)) / 100.0f;
                    this.f1292y0 = 0;
                    this.f1293z0 = nanoTime;
                }
            } else {
                this.f1293z0 = nanoTime;
            }
            Paint paint = new Paint();
            paint.setTextSize(42.0f);
            String str = this.A0 + " fps " + androidx.constraintlayout.motion.widget.a.e(this, this.H) + " -> ";
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(androidx.constraintlayout.motion.widget.a.e(this, this.J));
            sb.append(" (progress: ");
            sb.append(((int) (getProgress() * 1000.0f)) / 10.0f);
            sb.append(" ) state=");
            int i7 = this.I;
            sb.append(i7 == -1 ? "undefined" : androidx.constraintlayout.motion.widget.a.e(this, i7));
            String string = sb.toString();
            paint.setColor(-16777216);
            canvas.drawText(string, 11.0f, getHeight() - 29, paint);
            paint.setColor(-7864184);
            canvas.drawText(string, 10.0f, getHeight() - 30, paint);
        }
        if (this.f1267d0 > 1) {
            if (this.f1269e0 == null) {
                this.f1269e0 = new e();
            }
            this.f1269e0.a(canvas, this.N, this.D.o(), this.f1267d0);
        }
        ArrayList<n> arrayList2 = this.f1290w0;
        if (arrayList2 != null) {
            Iterator<n> it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                it2.next().B(canvas);
            }
        }
    }

    void f0(boolean z6) {
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            m mVar = this.N.get(getChildAt(i7));
            if (mVar != null) {
                mVar.f(z6);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:111:0x01ae  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x01eb  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0221  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00e2 A[PHI: r3
  0x00e2: PHI (r3v50 float) = (r3v49 float), (r3v51 float), (r3v51 float) binds: [B:47:0x00ab, B:58:0x00d6, B:60:0x00da] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x016f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void g0(boolean r24) {
        /*
            Method dump skipped, instruction units count: 629
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionLayout.g0(boolean):void");
    }

    public int[] getConstraintSetIds() {
        q qVar = this.D;
        if (qVar == null) {
            return null;
        }
        return qVar.m();
    }

    public int getCurrentState() {
        return this.I;
    }

    public ArrayList<q.b> getDefinedTransitions() {
        q qVar = this.D;
        if (qVar == null) {
            return null;
        }
        return qVar.n();
    }

    public androidx.constraintlayout.motion.widget.b getDesignTool() {
        if (this.f1276i0 == null) {
            this.f1276i0 = new androidx.constraintlayout.motion.widget.b(this);
        }
        return this.f1276i0;
    }

    public int getEndState() {
        return this.J;
    }

    protected long getNanoTime() {
        return System.nanoTime();
    }

    public float getProgress() {
        return this.R;
    }

    public q getScene() {
        return this.D;
    }

    public int getStartState() {
        return this.H;
    }

    public float getTargetPosition() {
        return this.T;
    }

    public Bundle getTransitionState() {
        if (this.O0 == null) {
            this.O0 = new i();
        }
        this.O0.c();
        return this.O0.b();
    }

    public long getTransitionTimeMs() {
        if (this.D != null) {
            this.P = r0.o() / 1000.0f;
        }
        return (long) (this.P * 1000.0f);
    }

    public float getVelocity() {
        return this.G;
    }

    @Override // i0.m
    public void h(View view, View view2, int i7, int i8) {
        this.f1285r0 = getNanoTime();
        this.f1286s0 = 0.0f;
        this.f1283p0 = 0.0f;
        this.f1284q0 = 0.0f;
    }

    @Override // i0.m
    public void i(View view, int i7) {
        q qVar = this.D;
        if (qVar != null) {
            float f7 = this.f1286s0;
            if (f7 == 0.0f) {
                return;
            }
            qVar.P(this.f1283p0 / f7, this.f1284q0 / f7);
        }
    }

    @Override // android.view.View
    public boolean isAttachedToWindow() {
        return super.isAttachedToWindow();
    }

    @Override // i0.m
    public void j(View view, int i7, int i8, int[] iArr, int i9) {
        q.b bVar;
        r rVarB;
        int iQ;
        q qVar = this.D;
        if (qVar == null || (bVar = qVar.f1524c) == null || !bVar.C()) {
            return;
        }
        int i10 = -1;
        if (!bVar.C() || (rVarB = bVar.B()) == null || (iQ = rVarB.q()) == -1 || view.getId() == iQ) {
            if (qVar.v()) {
                r rVarB2 = bVar.B();
                if (rVarB2 != null && (rVarB2.e() & 4) != 0) {
                    i10 = i8;
                }
                float f7 = this.Q;
                if ((f7 == 1.0f || f7 == 0.0f) && view.canScrollVertically(i10)) {
                    return;
                }
            }
            if (bVar.B() != null && (bVar.B().e() & 1) != 0) {
                float fW = qVar.w(i7, i8);
                float f8 = this.R;
                if ((f8 <= 0.0f && fW < 0.0f) || (f8 >= 1.0f && fW > 0.0f)) {
                    view.setNestedScrollingEnabled(false);
                    view.post(new a(view));
                    return;
                }
            }
            float f9 = this.Q;
            long nanoTime = getNanoTime();
            float f10 = i7;
            this.f1283p0 = f10;
            float f11 = i8;
            this.f1284q0 = f11;
            this.f1286s0 = (float) ((nanoTime - this.f1285r0) * 1.0E-9d);
            this.f1285r0 = nanoTime;
            qVar.O(f10, f11);
            if (f9 != this.Q) {
                iArr[0] = i7;
                iArr[1] = i8;
            }
            g0(false);
            if (iArr[0] == 0 && iArr[1] == 0) {
                return;
            }
            this.f1282o0 = true;
        }
    }

    protected void j0() {
        int iIntValue;
        CopyOnWriteArrayList<j> copyOnWriteArrayList;
        if ((this.f1261a0 != null || ((copyOnWriteArrayList = this.f1291x0) != null && !copyOnWriteArrayList.isEmpty())) && this.B0 == -1) {
            this.B0 = this.I;
            if (this.f1274g1.isEmpty()) {
                iIntValue = -1;
            } else {
                ArrayList<Integer> arrayList = this.f1274g1;
                iIntValue = arrayList.get(arrayList.size() - 1).intValue();
            }
            int i7 = this.I;
            if (iIntValue != i7 && i7 != -1) {
                this.f1274g1.add(Integer.valueOf(i7));
            }
        }
        u0();
        Runnable runnable = this.P0;
        if (runnable != null) {
            runnable.run();
        }
        int[] iArr = this.Q0;
        if (iArr == null || this.R0 <= 0) {
            return;
        }
        F0(iArr[0]);
        int[] iArr2 = this.Q0;
        System.arraycopy(iArr2, 1, iArr2, 0, iArr2.length - 1);
        this.R0--;
    }

    public void k0(int i7, boolean z6, float f7) {
        j jVar = this.f1261a0;
        if (jVar != null) {
            jVar.b(this, i7, z6, f7);
        }
        CopyOnWriteArrayList<j> copyOnWriteArrayList = this.f1291x0;
        if (copyOnWriteArrayList != null) {
            Iterator<j> it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                it.next().b(this, i7, z6, f7);
            }
        }
    }

    void l0(int i7, float f7, float f8, float f9, float[] fArr) {
        String resourceName;
        HashMap<View, m> map = this.N;
        View viewL = l(i7);
        m mVar = map.get(viewL);
        if (mVar != null) {
            mVar.l(f7, f8, f9, fArr);
            float y6 = viewL.getY();
            this.f1263b0 = f7;
            this.f1265c0 = y6;
            return;
        }
        if (viewL == null) {
            resourceName = "" + i7;
        } else {
            resourceName = viewL.getContext().getResources().getResourceName(i7);
        }
        Log.w("MotionLayout", "WARNING could not find view id " + resourceName);
    }

    @Override // i0.n
    public void m(View view, int i7, int i8, int i9, int i10, int i11, int[] iArr) {
        if (this.f1282o0 || i7 != 0 || i8 != 0) {
            iArr[0] = iArr[0] + i9;
            iArr[1] = iArr[1] + i10;
        }
        this.f1282o0 = false;
    }

    public androidx.constraintlayout.widget.d m0(int i7) {
        q qVar = this.D;
        if (qVar == null) {
            return null;
        }
        return qVar.k(i7);
    }

    @Override // i0.m
    public void n(View view, int i7, int i8, int i9, int i10, int i11) {
    }

    m n0(int i7) {
        return this.N.get(findViewById(i7));
    }

    @Override // i0.m
    public boolean o(View view, View view2, int i7, int i8) {
        q.b bVar;
        q qVar = this.D;
        return (qVar == null || (bVar = qVar.f1524c) == null || bVar.B() == null || (this.D.f1524c.B().e() & 2) != 0) ? false : true;
    }

    public q.b o0(int i7) {
        return this.D.F(i7);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        q.b bVar;
        int i7;
        super.onAttachedToWindow();
        Display display = getDisplay();
        if (display != null) {
            this.X0 = display.getRotation();
        }
        q qVar = this.D;
        if (qVar != null && (i7 = this.I) != -1) {
            androidx.constraintlayout.widget.d dVarK = qVar.k(i7);
            this.D.S(this);
            ArrayList<n> arrayList = this.f1290w0;
            if (arrayList != null) {
                Iterator<n> it = arrayList.iterator();
                while (it.hasNext()) {
                    it.next().A(this);
                }
            }
            if (dVarK != null) {
                dVarK.i(this);
            }
            this.H = this.I;
        }
        t0();
        i iVar = this.O0;
        if (iVar != null) {
            if (this.Z0) {
                post(new b());
                return;
            } else {
                iVar.a();
                return;
            }
        }
        q qVar2 = this.D;
        if (qVar2 == null || (bVar = qVar2.f1524c) == null || bVar.x() != 4) {
            return;
        }
        C0();
        setState(k.SETUP);
        setState(k.MOVING);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        r rVarB;
        int iQ;
        RectF rectFP;
        q qVar = this.D;
        if (qVar != null && this.M) {
            v vVar = qVar.f1540s;
            if (vVar != null) {
                vVar.g(motionEvent);
            }
            q.b bVar = this.D.f1524c;
            if (bVar != null && bVar.C() && (rVarB = bVar.B()) != null && ((motionEvent.getAction() != 0 || (rectFP = rVarB.p(this, new RectF())) == null || rectFP.contains(motionEvent.getX(), motionEvent.getY())) && (iQ = rVarB.q()) != -1)) {
                View view = this.f1270e1;
                if (view == null || view.getId() != iQ) {
                    this.f1270e1 = findViewById(iQ);
                }
                if (this.f1270e1 != null) {
                    this.f1268d1.set(r0.getLeft(), this.f1270e1.getTop(), this.f1270e1.getRight(), this.f1270e1.getBottom());
                    if (this.f1268d1.contains(motionEvent.getX(), motionEvent.getY()) && !p0(this.f1270e1.getLeft(), this.f1270e1.getTop(), this.f1270e1, motionEvent)) {
                        return onTouchEvent(motionEvent);
                    }
                }
            }
        }
        return false;
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        this.N0 = true;
        try {
            if (this.D == null) {
                super.onLayout(z6, i7, i8, i9, i10);
                return;
            }
            int i11 = i9 - i7;
            int i12 = i10 - i8;
            if (this.f1280m0 != i11 || this.f1281n0 != i12) {
                v0();
                g0(true);
            }
            this.f1280m0 = i11;
            this.f1281n0 = i12;
            this.f1278k0 = i11;
            this.f1279l0 = i12;
        } finally {
            this.N0 = false;
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View
    protected void onMeasure(int i7, int i8) {
        if (this.D == null) {
            super.onMeasure(i7, i8);
            return;
        }
        boolean z6 = false;
        boolean z7 = (this.K == i7 && this.L == i8) ? false : true;
        if (this.f1266c1) {
            this.f1266c1 = false;
            t0();
            u0();
            z7 = true;
        }
        if (this.f1657m) {
            z7 = true;
        }
        this.K = i7;
        this.L = i8;
        int iE = this.D.E();
        int iP = this.D.p();
        if ((z7 || this.f1264b1.f(iE, iP)) && this.H != -1) {
            super.onMeasure(i7, i8);
            this.f1264b1.e(this.f1652h, this.D.k(iE), this.D.k(iP));
            this.f1264b1.h();
            this.f1264b1.i(iE, iP);
        } else {
            if (z7) {
                super.onMeasure(i7, i8);
            }
            z6 = true;
        }
        if (this.E0 || z6) {
            int paddingTop = getPaddingTop() + getPaddingBottom();
            int iY = this.f1652h.Y() + getPaddingLeft() + getPaddingRight();
            int iZ = this.f1652h.z() + paddingTop;
            int i9 = this.J0;
            if (i9 == Integer.MIN_VALUE || i9 == 0) {
                iY = (int) (this.F0 + (this.L0 * (this.H0 - r8)));
                requestLayout();
            }
            int i10 = this.K0;
            if (i10 == Integer.MIN_VALUE || i10 == 0) {
                iZ = (int) (this.G0 + (this.L0 * (this.I0 - r8)));
                requestLayout();
            }
            setMeasuredDimension(iY, iZ);
        }
        h0();
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedFling(View view, float f7, float f8, boolean z6) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedPreFling(View view, float f7, float f8) {
        return false;
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i7) {
        q qVar = this.D;
        if (qVar != null) {
            qVar.V(r());
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        q qVar = this.D;
        if (qVar == null || !this.M || !qVar.a0()) {
            return super.onTouchEvent(motionEvent);
        }
        q.b bVar = this.D.f1524c;
        if (bVar != null && !bVar.C()) {
            return super.onTouchEvent(motionEvent);
        }
        this.D.Q(motionEvent, getCurrentState(), this);
        if (this.D.f1524c.D(4)) {
            return this.D.f1524c.B().r();
        }
        return true;
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public void onViewAdded(View view) {
        super.onViewAdded(view);
        if (view instanceof n) {
            n nVar = (n) view;
            if (this.f1291x0 == null) {
                this.f1291x0 = new CopyOnWriteArrayList<>();
            }
            this.f1291x0.add(nVar);
            if (nVar.z()) {
                if (this.f1288u0 == null) {
                    this.f1288u0 = new ArrayList<>();
                }
                this.f1288u0.add(nVar);
            }
            if (nVar.y()) {
                if (this.f1289v0 == null) {
                    this.f1289v0 = new ArrayList<>();
                }
                this.f1289v0.add(nVar);
            }
            if (nVar.x()) {
                if (this.f1290w0 == null) {
                    this.f1290w0 = new ArrayList<>();
                }
                this.f1290w0.add(nVar);
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        ArrayList<n> arrayList = this.f1288u0;
        if (arrayList != null) {
            arrayList.remove(view);
        }
        ArrayList<n> arrayList2 = this.f1289v0;
        if (arrayList2 != null) {
            arrayList2.remove(view);
        }
    }

    public boolean r0() {
        return this.M;
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View, android.view.ViewParent
    public void requestLayout() {
        q qVar;
        q.b bVar;
        if (!this.E0 && this.I == -1 && (qVar = this.D) != null && (bVar = qVar.f1524c) != null) {
            int iZ = bVar.z();
            if (iZ == 0) {
                return;
            }
            if (iZ == 2) {
                int childCount = getChildCount();
                for (int i7 = 0; i7 < childCount; i7++) {
                    this.N.get(getChildAt(i7)).w();
                }
                return;
            }
        }
        super.requestLayout();
    }

    protected g s0() {
        return h.f();
    }

    public void setDebugMode(int i7) {
        this.f1267d0 = i7;
        invalidate();
    }

    public void setDelayedApplicationOfInitialState(boolean z6) {
        this.Z0 = z6;
    }

    public void setInteractionEnabled(boolean z6) {
        this.M = z6;
    }

    public void setInterpolatedProgress(float f7) {
        if (this.D != null) {
            setState(k.MOVING);
            Interpolator interpolatorR = this.D.r();
            if (interpolatorR != null) {
                setProgress(interpolatorR.getInterpolation(f7));
                return;
            }
        }
        setProgress(f7);
    }

    public void setOnHide(float f7) {
        ArrayList<n> arrayList = this.f1289v0;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i7 = 0; i7 < size; i7++) {
                this.f1289v0.get(i7).setProgress(f7);
            }
        }
    }

    public void setOnShow(float f7) {
        ArrayList<n> arrayList = this.f1288u0;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i7 = 0; i7 < size; i7++) {
                this.f1288u0.get(i7).setProgress(f7);
            }
        }
    }

    public void setProgress(float f7) {
        if (f7 < 0.0f || f7 > 1.0f) {
            Log.w("MotionLayout", "Warning! Progress is defined for values between 0.0 and 1.0 inclusive");
        }
        if (!isAttachedToWindow()) {
            if (this.O0 == null) {
                this.O0 = new i();
            }
            this.O0.e(f7);
            return;
        }
        if (f7 <= 0.0f) {
            if (this.R == 1.0f && this.I == this.J) {
                setState(k.MOVING);
            }
            this.I = this.H;
            if (this.R == 0.0f) {
                setState(k.FINISHED);
            }
        } else if (f7 >= 1.0f) {
            if (this.R == 0.0f && this.I == this.H) {
                setState(k.MOVING);
            }
            this.I = this.J;
            if (this.R == 1.0f) {
                setState(k.FINISHED);
            }
        } else {
            this.I = -1;
            setState(k.MOVING);
        }
        if (this.D == null) {
            return;
        }
        this.U = true;
        this.T = f7;
        this.Q = f7;
        this.S = -1L;
        this.O = -1L;
        this.E = null;
        this.V = true;
        invalidate();
    }

    public void setScene(q qVar) {
        this.D = qVar;
        qVar.V(r());
        v0();
    }

    void setStartState(int i7) {
        if (isAttachedToWindow()) {
            this.I = i7;
            return;
        }
        if (this.O0 == null) {
            this.O0 = new i();
        }
        this.O0.f(i7);
        this.O0.d(i7);
    }

    void setState(k kVar) {
        k kVar2 = k.FINISHED;
        if (kVar == kVar2 && this.I == -1) {
            return;
        }
        k kVar3 = this.f1262a1;
        this.f1262a1 = kVar;
        k kVar4 = k.MOVING;
        if (kVar3 == kVar4 && kVar == kVar4) {
            i0();
        }
        int i7 = c.f1297a[kVar3.ordinal()];
        if (i7 != 1 && i7 != 2) {
            if (i7 == 3 && kVar == kVar2) {
                j0();
                return;
            }
            return;
        }
        if (kVar == kVar4) {
            i0();
        }
        if (kVar == kVar2) {
            j0();
        }
    }

    public void setTransition(int i7) {
        if (this.D != null) {
            q.b bVarO0 = o0(i7);
            this.H = bVarO0.A();
            this.J = bVarO0.y();
            if (!isAttachedToWindow()) {
                if (this.O0 == null) {
                    this.O0 = new i();
                }
                this.O0.f(this.H);
                this.O0.d(this.J);
                return;
            }
            float f7 = Float.NaN;
            int i8 = this.I;
            if (i8 == this.H) {
                f7 = 0.0f;
            } else if (i8 == this.J) {
                f7 = 1.0f;
            }
            this.D.X(bVarO0);
            this.f1264b1.e(this.f1652h, this.D.k(this.H), this.D.k(this.J));
            v0();
            if (this.R != f7) {
                if (f7 == 0.0f) {
                    f0(true);
                    this.D.k(this.H).i(this);
                } else if (f7 == 1.0f) {
                    f0(false);
                    this.D.k(this.J).i(this);
                }
            }
            this.R = Float.isNaN(f7) ? 0.0f : f7;
            if (!Float.isNaN(f7)) {
                setProgress(f7);
                return;
            }
            Log.v("MotionLayout", androidx.constraintlayout.motion.widget.a.b() + " transitionToStart ");
            E0();
        }
    }

    public void setTransitionDuration(int i7) {
        q qVar = this.D;
        if (qVar == null) {
            Log.e("MotionLayout", "MotionScene not defined");
        } else {
            qVar.U(i7);
        }
    }

    public void setTransitionListener(j jVar) {
        this.f1261a0 = jVar;
    }

    public void setTransitionState(Bundle bundle) {
        if (this.O0 == null) {
            this.O0 = new i();
        }
        this.O0.g(bundle);
        if (isAttachedToWindow()) {
            this.O0.a();
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout
    protected void t(int i7) {
        this.f1660p = null;
    }

    void t0() {
        q qVar = this.D;
        if (qVar == null) {
            return;
        }
        if (qVar.g(this, this.I)) {
            requestLayout();
            return;
        }
        int i7 = this.I;
        if (i7 != -1) {
            this.D.f(this, i7);
        }
        if (this.D.a0()) {
            this.D.Y();
        }
    }

    @Override // android.view.View
    public String toString() {
        Context context = getContext();
        return androidx.constraintlayout.motion.widget.a.c(context, this.H) + "->" + androidx.constraintlayout.motion.widget.a.c(context, this.J) + " (pos:" + this.R + " Dpos/Dt:" + this.G;
    }

    public void v0() {
        this.f1264b1.h();
        invalidate();
    }

    public void w0(float f7, float f8) {
        if (!isAttachedToWindow()) {
            if (this.O0 == null) {
                this.O0 = new i();
            }
            this.O0.e(f7);
            this.O0.h(f8);
            return;
        }
        setProgress(f7);
        setState(k.MOVING);
        this.G = f8;
        if (f8 != 0.0f) {
            Z(f8 > 0.0f ? 1.0f : 0.0f);
        } else {
            if (f7 == 0.0f || f7 == 1.0f) {
                return;
            }
            Z(f7 > 0.5f ? 1.0f : 0.0f);
        }
    }

    public void x0(int i7, int i8, int i9) {
        setState(k.SETUP);
        this.I = i7;
        this.H = -1;
        this.J = -1;
        androidx.constraintlayout.widget.c cVar = this.f1660p;
        if (cVar != null) {
            cVar.d(i7, i8, i9);
            return;
        }
        q qVar = this.D;
        if (qVar != null) {
            qVar.k(i7).i(this);
        }
    }

    public void y0(int i7, int i8) {
        if (!isAttachedToWindow()) {
            if (this.O0 == null) {
                this.O0 = new i();
            }
            this.O0.f(i7);
            this.O0.d(i8);
            return;
        }
        q qVar = this.D;
        if (qVar != null) {
            this.H = i7;
            this.J = i8;
            qVar.W(i7, i8);
            this.f1264b1.e(this.f1652h, this.D.k(i7), this.D.k(i8));
            v0();
            this.R = 0.0f;
            E0();
        }
    }

    protected void setTransition(q.b bVar) {
        this.D.X(bVar);
        setState(k.SETUP);
        if (this.I == this.D.p()) {
            this.R = 1.0f;
            this.Q = 1.0f;
            this.T = 1.0f;
        } else {
            this.R = 0.0f;
            this.Q = 0.0f;
            this.T = 0.0f;
        }
        this.S = bVar.D(1) ? -1L : getNanoTime();
        int iE = this.D.E();
        int iP = this.D.p();
        if (iE == this.H && iP == this.J) {
            return;
        }
        this.H = iE;
        this.J = iP;
        this.D.W(iE, iP);
        this.f1264b1.e(this.f1652h, this.D.k(this.H), this.D.k(this.J));
        this.f1264b1.i(this.H, this.J);
        this.f1264b1.h();
        v0();
    }
}
