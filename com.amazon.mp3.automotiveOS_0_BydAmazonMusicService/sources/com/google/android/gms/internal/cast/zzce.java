package com.google.android.gms.internal.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzce extends zzcj {
    final /* synthetic */ String zza;
    final /* synthetic */ zzcl zzb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzce(zzcl zzclVar, GoogleApiClient googleApiClient, String str) {
        super(zzclVar, googleApiClient);
        this.zzb = zzclVar;
        this.zza = str;
    }

    @Override // com.google.android.gms.internal.cast.zzcj, com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        doExecute((zzco) anyClient);
    }

    @Override // com.google.android.gms.internal.cast.zzcj
    /* JADX INFO: renamed from: zza */
    public final void doExecute(zzco zzcoVar) throws RemoteException {
        zzcoVar.zzr(new zzch(this, zzcoVar), this.zzb.zzd, this.zza);
    }
}
