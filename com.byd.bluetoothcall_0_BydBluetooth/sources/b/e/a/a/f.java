package b.e.a.a;

import android.os.Build;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import androidx.constraintlayout.core.j.a.o;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintAttribute;
import com.bydux.api.common.tools.util.ListUtils;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: ViewTimeCycle.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class f extends o {

    /* JADX INFO: compiled from: ViewTimeCycle.java */
    static class a extends f {
        a() {
        }

        @Override // b.e.a.a.f
        public boolean i(View view, float f, long j, androidx.constraintlayout.core.j.a.d dVar) {
            view.setAlpha(f(f, j, view, dVar));
            return this.i;
        }
    }

    /* JADX INFO: compiled from: ViewTimeCycle.java */
    public static class b extends f {
        String l;
        SparseArray<ConstraintAttribute> m;
        SparseArray<float[]> n = new SparseArray<>();
        float[] o;
        float[] p;

        public b(String str, SparseArray<ConstraintAttribute> sparseArray) {
            this.l = str.split(ListUtils.DEFAULT_JOIN_SEPARATOR)[1];
            this.m = sparseArray;
        }

        @Override // androidx.constraintlayout.core.j.a.o
        public void b(int i, float f, float f2, int i2, float f3) {
            throw new RuntimeException("don't call for custom attribute call setPoint(pos, ConstraintAttribute,...)");
        }

        @Override // androidx.constraintlayout.core.j.a.o
        public void e(int i) {
            int size = this.m.size();
            int iH = this.m.valueAt(0).h();
            double[] dArr = new double[size];
            int i2 = iH + 2;
            this.o = new float[i2];
            this.p = new float[iH];
            double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) double.class, size, i2);
            for (int i3 = 0; i3 < size; i3++) {
                int iKeyAt = this.m.keyAt(i3);
                ConstraintAttribute constraintAttributeValueAt = this.m.valueAt(i3);
                float[] fArrValueAt = this.n.valueAt(i3);
                dArr[i3] = ((double) iKeyAt) * 0.01d;
                constraintAttributeValueAt.f(this.o);
                int i4 = 0;
                while (true) {
                    if (i4 < this.o.length) {
                        dArr2[i3][i4] = r8[i4];
                        i4++;
                    }
                }
                dArr2[i3][iH] = fArrValueAt[0];
                dArr2[i3][iH + 1] = fArrValueAt[1];
            }
            this.f801b = androidx.constraintlayout.core.j.a.b.a(i, dArr, dArr2);
        }

        @Override // b.e.a.a.f
        public boolean i(View view, float f, long j, androidx.constraintlayout.core.j.a.d dVar) {
            this.f801b.e(f, this.o);
            float[] fArr = this.o;
            float f2 = fArr[fArr.length - 2];
            float f3 = fArr[fArr.length - 1];
            long j2 = j - this.j;
            if (Float.isNaN(this.k)) {
                float fA = dVar.a(view, this.l, 0);
                this.k = fA;
                if (Float.isNaN(fA)) {
                    this.k = 0.0f;
                }
            }
            float f4 = (float) ((((double) this.k) + ((j2 * 1.0E-9d) * ((double) f2))) % 1.0d);
            this.k = f4;
            this.j = j;
            float fA2 = a(f4);
            this.i = false;
            int i = 0;
            while (true) {
                float[] fArr2 = this.p;
                if (i >= fArr2.length) {
                    break;
                }
                boolean z = this.i;
                float[] fArr3 = this.o;
                this.i = z | (((double) fArr3[i]) != 0.0d);
                fArr2[i] = (fArr3[i] * fA2) + f3;
                i++;
            }
            b.e.a.a.a.b(this.m.valueAt(0), view, this.p);
            if (f2 != 0.0f) {
                this.i = true;
            }
            return this.i;
        }

        public void j(int i, ConstraintAttribute constraintAttribute, float f, int i2, float f2) {
            this.m.append(i, constraintAttribute);
            this.n.append(i, new float[]{f, f2});
            this.f802c = Math.max(this.f802c, i2);
        }
    }

    /* JADX INFO: compiled from: ViewTimeCycle.java */
    static class c extends f {
        c() {
        }

        @Override // b.e.a.a.f
        public boolean i(View view, float f, long j, androidx.constraintlayout.core.j.a.d dVar) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setElevation(f(f, j, view, dVar));
            }
            return this.i;
        }
    }

    /* JADX INFO: compiled from: ViewTimeCycle.java */
    public static class d extends f {
        @Override // b.e.a.a.f
        public boolean i(View view, float f, long j, androidx.constraintlayout.core.j.a.d dVar) {
            return this.i;
        }

        public boolean j(View view, androidx.constraintlayout.core.j.a.d dVar, float f, long j, double d2, double d3) {
            view.setRotation(f(f, j, view, dVar) + ((float) Math.toDegrees(Math.atan2(d3, d2))));
            return this.i;
        }
    }

    /* JADX INFO: compiled from: ViewTimeCycle.java */
    static class e extends f {
        boolean l = false;

        e() {
        }

        @Override // b.e.a.a.f
        public boolean i(View view, float f, long j, androidx.constraintlayout.core.j.a.d dVar) {
            if (view instanceof MotionLayout) {
                ((MotionLayout) view).setProgress(f(f, j, view, dVar));
            } else {
                if (this.l) {
                    return false;
                }
                Method method = null;
                try {
                    method = view.getClass().getMethod("setProgress", Float.TYPE);
                } catch (NoSuchMethodException unused) {
                    this.l = true;
                }
                Method method2 = method;
                if (method2 != null) {
                    try {
                        method2.invoke(view, Float.valueOf(f(f, j, view, dVar)));
                    } catch (IllegalAccessException e2) {
                        Log.e("ViewTimeCycle", "unable to setProgress", e2);
                    } catch (InvocationTargetException e3) {
                        Log.e("ViewTimeCycle", "unable to setProgress", e3);
                    }
                }
            }
            return this.i;
        }
    }

    /* JADX INFO: renamed from: b.e.a.a.f$f, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ViewTimeCycle.java */
    static class C0046f extends f {
        C0046f() {
        }

        @Override // b.e.a.a.f
        public boolean i(View view, float f, long j, androidx.constraintlayout.core.j.a.d dVar) {
            view.setRotation(f(f, j, view, dVar));
            return this.i;
        }
    }

    /* JADX INFO: compiled from: ViewTimeCycle.java */
    static class g extends f {
        g() {
        }

        @Override // b.e.a.a.f
        public boolean i(View view, float f, long j, androidx.constraintlayout.core.j.a.d dVar) {
            view.setRotationX(f(f, j, view, dVar));
            return this.i;
        }
    }

    /* JADX INFO: compiled from: ViewTimeCycle.java */
    static class h extends f {
        h() {
        }

        @Override // b.e.a.a.f
        public boolean i(View view, float f, long j, androidx.constraintlayout.core.j.a.d dVar) {
            view.setRotationY(f(f, j, view, dVar));
            return this.i;
        }
    }

    /* JADX INFO: compiled from: ViewTimeCycle.java */
    static class i extends f {
        i() {
        }

        @Override // b.e.a.a.f
        public boolean i(View view, float f, long j, androidx.constraintlayout.core.j.a.d dVar) {
            view.setScaleX(f(f, j, view, dVar));
            return this.i;
        }
    }

    /* JADX INFO: compiled from: ViewTimeCycle.java */
    static class j extends f {
        j() {
        }

        @Override // b.e.a.a.f
        public boolean i(View view, float f, long j, androidx.constraintlayout.core.j.a.d dVar) {
            view.setScaleY(f(f, j, view, dVar));
            return this.i;
        }
    }

    /* JADX INFO: compiled from: ViewTimeCycle.java */
    static class k extends f {
        k() {
        }

        @Override // b.e.a.a.f
        public boolean i(View view, float f, long j, androidx.constraintlayout.core.j.a.d dVar) {
            view.setTranslationX(f(f, j, view, dVar));
            return this.i;
        }
    }

    /* JADX INFO: compiled from: ViewTimeCycle.java */
    static class l extends f {
        l() {
        }

        @Override // b.e.a.a.f
        public boolean i(View view, float f, long j, androidx.constraintlayout.core.j.a.d dVar) {
            view.setTranslationY(f(f, j, view, dVar));
            return this.i;
        }
    }

    /* JADX INFO: compiled from: ViewTimeCycle.java */
    static class m extends f {
        m() {
        }

        @Override // b.e.a.a.f
        public boolean i(View view, float f, long j, androidx.constraintlayout.core.j.a.d dVar) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setTranslationZ(f(f, j, view, dVar));
            }
            return this.i;
        }
    }

    public static f g(String str, SparseArray<ConstraintAttribute> sparseArray) {
        return new b(str, sparseArray);
    }

    public static f h(String str, long j2) {
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
                gVar = new C0046f();
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
        gVar.c(j2);
        return gVar;
    }

    public float f(float f, long j2, View view, androidx.constraintlayout.core.j.a.d dVar) {
        this.f801b.e(f, this.h);
        float[] fArr = this.h;
        float f2 = fArr[1];
        if (f2 == 0.0f) {
            this.i = false;
            return fArr[2];
        }
        if (Float.isNaN(this.k)) {
            float fA = dVar.a(view, this.g, 0);
            this.k = fA;
            if (Float.isNaN(fA)) {
                this.k = 0.0f;
            }
        }
        float f3 = (float) ((((double) this.k) + (((j2 - this.j) * 1.0E-9d) * ((double) f2))) % 1.0d);
        this.k = f3;
        dVar.b(view, this.g, 0, f3);
        this.j = j2;
        float f4 = this.h[0];
        float fA2 = (a(this.k) * f4) + this.h[2];
        this.i = (f4 == 0.0f && f2 == 0.0f) ? false : true;
        return fA2;
    }

    public abstract boolean i(View view, float f, long j2, androidx.constraintlayout.core.j.a.d dVar);
}
