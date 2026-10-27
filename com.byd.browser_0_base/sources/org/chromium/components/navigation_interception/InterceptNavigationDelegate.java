package org.chromium.components.navigation_interception;

import org.chromium.base.annotations.CalledByNative;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.url.GURL;
import org.chromium.url.Origin;

/* JADX INFO: loaded from: classes3.dex */
public abstract class InterceptNavigationDelegate {
    @CalledByNative
    public GURL handleSubframeExternalProtocol(GURL gurl, int i, boolean z, Origin origin) {
        return null;
    }

    @CalledByNative
    public abstract boolean shouldIgnoreNavigation(NavigationHandle navigationHandle, GURL gurl, boolean z, boolean z2);

    @CalledByNative
    public void onResourceRequestWithGesture() {
    }
}
