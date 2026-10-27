package com.android.webview.chromium;

import android.webkit.WebViewDelegate;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class WebViewChromiumFactoryProviderForOMR1 extends WebViewChromiumFactoryProvider {
    public WebViewChromiumFactoryProviderForOMR1(WebViewDelegate webViewDelegate) {
        super(webViewDelegate);
    }

    public static WebViewChromiumFactoryProvider create(WebViewDelegate webViewDelegate) {
        return new WebViewChromiumFactoryProviderForOMR1(webViewDelegate);
    }
}
