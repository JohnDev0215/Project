package S8;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: renamed from: S8.x, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class EnumC0726x {
    public static final EnumC0726x l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final EnumC0726x f8141m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final EnumC0726x f8142n;
    public static final EnumC0726x o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final /* synthetic */ EnumC0726x[] f8143p;

    static {
        EnumC0726x enumC0726x = new EnumC0726x("DEFAULT", 0);
        l = enumC0726x;
        EnumC0726x enumC0726x2 = new EnumC0726x("LAZY", 1);
        f8141m = enumC0726x2;
        EnumC0726x enumC0726x3 = new EnumC0726x("ATOMIC", 2);
        f8142n = enumC0726x3;
        EnumC0726x enumC0726x4 = new EnumC0726x("UNDISPATCHED", 3);
        o = enumC0726x4;
        EnumC0726x[] enumC0726xArr = {enumC0726x, enumC0726x2, enumC0726x3, enumC0726x4};
        f8143p = enumC0726xArr;
        h6.r.e(enumC0726xArr);
    }

    public static EnumC0726x valueOf(String str) {
        return (EnumC0726x) Enum.valueOf(EnumC0726x.class, str);
    }

    public static EnumC0726x[] values() {
        return (EnumC0726x[]) f8143p.clone();
    }
}
