package r7;

import h6.r;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: renamed from: r7.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class EnumC2303a {
    public static final EnumC2303a l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final EnumC2303a f21872m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final EnumC2303a f21873n;
    public static final /* synthetic */ EnumC2303a[] o;

    static {
        EnumC2303a enumC2303a = new EnumC2303a("COROUTINE_SUSPENDED", 0);
        l = enumC2303a;
        EnumC2303a enumC2303a2 = new EnumC2303a("UNDECIDED", 1);
        f21872m = enumC2303a2;
        EnumC2303a enumC2303a3 = new EnumC2303a("RESUMED", 2);
        f21873n = enumC2303a3;
        EnumC2303a[] enumC2303aArr = {enumC2303a, enumC2303a2, enumC2303a3};
        o = enumC2303aArr;
        r.e(enumC2303aArr);
    }

    public static EnumC2303a valueOf(String str) {
        return (EnumC2303a) Enum.valueOf(EnumC2303a.class, str);
    }

    public static EnumC2303a[] values() {
        return (EnumC2303a[]) o.clone();
    }
}
