package defpackage;

import org.chromium.base.Callback;
import org.chromium.media.MediaDrmBridge;

/* JADX INFO: renamed from: qH, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1375qH implements Callback {
    public final /* synthetic */ MediaDrmBridge a;

    public C1375qH(MediaDrmBridge mediaDrmBridge) {
        this.a = mediaDrmBridge;
    }

    @Override // org.chromium.base.Callback
    public void a(Object obj) {
        if (((Boolean) obj).booleanValue()) {
            this.a.n();
        } else {
            Ts.a("media", "Failed to initialize storage for origin", new Object[0]);
            this.a.z();
        }
    }
}
