package b.e.a.a;

import android.os.Build;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintAttribute;
import com.bydux.api.common.tools.util.ListUtils;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: ViewSpline.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class d extends androidx.constraintlayout.core.j.a.j {

    /* JADX INFO: compiled from: ViewSpline.java */
    static class a extends d {
        a() {
        }

        @Override // b.e.a.a.d
        public void h(View view, float f) {
            view.setAlpha(a(f));
        }
    }

    /* JADX INFO: compiled from: ViewSpline.java */
    public static class b extends d {
        String f;
        SparseArray<ConstraintAttribute> g;
        float[] h;

        public b(String str, SparseArray<ConstraintAttribute> sparseArray) {
            this.f = str.split(ListUtils.DEFAULT_JOIN_SEPARATOR)[1];
            this.g = sparseArray;
        }

        @Override // androidx.constraintlayout.core.j.a.j
        public void c(int i, float f) {
            throw new RuntimeException("don't call for custom attribute call setPoint(pos, ConstraintAttribute)");
        }

        @Override // androidx.constraintlayout.core.j.a.j
        public void e(int i) {
            int size = this.g.size();
            int iH = this.g.valueAt(0).h();
            double[] dArr = new double[size];
            this.h = new float[iH];
            double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) double.class, size, iH);
            for (int i2 = 0; i2 < size; i2++) {
                int iKeyAt = this.g.keyAt(i2);
                ConstraintAttribute constraintAttributeValueAt = this.g.valueAt(i2);
                dArr[i2] = ((double) iKeyAt) * 0.01d;
                constraintAttributeValueAt.f(this.h);
                int i3 = 0;
                while (true) {
                    if (i3 < this.h.length) {
                        dArr2[i2][i3] = r6[i3];
                        i3++;
                    }
                }
            }
            this.f784a = androidx.constraintlayout.core.j.a.b.a(i, dArr, dArr2);
        }

        @Override // b.e.a.a.d
        public void h(View view, float f) {
            this.f784a.e(f, this.h);
            b.e.a.a.a.b(this.g.valueAt(0), view, this.h);
        }

        public void i(int i, ConstraintAttribute constraintAttribute) {
            this.g.append(i, constraintAttribute);
        }
    }

    /* JADX INFO: compiled from: ViewSpline.java */
    static class c extends d {
        c() {
        }

        @Override // b.e.a.a.d
        public void h(View view, float f) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setElevation(a(f));
            }
        }
    }

    /* JADX INFO: renamed from: b.e.a.a.d$d */
    /* JADX INFO: compiled from: ViewSpline.java */
    public static class C0045d extends d {
        @Override // b.e.a.a.d
        public void h(View view, float f) {
        }

        public void i(View view, float f, double d2, double d3) {
            view.setRotation(a(f) + ((float) Math.toDegrees(Math.atan2(d3, d2))));
        }
    }

    /* JADX INFO: compiled from: ViewSpline.java */
    static class e extends d {
        e() {
        }

        @Override // b.e.a.a.d
        public void h(View view, float f) {
            view.setPivotX(a(f));
        }
    }

    /* JADX INFO: compiled from: ViewSpline.java */
    static class f extends d {
        f() {
        }

        @Override // b.e.a.a.d
        public void h(View view, float f) {
            view.setPivotY(a(f));
        }
    }

    /* JADX INFO: compiled from: ViewSpline.java */
    static class g extends d {
        boolean f = false;

        g() {
        }

        @Override // b.e.a.a.d
        public void h(View view, float f) {
            if (view instanceof MotionLayout) {
                ((MotionLayout) view).setProgress(a(f));
                return;
            }
            if (this.f) {
                return;
            }
            Method method = null;
            try {
                method = view.getClass().getMethod("setProgress", Float.TYPE);
            } catch (NoSuchMethodException unused) {
                this.f = true;
            }
            if (method != null) {
                try {
                    method.invoke(view, Float.valueOf(a(f)));
                } catch (IllegalAccessException e2) {
                    Log.e("ViewSpline", "unable to setProgress", e2);
                } catch (InvocationTargetException e3) {
                    Log.e("ViewSpline", "unable to setProgress", e3);
                }
            }
        }
    }

    /* JADX INFO: compiled from: ViewSpline.java */
    static class h extends d {
        h() {
        }

        @Override // b.e.a.a.d
        public void h(View view, float f) {
            view.setRotation(a(f));
        }
    }

    /* JADX INFO: compiled from: ViewSpline.java */
    static class i extends d {
        i() {
        }

        @Override // b.e.a.a.d
        public void h(View view, float f) {
            view.setRotationX(a(f));
        }
    }

    /* JADX INFO: compiled from: ViewSpline.java */
    static class j extends d {
        j() {
        }

        @Override // b.e.a.a.d
        public void h(View view, float f) {
            view.setRotationY(a(f));
        }
    }

    /* JADX INFO: compiled from: ViewSpline.java */
    static class k extends d {
        k() {
        }

        @Override // b.e.a.a.d
        public void h(View view, float f) {
            view.setScaleX(a(f));
        }
    }

    /* JADX INFO: compiled from: ViewSpline.java */
    static class l extends d {
        l() {
        }

        @Override // b.e.a.a.d
        public void h(View view, float f) {
            view.setScaleY(a(f));
        }
    }

    /* JADX INFO: compiled from: ViewSpline.java */
    static class m extends d {
        m() {
        }

        @Override // b.e.a.a.d
        public void h(View view, float f) {
            view.setTranslationX(a(f));
        }
    }

    /* JADX INFO: compiled from: ViewSpline.java */
    static class n extends d {
        n() {
        }

        @Override // b.e.a.a.d
        public void h(View view, float f) {
            view.setTranslationY(a(f));
        }
    }

    /* JADX INFO: compiled from: ViewSpline.java */
    static class o extends d {
        o() {
        }

        @Override // b.e.a.a.d
        public void h(View view, float f) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setTranslationZ(a(f));
            }
        }
    }

    public static d f(String str, SparseArray<ConstraintAttribute> sparseArray) {
        return new b(str, sparseArray);
    }

    public static d g(String str) {
        str.hashCode();
        switch (str) {
        }
        return new a();
    }

    public abstract void h(View view, float f2);
}
