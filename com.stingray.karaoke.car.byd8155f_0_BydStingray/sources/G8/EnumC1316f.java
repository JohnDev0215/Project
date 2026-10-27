package g8;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: renamed from: g8.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class EnumC1316f {
    public static final EnumC1316f l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final EnumC1316f f15004m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final /* synthetic */ EnumC1316f[] f15005n;

    static {
        EnumC1316f enumC1316f = new EnumC1316f("READ_ONLY", 0);
        l = enumC1316f;
        EnumC1316f enumC1316f2 = new EnumC1316f("MUTABLE", 1);
        f15004m = enumC1316f2;
        f15005n = new EnumC1316f[]{enumC1316f, enumC1316f2};
    }

    public static EnumC1316f valueOf(String str) {
        return (EnumC1316f) Enum.valueOf(EnumC1316f.class, str);
    }

    public static EnumC1316f[] values() {
        return (EnumC1316f[]) f15005n.clone();
    }
}
