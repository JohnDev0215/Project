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

/* JADX INFO: compiled from: TimeCycleSplineSet.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static float f5294a = 6.2831855f;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public t.b f5295b;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f5299f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public String f5300g;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public long f5303j;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f5296c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int[] f5297d = new int[10];

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float[][] f5298e = (float[][]) Array.newInstance((Class<?>) float.class, 10, 3);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float[] f5301h = new float[3];

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f5302i = false;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f5304k = Float.NaN;

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class a extends s {
        @Override // u.s
        public boolean f(View view, float f3, long j3, u.e eVar) {
            view.setAlpha(b(f3, j3, view, eVar));
            return this.f5302i;
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class b extends s {

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public String f5305l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public SparseArray<ConstraintAttribute> f5306m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public SparseArray<float[]> f5307n = new SparseArray<>();

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public float[] f5308o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public float[] f5309p;

        public b(String str, SparseArray<ConstraintAttribute> sparseArray) {
            this.f5305l = str.split(ListUtils.DEFAULT_JOIN_SEPARATOR)[1];
            this.f5306m = sparseArray;
        }

        @Override // u.s
        public void e(int i3, float f3, float f4, int i4, float f5) {
            throw new RuntimeException("don't call for custom attribute call setPoint(pos, ConstraintAttribute,...)");
        }

        @Override // u.s
        public boolean f(View view, float f3, long j3, u.e eVar) {
            this.f5295b.e(f3, this.f5308o);
            float[] fArr = this.f5308o;
            float f4 = fArr[fArr.length - 2];
            float f5 = fArr[fArr.length - 1];
            float f6 = (float) ((((double) this.f5304k) + (((j3 - this.f5303j) * 1.0E-9d) * ((double) f4))) % 1.0d);
            this.f5304k = f6;
            this.f5303j = j3;
            float fA = a(f6);
            this.f5302i = false;
            int i3 = 0;
            while (true) {
                float[] fArr2 = this.f5309p;
                if (i3 >= fArr2.length) {
                    break;
                }
                boolean z3 = this.f5302i;
                float[] fArr3 = this.f5308o;
                this.f5302i = z3 | (((double) fArr3[i3]) != 0.0d);
                fArr2[i3] = (fArr3[i3] * fA) + f5;
                i3++;
            }
            this.f5306m.valueAt(0).i(view, this.f5309p);
            if (f4 != 0.0f) {
                this.f5302i = true;
            }
            return this.f5302i;
        }

        @Override // u.s
        public void i(int i3) {
            int size = this.f5306m.size();
            int iF = this.f5306m.valueAt(0).f();
            double[] dArr = new double[size];
            int i4 = iF + 2;
            this.f5308o = new float[i4];
            this.f5309p = new float[iF];
            double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) double.class, size, i4);
            for (int i5 = 0; i5 < size; i5++) {
                int iKeyAt = this.f5306m.keyAt(i5);
                ConstraintAttribute constraintAttributeValueAt = this.f5306m.valueAt(i5);
                float[] fArrValueAt = this.f5307n.valueAt(i5);
                dArr[i5] = ((double) iKeyAt) * 0.01d;
                constraintAttributeValueAt.e(this.f5308o);
                int i6 = 0;
                while (true) {
                    if (i6 < this.f5308o.length) {
                        dArr2[i5][i6] = r8[i6];
                        i6++;
                    }
                }
                dArr2[i5][iF] = fArrValueAt[0];
                dArr2[i5][iF + 1] = fArrValueAt[1];
            }
            this.f5295b = t.b.a(i3, dArr, dArr2);
        }

        public void j(int i3, ConstraintAttribute constraintAttribute, float f3, int i4, float f4) {
            this.f5306m.append(i3, constraintAttribute);
            this.f5307n.append(i3, new float[]{f3, f4});
            this.f5296c = Math.max(this.f5296c, i4);
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class c extends s {
        @Override // u.s
        public boolean f(View view, float f3, long j3, u.e eVar) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setElevation(b(f3, j3, view, eVar));
            }
            return this.f5302i;
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class d extends s {
        @Override // u.s
        public boolean f(View view, float f3, long j3, u.e eVar) {
            return this.f5302i;
        }

        public boolean j(View view, u.e eVar, float f3, long j3, double d3, double d4) {
            view.setRotation(b(f3, j3, view, eVar) + ((float) Math.toDegrees(Math.atan2(d4, d3))));
            return this.f5302i;
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class e extends s {

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public boolean f5310l = false;

        @Override // u.s
        public boolean f(View view, float f3, long j3, u.e eVar) {
            if (view instanceof MotionLayout) {
                ((MotionLayout) view).setProgress(b(f3, j3, view, eVar));
            } else {
                if (this.f5310l) {
                    return false;
                }
                Method method = null;
                try {
                    method = view.getClass().getMethod("setProgress", Float.TYPE);
                } catch (NoSuchMethodException unused) {
                    this.f5310l = true;
                }
                Method method2 = method;
                if (method2 != null) {
                    try {
                        method2.invoke(view, Float.valueOf(b(f3, j3, view, eVar)));
                    } catch (IllegalAccessException e3) {
                        Log.e("SplineSet", "unable to setProgress", e3);
                    } catch (InvocationTargetException e4) {
                        Log.e("SplineSet", "unable to setProgress", e4);
                    }
                }
            }
            return this.f5302i;
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class f extends s {
        @Override // u.s
        public boolean f(View view, float f3, long j3, u.e eVar) {
            view.setRotation(b(f3, j3, view, eVar));
            return this.f5302i;
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class g extends s {
        @Override // u.s
        public boolean f(View view, float f3, long j3, u.e eVar) {
            view.setRotationX(b(f3, j3, view, eVar));
            return this.f5302i;
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class h extends s {
        @Override // u.s
        public boolean f(View view, float f3, long j3, u.e eVar) {
            view.setRotationY(b(f3, j3, view, eVar));
            return this.f5302i;
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class i extends s {
        @Override // u.s
        public boolean f(View view, float f3, long j3, u.e eVar) {
            view.setScaleX(b(f3, j3, view, eVar));
            return this.f5302i;
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class j extends s {
        @Override // u.s
        public boolean f(View view, float f3, long j3, u.e eVar) {
            view.setScaleY(b(f3, j3, view, eVar));
            return this.f5302i;
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class k {
        public static void a(int[] iArr, float[][] fArr, int i3, int i4) {
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

        public static int b(int[] iArr, float[][] fArr, int i3, int i4) {
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

        public static void c(int[] iArr, float[][] fArr, int i3, int i4) {
            int i5 = iArr[i3];
            iArr[i3] = iArr[i4];
            iArr[i4] = i5;
            float[] fArr2 = fArr[i3];
            fArr[i3] = fArr[i4];
            fArr[i4] = fArr2;
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class l extends s {
        @Override // u.s
        public boolean f(View view, float f3, long j3, u.e eVar) {
            view.setTranslationX(b(f3, j3, view, eVar));
            return this.f5302i;
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class m extends s {
        @Override // u.s
        public boolean f(View view, float f3, long j3, u.e eVar) {
            view.setTranslationY(b(f3, j3, view, eVar));
            return this.f5302i;
        }
    }

    /* JADX INFO: compiled from: TimeCycleSplineSet.java */
    public static class n extends s {
        @Override // u.s
        public boolean f(View view, float f3, long j3, u.e eVar) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setTranslationZ(b(f3, j3, view, eVar));
            }
            return this.f5302i;
        }
    }

    public static s c(String str, SparseArray<ConstraintAttribute> sparseArray) {
        return new b(str, sparseArray);
    }

    public static s d(String str, long j3) {
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
        gVar.g(j3);
        return gVar;
    }

    public float a(float f3) {
        float fAbs;
        switch (this.f5296c) {
            case 1:
                return Math.signum(f3 * f5294a);
            case 2:
                fAbs = Math.abs(f3);
                break;
            case 3:
                return (((f3 * 2.0f) + 1.0f) % 2.0f) - 1.0f;
            case 4:
                fAbs = ((f3 * 2.0f) + 1.0f) % 2.0f;
                break;
            case 5:
                return (float) Math.cos(f3 * f5294a);
            case 6:
                float fAbs2 = 1.0f - Math.abs(((f3 * 4.0f) % 4.0f) - 2.0f);
                fAbs = fAbs2 * fAbs2;
                break;
            default:
                return (float) Math.sin(f3 * f5294a);
        }
        return 1.0f - fAbs;
    }

    public float b(float f3, long j3, View view, u.e eVar) {
        this.f5295b.e(f3, this.f5301h);
        float[] fArr = this.f5301h;
        float f4 = fArr[1];
        if (f4 == 0.0f) {
            this.f5302i = false;
            return fArr[2];
        }
        if (Float.isNaN(this.f5304k)) {
            float fA = eVar.a(view, this.f5300g, 0);
            this.f5304k = fA;
            if (Float.isNaN(fA)) {
                this.f5304k = 0.0f;
            }
        }
        float f5 = (float) ((((double) this.f5304k) + (((j3 - this.f5303j) * 1.0E-9d) * ((double) f4))) % 1.0d);
        this.f5304k = f5;
        eVar.b(view, this.f5300g, 0, f5);
        this.f5303j = j3;
        float f6 = this.f5301h[0];
        float fA2 = (a(this.f5304k) * f6) + this.f5301h[2];
        this.f5302i = (f6 == 0.0f && f4 == 0.0f) ? false : true;
        return fA2;
    }

    public void e(int i3, float f3, float f4, int i4, float f5) {
        int[] iArr = this.f5297d;
        int i5 = this.f5299f;
        iArr[i5] = i3;
        float[][] fArr = this.f5298e;
        fArr[i5][0] = f3;
        fArr[i5][1] = f4;
        fArr[i5][2] = f5;
        this.f5296c = Math.max(this.f5296c, i4);
        this.f5299f++;
    }

    public abstract boolean f(View view, float f3, long j3, u.e eVar);

    public void g(long j3) {
        this.f5303j = j3;
    }

    public void h(String str) {
        this.f5300g = str;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0063  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void i(int r12) {
        /*
            r11 = this;
            int r0 = r11.f5299f
            if (r0 != 0) goto L1d
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r0 = "Error no points added to "
            r12.append(r0)
            java.lang.String r0 = r11.f5300g
            r12.append(r0)
            java.lang.String r12 = r12.toString()
            java.lang.String r0 = "SplineSet"
            android.util.Log.e(r0, r12)
            return
        L1d:
            int[] r1 = r11.f5297d
            float[][] r2 = r11.f5298e
            r3 = 1
            int r0 = r0 - r3
            r4 = 0
            u.s.k.a(r1, r2, r4, r0)
            r0 = r3
            r1 = r4
        L29:
            int[] r2 = r11.f5297d
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
            int r7 = r11.f5299f
            if (r2 >= r7) goto L92
            if (r2 <= 0) goto L63
            int[] r7 = r11.f5297d
            r8 = r7[r2]
            int r9 = r2 + (-1)
            r7 = r7[r9]
            if (r8 != r7) goto L63
            goto L8f
        L63:
            int[] r7 = r11.f5297d
            r7 = r7[r2]
            double r7 = (double) r7
            r9 = 4576918229304087675(0x3f847ae147ae147b, double:0.01)
            double r7 = r7 * r9
            r0[r6] = r7
            r7 = r1[r6]
            float[][] r8 = r11.f5298e
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
            t.b r12 = t.b.a(r12, r0, r1)
            r11.f5295b = r12
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: u.s.i(int):void");
    }

    public String toString() {
        String str = this.f5300g;
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        for (int i3 = 0; i3 < this.f5299f; i3++) {
            str = str + "[" + this.f5297d[i3] + " , " + decimalFormat.format(this.f5298e[i3]) + "] ";
        }
        return str;
    }
}
