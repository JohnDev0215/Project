package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.MediaSeekOptions;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzba extends zzbk {
    final /* synthetic */ MediaSeekOptions zza;
    final /* synthetic */ RemoteMediaClient zzb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzba(RemoteMediaClient remoteMediaClient, MediaSeekOptions mediaSeekOptions) {
        super(remoteMediaClient, false);
        this.zzb = remoteMediaClient;
        this.zza = mediaSeekOptions;
    }

    @Override // com.google.android.gms.cast.framework.media.zzbk
    protected final void zza() throws com.google.android.gms.cast.internal.zzan {
        this.zzb.zzd.zzC(zzb(), this.zza);
    }
}
