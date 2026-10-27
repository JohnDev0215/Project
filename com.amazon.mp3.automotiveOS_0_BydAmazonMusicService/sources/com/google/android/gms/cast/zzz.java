package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzz extends zzab {
    final /* synthetic */ TaskCompletionSource zza;
    final /* synthetic */ CastRemoteDisplayClient zzb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzz(CastRemoteDisplayClient castRemoteDisplayClient, TaskCompletionSource taskCompletionSource) {
        super(null);
        this.zzb = castRemoteDisplayClient;
        this.zza = taskCompletionSource;
    }

    @Override // com.google.android.gms.cast.zzab, com.google.android.gms.internal.cast.zzcq
    public final void zzd(int i) throws RemoteException {
        this.zzb.zzc.d("onError: %d", Integer.valueOf(i));
        CastRemoteDisplayClient.zzd(this.zzb);
        TaskUtil.setResultOrApiException(Status.RESULT_INTERNAL_ERROR, this.zza);
    }

    @Override // com.google.android.gms.cast.zzab, com.google.android.gms.internal.cast.zzcq
    public final void zzf() throws RemoteException {
        this.zzb.zzc.d("onDisconnected", new Object[0]);
        CastRemoteDisplayClient.zzd(this.zzb);
        TaskUtil.setResultOrApiException(Status.RESULT_SUCCESS, this.zza);
    }
}
