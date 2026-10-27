package q8;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: renamed from: q8.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class EnumC2209m {
    public static final EnumC2209m l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final EnumC2209m f21344m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final /* synthetic */ EnumC2209m[] f21345n;

    static {
        EnumC2209m enumC2209m = new EnumC2209m("RENDER_OVERRIDE", 0);
        l = enumC2209m;
        EnumC2209m enumC2209m2 = new EnumC2209m("RENDER_OPEN", 1);
        f21344m = enumC2209m2;
        f21345n = new EnumC2209m[]{enumC2209m, enumC2209m2, new EnumC2209m("RENDER_OPEN_OVERRIDE", 2)};
    }

    public static EnumC2209m valueOf(String str) {
        return (EnumC2209m) Enum.valueOf(EnumC2209m.class, str);
    }

    public static EnumC2209m[] values() {
        return (EnumC2209m[]) f21345n.clone();
    }
}
