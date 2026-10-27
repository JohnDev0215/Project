package defpackage;

import android.media.MediaDrm;
import org.chromium.media.MediaDrmBridge;

/* JADX INFO: renamed from: tH, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1539tH implements MediaDrm.OnExpirationUpdateListener {
    public final /* synthetic */ MediaDrmBridge a;

    public C1539tH(MediaDrmBridge mediaDrmBridge, RunnableC1155mH runnableC1155mH) {
        this.a = mediaDrmBridge;
    }

    @Override // android.media.MediaDrm.OnExpirationUpdateListener
    public void onExpirationUpdate(MediaDrm mediaDrm, byte[] bArr, long j) {
        AH ahB = MediaDrmBridge.b(this.a, bArr);
        MediaDrmBridge.e(this.a, ahB, new RunnableC1484sH(this, ahB, j));
    }
}
