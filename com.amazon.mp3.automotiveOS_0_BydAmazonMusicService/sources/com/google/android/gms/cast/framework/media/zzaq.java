package com.google.android.gms.cast.framework.media;

import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzaq extends zzbk {
    final /* synthetic */ int zza;
    final /* synthetic */ long zzb;
    final /* synthetic */ JSONObject zzc;
    final /* synthetic */ RemoteMediaClient zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzaq(RemoteMediaClient remoteMediaClient, int i, long j, JSONObject jSONObject) {
        super(remoteMediaClient, false);
        this.zzd = remoteMediaClient;
        this.zza = i;
        this.zzb = j;
        this.zzc = jSONObject;
    }

    @Override // com.google.android.gms.cast.framework.media.zzbk
    protected final void zza() throws com.google.android.gms.cast.internal.zzan {
        this.zzd.zzd.zzA(zzb(), this.zza, this.zzb, null, 0, null, null, this.zzc);
    }
}
