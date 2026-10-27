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

/* JADX INFO: compiled from: TimeCycleSplineSet.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class i5 {
    public static float a = 6.2831855f;
    public j4 b;
    public int f;
    public String g;
    public long j;
    public int c = 0;
    public int[] d = new int[10];
    public float[][] e = (float[][]) Array.newInstance((Class<?>) float.class, 10, 3);
    public float[] h = new float[3];
    public boolean i = false;
    public float k = Float.NaN;

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class a extends i5 {
        @Override // defpackage.i5
        public boolean f(View view, float f, long j, u4 u4Var) {
            view.setAlpha(b(f, j, view, u4Var));
            return this.i;
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class b extends i5 {
        public String l;
        public SparseArray<ConstraintAttribute> m;
        public SparseArray<float[]> n = new SparseArray<>();
        public float[] o;
        public float[] p;

        public b(String str, SparseArray<ConstraintAttribute> sparseArray) {
            this.l = str.split(ListUtils.DEFAULT_JOIN_SEPARATOR)[1];
            this.m = sparseArray;
        }

        @Override // defpackage.i5
        public void e(int i, float f, float f2, int i2, float f3) {
            throw new RuntimeException("don't call for custom attribute call setPoint(pos, ConstraintAttribute,...)");
        }

        @Override // defpackage.i5
        public boolean f(View view, float f, long j, u4 u4Var) {
            this.b.e(f, this.o);
            float[] fArr = this.o;
            float f2 = fArr[fArr.length - 2];
            float f3 = fArr[fArr.length - 1];
            float f4 = (float) ((((double) this.k) + (((j - this.j) * 1.0E-9d) * ((double) f2))) % 1.0d);
            this.k = f4;
            this.j = j;
            float fA = a(f4);
            this.i = false;
            int i = 0;
            while (true) {
                float[] fArr2 = this.p;
                if (i >= fArr2.length) {
                    break;
                }
                boolean z = this.i;
                float[] fArr3 = this.o;
                this.i = z | (((double) fArr3[i]) != 0.0d);
                fArr2[i] = (fArr3[i] * fA) + f3;
                i++;
            }
            this.m.valueAt(0).i(view, this.p);
            if (f2 != 0.0f) {
                this.i = true;
            }
            return this.i;
        }

        @Override // defpackage.i5
        public void i(int i) {
            int size = this.m.size();
            int iF = this.m.valueAt(0).f();
            double[] dArr = new double[size];
            int i2 = iF + 2;
            this.o = new float[i2];
            this.p = new float[iF];
            double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) double.class, size, i2);
            for (int i3 = 0; i3 < size; i3++) {
                int iKeyAt = this.m.keyAt(i3);
                ConstraintAttribute constraintAttributeValueAt = this.m.valueAt(i3);
                float[] fArrValueAt = this.n.valueAt(i3);
                dArr[i3] = ((double) iKeyAt) * 0.01d;
                constraintAttributeValueAt.e(this.o);
                int i4 = 0;
                while (true) {
                    if (i4 < this.o.length) {
                        dArr2[i3][i4] = r8[i4];
                        i4++;
                    }
                }
                dArr2[i3][iF] = fArrValueAt[0];
                dArr2[i3][iF + 1] = fArrValueAt[1];
            }
            this.b = j4.a(i, dArr, dArr2);
        }

        public void j(int i, ConstraintAttribute constraintAttribute, float f, int i2, float f2) {
            this.m.append(i, constraintAttribute);
            this.n.append(i, new float[]{f, f2});
            this.c = Math.max(this.c, i2);
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class c extends i5 {
        @Override // defpackage.i5
        public boolean f(View view, float f, long j, u4 u4Var) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setElevation(b(f, j, view, u4Var));
            }
            return this.i;
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class d extends i5 {
        @Override // defpackage.i5
        public boolean f(View view, float f, long j, u4 u4Var) {
            return this.i;
        }

        public boolean j(View view, u4 u4Var, float f, long j, double d, double d2) {
            view.setRotation(b(f, j, view, u4Var) + ((float) Math.toDegrees(Math.atan2(d2, d))));
            return this.i;
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class e extends i5 {
        public boolean l = false;

        @Override // defpackage.i5
        public boolean f(View view, float f, long j, u4 u4Var) {
            if (view instanceof MotionLayout) {
                ((MotionLayout) view).setProgress(b(f, j, view, u4Var));
            } else {
                if (this.l) {
                    return false;
                }
                Method method = null;
                try {
                    method = view.getClass().getMethod("setProgress", Float.TYPE);
                } catch (NoSuchMethodException unused) {
                    this.l = true;
                }
                Method method2 = method;
                if (method2 != null) {
                    try {
                        method2.invoke(view, Float.valueOf(b(f, j, view, u4Var)));
                    } catch (IllegalAccessException e) {
                        Log.e("SplineSet", "unable to setProgress", e);
                    } catch (InvocationTargetException e2) {
                        Log.e("SplineSet", "unable to setProgress", e2);
                    }
                }
            }
            return this.i;
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class f extends i5 {
        @Override // defpackage.i5
        public boolean f(View view, float f, long j, u4 u4Var) {
            view.setRotation(b(f, j, view, u4Var));
            return this.i;
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class g extends i5 {
        @Override // defpackage.i5
        public boolean f(View view, float f, long j, u4 u4Var) {
            view.setRotationX(b(f, j, view, u4Var));
            return this.i;
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class h extends i5 {
        @Override // defpackage.i5
        public boolean f(View view, float f, long j, u4 u4Var) {
            view.setRotationY(b(f, j, view, u4Var));
            return this.i;
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class i extends i5 {
        @Override // defpackage.i5
        public boolean f(View view, float f, long j, u4 u4Var) {
            view.setScaleX(b(f, j, view, u4Var));
            return this.i;
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class j extends i5 {
        @Override // defpackage.i5
        public boolean f(View view, float f, long j, u4 u4Var) {
            view.setScaleY(b(f, j, view, u4Var));
            return this.i;
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class k {
        public static void a(int[] iArr, float[][] fArr, int i, int i2) {
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

        public static int b(int[] iArr, float[][] fArr, int i, int i2) {
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

        public static void c(int[] iArr, float[][] fArr, int i, int i2) {
            int i3 = iArr[i];
            iArr[i] = iArr[i2];
            iArr[i2] = i3;
            float[] fArr2 = fArr[i];
            fArr[i] = fArr[i2];
            fArr[i2] = fArr2;
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class l extends i5 {
        @Override // defpackage.i5
        public boolean f(View view, float f, long j, u4 u4Var) {
            view.setTranslationX(b(f, j, view, u4Var));
            return this.i;
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class m extends i5 {
        @Override // defpackage.i5
        public boolean f(View view, float f, long j, u4 u4Var) {
            view.setTranslationY(b(f, j, view, u4Var));
            return this.i;
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class n extends i5 {
        @Override // defpackage.i5
        public boolean f(View view, float f, long j, u4 u4Var) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setTranslationZ(b(f, j, view, u4Var));
            }
            return this.i;
        }
    }

    public static i5 c(String str, SparseArray<ConstraintAttribute> sparseArray) {
        return new b(str, sparseArray);
    }

    public static i5 d(String str, long j2) {
        i5 gVar;
        str.hashCode();
        switch (str) {
            case "rotationX":
                gVar = new g();
                break;
            case "rotationY":
                gVar = new h();
                break;
            case "translationX":
                gVar = new l();
                break;
            case "translationY":
                gVar = new m();
                break;
            case "translationZ":
                gVar = new n();
                break;
            case "progress":
                gVar = new e();
                break;
            case "scaleX":
                gVar = new i();
                break;
            case "scaleY":
                gVar = new j();
                break;
            case "rotation":
                gVar = new f();
                break;
            case "elevation":
                gVar = new c();
                break;
            case "transitionPathRotate":
                gVar = new d();
                break;
            case "alpha":
                gVar = new a();
                break;
            default:
                return null;
        }
        gVar.g(j2);
        return gVar;
    }

    public float a(float f2) {
        float fAbs;
        switch (this.c) {
            case 1:
                return Math.signum(f2 * a);
            case 2:
                fAbs = Math.abs(f2);
                break;
            case 3:
                return (((f2 * 2.0f) + 1.0f) % 2.0f) - 1.0f;
            case 4:
                fAbs = ((f2 * 2.0f) + 1.0f) % 2.0f;
                break;
            case 5:
                return (float) Math.cos(f2 * a);
            case 6:
                float fAbs2 = 1.0f - Math.abs(((f2 * 4.0f) % 4.0f) - 2.0f);
                fAbs = fAbs2 * fAbs2;
                break;
            default:
                return (float) Math.sin(f2 * a);
        }
        return 1.0f - fAbs;
    }

    public float b(float f2, long j2, View view, u4 u4Var) {
        this.b.e(f2, this.h);
        float[] fArr = this.h;
        float f3 = fArr[1];
        if (f3 == 0.0f) {
            this.i = false;
            return fArr[2];
        }
        if (Float.isNaN(this.k)) {
            float fA = u4Var.a(view, this.g, 0);
            this.k = fA;
            if (Float.isNaN(fA)) {
                this.k = 0.0f;
            }
        }
        float f4 = (float) ((((double) this.k) + (((j2 - this.j) * 1.0E-9d) * ((double) f3))) % 1.0d);
        this.k = f4;
        u4Var.b(view, this.g, 0, f4);
        this.j = j2;
        float f5 = this.h[0];
        float fA2 = (a(this.k) * f5) + this.h[2];
        this.i = (f5 == 0.0f && f3 == 0.0f) ? false : true;
        return fA2;
    }

    public void e(int i2, float f2, float f3, int i3, float f4) {
        int[] iArr = this.d;
        int i4 = this.f;
        iArr[i4] = i2;
        float[][] fArr = this.e;
        fArr[i4][0] = f2;
        fArr[i4][1] = f3;
        fArr[i4][2] = f4;
        this.c = Math.max(this.c, i3);
        this.f++;
    }

    public abstract boolean f(View view, float f2, long j2, u4 u4Var);

    public void g(long j2) {
        this.j = j2;
    }

    public void h(String str) {
        this.g = str;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0063  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void i(int r12) {
        /*
            r11 = this;
            int r0 = r11.f
            if (r0 != 0) goto L1d
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r0 = "Error no points added to "
            r12.append(r0)
            java.lang.String r0 = r11.g
            r12.append(r0)
            java.lang.String r12 = r12.toString()
            java.lang.String r0 = "SplineSet"
            android.util.Log.e(r0, r12)
            return
        L1d:
            int[] r1 = r11.d
            float[][] r2 = r11.e
            r3 = 1
            int r0 = r0 - r3
            r4 = 0
            i5.k.a(r1, r2, r4, r0)
            r0 = r3
            r1 = r4
        L29:
            int[] r2 = r11.d
            int r5 = r2.length
            if (r0 >= r5) goto L3b
            r5 = r2[r0]
            int r6 = r0 + (-1)
            r2 = r2[r6]
            if (r5 == r2) goto L38
            int r1 = r1 + 1
        L38:
            int r0 = r0 + 1
            goto L29
        L3b:
            if (r1 != 0) goto L3e
            r1 = r3
        L3e:
            double[] r0 = new double[r1]
            r2 = 3
            r5 = 2
            int[] r6 = new int[r5]
            r6[r3] = r2
            r6[r4] = r1
            java.lang.Class<double> r1 = double.class
            java.lang.Object r1 = java.lang.reflect.Array.newInstance(r1, r6)
            double[][] r1 = (double[][]) r1
            r2 = r4
            r6 = r2
        L52:
            int r7 = r11.f
            if (r2 >= r7) goto L92
            if (r2 <= 0) goto L63
            int[] r7 = r11.d
            r8 = r7[r2]
            int r9 = r2 + (-1)
            r7 = r7[r9]
            if (r8 != r7) goto L63
            goto L8f
        L63:
            int[] r7 = r11.d
            r7 = r7[r2]
            double r7 = (double) r7
            r9 = 4576918229304087675(0x3f847ae147ae147b, double:0.01)
            double r7 = r7 * r9
            r0[r6] = r7
            r7 = r1[r6]
            float[][] r8 = r11.e
            r9 = r8[r2]
            r9 = r9[r4]
            double r9 = (double) r9
            r7[r4] = r9
            r7 = r1[r6]
            r9 = r8[r2]
            r9 = r9[r3]
            double r9 = (double) r9
            r7[r3] = r9
            r7 = r1[r6]
            r8 = r8[r2]
            r8 = r8[r5]
            double r8 = (double) r8
            r7[r5] = r8
            int r6 = r6 + 1
        L8f:
            int r2 = r2 + 1
            goto L52
        L92:
            j4 r12 = defpackage.j4.a(r12, r0, r1)
            r11.b = r12
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.i5.i(int):void");
    }

    public String toString() {
        String str = this.g;
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        for (int i2 = 0; i2 < this.f; i2++) {
            str = str + "[" + this.d[i2] + " , " + decimalFormat.format(this.e[i2]) + "] ";
        }
        return str;
    }
}
