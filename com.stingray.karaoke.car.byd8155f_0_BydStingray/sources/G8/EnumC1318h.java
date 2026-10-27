package g8;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: renamed from: g8.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class EnumC1318h {
    public static final EnumC1318h l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final EnumC1318h f15007m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final EnumC1318h f15008n;
    public static final /* synthetic */ EnumC1318h[] o;

    static {
        EnumC1318h enumC1318h = new EnumC1318h("FORCE_FLEXIBILITY", 0);
        l = enumC1318h;
        EnumC1318h enumC1318h2 = new EnumC1318h("NULLABLE", 1);
        f15007m = enumC1318h2;
        EnumC1318h enumC1318h3 = new EnumC1318h("NOT_NULL", 2);
        f15008n = enumC1318h3;
        o = new EnumC1318h[]{enumC1318h, enumC1318h2, enumC1318h3};
    }

    public static EnumC1318h valueOf(String str) {
        return (EnumC1318h) Enum.valueOf(EnumC1318h.class, str);
    }

    public static EnumC1318h[] values() {
        return (EnumC1318h[]) o.clone();
    }
}
