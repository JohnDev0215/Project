package org.chromium.media;

import android.util.Log;
import java.util.UUID;
import org.chromium.base.Callback;
import org.chromium.media.l;

/* JADX INFO: compiled from: MediaDrmBridge.java */
/* JADX INFO: loaded from: classes3.dex */
public final class i implements Callback<Boolean> {
    public final /* synthetic */ long e;
    public final /* synthetic */ MediaDrmBridge f;

    public i(MediaDrmBridge mediaDrmBridge, long j) {
        this.f = mediaDrmBridge;
        this.e = j;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Boolean bool) {
        if (!bool.booleanValue()) {
            Log.w("cr_media", "Failed to clear persistent storage for non-exist license");
        }
        UUID uuid = MediaDrmBridge.l;
        MediaDrmBridge mediaDrmBridge = this.f;
        mediaDrmBridge.getClass();
        byte[] bArr = new byte[0];
        mediaDrmBridge.j(this.e, new l.a(bArr, bArr, null));
    }
}
