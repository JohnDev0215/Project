package K;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class O {
    public static final O l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final O f3190m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final O f3191n;
    public static final /* synthetic */ O[] o;

    static {
        O o4 = new O("Cursor", 0);
        l = o4;
        O o9 = new O("SelectionStart", 1);
        f3190m = o9;
        O o10 = new O("SelectionEnd", 2);
        f3191n = o10;
        o = new O[]{o4, o9, o10};
    }

    public static O valueOf(String str) {
        return (O) Enum.valueOf(O.class, str);
    }

    public static O[] values() {
        return (O[]) o.clone();
    }
}
