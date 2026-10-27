package org.chromium.chrome.browser.policy;

import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.AppHooks;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes2.dex */
public class PolicyAuditorBridge {
    @CalledByNative
    public static PolicyAuditor getPolicyAuditor() {
        AppHooks.get().getClass();
        return null;
    }

    @CalledByNative
    public static void notifyAuditEventForDidFinishLoad(GURL gurl, PolicyAuditor policyAuditor) {
        gurl.j();
        policyAuditor.getClass();
    }

    @CalledByNative
    public static void notifyAuditEventForDidFinishNavigation(NavigationHandle navigationHandle, PolicyAuditor policyAuditor) {
        if (navigationHandle.l != 0) {
            navigationHandle.e.j();
            policyAuditor.getClass();
        }
    }
}
