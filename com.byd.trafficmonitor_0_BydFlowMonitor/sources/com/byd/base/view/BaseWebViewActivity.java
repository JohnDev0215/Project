package com.byd.base.view;

import android.os.Build;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import c.a.a.c;
import c.a.a.d;

/* JADX INFO: loaded from: classes.dex */
public class BaseWebViewActivity extends BaseTitleActivity {
    public WebView j;

    public class a extends WebChromeClient {
        public a() {
        }

        @Override // android.webkit.WebChromeClient
        public void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
            BaseWebViewActivity.this.f4318b.setText(str);
        }
    }

    public class b extends WebViewClient {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ WebView f4327a;

        public b(WebView webView) {
            this.f4327a = webView;
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            this.f4327a.loadUrl(str);
            return true;
        }
    }

    @Override // com.byd.base.view.BaseTitleActivity
    public int b() {
        return d.web_view;
    }

    @Override // com.byd.base.view.BaseTitleActivity
    public void d() {
    }

    @Override // com.byd.base.view.BaseTitleActivity
    public void e() {
    }

    public void f(WebView webView) {
        if (webView == null) {
            return;
        }
        WebSettings settings = webView.getSettings();
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        if (Build.VERSION.SDK_INT >= 11) {
            settings.setDisplayZoomControls(false);
        }
        settings.setCacheMode(-1);
        settings.setAllowFileAccess(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setDefaultTextEncodingName("utf-8");
        webView.setWebChromeClient(new a());
        webView.setWebViewClient(new b(webView));
    }

    @Override // com.byd.base.view.BaseTitleActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(c.base_web_view_activity);
        WebView webView = (WebView) findViewById(c.a.a.b.web_view_activity);
        this.j = webView;
        f(webView);
        if (getIntent() != null) {
            this.j.loadUrl(getIntent().getStringExtra("URL"));
        }
    }
}
