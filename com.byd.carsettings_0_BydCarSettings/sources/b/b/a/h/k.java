package b.b.a.h;

import android.view.View;

/* JADX INFO: compiled from: ViewInvalidUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class k {
    public static boolean a(View view) {
        return view != null && view.getAlpha() == 0.45f;
    }

    public static void b(View view) {
        if (view != null) {
            view.setAlpha(0.45f);
        }
    }

    public static void c(View view) {
        if (view != null) {
            view.setAlpha(1.0f);
        }
    }

    public static void d(boolean z, View... viewArr) {
        for (View view : viewArr) {
            if (view == null) {
                return;
            }
            view.setAlpha(z ? 1.0f : 0.45f);
        }
    }

    public static void e(View view) {
        if (view != null) {
            view.setAlpha(0.45f);
            view.setEnabled(false);
        }
    }

    public static void f(View view, boolean z) {
        if (view == null) {
            return;
        }
        view.setAlpha(z ? 0.45f : 1.0f);
        view.setEnabled(!z);
    }

    public static void g(boolean z, View... viewArr) {
        for (View view : viewArr) {
            if (view == null) {
                return;
            }
            view.setAlpha(z ? 0.45f : 1.0f);
            view.setEnabled(!z);
        }
    }

    public static void h(View view) {
        if (view != null) {
            view.setAlpha(1.0f);
            view.setEnabled(true);
        }
    }
}
