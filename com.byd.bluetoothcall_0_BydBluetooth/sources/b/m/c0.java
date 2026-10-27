package b.m;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Build;
import android.util.Property;
import android.view.View;

/* JADX INFO: compiled from: ViewUtils.java */
/* JADX INFO: loaded from: classes.dex */
class c0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final i0 f2445a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final Property<View, Float> f2446b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final Property<View, Rect> f2447c;

    /* JADX INFO: compiled from: ViewUtils.java */
    static class a extends Property<View, Float> {
        a(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float get(View view) {
            return Float.valueOf(c0.c(view));
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(View view, Float f) {
            c0.g(view, f.floatValue());
        }
    }

    /* JADX INFO: compiled from: ViewUtils.java */
    static class b extends Property<View, Rect> {
        b(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Rect get(View view) {
            return b.g.k.t.s(view);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(View view, Rect rect) {
            b.g.k.t.q0(view, rect);
        }
    }

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 29) {
            f2445a = new h0();
        } else if (i >= 23) {
            f2445a = new g0();
        } else if (i >= 22) {
            f2445a = new f0();
        } else if (i >= 21) {
            f2445a = new e0();
        } else if (i >= 19) {
            f2445a = new d0();
        } else {
            f2445a = new i0();
        }
        f2446b = new a(Float.class, "translationAlpha");
        f2447c = new b(Rect.class, "clipBounds");
    }

    static void a(View view) {
        f2445a.a(view);
    }

    static b0 b(View view) {
        return Build.VERSION.SDK_INT >= 18 ? new a0(view) : z.e(view);
    }

    static float c(View view) {
        return f2445a.c(view);
    }

    static m0 d(View view) {
        return Build.VERSION.SDK_INT >= 18 ? new l0(view) : new k0(view.getWindowToken());
    }

    static void e(View view) {
        f2445a.d(view);
    }

    static void f(View view, int i, int i2, int i3, int i4) {
        f2445a.e(view, i, i2, i3, i4);
    }

    static void g(View view, float f) {
        f2445a.f(view, f);
    }

    static void h(View view, int i) {
        f2445a.g(view, i);
    }

    static void i(View view, Matrix matrix) {
        f2445a.h(view, matrix);
    }

    static void j(View view, Matrix matrix) {
        f2445a.i(view, matrix);
    }
}
