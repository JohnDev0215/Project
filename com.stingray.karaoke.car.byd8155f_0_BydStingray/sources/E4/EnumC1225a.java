package e4;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: renamed from: e4.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class EnumC1225a {
    public static final EnumC1225a l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final EnumC1225a f14427m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final EnumC1225a f14428n;
    public static final /* synthetic */ EnumC1225a[] o;

    static {
        EnumC1225a enumC1225a = new EnumC1225a("PREFER_ARGB_8888", 0);
        l = enumC1225a;
        EnumC1225a enumC1225a2 = new EnumC1225a("PREFER_RGB_565", 1);
        f14427m = enumC1225a2;
        o = new EnumC1225a[]{enumC1225a, enumC1225a2};
        f14428n = enumC1225a;
    }

    public static EnumC1225a valueOf(String str) {
        return (EnumC1225a) Enum.valueOf(EnumC1225a.class, str);
    }

    public static EnumC1225a[] values() {
        return (EnumC1225a[]) o.clone();
    }
}
