package b.q;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Build;
import android.util.Property;
import android.view.View;

/* JADX INFO: compiled from: ViewUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class b0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final h0 f1428a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Property<View, Float> f1429b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Property<View, Rect> f1430c;

    /* JADX INFO: compiled from: ViewUtils.java */
    public static class a extends Property<View, Float> {
        public a(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float get(View view) {
            return Float.valueOf(b0.c(view));
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(View view, Float f) {
            b0.g(view, f.floatValue());
        }
    }

    /* JADX INFO: compiled from: ViewUtils.java */
    public static class b extends Property<View, Rect> {
        public b(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Rect get(View view) {
            return b.g.j.t.s(view);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(View view, Rect rect) {
            b.g.j.t.p0(view, rect);
        }
    }

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 29) {
            f1428a = new g0();
        } else if (i >= 23) {
            f1428a = new f0();
        } else if (i >= 22) {
            f1428a = new e0();
        } else if (i >= 21) {
            f1428a = new d0();
        } else if (i >= 19) {
            f1428a = new c0();
        } else {
            f1428a = new h0();
        }
        f1429b = new a(Float.class, "translationAlpha");
        f1430c = new b(Rect.class, "clipBounds");
    }

    public static void a(View view) {
        f1428a.a(view);
    }

    public static a0 b(View view) {
        return Build.VERSION.SDK_INT >= 18 ? new z(view) : y.e(view);
    }

    public static float c(View view) {
        return f1428a.c(view);
    }

    public static l0 d(View view) {
        return Build.VERSION.SDK_INT >= 18 ? new k0(view) : new j0(view.getWindowToken());
    }

    public static void e(View view) {
        f1428a.d(view);
    }

    public static void f(View view, int i, int i2, int i3, int i4) {
        f1428a.e(view, i, i2, i3, i4);
    }

    public static void g(View view, float f) {
        f1428a.f(view, f);
    }

    public static void h(View view, int i) {
        f1428a.g(view, i);
    }

    public static void i(View view, Matrix matrix) {
        f1428a.h(view, matrix);
    }

    public static void j(View view, Matrix matrix) {
        f1428a.i(view, matrix);
    }
}
