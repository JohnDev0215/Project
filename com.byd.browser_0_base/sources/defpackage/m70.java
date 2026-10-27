package defpackage;

import android.content.pm.ResolveInfo;
import android.text.TextUtils;

/* JADX INFO: compiled from: DefaultBrowserPromoDeps.java */
/* JADX INFO: loaded from: classes2.dex */
public final class m70 {
    public static final String[] a = {"com.android.chrome", "org.chromium.chrome", "com.chrome.canary", "com.chrome.beta", "com.chrome.dev"};
    public static m70 b;

    public static int a(ResolveInfo resolveInfo) {
        if (resolveInfo == null || resolveInfo.match == 0) {
            return 0;
        }
        return TextUtils.equals(xz.a.getPackageName(), resolveInfo.activityInfo.packageName) ? 2 : 1;
    }
}
