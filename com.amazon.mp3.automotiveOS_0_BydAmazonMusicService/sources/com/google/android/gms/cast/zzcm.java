package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzcm extends zzdn {
    final /* synthetic */ long[] zza;
    final /* synthetic */ RemoteMediaPlayer zzb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzcm(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, long[] jArr) {
        super(remoteMediaPlayer, googleApiClient);
        this.zzb = remoteMediaPlayer;
        this.zza = jArr;
    }

    @Override // com.google.android.gms.cast.zzdn
    protected final void zza(com.google.android.gms.cast.internal.zzw zzwVar) throws com.google.android.gms.cast.internal.zzan {
        this.zzb.zzb.zzD(zzb(), this.zza);
    }
}
