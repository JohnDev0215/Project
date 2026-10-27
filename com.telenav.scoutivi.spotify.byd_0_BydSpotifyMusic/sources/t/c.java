package t;

import android.util.Log;
import android.view.View;
import androidx.constraintlayout.motion.widget.MotionLayout;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: ViewOscillator.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class c extends p.e {

    /* JADX INFO: compiled from: ViewOscillator.java */
    static class a extends c {
        a() {
        }

        @Override // t.c
        public void i(View view, float f7) {
            view.setAlpha(a(f7));
        }
    }

    /* JADX INFO: compiled from: ViewOscillator.java */
    static class b extends c {

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        float[] f9524h = new float[1];

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        protected androidx.constraintlayout.widget.a f9525i;

        b() {
        }

        @Override // p.e
        protected void b(Object obj) {
            this.f9525i = (androidx.constraintlayout.widget.a) obj;
        }

        @Override // t.c
        public void i(View view, float f7) {
            this.f9524h[0] = a(f7);
            t.a.b(this.f9525i, view, this.f9524h);
        }
    }

    /* JADX INFO: renamed from: t.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ViewOscillator.java */
    static class C0151c extends c {
        C0151c() {
        }

        @Override // t.c
        public void i(View view, float f7) {
            view.setElevation(a(f7));
        }
    }

    /* JADX INFO: compiled from: ViewOscillator.java */
    public static class d extends c {
        @Override // t.c
        public void i(View view, float f7) {
        }

        public void j(View view, float f7, double d7, double d8) {
            view.setRotation(a(f7) + ((float) Math.toDegrees(Math.atan2(d8, d7))));
        }
    }

    /* JADX INFO: compiled from: ViewOscillator.java */
    static class e extends c {

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        boolean f9526h = false;

        e() {
        }

        @Override // t.c
        public void i(View view, float f7) {
            if (view instanceof MotionLayout) {
                ((MotionLayout) view).setProgress(a(f7));
                return;
            }
            if (this.f9526h) {
                return;
            }
            Method method = null;
            try {
                method = view.getClass().getMethod("setProgress", Float.TYPE);
            } catch (NoSuchMethodException unused) {
                this.f9526h = true;
            }
            if (method != null) {
                try {
                    method.invoke(view, Float.valueOf(a(f7)));
                } catch (IllegalAccessException e7) {
                    Log.e("ViewOscillator", "unable to setProgress", e7);
                } catch (InvocationTargetException e8) {
                    Log.e("ViewOscillator", "unable to setProgress", e8);
                }
            }
        }
    }

    /* JADX INFO: compiled from: ViewOscillator.java */
    static class f extends c {
        f() {
        }

        @Override // t.c
        public void i(View view, float f7) {
            view.setRotation(a(f7));
        }
    }

    /* JADX INFO: compiled from: ViewOscillator.java */
    static class g extends c {
        g() {
        }

        @Override // t.c
        public void i(View view, float f7) {
            view.setRotationX(a(f7));
        }
    }

    /* JADX INFO: compiled from: ViewOscillator.java */
    static class h extends c {
        h() {
        }

        @Override // t.c
        public void i(View view, float f7) {
            view.setRotationY(a(f7));
        }
    }

    /* JADX INFO: compiled from: ViewOscillator.java */
    static class i extends c {
        i() {
        }

        @Override // t.c
        public void i(View view, float f7) {
            view.setScaleX(a(f7));
        }
    }

    /* JADX INFO: compiled from: ViewOscillator.java */
    static class j extends c {
        j() {
        }

        @Override // t.c
        public void i(View view, float f7) {
            view.setScaleY(a(f7));
        }
    }

    /* JADX INFO: compiled from: ViewOscillator.java */
    static class k extends c {
        k() {
        }

        @Override // t.c
        public void i(View view, float f7) {
            view.setTranslationX(a(f7));
        }
    }

    /* JADX INFO: compiled from: ViewOscillator.java */
    static class l extends c {
        l() {
        }

        @Override // t.c
        public void i(View view, float f7) {
            view.setTranslationY(a(f7));
        }
    }

    /* JADX INFO: compiled from: ViewOscillator.java */
    static class m extends c {
        m() {
        }

        @Override // t.c
        public void i(View view, float f7) {
            view.setTranslationZ(a(f7));
        }
    }

    public static c h(String str) {
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
                return new C0151c();
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

    public abstract void i(View view, float f7);
}
