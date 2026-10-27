package com.byd.base.view;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.NonNull;
import com.byd.base.R$id;
import com.byd.base.R$layout;
import com.byd.base.R$string;

/* JADX INFO: loaded from: classes.dex */
public class BaseWebViewActivity extends BaseTitleActivity {
    public WebView j;
    public String k;
    public String l;

    public class a implements View.OnLongClickListener {
        public a() {
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            return true;
        }
    }

    public class b extends WebChromeClient {
        public b() {
        }

        @Override // android.webkit.WebChromeClient
        public void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
        }
    }

    public class c extends WebViewClient {
        public c() {
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            BaseWebViewActivity.this.l = webView.getUrl();
            b.a.d.b.a.a("BaseWebViewActivity", "onPageFinished:" + BaseWebViewActivity.this.l);
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            b.a.d.b.a.a("BaseWebViewActivity", "onPageStarted:" + str);
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            String string = webResourceRequest.getUrl().toString();
            b.a.d.b.a.a("BaseWebViewActivity", "shouldOverrideUrlLoading:" + string);
            b.a.b.a.a aVarB = b.a.b.a.b.a().b();
            if (string.contains("privacy")) {
                aVarB.a(BaseWebViewActivity.this);
                return true;
            }
            if (!string.contains("agreement") || string.contains("agreementType")) {
                return super.shouldOverrideUrlLoading(webView, webResourceRequest);
            }
            aVarB.b(BaseWebViewActivity.this);
            return true;
        }
    }

    @Override // com.byd.base.view.BaseTitleActivity
    public int i() {
        return R$string.web_view;
    }

    public void n(WebView webView) {
        if (webView == null) {
            return;
        }
        webView.setOnLongClickListener(new a());
        webView.setLongClickable(false);
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
        settings.setCacheMode(2);
        settings.setAllowFileAccess(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setDefaultTextEncodingName("utf-8");
        webView.setWebChromeClient(new b());
        webView.setWebViewClient(new c());
    }

    @Override // com.byd.base.view.BaseTitleActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R$layout.base_web_view_activity);
        WebView webView = (WebView) findViewById(R$id.web_view_activity);
        this.j = webView;
        n(webView);
        if (getIntent() != null) {
            String stringExtra = getIntent().getStringExtra("URL");
            if (b.a.b.c.c.b(stringExtra)) {
                return;
            }
            this.j.loadUrl(stringExtra);
            String stringExtra2 = getIntent().getStringExtra("title");
            this.k = stringExtra2;
            if (b.a.b.c.c.b(stringExtra2)) {
                return;
            }
            this.f539b.setText(this.k);
        }
    }

    @Override // com.byd.base.view.BaseTitleActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        WebView webView = this.j;
        if (webView != null) {
            webView.setWebViewClient(null);
            this.j.setWebChromeClient(null);
            this.j.loadUrl("about:blank");
            this.j.clearCache(false);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("last_load_url", this.l);
    }
}
