package y0;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Build;
import android.util.Property;
import android.view.View;

/* JADX INFO: compiled from: ViewUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class b0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final h0 f5851a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Property<View, Float> f5852b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Property<View, Rect> f5853c;

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
        public void set(View view, Float f3) {
            b0.g(view, f3.floatValue());
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
            return l0.t.s(view);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(View view, Rect rect) {
            l0.t.p0(view, rect);
        }
    }

    static {
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 29) {
            f5851a = new g0();
        } else if (i3 >= 23) {
            f5851a = new f0();
        } else if (i3 >= 22) {
            f5851a = new e0();
        } else if (i3 >= 21) {
            f5851a = new d0();
        } else if (i3 >= 19) {
            f5851a = new c0();
        } else {
            f5851a = new h0();
        }
        f5852b = new a(Float.class, "translationAlpha");
        f5853c = new b(Rect.class, "clipBounds");
    }

    public static void a(View view) {
        f5851a.a(view);
    }

    public static a0 b(View view) {
        return Build.VERSION.SDK_INT >= 18 ? new z(view) : y.e(view);
    }

    public static float c(View view) {
        return f5851a.c(view);
    }

    public static l0 d(View view) {
        return Build.VERSION.SDK_INT >= 18 ? new k0(view) : new j0(view.getWindowToken());
    }

    public static void e(View view) {
        f5851a.d(view);
    }

    public static void f(View view, int i3, int i4, int i5, int i6) {
        f5851a.e(view, i3, i4, i5, i6);
    }

    public static void g(View view, float f3) {
        f5851a.f(view, f3);
    }

    public static void h(View view, int i3) {
        f5851a.g(view, i3);
    }

    public static void i(View view, Matrix matrix) {
        f5851a.h(view, matrix);
    }

    public static void j(View view, Matrix matrix) {
        f5851a.i(view, matrix);
    }
}
