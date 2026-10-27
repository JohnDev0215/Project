package com.google.android.gms.internal.cast;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'zzb' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzqf {
    public static final zzqf zza;
    public static final zzqf zzb;
    public static final zzqf zzc;
    public static final zzqf zzd;
    public static final zzqf zze;
    public static final zzqf zzf;
    public static final zzqf zzg;
    public static final zzqf zzh;
    public static final zzqf zzi;
    public static final zzqf zzj;
    private static final /* synthetic */ zzqf[] zzk;
    private final Class zzl;
    private final Class zzm;
    private final Object zzn;

    static {
        zzqf zzqfVar = new zzqf("VOID", 0, Void.class, Void.class, null);
        zza = zzqfVar;
        Class cls = Integer.TYPE;
        zzqf zzqfVar2 = new zzqf("INT", 1, cls, Integer.class, 0);
        zzb = zzqfVar2;
        zzqf zzqfVar3 = new zzqf("LONG", 2, Long.TYPE, Long.class, 0L);
        zzc = zzqfVar3;
        zzqf zzqfVar4 = new zzqf("FLOAT", 3, Float.TYPE, Float.class, Float.valueOf(0.0f));
        zzd = zzqfVar4;
        zzqf zzqfVar5 = new zzqf("DOUBLE", 4, Double.TYPE, Double.class, Double.valueOf(0.0d));
        zze = zzqfVar5;
        zzqf zzqfVar6 = new zzqf("BOOLEAN", 5, Boolean.TYPE, Boolean.class, Boolean.FALSE);
        zzf = zzqfVar6;
        zzqf zzqfVar7 = new zzqf("STRING", 6, String.class, String.class, "");
        zzg = zzqfVar7;
        zzqf zzqfVar8 = new zzqf("BYTE_STRING", 7, zzoy.class, zzoy.class, zzoy.zzb);
        zzh = zzqfVar8;
        zzqf zzqfVar9 = new zzqf("ENUM", 8, cls, Integer.class, null);
        zzi = zzqfVar9;
        zzqf zzqfVar10 = new zzqf("MESSAGE", 9, Object.class, Object.class, null);
        zzj = zzqfVar10;
        zzk = new zzqf[]{zzqfVar, zzqfVar2, zzqfVar3, zzqfVar4, zzqfVar5, zzqfVar6, zzqfVar7, zzqfVar8, zzqfVar9, zzqfVar10};
    }

    private zzqf(String str, int i, Class cls, Class cls2, Object obj) {
        this.zzl = cls;
        this.zzm = cls2;
        this.zzn = obj;
    }

    public static zzqf[] values() {
        return (zzqf[]) zzk.clone();
    }

    public final Class zza() {
        return this.zzm;
    }
}
