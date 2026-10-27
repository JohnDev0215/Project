package com.android.webview.chromium;

import android.webkit.WebViewDelegate;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class WebViewChromiumFactoryProviderForP extends WebViewChromiumFactoryProvider {
    public WebViewChromiumFactoryProviderForP(WebViewDelegate webViewDelegate) {
        super(webViewDelegate);
    }

    public static WebViewChromiumFactoryProvider create(WebViewDelegate webViewDelegate) {
        return new WebViewChromiumFactoryProviderForP(webViewDelegate);
    }
}
