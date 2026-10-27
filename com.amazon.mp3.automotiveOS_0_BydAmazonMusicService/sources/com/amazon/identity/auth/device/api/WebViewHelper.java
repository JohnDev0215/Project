package com.amazon.identity.auth.device.api;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.qe;
import com.amazon.identity.auth.device.t6;
import com.amazon.identity.auth.device.yd;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class WebViewHelper {
    public static boolean enableAmazonAuthenticatorForWebView(WebView webView, Bundle bundle) {
        if (!qe.a(webView.getContext())) {
            return false;
        }
        if (!webView.getSettings().getJavaScriptEnabled()) {
            Log.w(ga.a("WebViewHelper"), webView.getContext().getPackageName() + " disabled the JavaScript on WebView. MAP will enable the JavaScript.");
            yd.b("MAPWebViewJavaScriptOriginallyDisabled:" + webView.getContext().getPackageName(), new String[0]);
            webView.getSettings().setJavaScriptEnabled(true);
        }
        webView.addJavascriptInterface(new t6(webView), "FidoAuthenticatorJSBridge");
        return true;
    }
}
