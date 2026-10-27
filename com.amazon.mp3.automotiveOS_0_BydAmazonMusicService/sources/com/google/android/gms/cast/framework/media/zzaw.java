package com.google.android.gms.cast.framework.media;

import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzaw extends zzbk {
    final /* synthetic */ String zza;
    final /* synthetic */ RemoteMediaClient zzb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzaw(RemoteMediaClient remoteMediaClient, boolean z, String str, List list) {
        super(remoteMediaClient, true);
        this.zzb = remoteMediaClient;
        this.zza = str;
    }

    @Override // com.google.android.gms.cast.framework.media.zzbk
    protected final void zza() throws com.google.android.gms.cast.internal.zzan {
        this.zzb.zzd.zzs(this.zza, null);
    }
}
