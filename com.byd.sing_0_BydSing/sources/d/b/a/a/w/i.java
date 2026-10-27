package d.b.a.a.w;

import android.graphics.drawable.Drawable;
import android.view.View;

/* JADX INFO: compiled from: MaterialShapeUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class i {
    static d a(int i) {
        return i != 0 ? i != 1 ? b() : new e() : new l();
    }

    static d b() {
        return new l();
    }

    static f c() {
        return new f();
    }

    public static void d(View view, float f) {
        Drawable background = view.getBackground();
        if (background instanceof h) {
            ((h) background).W(f);
        }
    }

    public static void e(View view) {
        Drawable background = view.getBackground();
        if (background instanceof h) {
            f(view, (h) background);
        }
    }

    public static void f(View view, h hVar) {
        if (hVar.P()) {
            hVar.b0(com.google.android.material.internal.p.g(view));
        }
    }
}
