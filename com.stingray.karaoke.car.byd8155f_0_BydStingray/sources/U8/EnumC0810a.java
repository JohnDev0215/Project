package U8;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: renamed from: U8.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class EnumC0810a {
    public static final EnumC0810a l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final EnumC0810a f9223m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final EnumC0810a f9224n;
    public static final /* synthetic */ EnumC0810a[] o;

    static {
        EnumC0810a enumC0810a = new EnumC0810a("SUSPEND", 0);
        l = enumC0810a;
        EnumC0810a enumC0810a2 = new EnumC0810a("DROP_OLDEST", 1);
        f9223m = enumC0810a2;
        EnumC0810a enumC0810a3 = new EnumC0810a("DROP_LATEST", 2);
        f9224n = enumC0810a3;
        EnumC0810a[] enumC0810aArr = {enumC0810a, enumC0810a2, enumC0810a3};
        o = enumC0810aArr;
        h6.r.e(enumC0810aArr);
    }

    public static EnumC0810a valueOf(String str) {
        return (EnumC0810a) Enum.valueOf(EnumC0810a.class, str);
    }

    public static EnumC0810a[] values() {
        return (EnumC0810a[]) o.clone();
    }
}
