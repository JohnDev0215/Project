package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.MediaQueueItem;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzah extends zzbk {
    final /* synthetic */ MediaQueueItem zza;
    final /* synthetic */ int zzb;
    final /* synthetic */ long zzc;
    final /* synthetic */ JSONObject zzd;
    final /* synthetic */ RemoteMediaClient zze;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzah(RemoteMediaClient remoteMediaClient, MediaQueueItem mediaQueueItem, int i, long j, JSONObject jSONObject) {
        super(remoteMediaClient, false);
        this.zze = remoteMediaClient;
        this.zza = mediaQueueItem;
        this.zzb = i;
        this.zzc = j;
        this.zzd = jSONObject;
    }

    @Override // com.google.android.gms.cast.framework.media.zzbk
    protected final void zza() throws com.google.android.gms.cast.internal.zzan {
        this.zze.zzd.zzw(zzb(), new MediaQueueItem[]{this.zza}, this.zzb, 0, 0, this.zzc, this.zzd);
    }
}
