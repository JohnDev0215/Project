package defpackage;

import org.chromium.base.Callback;
import org.chromium.media.MediaDrmBridge;

/* JADX INFO: renamed from: oH, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1265oH implements Callback {
    public final /* synthetic */ long a;
    public final /* synthetic */ MediaDrmBridge b;

    public C1265oH(MediaDrmBridge mediaDrmBridge, long j) {
        this.b = mediaDrmBridge;
        this.a = j;
    }

    @Override // org.chromium.base.Callback
    public void a(Object obj) {
        if (!((Boolean) obj).booleanValue()) {
            Ts.f("media", "Failed to clear persistent storage for non-exist license", new Object[0]);
        }
        MediaDrmBridge.i(this.b, this.a);
    }
}
