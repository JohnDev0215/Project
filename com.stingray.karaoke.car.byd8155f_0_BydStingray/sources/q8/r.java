package q8;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public abstract class r {
    public static final q l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final C2212p f21350m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final /* synthetic */ r[] f21351n;

    static {
        q qVar = new q();
        l = qVar;
        C2212p c2212p = new C2212p();
        f21350m = c2212p;
        f21351n = new r[]{qVar, c2212p};
    }

    public static r valueOf(String str) {
        return (r) Enum.valueOf(r.class, str);
    }

    public static r[] values() {
        return (r[]) f21351n.clone();
    }

    public abstract String a(String str);
}
