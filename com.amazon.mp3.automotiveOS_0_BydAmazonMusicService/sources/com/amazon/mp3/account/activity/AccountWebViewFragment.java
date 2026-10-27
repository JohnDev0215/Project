package com.amazon.mp3.account.activity;

import android.content.Context;
import android.net.http.SslError;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.amazon.mp3.fragment.AuthenticatedWebViewFragment;
import com.amazon.mp3.fragment.BaseWebFragment;
import com.amazon.mp3.store.html5.bridge.JavascriptBridge;
import com.amazon.mp3.store.html5.bridge.Message;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.WebViewUtil;
import com.amazon.mp3.web.webtarget.WebTarget;

/* JADX INFO: loaded from: classes3.dex */
public class AccountWebViewFragment extends AuthenticatedWebViewFragment {
    private static final String TAG = AccountWebViewFragment.class.getSimpleName();
    private AccountWebViewListener mAccountWebViewListener;

    @Override // com.amazon.mp3.fragment.BaseWebFragment
    protected JavascriptBridge createJavascriptBridge() {
        return null;
    }

    @Override // com.amazon.mp3.fragment.BaseWebFragment
    protected void handleMessage(Message message) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.fragment.app.Fragment
    public void onAttach(final Context context) {
        super.onAttach(context);
        try {
            this.mAccountWebViewListener = (AccountWebViewListener) context;
        } catch (ClassCastException unused) {
            throw new ClassCastException(context.toString() + " must implement AccountWebViewListener");
        }
    }

    public static AccountWebViewFragment newInstance(final WebTarget webTarget) {
        AccountWebViewFragment accountWebViewFragment = new AccountWebViewFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("com.amazon.mp3.extra.WebTargetKey.EXTRA_WEB_TARGET", webTarget);
        accountWebViewFragment.setArguments(bundle);
        return accountWebViewFragment;
    }

    @Override // com.amazon.mp3.fragment.BaseWebFragment
    protected WebViewClient createWebViewClient() {
        return new WebViewClient() { // from class: com.amazon.mp3.account.activity.AccountWebViewFragment.1
            @Override // android.webkit.WebViewClient
            public void onReceivedError(final WebView view, final WebResourceRequest request, final WebResourceError error) {
                if (ConnectivityUtil.hasAnyInternetConnection()) {
                    return;
                }
                ((BaseWebFragment) AccountWebViewFragment.this).mWebView.stopLoading();
                if (request.hasGesture()) {
                    AccountWebViewFragment.this.mAccountWebViewListener.onWebViewFlowFailure();
                }
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedHttpError(final WebView view, final WebResourceRequest request, final WebResourceResponse errorResponse) {
                if (ConnectivityUtil.hasAnyInternetConnection()) {
                    return;
                }
                ((BaseWebFragment) AccountWebViewFragment.this).mWebView.stopLoading();
                if (request.hasGesture()) {
                    AccountWebViewFragment.this.mAccountWebViewListener.onWebViewFlowFailure();
                }
            }

            @Override // android.webkit.WebViewClient
            public void onPageFinished(final WebView view, final String url) {
                super.onPageFinished(view, url);
                ((BaseWebFragment) AccountWebViewFragment.this).mProgressBar.setVisibility(8);
                ((BaseWebFragment) AccountWebViewFragment.this).mWebView.setVisibility(0);
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedSslError(final WebView view, final SslErrorHandler handler, final SslError error) {
                handler.cancel();
                Log.warning(AccountWebViewFragment.TAG, "SSL Error: " + error);
                WebViewUtil.handleSslError(view.getContext(), view.getSettings());
                AccountWebViewFragment.this.mAccountWebViewListener.onWebViewFlowFailure();
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(final WebView view, final String url) {
                Log.info(AccountWebViewFragment.TAG, "Webview redirected to: " + url);
                if (!ConnectivityUtil.hasAnyInternetConnection()) {
                    Log.error(AccountWebViewFragment.TAG, "Unable to load AccountWebView page due to network loss");
                    AccountWebViewFragment.this.mAccountWebViewListener.onWebViewFlowFailure();
                    return true;
                }
                if (AccountWebViewFragment.this.mAccountWebViewListener == null || TextUtils.isEmpty(url)) {
                    return false;
                }
                if (url.contains("/result=Success")) {
                    AccountWebViewFragment.this.mAccountWebViewListener.onWebViewFlowSuccess();
                    return true;
                }
                if (url.contains("/result=Cancel")) {
                    AccountWebViewFragment.this.mAccountWebViewListener.onWebViewFlowFailure();
                    return true;
                }
                return AccountWebViewFragment.this.urlDomainChanged(url);
            }
        };
    }

    @Override // com.amazon.mp3.fragment.AuthenticatedWebViewFragment
    protected void onCookiesError() {
        AccountWebViewListener accountWebViewListener = this.mAccountWebViewListener;
        if (accountWebViewListener != null) {
            accountWebViewListener.onWebViewFlowFailure();
        }
    }
}
