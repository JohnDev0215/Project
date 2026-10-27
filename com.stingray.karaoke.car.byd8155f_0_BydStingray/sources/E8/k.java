package E8;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class k {
    public static final k l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final k f1857m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final k f1858n;
    public static final /* synthetic */ k[] o;

    static {
        k kVar = new k("NOT_COMPUTED", 0);
        l = kVar;
        k kVar2 = new k("COMPUTING", 1);
        f1857m = kVar2;
        k kVar3 = new k("RECURSION_WAS_DETECTED", 2);
        f1858n = kVar3;
        o = new k[]{kVar, kVar2, kVar3};
    }

    public static k valueOf(String str) {
        return (k) Enum.valueOf(k.class, str);
    }

    public static k[] values() {
        return (k[]) o.clone();
    }
}
