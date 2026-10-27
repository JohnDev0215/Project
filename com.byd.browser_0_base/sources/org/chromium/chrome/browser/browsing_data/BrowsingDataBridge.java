package org.chromium.chrome.browser.browsing_data;

import J.N;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.profiles.Profile;

/* JADX INFO: loaded from: classes.dex */
public final class BrowsingDataBridge {
    public static BrowsingDataBridge b;
    public a a;

    public interface ImportantSitesCallback {
        @CalledByNative
        void onImportantRegisterableDomainsReady(String[] strArr, String[] strArr2, int[] iArr, boolean z);
    }

    public interface OtherFormsOfBrowsingHistoryListener {
        @CalledByNative
        void enableDialogAboutOtherFormsOfBrowsingHistory();
    }

    public interface a {
        void s();
    }

    public static BrowsingDataBridge b() {
        Object obj = ThreadUtils.a;
        if (b == null) {
            b = new BrowsingDataBridge();
        }
        return b;
    }

    public final void a(a aVar, int[] iArr, int i) {
        this.a = aVar;
        N.McYsV35Z(this, Profile.d(), iArr, i, new String[0], new int[0], new String[0], new int[0]);
    }

    @CalledByNative
    public void browsingDataCleared() {
        a aVar = this.a;
        if (aVar != null) {
            aVar.s();
            this.a = null;
        }
    }

    public void setmClearBrowsingDataListener(a aVar) {
        this.a = aVar;
    }
}
