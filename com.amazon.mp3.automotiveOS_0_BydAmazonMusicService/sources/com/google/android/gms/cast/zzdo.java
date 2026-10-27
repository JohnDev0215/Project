package com.google.android.gms.cast;

import androidx.annotation.Nullable;
import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.api.Status;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzdo implements RemoteMediaPlayer.MediaChannelResult {
    private final Status zza;

    @Nullable
    private final JSONObject zzb;

    zzdo(Status status, @Nullable JSONObject jSONObject) {
        this.zza = status;
        this.zzb = jSONObject;
    }

    @Override // com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult
    @Nullable
    public final JSONObject getCustomData() {
        return this.zzb;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zza;
    }
}
