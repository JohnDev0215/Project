package com.amazon.identity.mobi.common.env;

import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/* JADX INFO: loaded from: classes3.dex */
public interface MAPCommonEnvironmentUtils {
    String getAmazonHostFromPartialDomain(String str);

    void handleWebViewReceivedSslError(WebViewClient webViewClient, WebView webView, SslErrorHandler sslErrorHandler, SslError sslError);
}
