package defpackage;

import android.net.Uri;
import android.webkit.WebResourceRequest;
import java.util.Map;

/* JADX INFO: renamed from: d9, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0645d9 implements WebResourceRequest {
    public final C0426Xn a;

    public C0645d9(C0426Xn c0426Xn) {
        this.a = c0426Xn;
    }

    @Override // android.webkit.WebResourceRequest
    public String getMethod() {
        return this.a.e;
    }

    @Override // android.webkit.WebResourceRequest
    public Map getRequestHeaders() {
        return this.a.f;
    }

    @Override // android.webkit.WebResourceRequest
    public Uri getUrl() {
        return Uri.parse(this.a.a);
    }

    @Override // android.webkit.WebResourceRequest
    public boolean hasGesture() {
        return this.a.c;
    }

    @Override // android.webkit.WebResourceRequest
    public boolean isForMainFrame() {
        return this.a.b;
    }

    @Override // android.webkit.WebResourceRequest
    public boolean isRedirect() {
        return this.a.d;
    }
}
