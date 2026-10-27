package com.amazon.music.explore.views.livestream;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.amazon.music.explore.R;

/* JADX INFO: loaded from: classes4.dex */
public class LiveStreamVideoView extends WebView {
    private static String UNMUTE_VIDEO_JS = "javascript:(function() { var video = document.getElementsByTagName('video')[0]; if (!!video) { video.muted = false; } })()";
    private FullScreenCallback fullScreenCallback;

    public interface FullScreenCallback {
        void onHideFullScreen();

        void onShowFullScreen(View view, WebChromeClient.CustomViewCallback customViewCallback);
    }

    public LiveStreamVideoView(Context context) {
        super(context);
        init();
    }

    public LiveStreamVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    @Override // android.webkit.WebView
    public void onPause() {
        super.onPause();
    }

    public void setFullScreenCallback(FullScreenCallback fullScreenCallback) {
        this.fullScreenCallback = fullScreenCallback;
    }

    public void setDebug(boolean z) {
        WebView.setWebContentsDebuggingEnabled(z);
    }

    public void bindWithUrl(String str) {
        if (getOriginalUrl() == null || !getOriginalUrl().equals(str)) {
            loadUrl(str);
        }
    }

    private void init() {
        setLayerType(2, null);
        getSettings().setJavaScriptEnabled(true);
        getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        getSettings().setLoadWithOverviewMode(true);
        getSettings().setMediaPlaybackRequiresUserGesture(false);
        setWebChromeClient(new ChromeClient());
        setWebViewClient(new ViewClient());
        setBackgroundColor(getResources().getColor(R.color.black));
    }

    private class ViewClient extends WebViewClient {
        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            return true;
        }

        private ViewClient() {
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            webView.loadUrl(LiveStreamVideoView.UNMUTE_VIDEO_JS);
        }
    }

    private class ChromeClient extends WebChromeClient {
        private View customView;
        private WebChromeClient.CustomViewCallback customViewCallback;

        ChromeClient() {
        }

        @Override // android.webkit.WebChromeClient
        public Bitmap getDefaultVideoPoster() {
            return Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_8888);
        }

        @Override // android.webkit.WebChromeClient
        public void onHideCustomView() {
            this.customView = null;
            if (LiveStreamVideoView.this.fullScreenCallback != null) {
                LiveStreamVideoView.this.fullScreenCallback.onHideFullScreen();
            }
            this.customViewCallback.onCustomViewHidden();
            this.customViewCallback = null;
        }

        @Override // android.webkit.WebChromeClient
        public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
            this.customView = view;
            this.customViewCallback = customViewCallback;
            if (LiveStreamVideoView.this.fullScreenCallback != null) {
                LiveStreamVideoView.this.fullScreenCallback.onShowFullScreen(view, customViewCallback);
            }
        }
    }
}
