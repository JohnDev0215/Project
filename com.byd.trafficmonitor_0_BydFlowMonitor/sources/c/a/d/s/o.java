package c.a.d.s;

import android.view.View;

/* JADX INFO: compiled from: ViewInvalidUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class o {
    public static void a(View view) {
        if (view != null) {
            view.setAlpha(0.45f);
            view.setEnabled(false);
        }
    }

    public static void b(View view) {
        if (view != null) {
            view.setAlpha(1.0f);
            view.setEnabled(true);
        }
    }
}
