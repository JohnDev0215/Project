package b.e.a.a;

import android.os.Build;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintAttribute;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: ViewOscillator.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class c extends androidx.constraintlayout.core.j.a.e {

    /* JADX INFO: compiled from: ViewOscillator.java */
    static class a extends c {
        a() {
        }

        @Override // b.e.a.a.c
        public void j(View view, float f) {
            view.setAlpha(a(f));
        }
    }

    /* JADX INFO: compiled from: ViewOscillator.java */
    static class b extends c {
        float[] h = new float[1];
        protected ConstraintAttribute i;

        b() {
        }

        @Override // androidx.constraintlayout.core.j.a.e
        protected void c(Object obj) {
            this.i = (ConstraintAttribute) obj;
        }

        @Override // b.e.a.a.c
        public void j(View view, float f) {
            this.h[0] = a(f);
            b.e.a.a.a.b(this.i, view, this.h);
        }
    }

    /* JADX INFO: renamed from: b.e.a.a.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ViewOscillator.java */
    static class C0044c extends c {
        C0044c() {
        }

        @Override // b.e.a.a.c
        public void j(View view, float f) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setElevation(a(f));
            }
        }
    }

    /* JADX INFO: compiled from: ViewOscillator.java */
    public static class d extends c {
        @Override // b.e.a.a.c
        public void j(View view, float f) {
        }

        public void k(View view, float f, double d2, double d3) {
            view.setRotation(a(f) + ((float) Math.toDegrees(Math.atan2(d3, d2))));
        }
    }

    /* JADX INFO: compiled from: ViewOscillator.java */
    static class e extends c {
        boolean h = false;

        e() {
        }

        @Override // b.e.a.a.c
        public void j(View view, float f) {
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
                } catch (IllegalAccessException e2) {
                    Log.e("ViewOscillator", "unable to setProgress", e2);
                } catch (InvocationTargetException e3) {
                    Log.e("ViewOscillator", "unable to setProgress", e3);
                }
            }
        }
    }

    /* JADX INFO: compiled from: ViewOscillator.java */
    static class f extends c {
        f() {
        }

        @Override // b.e.a.a.c
        public void j(View view, float f) {
            view.setRotation(a(f));
        }
    }

    /* JADX INFO: compiled from: ViewOscillator.java */
    static class g extends c {
        g() {
        }

        @Override // b.e.a.a.c
        public void j(View view, float f) {
            view.setRotationX(a(f));
        }
    }

    /* JADX INFO: compiled from: ViewOscillator.java */
    static class h extends c {
        h() {
        }

        @Override // b.e.a.a.c
        public void j(View view, float f) {
            view.setRotationY(a(f));
        }
    }

    /* JADX INFO: compiled from: ViewOscillator.java */
    static class i extends c {
        i() {
        }

        @Override // b.e.a.a.c
        public void j(View view, float f) {
            view.setScaleX(a(f));
        }
    }

    /* JADX INFO: compiled from: ViewOscillator.java */
    static class j extends c {
        j() {
        }

        @Override // b.e.a.a.c
        public void j(View view, float f) {
            view.setScaleY(a(f));
        }
    }

    /* JADX INFO: compiled from: ViewOscillator.java */
    static class k extends c {
        k() {
        }

        @Override // b.e.a.a.c
        public void j(View view, float f) {
            view.setTranslationX(a(f));
        }
    }

    /* JADX INFO: compiled from: ViewOscillator.java */
    static class l extends c {
        l() {
        }

        @Override // b.e.a.a.c
        public void j(View view, float f) {
            view.setTranslationY(a(f));
        }
    }

    /* JADX INFO: compiled from: ViewOscillator.java */
    static class m extends c {
        m() {
        }

        @Override // b.e.a.a.c
        public void j(View view, float f) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setTranslationZ(a(f));
            }
        }
    }

    public static c i(String str) {
        if (str.startsWith("CUSTOM")) {
            return new b();
        }
        switch (str) {
            case "rotationX":
                return new g();
            case "rotationY":
                return new h();
            case "translationX":
                return new k();
            case "translationY":
                return new l();
            case "translationZ":
                return new m();
            case "progress":
                return new e();
            case "scaleX":
                return new i();
            case "scaleY":
                return new j();
            case "waveVariesBy":
                return new a();
            case "rotation":
                return new f();
            case "elevation":
                return new C0044c();
            case "transitionPathRotate":
                return new d();
            case "alpha":
                return new a();
            case "waveOffset":
                return new a();
            default:
                return null;
        }
    }

    public abstract void j(View view, float f2);
}
