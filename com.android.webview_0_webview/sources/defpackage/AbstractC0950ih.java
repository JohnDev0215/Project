package defpackage;

import android.os.Build;
import android.os.UserManager;

/* JADX INFO: renamed from: ih, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0950ih {
    public static UserManager a;
    public static volatile boolean b = !a();

    public static boolean a() {
        return Build.VERSION.SDK_INT >= 24;
    }
}
