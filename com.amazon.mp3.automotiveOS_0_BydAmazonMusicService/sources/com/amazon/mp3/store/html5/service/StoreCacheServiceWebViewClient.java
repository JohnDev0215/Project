package com.amazon.mp3.store.html5.service;

import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.amazon.mp3.store.html5.webview.StoreWebView;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.WebViewUtil;

/* JADX INFO: loaded from: classes4.dex */
class StoreCacheServiceWebViewClient extends WebViewClient {
    private static final String TAG = StoreCacheServiceWebViewClient.class.getSimpleName();
    private final StoreCacheService mService;

    public StoreCacheServiceWebViewClient(final StoreCacheService service) {
        this.mService = service;
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        handler.cancel();
        this.mService.onDoneCaching();
        Log.warning(TAG, "Gave up on store caching due to SSL error: %s", error.toString());
        WebViewUtil.handleSslError(view.getContext(), view.getSettings());
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        StoreWebView storeWebView;
        Log.warning(TAG, "Giving up on store caching due to error retrieving %s: %s", failingUrl, description);
        if ((errorCode == -6 || errorCode == -2) && (storeWebView = StoreWebView.getInstance(this.mService)) != null) {
            storeWebView.setNetworkAvailable(false);
            storeWebView.setVisibility(4);
        }
        StoreWebView.destroyWebViewIfNotAttached();
        this.mService.onWebViewClientReportedError();
        super.onReceivedError(view, errorCode, description, failingUrl);
    }

    public boolean equals(Object o) {
        return super.equals(o);
    }

    public int hashCode() {
        return super.hashCode();
    }
}
