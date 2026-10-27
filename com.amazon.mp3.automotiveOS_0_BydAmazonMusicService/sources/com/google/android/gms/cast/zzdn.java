package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.VisibleForTesting;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@VisibleForTesting
abstract class zzdn extends com.google.android.gms.cast.internal.zzc {
    private com.google.android.gms.cast.internal.zzar zza;
    protected final WeakReference zzg;
    final /* synthetic */ RemoteMediaPlayer zzh;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzdn(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient) {
        super(googleApiClient);
        this.zzh = remoteMediaPlayer;
        this.zzg = new WeakReference(googleApiClient);
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ Result createFailedResult(Status status) {
        return new zzdm(this, status);
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        com.google.android.gms.cast.internal.zzw zzwVar = (com.google.android.gms.cast.internal.zzw) anyClient;
        synchronized (this.zzh.zza) {
            GoogleApiClient googleApiClient = (GoogleApiClient) this.zzg.get();
            if (googleApiClient == null) {
                setResult(new zzdm(this, new Status(2100)));
                return;
            }
            this.zzh.zzc.zzc(googleApiClient);
            try {
                zza(zzwVar);
            } catch (IllegalArgumentException e) {
                throw e;
            } catch (Throwable unused) {
                setResult(new zzdm(this, new Status(2100)));
            }
            this.zzh.zzc.zzc(null);
        }
    }

    abstract void zza(com.google.android.gms.cast.internal.zzw zzwVar) throws com.google.android.gms.cast.internal.zzan;

    protected final com.google.android.gms.cast.internal.zzar zzb() {
        if (this.zza == null) {
            this.zza = new zzdl(this);
        }
        return this.zza;
    }
}
