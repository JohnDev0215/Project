package Q;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class L0 {
    public static final L0 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final L0 f5573m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final L0 f5574n;
    public static final /* synthetic */ L0[] o;

    static {
        L0 l02 = new L0("Tabs", 0);
        l = l02;
        L0 l03 = new L0("Divider", 1);
        f5573m = l03;
        L0 l04 = new L0("Indicator", 2);
        f5574n = l04;
        o = new L0[]{l02, l03, l04};
    }

    public static L0 valueOf(String str) {
        return (L0) Enum.valueOf(L0.class, str);
    }

    public static L0[] values() {
        return (L0[]) o.clone();
    }
}
