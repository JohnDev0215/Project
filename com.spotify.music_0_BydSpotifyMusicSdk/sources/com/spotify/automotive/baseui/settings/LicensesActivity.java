package com.spotify.automotive.baseui.settings;

import android.os.Bundle;
import android.webkit.WebView;
import androidx.appcompat.widget.Toolbar;
import com.spotify.music.R;
import p.h91;
import p.i91;
import p.s;
import p.t7;

/* JADX INFO: loaded from: classes.dex */
public final class LicensesActivity extends t7 {
    public static final /* synthetic */ int G = 0;

    @Override // p.zw0, androidx.activity.a, p.wv, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_licenses);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.third_party_licenses);
        toolbar.setNavigationOnClickListener(new s(this, 1));
        WebView webView = (WebView) findViewById(R.id.webview);
        webView.loadUrl("file:///android_asset/licenses.xhtml");
        webView.getSettings().setTextZoom(getResources().getInteger(R.integer.licenses_text_zoom));
        webView.setWebViewClient(new i91());
        webView.setLongClickable(true);
        webView.setOnLongClickListener(new h91());
    }
}
