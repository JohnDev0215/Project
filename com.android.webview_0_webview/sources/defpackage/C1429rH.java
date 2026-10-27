package defpackage;

import android.media.MediaDrm;
import android.media.NotProvisionedException;
import android.os.Build;
import java.util.ArrayList;
import org.chromium.media.MediaDrmBridge;

/* JADX INFO: renamed from: rH, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1429rH implements MediaDrm.OnEventListener {
    public final /* synthetic */ MediaDrmBridge a;

    public C1429rH(MediaDrmBridge mediaDrmBridge, RunnableC1155mH runnableC1155mH) {
        this.a = mediaDrmBridge;
    }

    @Override // android.media.MediaDrm.OnEventListener
    public void onEvent(MediaDrm mediaDrm, byte[] bArr, int i, int i2, byte[] bArr2) {
        int i3 = Build.VERSION.SDK_INT;
        if (bArr == null) {
            Ts.a("media", "EventListener: No session for event %d.", Integer.valueOf(i));
            return;
        }
        AH ahB = MediaDrmBridge.b(this.a, bArr);
        if (ahB == null) {
            Ts.a("media", "EventListener: Invalid session %s", AH.d(bArr));
            return;
        }
        BH bhB = this.a.g.b(ahB);
        if (i != 2) {
            if (i != 3) {
                if (i != 4) {
                    Ts.a("media", Z7.a("Invalid DRM event ", i), new Object[0]);
                    return;
                }
                return;
            } else {
                if (i3 < 23) {
                    this.a.v(ahB, ((ArrayList) MediaDrmBridge.c(1)).toArray(), false, bhB.c == 3);
                    return;
                }
                return;
            }
        }
        try {
            MediaDrm.KeyRequest keyRequestO = this.a.o(ahB, bArr2, bhB.b, bhB.c, null);
            if (keyRequestO != null) {
                this.a.w(ahB, keyRequestO);
                return;
            }
            if (i3 < 23) {
                this.a.v(ahB, ((ArrayList) MediaDrmBridge.c(4)).toArray(), false, false);
            }
            Ts.a("media", "EventListener: getKeyRequest failed.", new Object[0]);
        } catch (NotProvisionedException e) {
            Ts.a("media", "Device not provisioned", e);
        }
    }
}
