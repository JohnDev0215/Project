package com.android.webview.chromium;

import android.webkit.PacProcessor;
import android.webkit.WebViewDelegate;
import defpackage.AbstractC1475s8;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class WebViewChromiumFactoryProviderForR extends WebViewChromiumFactoryProvider {
    public WebViewChromiumFactoryProviderForR(WebViewDelegate webViewDelegate) {
        super(webViewDelegate);
    }

    public static WebViewChromiumFactoryProvider create(WebViewDelegate webViewDelegate) {
        return new WebViewChromiumFactoryProviderForR(webViewDelegate);
    }

    public PacProcessor getPacProcessor() {
        return AbstractC1475s8.a;
    }
}
