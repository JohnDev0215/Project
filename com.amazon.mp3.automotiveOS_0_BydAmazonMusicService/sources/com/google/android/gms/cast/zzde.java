package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzde extends zzdn {
    final /* synthetic */ JSONObject zza;
    final /* synthetic */ RemoteMediaPlayer zzb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzde(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, JSONObject jSONObject) {
        super(remoteMediaPlayer, googleApiClient);
        this.zzb = remoteMediaPlayer;
        this.zza = jSONObject;
    }

    @Override // com.google.android.gms.cast.zzdn
    protected final void zza(com.google.android.gms.cast.internal.zzw zzwVar) throws com.google.android.gms.cast.internal.zzan {
        this.zzb.zzb.zzr(zzb(), this.zza);
    }
}
