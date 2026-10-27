package R;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class P {
    public static final P l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final /* synthetic */ P[] f7284m;

    static {
        P p10 = new P("Filled", 0);
        l = p10;
        f7284m = new P[]{p10, new P("Outlined", 1)};
    }

    public static P valueOf(String str) {
        return (P) Enum.valueOf(P.class, str);
    }

    public static P[] values() {
        return (P[]) f7284m.clone();
    }
}
