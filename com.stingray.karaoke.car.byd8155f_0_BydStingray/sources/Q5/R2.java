package Q5;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class R2 {
    public static final R2 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final /* synthetic */ R2[] f6532m;

    static {
        R2 r22 = new R2("DEFAULT", 0);
        l = r22;
        R2[] r2Arr = {r22, new R2("ACCENT", 1)};
        f6532m = r2Arr;
        h6.r.e(r2Arr);
    }

    public static R2 valueOf(String str) {
        return (R2) Enum.valueOf(R2.class, str);
    }

    public static R2[] values() {
        return (R2[]) f6532m.clone();
    }
}
