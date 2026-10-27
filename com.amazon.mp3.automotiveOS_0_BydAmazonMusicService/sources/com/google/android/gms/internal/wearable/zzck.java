package com.google.android.gms.internal.wearable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzck extends zzcl {
    private zzck() {
        super(null);
    }

    /* synthetic */ zzck(zzci zzciVar) {
        super(null);
    }

    @Override // com.google.android.gms.internal.wearable.zzcl
    final void zza(Object obj, long j) {
        ((zzbz) zzeg.zzn(obj, j)).zzb();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.google.android.gms.internal.wearable.zzbz, java.util.Collection, java.util.List] */
    /* JADX WARN: Type inference failed for: r6v3, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v4 */
    @Override // com.google.android.gms.internal.wearable.zzcl
    final <E> void zzb(Object obj, Object obj2, long j) {
        zzbz zzbzVar = (zzbz) zzeg.zzn(obj, j);
        ?? r6 = (zzbz) zzeg.zzn(obj2, j);
        int size = zzbzVar.size();
        int size2 = r6.size();
        ?? r0 = zzbzVar;
        r0 = zzbzVar;
        if (size > 0 && size2 > 0) {
            boolean zZza = zzbzVar.zza();
            ?? Zze = zzbzVar;
            if (!zZza) {
                Zze = zzbzVar.zze(size2 + size);
            }
            Zze.addAll(r6);
            r0 = Zze;
        }
        if (size > 0) {
            r6 = r0;
        }
        zzeg.zzo(obj, j, r6);
    }
}
