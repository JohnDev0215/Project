package com.google.android.gms.cast.internal;

import androidx.annotation.Nullable;
import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzak implements zzar {
    final /* synthetic */ zzap zza;

    zzak(zzap zzapVar) {
        this.zza = zzapVar;
    }

    @Override // com.google.android.gms.cast.internal.zzar
    public final void zza(long j, int i, @Nullable Object obj) {
        if (i != 0) {
            this.zza.zzB.setException(new IOException("storing session is timeout"));
        }
    }

    @Override // com.google.android.gms.cast.internal.zzar
    public final void zzb(long j) {
        this.zza.zzB.setResult(null);
    }
}
