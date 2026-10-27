package Q;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: renamed from: Q.y0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class EnumC0542y0 {
    public static final EnumC0542y0 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final EnumC0542y0 f6071m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final EnumC0542y0 f6072n;
    public static final /* synthetic */ EnumC0542y0[] o;

    static {
        EnumC0542y0 enumC0542y0 = new EnumC0542y0("StartToEnd", 0);
        l = enumC0542y0;
        EnumC0542y0 enumC0542y02 = new EnumC0542y0("EndToStart", 1);
        f6071m = enumC0542y02;
        EnumC0542y0 enumC0542y03 = new EnumC0542y0("Settled", 2);
        f6072n = enumC0542y03;
        o = new EnumC0542y0[]{enumC0542y0, enumC0542y02, enumC0542y03};
    }

    public static EnumC0542y0 valueOf(String str) {
        return (EnumC0542y0) Enum.valueOf(EnumC0542y0.class, str);
    }

    public static EnumC0542y0[] values() {
        return (EnumC0542y0[]) o.clone();
    }
}
