package com.google.android.gms.internal.cast;

import android.os.RemoteException;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.util.VisibleForTesting;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@VisibleForTesting
class zzcj extends BaseImplementation.ApiMethodImpl {
    final /* synthetic */ zzcl zzc;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzcj(zzcl zzclVar, GoogleApiClient googleApiClient) {
        super((Api<?>) zzclVar.zzb, googleApiClient);
        this.zzc = zzclVar;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    protected final /* synthetic */ Result createFailedResult(Status status) {
        return new zzck(status);
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl, com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder
    @KeepForSdk
    public final /* bridge */ /* synthetic */ void setResult(Object obj) {
        super.setResult((Result) obj);
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    @VisibleForTesting
    /* JADX INFO: renamed from: zza, reason: merged with bridge method [inline-methods] */
    public void doExecute(zzco zzcoVar) throws RemoteException {
        throw null;
    }
}
