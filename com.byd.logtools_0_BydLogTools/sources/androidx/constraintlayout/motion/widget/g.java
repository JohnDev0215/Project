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
    private b.e.a.a.b f802a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private d f803b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected ConstraintAttribute f804c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f805d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f806e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f807f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    ArrayList<p> f808g = new ArrayList<>();

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    class a implements Comparator<p> {
        a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(p pVar, p pVar2) {
            return Integer.compare(pVar.f820a, pVar2.f820a);
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    static class b extends g {
        b() {
        }

        @Override // androidx.constraintlayout.motion.widget.g
        public void f(View view, float f2) {
            view.setAlpha(a(f2));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    static class c extends g {

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        float[] f810h = new float[1];

        c() {
        }

        @Override // androidx.constraintlayout.motion.widget.g
        public void f(View view, float f2) {
            this.f810h[0] = a(f2);
            this.f804c.i(view, this.f810h);
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int f811a;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        float[] f813c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        double[] f814d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        float[] f815e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        float[] f816f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        float[] f817g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        int f818h;
        b.e.a.a.b i;
        double[] j;
        double[] k;
        float l;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        b.e.a.a.f f812b = new b.e.a.a.f();
        public HashMap<String, ConstraintAttribute> m = new HashMap<>();

        d(int i, int i2, int i3) {
            this.f818h = i;
            this.f811a = i2;
            this.f812b.g(i);
            this.f813c = new float[i3];
            this.f814d = new double[i3];
            this.f815e = new float[i3];
            this.f816f = new float[i3];
            this.f817g = new float[i3];
        }

        public double a(float f2) {
            b.e.a.a.b bVar = this.i;
            if (bVar != null) {
                double d2 = f2;
                bVar.g(d2, this.k);
                this.i.d(d2, this.j);
            } else {
                double[] dArr = this.k;
                dArr[0] = 0.0d;
                dArr[1] = 0.0d;
            }
            double d3 = f2;
            double dE = this.f812b.e(d3);
            double d4 = this.f812b.d(d3);
            double[] dArr2 = this.k;
            return dArr2[0] + (dE * dArr2[1]) + (d4 * this.j[1]);
        }

        public double b(float f2) {
            b.e.a.a.b bVar = this.i;
            if (bVar != null) {
                bVar.d(f2, this.j);
            } else {
                double[] dArr = this.j;
                dArr[0] = this.f816f[0];
                dArr[1] = this.f813c[0];
            }
            return this.j[0] + (this.f812b.e(f2) * this.j[1]);
        }

        public void c(int i, int i2, float f2, float f3, float f4) {
            this.f814d[i] = ((double) i2) / 100.0d;
            this.f815e[i] = f2;
            this.f816f[i] = f3;
            this.f813c[i] = f4;
        }

        public void d(float f2) {
            this.l = f2;
            double[][] dArr = (double[][]) Array.newInstance((Class<?>) double.class, this.f814d.length, 2);
            float[] fArr = this.f813c;
            this.j = new double[fArr.length + 1];
            this.k = new double[fArr.length + 1];
            if (this.f814d[0] > 0.0d) {
                this.f812b.a(0.0d, this.f815e[0]);
            }
            double[] dArr2 = this.f814d;
            int length = dArr2.length - 1;
            if (dArr2[length] < 1.0d) {
                this.f812b.a(1.0d, this.f815e[length]);
            }
            for (int i = 0; i < dArr.length; i++) {
                dArr[i][0] = this.f816f[i];
                int i2 = 0;
                while (true) {
                    if (i2 < this.f813c.length) {
                        dArr[i2][1] = r4[i2];
                        i2++;
                    }
                }
                this.f812b.a(this.f814d[i], this.f815e[i]);
            }
            this.f812b.f();
            double[] dArr3 = this.f814d;
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
        public void f(View view, float f2) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setElevation(a(f2));
            }
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    static class f extends g {
        f() {
        }

        @Override // androidx.constraintlayout.motion.widget.g
        public void f(View view, float f2) {
        }

        public void j(View view, float f2, double d2, double d3) {
            view.setRotation(a(f2) + ((float) Math.toDegrees(Math.atan2(d3, d2))));
        }
    }

    /* JADX INFO: renamed from: androidx.constraintlayout.motion.widget.g$g, reason: collision with other inner class name */
    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    static class C0009g extends g {

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        boolean f819h = false;

        C0009g() {
        }

        @Override // androidx.constraintlayout.motion.widget.g
        public void f(View view, float f2) {
            if (view instanceof MotionLayout) {
                ((MotionLayout) view).setProgress(a(f2));
                return;
            }
            if (this.f819h) {
                return;
            }
            Method method = null;
            try {
                method = view.getClass().getMethod("setProgress", Float.TYPE);
            } catch (NoSuchMethodException unused) {
                this.f819h = true;
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
    static class h extends g {
        h() {
        }

        @Override // androidx.constraintlayout.motion.widget.g
        public void f(View view, float f2) {
            view.setRotation(a(f2));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    static class i extends g {
        i() {
        }

        @Override // androidx.constraintlayout.motion.widget.g
        public void f(View view, float f2) {
            view.setRotationX(a(f2));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    static class j extends g {
        j() {
        }

        @Override // androidx.constraintlayout.motion.widget.g
        public void f(View view, float f2) {
            view.setRotationY(a(f2));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    static class k extends g {
        k() {
        }

        @Override // androidx.constraintlayout.motion.widget.g
        public void f(View view, float f2) {
            view.setScaleX(a(f2));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    static class l extends g {
        l() {
        }

        @Override // androidx.constraintlayout.motion.widget.g
        public void f(View view, float f2) {
            view.setScaleY(a(f2));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    static class m extends g {
        m() {
        }

        @Override // androidx.constraintlayout.motion.widget.g
        public void f(View view, float f2) {
            view.setTranslationX(a(f2));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    static class n extends g {
        n() {
        }

        @Override // androidx.constraintlayout.motion.widget.g
        public void f(View view, float f2) {
            view.setTranslationY(a(f2));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    static class o extends g {
        o() {
        }

        @Override // androidx.constraintlayout.motion.widget.g
        public void f(View view, float f2) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setTranslationZ(a(f2));
            }
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    static class p {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f820a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        float f821b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        float f822c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        float f823d;

        public p(int i, float f2, float f3, float f4) {
            this.f820a = i;
            this.f821b = f4;
            this.f822c = f3;
            this.f823d = f2;
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
                return new C0009g();
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
        return (float) this.f803b.b(f2);
    }

    public float b(float f2) {
        return (float) this.f803b.a(f2);
    }

    public void d(int i2, int i3, int i4, float f2, float f3, float f4) {
        this.f808g.add(new p(i2, f2, f3, f4));
        if (i4 != -1) {
            this.f807f = i4;
        }
        this.f806e = i3;
    }

    public void e(int i2, int i3, int i4, float f2, float f3, float f4, ConstraintAttribute constraintAttribute) {
        this.f808g.add(new p(i2, f2, f3, f4));
        if (i4 != -1) {
            this.f807f = i4;
        }
        this.f806e = i3;
        this.f804c = constraintAttribute;
    }

    public abstract void f(View view, float f2);

    public void g(String str) {
        this.f805d = str;
    }

    @TargetApi(19)
    public void h(float f2) {
        int size = this.f808g.size();
        if (size == 0) {
            return;
        }
        Collections.sort(this.f808g, new a());
        double[] dArr = new double[size];
        double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) double.class, size, 2);
        this.f803b = new d(this.f806e, this.f807f, size);
        int i2 = 0;
        for (p pVar : this.f808g) {
            float f3 = pVar.f823d;
            dArr[i2] = ((double) f3) * 0.01d;
            double[] dArr3 = dArr2[i2];
            float f4 = pVar.f821b;
            dArr3[0] = f4;
            double[] dArr4 = dArr2[i2];
            float f5 = pVar.f822c;
            dArr4[1] = f5;
            this.f803b.c(i2, pVar.f820a, f3, f5, f4);
            i2++;
        }
        this.f803b.d(f2);
        this.f802a = b.e.a.a.b.a(0, dArr, dArr2);
    }

    public boolean i() {
        return this.f807f == 1;
    }

    public String toString() {
        String str = this.f805d;
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        Iterator<p> it = this.f808g.iterator();
        while (it.hasNext()) {
            str = str + "[" + it.next().f820a + " , " + decimalFormat.format(r3.f821b) + "] ";
        }
        return str;
    }
}
