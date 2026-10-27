package org.chromium.chrome.browser.offlinepages;

import android.app.Activity;
import org.chromium.base.ApplicationStatus;
import org.chromium.chrome.browser.offlinepages.d;

/* JADX INFO: compiled from: OfflinePageUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c implements ApplicationStatus.c {
    @Override // org.chromium.base.ApplicationStatus.c
    public final void d(Activity activity, int i) {
        if (i == 6) {
            ((d.C0097d) d.b.remove(activity)).a();
            ApplicationStatus.i(this);
        }
    }
}
