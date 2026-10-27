package defpackage;

import android.os.Build;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintAttribute;
import com.bydux.api.common.tools.util.ListUtils;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.Arrays;

/* JADX INFO: compiled from: SplineSet.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class h5 {
    public j4 a;
    public int[] b = new int[10];
    public float[] c = new float[10];
    public int d;
    public String e;

    /* JADX INFO: compiled from: SplineSet.java */
    public static class a extends h5 {
        @Override // defpackage.h5
        public void f(View view, float f) {
            view.setAlpha(a(f));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class b extends h5 {
        public String f;
        public SparseArray<ConstraintAttribute> g;
        public float[] h;

        public b(String str, SparseArray<ConstraintAttribute> sparseArray) {
            this.f = str.split(ListUtils.DEFAULT_JOIN_SEPARATOR)[1];
            this.g = sparseArray;
        }

        @Override // defpackage.h5
        public void e(int i, float f) {
            throw new RuntimeException("don't call for custom attribute call setPoint(pos, ConstraintAttribute)");
        }

        @Override // defpackage.h5
        public void f(View view, float f) {
            this.a.e(f, this.h);
            this.g.valueAt(0).i(view, this.h);
        }

        @Override // defpackage.h5
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
            this.a = j4.a(i, dArr, dArr2);
        }

        public void i(int i, ConstraintAttribute constraintAttribute) {
            this.g.append(i, constraintAttribute);
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class c extends h5 {
        @Override // defpackage.h5
        public void f(View view, float f) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setElevation(a(f));
            }
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class d extends h5 {
        @Override // defpackage.h5
        public void f(View view, float f) {
        }

        public void i(View view, float f, double d, double d2) {
            view.setRotation(a(f) + ((float) Math.toDegrees(Math.atan2(d2, d))));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class e extends h5 {
        @Override // defpackage.h5
        public void f(View view, float f) {
            view.setPivotX(a(f));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class f extends h5 {
        @Override // defpackage.h5
        public void f(View view, float f) {
            view.setPivotY(a(f));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class g extends h5 {
        public boolean f = false;

        @Override // defpackage.h5
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
                } catch (IllegalAccessException e) {
                    Log.e("SplineSet", "unable to setProgress", e);
                } catch (InvocationTargetException e2) {
                    Log.e("SplineSet", "unable to setProgress", e2);
                }
            }
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class h extends h5 {
        @Override // defpackage.h5
        public void f(View view, float f) {
            view.setRotation(a(f));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class i extends h5 {
        @Override // defpackage.h5
        public void f(View view, float f) {
            view.setRotationX(a(f));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class j extends h5 {
        @Override // defpackage.h5
        public void f(View view, float f) {
            view.setRotationY(a(f));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class k extends h5 {
        @Override // defpackage.h5
        public void f(View view, float f) {
            view.setScaleX(a(f));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class l extends h5 {
        @Override // defpackage.h5
        public void f(View view, float f) {
            view.setScaleY(a(f));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class m {
        public static void a(int[] iArr, float[] fArr, int i, int i2) {
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

        public static int b(int[] iArr, float[] fArr, int i, int i2) {
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

        public static void c(int[] iArr, float[] fArr, int i, int i2) {
            int i3 = iArr[i];
            iArr[i] = iArr[i2];
            iArr[i2] = i3;
            float f = fArr[i];
            fArr[i] = fArr[i2];
            fArr[i2] = f;
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class n extends h5 {
        @Override // defpackage.h5
        public void f(View view, float f) {
            view.setTranslationX(a(f));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class o extends h5 {
        @Override // defpackage.h5
        public void f(View view, float f) {
            view.setTranslationY(a(f));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class p extends h5 {
        @Override // defpackage.h5
        public void f(View view, float f) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setTranslationZ(a(f));
            }
        }
    }

    public static h5 c(String str, SparseArray<ConstraintAttribute> sparseArray) {
        return new b(str, sparseArray);
    }

    public static h5 d(String str) {
        str.hashCode();
        switch (str) {
        }
        return new a();
    }

    public float a(float f2) {
        return (float) this.a.c(f2, 0);
    }

    public float b(float f2) {
        return (float) this.a.f(f2, 0);
    }

    public void e(int i2, float f2) {
        int[] iArr = this.b;
        if (iArr.length < this.d + 1) {
            this.b = Arrays.copyOf(iArr, iArr.length * 2);
            float[] fArr = this.c;
            this.c = Arrays.copyOf(fArr, fArr.length * 2);
        }
        int[] iArr2 = this.b;
        int i3 = this.d;
        iArr2[i3] = i2;
        this.c[i3] = f2;
        this.d = i3 + 1;
    }

    public abstract void f(View view, float f2);

    public void g(String str) {
        this.e = str;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void h(int r10) {
        /*
            r9 = this;
            int r0 = r9.d
            if (r0 != 0) goto L5
            return
        L5:
            int[] r1 = r9.b
            float[] r2 = r9.c
            r3 = 1
            int r0 = r0 - r3
            r4 = 0
            h5.m.a(r1, r2, r4, r0)
            r0 = r3
            r1 = r0
        L11:
            int r2 = r9.d
            if (r0 >= r2) goto L24
            int[] r2 = r9.b
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
            int r5 = r9.d
            if (r2 >= r5) goto L63
            if (r2 <= 0) goto L48
            int[] r5 = r9.b
            r6 = r5[r2]
            int r7 = r2 + (-1)
            r5 = r5[r7]
            if (r6 != r5) goto L48
            goto L60
        L48:
            int[] r5 = r9.b
            r5 = r5[r2]
            double r5 = (double) r5
            r7 = 4576918229304087675(0x3f847ae147ae147b, double:0.01)
            double r5 = r5 * r7
            r0[r3] = r5
            r5 = r1[r3]
            float[] r6 = r9.c
            r6 = r6[r2]
            double r6 = (double) r6
            r5[r4] = r6
            int r3 = r3 + 1
        L60:
            int r2 = r2 + 1
            goto L37
        L63:
            j4 r10 = defpackage.j4.a(r10, r0, r1)
            r9.a = r10
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.h5.h(int):void");
    }

    public String toString() {
        String str = this.e;
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        for (int i2 = 0; i2 < this.d; i2++) {
            str = str + "[" + this.b[i2] + " , " + decimalFormat.format(this.c[i2]) + "] ";
        }
        return str;
    }
}
