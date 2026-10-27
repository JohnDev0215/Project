package k1;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Build;
import android.util.Property;
import android.view.View;

/* JADX INFO: compiled from: ViewUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class b0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final h0 f5695a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Property<View, Float> f5696b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Property<View, Rect> f5697c;

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
        public void set(View view, Float f8) {
            b0.g(view, f8.floatValue());
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
            return o0.t.t(view);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(View view, Rect rect) {
            o0.t.r0(view, rect);
        }
    }

    static {
        int i8 = Build.VERSION.SDK_INT;
        if (i8 >= 29) {
            f5695a = new g0();
        } else if (i8 >= 23) {
            f5695a = new f0();
        } else if (i8 >= 22) {
            f5695a = new e0();
        } else if (i8 >= 21) {
            f5695a = new d0();
        } else if (i8 >= 19) {
            f5695a = new c0();
        } else {
            f5695a = new h0();
        }
        f5696b = new a(Float.class, "translationAlpha");
        f5697c = new b(Rect.class, "clipBounds");
    }

    public static void a(View view) {
        f5695a.a(view);
    }

    public static a0 b(View view) {
        return Build.VERSION.SDK_INT >= 18 ? new z(view) : y.e(view);
    }

    public static float c(View view) {
        return f5695a.c(view);
    }

    public static l0 d(View view) {
        return Build.VERSION.SDK_INT >= 18 ? new k0(view) : new j0(view.getWindowToken());
    }

    public static void e(View view) {
        f5695a.d(view);
    }

    public static void f(View view, int i8, int i9, int i10, int i11) {
        f5695a.e(view, i8, i9, i10, i11);
    }

    public static void g(View view, float f8) {
        f5695a.f(view, f8);
    }

    public static void h(View view, int i8) {
        f5695a.g(view, i8);
    }

    public static void i(View view, Matrix matrix) {
        f5695a.h(view, matrix);
    }

    public static void j(View view, Matrix matrix) {
        f5695a.i(view, matrix);
    }
}
