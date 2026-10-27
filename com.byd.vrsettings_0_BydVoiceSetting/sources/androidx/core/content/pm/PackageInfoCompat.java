package androidx.core.content.pm;

import android.content.pm.PackageInfo;

/* JADX INFO: loaded from: classes.dex */
public final class PackageInfoCompat {
    public static long getLongVersionCode(PackageInfo packageInfo) {
        return packageInfo.getLongVersionCode();
    }

    private PackageInfoCompat() {
    }
}
