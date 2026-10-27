package defpackage;

import android.content.ClipDescription;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.view.Display;
import android.view.View;

/* JADX INFO: renamed from: ot, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1295ot {
    public static long a(ClipDescription clipDescription) {
        return clipDescription.getTimestamp();
    }

    public static boolean b(PackageManager packageManager) {
        return packageManager.isInstantApp();
    }

    public static boolean c(Configuration configuration) {
        return configuration.isScreenWideColorGamut();
    }

    public static boolean d(Display display) {
        return display.isWideColorGamut();
    }

    public static void e(View view, boolean z) {
        view.setDefaultFocusHighlightEnabled(z);
    }
}
