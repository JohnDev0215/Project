package defpackage;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.View;
import android.webkit.WebViewDelegate;

/* JADX INFO: renamed from: Zb, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0450Zb implements InterfaceC0555bc {
    public WebViewDelegate a;

    public C0450Zb(WebViewDelegate webViewDelegate) {
        this.a = webViewDelegate;
    }

    @Override // defpackage.InterfaceC0555bc
    public void a(Canvas canvas, long j, Runnable runnable) {
        C1090l8.a(this.a, canvas, j, runnable);
    }

    @Override // defpackage.InterfaceC0555bc
    public void b(View view, long j) {
        this.a.detachDrawGlFunctor(view, j);
    }

    @Override // defpackage.InterfaceC0555bc
    public int c(Resources resources, String str) {
        return this.a.getPackageId(resources, str);
    }

    @Override // defpackage.InterfaceC0250Nr
    public void d(Canvas canvas, int i) {
        this.a.drawWebViewFunctor(canvas, i);
    }

    @Override // defpackage.InterfaceC0555bc
    public void e(Canvas canvas, long j) {
        this.a.callDrawGlFunction(canvas, j);
    }

    @Override // defpackage.InterfaceC0555bc
    public void f(View view, long j, boolean z) {
        this.a.invokeDrawGlFunctor(view, j, z);
    }

    @Override // defpackage.InterfaceC0555bc
    public String g() {
        return C1311p8.b(this.a);
    }

    @Override // defpackage.InterfaceC0555bc
    public void h(Context context) {
        this.a.addWebViewAssetPath(new C0432Yb(this, context));
    }

    @Override // defpackage.InterfaceC0555bc
    public void i(InterfaceC0499ac interfaceC0499ac) {
        this.a.setOnTraceEnabledChangeListener(new C0414Xb(this, interfaceC0499ac));
    }

    @Override // defpackage.InterfaceC0555bc
    public boolean isMultiProcessEnabled() {
        return C1201n8.a(this.a);
    }

    @Override // defpackage.InterfaceC0555bc
    public boolean j() {
        return this.a.isTraceTagEnabled();
    }

    @Override // defpackage.InterfaceC0555bc
    public String k(Context context, int i) {
        return this.a.getErrorString(context, i);
    }

    @Override // defpackage.InterfaceC0555bc
    public Application l() {
        return this.a.getApplication();
    }

    @Override // defpackage.InterfaceC0555bc
    public boolean m(View view) {
        return this.a.canInvokeDrawGlFunctor(view);
    }
}
