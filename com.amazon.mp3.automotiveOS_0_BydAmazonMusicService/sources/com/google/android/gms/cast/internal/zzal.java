package com.google.android.gms.cast.internal;

import androidx.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzal implements zzar {
    final /* synthetic */ zzar zza;
    final /* synthetic */ zzap zzb;

    zzal(zzap zzapVar, zzar zzarVar) {
        this.zzb = zzapVar;
        this.zza = zzarVar;
    }

    @Override // com.google.android.gms.cast.internal.zzar
    public final void zza(long j, int i, @Nullable Object obj) {
        if (this.zza != null) {
            if (i == 2001) {
                zzap zzapVar = this.zzb;
                zzapVar.zza.w("Possibility of local queue out of sync with receiver queue. Refetching sequence number. Current Local Sequence Number = %d", Integer.valueOf(zzapVar.zzA));
                this.zzb.zzz.zzl();
                i = 2001;
            }
            this.zza.zza(j, i, obj);
        }
    }

    @Override // com.google.android.gms.cast.internal.zzar
    public final void zzb(long j) {
        zzar zzarVar = this.zza;
        if (zzarVar != null) {
            zzarVar.zzb(j);
        }
    }
}
