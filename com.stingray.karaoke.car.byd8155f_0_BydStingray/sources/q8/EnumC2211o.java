package q8;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: renamed from: q8.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class EnumC2211o {
    public static final EnumC2211o l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final EnumC2211o f21348m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final /* synthetic */ EnumC2211o[] f21349n;

    /* JADX INFO: Fake field, exist only in values array */
    EnumC2211o EF0;

    static {
        EnumC2211o enumC2211o = new EnumC2211o("PRETTY", 0);
        EnumC2211o enumC2211o2 = new EnumC2211o("DEBUG", 1);
        l = enumC2211o2;
        EnumC2211o enumC2211o3 = new EnumC2211o("NONE", 2);
        f21348m = enumC2211o3;
        f21349n = new EnumC2211o[]{enumC2211o, enumC2211o2, enumC2211o3};
    }

    public static EnumC2211o valueOf(String str) {
        return (EnumC2211o) Enum.valueOf(EnumC2211o.class, str);
    }

    public static EnumC2211o[] values() {
        return (EnumC2211o[]) f21349n.clone();
    }
}
