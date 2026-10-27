package org.chromium.chrome.browser.suggestions.mostvisited;

import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public interface MostVisitedSites$HomepageClient {
    @CalledByNative
    String getHomepageUrl();

    @CalledByNative
    boolean isHomepageTileEnabled();
}
