package q2;

import android.view.View;

/* JADX INFO: compiled from: ViewInvalidUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class e {
    public static boolean a(View view) {
        return view != null && view.getAlpha() == 0.45f;
    }

    public static void b(View view, boolean z7) {
        if (view == null) {
            return;
        }
        view.setAlpha(z7 ? 0.45f : 1.0f);
        view.setEnabled(!z7);
    }
}
