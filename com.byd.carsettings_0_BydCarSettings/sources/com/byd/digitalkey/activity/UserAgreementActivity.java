package com.byd.digitalkey.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import b.b.a.f.f;
import b.b.a.h.i;
import com.byd.base.view.BaseTitleActivity;
import com.byd.digitalkey.R$drawable;
import com.byd.digitalkey.R$id;
import com.byd.digitalkey.R$layout;
import com.byd.digitalkey.R$string;

/* JADX INFO: loaded from: classes2.dex */
public class UserAgreementActivity extends BaseTitleActivity {
    public WebView i;
    public CheckBox j;
    public TextView k;

    public class a implements CompoundButton.OnCheckedChangeListener {
        public a() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            UserAgreementActivity.this.k.setEnabled(z);
            i.b(UserAgreementActivity.this.getApplication(), "nfc_user_pro_has_read_key", z ? "nfc_user_pro_has_read" : "");
        }
    }

    public class b implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f3538a;

        public b(int i) {
            this.f3538a = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.f3538a == 1) {
                UserAgreementActivity.this.setResult(2);
            } else {
                UserAgreementActivity.this.setResult(3);
            }
            UserAgreementActivity.this.finish();
        }
    }

    public class c extends WebViewClient {
        public c() {
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            String string = webResourceRequest.getUrl().toString();
            f.b("UserAgreementActivity", "url:%s", string);
            b.b.a.e.b bVarC = b.b.a.e.c.b().c();
            if (string.contains("privacy")) {
                bVarC.a(UserAgreementActivity.this);
                return true;
            }
            if (!string.contains("agreement")) {
                return super.shouldOverrideUrlLoading(webView, webResourceRequest);
            }
            bVarC.b(UserAgreementActivity.this);
            return true;
        }
    }

    @Override // com.byd.base.view.BaseTitleActivity
    public int f() {
        return R$string.nfc_user_agreement;
    }

    @Override // com.byd.base.view.BaseTitleActivity
    public void n() {
        startActivity(new Intent(this, (Class<?>) UserHelpActivity.class));
    }

    @Override // com.byd.base.view.BaseTitleActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R$layout.activity_user_agreement);
        WebView webView = (WebView) findViewById(R$id.user_agreement_wv);
        this.i = webView;
        x(webView);
        this.j = (CheckBox) findViewById(R$id.ua_checkbox);
        this.k = (TextView) findViewById(R$id.ua_add_digital_key_btn);
        int intExtra = getIntent().getIntExtra("from_source_page", 1);
        int iB = a.a.a.a(this).b();
        String string = getString(R$string.nfc_brand_byd);
        if (iB == 1) {
            string = getString(R$string.nfc_brand_denz);
        }
        this.j.setText(getString(R$string.nfc_user_agreement_has_read_2, new Object[]{string}));
        this.j.setOnCheckedChangeListener(new a());
        this.k.setOnClickListener(new b(intExtra));
        this.i.loadUrl(b.b.j.h.a.a(this));
        w();
    }

    @Override // com.byd.base.view.BaseTitleActivity
    public void p() {
        startActivity(new Intent(this, (Class<?>) DigitalKeySettingActivity.class));
    }

    public final void w() {
        r(0, 0, 8);
        u(0, 0, 8);
        q(R$drawable.icon_dark_public_help);
        t(R$drawable.icon_dark_navigation_setting);
    }

    public void x(WebView webView) {
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
        webView.setWebViewClient(new c());
    }
}
