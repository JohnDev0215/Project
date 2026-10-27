package org.chromium.chrome.browser.supervised_user;

import defpackage.a8;
import defpackage.tv1;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes2.dex */
public class WebsiteParentApproval {

    public static final class a {
    }

    @CalledByNative
    public static boolean isLocalApprovalSupported() {
        return false;
    }

    @CalledByNative
    public static void requestLocalApproval(WindowAndroid windowAndroid, GURL gurl) {
        tv1.a().getClass();
        a8.d0();
        throw null;
    }
}
