package c.n;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Build;
import android.util.Property;
import android.view.View;

/* JADX INFO: compiled from: ViewUtils.java */
/* JADX INFO: loaded from: classes.dex */
class c0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final i0 f2423a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final Property<View, Float> f2424b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final Property<View, Rect> f2425c;

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
            return c.g.k.t.s(view);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(View view, Rect rect) {
            c.g.k.t.p0(view, rect);
        }
    }

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 29) {
            f2423a = new h0();
        } else if (i >= 23) {
            f2423a = new g0();
        } else if (i >= 22) {
            f2423a = new f0();
        } else if (i >= 21) {
            f2423a = new e0();
        } else if (i >= 19) {
            f2423a = new d0();
        } else {
            f2423a = new i0();
        }
        f2424b = new a(Float.class, "translationAlpha");
        f2425c = new b(Rect.class, "clipBounds");
    }

    static void a(View view) {
        f2423a.a(view);
    }

    static b0 b(View view) {
        return Build.VERSION.SDK_INT >= 18 ? new a0(view) : z.e(view);
    }

    static float c(View view) {
        return f2423a.c(view);
    }

    static m0 d(View view) {
        return Build.VERSION.SDK_INT >= 18 ? new l0(view) : new k0(view.getWindowToken());
    }

    static void e(View view) {
        f2423a.d(view);
    }

    static void f(View view, int i, int i2, int i3, int i4) {
        f2423a.e(view, i, i2, i3, i4);
    }

    static void g(View view, float f) {
        f2423a.f(view, f);
    }

    static void h(View view, int i) {
        f2423a.g(view, i);
    }

    static void i(View view, Matrix matrix) {
        f2423a.h(view, matrix);
    }

    static void j(View view, Matrix matrix) {
        f2423a.i(view, matrix);
    }
}
