package t;

import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import androidx.constraintlayout.motion.widget.MotionLayout;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import p.o;

/* JADX INFO: compiled from: ViewTimeCycle.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class f extends o {

    /* JADX INFO: compiled from: ViewTimeCycle.java */
    static class a extends f {
        a() {
        }

        @Override // t.f
        public boolean i(View view, float f7, long j7, p.d dVar) {
            view.setAlpha(f(f7, j7, view, dVar));
            return this.f8804h;
        }
    }

    /* JADX INFO: compiled from: ViewTimeCycle.java */
    public static class b extends f {

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        String f9536l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        SparseArray<androidx.constraintlayout.widget.a> f9537m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        SparseArray<float[]> f9538n = new SparseArray<>();

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        float[] f9539o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        float[] f9540p;

        public b(String str, SparseArray<androidx.constraintlayout.widget.a> sparseArray) {
            this.f9536l = str.split(",")[1];
            this.f9537m = sparseArray;
        }

        @Override // p.o
        public void b(int i7, float f7, float f8, int i8, float f9) {
            throw new RuntimeException("don't call for custom attribute call setPoint(pos, ConstraintAttribute,...)");
        }

        @Override // p.o
        public void e(int i7) {
            int size = this.f9537m.size();
            int iH = this.f9537m.valueAt(0).h();
            double[] dArr = new double[size];
            int i8 = iH + 2;
            this.f9539o = new float[i8];
            this.f9540p = new float[iH];
            double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) double.class, size, i8);
            for (int i9 = 0; i9 < size; i9++) {
                int iKeyAt = this.f9537m.keyAt(i9);
                androidx.constraintlayout.widget.a aVarValueAt = this.f9537m.valueAt(i9);
                float[] fArrValueAt = this.f9538n.valueAt(i9);
                dArr[i9] = ((double) iKeyAt) * 0.01d;
                aVarValueAt.f(this.f9539o);
                int i10 = 0;
                while (true) {
                    if (i10 < this.f9539o.length) {
                        dArr2[i9][i10] = r8[i10];
                        i10++;
                    }
                }
                dArr2[i9][iH] = fArrValueAt[0];
                dArr2[i9][iH + 1] = fArrValueAt[1];
            }
            this.f8797a = p.b.a(i7, dArr, dArr2);
        }

        @Override // t.f
        public boolean i(View view, float f7, long j7, p.d dVar) {
            this.f8797a.e(f7, this.f9539o);
            float[] fArr = this.f9539o;
            float f8 = fArr[fArr.length - 2];
            float f9 = fArr[fArr.length - 1];
            long j8 = j7 - this.f8805i;
            if (Float.isNaN(this.f8806j)) {
                float fA = dVar.a(view, this.f9536l, 0);
                this.f8806j = fA;
                if (Float.isNaN(fA)) {
                    this.f8806j = 0.0f;
                }
            }
            float f10 = (float) ((((double) this.f8806j) + ((j8 * 1.0E-9d) * ((double) f8))) % 1.0d);
            this.f8806j = f10;
            this.f8805i = j7;
            float fA2 = a(f10);
            this.f8804h = false;
            int i7 = 0;
            while (true) {
                float[] fArr2 = this.f9540p;
                if (i7 >= fArr2.length) {
                    break;
                }
                boolean z6 = this.f8804h;
                float[] fArr3 = this.f9539o;
                this.f8804h = z6 | (((double) fArr3[i7]) != 0.0d);
                fArr2[i7] = (fArr3[i7] * fA2) + f9;
                i7++;
            }
            t.a.b(this.f9537m.valueAt(0), view, this.f9540p);
            if (f8 != 0.0f) {
                this.f8804h = true;
            }
            return this.f8804h;
        }

        public void j(int i7, androidx.constraintlayout.widget.a aVar, float f7, int i8, float f8) {
            this.f9537m.append(i7, aVar);
            this.f9538n.append(i7, new float[]{f7, f8});
            this.f8798b = Math.max(this.f8798b, i8);
        }
    }

    /* JADX INFO: compiled from: ViewTimeCycle.java */
    static class c extends f {
        c() {
        }

        @Override // t.f
        public boolean i(View view, float f7, long j7, p.d dVar) {
            view.setElevation(f(f7, j7, view, dVar));
            return this.f8804h;
        }
    }

    /* JADX INFO: compiled from: ViewTimeCycle.java */
    public static class d extends f {
        @Override // t.f
        public boolean i(View view, float f7, long j7, p.d dVar) {
            return this.f8804h;
        }

        public boolean j(View view, p.d dVar, float f7, long j7, double d7, double d8) {
            view.setRotation(f(f7, j7, view, dVar) + ((float) Math.toDegrees(Math.atan2(d8, d7))));
            return this.f8804h;
        }
    }

    /* JADX INFO: compiled from: ViewTimeCycle.java */
    static class e extends f {

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        boolean f9541l = false;

        e() {
        }

        @Override // t.f
        public boolean i(View view, float f7, long j7, p.d dVar) {
            if (view instanceof MotionLayout) {
                ((MotionLayout) view).setProgress(f(f7, j7, view, dVar));
            } else {
                if (this.f9541l) {
                    return false;
                }
                Method method = null;
                try {
                    method = view.getClass().getMethod("setProgress", Float.TYPE);
                } catch (NoSuchMethodException unused) {
                    this.f9541l = true;
                }
                Method method2 = method;
                if (method2 != null) {
                    try {
                        method2.invoke(view, Float.valueOf(f(f7, j7, view, dVar)));
                    } catch (IllegalAccessException e7) {
                        Log.e("ViewTimeCycle", "unable to setProgress", e7);
                    } catch (InvocationTargetException e8) {
                        Log.e("ViewTimeCycle", "unable to setProgress", e8);
                    }
                }
            }
            return this.f8804h;
        }
    }

    /* JADX INFO: renamed from: t.f$f, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ViewTimeCycle.java */
    static class C0153f extends f {
        C0153f() {
        }

        @Override // t.f
        public boolean i(View view, float f7, long j7, p.d dVar) {
            view.setRotation(f(f7, j7, view, dVar));
            return this.f8804h;
        }
    }

    /* JADX INFO: compiled from: ViewTimeCycle.java */
    static class g extends f {
        g() {
        }

        @Override // t.f
        public boolean i(View view, float f7, long j7, p.d dVar) {
            view.setRotationX(f(f7, j7, view, dVar));
            return this.f8804h;
        }
    }

    /* JADX INFO: compiled from: ViewTimeCycle.java */
    static class h extends f {
        h() {
        }

        @Override // t.f
        public boolean i(View view, float f7, long j7, p.d dVar) {
            view.setRotationY(f(f7, j7, view, dVar));
            return this.f8804h;
        }
    }

    /* JADX INFO: compiled from: ViewTimeCycle.java */
    static class i extends f {
        i() {
        }

        @Override // t.f
        public boolean i(View view, float f7, long j7, p.d dVar) {
            view.setScaleX(f(f7, j7, view, dVar));
            return this.f8804h;
        }
    }

    /* JADX INFO: compiled from: ViewTimeCycle.java */
    static class j extends f {
        j() {
        }

        @Override // t.f
        public boolean i(View view, float f7, long j7, p.d dVar) {
            view.setScaleY(f(f7, j7, view, dVar));
            return this.f8804h;
        }
    }

    /* JADX INFO: compiled from: ViewTimeCycle.java */
    static class k extends f {
        k() {
        }

        @Override // t.f
        public boolean i(View view, float f7, long j7, p.d dVar) {
            view.setTranslationX(f(f7, j7, view, dVar));
            return this.f8804h;
        }
    }

    /* JADX INFO: compiled from: ViewTimeCycle.java */
    static class l extends f {
        l() {
        }

        @Override // t.f
        public boolean i(View view, float f7, long j7, p.d dVar) {
            view.setTranslationY(f(f7, j7, view, dVar));
            return this.f8804h;
        }
    }

    /* JADX INFO: compiled from: ViewTimeCycle.java */
    static class m extends f {
        m() {
        }

        @Override // t.f
        public boolean i(View view, float f7, long j7, p.d dVar) {
            view.setTranslationZ(f(f7, j7, view, dVar));
            return this.f8804h;
        }
    }

    public static f g(String str, SparseArray<androidx.constraintlayout.widget.a> sparseArray) {
        return new b(str, sparseArray);
    }

    public static f h(String str, long j7) {
        f gVar;
        str.hashCode();
        switch (str) {
            case "rotationX":
                gVar = new g();
                break;
            case "rotationY":
                gVar = new h();
                break;
            case "translationX":
                gVar = new k();
                break;
            case "translationY":
                gVar = new l();
                break;
            case "translationZ":
                gVar = new m();
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
                gVar = new C0153f();
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
        gVar.c(j7);
        return gVar;
    }

    public float f(float f7, long j7, View view, p.d dVar) {
        this.f8797a.e(f7, this.f8803g);
        float[] fArr = this.f8803g;
        float f8 = fArr[1];
        if (f8 == 0.0f) {
            this.f8804h = false;
            return fArr[2];
        }
        if (Float.isNaN(this.f8806j)) {
            float fA = dVar.a(view, this.f8802f, 0);
            this.f8806j = fA;
            if (Float.isNaN(fA)) {
                this.f8806j = 0.0f;
            }
        }
        float f9 = (float) ((((double) this.f8806j) + (((j7 - this.f8805i) * 1.0E-9d) * ((double) f8))) % 1.0d);
        this.f8806j = f9;
        dVar.b(view, this.f8802f, 0, f9);
        this.f8805i = j7;
        float f10 = this.f8803g[0];
        float fA2 = (a(this.f8806j) * f10) + this.f8803g[2];
        this.f8804h = (f10 == 0.0f && f8 == 0.0f) ? false : true;
        return fA2;
    }

    public abstract boolean i(View view, float f7, long j7, p.d dVar);
}
