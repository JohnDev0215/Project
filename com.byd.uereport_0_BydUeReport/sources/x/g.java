package x;

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
    public w.b f8808a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public d f8809b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ConstraintAttribute f8810c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f8811d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f8812e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f8813f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ArrayList<p> f8814g = new ArrayList<>();

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public class a implements Comparator<p> {
        public a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(p pVar, p pVar2) {
            return Integer.compare(pVar.f8831a, pVar2.f8831a);
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class b extends g {
        @Override // x.g
        public void f(View view, float f8) {
            view.setAlpha(a(f8));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class c extends g {

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public float[] f8816h = new float[1];

        @Override // x.g
        public void f(View view, float f8) {
            this.f8816h[0] = a(f8);
            this.f8810c.i(view, this.f8816h);
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f8817a;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float[] f8819c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public double[] f8820d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float[] f8821e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public float[] f8822f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public float[] f8823g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f8824h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public w.b f8825i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public double[] f8826j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public double[] f8827k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public float f8828l;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public w.f f8818b = new w.f();

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public HashMap<String, ConstraintAttribute> f8829m = new HashMap<>();

        public d(int i8, int i9, int i10) {
            this.f8824h = i8;
            this.f8817a = i9;
            this.f8818b.g(i8);
            this.f8819c = new float[i10];
            this.f8820d = new double[i10];
            this.f8821e = new float[i10];
            this.f8822f = new float[i10];
            this.f8823g = new float[i10];
        }

        public double a(float f8) {
            w.b bVar = this.f8825i;
            if (bVar != null) {
                double d8 = f8;
                bVar.g(d8, this.f8827k);
                this.f8825i.d(d8, this.f8826j);
            } else {
                double[] dArr = this.f8827k;
                dArr[0] = 0.0d;
                dArr[1] = 0.0d;
            }
            double d9 = f8;
            double dE = this.f8818b.e(d9);
            double d10 = this.f8818b.d(d9);
            double[] dArr2 = this.f8827k;
            return dArr2[0] + (dE * dArr2[1]) + (d10 * this.f8826j[1]);
        }

        public double b(float f8) {
            w.b bVar = this.f8825i;
            if (bVar != null) {
                bVar.d(f8, this.f8826j);
            } else {
                double[] dArr = this.f8826j;
                dArr[0] = this.f8822f[0];
                dArr[1] = this.f8819c[0];
            }
            return this.f8826j[0] + (this.f8818b.e(f8) * this.f8826j[1]);
        }

        public void c(int i8, int i9, float f8, float f9, float f10) {
            this.f8820d[i8] = ((double) i9) / 100.0d;
            this.f8821e[i8] = f8;
            this.f8822f[i8] = f9;
            this.f8819c[i8] = f10;
        }

        public void d(float f8) {
            this.f8828l = f8;
            double[][] dArr = (double[][]) Array.newInstance((Class<?>) double.class, this.f8820d.length, 2);
            float[] fArr = this.f8819c;
            this.f8826j = new double[fArr.length + 1];
            this.f8827k = new double[fArr.length + 1];
            if (this.f8820d[0] > 0.0d) {
                this.f8818b.a(0.0d, this.f8821e[0]);
            }
            double[] dArr2 = this.f8820d;
            int length = dArr2.length - 1;
            if (dArr2[length] < 1.0d) {
                this.f8818b.a(1.0d, this.f8821e[length]);
            }
            for (int i8 = 0; i8 < dArr.length; i8++) {
                dArr[i8][0] = this.f8822f[i8];
                int i9 = 0;
                while (true) {
                    if (i9 < this.f8819c.length) {
                        dArr[i9][1] = r4[i9];
                        i9++;
                    }
                }
                this.f8818b.a(this.f8820d[i8], this.f8821e[i8]);
            }
            this.f8818b.f();
            double[] dArr3 = this.f8820d;
            if (dArr3.length > 1) {
                this.f8825i = w.b.a(0, dArr3, dArr);
            } else {
                this.f8825i = null;
            }
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class e extends g {
        @Override // x.g
        public void f(View view, float f8) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setElevation(a(f8));
            }
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class f extends g {
        @Override // x.g
        public void f(View view, float f8) {
        }

        public void j(View view, float f8, double d8, double d9) {
            view.setRotation(a(f8) + ((float) Math.toDegrees(Math.atan2(d9, d8))));
        }
    }

    /* JADX INFO: renamed from: x.g$g, reason: collision with other inner class name */
    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class C0140g extends g {

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public boolean f8830h = false;

        @Override // x.g
        public void f(View view, float f8) {
            if (view instanceof MotionLayout) {
                ((MotionLayout) view).setProgress(a(f8));
                return;
            }
            if (this.f8830h) {
                return;
            }
            Method method = null;
            try {
                method = view.getClass().getMethod("setProgress", Float.TYPE);
            } catch (NoSuchMethodException unused) {
                this.f8830h = true;
            }
            if (method != null) {
                try {
                    method.invoke(view, Float.valueOf(a(f8)));
                } catch (IllegalAccessException e8) {
                    Log.e("KeyCycleOscillator", "unable to setProgress", e8);
                } catch (InvocationTargetException e9) {
                    Log.e("KeyCycleOscillator", "unable to setProgress", e9);
                }
            }
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class h extends g {
        @Override // x.g
        public void f(View view, float f8) {
            view.setRotation(a(f8));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class i extends g {
        @Override // x.g
        public void f(View view, float f8) {
            view.setRotationX(a(f8));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class j extends g {
        @Override // x.g
        public void f(View view, float f8) {
            view.setRotationY(a(f8));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class k extends g {
        @Override // x.g
        public void f(View view, float f8) {
            view.setScaleX(a(f8));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class l extends g {
        @Override // x.g
        public void f(View view, float f8) {
            view.setScaleY(a(f8));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class m extends g {
        @Override // x.g
        public void f(View view, float f8) {
            view.setTranslationX(a(f8));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class n extends g {
        @Override // x.g
        public void f(View view, float f8) {
            view.setTranslationY(a(f8));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class o extends g {
        @Override // x.g
        public void f(View view, float f8) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setTranslationZ(a(f8));
            }
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class p {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f8831a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f8832b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f8833c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f8834d;

        public p(int i8, float f8, float f9, float f10) {
            this.f8831a = i8;
            this.f8832b = f10;
            this.f8833c = f9;
            this.f8834d = f8;
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
                return new C0140g();
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

    public float a(float f8) {
        return (float) this.f8809b.b(f8);
    }

    public float b(float f8) {
        return (float) this.f8809b.a(f8);
    }

    public void d(int i8, int i9, int i10, float f8, float f9, float f10) {
        this.f8814g.add(new p(i8, f8, f9, f10));
        if (i10 != -1) {
            this.f8813f = i10;
        }
        this.f8812e = i9;
    }

    public void e(int i8, int i9, int i10, float f8, float f9, float f10, ConstraintAttribute constraintAttribute) {
        this.f8814g.add(new p(i8, f8, f9, f10));
        if (i10 != -1) {
            this.f8813f = i10;
        }
        this.f8812e = i9;
        this.f8810c = constraintAttribute;
    }

    public abstract void f(View view, float f8);

    public void g(String str) {
        this.f8811d = str;
    }

    @TargetApi(19)
    public void h(float f8) {
        int size = this.f8814g.size();
        if (size == 0) {
            return;
        }
        Collections.sort(this.f8814g, new a());
        double[] dArr = new double[size];
        double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) double.class, size, 2);
        this.f8809b = new d(this.f8812e, this.f8813f, size);
        int i8 = 0;
        for (p pVar : this.f8814g) {
            float f9 = pVar.f8834d;
            dArr[i8] = ((double) f9) * 0.01d;
            double[] dArr3 = dArr2[i8];
            float f10 = pVar.f8832b;
            dArr3[0] = f10;
            double[] dArr4 = dArr2[i8];
            float f11 = pVar.f8833c;
            dArr4[1] = f11;
            this.f8809b.c(i8, pVar.f8831a, f9, f11, f10);
            i8++;
        }
        this.f8809b.d(f8);
        this.f8808a = w.b.a(0, dArr, dArr2);
    }

    public boolean i() {
        return this.f8813f == 1;
    }

    public String toString() {
        String str = this.f8811d;
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        Iterator<p> it = this.f8814g.iterator();
        while (it.hasNext()) {
            str = str + "[" + it.next().f8831a + " , " + decimalFormat.format(r3.f8832b) + "] ";
        }
        return str;
    }
}
