package b.a.a.a.n;

import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final boolean f1759a;

    static {
        f1759a = Build.VERSION.SDK_INT < 18;
    }

    public static void a(a aVar, View view, FrameLayout frameLayout) {
        c(aVar, view, frameLayout);
        if (f1759a) {
            frameLayout.setForeground(aVar);
        } else {
            view.getOverlay().add(aVar);
        }
    }

    public static void b(a aVar, View view, FrameLayout frameLayout) {
        if (aVar == null) {
            return;
        }
        if (f1759a) {
            frameLayout.setForeground(null);
        } else {
            view.getOverlay().remove(aVar);
        }
    }

    public static void c(a aVar, View view, FrameLayout frameLayout) {
        Rect rect = new Rect();
        (f1759a ? frameLayout : view).getDrawingRect(rect);
        aVar.setBounds(rect);
        aVar.v(view, frameLayout);
    }

    public static void d(Rect rect, float f, float f2, float f3, float f4) {
        rect.set((int) (f - f3), (int) (f2 - f4), (int) (f + f3), (int) (f2 + f4));
    }
}
