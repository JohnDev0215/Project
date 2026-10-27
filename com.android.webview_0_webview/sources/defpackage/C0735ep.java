package defpackage;

import J.N;
import android.content.Context;
import org.chromium.android_webview.AwBrowserContext;
import org.chromium.android_webview.AwContentsBackgroundThreadClient;
import org.chromium.android_webview.AwContentsIoThreadClient;

/* JADX INFO: renamed from: ep, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0735ep {
    public AbstractC0512ap a;
    public AwContentsIoThreadClient b;
    public AwContentsBackgroundThreadClient c;
    public C0791fp d;
    public AwBrowserContext e;

    public C0735ep(Context context, AwBrowserContext awBrowserContext) {
        this.d = new C0791fp(context);
        this.e = awBrowserContext;
    }

    public void a(AbstractC0512ap abstractC0512ap) {
        this.a = abstractC0512ap;
        this.c = new C0624cp(this, null);
        C0679dp c0679dp = new C0679dp(this, null);
        this.b = c0679dp;
        N.MhYNFMfv(c0679dp, this.e);
    }
}
