package defpackage;

import J.N;
import android.webkit.ValueCallback;
import org.chromium.weblayer_private.DownloadCallbackProxy;

/* JADX INFO: renamed from: mQ, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1164mQ implements ValueCallback {
    public final /* synthetic */ long a;
    public final /* synthetic */ DownloadCallbackProxy b;

    public C1164mQ(DownloadCallbackProxy downloadCallbackProxy, long j) {
        this.b = downloadCallbackProxy;
        this.a = j;
    }

    @Override // android.webkit.ValueCallback
    public void onReceiveValue(Object obj) {
        Boolean bool = (Boolean) obj;
        if (this.b.a == 0) {
            throw new IllegalStateException("Called after destroy()");
        }
        N.MYOOvdZV(this.a, bool.booleanValue());
    }
}
