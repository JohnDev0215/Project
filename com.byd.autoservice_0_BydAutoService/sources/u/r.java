package u;

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
    public t.b f5285a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int[] f5286b = new int[10];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float[] f5287c = new float[10];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f5288d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f5289e;

    /* JADX INFO: compiled from: SplineSet.java */
    public static class a extends r {
        @Override // u.r
        public void f(View view, float f3) {
            view.setAlpha(a(f3));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class b extends r {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public String f5290f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public SparseArray<ConstraintAttribute> f5291g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public float[] f5292h;

        public b(String str, SparseArray<ConstraintAttribute> sparseArray) {
            this.f5290f = str.split(ListUtils.DEFAULT_JOIN_SEPARATOR)[1];
            this.f5291g = sparseArray;
        }

        @Override // u.r
        public void e(int i3, float f3) {
            throw new RuntimeException("don't call for custom attribute call setPoint(pos, ConstraintAttribute)");
        }

        @Override // u.r
        public void f(View view, float f3) {
            this.f5285a.e(f3, this.f5292h);
            this.f5291g.valueAt(0).i(view, this.f5292h);
        }

        @Override // u.r
        public void h(int i3) {
            int size = this.f5291g.size();
            int iF = this.f5291g.valueAt(0).f();
            double[] dArr = new double[size];
            this.f5292h = new float[iF];
            double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) double.class, size, iF);
            for (int i4 = 0; i4 < size; i4++) {
                int iKeyAt = this.f5291g.keyAt(i4);
                ConstraintAttribute constraintAttributeValueAt = this.f5291g.valueAt(i4);
                dArr[i4] = ((double) iKeyAt) * 0.01d;
                constraintAttributeValueAt.e(this.f5292h);
                int i5 = 0;
                while (true) {
                    if (i5 < this.f5292h.length) {
                        dArr2[i4][i5] = r6[i5];
                        i5++;
                    }
                }
            }
            this.f5285a = t.b.a(i3, dArr, dArr2);
        }

        public void i(int i3, ConstraintAttribute constraintAttribute) {
            this.f5291g.append(i3, constraintAttribute);
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class c extends r {
        @Override // u.r
        public void f(View view, float f3) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setElevation(a(f3));
            }
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class d extends r {
        @Override // u.r
        public void f(View view, float f3) {
        }

        public void i(View view, float f3, double d3, double d4) {
            view.setRotation(a(f3) + ((float) Math.toDegrees(Math.atan2(d4, d3))));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class e extends r {
        @Override // u.r
        public void f(View view, float f3) {
            view.setPivotX(a(f3));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class f extends r {
        @Override // u.r
        public void f(View view, float f3) {
            view.setPivotY(a(f3));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class g extends r {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f5293f = false;

        @Override // u.r
        public void f(View view, float f3) {
            if (view instanceof MotionLayout) {
                ((MotionLayout) view).setProgress(a(f3));
                return;
            }
            if (this.f5293f) {
                return;
            }
            Method method = null;
            try {
                method = view.getClass().getMethod("setProgress", Float.TYPE);
            } catch (NoSuchMethodException unused) {
                this.f5293f = true;
            }
            if (method != null) {
                try {
                    method.invoke(view, Float.valueOf(a(f3)));
                } catch (IllegalAccessException e3) {
                    Log.e("SplineSet", "unable to setProgress", e3);
                } catch (InvocationTargetException e4) {
                    Log.e("SplineSet", "unable to setProgress", e4);
                }
            }
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class h extends r {
        @Override // u.r
        public void f(View view, float f3) {
            view.setRotation(a(f3));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class i extends r {
        @Override // u.r
        public void f(View view, float f3) {
            view.setRotationX(a(f3));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class j extends r {
        @Override // u.r
        public void f(View view, float f3) {
            view.setRotationY(a(f3));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class k extends r {
        @Override // u.r
        public void f(View view, float f3) {
            view.setScaleX(a(f3));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class l extends r {
        @Override // u.r
        public void f(View view, float f3) {
            view.setScaleY(a(f3));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class m {
        public static void a(int[] iArr, float[] fArr, int i3, int i4) {
            int[] iArr2 = new int[iArr.length + 10];
            iArr2[0] = i4;
            iArr2[1] = i3;
            int i5 = 2;
            while (i5 > 0) {
                int i6 = i5 - 1;
                int i7 = iArr2[i6];
                i5 = i6 - 1;
                int i8 = iArr2[i5];
                if (i7 < i8) {
                    int iB = b(iArr, fArr, i7, i8);
                    int i9 = i5 + 1;
                    iArr2[i5] = iB - 1;
                    int i10 = i9 + 1;
                    iArr2[i9] = i7;
                    int i11 = i10 + 1;
                    iArr2[i10] = i8;
                    i5 = i11 + 1;
                    iArr2[i11] = iB + 1;
                }
            }
        }

        public static int b(int[] iArr, float[] fArr, int i3, int i4) {
            int i5 = iArr[i4];
            int i6 = i3;
            while (i3 < i4) {
                if (iArr[i3] <= i5) {
                    c(iArr, fArr, i6, i3);
                    i6++;
                }
                i3++;
            }
            c(iArr, fArr, i6, i4);
            return i6;
        }

        public static void c(int[] iArr, float[] fArr, int i3, int i4) {
            int i5 = iArr[i3];
            iArr[i3] = iArr[i4];
            iArr[i4] = i5;
            float f3 = fArr[i3];
            fArr[i3] = fArr[i4];
            fArr[i4] = f3;
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class n extends r {
        @Override // u.r
        public void f(View view, float f3) {
            view.setTranslationX(a(f3));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class o extends r {
        @Override // u.r
        public void f(View view, float f3) {
            view.setTranslationY(a(f3));
        }
    }

    /* JADX INFO: compiled from: SplineSet.java */
    public static class p extends r {
        @Override // u.r
        public void f(View view, float f3) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setTranslationZ(a(f3));
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

    public float a(float f3) {
        return (float) this.f5285a.c(f3, 0);
    }

    public float b(float f3) {
        return (float) this.f5285a.f(f3, 0);
    }

    public void e(int i3, float f3) {
        int[] iArr = this.f5286b;
        if (iArr.length < this.f5288d + 1) {
            this.f5286b = Arrays.copyOf(iArr, iArr.length * 2);
            float[] fArr = this.f5287c;
            this.f5287c = Arrays.copyOf(fArr, fArr.length * 2);
        }
        int[] iArr2 = this.f5286b;
        int i4 = this.f5288d;
        iArr2[i4] = i3;
        this.f5287c[i4] = f3;
        this.f5288d = i4 + 1;
    }

    public abstract void f(View view, float f3);

    public void g(String str) {
        this.f5289e = str;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void h(int r10) {
        /*
            r9 = this;
            int r0 = r9.f5288d
            if (r0 != 0) goto L5
            return
        L5:
            int[] r1 = r9.f5286b
            float[] r2 = r9.f5287c
            r3 = 1
            int r0 = r0 - r3
            r4 = 0
            u.r.m.a(r1, r2, r4, r0)
            r0 = r3
            r1 = r0
        L11:
            int r2 = r9.f5288d
            if (r0 >= r2) goto L24
            int[] r2 = r9.f5286b
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
            int r5 = r9.f5288d
            if (r2 >= r5) goto L63
            if (r2 <= 0) goto L48
            int[] r5 = r9.f5286b
            r6 = r5[r2]
            int r7 = r2 + (-1)
            r5 = r5[r7]
            if (r6 != r5) goto L48
            goto L60
        L48:
            int[] r5 = r9.f5286b
            r5 = r5[r2]
            double r5 = (double) r5
            r7 = 4576918229304087675(0x3f847ae147ae147b, double:0.01)
            double r5 = r5 * r7
            r0[r3] = r5
            r5 = r1[r3]
            float[] r6 = r9.f5287c
            r6 = r6[r2]
            double r6 = (double) r6
            r5[r4] = r6
            int r3 = r3 + 1
        L60:
            int r2 = r2 + 1
            goto L37
        L63:
            t.b r10 = t.b.a(r10, r0, r1)
            r9.f5285a = r10
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: u.r.h(int):void");
    }

    public String toString() {
        String str = this.f5289e;
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        for (int i3 = 0; i3 < this.f5288d; i3++) {
            str = str + "[" + this.f5286b[i3] + " , " + decimalFormat.format(this.f5287c[i3]) + "] ";
        }
        return str;
    }
}
