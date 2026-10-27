package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzdi extends zzdn {
    final /* synthetic */ RemoteMediaPlayer zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzdi(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient) {
        super(remoteMediaPlayer, googleApiClient);
        this.zza = remoteMediaPlayer;
    }

    @Override // com.google.android.gms.cast.zzdn
    protected final void zza(com.google.android.gms.cast.internal.zzw zzwVar) {
        this.zza.zzb.zzB(zzb());
    }
}
