package u;

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
    public t.b f5094a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public d f5095b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ConstraintAttribute f5096c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f5097d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f5098e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f5099f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ArrayList<p> f5100g = new ArrayList<>();

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public class a implements Comparator<p> {
        public a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(p pVar, p pVar2) {
            return Integer.compare(pVar.f5117a, pVar2.f5117a);
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class b extends g {
        @Override // u.g
        public void f(View view, float f3) {
            view.setAlpha(a(f3));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class c extends g {

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public float[] f5102h = new float[1];

        @Override // u.g
        public void f(View view, float f3) {
            this.f5102h[0] = a(f3);
            this.f5096c.i(view, this.f5102h);
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f5103a;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float[] f5105c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public double[] f5106d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float[] f5107e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public float[] f5108f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public float[] f5109g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f5110h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public t.b f5111i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public double[] f5112j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public double[] f5113k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public float f5114l;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public t.f f5104b = new t.f();

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public HashMap<String, ConstraintAttribute> f5115m = new HashMap<>();

        public d(int i3, int i4, int i5) {
            this.f5110h = i3;
            this.f5103a = i4;
            this.f5104b.g(i3);
            this.f5105c = new float[i5];
            this.f5106d = new double[i5];
            this.f5107e = new float[i5];
            this.f5108f = new float[i5];
            this.f5109g = new float[i5];
        }

        public double a(float f3) {
            t.b bVar = this.f5111i;
            if (bVar != null) {
                double d3 = f3;
                bVar.g(d3, this.f5113k);
                this.f5111i.d(d3, this.f5112j);
            } else {
                double[] dArr = this.f5113k;
                dArr[0] = 0.0d;
                dArr[1] = 0.0d;
            }
            double d4 = f3;
            double dE = this.f5104b.e(d4);
            double d5 = this.f5104b.d(d4);
            double[] dArr2 = this.f5113k;
            return dArr2[0] + (dE * dArr2[1]) + (d5 * this.f5112j[1]);
        }

        public double b(float f3) {
            t.b bVar = this.f5111i;
            if (bVar != null) {
                bVar.d(f3, this.f5112j);
            } else {
                double[] dArr = this.f5112j;
                dArr[0] = this.f5108f[0];
                dArr[1] = this.f5105c[0];
            }
            return this.f5112j[0] + (this.f5104b.e(f3) * this.f5112j[1]);
        }

        public void c(int i3, int i4, float f3, float f4, float f5) {
            this.f5106d[i3] = ((double) i4) / 100.0d;
            this.f5107e[i3] = f3;
            this.f5108f[i3] = f4;
            this.f5105c[i3] = f5;
        }

        public void d(float f3) {
            this.f5114l = f3;
            double[][] dArr = (double[][]) Array.newInstance((Class<?>) double.class, this.f5106d.length, 2);
            float[] fArr = this.f5105c;
            this.f5112j = new double[fArr.length + 1];
            this.f5113k = new double[fArr.length + 1];
            if (this.f5106d[0] > 0.0d) {
                this.f5104b.a(0.0d, this.f5107e[0]);
            }
            double[] dArr2 = this.f5106d;
            int length = dArr2.length - 1;
            if (dArr2[length] < 1.0d) {
                this.f5104b.a(1.0d, this.f5107e[length]);
            }
            for (int i3 = 0; i3 < dArr.length; i3++) {
                dArr[i3][0] = this.f5108f[i3];
                int i4 = 0;
                while (true) {
                    if (i4 < this.f5105c.length) {
                        dArr[i4][1] = r4[i4];
                        i4++;
                    }
                }
                this.f5104b.a(this.f5106d[i3], this.f5107e[i3]);
            }
            this.f5104b.f();
            double[] dArr3 = this.f5106d;
            if (dArr3.length > 1) {
                this.f5111i = t.b.a(0, dArr3, dArr);
            } else {
                this.f5111i = null;
            }
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class e extends g {
        @Override // u.g
        public void f(View view, float f3) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setElevation(a(f3));
            }
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class f extends g {
        @Override // u.g
        public void f(View view, float f3) {
        }

        public void j(View view, float f3, double d3, double d4) {
            view.setRotation(a(f3) + ((float) Math.toDegrees(Math.atan2(d4, d3))));
        }
    }

    /* JADX INFO: renamed from: u.g$g, reason: collision with other inner class name */
    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class C0065g extends g {

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public boolean f5116h = false;

        @Override // u.g
        public void f(View view, float f3) {
            if (view instanceof MotionLayout) {
                ((MotionLayout) view).setProgress(a(f3));
                return;
            }
            if (this.f5116h) {
                return;
            }
            Method method = null;
            try {
                method = view.getClass().getMethod("setProgress", Float.TYPE);
            } catch (NoSuchMethodException unused) {
                this.f5116h = true;
            }
            if (method != null) {
                try {
                    method.invoke(view, Float.valueOf(a(f3)));
                } catch (IllegalAccessException e3) {
                    Log.e("KeyCycleOscillator", "unable to setProgress", e3);
                } catch (InvocationTargetException e4) {
                    Log.e("KeyCycleOscillator", "unable to setProgress", e4);
                }
            }
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class h extends g {
        @Override // u.g
        public void f(View view, float f3) {
            view.setRotation(a(f3));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class i extends g {
        @Override // u.g
        public void f(View view, float f3) {
            view.setRotationX(a(f3));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class j extends g {
        @Override // u.g
        public void f(View view, float f3) {
            view.setRotationY(a(f3));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class k extends g {
        @Override // u.g
        public void f(View view, float f3) {
            view.setScaleX(a(f3));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class l extends g {
        @Override // u.g
        public void f(View view, float f3) {
            view.setScaleY(a(f3));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class m extends g {
        @Override // u.g
        public void f(View view, float f3) {
            view.setTranslationX(a(f3));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class n extends g {
        @Override // u.g
        public void f(View view, float f3) {
            view.setTranslationY(a(f3));
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class o extends g {
        @Override // u.g
        public void f(View view, float f3) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setTranslationZ(a(f3));
            }
        }
    }

    /* JADX INFO: compiled from: KeyCycleOscillator.java */
    public static class p {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f5117a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f5118b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f5119c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f5120d;

        public p(int i3, float f3, float f4, float f5) {
            this.f5117a = i3;
            this.f5118b = f5;
            this.f5119c = f4;
            this.f5120d = f3;
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
                return new C0065g();
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

    public float a(float f3) {
        return (float) this.f5095b.b(f3);
    }

    public float b(float f3) {
        return (float) this.f5095b.a(f3);
    }

    public void d(int i3, int i4, int i5, float f3, float f4, float f5) {
        this.f5100g.add(new p(i3, f3, f4, f5));
        if (i5 != -1) {
            this.f5099f = i5;
        }
        this.f5098e = i4;
    }

    public void e(int i3, int i4, int i5, float f3, float f4, float f5, ConstraintAttribute constraintAttribute) {
        this.f5100g.add(new p(i3, f3, f4, f5));
        if (i5 != -1) {
            this.f5099f = i5;
        }
        this.f5098e = i4;
        this.f5096c = constraintAttribute;
    }

    public abstract void f(View view, float f3);

    public void g(String str) {
        this.f5097d = str;
    }

    @TargetApi(19)
    public void h(float f3) {
        int size = this.f5100g.size();
        if (size == 0) {
            return;
        }
        Collections.sort(this.f5100g, new a());
        double[] dArr = new double[size];
        double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) double.class, size, 2);
        this.f5095b = new d(this.f5098e, this.f5099f, size);
        int i3 = 0;
        for (p pVar : this.f5100g) {
            float f4 = pVar.f5120d;
            dArr[i3] = ((double) f4) * 0.01d;
            double[] dArr3 = dArr2[i3];
            float f5 = pVar.f5118b;
            dArr3[0] = f5;
            double[] dArr4 = dArr2[i3];
            float f6 = pVar.f5119c;
            dArr4[1] = f6;
            this.f5095b.c(i3, pVar.f5117a, f4, f6, f5);
            i3++;
        }
        this.f5095b.d(f3);
        this.f5094a = t.b.a(0, dArr, dArr2);
    }

    public boolean i() {
        return this.f5099f == 1;
    }

    public String toString() {
        String str = this.f5097d;
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        Iterator<p> it = this.f5100g.iterator();
        while (it.hasNext()) {
            str = str + "[" + it.next().f5117a + " , " + decimalFormat.format(r3.f5118b) + "] ";
        }
        return str;
    }
}
