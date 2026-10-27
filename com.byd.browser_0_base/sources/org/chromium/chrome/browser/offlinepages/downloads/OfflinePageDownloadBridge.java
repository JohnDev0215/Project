package org.chromium.chrome.browser.offlinepages.downloads;

import J.N;
import org.chromium.base.Callback;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.download.DownloadManagerService;
import org.chromium.chrome.browser.offlinepages.d;
import org.chromium.chrome.browser.profiles.Profile;

/* JADX INFO: loaded from: classes2.dex */
public class OfflinePageDownloadBridge {
    public static OfflinePageDownloadBridge a;

    public OfflinePageDownloadBridge() {
        N.MnuITPuJ(this);
    }

    @CalledByNative
    public static void openItem(String str, final long j, final int i, final boolean z, final boolean z2) {
        d.b(j, i, new Callback(j, i, z2, z) { // from class: zq1
            public final /* synthetic */ int e;
            public final /* synthetic */ boolean f;
            public final /* synthetic */ boolean g;

            {
                this.e = i;
                this.f = z2;
                this.g = z;
            }

            /* JADX WARN: Removed duplicated region for block: B:27:0x00b6  */
            @Override // org.chromium.base.Callback
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final void onResult(java.lang.Object r8) {
                /*
                    Method dump skipped, instruction units count: 210
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: defpackage.zq1.onResult(java.lang.Object):void");
            }
        }, Profile.d());
    }

    @CalledByNative
    public static void showDownloadingToast() {
        DownloadManagerService.g().m.c(null, true, false, false);
    }
}
