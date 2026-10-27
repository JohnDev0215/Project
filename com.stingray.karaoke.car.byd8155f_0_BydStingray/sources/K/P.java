package K;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class P {
    public static final P l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final P f3194m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final P f3195n;
    public static final /* synthetic */ P[] o;

    static {
        P p10 = new P("None", 0);
        l = p10;
        P p11 = new P("Selection", 1);
        f3194m = p11;
        P p12 = new P("Cursor", 2);
        f3195n = p12;
        o = new P[]{p10, p11, p12};
    }

    public static P valueOf(String str) {
        return (P) Enum.valueOf(P.class, str);
    }

    public static P[] values() {
        return (P[]) o.clone();
    }
}
