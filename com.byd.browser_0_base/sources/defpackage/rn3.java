package defpackage;

import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import androidx.constraintlayout.motion.widget.MotionLayout;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: ViewSpline.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class rn3 extends wq2 {

    /* JADX INFO: compiled from: ViewSpline.java */
    public static class a extends rn3 {
        @Override // defpackage.rn3
        public final void d(View view, float f) {
            view.setAlpha(a(f));
        }
    }

    /* JADX INFO: compiled from: ViewSpline.java */
    public static class b extends rn3 {
        public final SparseArray<ox> f;
        public float[] g;

        public b(SparseArray sparseArray, String str) {
            String str2 = str.split(",")[1];
            this.f = sparseArray;
        }

        @Override // defpackage.wq2
        public final void b(float f, int i) {
            throw new RuntimeException("call of custom attribute setPoint");
        }

        @Override // defpackage.wq2
        public final void c(int i) {
            SparseArray<ox> sparseArray = this.f;
            int size = sparseArray.size();
            int iC = sparseArray.valueAt(0).c();
            double[] dArr = new double[size];
            this.g = new float[iC];
            double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, size, iC);
            for (int i2 = 0; i2 < size; i2++) {
                int iKeyAt = sparseArray.keyAt(i2);
                ox oxVarValueAt = sparseArray.valueAt(i2);
                dArr[i2] = ((double) iKeyAt) * 0.01d;
                oxVarValueAt.b(this.g);
                int i3 = 0;
                while (true) {
                    if (i3 < this.g.length) {
                        dArr2[i2][i3] = r7[i3];
                        i3++;
                    }
                }
            }
            this.a = l20.a(i, dArr, dArr2);
        }

        @Override // defpackage.rn3
        public final void d(View view, float f) {
            this.a.d(f, this.g);
            a8.g0(this.f.valueAt(0), view, this.g);
        }
    }

    /* JADX INFO: compiled from: ViewSpline.java */
    public static class c extends rn3 {
        @Override // defpackage.rn3
        public final void d(View view, float f) {
            view.setElevation(a(f));
        }
    }

    /* JADX INFO: compiled from: ViewSpline.java */
    public static class e extends rn3 {
        @Override // defpackage.rn3
        public final void d(View view, float f) {
            view.setPivotX(a(f));
        }
    }

    /* JADX INFO: compiled from: ViewSpline.java */
    public static class f extends rn3 {
        @Override // defpackage.rn3
        public final void d(View view, float f) {
            view.setPivotY(a(f));
        }
    }

    /* JADX INFO: compiled from: ViewSpline.java */
    public static class g extends rn3 {
        public boolean f = false;

        @Override // defpackage.rn3
        public final void d(View view, float f) {
            Method method;
            if (view instanceof MotionLayout) {
                ((MotionLayout) view).setProgress(a(f));
                return;
            }
            if (this.f) {
                return;
            }
            try {
                method = view.getClass().getMethod("setProgress", Float.TYPE);
            } catch (NoSuchMethodException unused) {
                this.f = true;
                method = null;
            }
            if (method != null) {
                try {
                    method.invoke(view, Float.valueOf(a(f)));
                } catch (IllegalAccessException e) {
                    Log.e("ViewSpline", "unable to setProgress", e);
                } catch (InvocationTargetException e2) {
                    Log.e("ViewSpline", "unable to setProgress", e2);
                }
            }
        }
    }

    /* JADX INFO: compiled from: ViewSpline.java */
    public static class h extends rn3 {
        @Override // defpackage.rn3
        public final void d(View view, float f) {
            view.setRotation(a(f));
        }
    }

    /* JADX INFO: compiled from: ViewSpline.java */
    public static class i extends rn3 {
        @Override // defpackage.rn3
        public final void d(View view, float f) {
            view.setRotationX(a(f));
        }
    }

    /* JADX INFO: compiled from: ViewSpline.java */
    public static class j extends rn3 {
        @Override // defpackage.rn3
        public final void d(View view, float f) {
            view.setRotationY(a(f));
        }
    }

    /* JADX INFO: compiled from: ViewSpline.java */
    public static class k extends rn3 {
        @Override // defpackage.rn3
        public final void d(View view, float f) {
            view.setScaleX(a(f));
        }
    }

    /* JADX INFO: compiled from: ViewSpline.java */
    public static class l extends rn3 {
        @Override // defpackage.rn3
        public final void d(View view, float f) {
            view.setScaleY(a(f));
        }
    }

    /* JADX INFO: compiled from: ViewSpline.java */
    public static class m extends rn3 {
        @Override // defpackage.rn3
        public final void d(View view, float f) {
            view.setTranslationX(a(f));
        }
    }

    /* JADX INFO: compiled from: ViewSpline.java */
    public static class n extends rn3 {
        @Override // defpackage.rn3
        public final void d(View view, float f) {
            view.setTranslationY(a(f));
        }
    }

    /* JADX INFO: compiled from: ViewSpline.java */
    public static class o extends rn3 {
        @Override // defpackage.rn3
        public final void d(View view, float f) {
            view.setTranslationZ(a(f));
        }
    }

    public abstract void d(View view, float f2);

    /* JADX INFO: compiled from: ViewSpline.java */
    public static class d extends rn3 {
        @Override // defpackage.rn3
        public final void d(View view, float f) {
        }
    }
}
