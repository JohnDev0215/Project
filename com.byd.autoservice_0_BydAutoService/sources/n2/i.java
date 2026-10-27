package n2;

import android.graphics.drawable.Drawable;
import android.view.View;

/* JADX INFO: compiled from: MaterialShapeUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class i {
    public static d a(int i3) {
        return i3 != 0 ? i3 != 1 ? b() : new e() : new l();
    }

    public static d b() {
        return new l();
    }

    public static f c() {
        return new f();
    }

    public static void d(View view, float f3) {
        Drawable background = view.getBackground();
        if (background instanceof h) {
            ((h) background).W(f3);
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
            hVar.b0(i2.p.g(view));
        }
    }
}
