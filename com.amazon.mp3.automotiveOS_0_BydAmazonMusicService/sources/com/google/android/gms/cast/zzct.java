package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzct extends zzdn {
    final /* synthetic */ int[] zza;
    final /* synthetic */ int zzb;
    final /* synthetic */ JSONObject zzc;
    final /* synthetic */ RemoteMediaPlayer zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzct(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, int[] iArr, int i, JSONObject jSONObject) {
        super(remoteMediaPlayer, googleApiClient);
        this.zzd = remoteMediaPlayer;
        this.zza = iArr;
        this.zzb = i;
        this.zzc = jSONObject;
    }

    @Override // com.google.android.gms.cast.zzdn
    protected final void zza(com.google.android.gms.cast.internal.zzw zzwVar) throws com.google.android.gms.cast.internal.zzan {
        this.zzd.zzb.zzz(zzb(), this.zza, this.zzb, this.zzc);
    }
}
