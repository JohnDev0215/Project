package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzqs implements zzqz {
    private final zzqz[] zza;

    zzqs(zzqz... zzqzVarArr) {
        this.zza = zzqzVarArr;
    }

    @Override // com.google.android.gms.internal.cast.zzqz
    public final zzqy zzb(Class cls) {
        zzqz[] zzqzVarArr = this.zza;
        for (int i = 0; i < 2; i++) {
            zzqz zzqzVar = zzqzVarArr[i];
            if (zzqzVar.zzc(cls)) {
                return zzqzVar.zzb(cls);
            }
        }
        String name = cls.getName();
        throw new UnsupportedOperationException(name.length() != 0 ? "No factory is available for message type: ".concat(name) : new String("No factory is available for message type: "));
    }

    @Override // com.google.android.gms.internal.cast.zzqz
    public final boolean zzc(Class cls) {
        zzqz[] zzqzVarArr = this.zza;
        for (int i = 0; i < 2; i++) {
            if (zzqzVarArr[i].zzc(cls)) {
                return true;
            }
        }
        return false;
    }
}
