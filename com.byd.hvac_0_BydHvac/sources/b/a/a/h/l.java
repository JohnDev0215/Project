package b.a.a.h;

import android.view.View;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class l {
    public static void a(boolean z, View... viewArr) {
        if (viewArr == null || viewArr.length <= 0) {
            return;
        }
        for (View view : viewArr) {
            if (view != null) {
                view.setAlpha(z ? 1.0f : 0.45f);
            }
        }
    }

    public static void b(boolean z, View... viewArr) {
        if (viewArr == null || viewArr.length <= 0) {
            return;
        }
        for (View view : viewArr) {
            if (view != null) {
                view.setEnabled(z);
            }
        }
    }

    public static void c(boolean z, View... viewArr) {
        if (viewArr == null || viewArr.length <= 0) {
            return;
        }
        for (View view : viewArr) {
            if (view != null) {
                view.setAlpha(z ? 1.0f : 0.45f);
                view.setEnabled(z);
            }
        }
    }

    public static void d(int i, View... viewArr) {
        for (View view : viewArr) {
            if (view != null) {
                view.setVisibility(i);
            }
        }
    }
}
