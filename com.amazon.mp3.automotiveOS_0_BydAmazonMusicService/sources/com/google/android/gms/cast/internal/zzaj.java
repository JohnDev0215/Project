package com.google.android.gms.cast.internal;

import androidx.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzaj implements zzar {
    final /* synthetic */ zzar zza;
    final /* synthetic */ zzap zzb;

    zzaj(zzap zzapVar, zzar zzarVar) {
        this.zzb = zzapVar;
        this.zza = zzarVar;
    }

    @Override // com.google.android.gms.cast.internal.zzar
    public final void zza(long j, int i, @Nullable Object obj) {
        this.zzb.zzy = null;
        zzar zzarVar = this.zza;
        if (zzarVar != null) {
            zzarVar.zza(j, i, obj);
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
