package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.MediaQueueItem;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzai extends zzbk {
    final /* synthetic */ MediaQueueItem[] zza;
    final /* synthetic */ JSONObject zzb;
    final /* synthetic */ RemoteMediaClient zzc;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzai(RemoteMediaClient remoteMediaClient, MediaQueueItem[] mediaQueueItemArr, JSONObject jSONObject) {
        super(remoteMediaClient, false);
        this.zzc = remoteMediaClient;
        this.zza = mediaQueueItemArr;
        this.zzb = jSONObject;
    }

    @Override // com.google.android.gms.cast.framework.media.zzbk
    protected final void zza() throws com.google.android.gms.cast.internal.zzan {
        this.zzc.zzd.zzA(zzb(), 0, -1L, this.zza, 0, null, null, this.zzb);
    }
}
