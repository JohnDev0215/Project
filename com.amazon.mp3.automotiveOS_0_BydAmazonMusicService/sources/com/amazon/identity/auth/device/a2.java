package com.amazon.identity.auth.device;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class a2 extends WebChromeClient {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ AuthPortalUIActivity f167a;

    public a2(AuthPortalUIActivity authPortalUIActivity) {
        this.f167a = authPortalUIActivity;
    }

    @Override // android.webkit.WebChromeClient
    public void onProgressChanged(WebView webView, int i) {
        AuthPortalUIActivity authPortalUIActivity = this.f167a;
        if (authPortalUIActivity.x) {
            authPortalUIActivity.a(i);
        }
    }

    @Override // android.webkit.WebChromeClient
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
        Log.i(ga.a(AuthPortalUIActivity.Q), "AuthPortalUIActivity onShowFileChooser()");
        AuthPortalUIActivity authPortalUIActivity = this.f167a;
        ValueCallback<Uri[]> valueCallback2 = authPortalUIActivity.H;
        if (valueCallback2 != null) {
            valueCallback2.onReceiveValue(null);
        }
        authPortalUIActivity.H = valueCallback;
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("*/*");
        authPortalUIActivity.startActivityForResult(Intent.createChooser(intent, "File Browser"), 1);
        return true;
    }
}
