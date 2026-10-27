package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzf extends com.google.android.gms.cast.internal.zzab {
    final /* synthetic */ String zza;
    final /* synthetic */ String zzb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzf(zzm zzmVar, GoogleApiClient googleApiClient, String str, String str2) {
        super(googleApiClient);
        this.zza = str;
        this.zzb = str2;
    }

    @Override // com.google.android.gms.cast.internal.zzab, com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        doExecute((com.google.android.gms.cast.internal.zzw) anyClient);
    }

    @Override // com.google.android.gms.cast.internal.zzab
    /* JADX INFO: renamed from: zza */
    public final void doExecute(com.google.android.gms.cast.internal.zzw zzwVar) throws RemoteException {
        try {
            zzwVar.zzQ(this.zza, this.zzb, this);
        } catch (IllegalArgumentException | IllegalStateException unused) {
            zzc(CastStatusCodes.INVALID_REQUEST);
        }
    }
}
