package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BasePendingResult;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Iterator;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@VisibleForTesting
abstract class zzbk extends BasePendingResult {
    private com.google.android.gms.cast.internal.zzar zza;
    private final boolean zzb;
    final /* synthetic */ RemoteMediaClient zzg;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzbk(RemoteMediaClient remoteMediaClient, boolean z) {
        super((GoogleApiClient) null);
        this.zzg = remoteMediaClient;
        this.zzb = z;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ Result createFailedResult(Status status) {
        return new zzbj(this, status);
    }

    abstract void zza() throws com.google.android.gms.cast.internal.zzan;

    final com.google.android.gms.cast.internal.zzar zzb() {
        if (this.zza == null) {
            this.zza = new zzbi(this);
        }
        return this.zza;
    }

    public final void zzc() {
        if (!this.zzb) {
            Iterator it = this.zzg.zzh.iterator();
            while (it.hasNext()) {
                ((RemoteMediaClient.Listener) it.next()).onSendingRemoteMediaRequest();
            }
            Iterator it2 = this.zzg.zza.iterator();
            while (it2.hasNext()) {
                ((RemoteMediaClient.Callback) it2.next()).onSendingRemoteMediaRequest();
            }
        }
        try {
            synchronized (this.zzg.zzb) {
                zza();
            }
        } catch (com.google.android.gms.cast.internal.zzan unused) {
            setResult(new zzbj(this, new Status(2100)));
        }
    }
}
