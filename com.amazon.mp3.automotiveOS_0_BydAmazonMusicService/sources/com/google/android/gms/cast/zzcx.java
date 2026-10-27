package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzcx extends zzdn {
    final /* synthetic */ int zza;
    final /* synthetic */ JSONObject zzb;
    final /* synthetic */ RemoteMediaPlayer zzc;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzcx(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, int i, JSONObject jSONObject) {
        super(remoteMediaPlayer, googleApiClient);
        this.zzc = remoteMediaPlayer;
        this.zza = i;
        this.zzb = jSONObject;
    }

    @Override // com.google.android.gms.cast.zzdn
    protected final void zza(com.google.android.gms.cast.internal.zzw zzwVar) throws com.google.android.gms.cast.internal.zzan {
        this.zzc.zzb.zzA(zzb(), 0, -1L, null, 0, null, Integer.valueOf(this.zza), this.zzb);
    }
}
