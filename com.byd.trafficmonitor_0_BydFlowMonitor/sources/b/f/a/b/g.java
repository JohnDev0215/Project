package b.f.a.b;

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
public abstract class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b.f.a.a.b f2053a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public d f2054b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ConstraintAttribute f2055c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f2056d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2057e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2058f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ArrayList<p> f2059g = new ArrayList<>();

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public class a implements Comparator<p> {
        public a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(p pVar, p pVar2) {
            return Integer.compare(pVar.f2072a, pVar2.f2072a);
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class b extends g {
        @Override // b.f.a.b.g
        public void f(View view, float f2) {
            view.setAlpha(a(f2));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class c extends g {

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public float[] f2061h = new float[1];

        @Override // b.f.a.b.g
        public void f(View view, float f2) {
            this.f2061h[0] = a(f2);
            this.f2055c.i(view, this.f2061h);
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f2062a;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float[] f2064c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public double[] f2065d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float[] f2066e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public float[] f2067f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public float[] f2068g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f2069h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public b.f.a.a.b f2070i;
        public double[] j;
        public double[] k;
        public float l;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public b.f.a.a.f f2063b = new b.f.a.a.f();
        public HashMap<String, ConstraintAttribute> m = new HashMap<>();

        public d(int i2, int i3, int i4) {
            this.f2069h = i2;
            this.f2062a = i3;
            this.f2063b.g(i2);
            this.f2064c = new float[i4];
            this.f2065d = new double[i4];
            this.f2066e = new float[i4];
            this.f2067f = new float[i4];
            this.f2068g = new float[i4];
        }

        public double a(float f2) {
            b.f.a.a.b bVar = this.f2070i;
            if (bVar != null) {
                double d2 = f2;
                bVar.g(d2, this.k);
                this.f2070i.d(d2, this.j);
            } else {
                double[] dArr = this.k;
                dArr[0] = 0.0d;
                dArr[1] = 0.0d;
            }
            double d3 = f2;
            double dE = this.f2063b.e(d3);
            double d4 = this.f2063b.d(d3);
            double[] dArr2 = this.k;
            return dArr2[0] + (dE * dArr2[1]) + (d4 * this.j[1]);
        }

        public double b(float f2) {
            b.f.a.a.b bVar = this.f2070i;
            if (bVar != null) {
                bVar.d(f2, this.j);
            } else {
                double[] dArr = this.j;
                dArr[0] = this.f2067f[0];
                dArr[1] = this.f2064c[0];
            }
            return this.j[0] + (this.f2063b.e(f2) * this.j[1]);
        }

        public void c(int i2, int i3, float f2, float f3, float f4) {
            this.f2065d[i2] = ((double) i3) / 100.0d;
            this.f2066e[i2] = f2;
            this.f2067f[i2] = f3;
            this.f2064c[i2] = f4;
        }

        public void d(float f2) {
            this.l = f2;
            double[][] dArr = (double[][]) Array.newInstance((Class<?>) double.class, this.f2065d.length, 2);
            float[] fArr = this.f2064c;
            this.j = new double[fArr.length + 1];
            this.k = new double[fArr.length + 1];
            if (this.f2065d[0] > 0.0d) {
                this.f2063b.a(0.0d, this.f2066e[0]);
            }
            double[] dArr2 = this.f2065d;
            int length = dArr2.length - 1;
            if (dArr2[length] < 1.0d) {
                this.f2063b.a(1.0d, this.f2066e[length]);
            }
            for (int i2 = 0; i2 < dArr.length; i2++) {
                dArr[i2][0] = this.f2067f[i2];
                int i3 = 0;
                while (true) {
                    if (i3 < this.f2064c.length) {
                        dArr[i3][1] = r4[i3];
                        i3++;
                    }
                }
                this.f2063b.a(this.f2065d[i2], this.f2066e[i2]);
            }
            this.f2063b.f();
            double[] dArr3 = this.f2065d;
            if (dArr3.length > 1) {
                this.f2070i = b.f.a.a.b.a(0, dArr3, dArr);
            } else {
                this.f2070i = null;
            }
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class e extends g {
        @Override // b.f.a.b.g
        public void f(View view, float f2) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setElevation(a(f2));
            }
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class f extends g {
        @Override // b.f.a.b.g
        public void f(View view, float f2) {
        }

        public void j(View view, float f2, double d2, double d3) {
            view.setRotation(a(f2) + ((float) Math.toDegrees(Math.atan2(d3, d2))));
        }
    }

    /* JADX INFO: renamed from: b.f.a.b.g$g, reason: collision with other inner class name */
    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class C0026g extends g {

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public boolean f2071h = false;

        @Override // b.f.a.b.g
        public void f(View view, float f2) {
            if (view instanceof MotionLayout) {
                ((MotionLayout) view).setProgress(a(f2));
                return;
            }
            if (this.f2071h) {
                return;
            }
            Method method = null;
            try {
                method = view.getClass().getMethod("setProgress", Float.TYPE);
            } catch (NoSuchMethodException unused) {
                this.f2071h = true;
            }
            if (method != null) {
                try {
                    method.invoke(view, Float.valueOf(a(f2)));
                } catch (IllegalAccessException e2) {
                    Log.e("KeyCycleOscillator", "unable to setProgress", e2);
                } catch (InvocationTargetException e3) {
                    Log.e("KeyCycleOscillator", "unable to setProgress", e3);
                }
            }
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class h extends g {
        @Override // b.f.a.b.g
        public void f(View view, float f2) {
            view.setRotation(a(f2));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class i extends g {
        @Override // b.f.a.b.g
        public void f(View view, float f2) {
            view.setRotationX(a(f2));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class j extends g {
        @Override // b.f.a.b.g
        public void f(View view, float f2) {
            view.setRotationY(a(f2));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class k extends g {
        @Override // b.f.a.b.g
        public void f(View view, float f2) {
            view.setScaleX(a(f2));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class l extends g {
        @Override // b.f.a.b.g
        public void f(View view, float f2) {
            view.setScaleY(a(f2));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class m extends g {
        @Override // b.f.a.b.g
        public void f(View view, float f2) {
            view.setTranslationX(a(f2));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class n extends g {
        @Override // b.f.a.b.g
        public void f(View view, float f2) {
            view.setTranslationY(a(f2));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class o extends g {
        @Override // b.f.a.b.g
        public void f(View view, float f2) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setTranslationZ(a(f2));
            }
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class p {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f2072a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f2073b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f2074c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f2075d;

        public p(int i2, float f2, float f3, float f4) {
            this.f2072a = i2;
            this.f2073b = f4;
            this.f2074c = f3;
            this.f2075d = f2;
        }
    }

    public static g c(String str) {
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
                return new C0026g();
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
        return (float) this.f2054b.b(f2);
    }

    public float b(float f2) {
        return (float) this.f2054b.a(f2);
    }

    public void d(int i2, int i3, int i4, float f2, float f3, float f4) {
        this.f2059g.add(new p(i2, f2, f3, f4));
        if (i4 != -1) {
            this.f2058f = i4;
        }
        this.f2057e = i3;
    }

    public void e(int i2, int i3, int i4, float f2, float f3, float f4, ConstraintAttribute constraintAttribute) {
        this.f2059g.add(new p(i2, f2, f3, f4));
        if (i4 != -1) {
            this.f2058f = i4;
        }
        this.f2057e = i3;
        this.f2055c = constraintAttribute;
    }

    public abstract void f(View view, float f2);

    public void g(String str) {
        this.f2056d = str;
    }

    @TargetApi(19)
    public void h(float f2) {
        int size = this.f2059g.size();
        if (size == 0) {
            return;
        }
        Collections.sort(this.f2059g, new a());
        double[] dArr = new double[size];
        double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) double.class, size, 2);
        this.f2054b = new d(this.f2057e, this.f2058f, size);
        int i2 = 0;
        for (p pVar : this.f2059g) {
            float f3 = pVar.f2075d;
            dArr[i2] = ((double) f3) * 0.01d;
            double[] dArr3 = dArr2[i2];
            float f4 = pVar.f2073b;
            dArr3[0] = f4;
            double[] dArr4 = dArr2[i2];
            float f5 = pVar.f2074c;
            dArr4[1] = f5;
            this.f2054b.c(i2, pVar.f2072a, f3, f5, f4);
            i2++;
        }
        this.f2054b.d(f2);
        this.f2053a = b.f.a.a.b.a(0, dArr, dArr2);
    }

    public boolean i() {
        return this.f2058f == 1;
    }

    public String toString() {
        String str = this.f2056d;
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        Iterator<p> it = this.f2059g.iterator();
        while (it.hasNext()) {
            str = str + "[" + it.next().f2072a + " , " + decimalFormat.format(r3.f2073b) + "] ";
        }
        return str;
    }
}
