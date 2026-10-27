package e3;

import android.graphics.drawable.Drawable;
import android.view.View;

/* JADX INFO: compiled from: MaterialShapeUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class h {
    static d a(int i7) {
        return i7 != 0 ? i7 != 1 ? b() : new e() : new j();
    }

    static d b() {
        return new j();
    }

    static f c() {
        return new f();
    }

    public static void d(View view, float f7) {
        Drawable background = view.getBackground();
        if (background instanceof g) {
            ((g) background).W(f7);
        }
    }

    public static void e(View view) {
        Drawable background = view.getBackground();
        if (background instanceof g) {
            f(view, (g) background);
        }
    }

    public static void f(View view, g gVar) {
        if (gVar.O()) {
            gVar.a0(com.google.android.material.internal.l.c(view));
        }
    }
}
