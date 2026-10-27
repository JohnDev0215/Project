package com.google.android.gms.cast;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzdj implements ResultCallback {
    final /* synthetic */ zzdk zza;
    private final long zzb;

    zzdj(zzdk zzdkVar, long j) {
        this.zza = zzdkVar;
        this.zzb = j;
    }

    @Override // com.google.android.gms.common.api.ResultCallback
    public final /* bridge */ /* synthetic */ void onResult(Result result) {
        Status status = (Status) result;
        if (status.isSuccess()) {
            return;
        }
        this.zza.zza.zzb.zzR(this.zzb, status.getStatusCode());
    }
}
