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
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import androidx.constraintlayout.helper.widget.MotionEffect;
import androidx.constraintlayout.motion.widget.a;
import androidx.constraintlayout.motion.widget.c;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;
import androidx.constraintlayout.widget.R$styleable;
import androidx.constraintlayout.widget.a;
import androidx.core.widget.NestedScrollView;
import defpackage.C0175b;
import defpackage.a41;
import defpackage.ae;
import defpackage.ae3;
import defpackage.aj1;
import defpackage.b70;
import defpackage.be3;
import defpackage.bj1;
import defpackage.dj1;
import defpackage.ej1;
import defpackage.et3;
import defpackage.fu2;
import defpackage.gq2;
import defpackage.hf0;
import defpackage.iu0;
import defpackage.k41;
import defpackage.ku0;
import defpackage.l12;
import defpackage.lo3;
import defpackage.os2;
import defpackage.px;
import defpackage.qx;
import defpackage.r80;
import defpackage.rt0;
import defpackage.rx;
import defpackage.tl1;
import defpackage.wn0;
import defpackage.yc;
import defpackage.zi1;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class MotionLayout extends ConstraintLayout implements tl1 {
    public static boolean J0;
    public int A;
    public final Rect A0;
    public int B;
    public boolean B0;
    public int C;
    public i C0;
    public boolean D;
    public final e D0;
    public final HashMap<View, bj1> E;
    public boolean E0;
    public long F;
    public final RectF F0;
    public float G;
    public View G0;
    public float H;
    public Matrix H0;
    public float I;
    public final ArrayList<Integer> I0;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public long f6J;
    public float K;
    public boolean L;
    public boolean M;
    public h N;
    public int O;
    public d P;
    public boolean Q;
    public final fu2 R;
    public final c S;
    public r80 T;
    public int U;
    public int V;
    public boolean W;
    public float a0;
    public float b0;
    public long c0;
    public float d0;
    public boolean e0;
    public ArrayList<MotionHelper> f0;
    public ArrayList<MotionHelper> g0;
    public ArrayList<MotionHelper> h0;
    public CopyOnWriteArrayList<h> i0;
    public int j0;
    public long k0;
    public float l0;
    public int m0;
    public float n0;
    public boolean o0;
    public int p0;
    public int q0;
    public int r0;
    public int s0;
    public int t0;
    public androidx.constraintlayout.motion.widget.a u;
    public int u0;
    public dj1 v;
    public float v0;
    public Interpolator w;
    public final a41 w0;
    public float x;
    public boolean x0;
    public int y;
    public g y0;
    public int z;
    public Runnable z0;

    public class a implements Runnable {
        public final /* synthetic */ View e;

        public a(View view) {
            this.e = view;
        }

        @Override // java.lang.Runnable
        public final void run() {
            this.e.setNestedScrollingEnabled(true);
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            MotionLayout.this.y0.a();
        }
    }

    public class c extends dj1 {
        public float a = 0.0f;
        public float b = 0.0f;
        public float c;

        public c() {
        }

        @Override // defpackage.dj1
        public final float a() {
            return MotionLayout.this.x;
        }

        @Override // android.animation.TimeInterpolator
        public final float getInterpolation(float f) {
            float f2 = this.a;
            MotionLayout motionLayout = MotionLayout.this;
            if (f2 > 0.0f) {
                float f3 = this.c;
                if (f2 / f3 < f) {
                    f = f2 / f3;
                }
                motionLayout.x = f2 - (f3 * f);
                return ((f2 * f) - (((f3 * f) * f) / 2.0f)) + this.b;
            }
            float f4 = this.c;
            if ((-f2) / f4 < f) {
                f = (-f2) / f4;
            }
            motionLayout.x = (f4 * f) + f2;
            return (((f4 * f) * f) / 2.0f) + (f2 * f) + this.b;
        }
    }

    public class d {
        public float[] a;
        public final int[] b;
        public final float[] c;
        public Path d;
        public final Paint e;
        public final Paint f;
        public final Paint g;
        public final Paint h;
        public final Paint i;
        public final float[] j;
        public int k;
        public final Rect l = new Rect();
        public final int m = 1;

        public d() {
            Paint paint = new Paint();
            this.e = paint;
            paint.setAntiAlias(true);
            paint.setColor(-21965);
            paint.setStrokeWidth(2.0f);
            paint.setStyle(Paint.Style.STROKE);
            Paint paint2 = new Paint();
            this.f = paint2;
            paint2.setAntiAlias(true);
            paint2.setColor(-2067046);
            paint2.setStrokeWidth(2.0f);
            paint2.setStyle(Paint.Style.STROKE);
            Paint paint3 = new Paint();
            this.g = paint3;
            paint3.setAntiAlias(true);
            paint3.setColor(-13391360);
            paint3.setStrokeWidth(2.0f);
            paint3.setStyle(Paint.Style.STROKE);
            Paint paint4 = new Paint();
            this.h = paint4;
            paint4.setAntiAlias(true);
            paint4.setColor(-13391360);
            paint4.setTextSize(MotionLayout.this.getContext().getResources().getDisplayMetrics().density * 12.0f);
            this.j = new float[8];
            Paint paint5 = new Paint();
            this.i = paint5;
            paint5.setAntiAlias(true);
            paint3.setPathEffect(new DashPathEffect(new float[]{4.0f, 8.0f}, 0.0f));
            this.c = new float[100];
            this.b = new int[50];
        }

        public final void a(Canvas canvas, int i, int i2, bj1 bj1Var) {
            int width;
            int height;
            Paint paint;
            float f;
            float f2;
            int i3;
            Paint paint2 = this.g;
            int[] iArr = this.b;
            int i4 = 4;
            if (i == 4) {
                boolean z = false;
                boolean z2 = false;
                for (int i5 = 0; i5 < this.k; i5++) {
                    int i6 = iArr[i5];
                    if (i6 == 1) {
                        z = true;
                    }
                    if (i6 == 0) {
                        z2 = true;
                    }
                }
                if (z) {
                    float[] fArr = this.a;
                    canvas.drawLine(fArr[0], fArr[1], fArr[fArr.length - 2], fArr[fArr.length - 1], paint2);
                }
                if (z2) {
                    b(canvas);
                }
            }
            if (i == 2) {
                float[] fArr2 = this.a;
                canvas.drawLine(fArr2[0], fArr2[1], fArr2[fArr2.length - 2], fArr2[fArr2.length - 1], paint2);
            }
            if (i == 3) {
                b(canvas);
            }
            canvas.drawLines(this.a, this.e);
            View view = bj1Var.b;
            if (view != null) {
                width = view.getWidth();
                height = bj1Var.b.getHeight();
            } else {
                width = 0;
                height = 0;
            }
            int i7 = 1;
            while (i7 < i2 - 1) {
                if (i == i4 && iArr[i7 - 1] == 0) {
                    i3 = i7;
                } else {
                    int i8 = i7 * 2;
                    float[] fArr3 = this.c;
                    float f3 = fArr3[i8];
                    float f4 = fArr3[i8 + 1];
                    this.d.reset();
                    this.d.moveTo(f3, f4 + 10.0f);
                    this.d.lineTo(f3 + 10.0f, f4);
                    this.d.lineTo(f3, f4 - 10.0f);
                    this.d.lineTo(f3 - 10.0f, f4);
                    this.d.close();
                    int i9 = i7 - 1;
                    bj1Var.u.get(i9);
                    Paint paint3 = this.i;
                    if (i == i4) {
                        int i10 = iArr[i9];
                        if (i10 == 1) {
                            d(canvas, f3 - 0.0f, f4 - 0.0f);
                        } else if (i10 == 0) {
                            c(canvas, f3 - 0.0f, f4 - 0.0f);
                        } else {
                            if (i10 == 2) {
                                paint = paint3;
                                f = f4;
                                f2 = f3;
                                i3 = i7;
                                e(canvas, f3 - 0.0f, f4 - 0.0f, width, height);
                            }
                            canvas.drawPath(this.d, paint);
                        }
                        paint = paint3;
                        f = f4;
                        f2 = f3;
                        i3 = i7;
                        canvas.drawPath(this.d, paint);
                    } else {
                        paint = paint3;
                        f = f4;
                        f2 = f3;
                        i3 = i7;
                    }
                    if (i == 2) {
                        d(canvas, f2 - 0.0f, f - 0.0f);
                    }
                    if (i == 3) {
                        c(canvas, f2 - 0.0f, f - 0.0f);
                    }
                    if (i == 6) {
                        e(canvas, f2 - 0.0f, f - 0.0f, width, height);
                    }
                    canvas.drawPath(this.d, paint);
                }
                i7 = i3 + 1;
                i4 = 4;
            }
            float[] fArr4 = this.a;
            if (fArr4.length > 1) {
                float f5 = fArr4[0];
                float f6 = fArr4[1];
                Paint paint4 = this.f;
                canvas.drawCircle(f5, f6, 8.0f, paint4);
                float[] fArr5 = this.a;
                canvas.drawCircle(fArr5[fArr5.length - 2], fArr5[fArr5.length - 1], 8.0f, paint4);
            }
        }

        public final void b(Canvas canvas) {
            float[] fArr = this.a;
            float f = fArr[0];
            float f2 = fArr[1];
            float f3 = fArr[fArr.length - 2];
            float f4 = fArr[fArr.length - 1];
            float fMin = Math.min(f, f3);
            float fMax = Math.max(f2, f4);
            float fMax2 = Math.max(f, f3);
            float fMax3 = Math.max(f2, f4);
            Paint paint = this.g;
            canvas.drawLine(fMin, fMax, fMax2, fMax3, paint);
            canvas.drawLine(Math.min(f, f3), Math.min(f2, f4), Math.min(f, f3), Math.max(f2, f4), paint);
        }

        public final void c(Canvas canvas, float f, float f2) {
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
            Paint paint = this.h;
            f(paint, str);
            Rect rect = this.l;
            canvas.drawText(str, ((fMin2 / 2.0f) - (rect.width() / 2)) + fMin, f2 - 20.0f, paint);
            float fMin3 = Math.min(f3, f5);
            Paint paint2 = this.g;
            canvas.drawLine(f, f2, fMin3, f2, paint2);
            String str2 = "" + (((int) (((double) ((fMax2 * 100.0f) / Math.abs(f6 - f4))) + 0.5d)) / 100.0f);
            f(paint, str2);
            canvas.drawText(str2, f + 5.0f, fMax - ((fMax2 / 2.0f) - (rect.height() / 2)), paint);
            canvas.drawLine(f, f2, f, Math.max(f4, f6), paint2);
        }

        public final void d(Canvas canvas, float f, float f2) {
            float[] fArr = this.a;
            float f3 = fArr[0];
            float f4 = fArr[1];
            float f5 = fArr[fArr.length - 2];
            float f6 = fArr[fArr.length - 1];
            float fHypot = (float) Math.hypot(f3 - f5, f4 - f6);
            float f7 = f5 - f3;
            float f8 = f6 - f4;
            float f9 = (((f2 - f4) * f8) + ((f - f3) * f7)) / (fHypot * fHypot);
            float f10 = f3 + (f7 * f9);
            float f11 = f4 + (f9 * f8);
            Path path = new Path();
            path.moveTo(f, f2);
            path.lineTo(f10, f11);
            float fHypot2 = (float) Math.hypot(f10 - f, f11 - f2);
            String str = "" + (((int) ((fHypot2 * 100.0f) / fHypot)) / 100.0f);
            Paint paint = this.h;
            f(paint, str);
            canvas.drawTextOnPath(str, path, (fHypot2 / 2.0f) - (this.l.width() / 2), -20.0f, paint);
            canvas.drawLine(f, f2, f10, f11, this.g);
        }

        public final void e(Canvas canvas, float f, float f2, int i, int i2) {
            StringBuilder sb = new StringBuilder("");
            MotionLayout motionLayout = MotionLayout.this;
            sb.append(((int) (((double) (((f - (i / 2)) * 100.0f) / (motionLayout.getWidth() - i))) + 0.5d)) / 100.0f);
            String string = sb.toString();
            Paint paint = this.h;
            f(paint, string);
            Rect rect = this.l;
            canvas.drawText(string, ((f / 2.0f) - (rect.width() / 2)) + 0.0f, f2 - 20.0f, paint);
            float fMin = Math.min(0.0f, 1.0f);
            Paint paint2 = this.g;
            canvas.drawLine(f, f2, fMin, f2, paint2);
            String str = "" + (((int) (((double) (((f2 - (i2 / 2)) * 100.0f) / (motionLayout.getHeight() - i2))) + 0.5d)) / 100.0f);
            f(paint, str);
            canvas.drawText(str, f + 5.0f, 0.0f - ((f2 / 2.0f) - (rect.height() / 2)), paint);
            canvas.drawLine(f, f2, f, Math.max(0.0f, 1.0f), paint2);
        }

        public final void f(Paint paint, String str) {
            paint.getTextBounds(str, 0, str.length(), this.l);
        }
    }

    public class e {
        public rx a = new rx();
        public rx b = new rx();
        public androidx.constraintlayout.widget.a c = null;
        public androidx.constraintlayout.widget.a d = null;
        public int e;
        public int f;

        public e() {
        }

        public static void c(rx rxVar, rx rxVar2) {
            ArrayList<qx> arrayList = rxVar.w0;
            HashMap<qx, qx> map = new HashMap<>();
            map.put(rxVar, rxVar2);
            rxVar2.w0.clear();
            rxVar2.j(rxVar, map);
            for (qx qxVar : arrayList) {
                qx ycVar = qxVar instanceof yc ? new yc() : qxVar instanceof rt0 ? new rt0() : qxVar instanceof wn0 ? new wn0() : qxVar instanceof l12 ? new l12() : qxVar instanceof iu0 ? new ku0() : new qx();
                rxVar2.w0.add(ycVar);
                qx qxVar2 = ycVar.W;
                if (qxVar2 != null) {
                    ((et3) qxVar2).w0.remove(ycVar);
                    ycVar.G();
                }
                ycVar.W = rxVar2;
                map.put(qxVar, ycVar);
            }
            for (qx qxVar3 : arrayList) {
                map.get(qxVar3).j(qxVar3, map);
            }
        }

        public static qx d(rx rxVar, View view) {
            if (rxVar.i0 == view) {
                return rxVar;
            }
            ArrayList<qx> arrayList = rxVar.w0;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                qx qxVar = arrayList.get(i);
                if (qxVar.i0 == view) {
                    return qxVar;
                }
            }
            return null;
        }

        public final void a() {
            int i;
            HashMap<View, bj1> map;
            SparseArray sparseArray;
            int[] iArr;
            int i2;
            Rect rect;
            Rect rect2;
            MotionLayout motionLayout = MotionLayout.this;
            int childCount = motionLayout.getChildCount();
            HashMap<View, bj1> map2 = motionLayout.E;
            map2.clear();
            SparseArray sparseArray2 = new SparseArray();
            int[] iArr2 = new int[childCount];
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = motionLayout.getChildAt(i3);
                bj1 bj1Var = new bj1(childAt);
                int id = childAt.getId();
                iArr2[i3] = id;
                sparseArray2.put(id, bj1Var);
                map2.put(childAt, bj1Var);
            }
            int i4 = 0;
            while (i4 < childCount) {
                View childAt2 = motionLayout.getChildAt(i4);
                bj1 bj1Var2 = map2.get(childAt2);
                if (bj1Var2 == null) {
                    i = childCount;
                    map = map2;
                    sparseArray = sparseArray2;
                    iArr = iArr2;
                    i2 = i4;
                } else {
                    androidx.constraintlayout.widget.a aVar = this.c;
                    Rect rect3 = bj1Var2.a;
                    if (aVar != null) {
                        qx qxVarD = d(this.a, childAt2);
                        if (qxVarD != null) {
                            Rect rectS = MotionLayout.s(motionLayout, qxVarD);
                            androidx.constraintlayout.widget.a aVar2 = this.c;
                            map = map2;
                            int width = motionLayout.getWidth();
                            sparseArray = sparseArray2;
                            int height = motionLayout.getHeight();
                            iArr = iArr2;
                            int i5 = aVar2.d;
                            if (i5 != 0) {
                                bj1.g(i5, width, height, rectS, rect3);
                            }
                            ej1 ej1Var = bj1Var2.f;
                            ej1Var.g = 0.0f;
                            ej1Var.h = 0.0f;
                            bj1Var2.f(ej1Var);
                            i = childCount;
                            i2 = i4;
                            rect = rect3;
                            ej1Var.g(rectS.left, rectS.top, rectS.width(), rectS.height());
                            a.C0004a c0004aH = aVar2.h(bj1Var2.c);
                            ej1Var.a(c0004aH);
                            a.c cVar = c0004aH.d;
                            bj1Var2.l = cVar.g;
                            bj1Var2.h.g(rectS, aVar2, i5, bj1Var2.c);
                            bj1Var2.C = c0004aH.f.i;
                            bj1Var2.E = cVar.j;
                            bj1Var2.F = cVar.i;
                            Context context = bj1Var2.b.getContext();
                            int i6 = cVar.l;
                            bj1Var2.G = i6 != -2 ? i6 != -1 ? i6 != 0 ? i6 != 1 ? i6 != 2 ? i6 != 4 ? i6 != 5 ? null : new OvershootInterpolator() : new BounceInterpolator() : new DecelerateInterpolator() : new AccelerateInterpolator() : new AccelerateDecelerateInterpolator() : new aj1(hf0.c(cVar.k)) : AnimationUtils.loadInterpolator(context, cVar.m);
                        } else {
                            i = childCount;
                            map = map2;
                            sparseArray = sparseArray2;
                            iArr = iArr2;
                            i2 = i4;
                            rect = rect3;
                            if (motionLayout.O != 0) {
                                Log.e("MotionLayout", b70.b() + "no widget for  " + b70.d(childAt2) + " (" + childAt2.getClass().getName() + ")");
                            }
                        }
                    } else {
                        i = childCount;
                        map = map2;
                        sparseArray = sparseArray2;
                        iArr = iArr2;
                        i2 = i4;
                        rect = rect3;
                    }
                    if (this.d != null) {
                        qx qxVarD2 = d(this.b, childAt2);
                        if (qxVarD2 != null) {
                            Rect rectS2 = MotionLayout.s(motionLayout, qxVarD2);
                            androidx.constraintlayout.widget.a aVar3 = this.d;
                            int width2 = motionLayout.getWidth();
                            int height2 = motionLayout.getHeight();
                            int i7 = aVar3.d;
                            if (i7 != 0) {
                                Rect rect4 = rect;
                                bj1.g(i7, width2, height2, rectS2, rect4);
                                rect2 = rect4;
                            } else {
                                rect2 = rectS2;
                            }
                            ej1 ej1Var2 = bj1Var2.g;
                            ej1Var2.g = 1.0f;
                            ej1Var2.h = 1.0f;
                            bj1Var2.f(ej1Var2);
                            ej1Var2.g(rect2.left, rect2.top, rect2.width(), rect2.height());
                            ej1Var2.a(aVar3.h(bj1Var2.c));
                            bj1Var2.i.g(rect2, aVar3, i7, bj1Var2.c);
                        } else if (motionLayout.O != 0) {
                            Log.e("MotionLayout", b70.b() + "no widget for  " + b70.d(childAt2) + " (" + childAt2.getClass().getName() + ")");
                        }
                    }
                }
                i4 = i2 + 1;
                map2 = map;
                sparseArray2 = sparseArray;
                iArr2 = iArr;
                childCount = i;
            }
            SparseArray sparseArray3 = sparseArray2;
            int[] iArr3 = iArr2;
            int i8 = childCount;
            int i9 = 0;
            while (i9 < i8) {
                SparseArray sparseArray4 = sparseArray3;
                bj1 bj1Var3 = (bj1) sparseArray4.get(iArr3[i9]);
                int i10 = bj1Var3.f.o;
                if (i10 != -1) {
                    bj1 bj1Var4 = (bj1) sparseArray4.get(i10);
                    bj1Var3.f.i(bj1Var4, bj1Var4.f);
                    bj1Var3.g.i(bj1Var4, bj1Var4.g);
                }
                i9++;
                sparseArray3 = sparseArray4;
            }
        }

        public final void b(int i, int i2) {
            MotionLayout motionLayout = MotionLayout.this;
            int optimizationLevel = motionLayout.getOptimizationLevel();
            if (motionLayout.z == motionLayout.getStartState()) {
                rx rxVar = this.b;
                androidx.constraintlayout.widget.a aVar = this.d;
                motionLayout.p(rxVar, optimizationLevel, (aVar == null || aVar.d == 0) ? i : i2, (aVar == null || aVar.d == 0) ? i2 : i);
                androidx.constraintlayout.widget.a aVar2 = this.c;
                if (aVar2 != null) {
                    rx rxVar2 = this.a;
                    int i3 = aVar2.d;
                    int i4 = i3 == 0 ? i : i2;
                    if (i3 == 0) {
                        i = i2;
                    }
                    motionLayout.p(rxVar2, optimizationLevel, i4, i);
                    return;
                }
                return;
            }
            androidx.constraintlayout.widget.a aVar3 = this.c;
            if (aVar3 != null) {
                rx rxVar3 = this.a;
                int i5 = aVar3.d;
                motionLayout.p(rxVar3, optimizationLevel, i5 == 0 ? i : i2, i5 == 0 ? i2 : i);
            }
            rx rxVar4 = this.b;
            androidx.constraintlayout.widget.a aVar4 = this.d;
            int i6 = (aVar4 == null || aVar4.d == 0) ? i : i2;
            if (aVar4 == null || aVar4.d == 0) {
                i = i2;
            }
            motionLayout.p(rxVar4, optimizationLevel, i6, i);
        }

        public final void e(androidx.constraintlayout.widget.a aVar, androidx.constraintlayout.widget.a aVar2) {
            this.c = aVar;
            this.d = aVar2;
            this.a = new rx();
            rx rxVar = new rx();
            this.b = rxVar;
            rx rxVar2 = this.a;
            boolean z = MotionLayout.J0;
            MotionLayout motionLayout = MotionLayout.this;
            rx rxVar3 = motionLayout.g;
            ae.b bVar = rxVar3.A0;
            rxVar2.A0 = bVar;
            rxVar2.y0.f = bVar;
            ae.b bVar2 = rxVar3.A0;
            rxVar.A0 = bVar2;
            rxVar.y0.f = bVar2;
            rxVar2.w0.clear();
            this.b.w0.clear();
            rx rxVar4 = this.a;
            rx rxVar5 = motionLayout.g;
            c(rxVar5, rxVar4);
            c(rxVar5, this.b);
            if (motionLayout.I > 0.5d) {
                if (aVar != null) {
                    g(this.a, aVar);
                }
                g(this.b, aVar2);
            } else {
                g(this.b, aVar2);
                if (aVar != null) {
                    g(this.a, aVar);
                }
            }
            this.a.B0 = motionLayout.g();
            rx rxVar6 = this.a;
            rxVar6.x0.c(rxVar6);
            this.b.B0 = motionLayout.g();
            rx rxVar7 = this.b;
            rxVar7.x0.c(rxVar7);
            ViewGroup.LayoutParams layoutParams = motionLayout.getLayoutParams();
            if (layoutParams != null) {
                int i = layoutParams.width;
                qx.a aVar3 = qx.a.WRAP_CONTENT;
                if (i == -2) {
                    this.a.P(aVar3);
                    this.b.P(aVar3);
                }
                if (layoutParams.height == -2) {
                    this.a.Q(aVar3);
                    this.b.Q(aVar3);
                }
            }
        }

        public final void f() {
            HashMap<View, bj1> map;
            MotionLayout motionLayout = MotionLayout.this;
            int i = motionLayout.B;
            int i2 = motionLayout.C;
            int mode = View.MeasureSpec.getMode(i);
            int mode2 = View.MeasureSpec.getMode(i2);
            motionLayout.t0 = mode;
            motionLayout.u0 = mode2;
            b(i, i2);
            int i3 = 0;
            boolean z = true;
            if (((motionLayout.getParent() instanceof MotionLayout) && mode == 1073741824 && mode2 == 1073741824) ? false : true) {
                b(i, i2);
                motionLayout.p0 = this.a.u();
                motionLayout.q0 = this.a.o();
                motionLayout.r0 = this.b.u();
                int iO = this.b.o();
                motionLayout.s0 = iO;
                motionLayout.o0 = (motionLayout.p0 == motionLayout.r0 && motionLayout.q0 == iO) ? false : true;
            }
            int i4 = motionLayout.p0;
            int i5 = motionLayout.q0;
            int i6 = motionLayout.t0;
            if (i6 == Integer.MIN_VALUE || i6 == 0) {
                i4 = (int) ((motionLayout.v0 * (motionLayout.r0 - i4)) + i4);
            }
            int i7 = i4;
            int i8 = motionLayout.u0;
            int i9 = (i8 == Integer.MIN_VALUE || i8 == 0) ? (int) ((motionLayout.v0 * (motionLayout.s0 - i5)) + i5) : i5;
            rx rxVar = this.a;
            motionLayout.i(i, i2, i7, i9, rxVar.K0 || this.b.K0, rxVar.L0 || this.b.L0);
            int childCount = motionLayout.getChildCount();
            motionLayout.D0.a();
            motionLayout.M = true;
            SparseArray sparseArray = new SparseArray();
            int i10 = 0;
            while (true) {
                map = motionLayout.E;
                if (i10 >= childCount) {
                    break;
                }
                View childAt = motionLayout.getChildAt(i10);
                sparseArray.put(childAt.getId(), map.get(childAt));
                i10++;
            }
            int width = motionLayout.getWidth();
            int height = motionLayout.getHeight();
            a.b bVar = motionLayout.u.c;
            int i11 = bVar != null ? bVar.p : -1;
            if (i11 != -1) {
                for (int i12 = 0; i12 < childCount; i12++) {
                    bj1 bj1Var = map.get(motionLayout.getChildAt(i12));
                    if (bj1Var != null) {
                        bj1Var.B = i11;
                    }
                }
            }
            SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();
            int[] iArr = new int[map.size()];
            int i13 = 0;
            for (int i14 = 0; i14 < childCount; i14++) {
                bj1 bj1Var2 = map.get(motionLayout.getChildAt(i14));
                int i15 = bj1Var2.f.o;
                if (i15 != -1) {
                    sparseBooleanArray.put(i15, true);
                    iArr[i13] = bj1Var2.f.o;
                    i13++;
                }
            }
            if (motionLayout.h0 != null) {
                for (int i16 = 0; i16 < i13; i16++) {
                    bj1 bj1Var3 = map.get(motionLayout.findViewById(iArr[i16]));
                    if (bj1Var3 != null) {
                        motionLayout.u.f(bj1Var3);
                    }
                }
                Iterator<MotionHelper> it = motionLayout.h0.iterator();
                while (it.hasNext()) {
                    it.next().u(motionLayout, map);
                }
                for (int i17 = 0; i17 < i13; i17++) {
                    bj1 bj1Var4 = map.get(motionLayout.findViewById(iArr[i17]));
                    if (bj1Var4 != null) {
                        bj1Var4.h(width, height, motionLayout.getNanoTime());
                    }
                }
            } else {
                for (int i18 = 0; i18 < i13; i18++) {
                    bj1 bj1Var5 = map.get(motionLayout.findViewById(iArr[i18]));
                    if (bj1Var5 != null) {
                        motionLayout.u.f(bj1Var5);
                        bj1Var5.h(width, height, motionLayout.getNanoTime());
                    }
                }
            }
            for (int i19 = 0; i19 < childCount; i19++) {
                View childAt2 = motionLayout.getChildAt(i19);
                bj1 bj1Var6 = map.get(childAt2);
                if (!sparseBooleanArray.get(childAt2.getId()) && bj1Var6 != null) {
                    motionLayout.u.f(bj1Var6);
                    bj1Var6.h(width, height, motionLayout.getNanoTime());
                }
            }
            a.b bVar2 = motionLayout.u.c;
            float f = bVar2 != null ? bVar2.i : 0.0f;
            if (f != 0.0f) {
                boolean z2 = ((double) f) < 0.0d;
                float fAbs = Math.abs(f);
                float fMax = -3.4028235E38f;
                float fMin = Float.MAX_VALUE;
                float fMax2 = -3.4028235E38f;
                float fMin2 = Float.MAX_VALUE;
                int i20 = 0;
                while (true) {
                    if (i20 >= childCount) {
                        z = false;
                        break;
                    }
                    bj1 bj1Var7 = map.get(motionLayout.getChildAt(i20));
                    if (!Float.isNaN(bj1Var7.l)) {
                        break;
                    }
                    ej1 ej1Var = bj1Var7.g;
                    float f2 = ej1Var.i;
                    float f3 = ej1Var.j;
                    float f4 = z2 ? f3 - f2 : f3 + f2;
                    fMin2 = Math.min(fMin2, f4);
                    fMax2 = Math.max(fMax2, f4);
                    i20++;
                }
                if (!z) {
                    while (i3 < childCount) {
                        bj1 bj1Var8 = map.get(motionLayout.getChildAt(i3));
                        ej1 ej1Var2 = bj1Var8.g;
                        float f5 = ej1Var2.i;
                        float f6 = ej1Var2.j;
                        float f7 = z2 ? f6 - f5 : f6 + f5;
                        bj1Var8.n = 1.0f / (1.0f - fAbs);
                        bj1Var8.m = fAbs - (((f7 - fMin2) * fAbs) / (fMax2 - fMin2));
                        i3++;
                    }
                    return;
                }
                for (int i21 = 0; i21 < childCount; i21++) {
                    bj1 bj1Var9 = map.get(motionLayout.getChildAt(i21));
                    if (!Float.isNaN(bj1Var9.l)) {
                        fMin = Math.min(fMin, bj1Var9.l);
                        fMax = Math.max(fMax, bj1Var9.l);
                    }
                }
                while (i3 < childCount) {
                    bj1 bj1Var10 = map.get(motionLayout.getChildAt(i3));
                    if (!Float.isNaN(bj1Var10.l)) {
                        bj1Var10.n = 1.0f / (1.0f - fAbs);
                        if (z2) {
                            bj1Var10.m = fAbs - (((fMax - bj1Var10.l) / (fMax - fMin)) * fAbs);
                        } else {
                            bj1Var10.m = fAbs - (((bj1Var10.l - fMin) * fAbs) / (fMax - fMin));
                        }
                    }
                    i3++;
                }
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final void g(rx rxVar, androidx.constraintlayout.widget.a aVar) {
            a.C0004a c0004a;
            a.C0004a c0004a2;
            SparseArray<qx> sparseArray = new SparseArray<>();
            Constraints.LayoutParams layoutParams = new Constraints.LayoutParams();
            sparseArray.clear();
            sparseArray.put(0, rxVar);
            MotionLayout motionLayout = MotionLayout.this;
            sparseArray.put(motionLayout.getId(), rxVar);
            if (aVar != null && aVar.d != 0) {
                rx rxVar2 = this.b;
                int optimizationLevel = motionLayout.getOptimizationLevel();
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(motionLayout.getHeight(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
                int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(motionLayout.getWidth(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
                boolean z = MotionLayout.J0;
                motionLayout.p(rxVar2, optimizationLevel, iMakeMeasureSpec, iMakeMeasureSpec2);
            }
            for (qx qxVar : rxVar.w0) {
                qxVar.k0 = true;
                sparseArray.put(((View) qxVar.i0).getId(), qxVar);
            }
            for (qx qxVar2 : rxVar.w0) {
                View view = (View) qxVar2.i0;
                int id = view.getId();
                HashMap<Integer, a.C0004a> map = aVar.g;
                if (map.containsKey(Integer.valueOf(id)) && (c0004a2 = map.get(Integer.valueOf(id))) != null) {
                    c0004a2.a(layoutParams);
                }
                qxVar2.R(aVar.h(view.getId()).e.c);
                qxVar2.O(aVar.h(view.getId()).e.d);
                if (view instanceof ConstraintHelper) {
                    ConstraintHelper constraintHelper = (ConstraintHelper) view;
                    int id2 = constraintHelper.getId();
                    HashMap<Integer, a.C0004a> map2 = aVar.g;
                    if (map2.containsKey(Integer.valueOf(id2)) && (c0004a = map2.get(Integer.valueOf(id2))) != null && (qxVar2 instanceof ku0)) {
                        constraintHelper.o(c0004a, (ku0) qxVar2, layoutParams, sparseArray);
                    }
                    if (view instanceof Barrier) {
                        ((Barrier) view).t();
                    }
                }
                layoutParams.resolveLayoutDirection(motionLayout.getLayoutDirection());
                MotionLayout motionLayout2 = MotionLayout.this;
                boolean z2 = MotionLayout.J0;
                motionLayout2.b(false, view, qxVar2, layoutParams, sparseArray);
                if (aVar.h(view.getId()).c.c == 1) {
                    qxVar2.j0 = view.getVisibility();
                } else {
                    qxVar2.j0 = aVar.h(view.getId()).c.b;
                }
            }
            for (qx qxVar3 : rxVar.w0) {
                if (qxVar3 instanceof lo3) {
                    ConstraintHelper constraintHelper2 = (ConstraintHelper) qxVar3.i0;
                    iu0 iu0Var = (iu0) qxVar3;
                    constraintHelper2.s(iu0Var, sparseArray);
                    lo3 lo3Var = (lo3) iu0Var;
                    for (int i = 0; i < lo3Var.x0; i++) {
                        qx qxVar4 = lo3Var.w0[i];
                        if (qxVar4 != null) {
                            qxVar4.H = true;
                        }
                    }
                }
            }
        }
    }

    public static class f {
        public static final f b = new f();
        public VelocityTracker a;
    }

    public class g {
        public float a = Float.NaN;
        public float b = Float.NaN;
        public int c = -1;
        public int d = -1;

        public g() {
        }

        public final void a() {
            int i = this.c;
            MotionLayout motionLayout = MotionLayout.this;
            if (i != -1 || this.d != -1) {
                if (i == -1) {
                    int i2 = this.d;
                    if (motionLayout.isAttachedToWindow()) {
                        motionLayout.I(i2, -1);
                    } else {
                        if (motionLayout.y0 == null) {
                            motionLayout.y0 = motionLayout.new g();
                        }
                        motionLayout.y0.d = i2;
                    }
                } else {
                    int i3 = this.d;
                    if (i3 == -1) {
                        motionLayout.F(i);
                    } else {
                        motionLayout.G(i, i3);
                    }
                }
                motionLayout.setState(i.SETUP);
            }
            if (Float.isNaN(this.b)) {
                if (Float.isNaN(this.a)) {
                    return;
                }
                motionLayout.setProgress(this.a);
                return;
            }
            float f = this.a;
            float f2 = this.b;
            if (motionLayout.isAttachedToWindow()) {
                motionLayout.setProgress(f);
                motionLayout.setState(i.MOVING);
                motionLayout.x = f2;
                if (f2 != 0.0f) {
                    motionLayout.t(f2 > 0.0f ? 1.0f : 0.0f);
                } else if (f != 0.0f && f != 1.0f) {
                    motionLayout.t(f > 0.5f ? 1.0f : 0.0f);
                }
            } else {
                if (motionLayout.y0 == null) {
                    motionLayout.y0 = motionLayout.new g();
                }
                g gVar = motionLayout.y0;
                gVar.a = f;
                gVar.b = f2;
            }
            this.a = Float.NaN;
            this.b = Float.NaN;
            this.c = -1;
            this.d = -1;
        }
    }

    public interface h {
        void a(int i);

        void b();

        void c();

        void d();
    }

    public enum i {
        UNDEFINED,
        SETUP,
        MOVING,
        FINISHED
    }

    public MotionLayout(Context context) {
        super(context);
        this.w = null;
        this.x = 0.0f;
        this.y = -1;
        this.z = -1;
        this.A = -1;
        this.B = 0;
        this.C = 0;
        this.D = true;
        this.E = new HashMap<>();
        this.F = 0L;
        this.G = 1.0f;
        this.H = 0.0f;
        this.I = 0.0f;
        this.K = 0.0f;
        this.M = false;
        this.O = 0;
        this.Q = false;
        this.R = new fu2();
        this.S = new c();
        this.W = false;
        this.e0 = false;
        this.f0 = null;
        this.g0 = null;
        this.h0 = null;
        this.i0 = null;
        this.j0 = 0;
        this.k0 = -1L;
        this.l0 = 0.0f;
        this.m0 = 0;
        this.n0 = 0.0f;
        this.o0 = false;
        this.w0 = new a41();
        this.x0 = false;
        this.z0 = null;
        new HashMap();
        this.A0 = new Rect();
        this.B0 = false;
        this.C0 = i.UNDEFINED;
        this.D0 = new e();
        this.E0 = false;
        this.F0 = new RectF();
        this.G0 = null;
        this.H0 = null;
        this.I0 = new ArrayList<>();
        B(null);
    }

    public static Rect s(MotionLayout motionLayout, qx qxVar) {
        motionLayout.getClass();
        int iW = qxVar.w();
        Rect rect = motionLayout.A0;
        rect.top = iW;
        rect.left = qxVar.v();
        rect.right = qxVar.u() + rect.left;
        rect.bottom = qxVar.o() + rect.top;
        return rect;
    }

    public final boolean A(float f2, float f3, MotionEvent motionEvent, View view) {
        boolean z;
        boolean zOnTouchEvent;
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                if (A((r3.getLeft() + f2) - view.getScrollX(), (r3.getTop() + f3) - view.getScrollY(), motionEvent, viewGroup.getChildAt(childCount))) {
                    z = true;
                    break;
                }
            }
            z = false;
        } else {
            z = false;
        }
        if (!z) {
            RectF rectF = this.F0;
            rectF.set(f2, f3, (view.getRight() + f2) - view.getLeft(), (view.getBottom() + f3) - view.getTop());
            if (motionEvent.getAction() != 0 || rectF.contains(motionEvent.getX(), motionEvent.getY())) {
                float f4 = -f2;
                float f5 = -f3;
                Matrix matrix = view.getMatrix();
                if (matrix.isIdentity()) {
                    motionEvent.offsetLocation(f4, f5);
                    zOnTouchEvent = view.onTouchEvent(motionEvent);
                    motionEvent.offsetLocation(-f4, -f5);
                } else {
                    MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
                    motionEventObtain.offsetLocation(f4, f5);
                    if (this.H0 == null) {
                        this.H0 = new Matrix();
                    }
                    matrix.invert(this.H0);
                    motionEventObtain.transform(this.H0);
                    zOnTouchEvent = view.onTouchEvent(motionEventObtain);
                    motionEventObtain.recycle();
                }
                if (zOnTouchEvent) {
                    return true;
                }
            }
        }
        return z;
    }

    public final void B(AttributeSet attributeSet) {
        androidx.constraintlayout.motion.widget.a aVar;
        J0 = isInEditMode();
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.MotionLayout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            boolean z = true;
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                if (index == R$styleable.MotionLayout_layoutDescription) {
                    this.u = new androidx.constraintlayout.motion.widget.a(getContext(), this, typedArrayObtainStyledAttributes.getResourceId(index, -1));
                } else if (index == R$styleable.MotionLayout_currentState) {
                    this.z = typedArrayObtainStyledAttributes.getResourceId(index, -1);
                } else if (index == R$styleable.MotionLayout_motionProgress) {
                    this.K = typedArrayObtainStyledAttributes.getFloat(index, 0.0f);
                    this.M = true;
                } else if (index == R$styleable.MotionLayout_applyMotionScene) {
                    z = typedArrayObtainStyledAttributes.getBoolean(index, z);
                } else if (index == R$styleable.MotionLayout_showPaths) {
                    if (this.O == 0) {
                        this.O = typedArrayObtainStyledAttributes.getBoolean(index, false) ? 2 : 0;
                    }
                } else if (index == R$styleable.MotionLayout_motionDebug) {
                    this.O = typedArrayObtainStyledAttributes.getInt(index, 0);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
            if (this.u == null) {
                Log.e("MotionLayout", "WARNING NO app:layoutDescription tag");
            }
            if (!z) {
                this.u = null;
            }
        }
        if (this.O != 0) {
            androidx.constraintlayout.motion.widget.a aVar2 = this.u;
            if (aVar2 == null) {
                Log.e("MotionLayout", "CHECK: motion scene not set! set \"app:layoutDescription=\"@xml/file\"");
            } else {
                int iH = aVar2.h();
                androidx.constraintlayout.motion.widget.a aVar3 = this.u;
                androidx.constraintlayout.widget.a aVarB = aVar3.b(aVar3.h());
                String strC = b70.c(getContext(), iH);
                int childCount = getChildCount();
                for (int i3 = 0; i3 < childCount; i3++) {
                    View childAt = getChildAt(i3);
                    int id = childAt.getId();
                    if (id == -1) {
                        StringBuilder sbN = C0175b.n("CHECK: ", strC, " ALL VIEWS SHOULD HAVE ID's ");
                        sbN.append(childAt.getClass().getName());
                        sbN.append(" does not!");
                        Log.w("MotionLayout", sbN.toString());
                    }
                    if (aVarB.i(id) == null) {
                        StringBuilder sbN2 = C0175b.n("CHECK: ", strC, " NO CONSTRAINTS for ");
                        sbN2.append(b70.d(childAt));
                        Log.w("MotionLayout", sbN2.toString());
                    }
                }
                int[] iArrJ = aVarB.j();
                for (int i4 = 0; i4 < iArrJ.length; i4++) {
                    int i5 = iArrJ[i4];
                    String strC2 = b70.c(getContext(), i5);
                    if (findViewById(iArrJ[i4]) == null) {
                        Log.w("MotionLayout", "CHECK: " + strC + " NO View matches id " + strC2);
                    }
                    if (aVarB.h(i5).e.d == -1) {
                        Log.w("MotionLayout", "CHECK: " + strC + "(" + strC2 + ") no LAYOUT_HEIGHT");
                    }
                    if (aVarB.h(i5).e.c == -1) {
                        Log.w("MotionLayout", "CHECK: " + strC + "(" + strC2 + ") no LAYOUT_HEIGHT");
                    }
                }
                SparseIntArray sparseIntArray = new SparseIntArray();
                SparseIntArray sparseIntArray2 = new SparseIntArray();
                for (a.b bVar : this.u.d) {
                    a.b bVar2 = this.u.c;
                    if (bVar.d == bVar.c) {
                        Log.e("MotionLayout", "CHECK: start and end constraint set should not be the same!");
                    }
                    int i6 = bVar.d;
                    int i7 = bVar.c;
                    String strC3 = b70.c(getContext(), i6);
                    String strC4 = b70.c(getContext(), i7);
                    if (sparseIntArray.get(i6) == i7) {
                        Log.e("MotionLayout", "CHECK: two transitions with the same start and end " + strC3 + "->" + strC4);
                    }
                    if (sparseIntArray2.get(i7) == i6) {
                        Log.e("MotionLayout", "CHECK: you can't have reverse transitions" + strC3 + "->" + strC4);
                    }
                    sparseIntArray.put(i6, i7);
                    sparseIntArray2.put(i7, i6);
                    if (this.u.b(i6) == null) {
                        Log.e("MotionLayout", " no such constraintSetStart " + strC3);
                    }
                    if (this.u.b(i7) == null) {
                        Log.e("MotionLayout", " no such constraintSetEnd " + strC3);
                    }
                }
            }
        }
        if (this.z != -1 || (aVar = this.u) == null) {
            return;
        }
        this.z = aVar.h();
        this.y = this.u.h();
        a.b bVar3 = this.u.c;
        this.A = bVar3 != null ? bVar3.c : -1;
    }

    public final void C() {
        a.b bVar;
        androidx.constraintlayout.motion.widget.b bVar2;
        View viewFindViewById;
        androidx.constraintlayout.motion.widget.a aVar = this.u;
        if (aVar == null) {
            return;
        }
        if (aVar.a(this.z, this)) {
            requestLayout();
            return;
        }
        int i2 = this.z;
        if (i2 != -1) {
            androidx.constraintlayout.motion.widget.a aVar2 = this.u;
            ArrayList<a.b> arrayList = aVar2.d;
            for (a.b bVar3 : arrayList) {
                if (bVar3.m.size() > 0) {
                    Iterator<a.b.ViewOnClickListenerC0003a> it = bVar3.m.iterator();
                    while (it.hasNext()) {
                        it.next().b(this);
                    }
                }
            }
            ArrayList<a.b> arrayList2 = aVar2.f;
            for (a.b bVar4 : arrayList2) {
                if (bVar4.m.size() > 0) {
                    Iterator<a.b.ViewOnClickListenerC0003a> it2 = bVar4.m.iterator();
                    while (it2.hasNext()) {
                        it2.next().b(this);
                    }
                }
            }
            for (a.b bVar5 : arrayList) {
                if (bVar5.m.size() > 0) {
                    Iterator<a.b.ViewOnClickListenerC0003a> it3 = bVar5.m.iterator();
                    while (it3.hasNext()) {
                        it3.next().a(this, i2, bVar5);
                    }
                }
            }
            for (a.b bVar6 : arrayList2) {
                if (bVar6.m.size() > 0) {
                    Iterator<a.b.ViewOnClickListenerC0003a> it4 = bVar6.m.iterator();
                    while (it4.hasNext()) {
                        it4.next().a(this, i2, bVar6);
                    }
                }
            }
        }
        if (!this.u.o() || (bVar = this.u.c) == null || (bVar2 = bVar.l) == null) {
            return;
        }
        int i3 = bVar2.d;
        if (i3 != -1) {
            MotionLayout motionLayout = bVar2.r;
            viewFindViewById = motionLayout.findViewById(i3);
            if (viewFindViewById == null) {
                Log.e("TouchResponse", "cannot find TouchAnchorId @id/" + b70.c(motionLayout.getContext(), bVar2.d));
            }
        } else {
            viewFindViewById = null;
        }
        if (viewFindViewById instanceof NestedScrollView) {
            NestedScrollView nestedScrollView = (NestedScrollView) viewFindViewById;
            nestedScrollView.setOnTouchListener(new ae3());
            nestedScrollView.setOnScrollChangeListener(new be3());
        }
    }

    public final void D() {
        CopyOnWriteArrayList<h> copyOnWriteArrayList;
        if (this.N == null && ((copyOnWriteArrayList = this.i0) == null || copyOnWriteArrayList.isEmpty())) {
            return;
        }
        ArrayList<Integer> arrayList = this.I0;
        for (Integer num : arrayList) {
            h hVar = this.N;
            if (hVar != null) {
                hVar.a(num.intValue());
            }
            CopyOnWriteArrayList<h> copyOnWriteArrayList2 = this.i0;
            if (copyOnWriteArrayList2 != null) {
                Iterator<h> it = copyOnWriteArrayList2.iterator();
                while (it.hasNext()) {
                    it.next().a(num.intValue());
                }
            }
        }
        arrayList.clear();
    }

    public final void E() {
        this.D0.f();
        invalidate();
    }

    public final void F(int i2) {
        setState(i.SETUP);
        this.z = i2;
        this.y = -1;
        this.A = -1;
        px pxVar = this.o;
        if (pxVar == null) {
            androidx.constraintlayout.motion.widget.a aVar = this.u;
            if (aVar != null) {
                aVar.b(i2).b(this);
                return;
            }
            return;
        }
        float f2 = -1;
        int i3 = pxVar.b;
        SparseArray<px.a> sparseArray = pxVar.d;
        int i4 = 0;
        ConstraintLayout constraintLayout = pxVar.a;
        if (i3 != i2) {
            pxVar.b = i2;
            px.a aVar2 = sparseArray.get(i2);
            while (true) {
                ArrayList<px.b> arrayList = aVar2.b;
                if (i4 >= arrayList.size()) {
                    i4 = -1;
                    break;
                } else if (arrayList.get(i4).a(f2, f2)) {
                    break;
                } else {
                    i4++;
                }
            }
            ArrayList<px.b> arrayList2 = aVar2.b;
            androidx.constraintlayout.widget.a aVar3 = i4 == -1 ? aVar2.d : arrayList2.get(i4).f;
            if (i4 != -1) {
                int i5 = arrayList2.get(i4).e;
            }
            if (aVar3 == null) {
                return;
            }
            pxVar.c = i4;
            aVar3.b(constraintLayout);
            return;
        }
        px.a aVarValueAt = i2 == -1 ? sparseArray.valueAt(0) : sparseArray.get(i3);
        int i6 = pxVar.c;
        if (i6 == -1 || !aVarValueAt.b.get(i6).a(f2, f2)) {
            while (true) {
                ArrayList<px.b> arrayList3 = aVarValueAt.b;
                if (i4 >= arrayList3.size()) {
                    i4 = -1;
                    break;
                } else if (arrayList3.get(i4).a(f2, f2)) {
                    break;
                } else {
                    i4++;
                }
            }
            if (pxVar.c == i4) {
                return;
            }
            ArrayList<px.b> arrayList4 = aVarValueAt.b;
            androidx.constraintlayout.widget.a aVar4 = i4 == -1 ? null : arrayList4.get(i4).f;
            if (i4 != -1) {
                int i7 = arrayList4.get(i4).e;
            }
            if (aVar4 == null) {
                return;
            }
            pxVar.c = i4;
            aVar4.b(constraintLayout);
        }
    }

    public final void G(int i2, int i3) {
        if (!isAttachedToWindow()) {
            if (this.y0 == null) {
                this.y0 = new g();
            }
            g gVar = this.y0;
            gVar.c = i2;
            gVar.d = i3;
            return;
        }
        androidx.constraintlayout.motion.widget.a aVar = this.u;
        if (aVar != null) {
            this.y = i2;
            this.A = i3;
            aVar.n(i2, i3);
            this.D0.e(this.u.b(i2), this.u.b(i3));
            E();
            this.I = 0.0f;
            t(0.0f);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00c9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void H(int r16, float r17, float r18) {
        /*
            Method dump skipped, instruction units count: 364
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionLayout.H(int, float, float):void");
    }

    public final void I(int i2, int i3) {
        os2 os2Var;
        androidx.constraintlayout.motion.widget.a aVar = this.u;
        if (aVar != null && (os2Var = aVar.b) != null) {
            int i4 = this.z;
            float f2 = -1;
            os2.a aVar2 = os2Var.b.get(i2);
            if (aVar2 == null) {
                i4 = i2;
            } else {
                ArrayList<os2.b> arrayList = aVar2.b;
                int i5 = aVar2.c;
                if (f2 != -1.0f && f2 != -1.0f) {
                    Iterator<os2.b> it = arrayList.iterator();
                    os2.b bVar = null;
                    while (true) {
                        if (it.hasNext()) {
                            os2.b next = it.next();
                            if (next.a(f2, f2)) {
                                if (i4 == next.e) {
                                    break;
                                } else {
                                    bVar = next;
                                }
                            }
                        } else if (bVar != null) {
                            i4 = bVar.e;
                        }
                    }
                    i4 = i5;
                } else if (i5 != i4) {
                    Iterator<os2.b> it2 = arrayList.iterator();
                    while (it2.hasNext()) {
                        if (i4 == it2.next().e) {
                            break;
                        }
                    }
                    i4 = i5;
                }
            }
            if (i4 != -1) {
                i2 = i4;
            }
        }
        int i6 = this.z;
        if (i6 == i2) {
            return;
        }
        if (this.y == i2) {
            t(0.0f);
            if (i3 > 0) {
                this.G = i3 / 1000.0f;
                return;
            }
            return;
        }
        if (this.A == i2) {
            t(1.0f);
            if (i3 > 0) {
                this.G = i3 / 1000.0f;
                return;
            }
            return;
        }
        this.A = i2;
        if (i6 != -1) {
            G(i6, i2);
            t(1.0f);
            this.I = 0.0f;
            t(1.0f);
            this.z0 = null;
            if (i3 > 0) {
                this.G = i3 / 1000.0f;
                return;
            }
            return;
        }
        this.Q = false;
        this.K = 1.0f;
        this.H = 0.0f;
        this.I = 0.0f;
        this.f6J = getNanoTime();
        this.F = getNanoTime();
        this.L = false;
        this.v = null;
        if (i3 == -1) {
            this.G = this.u.c() / 1000.0f;
        }
        this.y = -1;
        this.u.n(-1, this.A);
        SparseArray sparseArray = new SparseArray();
        if (i3 == 0) {
            this.G = this.u.c() / 1000.0f;
        } else if (i3 > 0) {
            this.G = i3 / 1000.0f;
        }
        int childCount = getChildCount();
        HashMap<View, bj1> map = this.E;
        map.clear();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            map.put(childAt, new bj1(childAt));
            sparseArray.put(childAt.getId(), map.get(childAt));
        }
        this.M = true;
        androidx.constraintlayout.widget.a aVarB = this.u.b(i2);
        e eVar = this.D0;
        eVar.e(null, aVarB);
        E();
        eVar.a();
        int childCount2 = getChildCount();
        for (int i8 = 0; i8 < childCount2; i8++) {
            View childAt2 = getChildAt(i8);
            bj1 bj1Var = map.get(childAt2);
            if (bj1Var != null) {
                ej1 ej1Var = bj1Var.f;
                ej1Var.g = 0.0f;
                ej1Var.h = 0.0f;
                ej1Var.g(childAt2.getX(), childAt2.getY(), childAt2.getWidth(), childAt2.getHeight());
                zi1 zi1Var = bj1Var.h;
                zi1Var.getClass();
                childAt2.getX();
                childAt2.getY();
                childAt2.getWidth();
                childAt2.getHeight();
                zi1Var.e(childAt2);
            }
        }
        int width = getWidth();
        int height = getHeight();
        if (this.h0 != null) {
            for (int i9 = 0; i9 < childCount; i9++) {
                bj1 bj1Var2 = map.get(getChildAt(i9));
                if (bj1Var2 != null) {
                    this.u.f(bj1Var2);
                }
            }
            Iterator<MotionHelper> it3 = this.h0.iterator();
            while (it3.hasNext()) {
                it3.next().u(this, map);
            }
            for (int i10 = 0; i10 < childCount; i10++) {
                bj1 bj1Var3 = map.get(getChildAt(i10));
                if (bj1Var3 != null) {
                    bj1Var3.h(width, height, getNanoTime());
                }
            }
        } else {
            for (int i11 = 0; i11 < childCount; i11++) {
                bj1 bj1Var4 = map.get(getChildAt(i11));
                if (bj1Var4 != null) {
                    this.u.f(bj1Var4);
                    bj1Var4.h(width, height, getNanoTime());
                }
            }
        }
        a.b bVar2 = this.u.c;
        float f3 = bVar2 != null ? bVar2.i : 0.0f;
        if (f3 != 0.0f) {
            float fMin = Float.MAX_VALUE;
            float fMax = -3.4028235E38f;
            for (int i12 = 0; i12 < childCount; i12++) {
                ej1 ej1Var2 = map.get(getChildAt(i12)).g;
                float f4 = ej1Var2.j + ej1Var2.i;
                fMin = Math.min(fMin, f4);
                fMax = Math.max(fMax, f4);
            }
            for (int i13 = 0; i13 < childCount; i13++) {
                bj1 bj1Var5 = map.get(getChildAt(i13));
                ej1 ej1Var3 = bj1Var5.g;
                float f5 = ej1Var3.i;
                float f6 = ej1Var3.j;
                bj1Var5.n = 1.0f / (1.0f - f3);
                bj1Var5.m = f3 - ((((f5 + f6) - fMin) * f3) / (fMax - fMin));
            }
        }
        this.H = 0.0f;
        this.I = 0.0f;
        this.M = true;
        invalidate();
    }

    public final void J(int i2, androidx.constraintlayout.widget.a aVar) {
        androidx.constraintlayout.motion.widget.a aVar2 = this.u;
        if (aVar2 != null) {
            aVar2.g.put(i2, aVar);
        }
        this.D0.e(this.u.b(this.y), this.u.b(this.A));
        E();
        if (this.z == i2) {
            aVar.b(this);
        }
    }

    public final void K(int i2, View... viewArr) {
        String str;
        androidx.constraintlayout.motion.widget.a aVar = this.u;
        if (aVar == null) {
            Log.e("MotionLayout", " no motionScene");
            return;
        }
        androidx.constraintlayout.motion.widget.d dVar = aVar.q;
        dVar.getClass();
        ArrayList arrayList = new ArrayList();
        Iterator<androidx.constraintlayout.motion.widget.c> it = dVar.b.iterator();
        androidx.constraintlayout.motion.widget.c cVar = null;
        while (true) {
            boolean zHasNext = it.hasNext();
            str = dVar.d;
            if (!zHasNext) {
                break;
            }
            androidx.constraintlayout.motion.widget.c next = it.next();
            if (next.a == i2) {
                for (View view : viewArr) {
                    if (next.b(view)) {
                        arrayList.add(view);
                    }
                }
                if (!arrayList.isEmpty()) {
                    View[] viewArr2 = (View[]) arrayList.toArray(new View[0]);
                    MotionLayout motionLayout = dVar.a;
                    int currentState = motionLayout.getCurrentState();
                    if (next.e == 2) {
                        next.a(dVar, dVar.a, currentState, null, viewArr2);
                    } else if (currentState == -1) {
                        Log.w(str, "No support for ViewTransition within transition yet. Currently: " + motionLayout.toString());
                    } else {
                        androidx.constraintlayout.motion.widget.a aVar2 = motionLayout.u;
                        androidx.constraintlayout.widget.a aVarB = aVar2 == null ? null : aVar2.b(currentState);
                        if (aVarB != null) {
                            next.a(dVar, dVar.a, currentState, aVarB, viewArr2);
                        }
                    }
                    arrayList.clear();
                }
                cVar = next;
            }
        }
        if (cVar == null) {
            Log.e(str, " Could not find ViewTransition");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:186:0x0516  */
    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void dispatchDraw(android.graphics.Canvas r34) {
        /*
            Method dump skipped, instruction units count: 1329
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionLayout.dispatchDraw(android.graphics.Canvas):void");
    }

    public int[] getConstraintSetIds() {
        androidx.constraintlayout.motion.widget.a aVar = this.u;
        if (aVar == null) {
            return null;
        }
        SparseArray<androidx.constraintlayout.widget.a> sparseArray = aVar.g;
        int size = sparseArray.size();
        int[] iArr = new int[size];
        for (int i2 = 0; i2 < size; i2++) {
            iArr[i2] = sparseArray.keyAt(i2);
        }
        return iArr;
    }

    public int getCurrentState() {
        return this.z;
    }

    public ArrayList<a.b> getDefinedTransitions() {
        androidx.constraintlayout.motion.widget.a aVar = this.u;
        if (aVar == null) {
            return null;
        }
        return aVar.d;
    }

    public r80 getDesignTool() {
        if (this.T == null) {
            this.T = new r80();
        }
        return this.T;
    }

    public int getEndState() {
        return this.A;
    }

    public long getNanoTime() {
        return System.nanoTime();
    }

    public float getProgress() {
        return this.I;
    }

    public androidx.constraintlayout.motion.widget.a getScene() {
        return this.u;
    }

    public int getStartState() {
        return this.y;
    }

    public float getTargetPosition() {
        return this.K;
    }

    public Bundle getTransitionState() {
        if (this.y0 == null) {
            this.y0 = new g();
        }
        g gVar = this.y0;
        MotionLayout motionLayout = MotionLayout.this;
        gVar.d = motionLayout.A;
        gVar.c = motionLayout.y;
        gVar.b = motionLayout.getVelocity();
        gVar.a = motionLayout.getProgress();
        g gVar2 = this.y0;
        gVar2.getClass();
        Bundle bundle = new Bundle();
        bundle.putFloat("motion.progress", gVar2.a);
        bundle.putFloat("motion.velocity", gVar2.b);
        bundle.putInt("motion.StartState", gVar2.c);
        bundle.putInt("motion.EndState", gVar2.d);
        return bundle;
    }

    public long getTransitionTimeMs() {
        if (this.u != null) {
            this.G = r0.c() / 1000.0f;
        }
        return (long) (this.G * 1000.0f);
    }

    public float getVelocity() {
        return this.x;
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout
    public final void h(int i2) {
        this.o = null;
    }

    @Override // android.view.View
    public final boolean isAttachedToWindow() {
        return super.isAttachedToWindow();
    }

    @Override // defpackage.tl1
    public final void j(View view, int i2, int i3, int i4, int i5, int i6, int[] iArr) {
        if (this.W || i2 != 0 || i3 != 0) {
            iArr[0] = iArr[0] + i4;
            iArr[1] = iArr[1] + i5;
        }
        this.W = false;
    }

    @Override // defpackage.sl1
    public final boolean l(View view, View view2, int i2, int i3) {
        a.b bVar;
        androidx.constraintlayout.motion.widget.b bVar2;
        androidx.constraintlayout.motion.widget.a aVar = this.u;
        return (aVar == null || (bVar = aVar.c) == null || (bVar2 = bVar.l) == null || (bVar2.w & 2) != 0) ? false : true;
    }

    @Override // defpackage.sl1
    public final void m(View view, View view2, int i2, int i3) {
        this.c0 = getNanoTime();
        this.d0 = 0.0f;
        this.a0 = 0.0f;
        this.b0 = 0.0f;
    }

    @Override // defpackage.sl1
    public final void n(View view, int i2) {
        androidx.constraintlayout.motion.widget.b bVar;
        int i3;
        androidx.constraintlayout.motion.widget.a aVar = this.u;
        if (aVar != null) {
            float f2 = this.d0;
            if (f2 == 0.0f) {
                return;
            }
            float f3 = this.a0 / f2;
            float f4 = this.b0 / f2;
            a.b bVar2 = aVar.c;
            if (bVar2 == null || (bVar = bVar2.l) == null) {
                return;
            }
            bVar.m = false;
            MotionLayout motionLayout = bVar.r;
            float progress = motionLayout.getProgress();
            bVar.r.y(bVar.d, progress, bVar.h, bVar.g, bVar.n);
            float f5 = bVar.k;
            float[] fArr = bVar.n;
            float f6 = f5 != 0.0f ? (f3 * f5) / fArr[0] : (f4 * bVar.l) / fArr[1];
            if (!Float.isNaN(f6)) {
                progress += f6 / 3.0f;
            }
            if (progress == 0.0f || progress == 1.0f || (i3 = bVar.c) == 3) {
                return;
            }
            motionLayout.H(i3, ((double) progress) >= 0.5d ? 1.0f : 0.0f, f6);
        }
    }

    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v5 */
    @Override // defpackage.sl1
    public final void o(View view, int i2, int i3, int[] iArr, int i4) {
        a.b bVar;
        boolean z;
        ?? r1;
        androidx.constraintlayout.motion.widget.b bVar2;
        float f2;
        androidx.constraintlayout.motion.widget.b bVar3;
        androidx.constraintlayout.motion.widget.b bVar4;
        androidx.constraintlayout.motion.widget.b bVar5;
        int i5;
        androidx.constraintlayout.motion.widget.a aVar = this.u;
        if (aVar == null || (bVar = aVar.c) == null || !(!bVar.o)) {
            return;
        }
        int i6 = -1;
        if (!z || (bVar5 = bVar.l) == null || (i5 = bVar5.e) == -1 || view.getId() == i5) {
            a.b bVar6 = aVar.c;
            if ((bVar6 == null || (bVar4 = bVar6.l) == null) ? false : bVar4.u) {
                androidx.constraintlayout.motion.widget.b bVar7 = bVar.l;
                if (bVar7 != null && (bVar7.w & 4) != 0) {
                    i6 = i3;
                }
                float f3 = this.H;
                if ((f3 == 1.0f || f3 == 0.0f) && view.canScrollVertically(i6)) {
                    return;
                }
            }
            androidx.constraintlayout.motion.widget.b bVar8 = bVar.l;
            if (bVar8 != null && (bVar8.w & 1) != 0) {
                float f4 = i2;
                float f5 = i3;
                a.b bVar9 = aVar.c;
                if (bVar9 == null || (bVar3 = bVar9.l) == null) {
                    f2 = 0.0f;
                } else {
                    bVar3.r.y(bVar3.d, bVar3.r.getProgress(), bVar3.h, bVar3.g, bVar3.n);
                    float f6 = bVar3.k;
                    float[] fArr = bVar3.n;
                    if (f6 != 0.0f) {
                        if (fArr[0] == 0.0f) {
                            fArr[0] = 1.0E-7f;
                        }
                        f2 = (f4 * f6) / fArr[0];
                    } else {
                        if (fArr[1] == 0.0f) {
                            fArr[1] = 1.0E-7f;
                        }
                        f2 = (f5 * bVar3.l) / fArr[1];
                    }
                }
                float f7 = this.I;
                if ((f7 <= 0.0f && f2 < 0.0f) || (f7 >= 1.0f && f2 > 0.0f)) {
                    view.setNestedScrollingEnabled(false);
                    view.post(new a(view));
                    return;
                }
            }
            float f8 = this.H;
            long nanoTime = getNanoTime();
            float f9 = i2;
            this.a0 = f9;
            float f10 = i3;
            this.b0 = f10;
            this.d0 = (float) ((nanoTime - this.c0) * 1.0E-9d);
            this.c0 = nanoTime;
            a.b bVar10 = aVar.c;
            if (bVar10 != null && (bVar2 = bVar10.l) != null) {
                MotionLayout motionLayout = bVar2.r;
                float progress = motionLayout.getProgress();
                if (!bVar2.m) {
                    bVar2.m = true;
                    motionLayout.setProgress(progress);
                }
                bVar2.r.y(bVar2.d, progress, bVar2.h, bVar2.g, bVar2.n);
                float f11 = bVar2.k;
                float[] fArr2 = bVar2.n;
                if (Math.abs((bVar2.l * fArr2[1]) + (f11 * fArr2[0])) < 0.01d) {
                    fArr2[0] = 0.01f;
                    fArr2[1] = 0.01f;
                }
                float f12 = bVar2.k;
                float fMax = Math.max(Math.min(progress + (f12 != 0.0f ? (f9 * f12) / fArr2[0] : (f10 * bVar2.l) / fArr2[1]), 1.0f), 0.0f);
                if (fMax != motionLayout.getProgress()) {
                    motionLayout.setProgress(fMax);
                }
            }
            if (f8 != this.H) {
                iArr[0] = i2;
                r1 = 1;
                iArr[1] = i3;
            } else {
                r1 = 1;
            }
            v(false);
            if (iArr[0] == 0 && iArr[r1] == 0) {
                return;
            }
            this.W = r1;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        a.b bVar;
        int i2;
        boolean z;
        super.onAttachedToWindow();
        Display display = getDisplay();
        if (display != null) {
            display.getRotation();
        }
        androidx.constraintlayout.motion.widget.a aVar = this.u;
        if (aVar != null && (i2 = this.z) != -1) {
            androidx.constraintlayout.widget.a aVarB = aVar.b(i2);
            androidx.constraintlayout.motion.widget.a aVar2 = this.u;
            int i3 = 0;
            while (true) {
                SparseArray<androidx.constraintlayout.widget.a> sparseArray = aVar2.g;
                if (i3 >= sparseArray.size()) {
                    break;
                }
                int iKeyAt = sparseArray.keyAt(i3);
                SparseIntArray sparseIntArray = aVar2.i;
                int i4 = sparseIntArray.get(iKeyAt);
                int size = sparseIntArray.size();
                while (i4 > 0) {
                    if (i4 != iKeyAt) {
                        int i5 = size - 1;
                        if (size >= 0) {
                            i4 = sparseIntArray.get(i4);
                            size = i5;
                        }
                    }
                    z = true;
                    break;
                }
                z = false;
                if (z) {
                    Log.e("MotionScene", "Cannot be derived from yourself");
                    break;
                } else {
                    aVar2.m(iKeyAt, this);
                    i3++;
                }
            }
            ArrayList<MotionHelper> arrayList = this.h0;
            if (arrayList != null) {
                Iterator<MotionHelper> it = arrayList.iterator();
                while (it.hasNext()) {
                    it.next().getClass();
                }
            }
            if (aVarB != null) {
                aVarB.b(this);
            }
            this.y = this.z;
        }
        C();
        g gVar = this.y0;
        if (gVar != null) {
            if (this.B0) {
                post(new b());
                return;
            } else {
                gVar.a();
                return;
            }
        }
        androidx.constraintlayout.motion.widget.a aVar3 = this.u;
        if (aVar3 == null || (bVar = aVar3.c) == null || bVar.n != 4) {
            return;
        }
        t(1.0f);
        this.z0 = null;
        setState(i.SETUP);
        setState(i.MOVING);
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        androidx.constraintlayout.motion.widget.b bVar;
        int i2;
        RectF rectFB;
        MotionLayout motionLayout;
        int currentState;
        androidx.constraintlayout.motion.widget.c cVar;
        int i3;
        androidx.constraintlayout.motion.widget.a aVar = this.u;
        if (aVar == null || !this.D) {
            return false;
        }
        androidx.constraintlayout.motion.widget.d dVar = aVar.q;
        if (dVar != null && (currentState = (motionLayout = dVar.a).getCurrentState()) != -1) {
            HashSet<View> hashSet = dVar.c;
            ArrayList<androidx.constraintlayout.motion.widget.c> arrayList = dVar.b;
            if (hashSet == null) {
                dVar.c = new HashSet<>();
                for (androidx.constraintlayout.motion.widget.c cVar2 : arrayList) {
                    int childCount = motionLayout.getChildCount();
                    for (int i4 = 0; i4 < childCount; i4++) {
                        View childAt = motionLayout.getChildAt(i4);
                        if (cVar2.c(childAt)) {
                            childAt.getId();
                            dVar.c.add(childAt);
                        }
                    }
                }
            }
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            Rect rect = new Rect();
            int action = motionEvent.getAction();
            ArrayList<c.a> arrayList2 = dVar.e;
            int i5 = 2;
            if (arrayList2 != null && !arrayList2.isEmpty()) {
                for (c.a aVar2 : dVar.e) {
                    if (action != 1) {
                        if (action != 2) {
                            aVar2.getClass();
                        } else {
                            View view = aVar2.c.b;
                            Rect rect2 = aVar2.l;
                            view.getHitRect(rect2);
                            if (!rect2.contains((int) x, (int) y) && !aVar2.h) {
                                aVar2.b();
                            }
                        }
                    } else if (!aVar2.h) {
                        aVar2.b();
                    }
                }
            }
            if (action == 0 || action == 1) {
                androidx.constraintlayout.motion.widget.a aVar3 = motionLayout.u;
                androidx.constraintlayout.widget.a aVarB = aVar3 == null ? null : aVar3.b(currentState);
                Iterator<androidx.constraintlayout.motion.widget.c> it = arrayList.iterator();
                while (it.hasNext()) {
                    androidx.constraintlayout.motion.widget.c next = it.next();
                    int i6 = next.b;
                    if (i6 != 1 ? !(i6 != i5 ? !(i6 == 3 && action == 0) : action != 1) : action == 0) {
                        for (View view2 : dVar.c) {
                            if (next.c(view2)) {
                                view2.getHitRect(rect);
                                if (rect.contains((int) x, (int) y)) {
                                    cVar = next;
                                    i3 = i5;
                                    next.a(dVar, dVar.a, currentState, aVarB, view2);
                                } else {
                                    cVar = next;
                                    i3 = i5;
                                }
                                next = cVar;
                                i5 = i3;
                            }
                        }
                    }
                }
            }
        }
        a.b bVar2 = this.u.c;
        if (bVar2 == null || !(!bVar2.o) || (bVar = bVar2.l) == null) {
            return false;
        }
        if ((motionEvent.getAction() == 0 && (rectFB = bVar.b(this, new RectF())) != null && !rectFB.contains(motionEvent.getX(), motionEvent.getY())) || (i2 = bVar.e) == -1) {
            return false;
        }
        View view3 = this.G0;
        if (view3 == null || view3.getId() != i2) {
            this.G0 = findViewById(i2);
        }
        if (this.G0 == null) {
            return false;
        }
        RectF rectF = this.F0;
        rectF.set(r1.getLeft(), this.G0.getTop(), this.G0.getRight(), this.G0.getBottom());
        if (!rectF.contains(motionEvent.getX(), motionEvent.getY()) || A(this.G0.getLeft(), this.G0.getTop(), motionEvent, this.G0)) {
            return false;
        }
        return onTouchEvent(motionEvent);
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        this.x0 = true;
        try {
            if (this.u == null) {
                super.onLayout(z, i2, i3, i4, i5);
                return;
            }
            int i6 = i4 - i2;
            int i7 = i5 - i3;
            if (this.U != i6 || this.V != i7) {
                E();
                v(true);
            }
            this.U = i6;
            this.V = i7;
        } finally {
            this.x0 = false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0074  */
    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onMeasure(int r18, int r19) {
        /*
            Method dump skipped, instruction units count: 371
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionLayout.onMeasure(int, int):void");
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedFling(View view, float f2, float f3, boolean z) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedPreFling(View view, float f2, float f3) {
        return false;
    }

    @Override // android.view.View
    public final void onRtlPropertiesChanged(int i2) {
        androidx.constraintlayout.motion.widget.b bVar;
        androidx.constraintlayout.motion.widget.a aVar = this.u;
        if (aVar != null) {
            boolean zG = g();
            aVar.p = zG;
            a.b bVar2 = aVar.c;
            if (bVar2 == null || (bVar = bVar2.l) == null) {
                return;
            }
            bVar.c(zG);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:109:0x0232  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x023a  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0356  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x04ca  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x04ed  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0507  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x0514  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x0571  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x057c  */
    /* JADX WARN: Removed duplicated region for block: B:374:0x07e9  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0108 A[PHI: r19
  0x0108: PHI (r19v5 java.util.Iterator) = (r19v6 java.util.Iterator), (r19v7 java.util.Iterator) binds: [B:69:0x0106, B:63:0x00ed] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onTouchEvent(android.view.MotionEvent r34) {
        /*
            Method dump skipped, instruction units count: 2055
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionLayout.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public final void onViewAdded(View view) {
        super.onViewAdded(view);
        if (view instanceof MotionHelper) {
            MotionHelper motionHelper = (MotionHelper) view;
            if (this.i0 == null) {
                this.i0 = new CopyOnWriteArrayList<>();
            }
            this.i0.add(motionHelper);
            if (motionHelper.n) {
                if (this.f0 == null) {
                    this.f0 = new ArrayList<>();
                }
                this.f0.add(motionHelper);
            }
            if (motionHelper.o) {
                if (this.g0 == null) {
                    this.g0 = new ArrayList<>();
                }
                this.g0.add(motionHelper);
            }
            if (motionHelper instanceof MotionEffect) {
                if (this.h0 == null) {
                    this.h0 = new ArrayList<>();
                }
                this.h0.add(motionHelper);
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public final void onViewRemoved(View view) {
        super.onViewRemoved(view);
        ArrayList<MotionHelper> arrayList = this.f0;
        if (arrayList != null) {
            arrayList.remove(view);
        }
        ArrayList<MotionHelper> arrayList2 = this.g0;
        if (arrayList2 != null) {
            arrayList2.remove(view);
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View, android.view.ViewParent
    public final void requestLayout() {
        androidx.constraintlayout.motion.widget.a aVar;
        a.b bVar;
        if (!this.o0 && this.z == -1 && (aVar = this.u) != null && (bVar = aVar.c) != null) {
            int i2 = bVar.q;
            if (i2 == 0) {
                return;
            }
            if (i2 == 2) {
                int childCount = getChildCount();
                for (int i3 = 0; i3 < childCount; i3++) {
                    this.E.get(getChildAt(i3)).d = true;
                }
                return;
            }
        }
        super.requestLayout();
    }

    public void setDebugMode(int i2) {
        this.O = i2;
        invalidate();
    }

    public void setDelayedApplicationOfInitialState(boolean z) {
        this.B0 = z;
    }

    public void setInteractionEnabled(boolean z) {
        this.D = z;
    }

    public void setInterpolatedProgress(float f2) {
        if (this.u != null) {
            setState(i.MOVING);
            Interpolator interpolatorE = this.u.e();
            if (interpolatorE != null) {
                setProgress(interpolatorE.getInterpolation(f2));
                return;
            }
        }
        setProgress(f2);
    }

    public void setOnHide(float f2) {
        ArrayList<MotionHelper> arrayList = this.g0;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.g0.get(i2).setProgress(f2);
            }
        }
    }

    public void setOnShow(float f2) {
        ArrayList<MotionHelper> arrayList = this.f0;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.f0.get(i2).setProgress(f2);
            }
        }
    }

    public void setProgress(float f2) {
        if (f2 < 0.0f || f2 > 1.0f) {
            Log.w("MotionLayout", "Warning! Progress is defined for values between 0.0 and 1.0 inclusive");
        }
        if (!isAttachedToWindow()) {
            if (this.y0 == null) {
                this.y0 = new g();
            }
            this.y0.a = f2;
            return;
        }
        i iVar = i.FINISHED;
        i iVar2 = i.MOVING;
        if (f2 <= 0.0f) {
            if (this.I == 1.0f && this.z == this.A) {
                setState(iVar2);
            }
            this.z = this.y;
            if (this.I == 0.0f) {
                setState(iVar);
            }
        } else if (f2 >= 1.0f) {
            if (this.I == 0.0f && this.z == this.y) {
                setState(iVar2);
            }
            this.z = this.A;
            if (this.I == 1.0f) {
                setState(iVar);
            }
        } else {
            this.z = -1;
            setState(iVar2);
        }
        if (this.u == null) {
            return;
        }
        this.L = true;
        this.K = f2;
        this.H = f2;
        this.f6J = -1L;
        this.F = -1L;
        this.v = null;
        this.M = true;
        invalidate();
    }

    public void setScene(androidx.constraintlayout.motion.widget.a aVar) {
        androidx.constraintlayout.motion.widget.b bVar;
        this.u = aVar;
        boolean zG = g();
        aVar.p = zG;
        a.b bVar2 = aVar.c;
        if (bVar2 != null && (bVar = bVar2.l) != null) {
            bVar.c(zG);
        }
        E();
    }

    public void setStartState(int i2) {
        if (isAttachedToWindow()) {
            this.z = i2;
            return;
        }
        if (this.y0 == null) {
            this.y0 = new g();
        }
        g gVar = this.y0;
        gVar.c = i2;
        gVar.d = i2;
    }

    public void setState(i iVar) {
        i iVar2 = i.FINISHED;
        if (iVar == iVar2 && this.z == -1) {
            return;
        }
        i iVar3 = this.C0;
        this.C0 = iVar;
        i iVar4 = i.MOVING;
        if (iVar3 == iVar4 && iVar == iVar4) {
            w();
        }
        int iOrdinal = iVar3.ordinal();
        if (iOrdinal != 0 && iOrdinal != 1) {
            if (iOrdinal == 2 && iVar == iVar2) {
                x();
                return;
            }
            return;
        }
        if (iVar == iVar4) {
            w();
        }
        if (iVar == iVar2) {
            x();
        }
    }

    public void setTransition(int i2) {
        if (this.u != null) {
            a.b bVarZ = z(i2);
            this.y = bVarZ.d;
            this.A = bVarZ.c;
            if (!isAttachedToWindow()) {
                if (this.y0 == null) {
                    this.y0 = new g();
                }
                g gVar = this.y0;
                gVar.c = this.y;
                gVar.d = this.A;
                return;
            }
            int i3 = this.z;
            float f2 = i3 == this.y ? 0.0f : i3 == this.A ? 1.0f : Float.NaN;
            androidx.constraintlayout.motion.widget.a aVar = this.u;
            aVar.c = bVarZ;
            androidx.constraintlayout.motion.widget.b bVar = bVarZ.l;
            if (bVar != null) {
                bVar.c(aVar.p);
            }
            this.D0.e(this.u.b(this.y), this.u.b(this.A));
            E();
            if (this.I != f2) {
                if (f2 == 0.0f) {
                    u(true);
                    this.u.b(this.y).b(this);
                } else if (f2 == 1.0f) {
                    u(false);
                    this.u.b(this.A).b(this);
                }
            }
            this.I = Float.isNaN(f2) ? 0.0f : f2;
            if (!Float.isNaN(f2)) {
                setProgress(f2);
            } else {
                b70.b();
                t(0.0f);
            }
        }
    }

    public void setTransitionDuration(int i2) {
        androidx.constraintlayout.motion.widget.a aVar = this.u;
        if (aVar == null) {
            Log.e("MotionLayout", "MotionScene not defined");
            return;
        }
        a.b bVar = aVar.c;
        if (bVar != null) {
            bVar.h = Math.max(i2, 8);
        } else {
            aVar.j = i2;
        }
    }

    public void setTransitionListener(h hVar) {
        this.N = hVar;
    }

    public void setTransitionState(Bundle bundle) {
        if (this.y0 == null) {
            this.y0 = new g();
        }
        g gVar = this.y0;
        gVar.getClass();
        gVar.a = bundle.getFloat("motion.progress");
        gVar.b = bundle.getFloat("motion.velocity");
        gVar.c = bundle.getInt("motion.StartState");
        gVar.d = bundle.getInt("motion.EndState");
        if (isAttachedToWindow()) {
            this.y0.a();
        }
    }

    public final void t(float f2) {
        if (this.u == null) {
            return;
        }
        float f3 = this.I;
        float f4 = this.H;
        if (f3 != f4 && this.L) {
            this.I = f4;
        }
        float f5 = this.I;
        if (f5 == f2) {
            return;
        }
        this.Q = false;
        this.K = f2;
        this.G = r0.c() / 1000.0f;
        setProgress(this.K);
        this.v = null;
        this.w = this.u.e();
        this.L = false;
        this.F = getNanoTime();
        this.M = true;
        this.H = f5;
        this.I = f5;
        invalidate();
    }

    @Override // android.view.View
    public final String toString() {
        Context context = getContext();
        return b70.c(context, this.y) + "->" + b70.c(context, this.A) + " (pos:" + this.I + " Dpos/Dt:" + this.x;
    }

    public final void u(boolean z) {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            bj1 bj1Var = this.E.get(getChildAt(i2));
            if (bj1Var != null && "button".equals(b70.d(bj1Var.b)) && bj1Var.A != null) {
                int i3 = 0;
                while (true) {
                    k41[] k41VarArr = bj1Var.A;
                    if (i3 < k41VarArr.length) {
                        k41VarArr[i3].h(bj1Var.b, z ? -100.0f : 100.0f);
                        i3++;
                    }
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:111:0x01ac  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x01b9  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x01c6  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x01df  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x01e7  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x016c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void v(boolean r23) {
        /*
            Method dump skipped, instruction units count: 621
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionLayout.v(boolean):void");
    }

    public final void w() {
        CopyOnWriteArrayList<h> copyOnWriteArrayList;
        if ((this.N == null && ((copyOnWriteArrayList = this.i0) == null || copyOnWriteArrayList.isEmpty())) || this.n0 == this.H) {
            return;
        }
        if (this.m0 != -1) {
            h hVar = this.N;
            if (hVar != null) {
                hVar.c();
            }
            CopyOnWriteArrayList<h> copyOnWriteArrayList2 = this.i0;
            if (copyOnWriteArrayList2 != null) {
                Iterator<h> it = copyOnWriteArrayList2.iterator();
                while (it.hasNext()) {
                    it.next().c();
                }
            }
        }
        this.m0 = -1;
        this.n0 = this.H;
        h hVar2 = this.N;
        if (hVar2 != null) {
            hVar2.b();
        }
        CopyOnWriteArrayList<h> copyOnWriteArrayList3 = this.i0;
        if (copyOnWriteArrayList3 != null) {
            Iterator<h> it2 = copyOnWriteArrayList3.iterator();
            while (it2.hasNext()) {
                it2.next().b();
            }
        }
    }

    public final void x() {
        CopyOnWriteArrayList<h> copyOnWriteArrayList;
        if ((this.N != null || ((copyOnWriteArrayList = this.i0) != null && !copyOnWriteArrayList.isEmpty())) && this.m0 == -1) {
            this.m0 = this.z;
            ArrayList<Integer> arrayList = this.I0;
            int iIntValue = !arrayList.isEmpty() ? arrayList.get(arrayList.size() - 1).intValue() : -1;
            int i2 = this.z;
            if (iIntValue != i2 && i2 != -1) {
                arrayList.add(Integer.valueOf(i2));
            }
        }
        D();
        Runnable runnable = this.z0;
        if (runnable != null) {
            runnable.run();
            this.z0 = null;
        }
    }

    public final void y(int i2, float f2, float f3, float f4, float[] fArr) {
        View viewC = c(i2);
        bj1 bj1Var = this.E.get(viewC);
        if (bj1Var != null) {
            bj1Var.d(f2, f3, f4, fArr);
            viewC.getY();
        } else {
            Log.w("MotionLayout", "WARNING could not find view id " + (viewC == null ? gq2.e("", i2) : viewC.getContext().getResources().getResourceName(i2)));
        }
    }

    public final a.b z(int i2) {
        for (a.b bVar : this.u.d) {
            if (bVar.a == i2) {
                return bVar;
            }
        }
        return null;
    }

    public void setTransition(a.b bVar) {
        androidx.constraintlayout.motion.widget.b bVar2;
        androidx.constraintlayout.motion.widget.a aVar = this.u;
        aVar.c = bVar;
        if (bVar != null && (bVar2 = bVar.l) != null) {
            bVar2.c(aVar.p);
        }
        setState(i.SETUP);
        int i2 = this.z;
        a.b bVar3 = this.u.c;
        if (i2 == (bVar3 == null ? -1 : bVar3.c)) {
            this.I = 1.0f;
            this.H = 1.0f;
            this.K = 1.0f;
        } else {
            this.I = 0.0f;
            this.H = 0.0f;
            this.K = 0.0f;
        }
        this.f6J = (bVar.r & 1) != 0 ? -1L : getNanoTime();
        int iH = this.u.h();
        androidx.constraintlayout.motion.widget.a aVar2 = this.u;
        a.b bVar4 = aVar2.c;
        int i3 = bVar4 != null ? bVar4.c : -1;
        if (iH == this.y && i3 == this.A) {
            return;
        }
        this.y = iH;
        this.A = i3;
        aVar2.n(iH, i3);
        androidx.constraintlayout.widget.a aVarB = this.u.b(this.y);
        androidx.constraintlayout.widget.a aVarB2 = this.u.b(this.A);
        e eVar = this.D0;
        eVar.e(aVarB, aVarB2);
        int i4 = this.y;
        int i5 = this.A;
        eVar.e = i4;
        eVar.f = i5;
        eVar.f();
        E();
    }

    public MotionLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.w = null;
        this.x = 0.0f;
        this.y = -1;
        this.z = -1;
        this.A = -1;
        this.B = 0;
        this.C = 0;
        this.D = true;
        this.E = new HashMap<>();
        this.F = 0L;
        this.G = 1.0f;
        this.H = 0.0f;
        this.I = 0.0f;
        this.K = 0.0f;
        this.M = false;
        this.O = 0;
        this.Q = false;
        this.R = new fu2();
        this.S = new c();
        this.W = false;
        this.e0 = false;
        this.f0 = null;
        this.g0 = null;
        this.h0 = null;
        this.i0 = null;
        this.j0 = 0;
        this.k0 = -1L;
        this.l0 = 0.0f;
        this.m0 = 0;
        this.n0 = 0.0f;
        this.o0 = false;
        this.w0 = new a41();
        this.x0 = false;
        this.z0 = null;
        new HashMap();
        this.A0 = new Rect();
        this.B0 = false;
        this.C0 = i.UNDEFINED;
        this.D0 = new e();
        this.E0 = false;
        this.F0 = new RectF();
        this.G0 = null;
        this.H0 = null;
        this.I0 = new ArrayList<>();
        B(attributeSet);
    }

    public MotionLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.w = null;
        this.x = 0.0f;
        this.y = -1;
        this.z = -1;
        this.A = -1;
        this.B = 0;
        this.C = 0;
        this.D = true;
        this.E = new HashMap<>();
        this.F = 0L;
        this.G = 1.0f;
        this.H = 0.0f;
        this.I = 0.0f;
        this.K = 0.0f;
        this.M = false;
        this.O = 0;
        this.Q = false;
        this.R = new fu2();
        this.S = new c();
        this.W = false;
        this.e0 = false;
        this.f0 = null;
        this.g0 = null;
        this.h0 = null;
        this.i0 = null;
        this.j0 = 0;
        this.k0 = -1L;
        this.l0 = 0.0f;
        this.m0 = 0;
        this.n0 = 0.0f;
        this.o0 = false;
        this.w0 = new a41();
        this.x0 = false;
        this.z0 = null;
        new HashMap();
        this.A0 = new Rect();
        this.B0 = false;
        this.C0 = i.UNDEFINED;
        this.D0 = new e();
        this.E0 = false;
        this.F0 = new RectF();
        this.G0 = null;
        this.H0 = null;
        this.I0 = new ArrayList<>();
        B(attributeSet);
    }

    @Override // defpackage.sl1
    public final void k(View view, int i2, int i3, int i4, int i5, int i6) {
    }
}
