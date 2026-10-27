package defpackage;

import android.view.View;

/* JADX INFO: renamed from: h6, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0865h6 {
    public static int a(C0586c6 c0586c6, H5 h5, View view, View view2, W5 w5, boolean z) {
        if (w5.p() == 0 || c0586c6.a() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (z) {
            return Math.min(h5.f(), h5.b(view2) - h5.c(view));
        }
        w5.x(view);
        throw null;
    }

    public static int b(C0586c6 c0586c6, H5 h5, View view, View view2, W5 w5, boolean z, boolean z2) {
        if (w5.p() == 0 || c0586c6.a() == 0 || view == null || view2 == null) {
            return 0;
        }
        w5.x(view);
        throw null;
    }

    public static int c(C0586c6 c0586c6, H5 h5, View view, View view2, W5 w5, boolean z) {
        if (w5.p() == 0 || c0586c6.a() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z) {
            return c0586c6.a();
        }
        h5.b(view2);
        h5.c(view);
        w5.x(view);
        throw null;
    }
}
