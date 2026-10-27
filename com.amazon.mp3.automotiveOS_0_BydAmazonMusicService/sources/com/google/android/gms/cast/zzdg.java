package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzdg extends zzdn {
    final /* synthetic */ double zza;
    final /* synthetic */ JSONObject zzb;
    final /* synthetic */ RemoteMediaPlayer zzc;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzdg(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, double d, JSONObject jSONObject) {
        super(remoteMediaPlayer, googleApiClient);
        this.zzc = remoteMediaPlayer;
        this.zza = d;
        this.zzb = jSONObject;
    }

    @Override // com.google.android.gms.cast.zzdn
    protected final void zza(com.google.android.gms.cast.internal.zzw zzwVar) throws com.google.android.gms.cast.internal.zzan {
        this.zzc.zzb.zzG(zzb(), this.zza, this.zzb);
    }
}
