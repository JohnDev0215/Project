package com.amazon.identity.auth.device.api;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import com.amazon.identity.auth.device.e;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.ya;
import com.amazon.identity.auth.device.yd;
import java.net.URL;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class MAPAndroidWebViewClient extends WebViewClient {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final MAPAndroidWebViewHelper f211a;

    @FireOsSdk
    public MAPAndroidWebViewClient(MAPAndroidWebViewHelper mAPAndroidWebViewHelper) {
        e.a(mAPAndroidWebViewHelper, "MAPAndroidWebViewHelper");
        this.f211a = mAPAndroidWebViewHelper;
    }

    @Override // android.webkit.WebViewClient
    @FireOsSdk
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        ga.a("MAPAndroidWebViewClient");
        if (this.f211a.handleAuthentication(webView, str)) {
            return;
        }
        super.onPageStarted(webView, str, bitmap);
    }

    @Override // android.webkit.WebViewClient
    @FireOsSdk
    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        sslError.toString();
        ga.a("MAPAndroidWebViewClient");
        URL urlA = ya.a(sslError.getUrl());
        if (urlA != null) {
            String str = urlA.getHost() + urlA.getPath();
            Log.i(ga.a("MAPAndroidWebViewClient"), "SSL error when hitting " + str);
            yd.b("MAPWebViewSSLError_" + str, new String[0]);
        }
        super.onReceivedSslError(webView, sslErrorHandler, sslError);
    }

    @Override // android.webkit.WebViewClient
    @FireOsSdk
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (!webView.getSettings().getJavaScriptEnabled()) {
            Log.e(ga.a("MAPAndroidWebViewClient"), "App disabled the JavaScript on WebView: " + webView.getContext().getPackageName());
            yd.b("MAPWebViewJavaScriptDisabled:" + webView.getContext().getPackageName(), new String[0]);
        }
        ga.a("MAPAndroidWebViewClient");
        return this.f211a.handleAuthentication(webView, str);
    }

    @FireOsSdk
    public MAPAndroidWebViewClient(Activity activity) {
        e.a((Object) activity, "Activity");
        this.f211a = new MAPAndroidWebViewHelper(activity);
    }
}
