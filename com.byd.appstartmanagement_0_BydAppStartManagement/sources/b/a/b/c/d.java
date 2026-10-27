package b.a.b.c;

import android.view.View;

/* JADX INFO: compiled from: ViewInvalidUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class d {
    public static void a(View view, boolean z) {
        if (view == null) {
            return;
        }
        view.setAlpha(z ? 0.45f : 1.0f);
        view.setEnabled(!z);
    }
}
