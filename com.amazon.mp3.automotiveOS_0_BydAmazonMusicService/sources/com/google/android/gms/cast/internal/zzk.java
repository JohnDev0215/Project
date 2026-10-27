package com.google.android.gms.cast.internal;

import android.os.Bundle;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzk extends zzac {
    final /* synthetic */ TaskCompletionSource zza;

    zzk(zzn zznVar, TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    @Override // com.google.android.gms.cast.internal.zzad
    public final void zzb(Bundle bundle) {
        this.zza.setResult(bundle);
    }
}
