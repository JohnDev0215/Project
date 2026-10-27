package com.google.android.gms.internal.wearable;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'zza' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzbm {
    public static final zzbm zzA;
    public static final zzbm zzB;
    public static final zzbm zzC;
    public static final zzbm zzD;
    public static final zzbm zzE;
    public static final zzbm zzF;
    public static final zzbm zzG;
    public static final zzbm zzH;
    public static final zzbm zzI;
    public static final zzbm zzJ;
    public static final zzbm zzK;
    public static final zzbm zzL;
    public static final zzbm zzM;
    public static final zzbm zzN;
    public static final zzbm zzO;
    public static final zzbm zzP;
    public static final zzbm zzQ;
    public static final zzbm zzR;
    public static final zzbm zzS;
    public static final zzbm zzT;
    public static final zzbm zzU;
    public static final zzbm zzV;
    public static final zzbm zzW;
    public static final zzbm zzX;
    public static final zzbm zzY;
    public static final zzbm zza;
    private static final zzbm[] zzac;
    private static final /* synthetic */ zzbm[] zzad;
    public static final zzbm zzb;
    public static final zzbm zzc;
    public static final zzbm zzd;
    public static final zzbm zze;
    public static final zzbm zzf;
    public static final zzbm zzg;
    public static final zzbm zzh;
    public static final zzbm zzi;
    public static final zzbm zzj;
    public static final zzbm zzk;
    public static final zzbm zzl;
    public static final zzbm zzm;
    public static final zzbm zzn;
    public static final zzbm zzo;
    public static final zzbm zzp;
    public static final zzbm zzq;
    public static final zzbm zzr;
    public static final zzbm zzs;
    public static final zzbm zzt;
    public static final zzbm zzu;
    public static final zzbm zzv;
    public static final zzbm zzw;
    public static final zzbm zzx;
    public static final zzbm zzy;
    public static final zzbm zzz;
    private final zzcd zzZ;
    private final int zzaa;
    private final Class<?> zzab;

    static {
        zzcd zzcdVar = zzcd.zze;
        zzbm zzbmVar = new zzbm("DOUBLE", 0, 0, 1, zzcdVar);
        zza = zzbmVar;
        zzcd zzcdVar2 = zzcd.zzd;
        zzbm zzbmVar2 = new zzbm("FLOAT", 1, 1, 1, zzcdVar2);
        zzb = zzbmVar2;
        zzcd zzcdVar3 = zzcd.zzc;
        zzbm zzbmVar3 = new zzbm("INT64", 2, 2, 1, zzcdVar3);
        zzc = zzbmVar3;
        zzbm zzbmVar4 = new zzbm("UINT64", 3, 3, 1, zzcdVar3);
        zzd = zzbmVar4;
        zzcd zzcdVar4 = zzcd.zzb;
        zzbm zzbmVar5 = new zzbm("INT32", 4, 4, 1, zzcdVar4);
        zze = zzbmVar5;
        zzbm zzbmVar6 = new zzbm("FIXED64", 5, 5, 1, zzcdVar3);
        zzf = zzbmVar6;
        zzbm zzbmVar7 = new zzbm("FIXED32", 6, 6, 1, zzcdVar4);
        zzg = zzbmVar7;
        zzcd zzcdVar5 = zzcd.zzf;
        zzbm zzbmVar8 = new zzbm("BOOL", 7, 7, 1, zzcdVar5);
        zzh = zzbmVar8;
        zzcd zzcdVar6 = zzcd.zzg;
        zzbm zzbmVar9 = new zzbm("STRING", 8, 8, 1, zzcdVar6);
        zzi = zzbmVar9;
        zzcd zzcdVar7 = zzcd.zzj;
        zzbm zzbmVar10 = new zzbm("MESSAGE", 9, 9, 1, zzcdVar7);
        zzj = zzbmVar10;
        zzcd zzcdVar8 = zzcd.zzh;
        zzbm zzbmVar11 = new zzbm("BYTES", 10, 10, 1, zzcdVar8);
        zzk = zzbmVar11;
        zzbm zzbmVar12 = new zzbm("UINT32", 11, 11, 1, zzcdVar4);
        zzl = zzbmVar12;
        zzcd zzcdVar9 = zzcd.zzi;
        zzbm zzbmVar13 = new zzbm("ENUM", 12, 12, 1, zzcdVar9);
        zzm = zzbmVar13;
        zzbm zzbmVar14 = new zzbm("SFIXED32", 13, 13, 1, zzcdVar4);
        zzn = zzbmVar14;
        zzbm zzbmVar15 = new zzbm("SFIXED64", 14, 14, 1, zzcdVar3);
        zzo = zzbmVar15;
        zzbm zzbmVar16 = new zzbm("SINT32", 15, 15, 1, zzcdVar4);
        zzp = zzbmVar16;
        zzbm zzbmVar17 = new zzbm("SINT64", 16, 16, 1, zzcdVar3);
        zzq = zzbmVar17;
        zzbm zzbmVar18 = new zzbm("GROUP", 17, 17, 1, zzcdVar7);
        zzr = zzbmVar18;
        zzbm zzbmVar19 = new zzbm("DOUBLE_LIST", 18, 18, 2, zzcdVar);
        zzs = zzbmVar19;
        zzbm zzbmVar20 = new zzbm("FLOAT_LIST", 19, 19, 2, zzcdVar2);
        zzt = zzbmVar20;
        zzbm zzbmVar21 = new zzbm("INT64_LIST", 20, 20, 2, zzcdVar3);
        zzu = zzbmVar21;
        zzbm zzbmVar22 = new zzbm("UINT64_LIST", 21, 21, 2, zzcdVar3);
        zzv = zzbmVar22;
        zzbm zzbmVar23 = new zzbm("INT32_LIST", 22, 22, 2, zzcdVar4);
        zzw = zzbmVar23;
        zzbm zzbmVar24 = new zzbm("FIXED64_LIST", 23, 23, 2, zzcdVar3);
        zzx = zzbmVar24;
        zzbm zzbmVar25 = new zzbm("FIXED32_LIST", 24, 24, 2, zzcdVar4);
        zzy = zzbmVar25;
        zzbm zzbmVar26 = new zzbm("BOOL_LIST", 25, 25, 2, zzcdVar5);
        zzz = zzbmVar26;
        zzbm zzbmVar27 = new zzbm("STRING_LIST", 26, 26, 2, zzcdVar6);
        zzA = zzbmVar27;
        zzbm zzbmVar28 = new zzbm("MESSAGE_LIST", 27, 27, 2, zzcdVar7);
        zzB = zzbmVar28;
        zzbm zzbmVar29 = new zzbm("BYTES_LIST", 28, 28, 2, zzcdVar8);
        zzC = zzbmVar29;
        zzbm zzbmVar30 = new zzbm("UINT32_LIST", 29, 29, 2, zzcdVar4);
        zzD = zzbmVar30;
        zzbm zzbmVar31 = new zzbm("ENUM_LIST", 30, 30, 2, zzcdVar9);
        zzE = zzbmVar31;
        zzbm zzbmVar32 = new zzbm("SFIXED32_LIST", 31, 31, 2, zzcdVar4);
        zzF = zzbmVar32;
        zzbm zzbmVar33 = new zzbm("SFIXED64_LIST", 32, 32, 2, zzcdVar3);
        zzG = zzbmVar33;
        zzbm zzbmVar34 = new zzbm("SINT32_LIST", 33, 33, 2, zzcdVar4);
        zzH = zzbmVar34;
        zzbm zzbmVar35 = new zzbm("SINT64_LIST", 34, 34, 2, zzcdVar3);
        zzI = zzbmVar35;
        zzbm zzbmVar36 = new zzbm("DOUBLE_LIST_PACKED", 35, 35, 3, zzcdVar);
        zzJ = zzbmVar36;
        zzbm zzbmVar37 = new zzbm("FLOAT_LIST_PACKED", 36, 36, 3, zzcdVar2);
        zzK = zzbmVar37;
        zzbm zzbmVar38 = new zzbm("INT64_LIST_PACKED", 37, 37, 3, zzcdVar3);
        zzL = zzbmVar38;
        zzbm zzbmVar39 = new zzbm("UINT64_LIST_PACKED", 38, 38, 3, zzcdVar3);
        zzM = zzbmVar39;
        zzbm zzbmVar40 = new zzbm("INT32_LIST_PACKED", 39, 39, 3, zzcdVar4);
        zzN = zzbmVar40;
        zzbm zzbmVar41 = new zzbm("FIXED64_LIST_PACKED", 40, 40, 3, zzcdVar3);
        zzO = zzbmVar41;
        zzbm zzbmVar42 = new zzbm("FIXED32_LIST_PACKED", 41, 41, 3, zzcdVar4);
        zzP = zzbmVar42;
        zzbm zzbmVar43 = new zzbm("BOOL_LIST_PACKED", 42, 42, 3, zzcdVar5);
        zzQ = zzbmVar43;
        zzbm zzbmVar44 = new zzbm("UINT32_LIST_PACKED", 43, 43, 3, zzcdVar4);
        zzR = zzbmVar44;
        zzbm zzbmVar45 = new zzbm("ENUM_LIST_PACKED", 44, 44, 3, zzcdVar9);
        zzS = zzbmVar45;
        zzbm zzbmVar46 = new zzbm("SFIXED32_LIST_PACKED", 45, 45, 3, zzcdVar4);
        zzT = zzbmVar46;
        zzbm zzbmVar47 = new zzbm("SFIXED64_LIST_PACKED", 46, 46, 3, zzcdVar3);
        zzU = zzbmVar47;
        zzbm zzbmVar48 = new zzbm("SINT32_LIST_PACKED", 47, 47, 3, zzcdVar4);
        zzV = zzbmVar48;
        zzbm zzbmVar49 = new zzbm("SINT64_LIST_PACKED", 48, 48, 3, zzcdVar3);
        zzW = zzbmVar49;
        zzbm zzbmVar50 = new zzbm("GROUP_LIST", 49, 49, 2, zzcdVar7);
        zzX = zzbmVar50;
        zzbm zzbmVar51 = new zzbm("MAP", 50, 50, 4, zzcd.zza);
        zzY = zzbmVar51;
        zzad = new zzbm[]{zzbmVar, zzbmVar2, zzbmVar3, zzbmVar4, zzbmVar5, zzbmVar6, zzbmVar7, zzbmVar8, zzbmVar9, zzbmVar10, zzbmVar11, zzbmVar12, zzbmVar13, zzbmVar14, zzbmVar15, zzbmVar16, zzbmVar17, zzbmVar18, zzbmVar19, zzbmVar20, zzbmVar21, zzbmVar22, zzbmVar23, zzbmVar24, zzbmVar25, zzbmVar26, zzbmVar27, zzbmVar28, zzbmVar29, zzbmVar30, zzbmVar31, zzbmVar32, zzbmVar33, zzbmVar34, zzbmVar35, zzbmVar36, zzbmVar37, zzbmVar38, zzbmVar39, zzbmVar40, zzbmVar41, zzbmVar42, zzbmVar43, zzbmVar44, zzbmVar45, zzbmVar46, zzbmVar47, zzbmVar48, zzbmVar49, zzbmVar50, zzbmVar51};
        zzbm[] zzbmVarArrValues = values();
        zzac = new zzbm[zzbmVarArrValues.length];
        for (zzbm zzbmVar52 : zzbmVarArrValues) {
            zzac[zzbmVar52.zzaa] = zzbmVar52;
        }
    }

    private zzbm(String str, int i, int i2, int i3, zzcd zzcdVar) {
        this.zzaa = i2;
        this.zzZ = zzcdVar;
        zzcd zzcdVar2 = zzcd.zza;
        int i4 = i3 - 1;
        if (i4 == 1 || i4 == 3) {
            this.zzab = zzcdVar.zza();
        } else {
            this.zzab = null;
        }
        if (i3 == 1) {
            zzcdVar.ordinal();
        }
    }

    public static zzbm[] values() {
        return (zzbm[]) zzad.clone();
    }

    public final int zza() {
        return this.zzaa;
    }
}
