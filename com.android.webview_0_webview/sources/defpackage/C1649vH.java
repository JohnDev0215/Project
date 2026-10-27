package defpackage;

import android.media.MediaDrm;
import java.util.List;
import org.chromium.media.MediaDrmBridge;

/* JADX INFO: renamed from: vH, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1649vH implements MediaDrm.OnKeyStatusChangeListener {
    public final /* synthetic */ MediaDrmBridge a;

    public C1649vH(MediaDrmBridge mediaDrmBridge, RunnableC1155mH runnableC1155mH) {
        this.a = mediaDrmBridge;
    }

    @Override // android.media.MediaDrm.OnKeyStatusChangeListener
    public void onKeyStatusChange(MediaDrm mediaDrm, byte[] bArr, List list, boolean z) {
        AH ahB = MediaDrmBridge.b(this.a, bArr);
        MediaDrmBridge.e(this.a, ahB, new RunnableC1594uH(this, ahB, z, list, this.a.g.b(ahB).c == 3));
    }
}
