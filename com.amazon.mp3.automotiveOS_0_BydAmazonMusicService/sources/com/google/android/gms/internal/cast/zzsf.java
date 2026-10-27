package com.google.android.gms.internal.cast;

import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzsf extends zzsd {
    zzsf() {
    }

    @Override // com.google.android.gms.internal.cast.zzsd
    final /* synthetic */ int zza(Object obj) {
        return ((zzse) obj).zza();
    }

    @Override // com.google.android.gms.internal.cast.zzsd
    final /* synthetic */ int zzb(Object obj) {
        return ((zzse) obj).zzb();
    }

    @Override // com.google.android.gms.internal.cast.zzsd
    final /* synthetic */ Object zzc(Object obj) {
        return ((zzpt) obj).zzc;
    }

    @Override // com.google.android.gms.internal.cast.zzsd
    final /* bridge */ /* synthetic */ Object zzd(Object obj, Object obj2) {
        zzse zzseVar = (zzse) obj2;
        return zzseVar.equals(zzse.zzc()) ? obj : zzse.zzd((zzse) obj, zzseVar);
    }

    @Override // com.google.android.gms.internal.cast.zzsd
    final void zze(Object obj) {
        zzse zzseVar = ((zzpt) obj).zzc;
    }

    @Override // com.google.android.gms.internal.cast.zzsd
    final /* synthetic */ void zzf(Object obj, Object obj2) {
        ((zzpt) obj).zzc = (zzse) obj2;
    }

    @Override // com.google.android.gms.internal.cast.zzsd
    final /* synthetic */ void zzg(Object obj, zzph zzphVar) throws IOException {
    }
}
