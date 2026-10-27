package y0;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: renamed from: y0.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class EnumC2731i {
    public static final EnumC2731i l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final EnumC2731i f24067m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final EnumC2731i f24068n;
    public static final /* synthetic */ EnumC2731i[] o;

    static {
        EnumC2731i enumC2731i = new EnumC2731i("Initial", 0);
        l = enumC2731i;
        EnumC2731i enumC2731i2 = new EnumC2731i("Main", 1);
        f24067m = enumC2731i2;
        EnumC2731i enumC2731i3 = new EnumC2731i("Final", 2);
        f24068n = enumC2731i3;
        o = new EnumC2731i[]{enumC2731i, enumC2731i2, enumC2731i3};
    }

    public static EnumC2731i valueOf(String str) {
        return (EnumC2731i) Enum.valueOf(EnumC2731i.class, str);
    }

    public static EnumC2731i[] values() {
        return (EnumC2731i[]) o.clone();
    }
}
