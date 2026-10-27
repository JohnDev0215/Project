package com.google.android.gms.internal.cast;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'zzc' uses external variables
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
public final class zzss {
    public static final zzss zza;
    public static final zzss zzb;
    public static final zzss zzc;
    public static final zzss zzd;
    public static final zzss zze;
    public static final zzss zzf;
    public static final zzss zzg;
    public static final zzss zzh;
    public static final zzss zzi;
    public static final zzss zzj;
    public static final zzss zzk;
    public static final zzss zzl;
    public static final zzss zzm;
    public static final zzss zzn;
    public static final zzss zzo;
    public static final zzss zzp;
    public static final zzss zzq;
    public static final zzss zzr;
    private static final /* synthetic */ zzss[] zzs;
    private final zzst zzt;

    static {
        zzss zzssVar = new zzss("DOUBLE", 0, zzst.DOUBLE, 1);
        zza = zzssVar;
        zzss zzssVar2 = new zzss("FLOAT", 1, zzst.FLOAT, 5);
        zzb = zzssVar2;
        zzst zzstVar = zzst.LONG;
        zzss zzssVar3 = new zzss("INT64", 2, zzstVar, 0);
        zzc = zzssVar3;
        zzss zzssVar4 = new zzss("UINT64", 3, zzstVar, 0);
        zzd = zzssVar4;
        zzst zzstVar2 = zzst.INT;
        zzss zzssVar5 = new zzss("INT32", 4, zzstVar2, 0);
        zze = zzssVar5;
        zzss zzssVar6 = new zzss("FIXED64", 5, zzstVar, 1);
        zzf = zzssVar6;
        zzss zzssVar7 = new zzss("FIXED32", 6, zzstVar2, 5);
        zzg = zzssVar7;
        zzss zzssVar8 = new zzss("BOOL", 7, zzst.BOOLEAN, 0);
        zzh = zzssVar8;
        zzss zzssVar9 = new zzss("STRING", 8, zzst.STRING, 2);
        zzi = zzssVar9;
        zzst zzstVar3 = zzst.MESSAGE;
        zzss zzssVar10 = new zzss("GROUP", 9, zzstVar3, 3);
        zzj = zzssVar10;
        zzss zzssVar11 = new zzss("MESSAGE", 10, zzstVar3, 2);
        zzk = zzssVar11;
        zzss zzssVar12 = new zzss("BYTES", 11, zzst.BYTE_STRING, 2);
        zzl = zzssVar12;
        zzss zzssVar13 = new zzss("UINT32", 12, zzstVar2, 0);
        zzm = zzssVar13;
        zzss zzssVar14 = new zzss("ENUM", 13, zzst.ENUM, 0);
        zzn = zzssVar14;
        zzss zzssVar15 = new zzss("SFIXED32", 14, zzstVar2, 5);
        zzo = zzssVar15;
        zzss zzssVar16 = new zzss("SFIXED64", 15, zzstVar, 1);
        zzp = zzssVar16;
        zzss zzssVar17 = new zzss("SINT32", 16, zzstVar2, 0);
        zzq = zzssVar17;
        zzss zzssVar18 = new zzss("SINT64", 17, zzstVar, 0);
        zzr = zzssVar18;
        zzs = new zzss[]{zzssVar, zzssVar2, zzssVar3, zzssVar4, zzssVar5, zzssVar6, zzssVar7, zzssVar8, zzssVar9, zzssVar10, zzssVar11, zzssVar12, zzssVar13, zzssVar14, zzssVar15, zzssVar16, zzssVar17, zzssVar18};
    }

    private zzss(String str, int i, zzst zzstVar, int i2) {
        this.zzt = zzstVar;
    }

    public static zzss[] values() {
        return (zzss[]) zzs.clone();
    }

    public final zzst zza() {
        return this.zzt;
    }
}
