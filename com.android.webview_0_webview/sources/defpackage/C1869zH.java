package defpackage;

import java.nio.ByteBuffer;
import org.chromium.base.Callback;
import org.chromium.media.MediaDrmStorageBridge$PersistentInfo;

/* JADX INFO: renamed from: zH, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1869zH implements Callback {
    public final /* synthetic */ Callback a;
    public final /* synthetic */ CH b;

    public C1869zH(CH ch, Callback callback) {
        this.b = ch;
        this.a = callback;
    }

    @Override // org.chromium.base.Callback
    public void a(Object obj) {
        MediaDrmStorageBridge$PersistentInfo mediaDrmStorageBridge$PersistentInfo = (MediaDrmStorageBridge$PersistentInfo) obj;
        if (mediaDrmStorageBridge$PersistentInfo == null) {
            this.a.a(null);
            return;
        }
        AH ah = new AH(mediaDrmStorageBridge$PersistentInfo.emeId(), null, mediaDrmStorageBridge$PersistentInfo.keySetId(), null);
        String strMimeType = mediaDrmStorageBridge$PersistentInfo.mimeType();
        int iKeyType = mediaDrmStorageBridge$PersistentInfo.keyType();
        if (iKeyType != 2 && iKeyType != 3) {
            iKeyType = 2;
        }
        BH bh = new BH(ah, strMimeType, iKeyType);
        this.b.a.put(ByteBuffer.wrap(mediaDrmStorageBridge$PersistentInfo.emeId()), bh);
        this.a.a(bh.a);
    }
}
