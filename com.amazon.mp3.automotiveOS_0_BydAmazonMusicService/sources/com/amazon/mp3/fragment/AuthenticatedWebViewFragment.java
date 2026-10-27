package com.amazon.mp3.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.amazon.mp3.store.html5.map.MAPCookiesCallback;
import com.amazon.mp3.store.html5.map.MAPCookiesUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.web.webtarget.WebTarget;
import com.amazon.music.inappmessaging.internal.model.Splash;
import java.util.HashMap;

/* JADX INFO: loaded from: classes3.dex */
public abstract class AuthenticatedWebViewFragment extends BaseWebFragment {
    protected boolean mCookiesFetched = false;
    protected String mCurrentMusicTerritory;
    protected String mCurrentUrl;

    protected abstract void onCookiesError();

    protected void onWebChromeClientProgressChanged(WebView view, int newProgress) {
    }

    @Override // com.amazon.mp3.fragment.BaseWebFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewOnCreateView = super.onCreateView(inflater, container, savedInstanceState);
        String url = getUrl();
        this.mCurrentUrl = url;
        this.mCurrentMusicTerritory = getMusicTerritory(url);
        MAPCookiesUtil.fetchCookies(new CookiesCallback(this.mCurrentUrl), true);
        return viewOnCreateView;
    }

    @Override // com.amazon.mp3.fragment.BaseWebFragment
    protected void initWebView() {
        super.initWebView();
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.setOverScrollMode(2);
        this.mWebView.getSettings().setSupportMultipleWindows(true);
        this.mWebView.setWebChromeClient(new WebChromeClient() { // from class: com.amazon.mp3.fragment.AuthenticatedWebViewFragment.1
            @Override // android.webkit.WebChromeClient
            public boolean onCreateWindow(final WebView view, final boolean dialog, final boolean userGesture, Message resultMsg) {
                WebView.HitTestResult hitTestResult = view.getHitTestResult();
                if (hitTestResult.getType() != 7) {
                    return true;
                }
                view.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(hitTestResult.getExtra())));
                return false;
            }

            @Override // android.webkit.WebChromeClient
            public void onProgressChanged(WebView view, int newProgress) {
                AuthenticatedWebViewFragment.this.onWebChromeClientProgressChanged(view, newProgress);
            }
        });
    }

    private String getMusicTerritory(String url) {
        return Uri.parse(url).getQueryParameter(Splash.PARAMS_MUSIC_TERRITORY);
    }

    private String getUrl() {
        WebTarget webTarget = (WebTarget) getArguments().getSerializable("com.amazon.mp3.extra.WebTargetKey.EXTRA_WEB_TARGET");
        if (webTarget == null) {
            throw new IllegalArgumentException("Cannot call webview loader fragment without a web target.");
        }
        return webTarget.getUrl();
    }

    protected boolean requiresNewCookies(final String oldMusicTerritory, final String newMusicTerritory) {
        if (TextUtils.isEmpty(newMusicTerritory)) {
            return false;
        }
        return !newMusicTerritory.equals(oldMusicTerritory);
    }

    protected void requestNewCookies() {
        Log.info(AuthenticatedWebViewFragment.class.getSimpleName(), "Detected domain change to country: " + this.mCurrentMusicTerritory + " in url. Requesting new cookies for the new country.");
        this.mCookiesFetched = false;
        MAPCookiesUtil.fetchCookiesForMusicTerritory(this.mCurrentMusicTerritory, new CookiesCallback(this.mCurrentUrl), true);
    }

    public boolean urlDomainChanged(final String url) {
        String str = this.mCurrentMusicTerritory;
        String musicTerritory = getMusicTerritory(url);
        this.mCurrentMusicTerritory = musicTerritory;
        this.mCurrentUrl = url;
        if (!requiresNewCookies(str, musicTerritory)) {
            return false;
        }
        requestNewCookies();
        return true;
    }

    private class CookiesCallback extends MAPCookiesCallback {
        CookiesCallback(final String url) {
            super(AuthenticatedWebViewFragment.this.getApplication().getApplicationContext(), url);
        }

        @Override // com.amazon.mp3.store.html5.map.MAPCookiesCallback, com.amazon.identity.auth.device.api.Callback
        public void onError(Bundle errorBundle) {
            super.onError(errorBundle);
            AuthenticatedWebViewFragment authenticatedWebViewFragment = AuthenticatedWebViewFragment.this;
            authenticatedWebViewFragment.mCookiesFetched = false;
            authenticatedWebViewFragment.onCookiesError();
        }

        @Override // com.amazon.mp3.store.html5.map.MAPCookiesCallback, com.amazon.identity.auth.device.api.Callback
        public void onSuccess(final Bundle successBundle) {
            super.onSuccess(successBundle);
            AuthenticatedWebViewFragment.this.mCookiesFetched = true;
            this.mHandler.postDelayed(new Runnable() { // from class: com.amazon.mp3.fragment.AuthenticatedWebViewFragment.CookiesCallback.1
                @Override // java.lang.Runnable
                public void run() {
                    AuthenticatedWebViewFragment authenticatedWebViewFragment = AuthenticatedWebViewFragment.this;
                    authenticatedWebViewFragment.loadWebView(authenticatedWebViewFragment.mCurrentUrl);
                }
            }, 101L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadWebView(final String url) {
        if (this.mCookiesFetched) {
            Log.verbose(AuthenticatedWebViewFragment.class.getSimpleName(), "Webview url is: " + url);
            HashMap map = new HashMap();
            map.put("Referer", Uri.parse(url).getHost());
            this.mWebView.loadUrl(url, map);
        }
    }
}
