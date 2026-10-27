package defpackage;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintAttribute;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: compiled from: KeyCycleOscillator.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class w4 {
    public j4 a;
    public d b;
    public ConstraintAttribute c;
    public String d;
    public int e = 0;
    public int f = 0;
    public ArrayList<p> g = new ArrayList<>();

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public class a implements Comparator<p> {
        public a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(p pVar, p pVar2) {
            return Integer.compare(pVar.a, pVar2.a);
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class b extends w4 {
        @Override // defpackage.w4
        public void f(View view, float f) {
            view.setAlpha(a(f));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class c extends w4 {
        public float[] h = new float[1];

        @Override // defpackage.w4
        public void f(View view, float f) {
            this.h[0] = a(f);
            this.c.i(view, this.h);
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class d {
        public final int a;
        public float[] c;
        public double[] d;
        public float[] e;
        public float[] f;
        public float[] g;
        public int h;
        public j4 i;
        public double[] j;
        public double[] k;
        public float l;
        public n4 b = new n4();
        public HashMap<String, ConstraintAttribute> m = new HashMap<>();

        public d(int i, int i2, int i3) {
            this.h = i;
            this.a = i2;
            this.b.g(i);
            this.c = new float[i3];
            this.d = new double[i3];
            this.e = new float[i3];
            this.f = new float[i3];
            this.g = new float[i3];
        }

        public double a(float f) {
            j4 j4Var = this.i;
            if (j4Var != null) {
                double d = f;
                j4Var.g(d, this.k);
                this.i.d(d, this.j);
            } else {
                double[] dArr = this.k;
                dArr[0] = 0.0d;
                dArr[1] = 0.0d;
            }
            double d2 = f;
            double dE = this.b.e(d2);
            double d3 = this.b.d(d2);
            double[] dArr2 = this.k;
            return dArr2[0] + (dE * dArr2[1]) + (d3 * this.j[1]);
        }

        public double b(float f) {
            j4 j4Var = this.i;
            if (j4Var != null) {
                j4Var.d(f, this.j);
            } else {
                double[] dArr = this.j;
                dArr[0] = this.f[0];
                dArr[1] = this.c[0];
            }
            return this.j[0] + (this.b.e(f) * this.j[1]);
        }

        public void c(int i, int i2, float f, float f2, float f3) {
            this.d[i] = ((double) i2) / 100.0d;
            this.e[i] = f;
            this.f[i] = f2;
            this.c[i] = f3;
        }

        public void d(float f) {
            this.l = f;
            double[][] dArr = (double[][]) Array.newInstance((Class<?>) double.class, this.d.length, 2);
            float[] fArr = this.c;
            this.j = new double[fArr.length + 1];
            this.k = new double[fArr.length + 1];
            if (this.d[0] > 0.0d) {
                this.b.a(0.0d, this.e[0]);
            }
            double[] dArr2 = this.d;
            int length = dArr2.length - 1;
            if (dArr2[length] < 1.0d) {
                this.b.a(1.0d, this.e[length]);
            }
            for (int i = 0; i < dArr.length; i++) {
                dArr[i][0] = this.f[i];
                int i2 = 0;
                while (true) {
                    if (i2 < this.c.length) {
                        dArr[i2][1] = r4[i2];
                        i2++;
                    }
                }
                this.b.a(this.d[i], this.e[i]);
            }
            this.b.f();
            double[] dArr3 = this.d;
            if (dArr3.length > 1) {
                this.i = j4.a(0, dArr3, dArr);
            } else {
                this.i = null;
            }
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class e extends w4 {
        @Override // defpackage.w4
        public void f(View view, float f) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setElevation(a(f));
            }
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class f extends w4 {
        @Override // defpackage.w4
        public void f(View view, float f) {
        }

        public void j(View view, float f, double d, double d2) {
            view.setRotation(a(f) + ((float) Math.toDegrees(Math.atan2(d2, d))));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class g extends w4 {
        public boolean h = false;

        @Override // defpackage.w4
        public void f(View view, float f) {
            if (view instanceof MotionLayout) {
                ((MotionLayout) view).setProgress(a(f));
                return;
            }
            if (this.h) {
                return;
            }
            Method method = null;
            try {
                method = view.getClass().getMethod("setProgress", Float.TYPE);
            } catch (NoSuchMethodException unused) {
                this.h = true;
            }
            if (method != null) {
                try {
                    method.invoke(view, Float.valueOf(a(f)));
                } catch (IllegalAccessException e) {
                    Log.e("KeyCycleOscillator", "unable to setProgress", e);
                } catch (InvocationTargetException e2) {
                    Log.e("KeyCycleOscillator", "unable to setProgress", e2);
                }
            }
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class h extends w4 {
        @Override // defpackage.w4
        public void f(View view, float f) {
            view.setRotation(a(f));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class i extends w4 {
        @Override // defpackage.w4
        public void f(View view, float f) {
            view.setRotationX(a(f));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class j extends w4 {
        @Override // defpackage.w4
        public void f(View view, float f) {
            view.setRotationY(a(f));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class k extends w4 {
        @Override // defpackage.w4
        public void f(View view, float f) {
            view.setScaleX(a(f));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class l extends w4 {
        @Override // defpackage.w4
        public void f(View view, float f) {
            view.setScaleY(a(f));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class m extends w4 {
        @Override // defpackage.w4
        public void f(View view, float f) {
            view.setTranslationX(a(f));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class n extends w4 {
        @Override // defpackage.w4
        public void f(View view, float f) {
            view.setTranslationY(a(f));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class o extends w4 {
        @Override // defpackage.w4
        public void f(View view, float f) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setTranslationZ(a(f));
            }
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class p {
        public int a;
        public float b;
        public float c;
        public float d;

        public p(int i, float f, float f2, float f3) {
            this.a = i;
            this.b = f3;
            this.c = f2;
            this.d = f;
        }
    }

    public static w4 c(String str) {
        if (str.startsWith("CUSTOM")) {
            return new c();
        }
        switch (str) {
            case "rotationX":
                return new i();
            case "rotationY":
                return new j();
            case "translationX":
                return new m();
            case "translationY":
                return new n();
            case "translationZ":
                return new o();
            case "progress":
                return new g();
            case "scaleX":
                return new k();
            case "scaleY":
                return new l();
            case "waveVariesBy":
                return new b();
            case "rotation":
                return new h();
            case "elevation":
                return new e();
            case "transitionPathRotate":
                return new f();
            case "alpha":
                return new b();
            case "waveOffset":
                return new b();
            default:
                return null;
        }
    }

    public float a(float f2) {
        return (float) this.b.b(f2);
    }

    public float b(float f2) {
        return (float) this.b.a(f2);
    }

    public void d(int i2, int i3, int i4, float f2, float f3, float f4) {
        this.g.add(new p(i2, f2, f3, f4));
        if (i4 != -1) {
            this.f = i4;
        }
        this.e = i3;
    }

    public void e(int i2, int i3, int i4, float f2, float f3, float f4, ConstraintAttribute constraintAttribute) {
        this.g.add(new p(i2, f2, f3, f4));
        if (i4 != -1) {
            this.f = i4;
        }
        this.e = i3;
        this.c = constraintAttribute;
    }

    public abstract void f(View view, float f2);

    public void g(String str) {
        this.d = str;
    }

    @TargetApi(19)
    public void h(float f2) {
        int size = this.g.size();
        if (size == 0) {
            return;
        }
        Collections.sort(this.g, new a());
        double[] dArr = new double[size];
        double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) double.class, size, 2);
        this.b = new d(this.e, this.f, size);
        int i2 = 0;
        for (p pVar : this.g) {
            float f3 = pVar.d;
            dArr[i2] = ((double) f3) * 0.01d;
            double[] dArr3 = dArr2[i2];
            float f4 = pVar.b;
            dArr3[0] = f4;
            double[] dArr4 = dArr2[i2];
            float f5 = pVar.c;
            dArr4[1] = f5;
            this.b.c(i2, pVar.a, f3, f5, f4);
            i2++;
        }
        this.b.d(f2);
        this.a = j4.a(0, dArr, dArr2);
    }

    public boolean i() {
        return this.f == 1;
    }

    public String toString() {
        String str = this.d;
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        Iterator<p> it = this.g.iterator();
        while (it.hasNext()) {
            str = str + "[" + it.next().a + " , " + decimalFormat.format(r3.b) + "] ";
        }
        return str;
    }
}
