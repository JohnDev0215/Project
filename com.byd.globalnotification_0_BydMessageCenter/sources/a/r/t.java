package a.r;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.Property;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final z f1356a = new y();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Property<View, Float> f1357b = new a(Float.class, "translationAlpha");

    public static class a extends Property<View, Float> {
        public a(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        public Float get(View view) {
            return Float.valueOf(view.getTransitionAlpha());
        }

        @Override // android.util.Property
        public void set(View view, Float f2) {
            view.setTransitionAlpha(f2.floatValue());
        }
    }

    public static class b extends Property<View, Rect> {
        public b(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        public Rect get(View view) {
            return a.h.k.q.j(view);
        }

        @Override // android.util.Property
        public void set(View view, Rect rect) {
            a.h.k.q.Y(view, rect);
        }
    }

    static {
        new b(Rect.class, "clipBounds");
    }

    public static s a(View view) {
        return new r(view);
    }

    public static float b(View view) {
        return view.getTransitionAlpha();
    }

    public static d0 c(View view) {
        return new c0(view);
    }

    public static void d(View view) {
    }

    public static void e(View view, int i, int i2, int i3, int i4) {
        view.setLeftTopRightBottom(i, i2, i3, i4);
    }

    public static void f(View view, float f2) {
        view.setTransitionAlpha(f2);
    }

    public static void g(View view, int i) {
        view.setTransitionVisibility(i);
    }

    public static void h(View view, Matrix matrix) {
        view.transformMatrixToGlobal(matrix);
    }
}
