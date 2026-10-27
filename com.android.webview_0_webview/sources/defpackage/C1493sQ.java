package defpackage;

import J.N;
import android.webkit.ValueCallback;
import org.chromium.weblayer_private.FullscreenCallbackProxy;

/* JADX INFO: renamed from: sQ, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1493sQ implements ValueCallback {
    public final /* synthetic */ FullscreenCallbackProxy a;

    public C1493sQ(FullscreenCallbackProxy fullscreenCallbackProxy) {
        this.a = fullscreenCallbackProxy;
    }

    @Override // android.webkit.ValueCallback
    public void onReceiveValue(Object obj) {
        FullscreenCallbackProxy fullscreenCallbackProxy = this.a;
        long j = fullscreenCallbackProxy.a;
        if (j == 0) {
            throw new IllegalStateException("Called after destroy()");
        }
        N.MSOA10zg(j, fullscreenCallbackProxy);
    }
}
