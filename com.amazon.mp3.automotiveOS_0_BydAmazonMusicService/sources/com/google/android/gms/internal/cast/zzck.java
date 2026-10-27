package com.google.android.gms.internal.cast;

import android.view.Display;
import androidx.annotation.Nullable;
import com.google.android.gms.cast.CastRemoteDisplay;
import com.google.android.gms.common.api.Status;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzck implements CastRemoteDisplay.CastRemoteDisplaySessionResult {
    private final Status zza;

    @Nullable
    private final Display zzb;

    public zzck(Display display) {
        this.zza = Status.RESULT_SUCCESS;
        this.zzb = display;
    }

    public zzck(Status status) {
        this.zza = status;
        this.zzb = null;
    }

    @Override // com.google.android.gms.cast.CastRemoteDisplay.CastRemoteDisplaySessionResult
    @Nullable
    public final Display getPresentationDisplay() {
        return this.zzb;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zza;
    }
}
