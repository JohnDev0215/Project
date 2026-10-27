package A;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: renamed from: A.h0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class EnumC0017h0 {
    public static final EnumC0017h0 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final EnumC0017h0 f181m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final /* synthetic */ EnumC0017h0[] f182n;

    static {
        EnumC0017h0 enumC0017h0 = new EnumC0017h0("Vertical", 0);
        l = enumC0017h0;
        EnumC0017h0 enumC0017h02 = new EnumC0017h0("Horizontal", 1);
        f181m = enumC0017h02;
        f182n = new EnumC0017h0[]{enumC0017h0, enumC0017h02};
    }

    public static EnumC0017h0 valueOf(String str) {
        return (EnumC0017h0) Enum.valueOf(EnumC0017h0.class, str);
    }

    public static EnumC0017h0[] values() {
        return (EnumC0017h0[]) f182n.clone();
    }
}
