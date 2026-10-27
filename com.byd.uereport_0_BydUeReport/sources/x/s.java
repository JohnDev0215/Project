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

/* JADX INFO: compiled from: TimeCycleSplineSet.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static float f9006a = 6.2831855f;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public w.b f9007b;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f9011f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public String f9012g;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public long f9015j;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f9008c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int[] f9009d = new int[10];

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float[][] f9010e = (float[][]) Array.newInstance((Class<?>) float.class, 10, 3);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float[] f9013h = new float[3];

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f9014i = false;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f9016k = Float.NaN;

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class a extends s {
        @Override // x.s
        public boolean f(View view, float f8, long j8, x.e eVar) {
            view.setAlpha(b(f8, j8, view, eVar));
            return this.f9014i;
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class b extends s {

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public String f9017l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public SparseArray<ConstraintAttribute> f9018m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public SparseArray<float[]> f9019n = new SparseArray<>();

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public float[] f9020o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public float[] f9021p;

        public b(String str, SparseArray<ConstraintAttribute> sparseArray) {
            this.f9017l = str.split(ListUtils.DEFAULT_JOIN_SEPARATOR)[1];
            this.f9018m = sparseArray;
        }

        @Override // x.s
        public void e(int i8, float f8, float f9, int i9, float f10) {
            throw new RuntimeException("don't call for custom attribute call setPoint(pos, ConstraintAttribute,...)");
        }

        @Override // x.s
        public boolean f(View view, float f8, long j8, x.e eVar) {
            this.f9007b.e(f8, this.f9020o);
            float[] fArr = this.f9020o;
            float f9 = fArr[fArr.length - 2];
            float f10 = fArr[fArr.length - 1];
            float f11 = (float) ((((double) this.f9016k) + (((j8 - this.f9015j) * 1.0E-9d) * ((double) f9))) % 1.0d);
            this.f9016k = f11;
            this.f9015j = j8;
            float fA = a(f11);
            this.f9014i = false;
            int i8 = 0;
            while (true) {
                float[] fArr2 = this.f9021p;
                if (i8 >= fArr2.length) {
                    break;
                }
                boolean z7 = this.f9014i;
                float[] fArr3 = this.f9020o;
                this.f9014i = z7 | (((double) fArr3[i8]) != 0.0d);
                fArr2[i8] = (fArr3[i8] * fA) + f10;
                i8++;
            }
            this.f9018m.valueAt(0).i(view, this.f9021p);
            if (f9 != 0.0f) {
                this.f9014i = true;
            }
            return this.f9014i;
        }

        @Override // x.s
        public void i(int i8) {
            int size = this.f9018m.size();
            int iF = this.f9018m.valueAt(0).f();
            double[] dArr = new double[size];
            int i9 = iF + 2;
            this.f9020o = new float[i9];
            this.f9021p = new float[iF];
            double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) double.class, size, i9);
            for (int i10 = 0; i10 < size; i10++) {
                int iKeyAt = this.f9018m.keyAt(i10);
                ConstraintAttribute constraintAttributeValueAt = this.f9018m.valueAt(i10);
                float[] fArrValueAt = this.f9019n.valueAt(i10);
                dArr[i10] = ((double) iKeyAt) * 0.01d;
                constraintAttributeValueAt.e(this.f9020o);
                int i11 = 0;
                while (true) {
                    if (i11 < this.f9020o.length) {
                        dArr2[i10][i11] = r8[i11];
                        i11++;
                    }
                }
                dArr2[i10][iF] = fArrValueAt[0];
                dArr2[i10][iF + 1] = fArrValueAt[1];
            }
            this.f9007b = w.b.a(i8, dArr, dArr2);
        }

        public void j(int i8, ConstraintAttribute constraintAttribute, float f8, int i9, float f9) {
            this.f9018m.append(i8, constraintAttribute);
            this.f9019n.append(i8, new float[]{f8, f9});
            this.f9008c = Math.max(this.f9008c, i9);
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class c extends s {
        @Override // x.s
        public boolean f(View view, float f8, long j8, x.e eVar) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setElevation(b(f8, j8, view, eVar));
            }
            return this.f9014i;
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class d extends s {
        @Override // x.s
        public boolean f(View view, float f8, long j8, x.e eVar) {
            return this.f9014i;
        }

        public boolean j(View view, x.e eVar, float f8, long j8, double d8, double d9) {
            view.setRotation(b(f8, j8, view, eVar) + ((float) Math.toDegrees(Math.atan2(d9, d8))));
            return this.f9014i;
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class e extends s {

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public boolean f9022l = false;

        @Override // x.s
        public boolean f(View view, float f8, long j8, x.e eVar) {
            if (view instanceof MotionLayout) {
                ((MotionLayout) view).setProgress(b(f8, j8, view, eVar));
            } else {
                if (this.f9022l) {
                    return false;
                }
                Method method = null;
                try {
                    method = view.getClass().getMethod("setProgress", Float.TYPE);
                } catch (NoSuchMethodException unused) {
                    this.f9022l = true;
                }
                Method method2 = method;
                if (method2 != null) {
                    try {
                        method2.invoke(view, Float.valueOf(b(f8, j8, view, eVar)));
                    } catch (IllegalAccessException e8) {
                        Log.e("SplineSet", "unable to setProgress", e8);
                    } catch (InvocationTargetException e9) {
                        Log.e("SplineSet", "unable to setProgress", e9);
                    }
                }
            }
            return this.f9014i;
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class f extends s {
        @Override // x.s
        public boolean f(View view, float f8, long j8, x.e eVar) {
            view.setRotation(b(f8, j8, view, eVar));
            return this.f9014i;
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class g extends s {
        @Override // x.s
        public boolean f(View view, float f8, long j8, x.e eVar) {
            view.setRotationX(b(f8, j8, view, eVar));
            return this.f9014i;
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class h extends s {
        @Override // x.s
        public boolean f(View view, float f8, long j8, x.e eVar) {
            view.setRotationY(b(f8, j8, view, eVar));
            return this.f9014i;
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class i extends s {
        @Override // x.s
        public boolean f(View view, float f8, long j8, x.e eVar) {
            view.setScaleX(b(f8, j8, view, eVar));
            return this.f9014i;
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class j extends s {
        @Override // x.s
        public boolean f(View view, float f8, long j8, x.e eVar) {
            view.setScaleY(b(f8, j8, view, eVar));
            return this.f9014i;
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class k {
        public static void a(int[] iArr, float[][] fArr, int i8, int i9) {
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

        public static int b(int[] iArr, float[][] fArr, int i8, int i9) {
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

        public static void c(int[] iArr, float[][] fArr, int i8, int i9) {
            int i10 = iArr[i8];
            iArr[i8] = iArr[i9];
            iArr[i9] = i10;
            float[] fArr2 = fArr[i8];
            fArr[i8] = fArr[i9];
            fArr[i9] = fArr2;
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class l extends s {
        @Override // x.s
        public boolean f(View view, float f8, long j8, x.e eVar) {
            view.setTranslationX(b(f8, j8, view, eVar));
            return this.f9014i;
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class m extends s {
        @Override // x.s
        public boolean f(View view, float f8, long j8, x.e eVar) {
            view.setTranslationY(b(f8, j8, view, eVar));
            return this.f9014i;
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class n extends s {
        @Override // x.s
        public boolean f(View view, float f8, long j8, x.e eVar) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setTranslationZ(b(f8, j8, view, eVar));
            }
            return this.f9014i;
        }
    }

    public static s c(String str, SparseArray<ConstraintAttribute> sparseArray) {
        return new b(str, sparseArray);
    }

    public static s d(String str, long j8) {
        s gVar;
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
        gVar.g(j8);
        return gVar;
    }

    public float a(float f8) {
        float fAbs;
        switch (this.f9008c) {
            case 1:
                return Math.signum(f8 * f9006a);
            case 2:
                fAbs = Math.abs(f8);
                break;
            case 3:
                return (((f8 * 2.0f) + 1.0f) % 2.0f) - 1.0f;
            case 4:
                fAbs = ((f8 * 2.0f) + 1.0f) % 2.0f;
                break;
            case 5:
                return (float) Math.cos(f8 * f9006a);
            case 6:
                float fAbs2 = 1.0f - Math.abs(((f8 * 4.0f) % 4.0f) - 2.0f);
                fAbs = fAbs2 * fAbs2;
                break;
            default:
                return (float) Math.sin(f8 * f9006a);
        }
        return 1.0f - fAbs;
    }

    public float b(float f8, long j8, View view, x.e eVar) {
        this.f9007b.e(f8, this.f9013h);
        float[] fArr = this.f9013h;
        float f9 = fArr[1];
        if (f9 == 0.0f) {
            this.f9014i = false;
            return fArr[2];
        }
        if (Float.isNaN(this.f9016k)) {
            float fA = eVar.a(view, this.f9012g, 0);
            this.f9016k = fA;
            if (Float.isNaN(fA)) {
                this.f9016k = 0.0f;
            }
        }
        float f10 = (float) ((((double) this.f9016k) + (((j8 - this.f9015j) * 1.0E-9d) * ((double) f9))) % 1.0d);
        this.f9016k = f10;
        eVar.b(view, this.f9012g, 0, f10);
        this.f9015j = j8;
        float f11 = this.f9013h[0];
        float fA2 = (a(this.f9016k) * f11) + this.f9013h[2];
        this.f9014i = (f11 == 0.0f && f9 == 0.0f) ? false : true;
        return fA2;
    }

    public void e(int i8, float f8, float f9, int i9, float f10) {
        int[] iArr = this.f9009d;
        int i10 = this.f9011f;
        iArr[i10] = i8;
        float[][] fArr = this.f9010e;
        fArr[i10][0] = f8;
        fArr[i10][1] = f9;
        fArr[i10][2] = f10;
        this.f9008c = Math.max(this.f9008c, i9);
        this.f9011f++;
    }

    public abstract boolean f(View view, float f8, long j8, x.e eVar);

    public void g(long j8) {
        this.f9015j = j8;
    }

    public void h(String str) {
        this.f9012g = str;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0063  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void i(int r12) {
        /*
            r11 = this;
            int r0 = r11.f9011f
            if (r0 != 0) goto L1d
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r0 = "Error no points added to "
            r12.append(r0)
            java.lang.String r0 = r11.f9012g
            r12.append(r0)
            java.lang.String r12 = r12.toString()
            java.lang.String r0 = "SplineSet"
            android.util.Log.e(r0, r12)
            return
        L1d:
            int[] r1 = r11.f9009d
            float[][] r2 = r11.f9010e
            r3 = 1
            int r0 = r0 - r3
            r4 = 0
            x.s.k.a(r1, r2, r4, r0)
            r0 = r3
            r1 = r4
        L29:
            int[] r2 = r11.f9009d
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
            int r7 = r11.f9011f
            if (r2 >= r7) goto L92
            if (r2 <= 0) goto L63
            int[] r7 = r11.f9009d
            r8 = r7[r2]
            int r9 = r2 + (-1)
            r7 = r7[r9]
            if (r8 != r7) goto L63
            goto L8f
        L63:
            int[] r7 = r11.f9009d
            r7 = r7[r2]
            double r7 = (double) r7
            r9 = 4576918229304087675(0x3f847ae147ae147b, double:0.01)
            double r7 = r7 * r9
            r0[r6] = r7
            r7 = r1[r6]
            float[][] r8 = r11.f9010e
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
            w.b r12 = w.b.a(r12, r0, r1)
            r11.f9007b = r12
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: x.s.i(int):void");
    }

    public String toString() {
        String str = this.f9012g;
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        for (int i8 = 0; i8 < this.f9011f; i8++) {
            str = str + "[" + this.f9009d[i8] + " , " + decimalFormat.format(this.f9010e[i8]) + "] ";
        }
        return str;
    }
}
