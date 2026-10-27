package Q;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class V {
    public static final V l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final V f5709m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final /* synthetic */ V[] f5710n;

    static {
        V v10 = new V("THUMB", 0);
        l = v10;
        V v11 = new V("TRACK", 1);
        f5709m = v11;
        f5710n = new V[]{v10, v11};
    }

    public static V valueOf(String str) {
        return (V) Enum.valueOf(V.class, str);
    }

    public static V[] values() {
        return (V[]) f5710n.clone();
    }
}
