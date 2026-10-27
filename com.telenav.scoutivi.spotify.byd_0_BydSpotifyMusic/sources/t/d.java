package t;

import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import androidx.constraintlayout.motion.widget.MotionLayout;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: ViewSpline.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class d extends p.j {

    /* JADX INFO: compiled from: ViewSpline.java */
    static class a extends d {
        a() {
        }

        @Override // t.d
        public void g(View view, float f7) {
            view.setAlpha(a(f7));
        }
    }

    /* JADX INFO: compiled from: ViewSpline.java */
    public static class b extends d {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        String f9527f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        SparseArray<androidx.constraintlayout.widget.a> f9528g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        float[] f9529h;

        public b(String str, SparseArray<androidx.constraintlayout.widget.a> sparseArray) {
            this.f9527f = str.split(",")[1];
            this.f9528g = sparseArray;
        }

        @Override // p.j
        public void b(int i7, float f7) {
            throw new RuntimeException("don't call for custom attribute call setPoint(pos, ConstraintAttribute)");
        }

        @Override // p.j
        public void d(int i7) {
            int size = this.f9528g.size();
            int iH = this.f9528g.valueAt(0).h();
            double[] dArr = new double[size];
            this.f9529h = new float[iH];
            double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) double.class, size, iH);
            for (int i8 = 0; i8 < size; i8++) {
                int iKeyAt = this.f9528g.keyAt(i8);
                androidx.constraintlayout.widget.a aVarValueAt = this.f9528g.valueAt(i8);
                dArr[i8] = ((double) iKeyAt) * 0.01d;
                aVarValueAt.f(this.f9529h);
                int i9 = 0;
                while (true) {
                    if (i9 < this.f9529h.length) {
                        dArr2[i8][i9] = r6[i9];
                        i9++;
                    }
                }
            }
            this.f8764a = p.b.a(i7, dArr, dArr2);
        }

        @Override // t.d
        public void g(View view, float f7) {
            this.f8764a.e(f7, this.f9529h);
            t.a.b(this.f9528g.valueAt(0), view, this.f9529h);
        }

        public void h(int i7, androidx.constraintlayout.widget.a aVar) {
            this.f9528g.append(i7, aVar);
        }
    }

    /* JADX INFO: compiled from: ViewSpline.java */
    static class c extends d {
        c() {
        }

        @Override // t.d
        public void g(View view, float f7) {
            view.setElevation(a(f7));
        }
    }

    /* JADX INFO: renamed from: t.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ViewSpline.java */
    public static class C0152d extends d {
        @Override // t.d
        public void g(View view, float f7) {
        }

        public void h(View view, float f7, double d7, double d8) {
            view.setRotation(a(f7) + ((float) Math.toDegrees(Math.atan2(d8, d7))));
        }
    }

    /* JADX INFO: compiled from: ViewSpline.java */
    static class e extends d {
        e() {
        }

        @Override // t.d
        public void g(View view, float f7) {
            view.setPivotX(a(f7));
        }
    }

    /* JADX INFO: compiled from: ViewSpline.java */
    static class f extends d {
        f() {
        }

        @Override // t.d
        public void g(View view, float f7) {
            view.setPivotY(a(f7));
        }
    }

    /* JADX INFO: compiled from: ViewSpline.java */
    static class g extends d {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        boolean f9530f = false;

        g() {
        }

        @Override // t.d
        public void g(View view, float f7) {
            if (view instanceof MotionLayout) {
                ((MotionLayout) view).setProgress(a(f7));
                return;
            }
            if (this.f9530f) {
                return;
            }
            Method method = null;
            try {
                method = view.getClass().getMethod("setProgress", Float.TYPE);
            } catch (NoSuchMethodException unused) {
                this.f9530f = true;
            }
            if (method != null) {
                try {
                    method.invoke(view, Float.valueOf(a(f7)));
                } catch (IllegalAccessException e7) {
                    Log.e("ViewSpline", "unable to setProgress", e7);
                } catch (InvocationTargetException e8) {
                    Log.e("ViewSpline", "unable to setProgress", e8);
                }
            }
        }
    }

    /* JADX INFO: compiled from: ViewSpline.java */
    static class h extends d {
        h() {
        }

        @Override // t.d
        public void g(View view, float f7) {
            view.setRotation(a(f7));
        }
    }

    /* JADX INFO: compiled from: ViewSpline.java */
    static class i extends d {
        i() {
        }

        @Override // t.d
        public void g(View view, float f7) {
            view.setRotationX(a(f7));
        }
    }

    /* JADX INFO: compiled from: ViewSpline.java */
    static class j extends d {
        j() {
        }

        @Override // t.d
        public void g(View view, float f7) {
            view.setRotationY(a(f7));
        }
    }

    /* JADX INFO: compiled from: ViewSpline.java */
    static class k extends d {
        k() {
        }

        @Override // t.d
        public void g(View view, float f7) {
            view.setScaleX(a(f7));
        }
    }

    /* JADX INFO: compiled from: ViewSpline.java */
    static class l extends d {
        l() {
        }

        @Override // t.d
        public void g(View view, float f7) {
            view.setScaleY(a(f7));
        }
    }

    /* JADX INFO: compiled from: ViewSpline.java */
    static class m extends d {
        m() {
        }

        @Override // t.d
        public void g(View view, float f7) {
            view.setTranslationX(a(f7));
        }
    }

    /* JADX INFO: compiled from: ViewSpline.java */
    static class n extends d {
        n() {
        }

        @Override // t.d
        public void g(View view, float f7) {
            view.setTranslationY(a(f7));
        }
    }

    /* JADX INFO: compiled from: ViewSpline.java */
    static class o extends d {
        o() {
        }

        @Override // t.d
        public void g(View view, float f7) {
            view.setTranslationZ(a(f7));
        }
    }

    public static d e(String str, SparseArray<androidx.constraintlayout.widget.a> sparseArray) {
        return new b(str, sparseArray);
    }

    public static d f(String str) {
        str.hashCode();
        switch (str) {
        }
        return new a();
    }

    public abstract void g(View view, float f7);
}
