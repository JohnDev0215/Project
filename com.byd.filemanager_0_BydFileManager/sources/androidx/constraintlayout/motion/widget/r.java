package androidx.constraintlayout.motion.widget;

import android.os.Build;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintAttribute;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.Arrays;

/* JADX INFO: compiled from: SplineSet.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected b.e.a.a.b f675a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected int[] f676b = new int[10];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected float[] f677c = new float[10];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f678d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f679e;

    /* JADX INFO: compiled from: SplineSet.java */
    static class a extends r {
        a() {
        }

        @Override // androidx.constraintlayout.motion.widget.r
        public void f(View view, float f) {
            view.setAlpha(a(f));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    static class b extends r {
        String f;
        SparseArray<ConstraintAttribute> g;
        float[] h;

        public b(String str, SparseArray<ConstraintAttribute> sparseArray) {
            this.f = str.split(",")[1];
            this.g = sparseArray;
        }

        @Override // androidx.constraintlayout.motion.widget.r
        public void e(int i, float f) {
            throw new RuntimeException("don't call for custom attribute call setPoint(pos, ConstraintAttribute)");
        }

        @Override // androidx.constraintlayout.motion.widget.r
        public void f(View view, float f) {
            this.f675a.e(f, this.h);
            this.g.valueAt(0).i(view, this.h);
        }

        @Override // androidx.constraintlayout.motion.widget.r
        public void h(int i) {
            int size = this.g.size();
            int iF = this.g.valueAt(0).f();
            double[] dArr = new double[size];
            this.h = new float[iF];
            double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) double.class, size, iF);
            for (int i2 = 0; i2 < size; i2++) {
                int iKeyAt = this.g.keyAt(i2);
                ConstraintAttribute constraintAttributeValueAt = this.g.valueAt(i2);
                dArr[i2] = ((double) iKeyAt) * 0.01d;
                constraintAttributeValueAt.e(this.h);
                int i3 = 0;
                while (true) {
                    if (i3 < this.h.length) {
                        dArr2[i2][i3] = r6[i3];
                        i3++;
                    }
                }
            }
            this.f675a = b.e.a.a.b.a(i, dArr, dArr2);
        }

        public void i(int i, ConstraintAttribute constraintAttribute) {
            this.g.append(i, constraintAttribute);
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    static class c extends r {
        c() {
        }

        @Override // androidx.constraintlayout.motion.widget.r
        public void f(View view, float f) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setElevation(a(f));
            }
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    static class d extends r {
        d() {
        }

        @Override // androidx.constraintlayout.motion.widget.r
        public void f(View view, float f) {
        }

        public void i(View view, float f, double d2, double d3) {
            view.setRotation(a(f) + ((float) Math.toDegrees(Math.atan2(d3, d2))));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    static class e extends r {
        e() {
        }

        @Override // androidx.constraintlayout.motion.widget.r
        public void f(View view, float f) {
            view.setPivotX(a(f));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    static class f extends r {
        f() {
        }

        @Override // androidx.constraintlayout.motion.widget.r
        public void f(View view, float f) {
            view.setPivotY(a(f));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    static class g extends r {
        boolean f = false;

        g() {
        }

        @Override // androidx.constraintlayout.motion.widget.r
        public void f(View view, float f) {
            if (view instanceof MotionLayout) {
                ((MotionLayout) view).setProgress(a(f));
                return;
            }
            if (this.f) {
                return;
            }
            Method method = null;
            try {
                method = view.getClass().getMethod("setProgress", Float.TYPE);
            } catch (NoSuchMethodException unused) {
                this.f = true;
            }
            if (method != null) {
                try {
                    method.invoke(view, Float.valueOf(a(f)));
                } catch (IllegalAccessException e2) {
                    Log.e("SplineSet", "unable to setProgress", e2);
                } catch (InvocationTargetException e3) {
                    Log.e("SplineSet", "unable to setProgress", e3);
                }
            }
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    static class h extends r {
        h() {
        }

        @Override // androidx.constraintlayout.motion.widget.r
        public void f(View view, float f) {
            view.setRotation(a(f));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    static class i extends r {
        i() {
        }

        @Override // androidx.constraintlayout.motion.widget.r
        public void f(View view, float f) {
            view.setRotationX(a(f));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    static class j extends r {
        j() {
        }

        @Override // androidx.constraintlayout.motion.widget.r
        public void f(View view, float f) {
            view.setRotationY(a(f));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    static class k extends r {
        k() {
        }

        @Override // androidx.constraintlayout.motion.widget.r
        public void f(View view, float f) {
            view.setScaleX(a(f));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    static class l extends r {
        l() {
        }

        @Override // androidx.constraintlayout.motion.widget.r
        public void f(View view, float f) {
            view.setScaleY(a(f));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    private static class m {
        static void a(int[] iArr, float[] fArr, int i, int i2) {
            int[] iArr2 = new int[iArr.length + 10];
            iArr2[0] = i2;
            iArr2[1] = i;
            int i3 = 2;
            while (i3 > 0) {
                int i4 = i3 - 1;
                int i5 = iArr2[i4];
                i3 = i4 - 1;
                int i6 = iArr2[i3];
                if (i5 < i6) {
                    int iB = b(iArr, fArr, i5, i6);
                    int i7 = i3 + 1;
                    iArr2[i3] = iB - 1;
                    int i8 = i7 + 1;
                    iArr2[i7] = i5;
                    int i9 = i8 + 1;
                    iArr2[i8] = i6;
                    i3 = i9 + 1;
                    iArr2[i9] = iB + 1;
                }
            }
        }

        private static int b(int[] iArr, float[] fArr, int i, int i2) {
            int i3 = iArr[i2];
            int i4 = i;
            while (i < i2) {
                if (iArr[i] <= i3) {
                    c(iArr, fArr, i4, i);
                    i4++;
                }
                i++;
            }
            c(iArr, fArr, i4, i2);
            return i4;
        }

        private static void c(int[] iArr, float[] fArr, int i, int i2) {
            int i3 = iArr[i];
            iArr[i] = iArr[i2];
            iArr[i2] = i3;
            float f = fArr[i];
            fArr[i] = fArr[i2];
            fArr[i2] = f;
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    static class n extends r {
        n() {
        }

        @Override // androidx.constraintlayout.motion.widget.r
        public void f(View view, float f) {
            view.setTranslationX(a(f));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    static class o extends r {
        o() {
        }

        @Override // androidx.constraintlayout.motion.widget.r
        public void f(View view, float f) {
            view.setTranslationY(a(f));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    static class p extends r {
        p() {
        }

        @Override // androidx.constraintlayout.motion.widget.r
        public void f(View view, float f) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setTranslationZ(a(f));
            }
        }
    }

    static r c(String str, SparseArray<ConstraintAttribute> sparseArray) {
        return new b(str, sparseArray);
    }

    static r d(String str) {
        str.hashCode();
        switch (str) {
        }
        return new a();
    }

    public float a(float f2) {
        return (float) this.f675a.c(f2, 0);
    }

    public float b(float f2) {
        return (float) this.f675a.f(f2, 0);
    }

    public void e(int i2, float f2) {
        int[] iArr = this.f676b;
        if (iArr.length < this.f678d + 1) {
            this.f676b = Arrays.copyOf(iArr, iArr.length * 2);
            float[] fArr = this.f677c;
            this.f677c = Arrays.copyOf(fArr, fArr.length * 2);
        }
        int[] iArr2 = this.f676b;
        int i3 = this.f678d;
        iArr2[i3] = i2;
        this.f677c[i3] = f2;
        this.f678d = i3 + 1;
    }

    public abstract void f(View view, float f2);

    public void g(String str) {
        this.f679e = str;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void h(int r10) {
        /*
            r9 = this;
            int r0 = r9.f678d
            if (r0 != 0) goto L5
            return
        L5:
            int[] r1 = r9.f676b
            float[] r2 = r9.f677c
            r3 = 1
            int r0 = r0 - r3
            r4 = 0
            androidx.constraintlayout.motion.widget.r.m.a(r1, r2, r4, r0)
            r0 = r3
            r1 = r0
        L11:
            int r2 = r9.f678d
            if (r0 >= r2) goto L24
            int[] r2 = r9.f676b
            int r5 = r0 + (-1)
            r5 = r2[r5]
            r2 = r2[r0]
            if (r5 == r2) goto L21
            int r1 = r1 + 1
        L21:
            int r0 = r0 + 1
            goto L11
        L24:
            double[] r0 = new double[r1]
            r2 = 2
            int[] r2 = new int[r2]
            r2[r3] = r3
            r2[r4] = r1
            java.lang.Class<double> r1 = double.class
            java.lang.Object r1 = java.lang.reflect.Array.newInstance(r1, r2)
            double[][] r1 = (double[][]) r1
            r2 = r4
            r3 = r2
        L37:
            int r5 = r9.f678d
            if (r2 >= r5) goto L63
            if (r2 <= 0) goto L48
            int[] r5 = r9.f676b
            r6 = r5[r2]
            int r7 = r2 + (-1)
            r5 = r5[r7]
            if (r6 != r5) goto L48
            goto L60
        L48:
            int[] r5 = r9.f676b
            r5 = r5[r2]
            double r5 = (double) r5
            r7 = 4576918229304087675(0x3f847ae147ae147b, double:0.01)
            double r5 = r5 * r7
            r0[r3] = r5
            r5 = r1[r3]
            float[] r6 = r9.f677c
            r6 = r6[r2]
            double r6 = (double) r6
            r5[r4] = r6
            int r3 = r3 + 1
        L60:
            int r2 = r2 + 1
            goto L37
        L63:
            b.e.a.a.b r10 = b.e.a.a.b.a(r10, r0, r1)
            r9.f675a = r10
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.r.h(int):void");
    }

    public String toString() {
        String str = this.f679e;
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        for (int i2 = 0; i2 < this.f678d; i2++) {
            str = str + "[" + this.f676b[i2] + " , " + decimalFormat.format(this.f677c[i2]) + "] ";
        }
        return str;
    }
}
