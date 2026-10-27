package defpackage;

import android.webkit.WebResourceError;

/* JADX INFO: renamed from: c9, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0589c9 extends WebResourceError {
    public final C0408Wn a;

    public C0589c9(C0408Wn c0408Wn) {
        this.a = c0408Wn;
    }

    @Override // android.webkit.WebResourceError
    public CharSequence getDescription() {
        return this.a.b;
    }

    @Override // android.webkit.WebResourceError
    public int getErrorCode() {
        return this.a.a;
    }
}
