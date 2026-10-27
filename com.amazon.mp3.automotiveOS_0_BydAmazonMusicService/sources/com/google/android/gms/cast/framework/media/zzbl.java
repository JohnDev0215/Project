package com.google.android.gms.cast.framework.media;

import androidx.annotation.Nullable;
import com.google.android.gms.cast.MediaError;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.api.Status;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzbl implements RemoteMediaClient.MediaChannelResult {
    private final Status zza;

    @Nullable
    private final JSONObject zzb;

    @Nullable
    private final MediaError zzc;

    zzbl(Status status, @Nullable JSONObject jSONObject, @Nullable MediaError mediaError) {
        this.zza = status;
        this.zzb = jSONObject;
        this.zzc = mediaError;
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.MediaChannelResult
    @Nullable
    public final JSONObject getCustomData() {
        return this.zzb;
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.MediaChannelResult
    @Nullable
    public final MediaError getMediaError() {
        return this.zzc;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zza;
    }
}
