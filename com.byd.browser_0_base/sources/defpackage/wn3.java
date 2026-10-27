package defpackage;

import android.graphics.Rect;
import android.util.Property;
import android.view.View;
import defpackage.om3;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: ViewUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class wn3 {
    public static final a a;
    public static final b b;

    /* JADX INFO: compiled from: ViewUtils.java */
    public class a extends Property<View, Float> {
        public a() {
            super(Float.class, "translationAlpha");
        }

        @Override // android.util.Property
        public final Float get(View view) {
            a aVar = wn3.a;
            return Float.valueOf(view.getTransitionAlpha());
        }

        @Override // android.util.Property
        public final void set(View view, Float f) {
            float fFloatValue = f.floatValue();
            a aVar = wn3.a;
            view.setTransitionAlpha(fFloatValue);
        }
    }

    /* JADX INFO: compiled from: ViewUtils.java */
    public class b extends Property<View, Rect> {
        public b() {
            super(Rect.class, "clipBounds");
        }

        @Override // android.util.Property
        public final Rect get(View view) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
            return om3.f.a(view);
        }

        @Override // android.util.Property
        public final void set(View view, Rect rect) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.f.b(view, rect);
        }
    }

    static {
        new fo3();
        a = new a();
        b = new b();
    }

    public static void a(View view, int i, int i2, int i3, int i4) {
        view.setLeftTopRightBottom(i, i2, i3, i4);
    }

    public static void b(View view, int i) {
        view.setTransitionVisibility(i);
    }
}
