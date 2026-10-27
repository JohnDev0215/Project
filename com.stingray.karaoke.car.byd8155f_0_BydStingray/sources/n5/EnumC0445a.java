package N5;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: renamed from: N5.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class EnumC0445a {
    public static final EnumC0445a l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final EnumC0445a f4678m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final EnumC0445a f4679n;
    public static final /* synthetic */ EnumC0445a[] o;

    static {
        EnumC0445a enumC0445a = new EnumC0445a("SUCCESS", 0);
        l = enumC0445a;
        EnumC0445a enumC0445a2 = new EnumC0445a("FAILURE", 1);
        f4678m = enumC0445a2;
        EnumC0445a enumC0445a3 = new EnumC0445a("CANCEL", 2);
        f4679n = enumC0445a3;
        EnumC0445a[] enumC0445aArr = {enumC0445a, enumC0445a2, enumC0445a3};
        o = enumC0445aArr;
        h6.r.e(enumC0445aArr);
    }

    public static EnumC0445a valueOf(String str) {
        return (EnumC0445a) Enum.valueOf(EnumC0445a.class, str);
    }

    public static EnumC0445a[] values() {
        return (EnumC0445a[]) o.clone();
    }
}
