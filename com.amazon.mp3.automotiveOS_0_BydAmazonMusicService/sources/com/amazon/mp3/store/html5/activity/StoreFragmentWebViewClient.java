package com.amazon.mp3.store.html5.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Handler;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.amazon.mp3.store.html5.util.StoreUrls;
import com.amazon.mp3.store.html5.webview.StoreWebView;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.MediaPlaybackUtil;
import com.amazon.mp3.util.WebViewUtil;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
class StoreFragmentWebViewClient extends WebViewClient {
    private static final String TAG = StoreFragmentWebViewClient.class.getSimpleName();
    private final StoreFragment mStoreFragment;
    private final StoreWebView mWebView;
    private final Handler mHandler = new Handler();
    private final Runnable mBridgeTimeoutRunnable = new Runnable() { // from class: com.amazon.mp3.store.html5.activity.StoreFragmentWebViewClient.1
        @Override // java.lang.Runnable
        public void run() {
            StoreFragmentWebViewClient.this.mWebView.loadBlankAndClearHistory();
            StoreFragmentWebViewClient.this.mWebView.setState(StoreWebView.State.Failed);
            StoreFragmentWebViewClient.this.mStoreFragment.showConnectionErrorDialog();
        }
    };

    public StoreFragmentWebViewClient(StoreFragment storeFragment) {
        this.mStoreFragment = storeFragment;
        this.mWebView = StoreWebView.getInstance(storeFragment.getActivity());
    }

    public void clearBridgeTimeout() {
        this.mHandler.removeCallbacks(this.mBridgeTimeoutRunnable);
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        this.mStoreFragment.onWebViewClientReportedError(errorCode);
        super.onReceivedError(view, errorCode, description, failingUrl);
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        handler.cancel();
        this.mStoreFragment.showConnectionErrorDialog();
        Log.warning(TAG, "Gave up loading store due to SSL error: %s", error.toString());
        WebViewUtil.handleSslError(view.getContext(), view.getSettings());
    }

    @Override // android.webkit.WebViewClient
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        this.mHandler.removeCallbacks(this.mBridgeTimeoutRunnable);
        if (StoreUrls.pathContainsRootPath(Uri.parse(url).getPath())) {
            this.mHandler.postDelayed(this.mBridgeTimeoutRunnable, 60000L);
        }
        if ("about:blank".equals(url)) {
            return;
        }
        super.onPageStarted(view, url, favicon);
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView view, String url) {
        if (this.mStoreFragment.isClearingCacheAndReloading() && "about:blank".equals(url)) {
            this.mWebView.loadUrl(StoreUrls.getBaseUrl());
            this.mStoreFragment.onFinishedClearingCacheAndReloading();
        }
        super.onPageFinished(view, url);
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (url.toLowerCase(Locale.US).endsWith(".mp4")) {
            this.mStoreFragment.startActivity(MediaPlaybackUtil.getPlayVideoIntent(url));
            return true;
        }
        if (!StoreUrls.isUrlTrusted(url)) {
            this.mStoreFragment.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url)));
            return true;
        }
        return super.shouldOverrideUrlLoading(view, url);
    }

    public boolean equals(Object o) {
        return super.equals(o);
    }

    public int hashCode() {
        return super.hashCode();
    }
}
