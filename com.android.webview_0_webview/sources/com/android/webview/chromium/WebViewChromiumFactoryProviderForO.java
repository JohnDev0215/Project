package com.android.webview.chromium;

import android.webkit.WebViewDelegate;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class WebViewChromiumFactoryProviderForO extends WebViewChromiumFactoryProvider {
    public WebViewChromiumFactoryProviderForO(WebViewDelegate webViewDelegate) {
        super(webViewDelegate);
    }

    public static WebViewChromiumFactoryProvider create(WebViewDelegate webViewDelegate) {
        return new WebViewChromiumFactoryProviderForO(webViewDelegate);
    }
}
