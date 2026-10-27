package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzqt implements zzrn {
    private static final zzqz zza = new zzqr();
    private final zzqz zzb;

    public zzqt() {
        zzqz zzqzVar;
        zzqz[] zzqzVarArr = new zzqz[2];
        zzqzVarArr[0] = zzpp.zza();
        try {
            zzqzVar = (zzqz) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            zzqzVar = zza;
        }
        zzqzVarArr[1] = zzqzVar;
        zzqs zzqsVar = new zzqs(zzqzVarArr);
        zzqc.zzf(zzqsVar, "messageInfoFactory");
        this.zzb = zzqsVar;
    }

    private static boolean zzb(zzqy zzqyVar) {
        return zzqyVar.zzc() == 1;
    }

    @Override // com.google.android.gms.internal.cast.zzrn
    public final zzrm zza(Class cls) {
        zzro.zzE(cls);
        zzqy zzqyVarZzb = this.zzb.zzb(cls);
        return zzqyVarZzb.zzb() ? zzpt.class.isAssignableFrom(cls) ? zzrf.zzg(zzro.zzB(), zzpl.zzb(), zzqyVarZzb.zza()) : zzrf.zzg(zzro.zzz(), zzpl.zza(), zzqyVarZzb.zza()) : zzpt.class.isAssignableFrom(cls) ? zzb(zzqyVarZzb) ? zzre.zzg(cls, zzqyVarZzb, zzrh.zzb(), zzqp.zzd(), zzro.zzB(), zzpl.zzb(), zzqx.zzb()) : zzre.zzg(cls, zzqyVarZzb, zzrh.zzb(), zzqp.zzd(), zzro.zzB(), null, zzqx.zzb()) : zzb(zzqyVarZzb) ? zzre.zzg(cls, zzqyVarZzb, zzrh.zza(), zzqp.zzc(), zzro.zzz(), zzpl.zza(), zzqx.zza()) : zzre.zzg(cls, zzqyVarZzb, zzrh.zza(), zzqp.zzc(), zzro.zzA(), null, zzqx.zza());
    }
}
