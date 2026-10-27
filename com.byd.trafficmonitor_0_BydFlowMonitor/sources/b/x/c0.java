package b.x;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Build;
import android.util.Property;
import android.view.View;

/* JADX INFO: compiled from: ViewUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class c0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final i0 f3229a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Property<View, Float> f3230b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Property<View, Rect> f3231c;

    /* JADX INFO: compiled from: ViewUtils.java */
    public static class a extends Property<View, Float> {
        public a(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float get(View view) {
            return Float.valueOf(c0.c(view));
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(View view, Float f2) {
            c0.g(view, f2.floatValue());
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
            return b.h.m.t.s(view);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(View view, Rect rect) {
            b.h.m.t.q0(view, rect);
        }
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 29) {
            f3229a = new h0();
        } else if (i2 >= 23) {
            f3229a = new g0();
        } else if (i2 >= 22) {
            f3229a = new f0();
        } else if (i2 >= 21) {
            f3229a = new e0();
        } else if (i2 >= 19) {
            f3229a = new d0();
        } else {
            f3229a = new i0();
        }
        f3230b = new a(Float.class, "translationAlpha");
        f3231c = new b(Rect.class, "clipBounds");
    }

    public static void a(View view) {
        f3229a.a(view);
    }

    public static b0 b(View view) {
        return Build.VERSION.SDK_INT >= 18 ? new a0(view) : z.e(view);
    }

    public static float c(View view) {
        return f3229a.c(view);
    }

    public static m0 d(View view) {
        return Build.VERSION.SDK_INT >= 18 ? new l0(view) : new k0(view.getWindowToken());
    }

    public static void e(View view) {
        f3229a.d(view);
    }

    public static void f(View view, int i2, int i3, int i4, int i5) {
        f3229a.e(view, i2, i3, i4, i5);
    }

    public static void g(View view, float f2) {
        f3229a.f(view, f2);
    }

    public static void h(View view, int i2) {
        f3229a.g(view, i2);
    }

    public static void i(View view, Matrix matrix) {
        f3229a.h(view, matrix);
    }

    public static void j(View view, Matrix matrix) {
        f3229a.i(view, matrix);
    }
}
