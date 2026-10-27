package s5;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: renamed from: s5.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class EnumC2407a {
    public static final EnumC2407a l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final EnumC2407a f22368m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final EnumC2407a f22369n;
    public static final EnumC2407a o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final EnumC2407a f22370p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final /* synthetic */ EnumC2407a[] f22371q;

    /* JADX INFO: Fake field, exist only in values array */
    EnumC2407a EF0;

    static {
        EnumC2407a enumC2407a = new EnumC2407a("ERROR_CORRECTION", 0);
        EnumC2407a enumC2407a2 = new EnumC2407a("CHARACTER_SET", 1);
        l = enumC2407a2;
        EnumC2407a enumC2407a3 = new EnumC2407a("DATA_MATRIX_SHAPE", 2);
        EnumC2407a enumC2407a4 = new EnumC2407a("DATA_MATRIX_COMPACT", 3);
        EnumC2407a enumC2407a5 = new EnumC2407a("MIN_SIZE", 4);
        EnumC2407a enumC2407a6 = new EnumC2407a("MAX_SIZE", 5);
        EnumC2407a enumC2407a7 = new EnumC2407a("MARGIN", 6);
        EnumC2407a enumC2407a8 = new EnumC2407a("PDF417_COMPACT", 7);
        EnumC2407a enumC2407a9 = new EnumC2407a("PDF417_COMPACTION", 8);
        EnumC2407a enumC2407a10 = new EnumC2407a("PDF417_DIMENSIONS", 9);
        EnumC2407a enumC2407a11 = new EnumC2407a("PDF417_AUTO_ECI", 10);
        EnumC2407a enumC2407a12 = new EnumC2407a("AZTEC_LAYERS", 11);
        EnumC2407a enumC2407a13 = new EnumC2407a("QR_VERSION", 12);
        f22368m = enumC2407a13;
        EnumC2407a enumC2407a14 = new EnumC2407a("QR_MASK_PATTERN", 13);
        f22369n = enumC2407a14;
        EnumC2407a enumC2407a15 = new EnumC2407a("QR_COMPACT", 14);
        o = enumC2407a15;
        EnumC2407a enumC2407a16 = new EnumC2407a("GS1_FORMAT", 15);
        f22370p = enumC2407a16;
        f22371q = new EnumC2407a[]{enumC2407a, enumC2407a2, enumC2407a3, enumC2407a4, enumC2407a5, enumC2407a6, enumC2407a7, enumC2407a8, enumC2407a9, enumC2407a10, enumC2407a11, enumC2407a12, enumC2407a13, enumC2407a14, enumC2407a15, enumC2407a16, new EnumC2407a("FORCE_CODE_SET", 16), new EnumC2407a("FORCE_C40", 17), new EnumC2407a("CODE128_COMPACT", 18)};
    }

    public static EnumC2407a valueOf(String str) {
        return (EnumC2407a) Enum.valueOf(EnumC2407a.class, str);
    }

    public static EnumC2407a[] values() {
        return (EnumC2407a[]) f22371q.clone();
    }
}
