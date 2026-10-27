package com.amazon.identity.auth.device;

import android.webkit.WebView;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class e5 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ WebView f296a;
    public final /* synthetic */ String b;

    public e5(d5 d5Var, WebView webView, String str) {
        this.f296a = webView;
        this.b = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f296a.loadUrl(this.b);
    }
}
