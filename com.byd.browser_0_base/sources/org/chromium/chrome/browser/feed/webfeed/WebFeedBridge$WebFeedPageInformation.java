package org.chromium.chrome.browser.feed.webfeed;

import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes.dex */
public class WebFeedBridge$WebFeedPageInformation {
    public final GURL a;
    public final Tab b;

    public WebFeedBridge$WebFeedPageInformation(Tab tab, GURL gurl) {
        this.a = gurl;
        this.b = tab;
    }

    @CalledByNative
    public Tab getTab() {
        return this.b;
    }

    @CalledByNative
    public GURL getUrl() {
        return this.a;
    }
}
