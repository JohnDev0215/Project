package org.chromium.chrome.browser.download;

import J.N;
import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import defpackage.gm3;
import defpackage.km0;
import defpackage.ku2;
import defpackage.wb0;
import defpackage.xz;
import java.util.ArrayList;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.download.DownloadInfo;
import org.chromium.chrome.browser.download.b;
import org.chromium.components.download.DownloadCollectionBridge;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes.dex */
public class DownloadController {
    public static a a;

    public interface a {
    }

    @CalledByNative
    public static void enqueueAndroidDownloadManagerRequest(GURL gurl, String str, String str2, String str3, String str4, GURL gurl2) {
        DownloadInfo.a aVar = new DownloadInfo.a();
        aVar.a = gurl;
        aVar.b = str;
        aVar.e = str2;
        aVar.c = str3;
        aVar.d = str4;
        aVar.h = gurl2;
        aVar.l = true;
        DownloadInfo downloadInfoA = aVar.a();
        DownloadManagerService downloadManagerServiceG = DownloadManagerService.g();
        new DownloadItem(downloadInfoA, true);
        downloadManagerServiceG.getClass();
        Log.e("cr_ggy enqueueNewDownload", "dadada");
        Log.w("cr_ggy enqueueNewDownload", "dadada");
    }

    @CalledByNative
    public static boolean hasFileAccess(WindowAndroid windowAndroid) {
        ArrayList arrayList = DownloadCollectionBridge.a;
        return true;
    }

    @CalledByNative
    public static void onDownloadCancelled(DownloadInfo downloadInfo) {
        a aVar = a;
        if (aVar == null) {
            return;
        }
        ((DownloadManagerService) aVar).l(downloadInfo);
    }

    @CalledByNative
    public static void onDownloadCompleted(DownloadInfo downloadInfo) {
        String str = downloadInfo.g;
        int i = 1;
        if (str != null && !str.isEmpty()) {
            b.e.a.a(new gm3(str, i));
        }
        boolean zIsEmpty = TextUtils.isEmpty(downloadInfo.g);
        String strRemapGenericMimeType = downloadInfo.c;
        if (!zIsEmpty && strRemapGenericMimeType != null) {
            strRemapGenericMimeType.startsWith("image/");
        }
        a aVar = a;
        if (aVar == null) {
            return;
        }
        DownloadManagerService downloadManagerService = (DownloadManagerService) aVar;
        if (downloadInfo.j == 0) {
            i = 2;
        } else {
            strRemapGenericMimeType = MimeUtils.remapGenericMimeType(strRemapGenericMimeType, downloadInfo.i.j(), downloadInfo.e);
        }
        DownloadInfo.a aVarB = DownloadInfo.a.b(downloadInfo);
        aVarB.c = strRemapGenericMimeType;
        DownloadItem downloadItem = new DownloadItem(new DownloadInfo(aVarB), false);
        String str2 = downloadInfo.l;
        Object obj = DownloadManagerBridge.a;
        Log.e("cr_DownloadDelegate", "ggy getDownloadIdForDownloadGuid ");
        ku2 ku2VarT = ku2.t();
        try {
            long j = xz.a.getSharedPreferences("download_id_mappings", 0).getLong(str2, -1L);
            ku2VarT.close();
            downloadItem.d = j;
            downloadItem.a.b = downloadItem.a();
            downloadManagerService.p(downloadItem, i);
        } catch (Throwable th) {
            try {
                ku2VarT.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    @CalledByNative
    public static void onDownloadInterrupted(DownloadInfo downloadInfo, boolean z) {
        int i;
        a aVar = a;
        if (aVar == null) {
            return;
        }
        DownloadManagerService downloadManagerService = (DownloadManagerService) aVar;
        DownloadItem downloadItem = new DownloadItem(downloadInfo, false);
        if (downloadInfo.r) {
            if (z) {
                downloadItem.a();
            }
            i = 4;
        } else {
            i = 2;
        }
        downloadManagerService.p(downloadItem, i);
    }

    @CalledByNative
    public static void onDownloadStarted() {
        Activity activity = ApplicationStatus.c;
        Toast.makeText(activity, activity.getResources().getString(R$string.forbid_downloading), 1).show();
    }

    @CalledByNative
    public static void onDownloadUpdated(DownloadInfo downloadInfo) {
        Toast.makeText(ApplicationStatus.c, "禁止->updated", 1).show();
    }

    @CalledByNative
    public static void requestFileAccess(long j, WindowAndroid windowAndroid) {
        if (windowAndroid == null) {
            N.MLbF8aR_(j, false, null);
        } else {
            km0.a(windowAndroid, new wb0(0, j));
        }
    }
}
