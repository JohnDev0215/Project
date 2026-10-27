package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzqn extends zzqp {
    private zzqn() {
        super(null);
    }

    /* synthetic */ zzqn(zzqm zzqmVar) {
        super(null);
    }

    @Override // com.google.android.gms.internal.cast.zzqp
    final void zza(Object obj, long j) {
        ((zzqb) zzsn.zzf(obj, j)).zzb();
    }

    @Override // com.google.android.gms.internal.cast.zzqp
    final void zzb(Object obj, Object obj2, long j) {
        zzqb zzqbVarZzg = (zzqb) zzsn.zzf(obj, j);
        zzqb zzqbVar = (zzqb) zzsn.zzf(obj2, j);
        int size = zzqbVarZzg.size();
        int size2 = zzqbVar.size();
        if (size > 0 && size2 > 0) {
            if (!zzqbVarZzg.zzc()) {
                zzqbVarZzg = zzqbVarZzg.zzg(size2 + size);
            }
            zzqbVarZzg.addAll(zzqbVar);
        }
        if (size > 0) {
            zzqbVar = zzqbVarZzg;
        }
        zzsn.zzs(obj, j, zzqbVar);
    }
}
