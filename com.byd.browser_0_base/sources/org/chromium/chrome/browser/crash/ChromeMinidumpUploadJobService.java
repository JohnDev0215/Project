package org.chromium.chrome.browser.crash;

import defpackage.oj2;
import defpackage.xz;
import org.chromium.chrome.browser.base.SplitCompatMinidumpUploadJobService;
import org.chromium.chrome.browser.metrics.UmaUtils;

/* JADX INFO: loaded from: classes.dex */
public class ChromeMinidumpUploadJobService extends SplitCompatMinidumpUploadJobService {
    @Override // org.chromium.components.minidump_uploader.MinidumpUploadJobService
    public final void b(long j) {
        String str;
        oj2.T("Stability.Android.MinidumpUploadingTime", j, 1L, 86400000L, 50);
        switch (UmaUtils.a(xz.a)) {
            case 0:
                str = "Active";
                break;
            case 1:
                str = "WorkingSet";
                break;
            case 2:
                str = "Frequent";
                break;
            case 3:
                str = "Rare";
                break;
            case 4:
                str = "Restricted";
                break;
            case 5:
                str = "Unsupported";
                break;
            case 6:
                str = "Exempted";
                break;
            case 7:
                str = "Never";
                break;
            case 8:
                str = "Other";
                break;
            default:
                str = "Unknown";
                break;
        }
        oj2.T("Stability.Android.MinidumpUploadingTime.".concat(str), j, 1L, 86400000L, 50);
    }
}
