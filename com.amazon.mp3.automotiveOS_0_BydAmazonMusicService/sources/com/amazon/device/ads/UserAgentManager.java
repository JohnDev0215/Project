package com.amazon.device.ads;

import com.amazon.device.ads.ThreadUtils;

/* JADX INFO: loaded from: classes2.dex */
class UserAgentManager {
    private final ThreadUtils.ThreadRunner threadRunner;
    private String userAgentStringWithSDKVersion;
    private final WebViewFactory webViewFactory;

    public UserAgentManager() {
        this(new ThreadUtils.ThreadRunner(), WebViewFactory.getInstance());
    }

    UserAgentManager(ThreadUtils.ThreadRunner threadRunner, WebViewFactory webViewFactory) {
        this.threadRunner = threadRunner;
        this.webViewFactory = webViewFactory;
    }

    public String getUserAgentString() {
        return this.userAgentStringWithSDKVersion;
    }
}
