package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.Cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzbk extends Cast.Listener {
    final /* synthetic */ zzbl zza;

    zzbk(zzbl zzblVar) {
        this.zza = zzblVar;
    }

    @Override // com.google.android.gms.cast.Cast.Listener
    public final void onVolumeChanged() {
        this.zza.zza();
    }
}
