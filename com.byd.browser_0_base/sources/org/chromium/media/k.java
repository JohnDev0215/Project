package org.chromium.media;

import java.nio.ByteBuffer;
import org.chromium.base.Callback;
import org.chromium.media.MediaDrmBridge;
import org.chromium.media.MediaDrmStorageBridge;
import org.chromium.media.l;

/* JADX INFO: compiled from: MediaDrmSessionManager.java */
/* JADX INFO: loaded from: classes3.dex */
public final class k implements Callback<MediaDrmStorageBridge.PersistentInfo> {
    public final /* synthetic */ Callback e;
    public final /* synthetic */ l f;

    public k(l lVar, MediaDrmBridge.b bVar) {
        this.f = lVar;
        this.e = bVar;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(MediaDrmStorageBridge.PersistentInfo persistentInfo) {
        MediaDrmStorageBridge.PersistentInfo persistentInfo2 = persistentInfo;
        Callback callback = this.e;
        if (persistentInfo2 == null) {
            callback.onResult(null);
            return;
        }
        l.a aVar = new l.a(persistentInfo2.emeId(), null, persistentInfo2.keySetId());
        String strMimeType = persistentInfo2.mimeType();
        int iKeyType = persistentInfo2.keyType();
        if (iKeyType != 2 && iKeyType != 3) {
            iKeyType = 2;
        }
        this.f.a.put(ByteBuffer.wrap(persistentInfo2.emeId()), new l.b(aVar, strMimeType, iKeyType));
        callback.onResult(aVar);
    }
}
