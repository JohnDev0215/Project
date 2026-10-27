package E0;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class r0 {
    public static final r0 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final r0 f1697m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final r0 f1698n;
    public static final /* synthetic */ r0[] o;

    static {
        r0 r0Var = new r0("ContinueTraversal", 0);
        l = r0Var;
        r0 r0Var2 = new r0("SkipSubtreeAndContinueTraversal", 1);
        f1697m = r0Var2;
        r0 r0Var3 = new r0("CancelTraversal", 2);
        f1698n = r0Var3;
        o = new r0[]{r0Var, r0Var2, r0Var3};
    }

    public static r0 valueOf(String str) {
        return (r0) Enum.valueOf(r0.class, str);
    }

    public static r0[] values() {
        return (r0[]) o.clone();
    }
}
