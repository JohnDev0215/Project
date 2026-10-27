package b.e.a.b;

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
    public b.e.a.a.b f1255a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public d f1256b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ConstraintAttribute f1257c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f1258d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1259e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1260f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ArrayList<p> f1261g = new ArrayList<>();

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public class a implements Comparator<p> {
        public a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(p pVar, p pVar2) {
            return Integer.compare(pVar.f1274a, pVar2.f1274a);
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class b extends g {
        @Override // b.e.a.b.g
        public void f(View view, float f2) {
            view.setAlpha(a(f2));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class c extends g {

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public float[] f1263h = new float[1];

        @Override // b.e.a.b.g
        public void f(View view, float f2) {
            this.f1263h[0] = a(f2);
            this.f1257c.i(view, this.f1263h);
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f1264a;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float[] f1266c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public double[] f1267d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float[] f1268e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public float[] f1269f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public float[] f1270g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f1271h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public b.e.a.a.b f1272i;
        public double[] j;
        public double[] k;
        public float l;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public b.e.a.a.f f1265b = new b.e.a.a.f();
        public HashMap<String, ConstraintAttribute> m = new HashMap<>();

        public d(int i2, int i3, int i4) {
            this.f1271h = i2;
            this.f1264a = i3;
            this.f1265b.g(i2);
            this.f1266c = new float[i4];
            this.f1267d = new double[i4];
            this.f1268e = new float[i4];
            this.f1269f = new float[i4];
            this.f1270g = new float[i4];
        }

        public double a(float f2) {
            b.e.a.a.b bVar = this.f1272i;
            if (bVar != null) {
                double d2 = f2;
                bVar.g(d2, this.k);
                this.f1272i.d(d2, this.j);
            } else {
                double[] dArr = this.k;
                dArr[0] = 0.0d;
                dArr[1] = 0.0d;
            }
            double d3 = f2;
            double dE = this.f1265b.e(d3);
            double d4 = this.f1265b.d(d3);
            double[] dArr2 = this.k;
            return dArr2[0] + (dE * dArr2[1]) + (d4 * this.j[1]);
        }

        public double b(float f2) {
            b.e.a.a.b bVar = this.f1272i;
            if (bVar != null) {
                bVar.d(f2, this.j);
            } else {
                double[] dArr = this.j;
                dArr[0] = this.f1269f[0];
                dArr[1] = this.f1266c[0];
            }
            return this.j[0] + (this.f1265b.e(f2) * this.j[1]);
        }

        public void c(int i2, int i3, float f2, float f3, float f4) {
            this.f1267d[i2] = ((double) i3) / 100.0d;
            this.f1268e[i2] = f2;
            this.f1269f[i2] = f3;
            this.f1266c[i2] = f4;
        }

        public void d(float f2) {
            this.l = f2;
            double[][] dArr = (double[][]) Array.newInstance((Class<?>) double.class, this.f1267d.length, 2);
            float[] fArr = this.f1266c;
            this.j = new double[fArr.length + 1];
            this.k = new double[fArr.length + 1];
            if (this.f1267d[0] > 0.0d) {
                this.f1265b.a(0.0d, this.f1268e[0]);
            }
            double[] dArr2 = this.f1267d;
            int length = dArr2.length - 1;
            if (dArr2[length] < 1.0d) {
                this.f1265b.a(1.0d, this.f1268e[length]);
            }
            for (int i2 = 0; i2 < dArr.length; i2++) {
                dArr[i2][0] = this.f1269f[i2];
                int i3 = 0;
                while (true) {
                    if (i3 < this.f1266c.length) {
                        dArr[i3][1] = r4[i3];
                        i3++;
                    }
                }
                this.f1265b.a(this.f1267d[i2], this.f1268e[i2]);
            }
            this.f1265b.f();
            double[] dArr3 = this.f1267d;
            if (dArr3.length > 1) {
                this.f1272i = b.e.a.a.b.a(0, dArr3, dArr);
            } else {
                this.f1272i = null;
            }
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class e extends g {
        @Override // b.e.a.b.g
        public void f(View view, float f2) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setElevation(a(f2));
            }
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class f extends g {
        @Override // b.e.a.b.g
        public void f(View view, float f2) {
        }

        public void j(View view, float f2, double d2, double d3) {
            view.setRotation(a(f2) + ((float) Math.toDegrees(Math.atan2(d3, d2))));
        }
    }

    /* JADX INFO: renamed from: b.e.a.b.g$g, reason: collision with other inner class name */
    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class C0015g extends g {

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public boolean f1273h = false;

        @Override // b.e.a.b.g
        public void f(View view, float f2) {
            if (view instanceof MotionLayout) {
                ((MotionLayout) view).setProgress(a(f2));
                return;
            }
            if (this.f1273h) {
                return;
            }
            Method method = null;
            try {
                method = view.getClass().getMethod("setProgress", Float.TYPE);
            } catch (NoSuchMethodException unused) {
                this.f1273h = true;
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
        @Override // b.e.a.b.g
        public void f(View view, float f2) {
            view.setRotation(a(f2));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class i extends g {
        @Override // b.e.a.b.g
        public void f(View view, float f2) {
            view.setRotationX(a(f2));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class j extends g {
        @Override // b.e.a.b.g
        public void f(View view, float f2) {
            view.setRotationY(a(f2));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class k extends g {
        @Override // b.e.a.b.g
        public void f(View view, float f2) {
            view.setScaleX(a(f2));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class l extends g {
        @Override // b.e.a.b.g
        public void f(View view, float f2) {
            view.setScaleY(a(f2));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class m extends g {
        @Override // b.e.a.b.g
        public void f(View view, float f2) {
            view.setTranslationX(a(f2));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class n extends g {
        @Override // b.e.a.b.g
        public void f(View view, float f2) {
            view.setTranslationY(a(f2));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class o extends g {
        @Override // b.e.a.b.g
        public void f(View view, float f2) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setTranslationZ(a(f2));
            }
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class p {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1274a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f1275b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f1276c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f1277d;

        public p(int i2, float f2, float f3, float f4) {
            this.f1274a = i2;
            this.f1275b = f4;
            this.f1276c = f3;
            this.f1277d = f2;
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
                return new C0015g();
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
        return (float) this.f1256b.b(f2);
    }

    public float b(float f2) {
        return (float) this.f1256b.a(f2);
    }

    public void d(int i2, int i3, int i4, float f2, float f3, float f4) {
        this.f1261g.add(new p(i2, f2, f3, f4));
        if (i4 != -1) {
            this.f1260f = i4;
        }
        this.f1259e = i3;
    }

    public void e(int i2, int i3, int i4, float f2, float f3, float f4, ConstraintAttribute constraintAttribute) {
        this.f1261g.add(new p(i2, f2, f3, f4));
        if (i4 != -1) {
            this.f1260f = i4;
        }
        this.f1259e = i3;
        this.f1257c = constraintAttribute;
    }

    public abstract void f(View view, float f2);

    public void g(String str) {
        this.f1258d = str;
    }

    @TargetApi(19)
    public void h(float f2) {
        int size = this.f1261g.size();
        if (size == 0) {
            return;
        }
        Collections.sort(this.f1261g, new a());
        double[] dArr = new double[size];
        double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) double.class, size, 2);
        this.f1256b = new d(this.f1259e, this.f1260f, size);
        int i2 = 0;
        for (p pVar : this.f1261g) {
            float f3 = pVar.f1277d;
            dArr[i2] = ((double) f3) * 0.01d;
            double[] dArr3 = dArr2[i2];
            float f4 = pVar.f1275b;
            dArr3[0] = f4;
            double[] dArr4 = dArr2[i2];
            float f5 = pVar.f1276c;
            dArr4[1] = f5;
            this.f1256b.c(i2, pVar.f1274a, f3, f5, f4);
            i2++;
        }
        this.f1256b.d(f2);
        this.f1255a = b.e.a.a.b.a(0, dArr, dArr2);
    }

    public boolean i() {
        return this.f1260f == 1;
    }

    public String toString() {
        String str = this.f1258d;
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        Iterator<p> it = this.f1261g.iterator();
        while (it.hasNext()) {
            str = str + "[" + it.next().f1274a + " , " + decimalFormat.format(r3.f1275b) + "] ";
        }
        return str;
    }
}
