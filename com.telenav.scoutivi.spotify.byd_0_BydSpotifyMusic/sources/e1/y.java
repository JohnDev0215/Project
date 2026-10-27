package e1;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Build;
import android.util.Property;
import android.view.View;

/* JADX INFO: compiled from: ViewUtils.java */
/* JADX INFO: loaded from: classes.dex */
class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final e0 f6170a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final Property<View, Float> f6171b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final Property<View, Rect> f6172c;

    /* JADX INFO: compiled from: ViewUtils.java */
    static class a extends Property<View, Float> {
        a(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float get(View view) {
            return Float.valueOf(y.c(view));
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(View view, Float f7) {
            y.g(view, f7.floatValue());
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
            return i0.u.r(view);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(View view, Rect rect) {
            i0.u.q0(view, rect);
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 29) {
            f6170a = new d0();
        } else {
            f6170a = new c0();
        }
        f6171b = new a(Float.class, "translationAlpha");
        f6172c = new b(Rect.class, "clipBounds");
    }

    static void a(View view) {
        f6170a.a(view);
    }

    static x b(View view) {
        return new w(view);
    }

    static float c(View view) {
        return f6170a.b(view);
    }

    static h0 d(View view) {
        return new g0(view);
    }

    static void e(View view) {
        f6170a.c(view);
    }

    static void f(View view, int i7, int i8, int i9, int i10) {
        f6170a.d(view, i7, i8, i9, i10);
    }

    static void g(View view, float f7) {
        f6170a.e(view, f7);
    }

    static void h(View view, int i7) {
        f6170a.f(view, i7);
    }

    static void i(View view, Matrix matrix) {
        f6170a.g(view, matrix);
    }

    static void j(View view, Matrix matrix) {
        f6170a.h(view, matrix);
    }
}
