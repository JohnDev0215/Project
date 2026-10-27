package com.byd.base.view;

import android.content.Context;
import android.content.Intent;
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
import b.b.a.h.j;
import com.byd.base.R$id;
import com.byd.base.R$layout;
import com.byd.base.R$string;

/* JADX INFO: loaded from: classes.dex */
public class BaseWebViewActivity extends BaseTitleActivity {
    public WebView i;
    public String j;
    public String k;

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
            BaseWebViewActivity.this.k = webView.getUrl();
            b.b.m.c.a.a("BaseWebViewActivity", "onPageFinished:" + BaseWebViewActivity.this.k);
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            b.b.m.c.a.a("BaseWebViewActivity", "onPageStarted:" + str);
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            String string = webResourceRequest.getUrl().toString();
            b.b.m.c.a.a("BaseWebViewActivity", "shouldOverrideUrlLoading:" + string);
            b.b.a.e.b bVarC = b.b.a.e.c.b().c();
            if (string.contains("privacy")) {
                bVarC.a(BaseWebViewActivity.this);
                return true;
            }
            if (!string.contains("agreement") || string.contains("agreementType")) {
                return super.shouldOverrideUrlLoading(webView, webResourceRequest);
            }
            bVarC.b(BaseWebViewActivity.this);
            return true;
        }
    }

    public static void v(Context context, String str, String str2) {
        Intent intent = new Intent();
        intent.setClass(context, BaseWebViewActivity.class);
        intent.putExtra("URL", str);
        intent.putExtra("title", str2);
        context.startActivity(intent);
    }

    @Override // com.byd.base.view.BaseTitleActivity
    public int f() {
        return R$string.web_view;
    }

    @Override // com.byd.base.view.BaseTitleActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R$layout.base_web_view_activity);
        WebView webView = (WebView) findViewById(R$id.web_view_activity);
        this.i = webView;
        w(webView);
        if (getIntent() != null) {
            String stringExtra = getIntent().getStringExtra("URL");
            if (j.c(stringExtra)) {
                return;
            }
            this.i.loadUrl(stringExtra);
            String stringExtra2 = getIntent().getStringExtra("title");
            this.j = stringExtra2;
            if (j.c(stringExtra2)) {
                return;
            }
            this.f2446a.setText(this.j);
        }
    }

    @Override // com.byd.base.view.BaseTitleActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        WebView webView = this.i;
        if (webView != null) {
            webView.setWebViewClient(null);
            this.i.setWebChromeClient(null);
            this.i.loadUrl("about:blank");
            this.i.clearCache(false);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("last_load_url", this.k);
    }

    public void w(WebView webView) {
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
}
