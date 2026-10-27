package org.chromium.chrome.browser.offlinepages;

import android.app.DownloadManager;
import android.net.Uri;
import android.util.Log;
import defpackage.xz;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public class OfflinePageArchivePublisherBridge {
    @CalledByNative
    public static long addCompletedDownload(String str, String str2, String str3, long j, String str4, String str5) {
        try {
            DownloadManager downloadManager = (DownloadManager) xz.a.getSystemService("download");
            if (downloadManager == null) {
                return 0L;
            }
            return downloadManager.addCompletedDownload(str, str2, false, "multipart/related", str3, j, false, Uri.parse(str4), Uri.parse(str5));
        } catch (Exception e) {
            Log.i("cr_OPArchivePublisher", "ADM threw while trying to add a download. " + e);
            return 0L;
        }
    }

    @CalledByNative
    public static boolean isAndroidDownloadManagerInstalled() {
        return ((DownloadManager) xz.a.getSystemService("download")) != null;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x00f2 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0110 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0111  */
    @org.chromium.base.annotations.CalledByNative
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String publishArchiveToDownloadsCollection(org.chromium.chrome.browser.offlinepages.OfflinePageItem r17) {
        /*
            Method dump skipped, instruction units count: 331
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.offlinepages.OfflinePageArchivePublisherBridge.publishArchiveToDownloadsCollection(org.chromium.chrome.browser.offlinepages.OfflinePageItem):java.lang.String");
    }

    @CalledByNative
    public static int remove(long[] jArr) {
        DownloadManager downloadManager = (DownloadManager) xz.a.getSystemService("download");
        if (downloadManager == null) {
            return 0;
        }
        try {
            return downloadManager.remove(jArr);
        } catch (Exception e) {
            Log.i("cr_OPArchivePublisher", "ADM threw while trying to remove a download. " + e);
            return 0;
        }
    }
}
