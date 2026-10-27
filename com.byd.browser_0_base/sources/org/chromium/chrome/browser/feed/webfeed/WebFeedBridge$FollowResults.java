package org.chromium.chrome.browser.feed.webfeed;

import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes.dex */
public class WebFeedBridge$FollowResults {
    public final int a;
    public final WebFeedBridge$WebFeedMetadata b;

    @CalledByNative
    public WebFeedBridge$FollowResults(int i, WebFeedBridge$WebFeedMetadata webFeedBridge$WebFeedMetadata) {
        this.a = i;
        this.b = webFeedBridge$WebFeedMetadata;
    }
}
