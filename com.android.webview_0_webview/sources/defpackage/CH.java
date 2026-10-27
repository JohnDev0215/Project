package defpackage;

import J.N;
import java.nio.ByteBuffer;
import java.util.HashMap;
import org.chromium.base.Callback;
import org.chromium.media.MediaDrmStorageBridge$PersistentInfo;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class CH {
    public HashMap a = new HashMap();
    public HashMap b = new HashMap();
    public DH c;

    public CH(DH dh) {
        this.c = dh;
    }

    public void a(AH ah, Callback callback) {
        ah.c = null;
        DH dh = this.c;
        byte[] bArr = ah.a;
        if (dh.a()) {
            N.MYa_y6Dg(dh.a, dh, bArr, callback);
        } else {
            callback.a(Boolean.TRUE);
        }
    }

    public BH b(AH ah) {
        return (BH) this.a.get(ByteBuffer.wrap(ah.a));
    }

    public AH c(byte[] bArr) {
        BH bh = (BH) this.a.get(ByteBuffer.wrap(bArr));
        if (bh == null) {
            return null;
        }
        return bh.a;
    }

    public void d(AH ah, byte[] bArr, Callback callback) {
        ah.c = bArr;
        DH dh = this.c;
        BH bhB = b(ah);
        AH ah2 = bhB.a;
        MediaDrmStorageBridge$PersistentInfo mediaDrmStorageBridge$PersistentInfo = new MediaDrmStorageBridge$PersistentInfo(ah2.a, ah2.c, bhB.b, bhB.c);
        if (dh.a()) {
            N.MeALR1v2(dh.a, dh, mediaDrmStorageBridge$PersistentInfo, callback);
        } else {
            callback.a(Boolean.FALSE);
        }
    }
}
