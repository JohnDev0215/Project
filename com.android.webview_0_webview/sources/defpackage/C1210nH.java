package defpackage;

import org.chromium.base.Callback;
import org.chromium.media.MediaDrmBridge;

/* JADX INFO: renamed from: nH, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1210nH implements Callback {
    public final /* synthetic */ long a;
    public final /* synthetic */ MediaDrmBridge b;

    public C1210nH(MediaDrmBridge mediaDrmBridge, long j) {
        this.b = mediaDrmBridge;
        this.a = j;
    }

    @Override // org.chromium.base.Callback
    public void a(Object obj) {
        AH ah = (AH) obj;
        if (ah == null) {
            MediaDrmBridge.i(this.b, this.a);
        } else {
            MediaDrmBridge.j(this.b, ah, this.a);
        }
    }
}
