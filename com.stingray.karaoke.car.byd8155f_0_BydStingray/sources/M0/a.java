package M0;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static final a l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final a f4182m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final /* synthetic */ a[] f4183n;

    static {
        a aVar = new a("On", 0);
        l = aVar;
        a aVar2 = new a("Off", 1);
        f4182m = aVar2;
        f4183n = new a[]{aVar, aVar2, new a("Indeterminate", 2)};
    }

    public static a valueOf(String str) {
        return (a) Enum.valueOf(a.class, str);
    }

    public static a[] values() {
        return (a[]) f4183n.clone();
    }
}
