package m4;

import android.view.View;

/* JADX INFO: compiled from: ViewInvalidUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class g {
    public static void a(View view, boolean z7) {
        if (view == null) {
            return;
        }
        view.setAlpha(z7 ? 0.45f : 1.0f);
        view.setEnabled(!z7);
    }
}
