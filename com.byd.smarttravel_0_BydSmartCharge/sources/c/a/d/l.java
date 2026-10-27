package c.a.d;

import android.view.View;

/* JADX INFO: compiled from: ViewInvalidUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class l {
    public static void a(View... viewArr) {
        if (viewArr == null || viewArr.length == 0) {
            return;
        }
        for (View view : viewArr) {
            view.setAlpha(0.45f);
            view.setEnabled(false);
        }
    }

    public static void b(View... viewArr) {
        if (viewArr == null || viewArr.length == 0) {
            return;
        }
        for (View view : viewArr) {
            view.setAlpha(1.0f);
            view.setEnabled(true);
        }
    }
}
