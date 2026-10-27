package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzlk extends zzpt implements zzrc {
    private static final zzlk zzb;
    private int zzC;
    private int zzD;
    private zzls zzE;
    private int zzF;
    private zzli zzG;
    private zzls zzI;
    private int zzJ;
    private int zzK;
    private int zzL;
    private int zzM;
    private int zzN;
    private int zzO;
    private zzob zzP;
    private zzlc zzQ;
    private zzkr zzR;
    private zzmp zzS;
    private zznn zzT;
    private zzmt zzU;
    private int zzW;
    private zznb zzX;
    private boolean zzZ;
    private boolean zzaa;
    private int zzab;
    private zzku zzac;
    private zznj zzad;
    private zzmj zzae;
    private zzmz zzaf;
    private zznt zzag;
    private zzlm zzah;
    private int zzai;
    private int zzaj;
    private int zzak;
    private int zze;
    private int zzf;
    private long zzg;
    private long zzh;
    private int zzi;
    private zzmf zzj;
    private zznh zzk;
    private zzmd zzl;
    private zzly zzm;
    private zzlg zzn;
    private zznf zzo;
    private zzky zzp;
    private zznz zzq;
    private zzlw zzs;
    private zzfi zzt;
    private zzmx zzv;
    private byte zzam = 2;
    private String zzr = "";
    private String zzu = "";
    private String zzw = "";
    private zzpy zzx = zzpt.zzu();
    private zzqb zzy = zzpt.zzw();
    private zzqb zzz = zzpt.zzw();
    private zzqb zzA = zzpt.zzw();
    private zzqb zzB = zzpt.zzw();
    private zzqb zzH = zzpt.zzw();
    private zzqb zzV = zzpt.zzw();
    private zzqb zzY = zzpt.zzw();
    private zzqb zzal = zzpt.zzw();

    static {
        zzlk zzlkVar = new zzlk();
        zzb = zzlkVar;
        zzpt.zzA(zzlk.class, zzlkVar);
    }

    private zzlk() {
    }

    public static zzlj zzc() {
        return (zzlj) zzb.zzr();
    }

    public static zzlj zzd(zzlk zzlkVar) {
        zzpq zzpqVarZzr = zzb.zzr();
        zzpqVarZzr.zzo(zzlkVar);
        return (zzlj) zzpqVarZzr;
    }

    static /* synthetic */ void zzf(zzlk zzlkVar, int i) {
        zzlkVar.zze |= 268435456;
        zzlkVar.zzO = i;
    }

    static /* synthetic */ void zzg(zzlk zzlkVar, zzlc zzlcVar) {
        zzlcVar.getClass();
        zzlkVar.zzQ = zzlcVar;
        zzlkVar.zze |= 1073741824;
    }

    static /* synthetic */ void zzh(zzlk zzlkVar, zzla zzlaVar) {
        zzlaVar.getClass();
        zzqb zzqbVar = zzlkVar.zzV;
        if (!zzqbVar.zzc()) {
            int size = zzqbVar.size();
            zzlkVar.zzV = zzqbVar.zzg(size == 0 ? 10 : size + size);
        }
        zzlkVar.zzV.add(zzlaVar);
    }

    static /* synthetic */ void zzi(zzlk zzlkVar, zzku zzkuVar) {
        zzkuVar.getClass();
        zzlkVar.zzac = zzkuVar;
        zzlkVar.zzf |= 256;
    }

    static /* synthetic */ void zzj(zzlk zzlkVar, long j) {
        zzlkVar.zze |= 2;
        zzlkVar.zzh = j;
    }

    static /* synthetic */ void zzk(zzlk zzlkVar, String str) {
        zzlkVar.zze |= 2048;
        zzlkVar.zzr = str;
    }

    static /* synthetic */ void zzl(zzlk zzlkVar, String str) {
        str.getClass();
        zzlkVar.zze |= 16384;
        zzlkVar.zzu = str;
    }

    static /* synthetic */ void zzm(zzlk zzlkVar, String str) {
        zzlkVar.zze |= 65536;
        zzlkVar.zzw = str;
    }

    public final zzlc zza() {
        zzlc zzlcVar = this.zzQ;
        return zzlcVar == null ? zzlc.zze() : zzlcVar;
    }

    @Override // com.google.android.gms.internal.cast.zzpt
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.zzam);
        }
        if (i2 == 2) {
            return zzpt.zzz(zzb, "\u0001:\u0000\u0002\u0001::\u0000\t\u0001\u0001ဂ\u0000\u0002ဂ\u0001\u0003ဌ\u0002\u0004ဉ\u0003\u0005ဉ\u0004\u0006ဉ\u0005\u0007ဉ\u0006\bဉ\u0007\tဈ\u000e\nဉ\b\u000bဉ\t\fဉ\n\rဈ\u000b\u000eဉ\f\u000fဉ\r\u0010ဉ\u000f\u0011ဈ\u0010\u0012\u0016\u0013\u001b\u0014\u001b\u0015\u001b\u0016\u001b\u0017ဌ\u0011\u0018ဉ\u0015\u0019\u001b\u001aဉ\u0016\u001bဌ\u0018\u001cင\u0019\u001dင\u001a\u001eင\u001b\u001fဆ\u001c ဉ\u001d!ဉ\u001e\"ဉ\u001f#ဌ\u0012$ဉ\u0013%ᐉ &ဉ!'ဉ\"(\u001b)ဌ#*ဉ$+\u001b,ဌ\u0017-ဇ%.ဇ&/ဌ'0ဉ(1င\u00142ဉ)3ဉ*4ဉ+5ဉ,6ဉ-7ဌ.8ဌ/9ဌ0:\u001b", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", zzjs.zza(), "zzj", "zzk", "zzl", "zzm", "zzn", "zzu", "zzo", "zzp", "zzq", "zzr", "zzs", "zzt", "zzv", "zzw", "zzx", "zzy", zzmh.class, "zzz", zzml.class, "zzA", zzlo.class, "zzB", zznp.class, "zzC", zzjm.zza(), "zzG", "zzH", zzls.class, "zzI", "zzK", zzht.zza(), "zzL", "zzM", "zzN", "zzO", "zzP", "zzQ", "zzR", "zzD", zzjg.zza(), "zzE", "zzS", "zzT", "zzU", "zzV", zzla.class, "zzW", zzjj.zza(), "zzX", "zzY", zzle.class, "zzJ", zzhq.zza(), "zzZ", "zzaa", "zzab", zzhk.zza(), "zzac", "zzF", "zzad", "zzae", "zzaf", "zzag", "zzah", "zzai", zzgg.zza(), "zzaj", zzil.zza(), "zzak", zzgd.zza(), "zzal", zznv.class});
        }
        if (i2 == 3) {
            return new zzlk();
        }
        zzko zzkoVar = null;
        if (i2 == 4) {
            return new zzlj(zzkoVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        this.zzam = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
