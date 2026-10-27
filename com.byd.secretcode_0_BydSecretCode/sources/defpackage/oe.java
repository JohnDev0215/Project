package defpackage;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Build;
import android.util.Property;
import android.view.View;

/* JADX INFO: compiled from: ViewUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class oe {
    public static final ue a;
    public static final Property<View, Float> b;
    public static final Property<View, Rect> c;

    /* JADX INFO: compiled from: ViewUtils.java */
    public static class a extends Property<View, Float> {
        public a(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float get(View view) {
            return Float.valueOf(oe.c(view));
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(View view, Float f) {
            oe.g(view, f.floatValue());
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
            return t9.s(view);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(View view, Rect rect) {
            t9.p0(view, rect);
        }
    }

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 29) {
            a = new te();
        } else if (i >= 23) {
            a = new se();
        } else if (i >= 22) {
            a = new re();
        } else if (i >= 21) {
            a = new qe();
        } else if (i >= 19) {
            a = new pe();
        } else {
            a = new ue();
        }
        b = new a(Float.class, "translationAlpha");
        c = new b(Rect.class, "clipBounds");
    }

    public static void a(View view) {
        a.a(view);
    }

    public static ne b(View view) {
        return Build.VERSION.SDK_INT >= 18 ? new me(view) : le.e(view);
    }

    public static float c(View view) {
        return a.c(view);
    }

    public static ye d(View view) {
        return Build.VERSION.SDK_INT >= 18 ? new xe(view) : new we(view.getWindowToken());
    }

    public static void e(View view) {
        a.d(view);
    }

    public static void f(View view, int i, int i2, int i3, int i4) {
        a.e(view, i, i2, i3, i4);
    }

    public static void g(View view, float f) {
        a.f(view, f);
    }

    public static void h(View view, int i) {
        a.g(view, i);
    }

    public static void i(View view, Matrix matrix) {
        a.h(view, matrix);
    }

    public static void j(View view, Matrix matrix) {
        a.i(view, matrix);
    }
}
