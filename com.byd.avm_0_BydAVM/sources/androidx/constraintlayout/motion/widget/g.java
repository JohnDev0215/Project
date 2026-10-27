package androidx.constraintlayout.motion.widget;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;
import android.view.View;
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
    private b.e.a.a.b f564a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private d f565b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected ConstraintAttribute f566c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f567d;
    private int e = 0;
    public int f = 0;
    ArrayList<p> g = new ArrayList<>();

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    class a implements Comparator<p> {
        a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(p pVar, p pVar2) {
            return Integer.compare(pVar.f573a, pVar2.f573a);
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    static class b extends g {
        b() {
        }

        @Override // androidx.constraintlayout.motion.widget.g
        public void f(View view, float f) {
            view.setAlpha(a(f));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    static class c extends g {
        float[] h = new float[1];

        c() {
        }

        @Override // androidx.constraintlayout.motion.widget.g
        public void f(View view, float f) {
            this.h[0] = a(f);
            this.f566c.i(view, this.h);
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int f569a;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        float[] f571c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        double[] f572d;
        float[] e;
        float[] f;
        float[] g;
        int h;
        b.e.a.a.b i;
        double[] j;
        double[] k;
        float l;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        b.e.a.a.f f570b = new b.e.a.a.f();
        public HashMap<String, ConstraintAttribute> m = new HashMap<>();

        d(int i, int i2, int i3) {
            this.h = i;
            this.f569a = i2;
            this.f570b.g(i);
            this.f571c = new float[i3];
            this.f572d = new double[i3];
            this.e = new float[i3];
            this.f = new float[i3];
            this.g = new float[i3];
        }

        public double a(float f) {
            b.e.a.a.b bVar = this.i;
            if (bVar != null) {
                double d2 = f;
                bVar.g(d2, this.k);
                this.i.d(d2, this.j);
            } else {
                double[] dArr = this.k;
                dArr[0] = 0.0d;
                dArr[1] = 0.0d;
            }
            double d3 = f;
            double dE = this.f570b.e(d3);
            double d4 = this.f570b.d(d3);
            double[] dArr2 = this.k;
            return dArr2[0] + (dE * dArr2[1]) + (d4 * this.j[1]);
        }

        public double b(float f) {
            b.e.a.a.b bVar = this.i;
            if (bVar != null) {
                bVar.d(f, this.j);
            } else {
                double[] dArr = this.j;
                dArr[0] = this.f[0];
                dArr[1] = this.f571c[0];
            }
            return this.j[0] + (this.f570b.e(f) * this.j[1]);
        }

        public void c(int i, int i2, float f, float f2, float f3) {
            this.f572d[i] = ((double) i2) / 100.0d;
            this.e[i] = f;
            this.f[i] = f2;
            this.f571c[i] = f3;
        }

        public void d(float f) {
            this.l = f;
            double[][] dArr = (double[][]) Array.newInstance((Class<?>) double.class, this.f572d.length, 2);
            float[] fArr = this.f571c;
            this.j = new double[fArr.length + 1];
            this.k = new double[fArr.length + 1];
            if (this.f572d[0] > 0.0d) {
                this.f570b.a(0.0d, this.e[0]);
            }
            double[] dArr2 = this.f572d;
            int length = dArr2.length - 1;
            if (dArr2[length] < 1.0d) {
                this.f570b.a(1.0d, this.e[length]);
            }
            for (int i = 0; i < dArr.length; i++) {
                dArr[i][0] = this.f[i];
                int i2 = 0;
                while (true) {
                    if (i2 < this.f571c.length) {
                        dArr[i2][1] = r4[i2];
                        i2++;
                    }
                }
                this.f570b.a(this.f572d[i], this.e[i]);
            }
            this.f570b.f();
            double[] dArr3 = this.f572d;
            if (dArr3.length > 1) {
                this.i = b.e.a.a.b.a(0, dArr3, dArr);
            } else {
                this.i = null;
            }
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    static class e extends g {
        e() {
        }

        @Override // androidx.constraintlayout.motion.widget.g
        public void f(View view, float f) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setElevation(a(f));
            }
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    static class f extends g {
        f() {
        }

        @Override // androidx.constraintlayout.motion.widget.g
        public void f(View view, float f) {
        }

        public void j(View view, float f, double d2, double d3) {
            view.setRotation(a(f) + ((float) Math.toDegrees(Math.atan2(d3, d2))));
        }
    }

    /* JADX INFO: renamed from: androidx.constraintlayout.motion.widget.g$g, reason: collision with other inner class name */
    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    static class C0008g extends g {
        boolean h = false;

        C0008g() {
        }

        @Override // androidx.constraintlayout.motion.widget.g
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
    static class h extends g {
        h() {
        }

        @Override // androidx.constraintlayout.motion.widget.g
        public void f(View view, float f) {
            view.setRotation(a(f));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    static class i extends g {
        i() {
        }

        @Override // androidx.constraintlayout.motion.widget.g
        public void f(View view, float f) {
            view.setRotationX(a(f));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    static class j extends g {
        j() {
        }

        @Override // androidx.constraintlayout.motion.widget.g
        public void f(View view, float f) {
            view.setRotationY(a(f));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    static class k extends g {
        k() {
        }

        @Override // androidx.constraintlayout.motion.widget.g
        public void f(View view, float f) {
            view.setScaleX(a(f));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    static class l extends g {
        l() {
        }

        @Override // androidx.constraintlayout.motion.widget.g
        public void f(View view, float f) {
            view.setScaleY(a(f));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    static class m extends g {
        m() {
        }

        @Override // androidx.constraintlayout.motion.widget.g
        public void f(View view, float f) {
            view.setTranslationX(a(f));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    static class n extends g {
        n() {
        }

        @Override // androidx.constraintlayout.motion.widget.g
        public void f(View view, float f) {
            view.setTranslationY(a(f));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    static class o extends g {
        o() {
        }

        @Override // androidx.constraintlayout.motion.widget.g
        public void f(View view, float f) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setTranslationZ(a(f));
            }
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    static class p {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f573a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        float f574b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        float f575c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        float f576d;

        public p(int i, float f, float f2, float f3) {
            this.f573a = i;
            this.f574b = f3;
            this.f575c = f2;
            this.f576d = f;
        }
    }

    static g c(String str) {
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
                return new C0008g();
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
        return (float) this.f565b.b(f2);
    }

    public float b(float f2) {
        return (float) this.f565b.a(f2);
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
        this.f566c = constraintAttribute;
    }

    public abstract void f(View view, float f2);

    public void g(String str) {
        this.f567d = str;
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
        this.f565b = new d(this.e, this.f, size);
        int i2 = 0;
        for (p pVar : this.g) {
            float f3 = pVar.f576d;
            dArr[i2] = ((double) f3) * 0.01d;
            double[] dArr3 = dArr2[i2];
            float f4 = pVar.f574b;
            dArr3[0] = f4;
            double[] dArr4 = dArr2[i2];
            float f5 = pVar.f575c;
            dArr4[1] = f5;
            this.f565b.c(i2, pVar.f573a, f3, f5, f4);
            i2++;
        }
        this.f565b.d(f2);
        this.f564a = b.e.a.a.b.a(0, dArr, dArr2);
    }

    public boolean i() {
        return this.f == 1;
    }

    public String toString() {
        String str = this.f567d;
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        Iterator<p> it = this.g.iterator();
        while (it.hasNext()) {
            str = str + "[" + it.next().f573a + " , " + decimalFormat.format(r2.f574b) + "] ";
        }
        return str;
    }
}
