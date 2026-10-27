package org.chromium.chrome.browser.feed.webfeed;

import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes.dex */
public class WebFeedBridge$QueryResult {
    public final String a;
    public final String b;
    public final String c;

    @CalledByNative
    public WebFeedBridge$QueryResult(String str, String str2, String str3) {
        this.a = str;
        this.b = str2;
        this.c = str3;
    }
}
