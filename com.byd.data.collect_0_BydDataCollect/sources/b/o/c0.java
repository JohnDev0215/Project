package b.o;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Build;
import android.util.Property;
import android.view.View;

/* JADX INFO: compiled from: ViewUtils.java */
/* JADX INFO: loaded from: classes.dex */
class c0 {

    /* JADX INFO: renamed from: a */
    private static final i0 f2829a;

    /* JADX INFO: renamed from: b */
    static final Property<View, Float> f2830b;

    /* JADX INFO: renamed from: c */
    static final Property<View, Rect> f2831c;

    /* JADX INFO: compiled from: ViewUtils.java */
    static class a extends Property<View, Float> {
        a(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a */
        public Float get(View view) {
            return Float.valueOf(c0.c(view));
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b */
        public void set(View view, Float f2) {
            c0.g(view, f2.floatValue());
        }
    }

    /* JADX INFO: compiled from: ViewUtils.java */
    static class b extends Property<View, Rect> {
        b(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a */
        public Rect get(View view) {
            return b.g.k.t.s(view);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b */
        public void set(View view, Rect rect) {
            b.g.k.t.p0(view, rect);
        }
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 29) {
            f2829a = new h0();
        } else if (i2 >= 23) {
            f2829a = new g0();
        } else if (i2 >= 22) {
            f2829a = new f0();
        } else if (i2 >= 21) {
            f2829a = new e0();
        } else if (i2 >= 19) {
            f2829a = new d0();
        } else {
            f2829a = new i0();
        }
        f2830b = new a(Float.class, "translationAlpha");
        f2831c = new b(Rect.class, "clipBounds");
    }

    static void a(View view) {
        f2829a.a(view);
    }

    static b0 b(View view) {
        return Build.VERSION.SDK_INT >= 18 ? new a0(view) : z.e(view);
    }

    static float c(View view) {
        return f2829a.c(view);
    }

    static m0 d(View view) {
        return Build.VERSION.SDK_INT >= 18 ? new l0(view) : new k0(view.getWindowToken());
    }

    static void e(View view) {
        f2829a.d(view);
    }

    static void f(View view, int i2, int i3, int i4, int i5) {
        f2829a.e(view, i2, i3, i4, i5);
    }

    static void g(View view, float f2) {
        f2829a.f(view, f2);
    }

    static void h(View view, int i2) {
        f2829a.g(view, i2);
    }

    static void i(View view, Matrix matrix) {
        f2829a.h(view, matrix);
    }

    static void j(View view, Matrix matrix) {
        f2829a.i(view, matrix);
    }
}
