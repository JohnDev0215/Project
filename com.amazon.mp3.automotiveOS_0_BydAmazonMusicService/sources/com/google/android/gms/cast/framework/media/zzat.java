package com.google.android.gms.cast.framework.media;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzat extends zzbk {
    final /* synthetic */ int[] zza;
    final /* synthetic */ RemoteMediaClient zzb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzat(RemoteMediaClient remoteMediaClient, boolean z, int[] iArr) {
        super(remoteMediaClient, true);
        this.zzb = remoteMediaClient;
        this.zza = iArr;
    }

    @Override // com.google.android.gms.cast.framework.media.zzbk
    protected final void zza() throws com.google.android.gms.cast.internal.zzan {
        this.zzb.zzd.zzv(zzb(), this.zza);
    }
}
