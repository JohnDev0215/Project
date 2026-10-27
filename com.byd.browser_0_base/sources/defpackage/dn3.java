package defpackage;

import android.util.Log;
import android.view.View;
import androidx.constraintlayout.motion.widget.MotionLayout;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: ViewOscillator.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class dn3 extends d41 {

    /* JADX INFO: compiled from: ViewOscillator.java */
    public static class a extends dn3 {
        @Override // defpackage.dn3
        public final void e(View view, float f) {
            view.setAlpha(a(f));
        }
    }

    /* JADX INFO: compiled from: ViewOscillator.java */
    public static class b extends dn3 {
        public final float[] g = new float[1];
        public ox h;

        @Override // defpackage.d41
        public final void c(ox oxVar) {
            this.h = oxVar;
        }

        @Override // defpackage.dn3
        public final void e(View view, float f) {
            float fA = a(f);
            float[] fArr = this.g;
            fArr[0] = fA;
            a8.g0(this.h, view, fArr);
        }
    }

    /* JADX INFO: compiled from: ViewOscillator.java */
    public static class c extends dn3 {
        @Override // defpackage.dn3
        public final void e(View view, float f) {
            view.setElevation(a(f));
        }
    }

    /* JADX INFO: compiled from: ViewOscillator.java */
    public static class e extends dn3 {
        public boolean g = false;

        @Override // defpackage.dn3
        public final void e(View view, float f) {
            Method method;
            if (view instanceof MotionLayout) {
                ((MotionLayout) view).setProgress(a(f));
                return;
            }
            if (this.g) {
                return;
            }
            try {
                method = view.getClass().getMethod("setProgress", Float.TYPE);
            } catch (NoSuchMethodException unused) {
                this.g = true;
                method = null;
            }
            if (method != null) {
                try {
                    method.invoke(view, Float.valueOf(a(f)));
                } catch (IllegalAccessException e) {
                    Log.e("ViewOscillator", "unable to setProgress", e);
                } catch (InvocationTargetException e2) {
                    Log.e("ViewOscillator", "unable to setProgress", e2);
                }
            }
        }
    }

    /* JADX INFO: compiled from: ViewOscillator.java */
    public static class f extends dn3 {
        @Override // defpackage.dn3
        public final void e(View view, float f) {
            view.setRotation(a(f));
        }
    }

    /* JADX INFO: compiled from: ViewOscillator.java */
    public static class g extends dn3 {
        @Override // defpackage.dn3
        public final void e(View view, float f) {
            view.setRotationX(a(f));
        }
    }

    /* JADX INFO: compiled from: ViewOscillator.java */
    public static class h extends dn3 {
        @Override // defpackage.dn3
        public final void e(View view, float f) {
            view.setRotationY(a(f));
        }
    }

    /* JADX INFO: compiled from: ViewOscillator.java */
    public static class i extends dn3 {
        @Override // defpackage.dn3
        public final void e(View view, float f) {
            view.setScaleX(a(f));
        }
    }

    /* JADX INFO: compiled from: ViewOscillator.java */
    public static class j extends dn3 {
        @Override // defpackage.dn3
        public final void e(View view, float f) {
            view.setScaleY(a(f));
        }
    }

    /* JADX INFO: compiled from: ViewOscillator.java */
    public static class k extends dn3 {
        @Override // defpackage.dn3
        public final void e(View view, float f) {
            view.setTranslationX(a(f));
        }
    }

    /* JADX INFO: compiled from: ViewOscillator.java */
    public static class l extends dn3 {
        @Override // defpackage.dn3
        public final void e(View view, float f) {
            view.setTranslationY(a(f));
        }
    }

    /* JADX INFO: compiled from: ViewOscillator.java */
    public static class m extends dn3 {
        @Override // defpackage.dn3
        public final void e(View view, float f) {
            view.setTranslationZ(a(f));
        }
    }

    public abstract void e(View view, float f2);

    /* JADX INFO: compiled from: ViewOscillator.java */
    public static class d extends dn3 {
        @Override // defpackage.dn3
        public final void e(View view, float f) {
        }
    }
}
