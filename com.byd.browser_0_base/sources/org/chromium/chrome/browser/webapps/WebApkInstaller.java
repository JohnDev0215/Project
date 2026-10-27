package org.chromium.chrome.browser.webapps;

import J.N;
import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import defpackage.i9;
import defpackage.oj2;
import defpackage.xz;
import defpackage.zp3;
import defpackage.zt1;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.AppHooks;

/* JADX INFO: loaded from: classes2.dex */
public class WebApkInstaller {
    public long a;
    public final String b;

    public class a extends i9<Integer> {
        public a() {
        }

        @Override // defpackage.i9
        public final Integer b() {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getAbsolutePath());
            long availableBytes = statFs.getAvailableBytes();
            long totalBytes = statFs.getTotalBytes();
            ContentResolver contentResolver = xz.a.getContentResolver();
            long jMin = (availableBytes - Math.min(Settings.Global.getLong(contentResolver, "sys_storage_threshold_max_bytes", 524288000L), (totalBytes * ((long) Settings.Global.getInt(contentResolver, "sys_storage_threshold_percentage", 10))) / 100)) + 104857600;
            if (jMin > 0) {
                return 0;
            }
            return zp3.a(xz.a.getCacheDir()) + jMin > 0 ? 1 : 2;
        }

        @Override // defpackage.i9
        public final void l(Integer num) {
            N.Mz0ZUPry(WebApkInstaller.this.a, num.intValue());
        }
    }

    public WebApkInstaller(long j) {
        this.a = j;
        AppHooks.get().getClass();
        AppHooks.get().getClass();
        this.b = "";
    }

    @CalledByNative
    public static WebApkInstaller create(long j) {
        return new WebApkInstaller(j);
    }

    @CalledByNative
    public final void checkFreeSpace() {
        new a().c(i9.e);
    }

    @CalledByNative
    public final void destroy() {
        this.a = 0L;
    }

    @CalledByNative
    public final String getWebApkServerUrl() {
        return this.b;
    }

    @CalledByNative
    public final void installWebApkAsync(String str, int i, String str2, String str3, int i2, Bitmap bitmap) {
        if (zt1.e(str)) {
            long j = this.a;
            if (j != 0) {
                N.MQ3SBZxh(j, 0);
                return;
            }
            return;
        }
        long j2 = this.a;
        if (j2 != 0) {
            N.MQ3SBZxh(j2, 3);
        }
        oj2.U(1, 16, "WebApk.Install.GooglePlayInstallResult");
    }

    @CalledByNative
    public final void updateAsync(String str, int i, String str2, String str3) {
        long j = this.a;
        if (j != 0) {
            N.MQ3SBZxh(j, 3);
        }
    }
}
