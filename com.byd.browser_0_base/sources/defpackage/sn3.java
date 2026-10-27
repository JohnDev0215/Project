package defpackage;

import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import androidx.constraintlayout.motion.widget.MotionLayout;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: compiled from: ViewTimeCycle.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class sn3 extends pa3 {

    /* JADX INFO: compiled from: ViewTimeCycle.java */
    public static class a extends sn3 {
        @Override // defpackage.sn3
        public final boolean e(float f, long j, View view, a41 a41Var) {
            view.setAlpha(d(f, j, view, a41Var));
            return this.h;
        }
    }

    /* JADX INFO: compiled from: ViewTimeCycle.java */
    public static class b extends sn3 {
        public final String k;
        public final SparseArray<ox> l;
        public final SparseArray<float[]> m = new SparseArray<>();
        public float[] n;

        public b(SparseArray sparseArray, String str) {
            this.k = str.split(",")[1];
            this.l = sparseArray;
        }

        @Override // defpackage.pa3
        public final void b(float f, float f2, float f3, int i, int i2) {
            throw new RuntimeException("Wrong call for custom attribute");
        }

        @Override // defpackage.pa3
        public final void c(int i) {
            SparseArray<ox> sparseArray = this.l;
            int size = sparseArray.size();
            int iC = sparseArray.valueAt(0).c();
            double[] dArr = new double[size];
            int i2 = iC + 2;
            this.n = new float[i2];
            this.g = new float[iC];
            double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, size, i2);
            for (int i3 = 0; i3 < size; i3++) {
                int iKeyAt = sparseArray.keyAt(i3);
                ox oxVarValueAt = sparseArray.valueAt(i3);
                float[] fArrValueAt = this.m.valueAt(i3);
                dArr[i3] = ((double) iKeyAt) * 0.01d;
                oxVarValueAt.b(this.n);
                int i4 = 0;
                while (true) {
                    if (i4 < this.n.length) {
                        dArr2[i3][i4] = r10[i4];
                        i4++;
                    }
                }
                double[] dArr3 = dArr2[i3];
                dArr3[iC] = fArrValueAt[0];
                dArr3[iC + 1] = fArrValueAt[1];
            }
            this.a = l20.a(i, dArr, dArr2);
        }

        @Override // defpackage.sn3
        public final boolean e(float f, long j, View view, a41 a41Var) {
            this.a.d(f, this.n);
            float[] fArr = this.n;
            float f2 = fArr[fArr.length - 2];
            float f3 = fArr[fArr.length - 1];
            long j2 = j - this.i;
            if (Float.isNaN(this.j)) {
                float fA = a41Var.a(view, this.k);
                this.j = fA;
                if (Float.isNaN(fA)) {
                    this.j = 0.0f;
                }
            }
            float f4 = (float) ((((j2 * 1.0E-9d) * ((double) f2)) + ((double) this.j)) % 1.0d);
            this.j = f4;
            this.i = j;
            float fA2 = a(f4);
            this.h = false;
            int i = 0;
            while (true) {
                float[] fArr2 = this.g;
                if (i >= fArr2.length) {
                    break;
                }
                boolean z = this.h;
                float f5 = this.n[i];
                this.h = z | (((double) f5) != 0.0d);
                fArr2[i] = (f5 * fA2) + f3;
                i++;
            }
            a8.g0(this.l.valueAt(0), view, this.g);
            if (f2 != 0.0f) {
                this.h = true;
            }
            return this.h;
        }
    }

    /* JADX INFO: compiled from: ViewTimeCycle.java */
    public static class c extends sn3 {
        @Override // defpackage.sn3
        public final boolean e(float f, long j, View view, a41 a41Var) {
            view.setElevation(d(f, j, view, a41Var));
            return this.h;
        }
    }

    /* JADX INFO: compiled from: ViewTimeCycle.java */
    public static class d extends sn3 {
        @Override // defpackage.sn3
        public final boolean e(float f, long j, View view, a41 a41Var) {
            return this.h;
        }
    }

    /* JADX INFO: compiled from: ViewTimeCycle.java */
    public static class e extends sn3 {
        public boolean k = false;

        @Override // defpackage.sn3
        public final boolean e(float f, long j, View view, a41 a41Var) {
            Method method;
            if (view instanceof MotionLayout) {
                ((MotionLayout) view).setProgress(d(f, j, view, a41Var));
            } else {
                if (this.k) {
                    return false;
                }
                try {
                    method = view.getClass().getMethod("setProgress", Float.TYPE);
                } catch (NoSuchMethodException unused) {
                    this.k = true;
                    method = null;
                }
                if (method != null) {
                    try {
                        method.invoke(view, Float.valueOf(d(f, j, view, a41Var)));
                    } catch (IllegalAccessException e) {
                        Log.e("ViewTimeCycle", "unable to setProgress", e);
                    } catch (InvocationTargetException e2) {
                        Log.e("ViewTimeCycle", "unable to setProgress", e2);
                    }
                }
            }
            return this.h;
        }
    }

    /* JADX INFO: compiled from: ViewTimeCycle.java */
    public static class f extends sn3 {
        @Override // defpackage.sn3
        public final boolean e(float f, long j, View view, a41 a41Var) {
            view.setRotation(d(f, j, view, a41Var));
            return this.h;
        }
    }

    /* JADX INFO: compiled from: ViewTimeCycle.java */
    public static class g extends sn3 {
        @Override // defpackage.sn3
        public final boolean e(float f, long j, View view, a41 a41Var) {
            view.setRotationX(d(f, j, view, a41Var));
            return this.h;
        }
    }

    /* JADX INFO: compiled from: ViewTimeCycle.java */
    public static class h extends sn3 {
        @Override // defpackage.sn3
        public final boolean e(float f, long j, View view, a41 a41Var) {
            view.setRotationY(d(f, j, view, a41Var));
            return this.h;
        }
    }

    /* JADX INFO: compiled from: ViewTimeCycle.java */
    public static class i extends sn3 {
        @Override // defpackage.sn3
        public final boolean e(float f, long j, View view, a41 a41Var) {
            view.setScaleX(d(f, j, view, a41Var));
            return this.h;
        }
    }

    /* JADX INFO: compiled from: ViewTimeCycle.java */
    public static class j extends sn3 {
        @Override // defpackage.sn3
        public final boolean e(float f, long j, View view, a41 a41Var) {
            view.setScaleY(d(f, j, view, a41Var));
            return this.h;
        }
    }

    /* JADX INFO: compiled from: ViewTimeCycle.java */
    public static class k extends sn3 {
        @Override // defpackage.sn3
        public final boolean e(float f, long j, View view, a41 a41Var) {
            view.setTranslationX(d(f, j, view, a41Var));
            return this.h;
        }
    }

    /* JADX INFO: compiled from: ViewTimeCycle.java */
    public static class l extends sn3 {
        @Override // defpackage.sn3
        public final boolean e(float f, long j, View view, a41 a41Var) {
            view.setTranslationY(d(f, j, view, a41Var));
            return this.h;
        }
    }

    /* JADX INFO: compiled from: ViewTimeCycle.java */
    public static class m extends sn3 {
        @Override // defpackage.sn3
        public final boolean e(float f, long j, View view, a41 a41Var) {
            view.setTranslationZ(d(f, j, view, a41Var));
            return this.h;
        }
    }

    public final float d(float f2, long j2, View view, a41 a41Var) {
        this.a.d(f2, this.g);
        float[] fArr = this.g;
        boolean z = true;
        float f3 = fArr[1];
        if (f3 == 0.0f) {
            this.h = false;
            return fArr[2];
        }
        if (Float.isNaN(this.j)) {
            float fA = a41Var.a(view, this.f);
            this.j = fA;
            if (Float.isNaN(fA)) {
                this.j = 0.0f;
            }
        }
        float f4 = (float) (((((j2 - this.i) * 1.0E-9d) * ((double) f3)) + ((double) this.j)) % 1.0d);
        this.j = f4;
        String str = this.f;
        HashMap map = (HashMap) a41Var.b;
        if (map.containsKey(view)) {
            HashMap map2 = (HashMap) map.get(view);
            if (map2 == null) {
                map2 = new HashMap();
            }
            if (map2.containsKey(str)) {
                float[] fArrCopyOf = (float[]) map2.get(str);
                if (fArrCopyOf == null) {
                    fArrCopyOf = new float[0];
                }
                if (fArrCopyOf.length <= 0) {
                    fArrCopyOf = Arrays.copyOf(fArrCopyOf, 1);
                }
                fArrCopyOf[0] = f4;
                map2.put(str, fArrCopyOf);
            } else {
                map2.put(str, new float[]{f4});
                map.put(view, map2);
            }
        } else {
            HashMap map3 = new HashMap();
            map3.put(str, new float[]{f4});
            map.put(view, map3);
        }
        this.i = j2;
        float f5 = this.g[0];
        float fA2 = (a(this.j) * f5) + this.g[2];
        if (f5 == 0.0f && f3 == 0.0f) {
            z = false;
        }
        this.h = z;
        return fA2;
    }

    public abstract boolean e(float f2, long j2, View view, a41 a41Var);
}
