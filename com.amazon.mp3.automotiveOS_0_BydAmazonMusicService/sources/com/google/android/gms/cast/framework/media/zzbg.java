package com.google.android.gms.cast.framework.media;

import androidx.annotation.Nullable;
import com.google.android.gms.cast.MediaError;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.api.Status;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzbg implements RemoteMediaClient.MediaChannelResult {
    final /* synthetic */ Status zza;

    zzbg(zzbh zzbhVar, Status status) {
        this.zza = status;
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.MediaChannelResult
    @Nullable
    public final JSONObject getCustomData() {
        return null;
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.MediaChannelResult
    @Nullable
    public final MediaError getMediaError() {
        return null;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zza;
    }
}
