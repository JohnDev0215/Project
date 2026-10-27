package com.google.android.gms.internal.wearable;

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
/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzcd {
    public static final zzcd zza;
    public static final zzcd zzb;
    public static final zzcd zzc;
    public static final zzcd zzd;
    public static final zzcd zze;
    public static final zzcd zzf;
    public static final zzcd zzg;
    public static final zzcd zzh;
    public static final zzcd zzi;
    public static final zzcd zzj;
    private static final /* synthetic */ zzcd[] zzn;
    private final Class<?> zzk;
    private final Class<?> zzl;
    private final Object zzm;

    static {
        zzcd zzcdVar = new zzcd("VOID", 0, Void.class, Void.class, null);
        zza = zzcdVar;
        Class cls = Integer.TYPE;
        zzcd zzcdVar2 = new zzcd("INT", 1, cls, Integer.class, 0);
        zzb = zzcdVar2;
        zzcd zzcdVar3 = new zzcd("LONG", 2, Long.TYPE, Long.class, 0L);
        zzc = zzcdVar3;
        zzcd zzcdVar4 = new zzcd("FLOAT", 3, Float.TYPE, Float.class, Float.valueOf(0.0f));
        zzd = zzcdVar4;
        zzcd zzcdVar5 = new zzcd("DOUBLE", 4, Double.TYPE, Double.class, Double.valueOf(0.0d));
        zze = zzcdVar5;
        zzcd zzcdVar6 = new zzcd("BOOLEAN", 5, Boolean.TYPE, Boolean.class, Boolean.FALSE);
        zzf = zzcdVar6;
        zzcd zzcdVar7 = new zzcd("STRING", 6, String.class, String.class, "");
        zzg = zzcdVar7;
        zzcd zzcdVar8 = new zzcd("BYTE_STRING", 7, zzau.class, zzau.class, zzau.zzb);
        zzh = zzcdVar8;
        zzcd zzcdVar9 = new zzcd("ENUM", 8, cls, Integer.class, null);
        zzi = zzcdVar9;
        zzcd zzcdVar10 = new zzcd("MESSAGE", 9, Object.class, Object.class, null);
        zzj = zzcdVar10;
        zzn = new zzcd[]{zzcdVar, zzcdVar2, zzcdVar3, zzcdVar4, zzcdVar5, zzcdVar6, zzcdVar7, zzcdVar8, zzcdVar9, zzcdVar10};
    }

    private zzcd(String str, int i, Class cls, Class cls2, Object obj) {
        this.zzk = cls;
        this.zzl = cls2;
        this.zzm = obj;
    }

    public static zzcd[] values() {
        return (zzcd[]) zzn.clone();
    }

    public final Class<?> zza() {
        return this.zzl;
    }
}
