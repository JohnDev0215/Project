package android.support.v4.content.pm;

import android.content.pm.PackageInfo;
import android.support.v4.os.BuildCompat;

/* JADX INFO: loaded from: classes.dex */
public final class PackageInfoCompat {
    public static long getLongVersionCode(PackageInfo info) {
        if (BuildCompat.isAtLeastP()) {
            return info.getLongVersionCode();
        }
        return info.versionCode;
    }

    private PackageInfoCompat() {
    }
}
