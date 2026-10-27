package org.chromium.chrome.browser.webapps;

import android.content.Context;
import android.content.pm.PackageInfo;
import defpackage.du;
import defpackage.fq3;
import defpackage.xz;
import defpackage.zs;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public class WebApkHandlerDelegate {
    public long a;

    public WebApkHandlerDelegate(long j) {
        this.a = j;
    }

    @CalledByNative
    public static WebApkHandlerDelegate create(long j) {
        return new WebApkHandlerDelegate(j);
    }

    @CalledByNative
    public void reset() {
        this.a = 0L;
    }

    @CalledByNative
    public void retrieveWebApks() {
        if (this.a == 0) {
            return;
        }
        Context context = xz.a;
        for (PackageInfo packageInfo : context.getPackageManager().getInstalledPackages(0)) {
            if (fq3.c(context, packageInfo.packageName)) {
                zs.a(packageInfo.packageName, new du(this, packageInfo));
            }
        }
    }
}
