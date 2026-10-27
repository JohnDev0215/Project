package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzh extends zzp {
    final /* synthetic */ String zza;
    final /* synthetic */ LaunchOptions zzb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzh(zzm zzmVar, GoogleApiClient googleApiClient, String str, LaunchOptions launchOptions) {
        super(googleApiClient);
        this.zza = str;
        this.zzb = launchOptions;
    }

    @Override // com.google.android.gms.cast.zzp, com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        doExecute((com.google.android.gms.cast.internal.zzw) anyClient);
    }

    @Override // com.google.android.gms.cast.zzp
    /* JADX INFO: renamed from: zza */
    public final void doExecute(com.google.android.gms.cast.internal.zzw zzwVar) throws RemoteException {
        try {
            zzwVar.zzM(this.zza, this.zzb, this);
        } catch (IllegalStateException unused) {
            zzc(CastStatusCodes.INVALID_REQUEST);
        }
    }
}
