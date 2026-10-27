package com.google.android.gms.cast.framework.media;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzau extends zzbk {
    final /* synthetic */ int zza;
    final /* synthetic */ int zzb;
    final /* synthetic */ int zzc;
    final /* synthetic */ RemoteMediaClient zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzau(RemoteMediaClient remoteMediaClient, boolean z, int i, int i2, int i3) {
        super(remoteMediaClient, true);
        this.zzd = remoteMediaClient;
        this.zza = i;
        this.zzb = i2;
        this.zzc = i3;
    }

    @Override // com.google.android.gms.cast.framework.media.zzbk
    protected final void zza() throws com.google.android.gms.cast.internal.zzan {
        this.zzd.zzd.zzt(zzb(), this.zza, this.zzb, this.zzc);
    }
}
