package com.google.android.gms.internal.play_billing;

/* JADX INFO: compiled from: com.android.billingclient:billing@@5.2.1 */
/* JADX INFO: loaded from: classes6.dex */
final class zzcx implements zzdq {
    private static final zzdd zza = new zzcv();
    private final zzdd zzb;

    public zzcx() {
        zzdd zzddVar;
        zzdd[] zzddVarArr = new zzdd[2];
        zzddVarArr[0] = zzbw.zza();
        try {
            zzddVar = (zzdd) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            zzddVar = zza;
        }
        zzddVarArr[1] = zzddVar;
        zzcw zzcwVar = new zzcw(zzddVarArr);
        byte[] bArr = zzcg.zzd;
        this.zzb = zzcwVar;
    }

    private static boolean zzb(zzdc zzdcVar) {
        return zzdcVar.zzc() == 1;
    }

    @Override // com.google.android.gms.internal.play_billing.zzdq
    public final zzdp zza(Class cls) {
        zzdr.zzD(cls);
        zzdc zzdcVarZzb = this.zzb.zzb(cls);
        return zzdcVarZzb.zzb() ? zzcb.class.isAssignableFrom(cls) ? zzdj.zzc(zzdr.zzA(), zzbq.zzb(), zzdcVarZzb.zza()) : zzdj.zzc(zzdr.zzy(), zzbq.zza(), zzdcVarZzb.zza()) : zzcb.class.isAssignableFrom(cls) ? zzb(zzdcVarZzb) ? zzdi.zzl(cls, zzdcVarZzb, zzdl.zzb(), zzct.zzd(), zzdr.zzA(), zzbq.zzb(), zzdb.zzb()) : zzdi.zzl(cls, zzdcVarZzb, zzdl.zzb(), zzct.zzd(), zzdr.zzA(), null, zzdb.zzb()) : zzb(zzdcVarZzb) ? zzdi.zzl(cls, zzdcVarZzb, zzdl.zza(), zzct.zzc(), zzdr.zzy(), zzbq.zza(), zzdb.zza()) : zzdi.zzl(cls, zzdcVarZzb, zzdl.zza(), zzct.zzc(), zzdr.zzz(), null, zzdb.zza());
    }
}
