package com.google.android.gms.cast;

import androidx.annotation.Nullable;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.api.Status;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzo implements Cast.ApplicationConnectionResult {
    final /* synthetic */ Status zza;

    zzo(zzp zzpVar, Status status) {
        this.zza = status;
    }

    @Override // com.google.android.gms.cast.Cast.ApplicationConnectionResult
    @Nullable
    public final ApplicationMetadata getApplicationMetadata() {
        return null;
    }

    @Override // com.google.android.gms.cast.Cast.ApplicationConnectionResult
    @Nullable
    public final String getApplicationStatus() {
        return null;
    }

    @Override // com.google.android.gms.cast.Cast.ApplicationConnectionResult
    @Nullable
    public final String getSessionId() {
        return null;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zza;
    }

    @Override // com.google.android.gms.cast.Cast.ApplicationConnectionResult
    public final boolean getWasLaunched() {
        return false;
    }
}
