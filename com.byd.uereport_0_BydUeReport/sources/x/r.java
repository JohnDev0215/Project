package x;

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
public abstract class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public w.b f8997a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int[] f8998b = new int[10];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float[] f8999c = new float[10];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f9000d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f9001e;

    /* JADX INFO: compiled from: SplineSet.java */
    public static class a extends r {
        @Override // x.r
        public void f(View view, float f8) {
            view.setAlpha(a(f8));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class b extends r {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public String f9002f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public SparseArray<ConstraintAttribute> f9003g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public float[] f9004h;

        public b(String str, SparseArray<ConstraintAttribute> sparseArray) {
            this.f9002f = str.split(ListUtils.DEFAULT_JOIN_SEPARATOR)[1];
            this.f9003g = sparseArray;
        }

        @Override // x.r
        public void e(int i8, float f8) {
            throw new RuntimeException("don't call for custom attribute call setPoint(pos, ConstraintAttribute)");
        }

        @Override // x.r
        public void f(View view, float f8) {
            this.f8997a.e(f8, this.f9004h);
            this.f9003g.valueAt(0).i(view, this.f9004h);
        }

        @Override // x.r
        public void h(int i8) {
            int size = this.f9003g.size();
            int iF = this.f9003g.valueAt(0).f();
            double[] dArr = new double[size];
            this.f9004h = new float[iF];
            double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) double.class, size, iF);
            for (int i9 = 0; i9 < size; i9++) {
                int iKeyAt = this.f9003g.keyAt(i9);
                ConstraintAttribute constraintAttributeValueAt = this.f9003g.valueAt(i9);
                dArr[i9] = ((double) iKeyAt) * 0.01d;
                constraintAttributeValueAt.e(this.f9004h);
                int i10 = 0;
                while (true) {
                    if (i10 < this.f9004h.length) {
                        dArr2[i9][i10] = r6[i10];
                        i10++;
                    }
                }
            }
            this.f8997a = w.b.a(i8, dArr, dArr2);
        }

        public void i(int i8, ConstraintAttribute constraintAttribute) {
            this.f9003g.append(i8, constraintAttribute);
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class c extends r {
        @Override // x.r
        public void f(View view, float f8) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setElevation(a(f8));
            }
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class d extends r {
        @Override // x.r
        public void f(View view, float f8) {
        }

        public void i(View view, float f8, double d8, double d9) {
            view.setRotation(a(f8) + ((float) Math.toDegrees(Math.atan2(d9, d8))));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class e extends r {
        @Override // x.r
        public void f(View view, float f8) {
            view.setPivotX(a(f8));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class f extends r {
        @Override // x.r
        public void f(View view, float f8) {
            view.setPivotY(a(f8));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class g extends r {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f9005f = false;

        @Override // x.r
        public void f(View view, float f8) {
            if (view instanceof MotionLayout) {
                ((MotionLayout) view).setProgress(a(f8));
                return;
            }
            if (this.f9005f) {
                return;
            }
            Method method = null;
            try {
                method = view.getClass().getMethod("setProgress", Float.TYPE);
            } catch (NoSuchMethodException unused) {
                this.f9005f = true;
            }
            if (method != null) {
                try {
                    method.invoke(view, Float.valueOf(a(f8)));
                } catch (IllegalAccessException e8) {
                    Log.e("SplineSet", "unable to setProgress", e8);
                } catch (InvocationTargetException e9) {
                    Log.e("SplineSet", "unable to setProgress", e9);
                }
            }
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class h extends r {
        @Override // x.r
        public void f(View view, float f8) {
            view.setRotation(a(f8));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class i extends r {
        @Override // x.r
        public void f(View view, float f8) {
            view.setRotationX(a(f8));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class j extends r {
        @Override // x.r
        public void f(View view, float f8) {
            view.setRotationY(a(f8));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class k extends r {
        @Override // x.r
        public void f(View view, float f8) {
            view.setScaleX(a(f8));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class l extends r {
        @Override // x.r
        public void f(View view, float f8) {
            view.setScaleY(a(f8));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class m {
        public static void a(int[] iArr, float[] fArr, int i8, int i9) {
            int[] iArr2 = new int[iArr.length + 10];
            iArr2[0] = i9;
            iArr2[1] = i8;
            int i10 = 2;
            while (i10 > 0) {
                int i11 = i10 - 1;
                int i12 = iArr2[i11];
                i10 = i11 - 1;
                int i13 = iArr2[i10];
                if (i12 < i13) {
                    int iB = b(iArr, fArr, i12, i13);
                    int i14 = i10 + 1;
                    iArr2[i10] = iB - 1;
                    int i15 = i14 + 1;
                    iArr2[i14] = i12;
                    int i16 = i15 + 1;
                    iArr2[i15] = i13;
                    i10 = i16 + 1;
                    iArr2[i16] = iB + 1;
                }
            }
        }

        public static int b(int[] iArr, float[] fArr, int i8, int i9) {
            int i10 = iArr[i9];
            int i11 = i8;
            while (i8 < i9) {
                if (iArr[i8] <= i10) {
                    c(iArr, fArr, i11, i8);
                    i11++;
                }
                i8++;
            }
            c(iArr, fArr, i11, i9);
            return i11;
        }

        public static void c(int[] iArr, float[] fArr, int i8, int i9) {
            int i10 = iArr[i8];
            iArr[i8] = iArr[i9];
            iArr[i9] = i10;
            float f8 = fArr[i8];
            fArr[i8] = fArr[i9];
            fArr[i9] = f8;
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class n extends r {
        @Override // x.r
        public void f(View view, float f8) {
            view.setTranslationX(a(f8));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class o extends r {
        @Override // x.r
        public void f(View view, float f8) {
            view.setTranslationY(a(f8));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class p extends r {
        @Override // x.r
        public void f(View view, float f8) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setTranslationZ(a(f8));
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

    public float a(float f8) {
        return (float) this.f8997a.c(f8, 0);
    }

    public float b(float f8) {
        return (float) this.f8997a.f(f8, 0);
    }

    public void e(int i8, float f8) {
        int[] iArr = this.f8998b;
        if (iArr.length < this.f9000d + 1) {
            this.f8998b = Arrays.copyOf(iArr, iArr.length * 2);
            float[] fArr = this.f8999c;
            this.f8999c = Arrays.copyOf(fArr, fArr.length * 2);
        }
        int[] iArr2 = this.f8998b;
        int i9 = this.f9000d;
        iArr2[i9] = i8;
        this.f8999c[i9] = f8;
        this.f9000d = i9 + 1;
    }

    public abstract void f(View view, float f8);

    public void g(String str) {
        this.f9001e = str;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void h(int r10) {
        /*
            r9 = this;
            int r0 = r9.f9000d
            if (r0 != 0) goto L5
            return
        L5:
            int[] r1 = r9.f8998b
            float[] r2 = r9.f8999c
            r3 = 1
            int r0 = r0 - r3
            r4 = 0
            x.r.m.a(r1, r2, r4, r0)
            r0 = r3
            r1 = r0
        L11:
            int r2 = r9.f9000d
            if (r0 >= r2) goto L24
            int[] r2 = r9.f8998b
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
            int r5 = r9.f9000d
            if (r2 >= r5) goto L63
            if (r2 <= 0) goto L48
            int[] r5 = r9.f8998b
            r6 = r5[r2]
            int r7 = r2 + (-1)
            r5 = r5[r7]
            if (r6 != r5) goto L48
            goto L60
        L48:
            int[] r5 = r9.f8998b
            r5 = r5[r2]
            double r5 = (double) r5
            r7 = 4576918229304087675(0x3f847ae147ae147b, double:0.01)
            double r5 = r5 * r7
            r0[r3] = r5
            r5 = r1[r3]
            float[] r6 = r9.f8999c
            r6 = r6[r2]
            double r6 = (double) r6
            r5[r4] = r6
            int r3 = r3 + 1
        L60:
            int r2 = r2 + 1
            goto L37
        L63:
            w.b r10 = w.b.a(r10, r0, r1)
            r9.f8997a = r10
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: x.r.h(int):void");
    }

    public String toString() {
        String str = this.f9001e;
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        for (int i8 = 0; i8 < this.f9000d; i8++) {
            str = str + "[" + this.f8998b[i8] + " , " + decimalFormat.format(this.f8999c[i8]) + "] ";
        }
        return str;
    }
}
