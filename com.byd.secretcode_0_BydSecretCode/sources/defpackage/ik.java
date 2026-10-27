package defpackage;

import android.graphics.drawable.Drawable;
import android.view.View;

/* JADX INFO: compiled from: MaterialShapeUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class ik {
    public static dk a(int i) {
        return i != 0 ? i != 1 ? b() : new ek() : new lk();
    }

    public static dk b() {
        return new lk();
    }

    public static fk c() {
        return new fk();
    }

    public static void d(View view, float f) {
        Drawable background = view.getBackground();
        if (background instanceof hk) {
            ((hk) background).W(f);
        }
    }

    public static void e(View view) {
        Drawable background = view.getBackground();
        if (background instanceof hk) {
            f(view, (hk) background);
        }
    }

    public static void f(View view, hk hkVar) {
        if (hkVar.P()) {
            hkVar.b0(oj.g(view));
        }
    }
}
