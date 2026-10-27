package com.google.android.gms.cast;

import com.google.android.gms.cast.MediaSeekOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzdf extends zzdn {
    final /* synthetic */ long zza;
    final /* synthetic */ int zzb;
    final /* synthetic */ JSONObject zzc;
    final /* synthetic */ RemoteMediaPlayer zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzdf(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, long j, int i, JSONObject jSONObject) {
        super(remoteMediaPlayer, googleApiClient);
        this.zzd = remoteMediaPlayer;
        this.zza = j;
        this.zzb = i;
        this.zzc = jSONObject;
    }

    @Override // com.google.android.gms.cast.zzdn
    protected final void zza(com.google.android.gms.cast.internal.zzw zzwVar) throws com.google.android.gms.cast.internal.zzan {
        com.google.android.gms.cast.internal.zzap zzapVar = this.zzd.zzb;
        com.google.android.gms.cast.internal.zzar zzarVarZzb = zzb();
        MediaSeekOptions.Builder builder = new MediaSeekOptions.Builder();
        builder.setPosition(this.zza);
        builder.setResumeState(this.zzb);
        builder.setCustomData(this.zzc);
        zzapVar.zzC(zzarVarZzb, builder.build());
    }
}
