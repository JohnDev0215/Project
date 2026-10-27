package b.e.a.b;

import android.os.Build;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import androidx.constraintlayout.motion.widget.MotionLayout;
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
    public b.e.a.a.b f1327a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int[] f1328b = new int[10];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float[] f1329c = new float[10];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1330d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f1331e;

    /* JADX INFO: compiled from: SplineSet.java */
    public static class a extends r {
        @Override // b.e.a.b.r
        public void f(View view, float f2) {
            view.setAlpha(a(f2));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class b extends r {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public String f1332f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public SparseArray<ConstraintAttribute> f1333g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public float[] f1334h;

        public b(String str, SparseArray<ConstraintAttribute> sparseArray) {
            this.f1332f = str.split(",")[1];
            this.f1333g = sparseArray;
        }

        @Override // b.e.a.b.r
        public void e(int i2, float f2) {
            throw new RuntimeException("don't call for custom attribute call setPoint(pos, ConstraintAttribute)");
        }

        @Override // b.e.a.b.r
        public void f(View view, float f2) {
            this.f1327a.e(f2, this.f1334h);
            this.f1333g.valueAt(0).i(view, this.f1334h);
        }

        @Override // b.e.a.b.r
        public void h(int i2) {
            int size = this.f1333g.size();
            int iF = this.f1333g.valueAt(0).f();
            double[] dArr = new double[size];
            this.f1334h = new float[iF];
            double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) double.class, size, iF);
            for (int i3 = 0; i3 < size; i3++) {
                int iKeyAt = this.f1333g.keyAt(i3);
                ConstraintAttribute constraintAttributeValueAt = this.f1333g.valueAt(i3);
                dArr[i3] = ((double) iKeyAt) * 0.01d;
                constraintAttributeValueAt.e(this.f1334h);
                int i4 = 0;
                while (true) {
                    if (i4 < this.f1334h.length) {
                        dArr2[i3][i4] = r6[i4];
                        i4++;
                    }
                }
            }
            this.f1327a = b.e.a.a.b.a(i2, dArr, dArr2);
        }

        public void i(int i2, ConstraintAttribute constraintAttribute) {
            this.f1333g.append(i2, constraintAttribute);
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class c extends r {
        @Override // b.e.a.b.r
        public void f(View view, float f2) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setElevation(a(f2));
            }
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class d extends r {
        @Override // b.e.a.b.r
        public void f(View view, float f2) {
        }

        public void i(View view, float f2, double d2, double d3) {
            view.setRotation(a(f2) + ((float) Math.toDegrees(Math.atan2(d3, d2))));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class e extends r {
        @Override // b.e.a.b.r
        public void f(View view, float f2) {
            view.setPivotX(a(f2));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class f extends r {
        @Override // b.e.a.b.r
        public void f(View view, float f2) {
            view.setPivotY(a(f2));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class g extends r {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f1335f = false;

        @Override // b.e.a.b.r
        public void f(View view, float f2) {
            if (view instanceof MotionLayout) {
                ((MotionLayout) view).setProgress(a(f2));
                return;
            }
            if (this.f1335f) {
                return;
            }
            Method method = null;
            try {
                method = view.getClass().getMethod("setProgress", Float.TYPE);
            } catch (NoSuchMethodException unused) {
                this.f1335f = true;
            }
            if (method != null) {
                try {
                    method.invoke(view, Float.valueOf(a(f2)));
                } catch (IllegalAccessException e2) {
                    Log.e("SplineSet", "unable to setProgress", e2);
                } catch (InvocationTargetException e3) {
                    Log.e("SplineSet", "unable to setProgress", e3);
                }
            }
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class h extends r {
        @Override // b.e.a.b.r
        public void f(View view, float f2) {
            view.setRotation(a(f2));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class i extends r {
        @Override // b.e.a.b.r
        public void f(View view, float f2) {
            view.setRotationX(a(f2));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class j extends r {
        @Override // b.e.a.b.r
        public void f(View view, float f2) {
            view.setRotationY(a(f2));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class k extends r {
        @Override // b.e.a.b.r
        public void f(View view, float f2) {
            view.setScaleX(a(f2));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class l extends r {
        @Override // b.e.a.b.r
        public void f(View view, float f2) {
            view.setScaleY(a(f2));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class m {
        public static void a(int[] iArr, float[] fArr, int i2, int i3) {
            int[] iArr2 = new int[iArr.length + 10];
            iArr2[0] = i3;
            iArr2[1] = i2;
            int i4 = 2;
            while (i4 > 0) {
                int i5 = i4 - 1;
                int i6 = iArr2[i5];
                i4 = i5 - 1;
                int i7 = iArr2[i4];
                if (i6 < i7) {
                    int iB = b(iArr, fArr, i6, i7);
                    int i8 = i4 + 1;
                    iArr2[i4] = iB - 1;
                    int i9 = i8 + 1;
                    iArr2[i8] = i6;
                    int i10 = i9 + 1;
                    iArr2[i9] = i7;
                    i4 = i10 + 1;
                    iArr2[i10] = iB + 1;
                }
            }
        }

        public static int b(int[] iArr, float[] fArr, int i2, int i3) {
            int i4 = iArr[i3];
            int i5 = i2;
            while (i2 < i3) {
                if (iArr[i2] <= i4) {
                    c(iArr, fArr, i5, i2);
                    i5++;
                }
                i2++;
            }
            c(iArr, fArr, i5, i3);
            return i5;
        }

        public static void c(int[] iArr, float[] fArr, int i2, int i3) {
            int i4 = iArr[i2];
            iArr[i2] = iArr[i3];
            iArr[i3] = i4;
            float f2 = fArr[i2];
            fArr[i2] = fArr[i3];
            fArr[i3] = f2;
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class n extends r {
        @Override // b.e.a.b.r
        public void f(View view, float f2) {
            view.setTranslationX(a(f2));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class o extends r {
        @Override // b.e.a.b.r
        public void f(View view, float f2) {
            view.setTranslationY(a(f2));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class p extends r {
        @Override // b.e.a.b.r
        public void f(View view, float f2) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setTranslationZ(a(f2));
            }
        }
    }

    public static r c(String str, SparseArray<ConstraintAttribute> sparseArray) {
        return new b(str, sparseArray);
    }

    public static r d(String str) {
        str.hashCode();
        switch (str) {
        }
        return new a();
    }

    public float a(float f2) {
        return (float) this.f1327a.c(f2, 0);
    }

    public float b(float f2) {
        return (float) this.f1327a.f(f2, 0);
    }

    public void e(int i2, float f2) {
        int[] iArr = this.f1328b;
        if (iArr.length < this.f1330d + 1) {
            this.f1328b = Arrays.copyOf(iArr, iArr.length * 2);
            float[] fArr = this.f1329c;
            this.f1329c = Arrays.copyOf(fArr, fArr.length * 2);
        }
        int[] iArr2 = this.f1328b;
        int i3 = this.f1330d;
        iArr2[i3] = i2;
        this.f1329c[i3] = f2;
        this.f1330d = i3 + 1;
    }

    public abstract void f(View view, float f2);

    public void g(String str) {
        this.f1331e = str;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void h(int r10) {
        /*
            r9 = this;
            int r0 = r9.f1330d
            if (r0 != 0) goto L5
            return
        L5:
            int[] r1 = r9.f1328b
            float[] r2 = r9.f1329c
            r3 = 1
            int r0 = r0 - r3
            r4 = 0
            b.e.a.b.r.m.a(r1, r2, r4, r0)
            r0 = r3
            r1 = r0
        L11:
            int r2 = r9.f1330d
            if (r0 >= r2) goto L24
            int[] r2 = r9.f1328b
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
            int r5 = r9.f1330d
            if (r2 >= r5) goto L63
            if (r2 <= 0) goto L48
            int[] r5 = r9.f1328b
            r6 = r5[r2]
            int r7 = r2 + (-1)
            r5 = r5[r7]
            if (r6 != r5) goto L48
            goto L60
        L48:
            int[] r5 = r9.f1328b
            r5 = r5[r2]
            double r5 = (double) r5
            r7 = 4576918229304087675(0x3f847ae147ae147b, double:0.01)
            double r5 = r5 * r7
            r0[r3] = r5
            r5 = r1[r3]
            float[] r6 = r9.f1329c
            r6 = r6[r2]
            double r6 = (double) r6
            r5[r4] = r6
            int r3 = r3 + 1
        L60:
            int r2 = r2 + 1
            goto L37
        L63:
            b.e.a.a.b r10 = b.e.a.a.b.a(r10, r0, r1)
            r9.f1327a = r10
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: b.e.a.b.r.h(int):void");
    }

    public String toString() {
        String str = this.f1331e;
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        for (int i2 = 0; i2 < this.f1330d; i2++) {
            str = str + "[" + this.f1328b[i2] + " , " + decimalFormat.format(this.f1329c[i2]) + "] ";
        }
        return str;
    }
}
