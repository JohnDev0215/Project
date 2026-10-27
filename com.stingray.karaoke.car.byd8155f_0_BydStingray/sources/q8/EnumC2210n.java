package q8;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: renamed from: q8.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class EnumC2210n {
    public static final EnumC2210n l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final EnumC2210n f21346m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final EnumC2210n f21347n;
    public static final /* synthetic */ EnumC2210n[] o;

    static {
        EnumC2210n enumC2210n = new EnumC2210n("ALL", 0);
        l = enumC2210n;
        EnumC2210n enumC2210n2 = new EnumC2210n("ONLY_NON_SYNTHESIZED", 1);
        f21346m = enumC2210n2;
        EnumC2210n enumC2210n3 = new EnumC2210n("NONE", 2);
        f21347n = enumC2210n3;
        o = new EnumC2210n[]{enumC2210n, enumC2210n2, enumC2210n3};
    }

    public static EnumC2210n valueOf(String str) {
        return (EnumC2210n) Enum.valueOf(EnumC2210n.class, str);
    }

    public static EnumC2210n[] values() {
        return (EnumC2210n[]) o.clone();
    }
}
