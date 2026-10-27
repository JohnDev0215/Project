package com.google.android.gms.cast;

import android.app.PendingIntent;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzl extends com.google.android.gms.cast.internal.zzab {
    final /* synthetic */ String zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzl(zzm zzmVar, GoogleApiClient googleApiClient, String str) {
        super(googleApiClient);
        this.zza = str;
    }

    @Override // com.google.android.gms.cast.internal.zzab, com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        doExecute((com.google.android.gms.cast.internal.zzw) anyClient);
    }

    @Override // com.google.android.gms.cast.internal.zzab
    /* JADX INFO: renamed from: zza */
    public final void doExecute(com.google.android.gms.cast.internal.zzw zzwVar) throws RemoteException {
        if (TextUtils.isEmpty(this.zza)) {
            setResult(new Status(CastStatusCodes.INVALID_REQUEST, "IllegalArgument: sessionId cannot be null or empty", (PendingIntent) null));
            return;
        }
        try {
            zzwVar.zzV(this.zza, this);
        } catch (IllegalStateException unused) {
            zzc(CastStatusCodes.INVALID_REQUEST);
        }
    }
}
