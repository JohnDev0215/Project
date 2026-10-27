package org.chromium.media;

import android.util.Log;
import java.util.UUID;
import org.chromium.base.Callback;

/* JADX INFO: compiled from: MediaDrmBridge.java */
/* JADX INFO: loaded from: classes3.dex */
public final class j implements Callback<Boolean> {
    public final /* synthetic */ MediaDrmBridge e;

    public j(MediaDrmBridge mediaDrmBridge) {
        this.e = mediaDrmBridge;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Boolean bool) {
        boolean zBooleanValue = bool.booleanValue();
        MediaDrmBridge mediaDrmBridge = this.e;
        if (zBooleanValue) {
            UUID uuid = MediaDrmBridge.l;
            mediaDrmBridge.d();
        } else {
            Log.e("cr_media", "Failed to initialize storage for origin");
            UUID uuid2 = MediaDrmBridge.l;
            mediaDrmBridge.n();
        }
    }
}
