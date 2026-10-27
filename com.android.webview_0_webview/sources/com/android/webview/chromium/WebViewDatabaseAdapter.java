package com.android.webview.chromium;

import J.N;
import android.os.Build;
import android.webkit.WebViewDatabase;
import defpackage.C0464Zp;
import defpackage.CallableC0270Pb;
import defpackage.CallableC0324Sb;
import defpackage.CallableC0342Tb;
import defpackage.RunnableC0288Qb;
import defpackage.RunnableC0306Rb;
import defpackage.RunnableC0360Ub;
import java.util.concurrent.FutureTask;
import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class WebViewDatabaseAdapter extends WebViewDatabase {
    public final WebViewChromiumFactoryProvider a;
    public final C0464Zp b;

    public WebViewDatabaseAdapter(WebViewChromiumFactoryProvider webViewChromiumFactoryProvider, C0464Zp c0464Zp) {
        this.a = webViewChromiumFactoryProvider;
        this.b = c0464Zp;
    }

    public static boolean a() {
        return !ThreadUtils.j();
    }

    @Override // android.webkit.WebViewDatabase
    public void clearFormData() {
        if (Build.VERSION.SDK_INT >= 26) {
            return;
        }
        if (!a()) {
            N.MonDM6EC();
            return;
        }
        WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.a;
        webViewChromiumFactoryProvider.a.a(new RunnableC0360Ub(this));
    }

    @Override // android.webkit.WebViewDatabase
    public void clearHttpAuthUsernamePassword() {
        if (a()) {
            WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.a;
            webViewChromiumFactoryProvider.a.a(new RunnableC0288Qb(this));
        } else {
            C0464Zp c0464Zp = this.b;
            if (c0464Zp.e()) {
                c0464Zp.a.delete("httpauth", null, null);
            }
        }
    }

    @Override // android.webkit.WebViewDatabase
    public void clearUsernamePassword() {
    }

    @Override // android.webkit.WebViewDatabase
    public String[] getHttpAuthUsernamePassword(String str, String str2) {
        if (!a()) {
            return this.b.a(str, str2);
        }
        return (String[]) this.a.a.c(new FutureTask(new CallableC0324Sb(this, str, str2)));
    }

    @Override // android.webkit.WebViewDatabase
    public boolean hasFormData() {
        if (Build.VERSION.SDK_INT >= 26) {
            return false;
        }
        if (!a()) {
            return N.MxzPtFuv();
        }
        return ((Boolean) this.a.a.c(new FutureTask(new CallableC0342Tb(this)))).booleanValue();
    }

    @Override // android.webkit.WebViewDatabase
    public boolean hasHttpAuthUsernamePassword() {
        if (!a()) {
            return this.b.b();
        }
        return ((Boolean) this.a.a.c(new FutureTask(new CallableC0270Pb(this)))).booleanValue();
    }

    @Override // android.webkit.WebViewDatabase
    public boolean hasUsernamePassword() {
        return false;
    }

    @Override // android.webkit.WebViewDatabase
    public void setHttpAuthUsernamePassword(String str, String str2, String str3, String str4) {
        if (!a()) {
            this.b.d(str, str2, str3, str4);
            return;
        }
        WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.a;
        webViewChromiumFactoryProvider.a.a(new RunnableC0306Rb(this, str, str2, str3, str4));
    }
}
